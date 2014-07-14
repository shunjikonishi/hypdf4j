package jp.co.flect.hypdf;

import java.io.IOException;
import java.io.File;
import java.util.Map;
import java.util.HashMap;
import java.net.URL;

import jp.co.flect.hypdf.transport.TransportFactory;
import jp.co.flect.hypdf.transport.Transport;
import jp.co.flect.hypdf.model.HyPDFOption;
import jp.co.flect.hypdf.model.JobStatus;
import jp.co.flect.hypdf.model.PdfResponse;

public class HyPDF {
	
	private static final String URL_BASE = "https://www.hypdf.com/";
	private String username;
	private String password;
	private Transport transport;
	private boolean testMode = false;

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

	public PdfResponse htmlToPdf(String content) throws IOException {
		return htmlToPdf(content, null);
	}

	public PdfResponse htmlToPdf(String content, HyPDFOption.HtmlToPdf option) throws IOException {
		Map<String, Object> params = createParams();
		params.put("content", content);
		if (option != null) {
			option.apply(params);
		}
		return transport.streamRequest(URL_BASE + "htmltopdf", params);
	}

	public String notifyHtmlToPdf(String content, URL callbackUrl) throws IOException {
		return notifyHtmlToPdf(content, callbackUrl, null);
	}

	public String notifyHtmlToPdf(String content, URL callbackUrl, HyPDFOption.HtmlToPdf option) throws IOException {
		Map<String, Object> params = createParams();
		params.put("content", content);
		params.put("callback", callbackUrl.toString());
		if (option != null) {
			option.apply(params);
		}
		Map<String, Object> json = transport.jsonRequest(URL_BASE + "htmltopdf", params);
		return (String)json.get("job_id");
	}

	public JobStatus jobstatus(String jobId) throws IOException {
		Map<String, Object> params = createParams();
		params.put("job_id", jobId);
		Map<String, Object> json = transport.jsonRequest(URL_BASE + "jobstatus", params);
		String status = (String)json.get("status");
		return JobStatus.valueOf(status);
	}

	public Map<String, Object> pdfInfo(File file) throws IOException {
		Map<String, Object> params = createParams();
		return transport.jsonRequest(URL_BASE + "pdfinfo", params, file);
	}

	public Map<String, Object> pdfToText(File file) throws IOException {
		return pdfToText(file, null);
	}

	public Map<String, Object> pdfToText(File file, HyPDFOption.PdfToText option) throws IOException {
		Map<String, Object> params = createParams();
		if (option != null) {
			option.apply(params);
		}
		return transport.jsonRequest(URL_BASE + "pdftotext", params, file);
	}

	public PdfResponse pdfExtract(File file, int firstPage, int lastPage) throws IOException {
		HyPDFOption.PdfExtract option = new HyPDFOption.PdfExtract();
		option.first_page = firstPage;
		option.last_page = lastPage;
		return pdfExtract(file, option);
	}

	public PdfResponse pdfExtract(File file, HyPDFOption.PdfExtract option) throws IOException {
		Map<String, Object> params = createParams();
		if (option != null) {
			option.apply(params);
		}
		return transport.streamRequest(URL_BASE + "pdfextract", params, file);
	}

	public PdfResponse pdfUnite(File... files) throws IOException {
		return pdfUnite(null, files);
	}

	public PdfResponse pdfUnite(HyPDFOption.PdfExtract option, File... files) throws IOException {
		Map<String, Object> params = createParams();
		if (option != null) {
			option.apply(params);
		}
		return transport.streamRequest(URL_BASE + "pdfunite", params, files);
	}

}

