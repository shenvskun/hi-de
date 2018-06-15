package sk.base.exception;

import com.sk.hide.exception.HideException;

public class Learn_Exception {
	public static void main(String[] args) {
		System.out.println(getS());
	}
	
	static String getS() {
		try{
			if(true)
				throw new HideException(4400, "dd");
		} catch(HideException e) {
			System.out.println("catch");
		}finally {
			System.out.println("finally");
		}
		return "return";
	}
}
