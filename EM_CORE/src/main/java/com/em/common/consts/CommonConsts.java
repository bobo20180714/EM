/**
 * Copyright2015-2019 em All Rights Reserved.
 */
package com.em.common.consts;

/**
 * @ClassName: CommonConsts
 * @Description: TODO
 * @author: liuyx
 * @date: 2015年9月20日下午1:12:41
 */
public class CommonConsts {

	/**
	 * 数据库操作返回标识 -254：数据重复
	 */
	public static final int DATA_REPEAT_ERR = -254;

	/* 人员权限----------------------↓ */
	/**
	 * employee状态 0：正常在职
	 */
	public static final String EMP_STATUS_NORMAL = "0";

	/**
	 * 操作员状态 0：可用
	 */
	public static final String OPERATOR_STATUS_NORMAL = "0";

	/**
	 * 操作员状态 1：禁用
	 */
	public static final String OPERATOR_STATUS_ABNORMAL = "1";

	/* 人员权限----------------------↑ */

	/**
	 * 通用判断标示 Y: 真
	 */
	public static final String TRUE = "Y";

	/**
	 * 通用判断标示 N: 假
	 */
	public static final String FALSE = "N";

	/**
	 * 空字符串
	 */
	public static final String EMPTY = "";
}
