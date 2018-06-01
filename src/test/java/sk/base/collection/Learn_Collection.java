package sk.base.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Learn_Collection {
	public static void main(String[] args) {
		System.out.println("======equals方法=========");
		List a = new ArrayList();
		a.add("3");
		List b = new ArrayList();
		b.add("3");
		System.out.println(a.equals(b));
		
		
		System.out.println("==========retain方法==== 保留交集======");
		a.add("4");
		a.add("5");
		a.add("6");
		
		b.add("5");
		b.add("7");
//		System.out.println(a.retainAll(b));
		System.out.println(a);
		System.out.println("==========removeAll方法=  删除交集=========");
		System.out.println(a.removeAll(b));
		System.out.println(a);
		
		System.out.println("==========迭代过程种删除=========");
			
		Iterator it = b.iterator();
		while (it.hasNext()) {
			String s = it.next().toString();
			System.out.println(s);
			if(s.equals("3")) {
				it.remove();
			}
			
		}
		System.out.println(b);
		
		System.out.println("==========排序  Collection.sort===================");
		List sortL = new ArrayList();
		sortL.add(3);
		sortL.add(1);
		sortL.add(99);
		sortL.add(11);
		Collections.sort(sortL);
		System.out.println(sortL);
	}
}
