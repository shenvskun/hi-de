package sk.base.shuati;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Tencent2 {
	public static void main(String[] args) {
		//9
		System.out.println(subSameStr("avaddsava"));
		
		//10
		System.out.println(sumNumInStr("dsdfjj230dw2skdj32"));
		
		//11
//		System.out.println(removeTheStr("THISis is a fish", "IS"));
//		System.out.println(removeTheStr2("THIS is a fish", "IS"));
		System.out.println(removeTheStr3("THISIs is a fish", "IS"));
		
		//12 
		System.out.println(func(5));
		
//		System.out.println("this is age".substring(3, 222));
		
		//13
//		printPath("C:/Users/eric/Documents/java", "-");
//		System.out.println("-".equals("-"));
		
		//13.1
		printpath("C:/Users/eric/Documents/java", "-");
	}
	/*
	 * 13.1递归打印
	 */
	static void printpath(String directory, String seporate) {
		if("-".equals(seporate)) {
			System.out.println(directory);
		}
		File file = new File(directory);
		if(file.isFile())
			return;
		
		String[] chilF = file.list();
		for (String str : chilF) {
			String childDirectory = directory + "/" + str;
			File child = new File(childDirectory);
			if(child.isDirectory()) {
				System.out.println(seporate + str);
				printpath(childDirectory, seporate+"-");
			} else {
				System.out.println(seporate + "|" + str);
			}
		}
		
	}
	/*
	 * 13递归打印文件夹所有文件路径
	 */
	static void printPath(String directory, String pre) {
		if("-".equals(pre)) {
			System.out.println(directory);
		}
		File f = new File(directory);
		if (f.isDirectory()) {
			String[] fl = f.list();
			for (String str : fl) {
				String c1s = directory + "/" + str;
				File cf = new File(c1s);
				if (cf.isDirectory()) {
					System.out.println(pre + str);
					printPath(c1s , pre + "-");
				} else {
					System.out.println(pre + "|" + str);
				}
			}
		} else {
			System.out.println("该目录无效！");
		}
		
	}
	/*
	 * 12 递归求阶乘
	 */
	static int func(int i) {
		if(i == 1) 
			return 1;
		return i*func(i-1);
	}
	
	/*
	 * 11移除指定字符串 不区分大小写
	 */
	static String removeTheStr(String base, String remove) {
		for (int i = 0; i < base.length(); i++) {
			if(remove.equalsIgnoreCase(base.substring(i,i + remove.length()))) {
				base  = base.substring(0, i) + "" + base.substring(i+remove.length(), base.length());
			}
		}
		return base;
	} 
	
	static String removeTheStr2(String base, String remove) {
		return base.replaceAll(remove, "");
	} 
	
	static String removeTheStr3(String base, String remove) {
		for (int i = 0; i < base.length(); i++) {
			int end = i + remove.length();
			if(end > base.length()) {
				end = base.length();
			}
			String newStr = base.substring(i, end);
			if(remove.equalsIgnoreCase(newStr)) {
				base  = base.replaceAll(newStr, "");
				i--;
			}
		}
		return base;
	} 
	/*
	 *10 求字符串中的数字之和
	 */
	static int sumNumInStr(String str) {
		int count = 0;
		String[] s = str.split("[^0-9]+");
		for (String is : s) {
			if(is.isEmpty()) {
				continue;
			}
			count += Integer.parseInt(is);
		}
		return count;
	}
	
	/*
	 * 9 截取出首尾相同的不可重叠的字串
	 */
	static String subSameStr(String str) {
		List<String> list = new ArrayList();
		int l = str.length();
		for (int i = 1; i < l; i++) {
			if(i > l-i) {
				break;
			}
			if(str.substring(0, i).equals(str.substring(l-i, l))) {
				list.add(str.substring(0, i));
			}
		}
		System.out.println(list);
		return list.get(list.size()-1);
	}
}
