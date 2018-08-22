package com.sk.hide.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.ibatis.SqlMapClientFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import sk.com.util.MyInjectLogic;

import com.alibaba.druid.pool.DruidDataSource;
import com.ibatis.sqlmap.client.SqlMapClient;

/*
 * 1 @Configuration注解在这里可以省略 RootConfig直接配置给了getRootConfigClasses方法
 * 2 如果使用applicationContext.xml中的<context:component-scan base-package="com.lianjinsoft" /> 寻找配置类 则需要@Configuration注解单纯的@Component不行 因为@Bean必须和@Configuration配合
 */
//@Configuration
@EnableTransactionManagement //开启事务管理 NO.1 <tx:annotation-driven transaction-manager=""/>
@PropertySource("classpath:/resource/mydatasource.properties")
@ComponentScan(basePackages={"sk.com.util"})
@MapperScan(basePackages = {"com.sk.hide.dao"})
public class RootConfig {
	public static Logger logger = Logger.getLogger(RootConfig.class);
	/*
	 * @PropertySource给出配置文件路径  @Value从指定文件中取值
	 */
//	@Value("${name}")
//	private String name;
	
	@Value("${jdbc.url}")
    private String url;  
	
	@Value("${jdbc.username}")
    private String username;  
	
	@Value("${jdbc.password}")
    private String password;  
	
	@Value("${jdbc.driverClassName}")
    private String driverClassName;  
	
	@Value("${jdbc.initialSize}")
    private int initialSize;  
	
	@Value("${jdbc.minIdle}")
    private int minIdle;  
	
	@Value("${jdbc.maxActive}")
    private int maxActive;  
	
	@Value("${jdbc.maxWait}")
    private int maxWait;  
	
	@Value("${jdbc.timeBetweenEvictionRunsMillis}")
    private int timeBetweenEvictionRunsMillis;  
	
	@Value("${jdbc.minEvictableIdleTimeMillis}")
    private int minEvictableIdleTimeMillis;  
	
	@Value("${jdbc.validationQuery}")
    private String validationQuery;  
	
	@Value("${jdbc.testWhileIdle}")
    private boolean testWhileIdle;  
	
	@Value("${jdbc.testOnBorrow}")
    private boolean testOnBorrow;  
	
	@Value("${jdbc.testOnReturn}")
    private boolean testOnReturn;  
	
	@Value("${jdbc.poolPreparedStatements}")
    private boolean poolPreparedStatements;  
	
	@Value("${jdbc.maxPoolPreparedStatementPerConnectionSize}")
    private int maxPoolPreparedStatementPerConnectionSize;  
	
	@Value("${jdbc.filters}")
    private String filters;  
	
	/**
	 * <bean class="org.springframework.beans.factory.config.PropertyPlaceholderConfigurer">
		<property name="location">
			<value>classpath:/properties/config-test.properties</value>
		</property>
	</bean>
	 * @return
	 */
//	@Bean
//	public PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
//		PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
//		ppc.setLocation(new ClassPathResource("/resource/mydatasource.properties"));
//		return ppc;
//	}
	
	
	
	@Bean    
	@Primary  //在同样的DataSource中，首先使用被标注的DataSource  
	public DataSource dataSource(){  
		DruidDataSource datasource = new DruidDataSource();  
		
		datasource.setUrl(url);  
		datasource.setUsername(username);  
		datasource.setPassword(password);  
		datasource.setDriverClassName(driverClassName);  
		
		//configuration  
		datasource.setInitialSize(initialSize);  
		datasource.setMinIdle(minIdle);  
		datasource.setMaxActive(maxActive);  
		datasource.setMaxWait(maxWait);  
		datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);  
		datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);  
		datasource.setValidationQuery(validationQuery);  
		datasource.setTestWhileIdle(testWhileIdle);  
		datasource.setTestOnBorrow(testOnBorrow);  
		datasource.setTestOnReturn(testOnReturn);  
		datasource.setPoolPreparedStatements(poolPreparedStatements);  
		datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);  
		try {  
			datasource.setFilters(filters);  
		} catch (SQLException e) {  
			logger.error("druid configuration initialization filter", e);  
		}  
//		datasource.setConnectionProperties(connectionProperties);  
		
//		try {
//			datasource.init();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		logger.info("datasource init----------------------");
		return datasource;  
	}  
	
	@Bean //开启事务管理 NO.2
	public PlatformTransactionManager dataSourceTransactionManager() {
		
		return new DataSourceTransactionManager(dataSource());
	}
	
	@Bean
	public SqlMapClient sqlMapClient(DataSource dataSource) {
		SqlMapClientFactoryBean sqlMap = new SqlMapClientFactoryBean();
		sqlMap.setConfigLocation(new ClassPathResource("/config/sqlMapConfig.xml"));
		sqlMap.setDataSource(dataSource);
		try {
			sqlMap.afterPropertiesSet();
		} catch (Exception e) {
			logger.error("sqlMapClient configuration initialization config properties", e); 
		}
		SqlMapClient sqlMapClient = sqlMap.getObject();
		return sqlMapClient;
	}
	
	@Bean
	public SqlSessionFactoryBean sqlSessionFactoryBean() {
//		String resource = "classpath:/mybatis/sqlMapConfig.xml";
		SqlSessionFactoryBean ssfb = new SqlSessionFactoryBean();
		ssfb.setDataSource(dataSource());
//		ssfb.setConfigLocation(new ClassPathResource("/mybatis/sqlMapConfig.xml")); //可以不需要配置文件  别名 有setTypeAlias方法解决
//		ssfb.setMapperLocations(mapperLocations); mapperLocations是resource数组 传递mapper.xml的位置
		return ssfb;
	}
	
	//----★★★★★★★★★★----以下代码在JavaConfig之下必须用@MapperScan方式替换
	/**
	 *  <!-- mybatis的Mapper的扫描器 MapperScannerConfigure  会自动扫描  
         mapper包下的所有接口自动生成代理对象: 对象名字为Mapper接口类名(首字母小写)  
     -->  
    <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">  
        <property name="basePackage" value="com.steadyjack.mapper"></property>  
          
        <!-- 注意这里是SqlSessionFactoryBeanName -->  
        <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory"></property>  
    </bean>  
	 */
//	@Bean
//	@DependsOn("propertiesFactoryBean")
//	public MapperScannerConfigurer mapperScannerConfigurer() {
//		MapperScannerConfigurer mc = new MapperScannerConfigurer();
//		mc.setBasePackage("com.sk.hide.dao");
//		mc.setSqlSessionFactoryBeanName("sqlSessionFactory");
//		return mc;
//	}
	
	
}


















