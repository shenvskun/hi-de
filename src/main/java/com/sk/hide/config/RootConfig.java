package com.sk.hide.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/*
 * 1 @Configuration注解在这里可以省略 RootConfig直接配置给了getRootConfigClasses方法
 * 2 如果使用applicationContext.xml中的<context:component-scan base-package="com.lianjinsoft" /> 寻找配置类 则需要@Configuration注解单纯的@Component不行 因为@Bean必须和@Configuration配合
 */
@Configuration
//@PropertySource("classpath:/resource/user.properties")
@ComponentScan(basePackages={"sk.com.util"})
public class RootConfig {
	
	/*
	 * @PropertySource给出配置文件路径  @Value从指定文件中取值
	 */
//	@Value("${name}")
//	private String name;
	
}






















