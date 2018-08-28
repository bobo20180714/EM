/**
 * Copyright2015-2019 em All Rights Reserved.
 */
package com.em.core.org.position.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.em.core.org.mappers.OrgPositionMapper;
import com.em.core.org.position.entity.OrgPosition;

/**
 * @ClassName: OmPositionServiceImpl
 * @Description: TODO
 * @author: liuyx
 * @date: 2015年9月5日下午4:48:41
 */
@Service
public class OrgPositionServiceImpl implements IOrgPositionService {

	@Autowired OrgPositionMapper orgPositionMapper;	

	@Override
	public OrgPosition get(String id) {
		return orgPositionMapper.selectByPrimaryKey(id);
	}

	/**
	 * 插入 一条记录
	 * 
	 * @param beanMap
	 * @return
	 */
	@Override
	public void addOrgPosition(OrgPosition orgPosition) {
		orgPositionMapper.insert(orgPosition);
	}

	/**
	 * 修改 一条记录
	 * 
	 * @param beanMap
	 * @return
	 */
	@Override
	public void updateOrgPosition(OrgPosition orgPosition) {
		orgPositionMapper.updateByPrimaryKeySelective(orgPosition);
	}

	/**
	 * 删除 一条记录
	 * 
	 * @param
	 */
	@Override
	public void delOrgPosition(String id) {
		orgPositionMapper.deleteByPrimaryKey(id);
	}

	/**
	 * @Title: query
	 * @author：liuyx
	 * @date：2015年9月5日下午3:51:13
	 * @Description: TODO
	 * @param paramMap
	 * @return
	 */
	@Override
	public List<OrgPosition> query(Map<String, String> map) {
		List<OrgPosition> list = orgPositionMapper.query(map);
		return list;
	}
}
