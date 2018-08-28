/**
 * Copyright2015-2019 em All Rights Reserved.
 */
package com.em.core.auth.home.module.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;

import com.em.common.page.PageView;
import com.em.common.zTree.ZTreeNode;
import com.em.core.auth.home.module.entity.AuthHomeModule;
import com.em.core.auth.mappers.AuthHomeModuleMapper;

/**
 * @ClassName: AcHomeModuleServiceImpl
 * @Description: TODO
 * @author: liuyx
 * @date: 2015年9月5日下午4:48:41
 */
@Service
public class AuthHomeModuleServiceImpl implements IAuthHomeModuleService {

	@Autowired
	private AuthHomeModuleMapper AuthHomeModuleMapper;

	@Autowired
	private DataSourceTransactionManager transactionManager;

	//XXX
	@Override
	public AuthHomeModule getAuthHomeModule(String homeModuleId) {
		AuthHomeModule authHomeModule = AuthHomeModuleMapper.selectByPrimaryKey(homeModuleId);
		return authHomeModule;
	}

	/**
	 * 查询 一页记录
	 * XXX
	 * @param rowSelection
	 * @param map
	 * @return
	 */
	@Override
	public PageView<AuthHomeModule> queryPage(PageView<AuthHomeModule> pageView, Map<String, Object> paramMap) {
		Map<String, Object> queryPageMap = new HashMap<String, Object>();
		int count = AuthHomeModuleMapper.count(pageView.getLikeParamJoinedMap(paramMap));
		pageView.setRowCountAndPreventAutoCount(count);
		queryPageMap.put("paging", pageView);
		queryPageMap.put("t", paramMap);
		List<AuthHomeModule> list = AuthHomeModuleMapper.queryPage(pageView, queryPageMap);
		pageView.setRecords(list);
		return pageView;
	}

	//XXX :报错,缺少字段，已解决！
	@Override
	public void addAuthHomeModule(AuthHomeModule homeModule) {
		AuthHomeModuleMapper.insert(homeModule);
	}

	@Override
	public void updateAuthHomeModule(AuthHomeModule homeModule) {
		AuthHomeModuleMapper.updateByPrimaryKeySelective(homeModule);
	}
//XXX
	@Override
	public int delAuthHomeModule(String homeModuleId) {
		return AuthHomeModuleMapper.deleteByPrimaryKey(homeModuleId);
	}
//FIXME: 删除后无法更新数据
	@Override
	public int batchDelete(String ids) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("HOME_MODULE_IDS", ids);
		int ret = AuthHomeModuleMapper.batchDelete(paramMap);
		return ret;
	}
//XXX 没用到
	@Override
	public List<AuthHomeModule> queryAuthHomeModule(AuthHomeModule homeModule) {
		List<AuthHomeModule> list = AuthHomeModuleMapper.query(homeModule);
		return list;
	}
	//XXX 没用到
	@Override
	public List<AuthHomeModule> queryAuthHomeModuleByMap(Map paramMap) {
		List<AuthHomeModule> list = AuthHomeModuleMapper.queryByMap(paramMap);
		return list;
	}

	/**
	 * //XXX 没用到
	 * @Title: queryChildrenTreeNodes
	 * @author：liuyx
	 * @date：2015年9月7日下午4:56:36
	 * @Description: 构建 机构 岗位 人员树
	 * @param paramMap
	 * @return
	 */
	public List<ZTreeNode> queryNodesForRole(Map<String, Object> paramMap) {
		List<ZTreeNode> list = AuthHomeModuleMapper.queryNodesForRole(paramMap);
		return list;
	}

	/*****
	 * //XXX 没用到
	 * 批量插入home module
	 */
	@Override
	public int batchInsertRoleHomeModule(List<Map> list, Map deleteParam) {
		AuthHomeModuleMapper.deleteRoleHomeModule(deleteParam);
		if (CollectionUtils.isEmpty(list)) {
			return 0;
		}
		return AuthHomeModuleMapper.batchInsertRoleHomeModule(list);
	}

	/**
	 * //XXX 没用到
	 * @Title: queryOperHomeModule
	 * @author：liuyx
	 * @date：2015年12月3日15:09:20
	 * @Description: TODO
	 * @param paramMap
	 * @return
	 */
	@Override
	public List<Map> queryOperHomeModule(Map paramMap) {
		List list = AuthHomeModuleMapper.queryOperHomeModule(paramMap);
		return list;
	}

	/**
	 * @Title: queryOperRoleHomeModule
	 * @author：liuyx
	 * @date：2015年12月3日15:09:20
	 * @Description: TODO
	 * @param paramMap
	 * @return
	 */
	@Override
	public List<Map> queryOperRoleHomeModule(Map paramMap) {
		List list = AuthHomeModuleMapper.queryOperRoleHomeModule(paramMap);
		return list;
	}

	/**
	 * @Title: batchInsert
	 * @author：liuyx
	 * @date：2015年12月3日13:58:30
	 * @Description: 批量插入
	 * @param list
	 * @return
	 */
	@Override
	public int batchInsertOperHomeModule(List<Map> list, Map deleteParam) {
		AuthHomeModuleMapper.deleteRoleHomeModule(deleteParam);
		if (CollectionUtils.isEmpty(list)) {
			return 0;
		}
		return AuthHomeModuleMapper.batchInsertOperHomeModule(list, deleteParam);
	}

}
