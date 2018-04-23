package com.sk.hide.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.VersionResourceResolver;

@Configuration
@ComponentScan(basePackages={"com.sk.hide"})
@EnableWebMvc
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
        registry.jsp();
    }
	
	//@RequestBody @ResponseBody 需要用到消息转换器实现不同格式字符串与java对象之间的转化 pom.xml中配置com.google.code.gson就会自动注册json的消息转换器

}
