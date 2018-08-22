package sk.base.random;

import java.util.Random;

public class Learn_Random {
	public static void main(String[] args) {
		Random r = new Random();
		for(int i=1; i<100; i++) {
			System.out.println(r.nextInt(5)); //0-4的随机整数
			System.out.println("===="+ Thread.activeCount());//don't know what's mean
		}
	}
}
