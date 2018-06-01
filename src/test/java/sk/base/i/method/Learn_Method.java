package sk.base.i.method;

import static org.junit.Assert.*;

import org.junit.Test;

public class Learn_Method {

	@Test
	public void test() {
		String s = "less";
		changeValue(s);
		System.out.println(s);
	}
	
	/**
	 值传递  引用传递
	 * @param v
	 */
	public void changeValue(String v) {
		v = "bbb";
	}
	
	@Test
	public void testOrder() throws Exception {
		Child child = new Child();
		child.fangfa();
	}
	
	public static void main(String[] args) {
		System.out.println(Child.pss);
	}
	
	@Test
	public void testAnonymity() {
		new Parent() {
			public Parent ss() {
				System.out.println("ddsss");
				return this;
			}
			public void tt() {
				System.out.println("第二层调用");
			}
		}.ss();
	}
}

class Parent{
	static {
		System.out.println("--------父类的静态代码块----");
	}
	public static String pss = "父类静态常量初始化";
	
	
	{
		System.out.println("-----父类构造代码块-----");
	}
	public String ps = getPs();
	public String sssss="00000";
	
	public Parent() {
		System.out.println("-----父类构造方法-------");
	}
	private String getPs() {
		String str = "父类属性的初始化";
		System.out.println(str);
		return str;
	}
	 void fangfa() {
		System.out.println("父类方法");
	}
	
	 public static void jingtaifangfa() {
		 System.out.println("父类静态方法");
	 }
}
class Child extends Parent{
	static {
		System.out.println("-------子类的静态代码块----");
	}
	public static String pss = getPss();
	
	public String ps = "子类实例变量初始化";
	
	{
		System.out.println("-----子类构造代码块-----");
	}
	
	public Child() {
		System.out.println("-----子类构造方法-------");
	}
	public static String getPss() {
		String s = "子类静态常量初始化";
		System.out.println(s);
		return s;
	}
	
	public void fangfa() {
		System.out.println("子类方法");
	}
	
	public void ceshiSuper() {
		System.out.println(this.sssss);
	}
//	public static void jingtaifangfa() {
//		 System.out.println("子类静态方法");
//	 }
}