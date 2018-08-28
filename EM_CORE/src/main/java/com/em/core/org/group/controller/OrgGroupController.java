/**
 * Copyright2015-2019 em All Rights Reserved.
 */
package com.em.core.org.group.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.em.common.controller.BaseController;
import com.em.common.retobj.RetObj;
import com.em.common.utils.DateUtils;
import com.em.common.utils.StringUtils;
import com.em.common.zTree.ZTreeNode;
import com.em.core.org.group.entity.OrgGroup;
import com.em.core.org.group.service.IOrgGroupService;
import com.em.core.org.organization.entity.OrgOrganization;
import com.em.core.org.organization.service.IOrgOrganizationService;

/**
 * @ClassName: OcGroupController
 * @Description: 工作组
 * @author: 于庆
 * @date: 2015年9月24日下午6:07:10
 */
@Controller
@RequestMapping("/orgGroup")
public class OrgGroupController extends BaseController {

	@Autowired
	private IOrgGroupService orgGroupService;
	@Autowired
	private IOrgOrganizationService orgOrganizationService;

	@RequestMapping("/forMain")
	public String forMain() {
		return "core/org/org_group/org_group_forMain";
	}

	@RequestMapping("/queryTreeNodes")
	@ResponseBody
	public List<ZTreeNode> queryTreeNodes(String pId) {
		return orgGroupService.queryChildrenTreeNodes(pId);
	}

	@RequestMapping("/forUpdate")
	public ModelAndView forUpdate(HttpServletRequest request, String id, String pId, String level, String pname) {
		ModelAndView mv = new ModelAndView();
		OrgGroup og = null;
		if (!StringUtils.isEmpty(id)) {
			// 修改数据
			og = orgGroupService.get(id);
		}
		List<OrgOrganization> orgList = orgOrganizationService.query(null);
		mv.addObject("orgList", orgList);
		if (!StringUtils.isEmpty(pId)) {
			og.setParentGroupId(pId);
		}
		if (!StringUtils.isEmpty(pname)) {
			og.setParentGroupName(pname);
		}

		if (!StringUtils.isEmpty(level)) {
			og.setGroupLevel(Integer.valueOf(level));
		}
		mv.addObject("record", og);
		mv.setViewName("core/org/org_group/org_group_forUpdate");
		return mv;
	}

	/**
	 * 
	 * @Title: update
	 * @author: yuqing
	 * @date：2015年9月25日下午3:15:30
	 * @Description: 新增/修改 数据
	 * @param request
	 * @return 操作结果
	 */
	@RequestMapping("/update")
	@ResponseBody
	public RetObj update(HttpServletRequest request, OrgGroup og) {

		String now = DateUtils.getCurrentDate();
		try {
			if (StringUtils.isEmpty(og.getGroupId())) {
				og.setGroupId(StringUtils.uniqueKey36());
				og.setCreateTime(now);
				orgGroupService.addOrgGroup(og);
			} else {
				// 修改
				og.setLastupDate(now);
				og.setUpdator(getEmpIdFromRequest(request));
				orgGroupService.updateOrgGroup(og);
			}
			return new RetObj(true, request, og.getGroupId());
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
			return new RetObj(false, request);
		}
	}

	/**
	 * 
	 * @Title: delete
	 * @author: yuqing
	 * @date：2015年9月28日上午午10:15:30
	 * @Description: 删除工作组
	 * @param request
	 * @return 操作结果
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public void delete(HttpServletRequest request, String id, HttpServletResponse response) {
		try {
			int status = 0;
			if (!StringUtils.isEmpty(id)) {
				status = orgGroupService.deleteOrgGroup(id);
			}
			StringBuffer jsonStr = new StringBuffer("{\"status\":\"" + status + "\"}");
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().write(jsonStr.toString());
			response.getWriter().flush();
			response.getWriter().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/query")
	@ResponseBody
	public RetObj query(OrgGroup og, ModelMap model) {
		model.addAttribute("record", og);
		List<OrgGroup> list;
		if (StringUtils.isEmpty(og.getGroupId())) {
			list = orgGroupService.queryOrgGroup(og);
		} else {
			list = orgGroupService.queryUpdate(og.getGroupId());
		}
		int count = list.size();
		if (count > 0) {
			return new RetObj(true, "机构代码已经存在！");
		} else {
			return new RetObj(false, "");
		}
	}
}
