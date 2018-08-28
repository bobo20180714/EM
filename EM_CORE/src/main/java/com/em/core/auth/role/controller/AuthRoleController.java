
package com.em.core.auth.role.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.em.common.controller.BaseController;
import com.em.common.page.PageView;
import com.em.common.retobj.RetObj;
import com.em.common.utils.DateUtils;
import com.em.common.utils.HttpUtils;
import com.em.common.utils.StringUtils;
import com.em.core.auth.application.entity.AuthApplication;
import com.em.core.auth.application.service.IAuthApplicationService;
import com.em.core.auth.role.entity.AuthRole;
import com.em.core.auth.role.service.IAuthRoleService;

/****
 * 角色管理
 * 
 * @author yangy 2018年5月16日 下午10:54:15 AuthRoleController.java
 * @Description
 */
@Controller
@RequestMapping("/authRole")
public class AuthRoleController extends BaseController {

	@Autowired(required = false)
	private IAuthRoleService authRoleService;

	@Autowired
	private IAuthApplicationService authApplicationService;

	/***
	 * 获取列表数据，ajax请求，返回retobj
	 * XXX
	 * @param request
	 * @return
	 */
	@RequestMapping("/getPageData")
	@ResponseBody
	public PageView<AuthRole> getPageData(HttpServletRequest request) {
		PageView<AuthRole> pageView = new PageView<AuthRole>(request);
		Map<String, Object> record = HttpUtils.getRequestMap(request);
		try {
			pageView = authRoleService.queryPage(pageView, record);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return pageView;
	}

	/***
	 * XXX
	 * @param request
	 * @return
	 */
	@RequestMapping("/forQueryPage")
	public String forQueryPage(HttpServletRequest request) {
		return "core/auth/auth_role/auth_role_forQueryPage";
	}

	/***
	 * 编辑页面
	 * XXX
	 * @param request
	 * @param id
	 * @param pageNow
	 * @return
	 */
	@RequestMapping("/forUpdate")
	public ModelAndView forUpdate(String id) {
		ModelAndView mv = new ModelAndView();
		AuthRole authRole = null;
		if (!StringUtils.isEmpty(id)) {
			authRole = authRoleService.getAuthRole(id);
		}
		mv.setViewName("core/auth/auth_role/auth_role_forUpdate");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("appId", "root");
		List<AuthApplication> appList = authApplicationService.queryAuthApplicationByMap(paramMap);
		mv.addObject("appList", appList);
		mv.addObject("record", authRole);
		return mv;
	}

	/****
	 * 编辑
	 * XXX
	 * @param request
	 * @return
	 */
	@RequestMapping("/update")
	@ResponseBody
	public RetObj update(AuthRole role) {
		String now = DateUtils.getCurrentDate();
		try {
			if (StringUtils.isEmpty(role.getRoleId())) {
				// 新增
				role.setRoleId(StringUtils.uniqueKey36());
				role.setCreateTime(now);
				authRoleService.addAuthRole(role);
			} else {
				// 修改
				authRoleService.updateAuthRole(role);
			}
			return new RetObj(true, "操作成功", role.getRoleId());
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
			return new RetObj(false, "操作失败");
		}

	}

	/***
	 * 批量删除
	 * XXX
	 * @param request
	 * @param ids
	 * @return
	 */
	@RequestMapping("/batchDelete")
	@ResponseBody
	public RetObj batchDelete(HttpServletRequest request, String ids) {

		try {
			authRoleService.batchDelete(StringUtils.getStrJoinQuotes(ids));
			return new RetObj(true, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
			return new RetObj(false, request);
		}
	}
}
