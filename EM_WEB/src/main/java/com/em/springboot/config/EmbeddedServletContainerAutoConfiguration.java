package com.em.springboot.config;

import javax.servlet.Servlet;

import org.apache.catalina.startup.Tomcat;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.SearchStrategy;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.EFPTomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmbeddedServletContainerAutoConfiguration {
	@Configuration
	@ConditionalOnClass({ Servlet.class, Tomcat.class })
	@ConditionalOnMissingBean(value = EmbeddedServletContainerFactory.class, search = SearchStrategy.CURRENT)
	public static class EmbeddedTomcat {
		@Bean
		public TomcatEmbeddedServletContainerFactory tomcatEmbeddedServletContainerFactory() {
			EFPTomcatEmbeddedServletContainerFactory tomcatEmbeddedServletContainerFactory = new EFPTomcatEmbeddedServletContainerFactory();
			return tomcatEmbeddedServletContainerFactory;
		}

	}

	// public @Bean EmbeddedServletContainerFactory
	// embeddedServletContainerFactory() {
	// TomcatEmbeddedServletContainerFactory factory = new
	// TomcatEmbeddedServletContainerFactory() {
	// @Override
	// protected void configureContext(Context context,
	// ServletContextInitializer[] initializers) {
	// context.setDocBase("d:/upload");
	// context.setPath("/upload");
	// super.configureContext(context, initializers);
	//
	//
	// }
	//
	// };
	//
	// return factory;
	// }

}
