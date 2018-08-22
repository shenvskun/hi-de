package sk.base.io;

import java.io.File;
import java.io.FileReader;

public class Learn_Reader {
	public static void main(String[] args) throws Exception{
		//字符流默认编码是系统默认编码 utf-8 
		//txt文件编码默认是gbk 用UE编辑器改成utf-8即可
		File file = new File("d:/a.txt");
		System.out.println(file.length());
		FileReader fr = new FileReader("d:/a.txt");
		char[] c = new char[1024];
		int read = fr.read(c);
		System.out.println((char)read);
		System.out.println(c);
		
		//字符数组存放中文
		char[] c1 = new char[10];
		c1[0]= '到';
		System.out.println(c1);
	}
}
