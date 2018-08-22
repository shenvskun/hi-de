package com.sk.hide.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * https://blog.csdn.net/u012809062/article/details/73251036/  javaconfig
 * https://blog.csdn.net/qq_27529917/article/details/78490956  //更全面
 * @author eric
 *
 */
public class MyWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getServletConfigClasses() {
		System.out.println("===");
		return new Class[] { MyWebConfig.class };
	}
	
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { RootConfig.class };
    }


    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
//    
//    @Override
//    protected Filter[] getServletFilters() {
//    	CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
//        characterEncodingFilter.setEncoding("UTF-8");
//        characterEncodingFilter.setForceEncoding(true);
//    	return new Filter[]{ characterEncodingFilter};
//    }
    
    
    
}