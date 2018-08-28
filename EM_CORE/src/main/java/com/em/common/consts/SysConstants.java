package com.em.common.consts;

import java.io.File;
import java.math.BigDecimal;

public interface SysConstants {

	public static final String SYS_CODE = "EFP";

	// Date format used for generate primary key
	public static final String DEFAULT_DATE_FORMAT = "yyyymmdd";
	public static final String SHORT_DATE_FORMAT = "yymmdd";
	public static final String FULL_DATE_FORMAT = "dd/mm/yyyy";
	public static final String DEFAULT_TIMESTAMP_FORMAT = "yyyy/MM/dd HH:mm";
	public static final String DEFAULT_DECIMAL_FORMAT = "#,###,##0.00;-#,###,##0.00";

	// ####################################################################
	// ## ##
	// ## Email Config ##
	// ## ##
	// ####################################################################

	public static final String EMAIL_DELIM = ",";
	public static final String EMAIL_TEMPLATE_EXT = ".html";

	// General
	public static final String TRUE = "Y";
	public static final String FALSE = "N";
	public static final String EMPTY = "";

	public static final String DOT = ".";
	public static final String COLON = ":";
	public static final String COMMA = ",";
	public static final String SPACE = " ";
	public static final String US_SEP = "_";
	public static final String BR_DEPT_SEP = US_SEP;

	public static final String EDIT = "E";
	public static final String READ = "R";

	public static final String SORT_ASCENDING = "ASC";
	public static final String SORT_DESCENDING = "DESC";

	public static final String SQLMAP_OFFSET = "offset";
	public static final String SQLMAP_OFFSET_MAX = "offset_max";
	public static final String SQLMAP_GROUP_BY = "group_by";
	public static final String SQLMAP_ORDER_BY = "order_by";
	public static final String SQLMAP_FILTER_BY = "filter_by";
	public static final String SQLMAP_IS_COUNT = "is_count";
	public static final String SQLMAP_SQL_FIELDS = "sql_fields";
	public static final String SQLMAP_LIST_DELIM = "','";

	public static final BigDecimal ZERO = new BigDecimal(0);
	public static final Integer INT_ZERO = new Integer(0);
	public static final String STR_ZERO = "0";

	public static final String DATE_FORMAT = "yyyy-MM-dd";
	public static final String TIME_FORMAT = "HH:mm";
	public static final String DATE_RANGE_SEP = " - ";
	public static final String DATE_TIME_SEP = " ";
	public static final String DATE_TIME_FORMAT = DATE_FORMAT + DATE_TIME_SEP + TIME_FORMAT;
	public static final String STRING_ZERO = "0";

	public static final int PORT_HTTPS = 443;
	public static final String PROTOCOL_HTTP = "http";
	public static final String PROTOCOL_HTTPS = "https";

	public static final String TEMP = "Temp";
	public static final String DEFAULT_ENCODING = "UTF-8";
	public static final String FILE_SEP = File.separator;
	public static final String PATH_SEP = FILE_SEP; // "\\"; // "/" for Unix
	public static final String LINE_SEP = System.getProperty("line.separator");
	public static final String CRLF = "\r\n";
}