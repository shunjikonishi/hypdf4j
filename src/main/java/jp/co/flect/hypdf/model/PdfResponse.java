package jp.co.flect.hypdf.model;

import java.io.File;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.IOException;


public class PdfResponse {
	private int pages;
	private String pageSize;
	private String pdfVersion;
	private InputStream content;

	public PdfResponse(int pages, String pageSize, String pdfVersion, InputStream content) {
		this.pages = pages;
		this.pageSize = pageSize;
		this.pdfVersion = pdfVersion;
		this.content = content;
	}

	public int getPages() { return this.pages;}
	public String getPageSize() { return this.pageSize;}
	public String getPdfVersion() { return this.pdfVersion;}

	public InputStream getContent() { return this.content;}
	public boolean hasContent() { return this.content != null;}

	public void writeTo(File file) throws IOException {
		writeTo(new FileOutputStream(file));
	}

	public void writeTo(OutputStream os) throws IOException {
		InputStream is = getContent();
		if (is == null) {
			throw new IllegalStateException("InputStream was consumed.");
		}
		try {
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
			this.content = null;
		}
	}
}