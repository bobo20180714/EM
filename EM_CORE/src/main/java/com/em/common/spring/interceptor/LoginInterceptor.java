package com.em.common.spring.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;
import com.em.common.controller.BaseController;
import com.em.common.page.PageView;
import com.em.common.retobj.RetObj;

//import base.common.Access;

/**
 * 登录拦截器
 * 
 * @ClassName: LoginInterceptor
 * @Description: 判断是否有session，或者是否是无需验证的请求链接
 * @author: liuyx
 * @date: 2015年9月21日上午11:16:18
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

	public static final String[] noFilters = new String[] { "/login", "/loginCheck", "/sessionTimeOut", "/logout", "/validateImg", "/sysFile/batchUpload.do", "/weixin", "/apiLogin", "/apiLoginCheck", "/druid","/testupload" };
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// HandlerMethod method = (HandlerMethod) handler;
		// Access access = method.getMethodAnnotation(Access.class);
		// if (access != null) {
		// String role = access.role();
		// System.out.println(role +
		// "  fffffffffffffffffffffffffffffffffffffffffff");
		// }

		// method.getMethodAnnotation(annotationType)
		// System.out.println(method.);
		// MethodNameResolver methodNameResolver = new
		// InternalPathMethodNameResolver();
		// System.out.println("methodName="+methodNameResolver.getHandlerMethodName(request));

		String path = request.getContextPath();
		// String ctxPath = (String)
		// request.getSession().getServletContext().getAttribute("ctx");
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
		/*
		 * String[] noFilters = new String[] { "/login", "/verficationCode",
		 * "loginCheck", "/error", "/sessionTimeOut", "/directAudit",
		 * "/register","/logout","/noPermission"};
		 */

		String uri = request.getRequestURI();
		boolean pass = false;
		for (int i = 0; i < noFilters.length; i++) {
			if (uri.startsWith(path + noFilters[i])) {
				pass = true;
				break;
			}
		}
		if (pass) {
			return true;
		} else {
			// test only
			// 当找到session时返回这个session，如果找不到则创建一个返回
			// HttpSession a1 = request.getSession();
			// 当找到session时返回这个session，如果找不到则返回null
			// HttpSession a2 = request.getSession(false);
			if (request.getSession().getAttribute(BaseController.LOGIN_IN_OPERATOR_SESSION) != null) {
				return true;
			} else {
				response.setHeader("Content-type", "application/json; charset=UTF-8");
				response.setCharacterEncoding("UTF-8");
				if (request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
					// && request.getMethod().equalsIgnoreCase("POST")
					try (PrintWriter pw = response.getWriter();) {
						RetObj ret = new RetObj(false, "sessionOut");
						String retJsonStr = JSON.toJSONString(ret);
						pw.write(retJsonStr);
						pw.flush();
						pw.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					response.sendRedirect(basePath + "sessionTimeOut.do");
				}

				return false;
			}
		}
	}

}
