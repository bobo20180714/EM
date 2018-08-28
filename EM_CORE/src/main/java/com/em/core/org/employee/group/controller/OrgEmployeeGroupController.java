/**
 * Copyright2015-2019 em All Rights Reserved.
 */
package com.em.core.org.employee.group.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.em.common.controller.BaseController;
import com.em.common.retobj.RetObj;
import com.em.common.utils.StringUtils;
import com.em.core.org.employee.group.entity.OrgEmployeeGroup;
import com.em.core.org.employee.group.service.IOrgEmployeeGroupService;
import com.em.core.org.employee.service.IOrgEmployeeService;

/**
 * @ClassName: OmEmpGroupController
 * @Description: TODO
 * @author: liuyx
 * @date: 2015年9月27日上午10:53:55
 */
@Controller
@RequestMapping("/orgEmpGroup")
public class OrgEmployeeGroupController extends BaseController {

	@Autowired
	private IOrgEmployeeGroupService orgEmpGroupService;
	@Autowired
	private IOrgEmployeeService omEmployeeService;

	@RequestMapping("/queryEmpGroup")
	@ResponseBody
	public List<OrgEmployeeGroup> queryEmpGroup(OrgEmployeeGroup orgEmployeeGroup) {
		List<OrgEmployeeGroup> list = orgEmpGroupService.queryOrgEmployeeGroup(orgEmployeeGroup);
		return list;
	}

	@RequestMapping("/batchInsertByJson")
	@ResponseBody
	public RetObj batchInsertByJson(HttpServletRequest request, String dataJsonStr, String groupId, String notInEmpIds) {
		List<Map> dataList = JSON.parseArray(dataJsonStr, Map.class);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("GROUP_ID", groupId);
		paramMap.put("notInEmpIds", notInEmpIds);
		try {
			orgEmpGroupService.batchInsert(dataList, paramMap);
			return new RetObj(true, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
			return new RetObj(false, request);
		}
	}

	@RequestMapping("/delete")
	@ResponseBody
	public RetObj delete(HttpServletRequest request, OrgEmployeeGroup orgEmployeeGroup) {
		try {
			orgEmpGroupService.delOrgEmployeeGroup(orgEmployeeGroup);
			return new RetObj(true, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
		}
		return new RetObj(false, request);
	}
}
