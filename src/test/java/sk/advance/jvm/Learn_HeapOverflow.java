package sk.advance.jvm;

import java.util.ArrayList;
import java.util.List;

public class Learn_HeapOverflow {
	public static void main(String[] args) throws Exception{
		Thread.sleep(5000);
		List l = new ArrayList();
		for(int i = 0; i <=100; i++) {
			Thread.sleep(50);
			l.add(new byte[64 * 1024*40]);
		}
	}
}
