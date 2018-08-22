package sk.test.springmvc.testdate;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class TestDateBinder {
	public static void main(String[] args) throws Exception {
		formPost();
//		json();
	}

	private static void formPost() throws UnsupportedEncodingException,
			IOException, ClientProtocolException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost("http://localhost:9091/hi-de/date");
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		parameters.add(new BasicNameValuePair("id", "114"));
		parameters.add(new BasicNameValuePair("name", "114"));
		parameters.add(new BasicNameValuePair("birth", "2010-10-09 10:12:22"));
		UrlEncodedFormEntity uefe = new UrlEncodedFormEntity(parameters, "utf-8");
		httpPost.setEntity(uefe);
		String execute = httpClient.execute(httpPost, new ResponseHandler<String>() {

			@Override
			public String handleResponse(HttpResponse response)
					throws ClientProtocolException, IOException {
				StatusLine line = response.getStatusLine();
				int status = line.getStatusCode();
				if(200 == status) {
					HttpEntity e = response.getEntity();
					return EntityUtils.toString(e);
				}
				return "错误";
			}
		});
		System.out.println(execute);
	}
	private static void json() throws UnsupportedEncodingException,
	IOException, ClientProtocolException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost("http://localhost:9091/hi-de/date3");

		StringEntity se = new StringEntity("{\"name\":\"dsd\",\"id\":\"121\",\"birth\":\"2018-09-12 10:10\"}", "utf-8");
		se.setContentType("application/json;charset=utf-8");
		httpPost.setEntity(se);
		String execute = httpClient.execute(httpPost, new ResponseHandler<String>() {
			
			@Override
			public String handleResponse(HttpResponse response)
					throws ClientProtocolException, IOException {
				StatusLine line = response.getStatusLine();
				int status = line.getStatusCode();
				HttpEntity e = response.getEntity();
				
				return EntityUtils.toString(e);
			}
		});
		System.out.println(execute);
	}
}
