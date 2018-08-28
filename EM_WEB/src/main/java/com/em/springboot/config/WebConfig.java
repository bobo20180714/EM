package com.em.springboot.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.glassfish.jersey.servlet.ServletContainer;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.MultipartAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.annotation.Order;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.MultipartFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.util.IntrospectorCleanupListener;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.em.common.filters.CsrfFilter;
import com.em.common.filters.XssFilter;
import com.em.common.spring.interceptor.LogInterceptor;
import com.em.common.spring.interceptor.LoginInterceptor;
import com.em.common.spring.interceptor.PermissionInterceptor;
import com.em.common.utils.SysInit;

/****
 * 配置springmvc容器的bean，相当于spring-mvc.xml
 * 
 * @author yangy 2018年5月8日 上午9:55:46 WebConfig.java
 * @Description
 */
@Configuration
@EnableAutoConfiguration(exclude = {MultipartAutoConfiguration.class})
// @EnableWebMvc
@ComponentScan(basePackages = { "com.em.core.*","com.em.biz.*" }, useDefaultFilters = false, includeFilters = { @Filter(type = FilterType.ANNOTATION, classes = { Controller.class }) })
public class WebConfig extends WebMvcConfigurerAdapter {

	@Cacheable
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/jsp/");
		resolver.setSuffix(".jsp");
		resolver.setExposeContextBeansAsAttributes(true);
		resolver.setViewClass(JstlView.class);
		return resolver;
	}

	/***
	 * 定义spring mvc错误页
	 */
	@Bean
	public AbstractHandlerExceptionResolver SimpleMappingExceptionResolver() {
		Properties properties = new Properties();
		properties.setProperty("java.lang.Exception", "web_error");
		SimpleMappingExceptionResolver smer = new SimpleMappingExceptionResolver();
		smer.setExceptionMappings(properties);
		return smer;
	}

	/***
	 * 配置dispatcher映射
	 * 
	 * @param dispatcherServlet
	 * @return
	 */
	@Bean
	public ServletRegistrationBean dispatcherRegistration(DispatcherServlet dispatcherServlet) {
		ServletRegistrationBean reg = new ServletRegistrationBean(dispatcherServlet);
		reg.setLoadOnStartup(1);
		reg.getUrlMappings().clear();
		reg.addUrlMappings("*.do");
		return reg;
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		super.configureMessageConverters(converters);

		FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();

		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
		fastConverter.setFastJsonConfig(fastJsonConfig);

		converters.add(fastConverter);
	}

	/****
	 * springmvc上传文件参数设置
	 */
	@Bean
	public CommonsMultipartResolver multipartResolver() {
		String defaultEncoding = "UTF-8";
		int maxInMemorySize = 4096;
		long maxUploadSize = 104857600;
		CommonsMultipartResolver cmr = new CommonsMultipartResolver();
		cmr.setDefaultEncoding(defaultEncoding);
		cmr.setMaxUploadSize(maxUploadSize);
		cmr.setMaxInMemorySize(maxInMemorySize);
		return cmr;
	}

	@Bean
	@Order(0)
	public MultipartFilter multipartFilter() {
	    MultipartFilter multipartFilter = new MultipartFilter();
	    multipartFilter.setMultipartResolverBeanName("multipartResolver");
	    return multipartFilter;
	}

	/***
	 * 定义拦截器
	 */
	@Bean
	public HandlerInterceptor loginInterceptor() {
		return new LoginInterceptor();
	}

	@Bean
	public HandlerInterceptor permissionInterceptor() {
		return new PermissionInterceptor();
	}

	@Bean
	public HandlerInterceptor logInterceptor() {
		return new LogInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		super.addInterceptors(registry);
		registry.addInterceptor(loginInterceptor()).addPathPatterns("/**");
		registry.addInterceptor(permissionInterceptor()).addPathPatterns("/**");
		registry.addInterceptor(logInterceptor()).addPathPatterns("/**");
	}

	/*****
	 * servlet定义
	 */
	@Bean
	public ServletRegistrationBean initializeJerseyRestServlet() {
		ServletRegistrationBean registration = new ServletRegistrationBean(new ServletContainer());
		registration.addInitParameter("jersey.config.server.provider.packages", "com.em.rest.webService");
		registration.addInitParameter("jersey.config.server.provider.classnames", "org.glassfish.jersey.filter.LoggingFilter;org.glassfish.jersey.media.multipart.MultiPartFeature");
		registration.setLoadOnStartup(2);
		registration.addUrlMappings("/rest/*");
		registration.setName("rest");
		return registration;
	}

	@Bean
	public ServletRegistrationBean initializeDruidStatView() {
		ServletRegistrationBean registration = new ServletRegistrationBean(new StatViewServlet());
		registration.addInitParameter("resetEnable", "true");
		registration.addInitParameter("loginUsername", "superadmin");
		registration.addInitParameter("loginPassword", "2wdc$ESZ");
		registration.addUrlMappings("/druid/*");
		return registration;
	}

	/***
	 * filter定义
	 */

	/****
	 * xss filter配置
	 */
	@Bean
	public FilterRegistrationBean initializeXSSFilter() {
		FilterRegistrationBean registration = new FilterRegistrationBean(new XssFilter());
		registration.addUrlPatterns("/*");
		return registration;
	}

	/***
	 * csrf filter配置
	 * 
	 * @param container
	 */
	@Bean
	public FilterRegistrationBean initializeCSRFFilter() {
		FilterRegistrationBean registration = new FilterRegistrationBean(new CsrfFilter());
		registration.addUrlPatterns("/*");
		return registration;
	}

	/***
	 * druid监控
	 * 
	 * @param container
	 */
	@Bean
	public FilterRegistrationBean initializeDruidWebStatFilter() {
		FilterRegistrationBean registration = new FilterRegistrationBean(new WebStatFilter());
		Map<String, String> initParameters = new HashMap<String, String>();
		initParameters.put("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
		initParameters.put("sessionStatMaxCount", "2000");
		initParameters.put("sessionStatEnable", "true");
		registration.setInitParameters(initParameters);
		registration.addUrlPatterns("/*");
		return registration;
	}

	/****
	 * 监听器
	 */
	@Bean
	public ServletListenerRegistrationBean<IntrospectorCleanupListener> introspectorCleanupListenerRegistration() {
		ServletListenerRegistrationBean<IntrospectorCleanupListener> registration = new ServletListenerRegistrationBean<IntrospectorCleanupListener>(new IntrospectorCleanupListener());
		return registration;
	}

	@Bean
	public ServletListenerRegistrationBean<RequestContextListener> requestContextListenerRegistration() {
		ServletListenerRegistrationBean<RequestContextListener> registration = new ServletListenerRegistrationBean<RequestContextListener>(new RequestContextListener());
		return registration;
	}

	@Bean
	public ServletListenerRegistrationBean<SysInit> sysInitListenerRegistration() {
		ServletListenerRegistrationBean<SysInit> registration = new ServletListenerRegistrationBean<SysInit>(new SysInit());
		return registration;
	}

}
