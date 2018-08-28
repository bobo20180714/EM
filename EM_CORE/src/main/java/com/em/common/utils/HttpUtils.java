/**
 * Copyright2015-2019 em All Rights Reserved.
 */
package com.em.common.utils;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.HtmlUtils;
import org.springframework.web.util.JavaScriptUtils;

/**
 * @ClassName: HttpUtils
 * @Description: 和requst response session等对象相关的工具类
 * @author: liuyx
 * @date: 2015年8月28日下午4:32:51
 */
public class HttpUtils {
	/**
	 * 
	 * @Title: getRequestMap
	 * @author：liuyx
	 * @date：2015年8月28日下午4:37:08
	 * @Description: 获取简单表单对象，转化为map
	 * @param request
	 * @return
	 */
	public static Map<String, Object> getRequestMap(HttpServletRequest request) {
		Map<String, Object> requestMap = new HashMap<String, Object>();
		Enumeration<String> enu = request.getParameterNames();
		String[] valueArrayTemp = null;
		String valueTemp = null;
		StringBuilder sb = new StringBuilder();
		while (enu.hasMoreElements()) {
			String key = enu.nextElement().toString();
			valueArrayTemp = request.getParameterValues(key);
			valueTemp = "";

			if (valueArrayTemp.length <= 1) {
				valueTemp = request.getParameter(key);
				// valueTemp = HtmlUtils.htmlEscape(valueTemp);
				// valueTemp = JavaScriptUtils.javaScriptEscape(valueTemp);
				requestMap.put(key, valueTemp);
			} else {
				requestMap.put(key, StringUtils.join(valueArrayTemp, ","));
			}

		}
		return requestMap;
	}

	/**
	 * 
	 * @Title: getCurrentRequest
	 * @author：liuyx
	 * @date：2016年1月13日下午6:14:43
	 * @Description: 获取当前request
	 * @return
	 * @throws IllegalStateException
	 *             当前线程不是web请求抛出此异常.
	 */
	public static HttpServletRequest getCurrentRequest() throws IllegalStateException {
		ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		if (attrs == null) {
			throw new IllegalStateException("当前线程中不存在 Request 上下文");
		}
		return attrs.getRequest();
	}

	public static void main(String[] args) {
		String[] a = { "abc", "efg" };
		System.out.println(a.toString());
	}
}
