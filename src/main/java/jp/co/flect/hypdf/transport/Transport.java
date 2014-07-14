package jp.co.flect.hypdf.transport;

import java.io.File;
import java.io.InputStream;
import java.io.IOException;
import java.util.Map;
import jp.co.flect.hypdf.model.PdfResponse;

public interface Transport {
	
	public PdfResponse streamRequest(String url, Map<String, Object> params, File... pdfFiles) throws IOException;
	public Map<String, Object> jsonRequest(String url, Map<String, Object> params, File... pdfFiles) throws IOException;
	
	public boolean isIgnoreHostNameVerification();
	public void setIgnoreHostNameVerification(boolean b);

	public ProxyInfo getProxyInfo();
	public void setProxyInfo(ProxyInfo proxy);
}

