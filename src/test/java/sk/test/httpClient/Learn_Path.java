package sk.test.httpClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import com.sk.hide.exception.HideException;

public class Learn_Path {
	public static void main(String[] args) throws URISyntaxException, IOException {
		//Class.getClassLoader().getResource();  拿到的是classpath路径下的  不是class同级的
		URL resource = HideException.class.getClassLoader().getResource("tt.txt");
		FileInputStream fi = new FileInputStream(new File(resource.toURI()));
		System.out.println(new String(new byte[]{(byte) fi.read()}));
		
		//Class.getResource 拿到的是class同级的文件
		URL resource2 = HideException.class.getResource("tt.txt");
		FileInputStream fi2 = new FileInputStream(new File(resource2.toURI()));
		System.out.println(new String(new byte[]{(byte) fi2.read()}));
		
	}
}
