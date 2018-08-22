package sk.base.learnmath;

import java.util.Random;

public class Learn_Math {
	int i = 3+2;
	public static void main(String[] args) {
		System.out.println("-==Math.pow(a, b) doble类型  a的b次方=");
		double pow = Math.pow(2, 2);
		System.out.println(pow);
		
		System.out.println("=============");
		System.out.println("=======绝对值======");
		
		System.out.println(Math.abs(-392));

		System.out.println("=============");
		System.out.println("=======四舍五入======");
		System.out.println(Math.round(-12.6));
		
		System.out.println("======随机数======");
		Random random = new Random();
		int nextInt = random.nextInt(10);
		System.out.println(nextInt);
	}
}
