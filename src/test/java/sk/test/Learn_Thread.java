package sk.test;

import org.apache.http.client.protocol.HttpClientContext;

public class Learn_Thread {
	public static void main(String[] args) {
		//join
		joinTest();
		
		//同一把锁
		lockTest();
		
	}

	private static void lockTest() {
		LockThread l1 = new LockThread();
		LockThread l2 = new LockThread();
		l1.start();
		l2.start();
	}

	private static void joinTest() {
		TestThread tt1 = new TestThread();
		TestThread tt2 = new TestThread();
		tt1.start();
		tt2.start();
		try {
			tt2.join();
		} catch (InterruptedException e) {}
		System.out.println("主线程结束");
	}
}

class TestThread extends Thread{
	@Override
	public void run() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {}
		System.out.println("打印到控制台");
	}
}

class  LockThread extends Thread{
	public static Integer count = 0;
	
	@Override
	public void run() {
		synchronized ("this") {
			while (true) {
				count++;
				
				System.out.println(Thread.currentThread().getName()+ " : " + count);
				
				try {
					"this".wait(1000);
				} catch (InterruptedException e) {}
			}
		}
	}
}