package sk.test.httpClient;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

@SuppressWarnings({"resource"}) //★ @SuppressWarnings("resource") 对于J2EE，可以使用@Resource来完成依赖注入或者叫资源注入，但是当你在一个类中使用已经使用注解的类，却没有为其注入依赖
public class Learn_HttpClient {
	public static void main(String[] args) {
//		poolConnectionManager();
//		poolConnectionManager2();
//		System.out.println(HttpClientContext.HTTP_TARGET_HOST);
		
//		socket();
		
//		sslSocketFactory();
//		sslWithoutValidation();
		
		//字符集常量
		System.out.println(Consts.UTF_8);
	}
	//★概念
	//路由：需要连接的主机的个数
	/*此处解释下MaxtTotal和DefaultMaxPerRoute的区别：
	   1、MaxtTotal是整个池子的大小；
	  	2、DefaultMaxPerRoute是根据连接到的主机对MaxTotal的一个细分；比如：
		MaxtTotal=400 DefaultMaxPerRoute=200
		而我只连接到http://sishuok.com时，到这个主机的并发最多只有200；而不是400；
		而我连接到http://sishuok.com 和 http://qq.com时，到每个主机的并发最多只有200；即加起来是400（但不能超过400）；所以起作用的设置是DefaultMaxPerRoute。 
	 */
	//1、使用keep-alive一定要设置Content-Length头（否则也不是长连接）。
	//2 EntityUtils.consume(response.getEntity()); //会自动释放连接
	//3 超过三种超时时间对应异常ConnectionPoolTimeoutException, ConnectionTimeoutException，SocketTimeoutException。
	//连接池管理器
	public static void poolConnectionManager() {
		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
		cm.setMaxTotal(20);
		cm.setDefaultMaxPerRoute(cm.getMaxTotal()); //=最大连接数/主机数量 是真正起作用的设置
		HttpHost host = new HttpHost("localhost", 9090);
//		cm.setMaxPerRoute(new HttpRoute(host), 50); //为特定主机设置最大连接数

		RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(8500).setConnectTimeout(5000).setSocketTimeout(3000).build();

		CloseableHttpClient httpclient = HttpClients.custom().setConnectionManager(cm).setDefaultRequestConfig(requestConfig).build();
		
		String url = "http://localhost:9090/hi-de/server";
		
		PostThread[] pts = new PostThread[100];
		for(int i = 1; i <=100 ; i ++) {
			PostThread postThread = new PostThread(httpclient, url);
			pts[i-1] = postThread;
			postThread.start();
		}
		for (PostThread postThread : pts) {
			try {
				postThread.join();
			} catch (InterruptedException e) {
				System.out.println("join出错");
			}
		}
	}
	public static void poolConnectionManager2() {
		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
		cm.setMaxTotal(20);
		cm.setDefaultMaxPerRoute(cm.getMaxTotal()); //=最大连接数/主机数量 是真正起作用的设置
		HttpHost host = new HttpHost("localhost", 9090);
//		cm.setMaxPerRoute(new HttpRoute(host), 50); //为特定主机设置最大连接数
		
		RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(5000).setConnectTimeout(5000).setSocketTimeout(3000).build();
		
		String url = "http://localhost:9090/hi-de/server";
		
		PostThread2[] pts = new PostThread2[100];
		for(int i = 1; i <=100 ; i ++) {
			PostThread2 postThread = new PostThread2(cm, requestConfig, url);
			pts[i-1] = postThread;
			postThread.start();
		}
		for (PostThread2 postThread : pts) {
			try {
				postThread.join();
			} catch (InterruptedException e) {
				System.out.println("join出错");
			}
		}
	}
	
	public static void  socket() {
		HttpClientContext clientContext = HttpClientContext.create();
	    PlainConnectionSocketFactory sf = PlainConnectionSocketFactory.getSocketFactory();
	    int timeout = 1000; //ms
	    Socket socket = null;
	    HttpHost target = null;
	    InetSocketAddress remoteAddress = null;
	    try {
	    socket = sf.createSocket(clientContext);
	    target = new HttpHost("localhost");
			remoteAddress = new InetSocketAddress(
			    InetAddress.getByName("localhost"), 9090);
		} catch (Exception e) {
			System.out.println(" cuowu ");
		}
        //connectSocket源码中，实际没有用到target参数
        try {
			sf.connectSocket(timeout, socket, target, remoteAddress, null, clientContext);
		} catch (IOException e) {
			System.out.println(" cuowu2 ");
		}
	}
	
