package sk.base.pattern;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 单例模式
 * 单例模式确保某个类只有一个实例，而且自行实例化并向整个系统提供这个实例。在计算机系统中，线程池、缓存、日志对象、对话框、打印机、显卡的驱动程序对象常被设计成单例。这些应用都或多或少具有资源管理器的功能。每台计算机可以有若干个打印机，但只能有一个Printer Spooler，以避免两个打印作业同时输出到打印机中。每台计算机可以有若干通信端口，系统应当集中管理这些通信端口，以避免一个通信端口同时被两个请求同时调用。总之，选择单例模式就是为了避免不一致状态，避免政出多头。
 * @author eric
 *
 */
public class Learn_Singleton {

	public static void main(String[] args) {
		ExecutorService ftp = Executors.newFixedThreadPool(2);
		for (int i = 0; i < 5; i++) {
			ftp.execute(new Runnable() {
				@Override
				public void run() {
					User2 su = User2.getSingleTon();
					System.out.println(su.getName());
					
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
					}
				}
			});
		}
		try {
			boolean awaitTermination = ftp.awaitTermination(10, TimeUnit.SECONDS);//等10秒看是否所有线程都结束了
			if(!awaitTermination) {
				ftp.shutdown();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
}

//饿汉模式
class User {
	private static User u = new User("饿汉单例");
	String name;
	
	private User(String name) {
		this.name = name;
	}

	public static User getSingleton() {
		return u;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

//懒汉模式
class User2{
	String name;
	private static User2 u2;
	
	private User2(String name) {
		super();
		this.name = name;
	}

	public static User2 getSingleTon() {
		if(u2==null) {                //3 在2基础上 避免所有线程都必须等待前面线程释放锁才能获取对象
			synchronized ("this") {   //2 在1基础上 避免并发访问得到多个实例
				if (u2==null) {		//1
					u2 = new User2("懒汉模式");
				}
			}
		}
		return u2;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
