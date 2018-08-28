/**
 * Copyright2015-2019 em All Rights Reserved.
 */
package com.em.core.org.group.entity;

import com.em.common.zTree.ZTreeNode;

/**
 * @ClassName: OmGroupTreeNode.java
 * @Description:
 * @author yuqing
 * @version 2015年9月25日 上午9:21:30
 */

public class OrgGroupTreeNode extends ZTreeNode {

	// private String groupAction;//菜单链接
	// private String groupParam;//菜单参数
	// private String groupLabel;//菜单对用户的显示名
	private boolean isLeaf;// 是否叶子菜单
	// private String groupCss;

	public boolean isLeaf() {
		return isLeaf;
	}

	public void setLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}

}
