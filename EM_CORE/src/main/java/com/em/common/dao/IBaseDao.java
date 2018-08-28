/**
 * IBaseDao.java
 * @author：caojian @date：2015年1月22日下午10:43:40
 * Copyright2011-2015 em All Rights Reserved.
 */
package com.em.common.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.em.common.page.PageView;

/**
 * @Description：
 * @author：caojian @date：2015年1月22日下午10:43:40
 */
public interface IBaseDao {
	/**
	 * 线程级变量，是否只检查本地SQL
	 */
	public static final ThreadLocal<Boolean> OnlyCallLocalFlg = new ThreadLocal<Boolean>() {
		protected Boolean initialValue() {
			return false;
		}
	};

	public static final int SQL_NOT_EXIST = -1;
	public static final int SQL_EXISTS_IN_LOCAL = 1;
	/**
	 * 表示本地注册表中已注册改远程Dao
	 */
	public static final int SQL_EXISTS_IN_REMOTE = 2;
	public static final int SQL_FORBIDDEN_BY_REMOTE = -255;

	public Object insert(String sqlId, Object parameter);

	public int insert_(String sqlId, Object parameter);

	// 编辑对象
	public Object update(String sqlId, Object parameter);

	public int update_(String sqlId, Object parameter);

	// 删除对象
	public int delete(String sqlId, Object parameter);

	// 根据主键查询单个对象 返回对象
	public Object get(String sqlId, Object parameter);

	// 根据不同条件 查询 返回集合
	@SuppressWarnings("rawtypes")
	public List query(String sqlId, Object parameter);

	// 根据ibatis的sql语句查询
	@SuppressWarnings("rawtypes")
	public List queryAll(String sqlId);

	// 批量新增
	public <T extends Object> int batchInsert(String sqlId, List<T> list);

	// 批量修改
	public int batchUpdate(String sqlId, List<Object> list);

	/**
	 * 
	 * 方法名: queryPage 描述: 返回分页的数据 参数： @param sqlId： 参数： @param pageView 分页 参数： @param
	 * t 实体类 参数： @return 返回分页后的list 参数： @throws SQLException 返回类型： List<Object>
	 * 创建人：gepg 创建时间：2015年1月27日 上午10:39:33 修改人：gepg 修改时间：2015年1月27日 上午10:39:33
	 * 修改备注： 其他：
	 */
	// 根据ibatis的sql语句查询
	@SuppressWarnings("rawtypes")
	public List queryPage(String sqlId, PageView pageView, Object t);
}
