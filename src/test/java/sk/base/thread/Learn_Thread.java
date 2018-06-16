package sk.base.thread;

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
