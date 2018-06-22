package sk.base.shuati;

/**
 * happyG重新梳理  之前的是能写出来 但走马观花
 * @author eric
 *
 */
public class Tencent7 {

	
	public static void main(String[] args) {
		System.out.println(gHappy("lskgg"));
	}
	
	static boolean gHappy(String gString) {
		boolean sumFlag = true;
		boolean flag = false;
		gString = "a" + gString + "a";
		for(int i = 1; i<gString.length() - 1; i++) {
			if('g' == gString.charAt(i)) {
				if('g' == gString.charAt(i-1) || 'g' == gString.charAt(i+1)) {
					flag = true;
				} else {
					flag = false;
				}
				sumFlag = sumFlag && flag;
			}
		}
		return sumFlag && flag;
	}

}
