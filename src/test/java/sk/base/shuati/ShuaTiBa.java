package sk.base.shuati;

import org.junit.Test;

public class ShuaTiBa {
	/*题目:返回两个任意长度的整型数组有多少个以1开头
	 * 
	 */
	@Test
	public void solution() {
		System.out.println(start(new int[]{1,2,3,4}, new int[]{1}));
	}
	public int start(int[] intArr1, int[] intArr2) {
		return start1(intArr1) + start1(intArr2);
	}
	
	public int start1(int[] intArr) {
		if(intArr == null || intArr.length == 0 || intArr[0]!=1) {
			return 0;
		} else {
			return 1;
		}
	}
	
	
	@Test
	public void getNewString() {
		String subTheStr = subTheStr("IS");
		System.out.println(subTheStr);
	}
	
	public String subTheStr(String subStr) {
		String str = "THIS IS DDDASIS";
		if(str.contains(subStr)) {
			str = str.replace(subStr, "");
		}
		return str;
	}
}
