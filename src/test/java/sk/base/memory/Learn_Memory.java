package sk.base.memory;

import com.sk.hide.exception.HideException;

public class Learn_Memory {
	public static void main(String[] args) {
		System.out.println("堆内存溢出");
		byte[] b = new byte[10*1024*1024*1024];
	}
	
}
