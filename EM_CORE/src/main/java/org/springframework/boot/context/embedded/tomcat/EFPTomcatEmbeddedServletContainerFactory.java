package org.springframework.boot.context.embedded.tomcat;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;

import org.apache.catalina.Context;
import org.apache.catalina.Host;
import org.apache.catalina.Lifecycle;
import org.apache.catalina.LifecycleEvent;
import org.apache.catalina.LifecycleListener;
import org.apache.catalina.Wrapper;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.loader.WebappLoader;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.startup.Tomcat.FixContextListener;
import org.apache.coyote.AbstractProtocol;
import org.springframework.boot.context.embedded.EmbeddedServletContainer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.StreamUtils;

import com.em.common.service.ConfigService;

public class EFPTomcatEmbeddedServletContainerFactory extends TomcatEmbeddedServletContainerFactory {

	private static final String CONTEXT_NAME = "/OA_DOC";
	
	private static final String CONTEXT_PATH = "/OA_DOC";
	
	private static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
	
	private Set<String> tldSkipPatterns = new LinkedHashSet<String>(TldSkipPatterns.DEFAULT);

	private ResourceLoader resourceLoader;

	public EFPTomcatEmbeddedServletContainerFactory() {
		super();
	}

	public EFPTomcatEmbeddedServletContainerFactory(int port) {
		super(port);
	}

	public EFPTomcatEmbeddedServletContainerFactory(String contextPath, int port) {
		super(contextPath, port);
	}

	@Override
	public EmbeddedServletContainer getEmbeddedServletContainer(ServletContextInitializer... initializers) {
		Tomcat tomcat = new Tomcat();
		File baseDir = createTempDir("tomcat");
		tomcat.setBaseDir(baseDir.getAbsolutePath());
		Connector connector = new Connector("org.apache.coyote.http11.Http11Protocol");//把之前的nio改成了nio2
		tomcat.getService().addConnector(connector);
		customizeConnector(connector);
		customizePrivateConnector(connector);
		tomcat.setConnector(connector);
		tomcat.getHost().setAutoDeploy(false);
		tomcat.getEngine().setBackgroundProcessorDelay(-1);
		for (Connector additionalConnector : super.getAdditionalTomcatConnectors()) {
//注释掉加载自定义tomcat实例的代码
			//tomcat.getService().addConnector(additionalConnector);
		}
		tomcat.getHost().setAutoDeploy(false);
		tomcat.getEngine().setBackgroundProcessorDelay(-1);
		//增加虚拟路径
		prepareContext_docBase(tomcat.getHost(), initializers);
		//正常访问路径
		prepareContext(tomcat.getHost(), initializers);
		return getTomcatEmbeddedServletContainer(tomcat);
	}
	
	protected void customizePrivateConnector(Connector connector) {
		if (connector.getProtocolHandler() instanceof AbstractProtocol) {
			customizePrivateProtocol((AbstractProtocol<?>) connector.getProtocolHandler());
		}
	}
	private void customizePrivateProtocol(AbstractProtocol<?> protocol) {
		protocol.setMaxConnections(500);
		//在这里随便写自定义的配置
		
	}

	protected void prepareContext_docBase(Host host, ServletContextInitializer[] initializers) {
		File docBase = getValidDocumentRoot();
		docBase = (docBase != null ? docBase : createTempDir("tomcat-docbase"));
		final TomcatEmbeddedContext context = new TomcatEmbeddedContext();
		context.setName(CONTEXT_NAME);
		context.setDisplayName(CONTEXT_NAME);
		context.setPath(CONTEXT_PATH);
		String docmentBase = ConfigService.getConfig("rootUploadPath");
		context.setDocBase(docmentBase);
		context.addLifecycleListener(new FixContextListener());
		context.setParentClassLoader(this.resourceLoader != null ? this.resourceLoader.getClassLoader() : ClassUtils.getDefaultClassLoader());
		resetDefaultLocaleMapping(context);
		addLocaleMappings(context);
		try {
			context.setUseRelativeRedirects(false);
		} catch (NoSuchMethodError ex) {
			// Tomcat is < 8.0.30. Continue
		}
		SkipPatternJarScanner.apply(context, this.tldSkipPatterns);
		WebappLoader loader = new WebappLoader(context.getParentClassLoader());
		loader.setLoaderClass(TomcatEmbeddedWebappClassLoader.class.getName());
		loader.setDelegate(true);
		context.setLoader(loader);
		if (isRegisterDefaultServlet()) {
			addDefaultServlet(context);
		}
		if (shouldRegisterJspServlet()) {
			addJspServlet(context);
			addJasperInitializer(context);
			context.addLifecycleListener(new StoreMergedWebXmlListener());
		}
		context.addLifecycleListener(new LifecycleListener() {

			@Override
			public void lifecycleEvent(LifecycleEvent event) {
				if (event.getType().equals(Lifecycle.CONFIGURE_START_EVENT)) {
					TomcatResources.get(context).addResourceJars(getUrlsOfJarsWithMetaInfResources());
				}
			}

		});
		ServletContextInitializer[] initializersToUse = mergeInitializers(initializers);
		host.addChild(context);
		configureContext(context, initializersToUse);
		postProcessContext(context);
	}

