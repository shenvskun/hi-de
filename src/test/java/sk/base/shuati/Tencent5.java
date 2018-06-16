package sk.base.shuati;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.TreeSet;

import com.sk.hide.exception.HideException;

/*
 * 对应第五套刷题
 */
public class Tencent5 {
	public static void main(String[] args) {
		ti13("d:/b.txt");
	}
	
	/*
	 * 
	 */
	public void ti14(int[] i) {
		
	}
	/*
	 * 13文件读写去重排序
	 */
	static void ti13(String filepath) {
		File file = new File(filepath);
		if (file.isDirectory()) {
			throw new HideException(500, "请输入具体文件名");
		}
		HashSet<Integer> hs = new HashSet<Integer>();
		TreeSet<Integer> ts = new TreeSet<Integer>();
		StringBuilder sb = new StringBuilder();
		try(BufferedReader br = new BufferedReader(new FileReader(file));
			BufferedWriter bw = new BufferedWriter(new FileWriter("d:/a.txt"));
			) {
			String line = null;
			while ((line = br.readLine()) != null) {
				//去重
				String[] split = line.split("\\D");
				for (String str : split) {
					hs.add(Integer.parseInt(str));
				}
				System.out.println("去重之后" + hs);
				
				//排序
				ts.addAll(hs);
				System.out.println("排序之后" + ts);
				
				//存储文件
				for (Integer i : ts) {
					sb.append(i + " ");
				}
				
				hs.clear();
				ts.clear();
				
				sb.append("\r\n");
			}
			bw.write(sb.toString());
		} catch (IOException e) {}
		
	}
}
