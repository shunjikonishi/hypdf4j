package jp.co.flect.hypdf.transport;

import java.io.File;
import java.io.InputStream;
import java.io.IOException;
import java.util.Map;

public interface Transport {
	
	public InputStream streamRequest(String url, Map<String, Object> params, File... pdfFiles) throws IOException;
	public Map<String, Object> jsonRequest(String url, Map<String, Object> params, File... pdfFiles) throws IOException;
	
	public boolean isIgnoreHostNameValidation();
	public void setIgnoreHostNameValidation(boolean b);

	public ProxyInfo getProxyInfo();
	public void setProxyInfo(ProxyInfo proxy);
}

