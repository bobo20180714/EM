package com.em.common.spring.interceptor;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.em.common.controller.BaseController;
import com.em.common.spring.ComponentFactory;
import com.em.common.utils.DateUtils;
import com.em.common.utils.StringUtils;
import com.em.common.utils.cache.ICacheService;
import com.em.core.auth.operator.entity.AuthOperator;
import com.em.core.sys.log.service.ISysLogService;

/**
 * 
 * @ClassName: LogInterceptor
 * @Description: 操作日志记录拦截器(暂不使用)
 * @author: liuyx
 * @date: 2015年9月21日上午10:58:33
 */
public class LogInterceptor extends HandlerInterceptorAdapter {

	private static ISysLogService sysLogService = ComponentFactory.getBean(ISysLogService.class);
	private ThreadPoolTaskExecutor threadPool = ComponentFactory.getBean(ThreadPoolTaskExecutor.class);
	private static ICacheService ehcacheService = (ICacheService) ComponentFactory.getBean("ehcacheService");
	public static final String LOG_KEY_PRIFIX = "log_";
	/**
	 * 日志对象
	 */
	private Logger logger = Logger.getLogger(this.getClass().getName());

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		if (request.getRequestURI().indexOf("sysLog") != -1) {
			return;
		}
		AuthOperator operator = (AuthOperator) request.getSession().getAttribute(BaseController.LOGIN_IN_OPERATOR_SESSION);
		if (operator != null && operator.getOperatorId() != null) {
			Map<String, Object> log = new HashMap<String, Object>();
			log.put("LOG_ID", StringUtils.uniqueKey36());
			// 点击的资源名称,不一定注册
			log.put("CREATE_BY", operator.getOperatorName() + "(" + operator.getUserId().toString() + ")");
			log.put("CREATE_TIME", DateUtils.getCurrentDate());
			log.put("TITLE", "");
			log.put("TYPE", ex == null ? "1" : "2");
			log.put("REMOTE_ADDR", StringUtils.getRemoteAddr(request));
			log.put("USER_AGENT", request.getHeader("user-agent"));
			log.put("REQUEST_URI", request.getRequestURI());

			Map paramMap = request.getParameterMap();
			if (paramMap != null) {
				StringBuilder params = new StringBuilder();
				for (Map.Entry<String, String[]> param : ((Map<String, String[]>) paramMap).entrySet()) {
					params.append(("".equals(params.toString()) ? "" : "&") + param.getKey() + "=");
					String paramValue = (param.getValue() != null && param.getValue().length > 0 ? param.getValue()[0] : "");
					params.append(StringUtils.abbr(StringUtils.endsWithIgnoreCase(param.getKey(), "password") ? "" : paramValue, 100));
				}
				log.put("PARAMS", params.toString());
			}

			log.put("METHOD", request.getMethod());
			// 异步保存日志
			threadPool.execute(new SaveLogThread(log, handler, ex));
		}
	}

	/**
	 * 保存日志线程
	 */
	public static class SaveLogThread extends Thread implements Runnable {

		private Map log;
		private Object handler;
		private Exception ex;

		public SaveLogThread(Map log, Object handler, Exception ex) {
			super(SaveLogThread.class.getSimpleName());
			this.log = log;
			this.handler = handler;
			this.ex = ex;
		}

		@Override
		public void run() {

			String exceptionStr = "";
			if (ex != null) {
				StringWriter stringWriter = new StringWriter();
				ex.printStackTrace(new PrintWriter(stringWriter));
				exceptionStr = stringWriter.toString();
			}
			// 如果有异常，设置异常信息
			log.put("EXCEPTION", exceptionStr);
			ehcacheService.putExpireMap(LOG_KEY_PRIFIX + StringUtils.uniqueKey(), log, 7200);
		}
	}

}
