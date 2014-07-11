package jp.co.flect.hypdf.model;

import java.io.InputStream;

public class PdfResponse {
	private int pages;
	private String pageSize;
	private String pdfVersion;
	private InputStream content;

	public PdfResponse(int pages, String pageSize, String pdfVersion) {
		this.pages = pages;
		this.pageSize = pageSize;
		this.pdfVersion = pdfVersion;
	}

	public int getPages() { return this.pages;}
	public String getPageSize() { return this.pageSize;}
	public String getPdfVersion() { return this.pdfVersion;}

	public InputStream getContent() { return this.content;}
	public void setContent(InputStream is) { this.content = is;}
	public boolean hasContent() { return this.content != null;}

}