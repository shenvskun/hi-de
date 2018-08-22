package sk.com.util;

import java.lang.reflect.Field;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

//需求 自定义一个类MyBean 实例由Spring管理 自己实现注入逻辑

public class MyInjectLogic implements ApplicationContextAware {

	@Override
	public void setApplicationContext(ApplicationContext ct) throws BeansException {
		//找出FindMe注解的类
		Map<String, Object> bwas = ct.getBeansWithAnnotation(FindMe.class);
		for (Object o : bwas.values()) {
			Field[] fields = o.getClass().getDeclaredFields();
			
			//将FindMe注解的类中 注解了AutowiredMe的成员变量 设置值
			for (Field field : fields) {
				field.setAccessible(true);
//				AutowiredMe[] annotationsByType = field.getAnnotationsByType(AutowiredMe.class);
				boolean isanno = field.isAnnotationPresent(AutowiredMe.class);
				if(isanno) {
					try {
						field.set(o, new Writer("王安石"));
					} catch (Exception e) {
						System.out.println("设置书法家出错");
						return;
					}
				}
			}
			
			//将这个类对象放入IOC -- 好像办不到
			MyBean mb = (MyBean)o;
			mb.doSth("小粉丝");
		}
	}

}