	private void resetDefaultLocaleMapping(TomcatEmbeddedContext context) {
		context.addLocaleEncodingMappingParameter(Locale.ENGLISH.toString(), DEFAULT_CHARSET.displayName());
		context.addLocaleEncodingMappingParameter(Locale.FRENCH.toString(), DEFAULT_CHARSET.displayName());
	}

	private void addLocaleMappings(TomcatEmbeddedContext context) {
		for (Map.Entry<Locale, Charset> entry : getLocaleCharsetMappings().entrySet()) {
			Locale locale = entry.getKey();
			Charset charset = entry.getValue();
			context.addLocaleEncodingMappingParameter(locale.toString(), charset.toString());
		}
	}

	private void addDefaultServlet(Context context) {
		Wrapper defaultServlet = context.createWrapper();
		defaultServlet.setName("default");
		defaultServlet.setServletClass("org.apache.catalina.servlets.DefaultServlet");
		defaultServlet.addInitParameter("debug", "0");
		defaultServlet.addInitParameter("listings", "false");
		defaultServlet.setLoadOnStartup(1);
		// Otherwise the default location of a Spring DispatcherServlet cannot
		// be set
		defaultServlet.setOverridable(true);
		context.addChild(defaultServlet);
		addServletMapping(context, "/", "default");
	}

	private void addJspServlet(Context context) {
		Wrapper jspServlet = context.createWrapper();
		jspServlet.setName("jsp");
		jspServlet.setServletClass(getJspServlet().getClassName());
		jspServlet.addInitParameter("fork", "false");
		for (Entry<String, String> initParameter : getJspServlet().getInitParameters().entrySet()) {
			jspServlet.addInitParameter(initParameter.getKey(), initParameter.getValue());
		}
		jspServlet.setLoadOnStartup(3);
		context.addChild(jspServlet);
		addServletMapping(context, "*.jsp", "jsp");
		addServletMapping(context, "*.jspx", "jsp");
	}

	private void addJasperInitializer(TomcatEmbeddedContext context) {
		try {
			ServletContainerInitializer initializer = (ServletContainerInitializer) ClassUtils.forName("org.apache.jasper.servlet.JasperInitializer", null).newInstance();
			context.addServletContainerInitializer(initializer, null);
		} catch (Exception ex) {
			// Probably not Tomcat 8
		}
	}

	private static class StoreMergedWebXmlListener implements LifecycleListener {

		private static final String MERGED_WEB_XML = "org.apache.tomcat.util.scan.MergedWebXml";

		@Override
		public void lifecycleEvent(LifecycleEvent event) {
			if (event.getType().equals(Lifecycle.CONFIGURE_START_EVENT)) {
				onStart((Context) event.getLifecycle());
			}
		}

		private void onStart(Context context) {
			ServletContext servletContext = context.getServletContext();
			if (servletContext.getAttribute(MERGED_WEB_XML) == null) {
				servletContext.setAttribute(MERGED_WEB_XML, getEmptyWebXml());
			}
		}

		private String getEmptyWebXml() {
			InputStream stream = TomcatEmbeddedServletContainerFactory.class.getResourceAsStream("empty-web.xml");
			Assert.state(stream != null, "Unable to read empty web.xml");
			try {
				try {
					return StreamUtils.copyToString(stream, Charset.forName("UTF-8"));
				} finally {
					stream.close();
				}
			} catch (IOException ex) {
				throw new IllegalStateException(ex);
			}
		}

	}

	@SuppressWarnings("deprecation")
	private void addServletMapping(Context context, String pattern, String name) {
		context.addServletMapping(pattern, name);
	}
}
