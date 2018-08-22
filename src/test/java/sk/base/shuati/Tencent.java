package sk.base.shuati;

import java.util.regex.Pattern;

public class Tencent {
	public static void main(String[] args) {
//		System.out.println(delDel("ss"));
//		System.out.println(countYZ("poiopy sdkfdjz ddkjs zdeZ y"));
//		System.out.println(gHappy("sdfsgadfag"));
		//6.1
//		System.out.println(Pattern.matches("([^g]*g[^g]*)\\1*", "dddggdsjajg")); //等懂得正则多一些时再来解
		//6.2 happyg
//		System.out.println(happyG(""));
//		System.out.println("sdg".charAt(2));
//		System.out.println("g".equals('g'));
		
//		System.out.println(happyG63("sdsfaggggggadafd"));
//		System.out.println("sdkafgggggggsds".replaceAll("(g)\\1+", "-"));
		
		//7
		//匹配叠字
//		System.out.println("dsssfdsdjjj".replaceAll("(.)\\1+", "--"));
//		System.out.println("dsssfdsdjjj".replaceFirst("(.)\\1+", "-"));
		
//		System.out.println(maxBlock("ss000000wewllppppp0"));
		
		
		//replace不支持按正则匹配替换 只支持匹配子串替换
//		System.out.println("dddssadfs".replace("(.)\\1+", "-"));
		
		//8
		String[] newsarr = ipOrder(new String[]{"61.54.231.245","61.54.231.9", "61.54.231.246", "61.54.231.48", "61.53.231.249"});
		for (String str : newsarr) {
			System.out.println(str);
		}
		
//		System.out.println(compareIp("61.54.234.6", "61.54.234.5"));
	}
	/*
	 * 8按数字大小排序 61.54.231.245 61.54.231.9 61.54.231.246 61.54.231.48 61.53.231.249
	 */
	public static String[] ipOrder(String[] ips) {
		String temp = "";
		for (int i = 1; i < ips.length; i++) {
			for (int j = 0; j < ips.length-i; j++) {
				if(compareIp(ips[j], ips[j+1])) {
					temp = ips[j];
					ips[j] = ips[j+1];
					ips[j+1] = temp;
				}
			}
		}
		return ips;
	}
	
	//ip1 > ip2 返回ture 
	static boolean compareIp(String ip1, String ip2) {
		String[] ip1A = ip1.split("\\.");
		String[] ip2A = ip2.split("\\.");
		for (int i = 0; i < ip2A.length; i++) {
			if (Integer.parseInt(ip1A[i]) > Integer.parseInt(ip2A[i])) {
				return true;
			}
		}
		return false;
	}
	/*
	 * 7字符串中连续出现个数最多的字符的数量 vs字符串中由连续字符组成的字串的最长长度
	 */
	public static int maxBlock(String str) {
		String newStr = "";
		int count = 0;
		int tempCount = 0;
		while(true) {
			newStr = str.replaceFirst("(.)\\1+", "");
			if(newStr.equals(str)) {
				break;
			}
			//如果要找出被替换掉的字串字母  
			//遍历str 每个字符 charAt(i) 判断newStr contrans否 false则是被替换的字母
			tempCount = str.length()-newStr.length();
			if(tempCount > count) {
				count = tempCount;
			}
			str = newStr;
		}
		return count;
	}
	/*
	 * 6.2
	 */
	public static boolean happyG(String str) {
		if(!str.contains("g")) {
			return false;
		}else {
			str = "a" + str + "a";
			for(int i = 1; i < str.length()-1; i ++){
				if("g".equalsIgnoreCase(str.charAt(i)+"") && (!"g".equals(str.charAt(i-1)+"") && !"g".equals(str.charAt(i+1)+""))) {
					return false;
				}
			}
		}
		return true;
	}
	/*
	 * 6.3 happyG
	 */
	public static boolean happyG63(String str) {
		
		return !str.replaceAll("(g)\\1+", "").contains("g");
	}
	/*
	 * 4
	 */
	public static String delDel(String str) {
		if(str.indexOf("del")==1) {
			str = str.replace("del", "");
		}
		return str;
	}
	
	/*
	 * 5
	 */
	public static int countYZ(String s) {
		int count = 0;
		String[] ss = s.split(" ");
		for (int i = 0; i < ss.length; i++) {
			if(ss[i].endsWith("y") || ss[i].endsWith("Y") || ss[i].endsWith("z") || ss[i].endsWith("Z")) {
				count++;
			}
		}
		return count;
	}
	
	/*
	 * 6
	 */
	public static boolean gHappy(String str) {
		if(str.contains("g") && !Pattern.matches("[^g]*g[^g]*", str)) {
			return true;
		} else {
			return false;
		}
	}
}
