/**
 * Copyright2015-2019 em All Rights Reserved.
 */
package com.em.core.sys.log.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.em.common.controller.BaseController;
import com.em.common.page.PageView;
import com.em.common.utils.HttpUtils;
import com.em.core.auth.role.entity.AuthRole;
import com.em.core.sys.log.entity.SysLog;
import com.em.core.sys.log.service.ISysLogService;

/**
 * @ClassName: SysLogController.java
 * @Description:
 * @author yuqing
 * @version 2015年10月19日 下午2:44:06
 */
@Controller
@RequestMapping("/sysLog")
public class SysLogController extends BaseController {

	@Autowired
	private ISysLogService logService;
	
	// XXX
	@RequestMapping("/getPageData")
	@ResponseBody
	public PageView<SysLog> getPageData(HttpServletRequest request) {
		PageView<SysLog> pageView = new PageView<SysLog>(request);

		Map<String, Object> record = HttpUtils.getRequestMap(request);
		// 对应查询的mybatis的xml中的id必须以queryPage结尾，查询中加入select top 100
		// percent，结束时并按某个字段排序，否则不能进行分页
		try {
			pageView = logService.queryPage(pageView, record);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pageView;
	}
// XXX
	@RequestMapping("/forQueryPage")
	public String forQueryPage(HttpServletRequest request, PageView pageView) {
		// try {
		// pageView = logService.queryPage(pageView, record);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// request.setAttribute("pageView", pageView);
		// request.setAttribute("record", record);
		return "core/sys/sys_log/sys_log_forQueryPage";
	}

}
