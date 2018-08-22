package sk.test.httpClient;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class Learn_MultipartEntity {
	public static void main(String[] args) throws Exception {
		formPost();
	}

	private static void formPost() throws Exception {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost("http://localhost:9091/hi-de/file");
		
		MultipartEntityBuilder meb = MultipartEntityBuilder.create();
		meb.setCharset(Charset.forName("utf-8"));
		meb.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
		meb.addBinaryBody("file", new File("d:/哈哈.txt"));
		meb.addBinaryBody("file2", new File("d:/哈哈.txt"));
		meb.addPart("name", new StringBody("名字", ContentType.create("text/plain","utf-8")));
//		meb.addPart(new StringBody("{\"name\":\"dsd\",\"id\":\"121\",\"birth\":\"2018-09-12 10:10\"}", ContentType.create("application/json","utf-8")));
		HttpEntity me = meb.build();
		httpPost.setEntity(me);
		
		String execute = httpClient.execute(httpPost, new ResponseHandler<String>() {

			@Override
			public String handleResponse(HttpResponse response)
					throws ClientProtocolException, IOException {
				StatusLine line = response.getStatusLine();
				int status = line.getStatusCode();
				return EntityUtils.toString(response.getEntity());
			}
		});
		System.out.println(execute);
	}
	
}
