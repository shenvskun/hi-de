package sk.test.httpClient;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManagerFactory;

public class Learn_SSLSocket_client {
	public static void main(String[] args) throws NoSuchAlgorithmException, KeyStoreException, CertificateException, FileNotFoundException, IOException, UnrecoverableKeyException, KeyManagementException {
	/*首先创建服务器端私有密钥和公共密钥
	1, keytool -genkey -alias serverkey -keystore kserver.ks
	    密码: serverpass
	2, keytool -export -alias serverkey -keystore kserver.ks -file server.crt
	3, keytool -import -alias serverkey -file server.crt -keystore tclient.ks
	    密码: clientpublicpass
	
	下面创建客户器端私有密钥和公共密钥
	1, keytool -genkey -alias clientkey -keystore kclient.ks
	    密码: clientpass
	2, keytool -export -alias clientkey -keystore kclient.ks -file client.crt
	3, keytool -import -alias clientkey -file client.crt -keystore tserver.ks
	密码: serverpublicpass
	
	*/
		
		SSLContext ctx = SSLContext.getInstance("TLS");
		
		KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
		TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
		
		KeyStore ks = KeyStore.getInstance("JKS");
		KeyStore tks = KeyStore.getInstance("JKS");
		
		ks.load(new FileInputStream("e:/keystore/client.ks"), "654321".toCharArray());
		tks.load(new FileInputStream("e:/keystore/tclient.ks"), "123456".toCharArray());
		
		kmf.init(ks, "654321".toCharArray());
		tmf.init(tks);
		
//		ctx.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
		ctx.init(null, tmf.getTrustManagers(), null);
		SSLSocket ss = (SSLSocket) ctx.getSocketFactory().createSocket("localhost", 8445);
		
//		OutputStreamWriter writer = new OutputStreamWriter(ss.getOutputStream());
//		writer.write("你好");
//		writer.flush();
		
		BufferedInputStream bis = new BufferedInputStream(ss.getInputStream());
		BufferedOutputStream bos = new BufferedOutputStream(ss.getOutputStream());
		
		bos.write("你好".getBytes());
		bos.flush();
//		
		byte[] buffer = new byte[20];
		int length = bis.read(buffer);
		System.out.println("服务端返回数据：" + new String(buffer, 0, length).toString());
		
		ss.close();
		
	}
}









