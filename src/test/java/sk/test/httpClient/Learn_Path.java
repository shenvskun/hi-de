package sk.test.httpClient;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

import com.sk.hide.controller.RSA;
import com.sk.hide.exception.HideException;

public class Learn_Path {
	public static void main(String[] args) throws URISyntaxException, IOException {
		//Class.getClassLoader().getResource();  无论是否/开头拿到的是classpath路径下的  不是class同级的
		InputStream in = HideException.class.getClassLoader().getResourceAsStream("tt.txt");
		System.out.println((char)in.read());
		in.close();
		
		//Class 以/开头是classpath 不以/开头该类同级目录
		in = HideException.class.getResourceAsStream("tt.txt");
		System.out.println((char)in.read());
		in.close();
		
		System.out.println(RSA.class.getClassLoader().getResource("/tt.txt").getPath());
		System.out.println(HideException.class.getResource("/tt.txt").getPath());
		
	}
}
