package jp.co.flect.hypdf.transport;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import jp.co.flect.hypdf.json.JsonUtils;
import jp.co.flect.hypdf.HyPDFException;

import com.ning.http.client.Response;
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.AsyncHttpClientConfig;
import com.ning.http.client.ProxyServer;
import com.ning.http.multipart.StringPart;
import com.ning.http.multipart.FilePart;

public class AsyncHttpClientTransport extends AbstractTransport {
	
	private AsyncHttpClient client = null;
	
	private void checkResponse(Response res) throws IOException {
System.out.println("checkResponse: " + res.getStatusCode() + ", " + res.getContentType());
		if (res.getStatusCode() != 200) {
			String body = res.getResponseBody("utf-8");
			String msg = null;
			try {
				Map<String, Object> map = JsonUtils.parse(body);
				msg = (String)map.get("message");
			} catch (Exception e) {
				e.printStackTrace();
			}
			throw new HyPDFException(res.getStatusCode(), msg == null ? body : msg);
		}
	}
	
	public InputStream streamRequest(String url, Map<String, Object> params, File... pdfFiles) throws IOException {
		try {
			if (pdfFiles == null || pdfFiles.length == 0) {
				Response res = simpleRequest(url, params);
				return res.getResponseBodyAsStream();
			} else {
				Response res = multipartRequest(url, params, pdfFiles);
				return res.getResponseBodyAsStream();
			}
		} catch (InterruptedException e) {
			throw new IOException(e);
		} catch (ExecutionException e) {
			throw new IOException(e);
		}
	}
	
	public Map<String, Object> jsonRequest(String url, Map<String, Object> params, File... pdfFiles) throws IOException {
		try {
			Response res = null;
			if (pdfFiles == null || pdfFiles.length == 0) {
				res = simpleRequest(url, params);
			} else {
				res = multipartRequest(url, params, pdfFiles);
			}
			String body = res.getResponseBody("utf-8");
			return JsonUtils.parse(body);
		} catch (InterruptedException e) {
			throw new IOException(e);
		} catch (ExecutionException e) {
			throw new IOException(e);
		}
	}
	
	private Response simpleRequest(String url, Map<String, Object> params) throws IOException, InterruptedException, ExecutionException {
		AsyncHttpClient client = getHttpClient();
		AsyncHttpClient.BoundRequestBuilder builder = client.preparePost(url);

		String json = JsonUtils.serialize(params);
		builder.setBody(json);
		builder.setHeader("content-type", "application/json");

		Response res = builder.execute().get();
		checkResponse(res);
		return res;
	}
	
	private Response multipartRequest(String url, Map<String, Object> params, File... pdfFiles) throws IOException, InterruptedException, ExecutionException {
		AsyncHttpClient client = getHttpClient();
		AsyncHttpClient.BoundRequestBuilder builder = client.preparePost(url);
		builder.setHeader("content-type", "multipart/form-data");

		for (Map.Entry<String, Object> entry : params.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();
			builder.addBodyPart(new StringPart(key, value.toString(), "utf-8"));
		}
		int index = 1;
		for (File f : pdfFiles) {
			String filename = f.getName();
			String key = "file";
			if (pdfFiles.length > 1) {
				key += "_" + index;
				index++;
			}
			builder.addBodyPart(new FilePart(key, filename, f, "application/pdf", "utf-8"));
		}
		Response res = builder.execute().get();
		checkResponse(res);
		return res;
	}
	
	private AsyncHttpClient getHttpClient() {
		if (this.client == null) {
			AsyncHttpClientConfig.Builder builder = new AsyncHttpClientConfig.Builder();
			if (getConnectionTimeout() > 0) {
				builder.setConnectionTimeoutInMs(getConnectionTimeout());
			}
			if (getSoTimeout() > 0) {
				builder.setRequestTimeoutInMs(getSoTimeout());
			}
			if (getProxyInfo() != null) {
				ProxyInfo proxyInfo = getProxyInfo();
				ProxyServer proxy = null;
				if (proxyInfo.getUserName() != null && proxyInfo.getPassword() != null) {
					proxy = new ProxyServer(proxyInfo.getHost(), proxyInfo.getPort(),
						proxyInfo.getUserName(), proxyInfo.getPassword());
				} else {
					proxy = new ProxyServer(proxyInfo.getHost(), proxyInfo.getPort());
				}
				builder.setProxyServer(proxy);
			}
			this.client = new AsyncHttpClient(builder.build());
		}
		return this.client;
	}
	
}

