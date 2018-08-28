package com.em.common.page;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.em.common.utils.StringUtils;

public class DataTableView<T> {

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

	// 返回数据 end----------------------

	// 查询参数 start-----------------
	private int start;
	private int length;
	private Search search;
	private List<Order> order = new ArrayList<Order>();
	private List<Columns> columns = new ArrayList<Columns>();

	// 查询参数 end-----------------

	public DataTableView(HttpServletRequest request) {
		this.start = StringUtils.getIntValue(request.getParameter("start"));
		this.length = StringUtils.getIntValue(request.getParameter("length"));
		Search s = new Search();
		s.setValue(request.getParameter("search[value]"));
		s.setRegex(Boolean.getBoolean(request.getParameter("search[regex]")));

		int i = 0;
		while (request.getParameter("columns[" + i + "][data]") != null) {
			Columns c = new Columns();
			c.setData(request.getParameter("columns[" + i + "][data]"));
			c.setName(request.getParameter("columns[" + i + "][name]"));
			c.setSearchable(Boolean.getBoolean(request.getParameter("columns[" + i + "][searchable]")));
			c.setOrderable(Boolean.getBoolean(request.getParameter("columns[" + i + "][orderable]")));
			// search暂时用不到
			columns.add(c);
			i++;

		}

		i = 0;

		while (request.getParameter("order[" + i + "][column]") != null) {
			Order o = new Order();
			o.setColumn(columns.get(StringUtils.getIntValue(request.getParameter("order[" + i + "][column]"))).getData());
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

	public List<Order> getOrder() {
		return order;
	}

	public void setOrder(List<Order> order) {
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

	class Columns {
		private String data;
		private String name;
		private boolean searchable;
		private boolean orderable;
		private Search search;

		public Search getSearch() {
			return search;
		}

		public void setSearch(Search search) {
			this.search = search;
		}

		public String getData() {
			return data;
		}

		public void setData(String data) {
			this.data = data;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public boolean isSearchable() {
			return searchable;
		}

		public void setSearchable(boolean searchable) {
			this.searchable = searchable;
		}

		public boolean isOrderable() {
			return orderable;
		}

		public void setOrderable(boolean orderable) {
			this.orderable = orderable;
		}

	}

	class Order {
		private String column;
		private String dir;

		public String getColumn() {
			return column;
		}

		public void setColumn(String column) {
			this.column = column;
		}

		public String getDir() {
			return dir;
		}

		public void setDir(String dir) {
			this.dir = dir;
		}
	}

	class Search {
		private String value;
		private boolean regex;

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public boolean isRegex() {
			return regex;
		}

		public void setRegex(boolean regex) {
			this.regex = regex;
		}

	}

}
