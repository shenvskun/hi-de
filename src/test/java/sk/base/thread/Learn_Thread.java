package sk.base.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.sk.hide.exception.HideException;

public class Learn_Thread {
	public static void main(String[] args) throws InterruptedException {
		//子线程出现异常中断 不会影响父线程
//		T1 t1 = new T1();
//		t1.start();
//		Thread.sleep(10000);
//		System.out.println("主线程终止");
		
		//线程优先级
		System.out.println("优先级从低到高 ： " + Thread.MIN_PRIORITY + " : " + Thread.NORM_PRIORITY + " : " + Thread.MAX_PRIORITY);
		
		System.out.println("====固定长度线程池");
		ExecutorService ftp = Executors.newFixedThreadPool(4);
		for (int i = 0; i < 8; i++) {
			final int k = i;
			ftp.execute(new Runnable() {
				
				@Override
				public void run() {
					for (int j = 0; j <= 100; j++) {
						System.out.println("T " + k + "  正在执行。。" + j);
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			});
		}
		
		System.out.println(ftp.isShutdown());
		System.out.println(ftp.isTerminated());
		if(!ftp.awaitTermination(10, TimeUnit.SECONDS)) {
			ftp.shutdownNow();
		}
		System.out.println(ftp.isTerminated());
		System.out.println(ftp.isShutdown());
	}
}
class T1 extends Thread{
	@Override
	public void run() {
		System.out.println("子线程执行" + Thread.currentThread().getName());
		if(true) 
			throw new HideException(4, "线程异常");
		System.out.println("未执行的代码");
	}
}
