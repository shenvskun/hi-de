package sk.test.httpClient;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.TrustManagerFactory;

public class learn_SSLSocket_server {
	public static void main(String[] args) throws NoSuchAlgorithmException, KeyStoreException, CertificateException, FileNotFoundException, IOException, UnrecoverableKeyException, KeyManagementException, InterruptedException {
		SSLContext ctx = SSLContext.getInstance("SSL");
		KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
		TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
		
		KeyStore ks = KeyStore.getInstance("JKS");
		KeyStore tks = KeyStore.getInstance("JKS");
		
		ks.load(new FileInputStream("e:/keystore/server.ks"), "123456".toCharArray());
		tks.load(new FileInputStream("e:/keystore/tserver.ks"), "654321".toCharArray());
		
		kmf.init(ks, "123456".toCharArray());
		tmf.init(tks);
		
//		ctx.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
		ctx.init(kmf.getKeyManagers(), null, null);
		
		SSLServerSocket serverSocket = (SSLServerSocket)ctx.getServerSocketFactory().createServerSocket(8445);
//		serverSocket.setNeedClientAuth(true);
		
		while(true) {
			Socket s = serverSocket.accept();
			InputStream in = s.getInputStream();
			OutputStream out = s.getOutputStream();
			
			BufferedInputStream bis = new BufferedInputStream(in);
			BufferedOutputStream bos = new BufferedOutputStream(out);
			
			byte[] buffer = new byte[20];
			int length = bis.read(buffer);
			System.out.println("读到客户端数据：" + new String(buffer, 0, length).toString());
			
			Thread.sleep(3000);
			
			bos.write("hellow".getBytes());
			bos.flush();
			
			s.close();
		}
	}
	
}




















