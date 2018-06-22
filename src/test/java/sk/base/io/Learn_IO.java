package sk.base.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;

public class Learn_IO {
	public static void main(String[] args) throws Exception {
//		System.out.println("====file方法 renameTo====");
//		File file = new File("d:/aaa.txt");
//		File file2 = new File("d:/aat.txt");
//		
//		System.out.println(file.createNewFile());
//		file.setWritable(true);
//		FileWriter fw = new FileWriter(file);
//		fw.write("dddssadfaadfad");
//		fw.close();
//		System.out.println(file.renameTo(file2));
//		
//		System.out.println("========字节流读取方式2");
//		FileInputStream fi = new FileInputStream(file2);
//		byte[] b = new byte[1024];

//		System.out.println("=========fileoutputstream");
//		FileOutputStream fo = new FileOutputStream("d:/a.tx",true);
//		fo.write('0');
//		fo.write('0');
//		fo.write('0');
//		fo.write('0');
//		fo.close();
		
//		long start1 = System.currentTimeMillis();
//		FileInputStream fi1 = new FileInputStream("d:/33.rmvb");
//		FileOutputStream fo1 = new FileOutputStream("d:/2.rmvb");
//		byte[] b3 = new byte[1024];
//		int len1;
//		while((len1 = fi1.read(b3))!=-1) {
//			fo1.write(b3, 0, len1);
//		}
//		fi1.close();
//		fo1.close();

//		long end1 = System.currentTimeMillis();
//		System.out.println("==========字节流+缓冲数组所用时间 " + (end1 - start1));
//		
//		long start = System.currentTimeMillis();
//		BufferedInputStream bis = new BufferedInputStream(new FileInputStream("d:/33.rmvb"));
//		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("d:/1.rmvb"));
//		int len;
//		while((len = bis.read()) != -1) {
//			bos.write(len);
////			bos.flush();
//		}
//		bis.close();
//		bos.close();
//		long end = System.currentTimeMillis();
//		System.out.println("===缓冲字节流+字节数组花费时间==" + (end-start));
		
//		System.out.println("=====多线程下载");
//		String path = "http://ox4j4dsnp.bkt.clouddn.com/17-10-1/58878601.jpg";
//		int fileL = ServerUtils.getFilelength("http://ox4j4dsnp.bkt.clouddn.com/17-10-1/58878601.jpg");
//		int threadC = 6;
//		int perLen = fileL/threadC;
//		for (int i = 0; i< threadC ; i++) {
//			int startIndex= perLen *i;
//			int endIndex = perLen + startIndex -1;
//			if(i == threadC - 1) {
//				endIndex = fileL-1;
//			}
//			
//			new DownloadThread(startIndex, endIndex, i, path).start();
//		}
//		
//		while(DownloadThread.threadCount > 0) 
//			Thread.sleep(2000);
//		
//		FileUtil.mergeFile("d:/tt", "d:/tt/final.jpg");
		
		System.out.println("字符流写");
		FileWriter fw2 = new FileWriter("d:/opop.txt", true);
		System.out.println("默认编码格式为" + fw2.getEncoding());
		fw2.write("月色烙印在城墙，风声呼啸过苍茫");
//		Thread.sleep(4000);
		fw2.close();
		
		System.out.println("列出jvm的所有属性");
		Properties prop = System.getProperties();
		prop.list(System.out);
		
		Properties prop1 = new Properties();
		prop1.setProperty("dd", "乱不乱码");
		prop1.store(new OutputStreamWriter(new FileOutputStream("d:/prop1.properties"), "UTF-8"), new String("注释啊".getBytes("utf-8"), "iso8859-1"));
		
//		System.out.println("重定向syso");
//		System.setOut(new PrintStream("d:/console.txt"));
//		System.out.println("都开始");
		
		System.out.printf("%s和%8s大大大", "得到", "婆婆");
		
		System.out.println("==========用指定码表打印字符串=========");
		
		PrintStream printStream = new PrintStream("d:/e.txt", "iso8859-1");
		printStream.println("d的身份");
		
		System.out.println("打印字符流");
//		PrintWriter pw = new PrintWriter("", "");
	}
	
	
}
//IO多线程综合
//多线程下载
class DownloadThread extends Thread{
	public static Integer threadCount = 0;
	
	private String path;
	private int startIndex;
	private int endIndex;
	private int id;
	public DownloadThread(int startIndex, int endIndex, int id, String path) {
		this.startIndex = startIndex;
		this.endIndex = endIndex;
		this.id = id;
		this.path = path;
		threadCount ++;
	}
	@Override
	public void run() {
		try {
			URL url = new URL(path);
			URLConnection conn = url.openConnection();
			
			conn.setRequestProperty("Range", "bytes" + startIndex + "-" + endIndex);
			InputStream in = conn.getInputStream();
			
			FileOutputStream fo = new FileOutputStream("d:/tt/"+id + ".temp");
			byte[] arr  = new byte[100];
			int len;
			while((len = in.read(arr)) != -1) {
				fo.write(arr, 0, len);
			}
			in.close();
			fo.close();
			
			threadCount--;
			System.out.println(getName() + " == 下载完毕");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
}

class FileUtil {
	public static void mergeFile(String srcPath, String destPath) throws Exception{
		File file = new File(srcPath);
		File[] listFiles = file.listFiles(new FileFilter() {

			@Override
			public boolean accept(File file) {
				if(file.isFile() && file.getName().endsWith(".temp")) {
					return true;
				}
				return false;
			}
		});
		
		FileOutputStream fo = new FileOutputStream(destPath);
		byte[] arr = new byte[1024];
		int len;
		for(File f : listFiles) {
			FileInputStream fi = new FileInputStream(f);
			while((len = fi.read(arr)) != -1) {
				fo.write(arr, 0, len);
			}
			
			fi.close();
		}
		fo.close();
	}
}
class ServerUtils {
	public static int getFilelength(String path) {
		try {
			URL url = new URL(path);
			URLConnection conn = url.openConnection();
			return conn.getContentLength();
		} catch(Exception e){
			return -1;
		}
	}
}
