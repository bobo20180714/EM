package com.em.springboot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import com.alibaba.druid.util.DruidPasswordCallback;
import com.em.common.controller.ExceptionResolver;
import com.em.common.druid.DBPasswordCallback;
import com.em.common.spring.ComponentFactory;
import com.em.common.utils.cache.RedisService;

/***
 * 定义spring容器中的bean，类似于spring.xml。
 * 
 * @author yangy 2018年5月8日 上午10:46:32 RootConfig.java
 * @Description
 */

@Configuration
@ComponentScan(basePackages = { "com.em.core", "com.em.common"}, excludeFilters = { @Filter(type = FilterType.ANNOTATION, classes = { Controller.class }) })
@EnableAspectJAutoProxy(proxyTargetClass=true)
@PropertySource("classpath:config.properties")
@EnableAsync	//代替task:annotation-driven
@EnableScheduling //代替task:annotation-driven
public class RootConfig {
	
	@Value("${redis.host}")
	private String redisHost;
	
	@Value("${redis.port}")
	private int redisPort;
	
	/***
	 * 定义获取spring context
	 * @return
	 */
	@Bean
	@Scope("singleton")
	public ComponentFactory getApplicationContextFactory() {
		ComponentFactory componentFactory = new ComponentFactory();
		return componentFactory;
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	/***
	 * 应用部署时密码加密
	 */
	@Bean(name="dbPasswordCallback")
	@Lazy(value = true)
	public DruidPasswordCallback dBPasswordCallback() {
		DBPasswordCallback dbPasswordCallback = new DBPasswordCallback();
		return dbPasswordCallback;
	}

	/***
	 * 处理controller抛出的异常
	 */
	@Bean
	public ExceptionResolver exceptionResolver() {
		ExceptionResolver exceptionResolver = new ExceptionResolver();
		return exceptionResolver;
	}

	/***
	 * 配置国际化
	 */
	@Bean(name="messageSource")
	public MessageSource resourceBundleMessageSource() {
		ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
		resourceBundleMessageSource.setBasename("messages.message");
		return resourceBundleMessageSource;
	}
	
	/***
	 * 基于cookie的本地化解析
	 */
	@Bean
	public CookieLocaleResolver cookieLocaleResolver() {
		Integer cookieMaxAge = 604800;
		CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
		cookieLocaleResolver.setCookieName("Language");
		cookieLocaleResolver.setCookieMaxAge(cookieMaxAge);
		return cookieLocaleResolver;
	}
	
	/*****
	 * 缓存redis
	 */
	@Bean
	public JedisPoolConfig jedisPoolConfig() {
		int maxTotal = 300;
		int maxIdle = 200;
		int minIdle = 150;
		long maxWaitMillis = 5000;
		boolean testOnBorrow = false;
		boolean testOnReturn = false;
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxTotal(maxTotal);
		jedisPoolConfig.setMaxIdle(maxIdle);
		jedisPoolConfig.setMinIdle(minIdle);
		jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
		jedisPoolConfig.setTestOnBorrow(testOnBorrow);
		jedisPoolConfig.setTestOnReturn(testOnReturn);
		return jedisPoolConfig;
	}
	@Bean
	public JedisPool jedisPool(JedisPoolConfig jedisPoolConfig) {
		JedisPool jedisPool = new JedisPool(jedisPoolConfig, redisHost, redisPort);
		return jedisPool;
	}
	@Bean(name="redisService")
	public RedisService getRedisBean(JedisPool jedisPool) {
		RedisService redisService = new RedisService();
		redisService.setCacheManager(jedisPool);  //或者用jedisPool(jedisPoolConfig).getObject()
		return redisService;
	}
	
	/***
	 * 注册定时任务工厂bean
	 */
	@Bean
	public SchedulerFactoryBean schedulerFactoryBean() {
		SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
		return schedulerFactoryBean;
	}
	
	/***
	 * spring线程池
	 */
	@Bean
	public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
		ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
		threadPoolTaskExecutor.setCorePoolSize(10);
		threadPoolTaskExecutor.setKeepAliveSeconds(300);
		threadPoolTaskExecutor.setMaxPoolSize(100);
		return threadPoolTaskExecutor;
	}
	
	/***
	 * 启用定时器注解
	 */
	@Bean(name="taskScheduler")
	public TaskScheduler concurrentTaskScheduler() {
		ConcurrentTaskScheduler taskScheduler = new ConcurrentTaskScheduler();
		return taskScheduler;
	}

}
