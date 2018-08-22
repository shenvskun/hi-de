package sk.base.collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Learn_List {
	public static void main(String[] args) {
		Map m = new HashMap();m.put("c", 1);
		List l = new ArrayList();
		l.add("d");
		l.add(m);
		System.out.println(l.isEmpty());
		
//		System.out.println(l.remove(0));
		Map m2 = (Map) l.get(1);
		m2.put("c", 2);
		
		System.out.println(l.get(1));
	}
}
