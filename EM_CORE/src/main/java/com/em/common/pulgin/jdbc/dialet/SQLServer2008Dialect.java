package com.em.common.pulgin.jdbc.dialet;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.alibaba.druid.util.StringUtils;
import com.em.common.page.Columns;
import com.em.common.page.OrderBy;

/**
 * Modified version based on the work found at
 * http://opensource.atlassian.com/projects/hibernate/browse/HHH-2655
 * 
 * @author Yoryos Valotassios
 * @since 10.1.1
 */
public class SQLServer2008Dialect extends Dialect {

	@Override
	public boolean supportsLimit() {
		return true;
	}

	@Override
	public boolean supportsLimitOffset() {
		return true;
	}

	@Override
	public String getLimitString(String querySqlString, int offset, String offsetPlaceholder, int limit, String limitPlaceholder) {
		StringBuffer pagingBuilder = new StringBuffer();
		String orderby = getOrderByPart(querySqlString);
		String distinctStr = "";

		String loweredString = querySqlString.toLowerCase();
		String sqlPartString = querySqlString;
		if (loweredString.trim().startsWith("select")) {
			int index = 6;
			if (loweredString.startsWith("select distinct")) {
				distinctStr = "DISTINCT ";
				index = 15;
			}
			sqlPartString = sqlPartString.substring(index);
			// 没有top 100 percent进不了这个方法，加上查询报错
			if (sqlPartString.trim().startsWith("top 100 percent")) {
				index = 15;
				sqlPartString = sqlPartString.trim().substring(index);
			}

		}
		pagingBuilder.append(sqlPartString);

		// if no ORDER BY is specified use fake ORDER BY field to avoid errors
		if (orderby == null || orderby.length() == 0) {
			orderby = "ORDER BY cjsj";
		}

		StringBuffer result = new StringBuffer();
		result.append("WITH query AS (SELECT ").append(distinctStr).append("TOP 100 PERCENT ").append(" ROW_NUMBER() OVER (").append(orderby).append(") as __row_number__, ").append(pagingBuilder)
				.append(") SELECT * FROM query WHERE __row_number__ BETWEEN ").append(offset + 1).append(" AND ").append(offset + limit).append(" ORDER BY __row_number__");

		return result.toString();
	}

	static String getOrderByPart(String sql) {
		String loweredString = sql.toLowerCase();
		int orderByIndex = loweredString.indexOf("order by");
		int lastIndex = loweredString.lastIndexOf("order by");
		// orderByIndex == lastIndex相同的时候只有一个，否则有多个
		if (orderByIndex != -1 && orderByIndex == lastIndex) {
			// if we find a new "order by" then we need to ignore
			// the previous one since it was probably used for a subquery
			return sql.substring(orderByIndex);
		} else {
			return "";
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
	public String getLikeOrderLimitString(String querySqlString, int offset, String offsetPlaceholder, int limit, String limitPlaceholder, List<OrderBy> orders, List<Columns> searchAbleCols,
			String likeStr) {
		StringBuffer pagingBuilder = new StringBuffer();
		// String orderby = getOrderByPart(querySqlString);
		String orderby = getOrderByStr(orders);
		String distinctStr = "";

		String loweredString = querySqlString.toLowerCase();
		String sqlPartString = querySqlString;
		if (loweredString.trim().startsWith("select")) {
			int index = 6;
			if (loweredString.startsWith("select distinct")) {
				distinctStr = "DISTINCT ";
				index = 15;
			}
			sqlPartString = sqlPartString.substring(index);
			// 没有top 100 percent进不了这个方法，加上查询报错
			if (sqlPartString.trim().startsWith("top 100 percent")) {
				index = 15;
				sqlPartString = sqlPartString.trim().substring(index);
			}

		}
		pagingBuilder.append(sqlPartString + getLikeStr(searchAbleCols, likeStr));

		// if no ORDER BY is specified use fake ORDER BY field to avoid errors
		if (orderby == null || orderby.length() == 0) {
			orderby = "ORDER BY cjsj";
		}

		StringBuffer result = new StringBuffer();
		result.append("WITH query AS (SELECT ").append(distinctStr).append("TOP 100 PERCENT ").append(" ROW_NUMBER() OVER (").append(orderby).append(") as __row_number__, ").append(pagingBuilder)
				.append(") SELECT * FROM query WHERE __row_number__ BETWEEN ").append(offset + 1).append(" AND ").append(offset + limit).append(" ORDER BY __row_number__");

		return result.toString();

	}

	private String getLikeStr(List<Columns> searchAbleCols, String likeStr) {
		if (!StringUtils.isEmpty(likeStr)) {
			StringBuffer temp = new StringBuffer(" and (");
			for (Columns col : searchAbleCols) {
				if (!StringUtils.isEmpty(col.getData())) {
					temp.append(col.getData() + " like '%" + likeStr + "%' or ");
				}

			}
			if (temp.length() > 3) {
				// temp.append(temp.substring(0, temp.length() - 3));
				return temp.substring(0, temp.length() - 3) + ")";
			}
		}
		return "";
	}

	private String getOrderByStr(List<OrderBy> orders) {
		if (CollectionUtils.isNotEmpty(orders)) {
			StringBuffer temp = new StringBuffer();
			for (OrderBy orderBy : orders) {
				temp.append(" " + orderBy.getColumn() + " " + orderBy.getDir() + ",");
			}
			return "order by" + temp.substring(0, temp.length() - 1);
		}
		return "";
	}
}
