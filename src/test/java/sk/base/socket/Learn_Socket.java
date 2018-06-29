package sk.base.socket;

import java.net.InetAddress;
import java.net.URL;

public class Learn_Socket {

	public static void main(String[] args) throws Exception {
//		testIp();
		testUrl();
	}
	
	//Ip
	static void testIp() throws Exception {
		System.out.println("==============ip====");
		InetAddress localHost = InetAddress.getLocalHost();
		System.out.println(localHost.getHostAddress());
		System.out.println(localHost.getHostName());
		
		System.out.println("===========根据域名得到ip对象");
		InetAddress addr = InetAddress.getByName("www.baidu.com");
		System.out.println(addr.getHostAddress());
		System.out.println(addr.getHostName());
		
		System.out.println("==========根据地址");
		InetAddress ljaddr = InetAddress.getByName("222.128.51.46");
		System.out.println(ljaddr.getHostAddress());
		System.out.println(ljaddr.getHostName());
	}
	//Url  
	//url终极划分 [scheme:][//host:port][path][?query][#fragment]   其中authority=host:port
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
