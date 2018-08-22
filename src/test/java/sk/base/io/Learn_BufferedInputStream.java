package sk.base.io;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

public class Learn_BufferedInputStream {
	public static void main(String[] args) throws Exception{
		File file = new File("d:/a.txt");
		System.out.println(file.length());
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream("d:/a.txt"));
		int read = bis.read(new byte[1023]);
		System.out.println(read);
	}
}
