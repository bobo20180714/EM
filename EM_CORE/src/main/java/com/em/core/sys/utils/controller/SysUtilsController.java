/**
 * Copyright2015-2019 em All Rights Reserved.
 */
package com.em.core.sys.utils.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.em.common.controller.BaseController;
import com.em.common.retobj.RetObj;
import com.em.common.spring.ComponentFactory;
import com.em.common.utils.MybatisMapperLoadUtil;

/**
 * 
 * @ClassName: SysUtilsController
 * @Description: TODO
 * @author: liuyx
 * @date: 2016年1月5日下午5:43:52
 */
@Controller
@RequestMapping("/sysUtils")
public class SysUtilsController extends BaseController {
	// 日志记录器
	private final Logger logger = Logger.getLogger(this.getClass());

	/**
	 * 
	 * @Title: refreshMappers
	 * @author：liuyx
	 * @date：2015年12月30日下午6:39:09
	 * @Description: 重新加载所有mapper xml文件
	 * @param request
	 * @return
	 */
	@RequestMapping("/refreshMappers")
	@ResponseBody
	public RetObj refreshMappers(HttpServletRequest request) {
		MybatisMapperLoadUtil mapperUtil = (MybatisMapperLoadUtil) ComponentFactory.getBean(MybatisMapperLoadUtil.class);
		try {
			mapperUtil.getScanner().reloadXML();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e);
			return new RetObj(false, request);
		}
		return new RetObj(true, request);
	}

}
