/**
 * Copyright2015-2019 em All Rights Reserved.
 */
package com.em.core.auth.menu.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.em.common.controller.BaseController;
import com.em.common.retobj.RetObj;
import com.em.common.utils.HttpUtils;
import com.em.common.utils.StringUtils;
import com.em.common.zTree.ZTreeNode;
import com.em.core.auth.menu.entity.AuthMenu;
import com.em.core.auth.menu.service.IAuthMenuService;

/****
 * 
 * @author yangy 2018年5月21日 下午4:14:19 AuthMenuController.java
 * @Description
 */
@Controller
@RequestMapping("/authMenu")
public class AuthMenuController extends BaseController {

	@Autowired
	private IAuthMenuService authMenuService;

	/***
	 * 加载菜单管理的主页面
	 * XXX
	 * @return
	 */
	@RequestMapping("/forMain")
	public String forMain() {
		return "core/auth/auth_menu/auth_menu_forMain";
	}

	/***
	 * XXX
	 * @param pId
	 * @return
	 */
	@RequestMapping("/queryTreeNodes")
	@ResponseBody
	public List<ZTreeNode> queryTreeNodes(String pId) {
		return authMenuService.queryChildrenTreeNodes(pId);
	}

	/***
	 * XXX
	 * @param request
	 * @return
	 */
	@RequestMapping("/query")
	@ResponseBody
	public List<AuthMenu> query(HttpServletRequest request) {
		Map<String, Object> record = HttpUtils.getRequestMap(request);
		return authMenuService.queryAuthMenuByMap(record);
	}

	/****
	 * XXX 菜单管理中菜单编辑页面
	 * @param request
	 * @param id
	 * @param pId
	 * @param level
	 * @param appId
	 * @return
	 */
	@RequestMapping("/forUpdate")
	public ModelAndView forUpdate(HttpServletRequest request, String id, String pId, String level, String appId) {
		ModelAndView mv = new ModelAndView();
		AuthMenu menu = new AuthMenu();
		if (!StringUtils.isEmpty(id)) {
			// 修改数据
			menu = authMenuService.getAuthMenu(id);

		}
		if (!StringUtils.isEmpty(pId)) {
			menu.setParentId(pId);
			String parentName = authMenuService.getParentName(pId);
			menu.setParentMenuName(parentName);
		}
		if (!StringUtils.isEmpty(level)) {
			menu.setMenuLevel(Integer.valueOf(level));
		}
		if (!StringUtils.isEmpty(appId)) {
			menu.setAppId(appId);
		}
		mv.setViewName("core/auth/auth_menu/auth_menu_forUpdate");
		mv.addObject("record", menu);
		return mv;
	}

	/***
	 * XXX
	 * 新增或者更新菜单
	 * @param menu
	 * @return
	 */
	@RequestMapping("/update")
	@ResponseBody
	public RetObj update(AuthMenu menu) {
		List<AuthMenu> list = null;
		try {
			if (StringUtils.isEmpty(menu.getMenuId())) {
				// 新增
				list = authMenuService.queryAuthMenu(menu);
				if (!CollectionUtils.isEmpty(list)) {
					return new RetObj(false, "菜单代码已经存在！");
				}
				menu.setMenuId(StringUtils.uniqueKey36());
				authMenuService.addAuthMenu(menu);
			} else {
				// 修改
				menu.setNotMenuId(menu.getMenuId());
				list = authMenuService.queryAuthMenu(menu);
				if (!CollectionUtils.isEmpty(list)) {
					return new RetObj(false, "菜单代码已经存在！");
				}
				authMenuService.updateAuthMenu(menu);
			}
			return new RetObj(true, "操作成功", menu.getMenuId());
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
			return new RetObj(false, "操作失败");
		}
	}

	/****
	 * 删除菜单 XXX
	 * @param request
	 * @param id
	 * @param response
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public void delete(HttpServletRequest request, String id, HttpServletResponse response) {
		int status = 0;
		try {
			if (!StringUtils.isEmpty(id)) {
				status = authMenuService.delAuthMenu(id);
			}
			StringBuffer jsonStr = new StringBuffer("{\"status\":\"" + status + "\"}");
			// System.out.println("************************************************************"+jsonStr);
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().write(jsonStr.toString());
			response.getWriter().flush();
			response.getWriter().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
