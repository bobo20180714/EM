/**
 * Copyright2015-2019 em All Rights Reserved.
 */
package com.em.core.org.group.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.em.common.zTree.ZTreeNode;
import com.em.core.org.group.entity.OrgGroup;
import com.em.core.org.mappers.OrgGroupMapper;

/**
 * @ClassName: OmGroupServiceImpl.java
 * @Description:
 * @author yuqing
 * @version 2015年9月24日 下午6:16:47
 */
@Service
public class OrgGroupServiceImpl implements IOrgGroupService {

	@Autowired
	private OrgGroupMapper orgGroupMapper;

	@Override
	public OrgGroup get(String id) {
		return orgGroupMapper.selectByPrimaryKey(id);
	}

	/**
	 * 插入 一条记录
	 * 
	 * @param beanMap
	 * @return
	 */
	@Override
	public void addOrgGroup(OrgGroup orgGroup) {
		orgGroupMapper.insertSelective(orgGroup);
	}

	/**
	 * 修改 一条记录
	 * 
	 * @param beanMap
	 * @return
	 */
	@Override
	public void updateOrgGroup(OrgGroup orgGroup) {
		orgGroupMapper.updateByPrimaryKeySelective(orgGroup);
	}

	/**
	 * 删除 一条记录
	 * 
	 * @param
	 */
	@Override
	public int deleteOrgGroup(String id) {
		return orgGroupMapper.deleteByPrimaryKey(id);
	}

	/**
	 * @Title: query
	 * @param paramMap
	 * @return
	 */
	@Override
	public List<OrgGroup> queryOrgGroup(OrgGroup orgGroup) {
		List<OrgGroup> list = orgGroupMapper.query(orgGroup);
		return list;
	}

	/**
	 * @Title: query for update
	 * @author：yuqing
	 * @date：2015年9月30日下午4:51:13
	 * @Description: TODO
	 * @param paramMap
	 * @return
	 */
	@Override
	public List<OrgGroup> queryUpdate(String id) {
		List<OrgGroup> list = orgGroupMapper.queryUpdate(id);
		return list;
	}

	/**
	 * @Title: queryChildrenTreeNodes
	 * @param pid
	 * @return
	 */
	@Override
	public List<ZTreeNode> queryChildrenTreeNodes(String pId) {
		List<ZTreeNode> list = orgGroupMapper.queryChildrenTreeNodes(pId);
		// 设置根节点打开
		if (pId == null) {
			for (ZTreeNode node : list) {
				if ("-1".equals(node.getpId())) {
					node.setOpen(true);
					break;
				}
			}
		}
		return list;
	}

}
