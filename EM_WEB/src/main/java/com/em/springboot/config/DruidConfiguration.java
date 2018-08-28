package com.em.springboot.config;

import java.util.Arrays;

import org.springframework.aop.support.JdkRegexpMethodPointcut;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.spring.stat.DruidStatInterceptor;

/***
 * druid数据源配置
 * @author yangy
 * 2018年5月16日 上午9:41:24
 * DruidConfiguration.java
 * @Description
 */
@Configuration
@AutoConfigureBefore(MybatisConfiguration.class)	//加载完druidConfiguration后加载mybatisConfiguration
public class DruidConfiguration {
	
    @ConfigurationProperties(prefix = "spring.datasource.druid")
    @Bean(initMethod = "init",destroyMethod = "close")
    public DruidDataSource dataSource() {
        DruidDataSource ds = new DruidDataSource();
        ds.setProxyFilters(Arrays.asList(statFilter()));
        return ds;
    }
    @Bean
    public Filter statFilter() {
        StatFilter filter = new StatFilter();
        filter.setSlowSqlMillis(5000);
        filter.setLogSlowSql(true);
        filter.setMergeSql(true);
        return filter;
    }
    
	@Bean(name="druid-stat-interceptor")
	public DruidStatInterceptor druidStatInterceptor() {
		DruidStatInterceptor druidStatInterceptor = new DruidStatInterceptor();
		return druidStatInterceptor;
	}
	
	@Bean(name="druid-stat-pointcut")
	@Scope("prototype")
	public JdkRegexpMethodPointcut jdkRegexpMethodPointcut() {
		String patterns = "com.em.*?service.*";
		JdkRegexpMethodPointcut jdkRegexpMethodPointcut = new JdkRegexpMethodPointcut();
		jdkRegexpMethodPointcut.setPatterns(patterns);
		return jdkRegexpMethodPointcut;
	}
	
}