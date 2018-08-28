package com.em.common.pulgin.jdbc.dialet;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.alibaba.druid.util.StringUtils;
import com.em.common.page.Columns;
import com.em.common.page.OrderBy;

/**
 * @author badqiu
 */
public class OracleDialect extends Dialect {

	public boolean supportsLimit() {
		return true;
	}

	public boolean supportsLimitOffset() {
		return true;
	}

	public String getLimitString(String sql, int offset, String offsetPlaceholder, int limit, String limitPlaceholder) {
		sql = sql.trim();
		boolean isForUpdate = false;
		if (sql.toLowerCase().endsWith(" for update")) {
			sql = sql.substring(0, sql.length() - 11);
			isForUpdate = true;
		}

		StringBuffer pagingSelect = new StringBuffer(sql.length() + 100);
		if (offset > 0) {
			pagingSelect.append("select * from ( select row_.*, rownum rownum_ from ( ");
		} else {
			pagingSelect.append("select * from ( ");
		}
		pagingSelect.append(sql);
		if (offset > 0) {
			// int end = offset+limit;
			String endString = offsetPlaceholder + "+" + limitPlaceholder;
			pagingSelect.append(" ) row_ ) where rownum_ <= " + endString + " and rownum_ > " + offsetPlaceholder);
		} else {
			pagingSelect.append(" ) where rownum <= " + limitPlaceholder);
		}

		if (isForUpdate) {
			pagingSelect.append(" for update");
		}

		return pagingSelect.toString();
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
		sql = "select * from (" + sql + ") dialectTemp where 1=1"; // 记录统计

		boolean isForUpdate = false;
		if (sql.toLowerCase().endsWith(" for update")) {
			sql = sql.substring(0, sql.length() - 11);
			isForUpdate = true;
		}

		StringBuilder sb = new StringBuilder();

		if (offset > 0) {
			sb.append("select * from ( select row_.*, rownum rownum_ from ( ");
		} else {
			sb.append("select * from ( ");
		}

		sb.append(sql);

		if (!StringUtils.isEmpty(likeStr)) {
			StringBuffer temp = new StringBuffer();
			for (Columns col : searchAbleCols) {
				if (!StringUtils.isEmpty(col.getData())) {
					temp.append(col.getData() + " like '%" + likeStr + "%' or ");
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
			// int end = offset+limit;
			String endString = offsetPlaceholder + "+" + limitPlaceholder;
			sb.append(" ) row_ ) where rownum_ <= " + endString + " and rownum_ > " + offsetPlaceholder);
		} else {
			sb.append(" ) where rownum <= " + limitPlaceholder);
		}

		if (isForUpdate) {
			sb.append(" for update");
		}

		return sb.toString();

	}

}
