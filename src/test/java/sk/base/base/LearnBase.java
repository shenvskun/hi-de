package sk.base.base;

import sk.base.learn_abstract.IProvinceGovernMent;
import sk.base.learn_enum.Animal;
import sk.base.learn_enum.OrderStatus;

public class LearnBase {
	public static void main(String[] args) {
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

			@Override
			public void shoushui() {
				
			}};
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























