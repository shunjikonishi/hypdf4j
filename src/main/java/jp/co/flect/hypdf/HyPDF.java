package jp.co.flect.hypdf;

import java.io.IOException;
import java.io.File;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.util.Map;
import java.util.HashMap;

import jp.co.flect.hypdf.transport.TransportFactory;
import jp.co.flect.hypdf.transport.Transport;
import jp.co.flect.hypdf.model.HtmlToPdfOption;

public class HyPDF {
	
	private static final String URL_BASE = "https://www.hypdf.com/";
	private String username;
	private String password;
	private Transport transport;
	private boolean testMode = false;

	/*
	static {
		System.setProperty ("jsse.enableSNIExtension", "false");
	}
	*/

	public HyPDF(String username, String password) {
		this.username = username;
		this.password = password;
		this.transport = TransportFactory.createDefaultTransport();
	}

	public boolean isTestMode() { return this.testMode;}
	public void setTestMode(boolean b) { this.testMode = b;}

	public Transport getTransport() { return this.transport;}

	private Map<String, Object> createParams() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("user", this.username);
		params.put("password", this.password);
		if (this.testMode) {
			params.put("test", true);
		}
		return params;
	}

	public InputStream htmlToPdf(String content) throws IOException {
		return htmlToPdf(content, null);
	}

	public InputStream htmlToPdf(String content, HtmlToPdfOption option) throws IOException {
		Map<String, Object> params = createParams();
		params.put("content", content);
		if (option != null) {
			option.apply(params);
		}
		return transport.streamRequest(URL_BASE + "htmltopdf", params);
	}

	public void htmlToPdfFile(String content, File outputFile) throws IOException {
		htmlToPdfFile(content, outputFile, null);
	}

	public void htmlToPdfFile(String content, File outputFile, HtmlToPdfOption option) throws IOException {
		InputStream is = htmlToPdf(content, option);
		try {
			FileOutputStream os = new FileOutputStream(outputFile);
			try {
				byte[] buf = new byte[8192];
				int n = is.read(buf);
				while (n != -1) {
					os.write(buf, 0, n);
					n = is.read(buf);
				}
			} finally {
				os.close();
			}
		} finally {
			is.close();
		}
	}

}
