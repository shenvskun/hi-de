package com.sk.hide.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/*
 * 1 该类在相应容器配置类中被扫描就注入的是哪个ctx  2 仅仅是为了查找bean
 */
@Component
public class ApplicationContextUtil implements ApplicationContextAware {
	
	private static ApplicationContext ctx;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.ctx = applicationContext;
	}
	
	public static <T> T getBean(Class<T> t) {
		return ctx.getBean(t);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) {
		return (T)ctx.getBean(name);
	}
}
