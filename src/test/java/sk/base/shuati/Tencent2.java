package sk.base.shuati;

import java.util.ArrayList;
import java.util.List;

public class Tencent2 {
	public static void main(String[] args) {
		//9
		System.out.println(subSameStr("xxx"));
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
