package jp.co.flect.hypdf.transport;

public abstract class TransportFactory {
	
	private static TransportFactory DEFAULT_FACTORY;
	private static boolean IGNORE_SSL;

	static {
		TransportFactory ret = null;
		if (ret == null) {
			try {
				Class.forName("org.apache.http.impl.client.DefaultHttpClient");
				ret = new HttpClientTransportFactory();
			} catch (Exception e) {
			}
		}
		DEFAULT_FACTORY = ret;
	}

	public static TransportFactory getDefaultFactory() { return DEFAULT_FACTORY;}
	public static void setDefaultFactory(TransportFactory v) { DEFAULT_FACTORY = v;}

	public static boolean isAlwaysIgnoreHostNameVerification() { return IGNORE_SSL;}
	public static void setAlwaysIgnoreHostNameVerification(boolean b) { IGNORE_SSL = b;}

	public static Transport createDefaultTransport() {
		if (DEFAULT_FACTORY == null) {
			throw new IllegalStateException("Http client libraries not found.");
		}
		return DEFAULT_FACTORY.create();
	}

	public abstract Transport create();

	public static class HttpClientTransportFactory extends TransportFactory {
		public Transport create() { 
			Transport t = new HttpClientTransport();
			if (IGNORE_SSL) {
				t.setIgnoreHostNameVerification(true);
			}
			return t;
		}
	}

}

