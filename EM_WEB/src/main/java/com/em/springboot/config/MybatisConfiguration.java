package com.em.springboot.config;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.em.common.dao.BaseDaoImpl;
import com.em.common.dao.IBaseDao;
import com.em.common.pulgin.mybatis.plugin.PagePlugin;

/***
 * druid数据源配置
 * 
 * @author yangy 2018年5月16日 上午9:41:24 DruidConfiguration.java
 * @Description
 */
@Configuration
@PropertySource("classpath:config.properties")
@ImportResource("classpath:/mybatis-aop-tx.xml")
public class MybatisConfiguration implements EnvironmentAware {

	@Autowired
	Environment environment;

	@Override
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

	private final static Logger logger = Logger.getLogger(MybatisConfiguration.class);

	/****
	 * 分页插件
	 * 
	 * @return
	 * @throws IOException
	 */
	@Bean
	public PagePlugin pagePlugin() {
		String dialectPrefix = "com.em.common.pulgin.jdbc.dialet.";
		Properties properties = new Properties();
		String dialetname = environment.getProperty("spring.datasource.druid.dialetname");
		properties.setProperty("dialect", dialectPrefix + dialetname);
		properties.setProperty("pageSqlId", ".*query.*page.*");
		PagePlugin pagePlugin = new PagePlugin();
		pagePlugin.setProperties(properties);
		return pagePlugin;
	}

	// mybatis的配置
	@Bean(name = "sqlSessionFactory")
	public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource) throws IOException {
		String db = environment.getProperty("spring.datasource.druid.db");
		Resource configLocation = new ClassPathResource("mybatis-config.xml");
		ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		sqlSessionFactoryBean.setConfigLocation(configLocation);
		sqlSessionFactoryBean.setMapperLocations(resourcePatternResolver.getResources("classpath*:mappers/mybatis/" + db + "/**/*.xml"));
		// sqlSessionFactoryBean.setTypeAliasesPackage("com.open.ssm.model");//
		// 别名，让*Mpper.xml实体类映射可以不加上具体包名
		PagePlugin pagePlugin = pagePlugin();
		sqlSessionFactoryBean.setPlugins(new Interceptor[] { pagePlugin });
		return sqlSessionFactoryBean;
	}

	@Bean(name = "sqlSession")
	public SqlSession sqlSessionTemplate(SqlSessionFactoryBean sqlSessionFactoryBean) throws Exception {
		SqlSession sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactoryBean.getObject());
		return sqlSessionTemplate;
	}

	@Bean(name = "transactionManager")
	public DataSourceTransactionManager dataSourceTransactionManager(DataSource dataSource) {
		DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
		dataSourceTransactionManager.setDataSource(dataSource);
		return dataSourceTransactionManager;
	}

	@Bean
	@ConditionalOnBean(SqlSessionFactoryBean.class)
	// 当 SqlSessionFactoryBean 实例存在时创建对象
	public MapperScannerConfigurer mapperScannerConfigurer() {
		MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
		mapperScannerConfigurer.setBasePackage("com.em.**.mappers");
		return mapperScannerConfigurer;
	}
}