package sk.base.socket;

import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.apache.commons.codec.binary.Hex;
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
		
		testUrl();
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
	
	//http请求 post get编码问题
	//post请求数据放在实体里 用content-type请求头指定编码格式【同样是UrlEncode() 中的编码 因为中文在请求体中是%+16进制大写】 到服务器端用CharacterEncodingFilter就行
	//
	//get服务器tomcat8已经是UrlDecoder.decode(, "utf-8") 解码  客户端只需encode一下就行
	//UrlDecoder和UrlEncoder不指定编码就是默认系统的
	//Url加解密 指定编码的意义是编程什么样的字节数组  之后都是用16进制加密 一个字节数组对应2个大写16进制数前再拼%
	static void base() throws Exception {
		String haha = "代开收费了+/";
//		haha = URLEncoder.encode(haha);
		String url = "http://localhost:9090/hi-de/gg?name="+haha;
		String encodeUrl = URLEncoder.encode(url, "utf-8");
		System.out.println("urlEncode之后的"+encodeUrl);
		System.out.println("编码后用hex加密:"+ Hex.encodeHexString(haha.getBytes("utf-8")).toUpperCase());
		
		String decodeStr = URLDecoder.decode(encodeUrl, "iso8859-1");
		System.out.println(decodeStr);
		System.out.println(new String(decodeStr.getBytes("iso8859-1"), "utf-8"));
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
	
	//url类
	static void testUrl() throws Exception {
		System.out.println("==============url====");
		URL url = new URL("https://baike.baidu.com/item/IP/224599#aa?fr=aladdin");
		System.out.println("===authority=host+port: ===" +  url.getAuthority());
		System.out.println("======默认端口=====" + url.getDefaultPort());
		System.out.println("======协议=====" + url.getProtocol());
		System.out.println("======主机名=====" + url.getHost());
		System.out.println("======端口=====" + url.getPort());
		System.out.println("======路径=====" + url.getPath());
		System.out.println("======文件=====" + url.getFile());
		System.out.println("=====参数=====" + url.getQuery());
	}
}
