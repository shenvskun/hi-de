package com.sk.hide.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
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

import com.alibaba.druid.pool.DruidDataSource;
import com.ibatis.sqlmap.client.SqlMapClient;

/*
 * 1 @Configuration注解在这里可以省略 RootConfig直接配置给了getRootConfigClasses方法
 * 2 如果使用applicationContext.xml中的<context:component-scan base-package="com.lianjinsoft" /> 寻找配置类 则需要@Configuration注解单纯的@Component不行 因为@Bean必须和@Configuration配合
 */
@Configuration
@EnableTransactionManagement 
@PropertySource("classpath:/resource/mydatasource.properties")
@ComponentScan(basePackages={"sk.com.util"})
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
		
		try {
			datasource.init();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return datasource;  
	}  
	
	@Bean
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
}






















