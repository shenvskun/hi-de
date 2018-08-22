package sk.base.base;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import sk.base.learn_abstract.IProvinceGovernMent;
import sk.base.learn_enum.Animal;
import sk.base.learn_enum.OrderStatus;

public class LearnBase {
	
	public static void print(Collection<? extends Object> c) {
		for (Object object : c) {
			System.out.println(object);
		}
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println("=====char");
		char y = 65;
		System.out.println(++y);
		char c = '\6';
		System.out.println(c);
		
		System.out.println("=======-----运算符模等于");
		int p = 7;
		p%=3;
		System.out.println(p);
		
		System.out.println("===========---位运算符");
		System.out.println("与&运算符"+(3&4));
		System.out.println("或|运算符"+(3|4));
		System.out.println("非~运算符"+(~20) + "    加1变负");
		System.out.println("异或^运算符"+(3^4));
		System.out.println("位移<<运算符"+(2<<3));
		System.out.println("位移>>>运算符"+(16>>>3));
		
		//格式稍微及以下  可以筛选 byte short int char 字符串 枚举
//		System.out.println("================switch");
//		switch (Animal.Shizi) {
//		case Shizi:
//			
//			break;
//
//		case Laohu:	
//		default:
//			break;
//		}
		
		System.out.println("===枚举");
		System.out.println(Season.summum);
		System.out.println(Animal.Laohu);
		System.out.println(Animal.Laohu.getName());
		System.out.println(Animal.Laohu.speak());
		System.out.println(Animal.values()[1]);
		System.out.println(OrderStatus.paySuccess.getName());
		
		
		//修饰符 abstract
		new IProvinceGovernMent(){
			private String name;
			@Override
			public void shoushui() {
				
			}};
			
		new C().getS();
		
		
		
		List<String> arr = new ArrayList<String>();
		arr.add("addd");
		print(arr);
		
		Date d = new SimpleDateFormat("yyyyMMdd").parse("20180606");
		Calendar cn = Calendar.getInstance();
		Date d2 = cn.getTime();
		System.out.println(d.compareTo(d2));
		
		cn.add(Calendar.DATE, 1);
		System.out.println(cn.getTime());
		System.out.println(d2.compareTo(new Date()));
		
		System.out.println("=====匿名内部类=======");
		new Outer().print();
		
		System.out.println("cpu的核心个数为");
		System.out.println(Runtime.getRuntime().availableProcessors());
		
	}
}

enum Season{
	spring,summum,autumn,winter
}
class JubuBianliang{
	public void say() {
		final String s = "s";
	}
}

class P {
	private String s = "sss";

	public String getS() {
		return s;
	}

	public void setS(String s) {
		this.s = s;
	}
	
}
class C extends P {
	public void s() {
		
	}
}
interface Actor{
	Actor show1();
	Actor show2();
}
class Outer{
	Actor i = new Actor() {
		public Actor show1() {
			System.out.println("show1");
			return this;
		}
		public Actor show2() {
			System.out.println("show2");
			return this;
		}
	}; 
	
	public void print() {
		i.show1().show2();
	}
}