	public static void sslSocketFactory() {
		 SSLContext ctx = null;
			try {
				ctx = SSLContext.getInstance("TLS");
			} catch (NoSuchAlgorithmException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}  
	         X509TrustManager tm = new X509TrustManager() {  
	             public X509Certificate[] getAcceptedIssuers() {  
	                 return null;  
	             }  

	             public void checkClientTrusted(X509Certificate[] arg0,  
	                     String arg1) throws CertificateException {  
	             }  

	             public void checkServerTrusted(X509Certificate[] arg0,  
	                     String arg1) throws CertificateException {  
	             }  
	         };  
	         try {
				ctx.init(null, new TrustManager[] { tm }, null);
			} catch (KeyManagementException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
		
		LayeredConnectionSocketFactory sslcsf = new SSLConnectionSocketFactory(ctx, NoopHostnameVerifier.INSTANCE);
		
		Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
				.register("http", PlainConnectionSocketFactory.INSTANCE)
				.register("https", sslcsf).build();
		
		PoolingHttpClientConnectionManager pcm = new PoolingHttpClientConnectionManager(registry);
		
		pcm.setMaxTotal(200);
		pcm.setDefaultMaxPerRoute(pcm.getMaxTotal());
		
		RequestConfig requestCfg = RequestConfig.custom().setConnectionRequestTimeout(2000).setConnectTimeout(5000).setSocketTimeout(3000).build();
		
		CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(pcm).setDefaultRequestConfig(requestCfg).build();
		
//		HttpPost post = new HttpPost("https://localhost:8443/hi-de/server");
		HttpPost post = new HttpPost("https://paymentnotify.1hai.cn/Payment/LianJin/Notify4LianJin.aspx");
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("kkk", "这是名"));
		nvps.add(new BasicNameValuePair("ssss", "这是xin"));
		
		UrlEncodedFormEntity uefe = null;
		try {
			uefe = new UrlEncodedFormEntity(nvps);
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		post.setEntity(uefe);
		
		try {
			CloseableHttpResponse res = httpClient.execute(post);
			System.out.println(res.getStatusLine().getStatusCode());
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void sslWithoutValidation() {
		 SSLContext ctx = null;
		try {
			ctx = SSLContext.getInstance("TLS");
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  
         X509TrustManager tm = new X509TrustManager() {  
             public X509Certificate[] getAcceptedIssuers() {  
                 return null;  
             }  

             public void checkClientTrusted(X509Certificate[] arg0,  
                     String arg1) throws CertificateException {  
             }  

             public void checkServerTrusted(X509Certificate[] arg0,  
                     String arg1) throws CertificateException {  
             }  
         };  
         try {
			ctx.init(null, new TrustManager[] { tm }, null);
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
        SSLConnectionSocketFactory ssf = new SSLConnectionSocketFactory(  
                 ctx, NoopHostnameVerifier.INSTANCE);  
         CloseableHttpClient httpclient = HttpClients.custom()  
                 .setSSLSocketFactory(ssf).build();  
         
         HttpPost httpPost = new HttpPost("https://localhost:8443/hi-de/server");
//         HttpPost httpPost = new HttpPost("https://paymentnotify.1hai.cn/PaymentError.aspx?aspxerrorpath=/Payment/LianJin/Notify4LianJin.aspx");
//         HttpPost httpPost = new HttpPost("https://paymentnotify.1hai.cn/Payment/LianJin/Notify4LianJin.aspx");
         
         ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

 			@Override
 			public String handleResponse(final HttpResponse response) throws ClientProtocolException, IOException {
 				int status = response.getStatusLine().getStatusCode();
 				if (status >= 200 && status < 300) {
 					HttpEntity entity = response.getEntity();
 					return entity != null ? EntityUtils.toString(entity) : null;
 				} else {
 					// 处理异常信息
 					System.err.println("httpstatus:" + status);
 					HttpEntity entity = response.getEntity();
 					String resultString = "";
 					if (entity != null) {
 						resultString = EntityUtils.toString(entity);
 					}
 					return resultString;
 				}
 			}

 		};
         try {
			String res = httpclient.execute(httpPost, responseHandler);
			System.out.println(res);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
	}
}

class PostThread extends Thread {
	private CloseableHttpClient hc;
	private String url;

	public PostThread(CloseableHttpClient hc, String url) {
		this.hc = hc;
		this.url = url;
	}
	
	@Override
	public void run() {
		HttpPost post = new HttpPost(url);
		HttpResponse response = null;
		try {
			response = hc.execute(post);
		} catch (Exception e) {
			System.out.println("cuo");
		}
		if(response == null) {
			return;
		}
		HttpEntity entity = response.getEntity();
		if(entity != null) {
			try {
				String returnStr = EntityUtils.toString(entity);
				System.out.println("=============" + returnStr);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				EntityUtils.consume(entity);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
class PostThread2 extends Thread {
	private PoolingHttpClientConnectionManager pcm;
	private RequestConfig rc;
	private String url;
	
	public PostThread2(PoolingHttpClientConnectionManager pcm, RequestConfig rc, String url) {
		this.pcm = pcm;
		this.rc = rc;
		this.url = url;
	}
	
	@Override
	public void run() {
		CloseableHttpClient hc = HttpClients.custom().setConnectionManager(pcm).setDefaultRequestConfig(rc).build();
		HttpPost post = new HttpPost(url);
		HttpResponse response = null;
		try {
			response = hc.execute(post);
		} catch (Exception e) {
			System.out.println("cuo");
		}
		if(response == null) {
			return;
		}
		HttpEntity entity = response.getEntity();
		if(entity != null) {
			try {
				String returnStr = EntityUtils.toString(entity);
				System.out.println("=============" + returnStr);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				EntityUtils.consume(entity);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}

class IdleConnectionMonitorThread extends Thread{
	private final HttpClientConnectionManager hcm;

	public IdleConnectionMonitorThread(HttpClientConnectionManager hcm) {
		this.hcm = hcm;
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO logger
			}
			
			//关闭失效的连接
			hcm.closeExpiredConnections();
			
			//关闭30秒不活动的连接
			hcm.closeIdleConnections(30, TimeUnit.SECONDS);
		}
	}
}











