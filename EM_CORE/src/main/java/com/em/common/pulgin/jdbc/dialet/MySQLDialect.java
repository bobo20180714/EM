package com.em.common.pulgin.jdbc.dialet;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.alibaba.druid.util.StringUtils;
import com.em.common.page.Columns;
import com.em.common.page.OrderBy;

/**
 * @author badqiu
 */
public class MySQLDialect extends Dialect {

	public boolean supportsLimitOffset() {
		return true;
	}

	public boolean supportsLimit() {
		return true;
	}

	public String getLimitString(String sql, int offset, String offsetPlaceholder, int limit, String limitPlaceholder) {
		if (offset > 0) {
			return sql + " limit " + offsetPlaceholder + "," + limitPlaceholder;
		} else {
			return sql + " limit " + limitPlaceholder;
		}
	}

	/**
	 * 
	 * @Title: getLikeOrderLimitString
	 * @author：liuyx
	 * @date：2016年1月27日下午1:45:25
	 * @Description: 包括查询、分页、和排序功能
	 * @param sql
	 * @param offset
	 * @param offsetPlaceholder
	 * @param limit
	 * @param limitPlaceholder
	 * @param orders
	 * @param searchAbleCols
	 * @param likeStr
	 * @return
	 */
	public String getLikeOrderLimitString(String sql, int offset, String offsetPlaceholder, int limit, String limitPlaceholder, List<OrderBy> orders, List<Columns> searchAbleCols, String likeStr) {
		// sql = "select * from (" + sql + ") dialectTemp where 1=1"; // 记录统计
		StringBuilder sb = new StringBuilder(sql);

		boolean hasInvlidSqlStr = false;
		if (!StringUtils.isEmpty(likeStr)) {

			if (likeStr.indexOf('\'') != -1) {
				hasInvlidSqlStr = true;
			}

			StringBuffer temp = new StringBuffer();

			if (!hasInvlidSqlStr) {
				for (Columns col : searchAbleCols) {
					if (!StringUtils.isEmpty(col.getData())) {
						/***
						 * 2018/5/24升级  解决列筛选查询bug
						 */
						String column = col.getName();
						if(StringUtils.isEmpty(column)) {
							column = col.getData();
						}
						temp.append(column + " like '%" + likeStr + "%' or ");
					}

				}
			}

			if (temp.length() != 0) {
				sb.append(" and (");
				sb.append(temp.substring(0, temp.length() - 3));
				sb.append(" )");
			}
		}

		if (CollectionUtils.isNotEmpty(orders)) {
			sb.append(" order by ");
			StringBuffer temp = new StringBuffer();
			for (OrderBy orderBy : orders) {
				temp.append(" " + orderBy.getColumn() + " " + orderBy.getDir() + ",");
			}
			sb.append(temp.substring(0, temp.length() - 1));
		}

		if (offset > 0) {
			return sb.toString() + " limit " + offsetPlaceholder + "," + limitPlaceholder;
		} else {
			return sb.toString() + " limit " + limitPlaceholder;
		}

	}

}
