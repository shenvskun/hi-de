package sk.base.array;

import static org.junit.Assert.*;

import org.junit.Test;

public class Learn_Array {
	
	/*
	 * 冒泡排序
	 */
	@Test
	public void test1() {
		int[] a = new int[]{3,5,67,21,20};
		for (int i = 1; i <= a.length-1; i++) {
			int temp;
			for (int j = 0; j < a.length-i; j++) {
				if(a[j] > a[j+1]) {
					temp = a[j];
					a[j] = a[j+1];
					a[j+1] = temp;
				}
			}
		}
		for (int i : a) {
			System.out.println(i);
		}
	}
	
	/*
	 * 顺序排序
	 */
	@Test
	public void test2() {
		int[] a = {32,43,67,16,44};
		int temp;
		for (int i = 0; i < a.length-1; i++) {
			for (int j = i+1; j < a.length; j++) {
				if(a[i]<a[j]) {
					temp = a[i];
					a[i] = a[j];
					a[j] = temp;
				}
			}
		}
		for (int i : a) {
			System.out.println(i);
		}
	}
	@Test
	public void test3() {
		fail("Not yet implemented");
	}

}
