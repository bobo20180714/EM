/**
 * Copyright2015-2019 em All Rights Reserved.
 */
package com.em.core.org.position.service;

import java.util.List;
import java.util.Map;

import com.em.core.org.position.entity.OrgPosition;


/**
 * @ClassName: IOmPositionService
 * @Description: TODO
 * @author: liuyx
 * @date: 2015年9月5日下午3:39:36
 */
public interface IOrgPositionService {

	OrgPosition get(String id);

	void addOrgPosition(OrgPosition orgPosition);

	void updateOrgPosition(OrgPosition orgPosition);

	void delOrgPosition(String id);

	List<OrgPosition> query(Map<String, String> map);

}
