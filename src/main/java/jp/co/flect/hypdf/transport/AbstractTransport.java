package jp.co.flect.hypdf.transport;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public abstract class AbstractTransport implements Transport {

	private ProxyInfo proxyInfo = null;
	private int soTimeout = 0;
	private int connectionTimeout = 0;

	public ProxyInfo getProxyInfo() { return this.proxyInfo;}
	public void setProxyInfo(ProxyInfo proxy) { this.proxyInfo = proxy;}

	public int getSoTimeout() { return this.soTimeout;}
	public void setSoTimeout(int n) { this.soTimeout = n;}
	
	public int getConnectionTimeout() { return this.connectionTimeout;}
	public void setConnectionTimeout(int n) { this.connectionTimeout = n;}
}