package sk.base.loop;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class Learn_Loop {

	@Test
	public void test() {
		//双循环 校验外循环的东西是否跟内循环的全部匹配
		List<String> l1 = new ArrayList();
		l1.add("1");
		l1.add("2");
		l1.add("3");
		List<String> l2 = new ArrayList();
		l2.add("1");
		l2.add("2");
		l2.add("3");
		l2.add("4");
		l2.add("5");
		l2.add("6");
		
		for (String s1 : l1) {
			for (String s2 : l2) {
				if(s2.equals(s1)) {
					
				}
			}
		}
		
	}

}
