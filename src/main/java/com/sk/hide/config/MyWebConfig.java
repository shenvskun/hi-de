package com.sk.hide.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.VersionResourceResolver;

import sk.com.util.MyInjectLogic;

/*
 * https://blog.csdn.net/qq_27529917/article/details/78490956  继承WebMvcConfigurerAdapter 可以有更多配置
 */

@Configuration
@ComponentScan(basePackages={"com.sk.hide"})
@EnableWebMvc 	//        <mvc:annotation-driven /> 启用处理器映射器 处理器适配器 的默认配置  
@EnableScheduling
public class MyWebConfig implements WebMvcConfigurer{

	
	//��̬��Դ����
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("classpath:/static/")
                .resourceChain(true)
                .addResolver(new VersionResourceResolver().addContentVersionStrategy("/**"));
    }
	
	@Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
//        registry.enableContentNegotiation(new MappingJackson2JsonView());
        registry.jsp(); //默认的是/WEB-INF/   .jsp
//        registry.jsp("/WEB-INF/jsp/", ".jsp");
    }
	
	//@RequestBody @ResponseBody 需要用到消息转换器实现不同格式字符串与java对象之间的转化 pom.xml中配置com.google.code.gson就会自动注册json的消息转换器
	
	@Bean
	public MultipartResolver multipartResolver() {
		CommonsMultipartResolver mr = new CommonsMultipartResolver();
		mr.setMaxUploadSize(1024*1024);
		mr.setDefaultEncoding("utf-8");
		return mr;
	}
	
//	@Bean
//	public MyInjectLogic myInjectLogic() {
//		MyInjectLogic mil = new MyInjectLogic();
//		return mil;
//	}
}
