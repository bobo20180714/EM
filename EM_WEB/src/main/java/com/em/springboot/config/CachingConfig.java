package com.em.springboot.config;

import org.apache.log4j.Logger;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.em.common.utils.cache.EhcacheService;

/***
 * 缓存ehcache
 */
@Configuration
@EnableCaching
// <!-- 启用缓存注解 -->类似于<cache:annotation-driven cache-manager="cacheManager" />
public class CachingConfig {

	private static final Logger logger = Logger.getLogger(CachingConfig.class);

	@Bean(name = "realCache")
	public EhCacheManagerFactoryBean ehCacheManagerFactoryBean() {
		EhCacheManagerFactoryBean ehCacheManagerFactoryBean = new EhCacheManagerFactoryBean();
		ehCacheManagerFactoryBean.setConfigLocation(new ClassPathResource("cache/ehcache.xml"));
		return ehCacheManagerFactoryBean;
	}

	@Bean(name = "cacheManager")
	public CacheManager cacheManager() {
		logger.info("EhCacheCacheManager");
		EhCacheCacheManager cacheManager = new EhCacheCacheManager();
		cacheManager.setCacheManager(ehCacheManagerFactoryBean().getObject());
		return cacheManager;
	}

	@Bean(name = "ehcacheService")
	public EhcacheService ehcacheService() {
		EhcacheService ehcacheService = new EhcacheService();
		ehcacheService.setCacheManager(ehCacheManagerFactoryBean().getObject());
		return ehcacheService;
	}
}
