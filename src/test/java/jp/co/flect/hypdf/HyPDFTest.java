package jp.co.flect.hypdf;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.net.URL;

import jp.co.flect.hypdf.transport.Transport;
import jp.co.flect.hypdf.transport.TransportFactory;
import jp.co.flect.hypdf.transport.HttpClientTransport;
import jp.co.flect.hypdf.model.HtmlToPdfOption;
import jp.co.flect.hypdf.model.JobStatus;

public class HyPDFTest {

	public static String USERNAME;
	public static String PASSWORD;
	public static File OUTPUT_DIR;

	static {
		try {
			Properties props = new Properties();
			
			InputStream is = new FileInputStream(new File("test.properties"));
			try {
				props.load(is);
			} finally {
				is.close();
			}
			USERNAME  = props.getProperty("HYPDF_USER");
			PASSWORD  = props.getProperty("HYPDF_PASSWORD");
			
			assertNotNull(USERNAME);
			assertNotNull(PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}
		OUTPUT_DIR = new File("target/testoutput");
		OUTPUT_DIR.mkdirs();

//		System.setProperty ("jsse.enableSNIExtension", "false");
	}
	
	private void log(String msg) {
		System.out.println("=== " + msg);
	}

	@Test
	public void testHttpClient() {
		log("*** Test HttpClient");
		TransportFactory.setDefaultFactory(new TransportFactory.HttpClientTransportFactory());
		doTest();
	}

	//@Test
	public void testAsyncHttp() {
		log("*** Test AsyncHttp");
		TransportFactory.setDefaultFactory(new TransportFactory.AsyncHttpClientTransportFactory());
		doTest();
	}

	private void doTest() {
		loginError();

		HyPDF hypdf = createHyPDF(USERNAME, PASSWORD);
		//simpleHtml(hypdf);
		//callback(hypdf);
		pdfInfo(hypdf);
	}

	private HyPDF createHyPDF(String username, String password) {
		HyPDF hypdf = new HyPDF(username, password);
		hypdf.setTestMode(true);
		Transport t = hypdf.getTransport();
		if (t instanceof HttpClientTransport) {
			((HttpClientTransport)t).setIgnoreHostNameValidation(true);
		}
		return hypdf;
	}

	private void loginError() {
		log("loginError");
		try {
			HyPDF hypdf = createHyPDF("", "");
			hypdf.htmlToPdf("dummy");
			fail();
		} catch (HyPDFException e) {
			log("loginError response: " + e.getStatusCode() + ", " + e.getMessage());
			assertEquals(401, e.getStatusCode());
			assertNotNull(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}
	}

	private void simpleHtml(HyPDF hypdf) {
		log("simpleHtml");
		String content = "https://excel-report2.herokuapp.com/report/html/sample/chart?score=50&score=40&score=60&score=70&score=80&score=82&score=73&score=94&score=100&score=62&score=72&score=43&score=22&score=84&score=63&app.print=false&app.download=false&app.webfont=true&app.border=false";
		File file = new File(OUTPUT_DIR, "simpleHtml.pdf");
		try {
			HtmlToPdfOption option = new HtmlToPdfOption();
			hypdf.htmlToPdfFile(content, file, option);
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}
	}

	private void callback(HyPDF hypdf) {
		log("callback");
		String content = "https://excel-report2.herokuapp.com/report/html/sample/chart?score=50&score=40&score=60&score=70&score=80&score=82&score=73&score=94&score=100&score=62&score=72&score=43&score=22&score=84&score=63&app.print=false&app.download=false&app.webfont=true&app.border=false";
		try {
			URL url = new URL("http://flect-panda.herokuapp.com/");
			String jobId = hypdf.notifyHtmlToPdf(content, url);
			System.out.println("job_id: " + jobId);
			assertNotNull(jobId);
			JobStatus status = hypdf.jobstatus(jobId);
			System.out.println("status: " + status);
			assertNotNull(status);
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}
	}

	private void pdfInfo(HyPDF hypdf) {
		log("pdfInfo");
		File file = new File(OUTPUT_DIR, "simpleHtml.pdf");
		try {
			Map<String, Object> ret = hypdf.pdfInfo(file);
			System.out.println(ret);
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}
	}
}