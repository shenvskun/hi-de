package sk.base.socket;

import java.net.URLDecoder;
import java.net.URLEncoder;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class Learn_Url {
	public static void main(String[] args) throws Exception{
//		base();
		trans();
	}
	
	static void trans() throws Exception {
		CloseableHttpClient hc = HttpClients.createDefault();
		String haha = "代开收费了/?:#";
//		haha = URLEncoder.encode(haha);
		String url = "http://localhost:9090/hi-de/gg?name="+haha+"";
		HttpGet get = new HttpGet(url);
		CloseableHttpResponse res = hc.execute(get);
		HttpEntity entity = res.getEntity();
		String string = EntityUtils.toString(entity);
		System.out.println(string);
	}
	
	
	static void base() throws Exception {
		String haha = "代开收费了+/";
//		haha = URLEncoder.encode(haha);
		String url = "http://localhost:9090/hi-de/gg?name="+haha;
		String encodeUrl = URLEncoder.encode(url, "utf-8");
		System.out.println(encodeUrl);
		System.out.println(URLDecoder.decode(url));
		/*
		 * url中/ ? # & =都有特殊作用 再参数中直接传递会被误解 再参数中需要编码后传
		 * 而空格%者两个是不能直接传递的必须要转换成对应编码 到了服务器端解码得 【编码中有特殊意义得字符？】
		 * tomcat8已解决get中文乱码问题可以直接传中文
		 * +     %2B
		 * 空格   +或者%20
		 * /    分隔目录和子目录      %2F
		 * %  指定特殊字符   %25
		 * ?               %3F
		 * #               %23
		 * &               %26
		 * =               %3D
		 * 
		 * ajax传特殊字符得两种方法
		 * 1 编码
		 * 2 放到data中
		 * https://blog.csdn.net/rainbow702/article/details/52962905
		 */
	}
}
