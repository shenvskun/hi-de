package sk.advance.reflect;

import java.lang.reflect.Method;


public class learn_Reflect {
	public static void main(String[] args) throws Exception{
		Class<?> clazz = Class.forName("com.sk.hide.entity.User"); 
		Object obj = clazz.newInstance();
		Method method = clazz.getMethod("setName", String.class);
		Method method2 = clazz.getMethod("getName");
		method.invoke(obj, "dsf");
		System.out.println(method2.invoke(obj)); //可变参数
	}
}
