package sk.base.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

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
		
		System.out.println("===linkedList");
		LinkedList linkedList = new LinkedList();
		linkedList.add("dsd");
		linkedList.add("dssssd");
		linkedList.offer("aaa");
		linkedList.remove("dd");
		System.out.println(linkedList);
		System.out.println(linkedList.pop());
		
		int ppp = 0;
		int[] i = {3,4,ppp};
		
		System.out.println("============测试set===============");
		Person p1 = new Person("p1", 11);
		Person p2 = new Person("p2", 11);
		Person p3 = new Person("p2", 11);
		Person p4 = new Person("p4", 11);
		Set<Person> treeSet = new HashSet();
		treeSet.add(p1);
		treeSet.add(p2);
		treeSet.add(p3);
		treeSet.add(p4);
		System.out.println(treeSet.contains(p3));
		System.out.println(treeSet);
		for (Person p : treeSet) {
			System.out.println(p);
		}
		
		System.out.println("=======list特有的迭代器=============");
		List testListIterator = new ArrayList();
		testListIterator.add("第一本书");
		testListIterator.add("第二本书");
		testListIterator.add("第三本书");
		testListIterator.add("第四本书");
		ListIterator li = testListIterator.listIterator();
		li.next();
		li.remove();//删掉第一本  iterator的公共方法
		System.out.println(testListIterator);
		li.next();
		li.set("dddd");
		li.add("第五本书"); //在it.next后面添加一个元素
		System.out.println(testListIterator);
		li.next();
		System.out.println(testListIterator);
		
	}
}

class Person {
	private String name;
	private Integer age;
	
	public Person() {
	}
	
	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((age == null) ? 0 : age.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (age == null) {
			if (other.age != null)
				return false;
		} else if (!age.equals(other.age))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public Person(String name, Integer age) {
		this.name = name;
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
}
