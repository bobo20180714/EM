package com.em.common.page;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.em.common.utils.StringUtils;

public class PageView<T> {

	/**
	 * 【共有数据】：查询次数
	 */
	private int draw;

	// 返回数据 start------------------

	/**
	 * 【返回数据】：总记录数
	 */
	private int recordsTotal;

	/**
	 * 【返回数据】：过滤后的总记录数
	 */
	private int recordsFiltered;

	/**
	 * 【返回数据】：具体的数据对象数组
	 */
	private List<T> data;

	/**
	 * 是否在分页插件中自动统计数量
	 */
	private boolean isAutoCount = true;

	// 返回数据 end----------------------

	// 查询参数 start-----------------
	private int start;
	private int length;
	private Search search;
	private List<OrderBy> order = new ArrayList<OrderBy>();
	private List<Columns> columns = new ArrayList<Columns>();

	// 查询参数 end-----------------

	public List<Columns> getSearchAbleCols() {
		List<Columns> orderAbleCols = new ArrayList<Columns>();
		for (Columns c : columns) {
			if (c.isSearchable()) {
				orderAbleCols.add(c);
			}
		}
		return orderAbleCols;
	}

	// 兼容旧写法 start-----------
	public void setRecords(List<T> data) {
		this.data = data;
	}

	public void setRowCountAndPreventAutoCount(int count) {
		isAutoCount = false;
		setRowCount(count);
	}

	public void setRowCount(int count) {
		// 暂时做同样处理
		recordsTotal = count;
		recordsFiltered = count;

		// 分离datatables写法
		this.pageCount = this.recordsTotal % this.length == 0 ? this.recordsTotal / this.length : this.recordsTotal / this.length + 1;
		this.pageindex = WebTool.getPageIndex(this.pagecode, this.pageNow, this.pageCount);
	}

	// 兼容旧写法end-----------

	public PageView() {

	}

	public PageView(HttpServletRequest request) {
		this.start = StringUtils.getIntValue(request.getParameter("start"));
		this.length = StringUtils.getIntValue(request.getParameter("length"));
		Search s = new Search();
		s.setValue(request.getParameter("search[value]"));
		s.setRegex(Boolean.getBoolean(request.getParameter("search[regex]")));

		this.search = s;

		int i = 0;
		while (request.getParameter("columns[" + i + "][data]") != null) {
			Columns c = new Columns();
			c.setData(request.getParameter("columns[" + i + "][data]"));
			c.setName(request.getParameter("columns[" + i + "][name]"));
			c.setSearchable(StringUtils.getBoolean(request.getParameter("columns[" + i + "][searchable]")));
			c.setOrderable(StringUtils.getBoolean(request.getParameter("columns[" + i + "][orderable]")));
			// search暂时用不到
			columns.add(c);
			i++;

		}

		i = 0;

		while (request.getParameter("order[" + i + "][column]") != null) {
			/***
			 * 2018/05/04升级，增加使用对象时，datatable可以进行排序功能
			 */
			String column = null;
			OrderBy o = new OrderBy();
			String orderColumnSNum = request.getParameter("order[" + i + "][column]");
			int orderColumn = StringUtils.getIntValue(orderColumnSNum);
			column = columns.get(orderColumn).getName();
			if(StringUtils.isEmpty(column)) {
				column = columns.get(orderColumn).getData();
			}
			o.setColumn(column);
			o.setDir(request.getParameter("order[" + i + "][dir]"));
			order.add(o);
			i++;
		}

	}

	/**
	 * 
	 * @param recordsTotal
	 *            总记录数
	 * @param recordsFiltered
	 *            过滤后的总记录数
	 * @param data
	 *            当页的数据
	 */
	public void pushData(int recordsTotal, int recordsFiltered, List<T> data) {
		this.recordsTotal = recordsTotal;
		this.recordsFiltered = recordsFiltered;
		this.data = data;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public Search getSearch() {
		return search;
	}

	public void setSearch(Search search) {
		this.search = search;
	}

	public List<OrderBy> getOrder() {
		return order;
	}

	public void setOrder(List<OrderBy> order) {
		this.order = order;
	}

	public List<Columns> getColumns() {
		return columns;
	}

	public void setColumns(List<Columns> columns) {
		this.columns = columns;
	}

	public int getDraw() {
		return draw;
	}

	public void setDraw(int draw) {
		this.draw = draw;
	}

	public int getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(int recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public int getRecordsFiltered() {
		return recordsFiltered;
	}

	public void setRecordsFiltered(int recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	/**
	 * 分离datatables写法
	 */
	public PageView(HttpServletRequest request, String pageNow) {
		this.pageNow = StringUtils.isEmpty(pageNow) ? 1 : Integer.parseInt(pageNow);
		this.length = 10;

		this.start = (this.pageNow - 1) * this.length;

		Search s = new Search();
		s.setValue(request.getParameter("search[value]"));
		s.setRegex(Boolean.getBoolean(request.getParameter("search[regex]")));
		this.search = s;
	}

	private int pageCount;
	private int pageNow;
	private int pagecode = 5;
	private PageIndex pageindex;

	public void setPageCount(int pageCount) {
		this.pageCount = this.recordsTotal % this.length == 0 ? this.recordsTotal / this.length : this.recordsTotal / this.length + 1;
	}

	public int getPageCount() {
		return this.pageCount;
	}

	public void setPageNow(int pageNow) {
		this.pageNow = pageNow;
	}

	public int getPageNow() {
		return pageNow;
	}

	public int getPagecode() {
		return pagecode;
	}

	public void setPagecode(int pagecode) {
		this.pagecode = pagecode;
	}

	public void setPageindex(PageIndex pageindex) {
		this.pageindex = pageindex;
	}

	public PageIndex getPageindex() {
		return this.pageindex;
	}

	/**
	 * 分离datatables写法 END
	 */

	public boolean isAutoCount() {
		return isAutoCount;
	}

	public void setAutoCount(boolean isAutoCount) {
		this.isAutoCount = isAutoCount;
	}

	public String getLikeStr() {
		StringBuffer temp = new StringBuffer();
		List<Columns> searchAbleCols = this.getSearchAbleCols();
		Search search = this.getSearch();
		if (search == null) {
			return null;
		}
		String likeStr = search.getValue();
		if (StringUtils.isEmpty(likeStr)) {
			return null;
		}
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
		return temp.substring(0, temp.length() - 3);
	}

	public Map getLikeQueryMap() {
		Map countQMap = new HashMap();
		String likeStr = this.getLikeStr();
		countQMap.put("LIKE_STR", likeStr);
		return countQMap;
	}

	public void addLikeParam(Map paramMap) {
		String likeStr = this.getLikeStr();
		paramMap.put("LIKE_STR", likeStr);
	}

	public Map getLikeParamJoinedMap(Map paramMap) {
		this.addLikeParam(paramMap);
		return paramMap;
	}
}
