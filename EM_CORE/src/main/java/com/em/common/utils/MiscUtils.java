package com.em.common.utils;

import java.io.Closeable;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.em.common.consts.SysConstants;

@SuppressWarnings({ "unchecked", "unused", "deprecation" })
public class MiscUtils implements SysConstants {

	// Constants
	private static final String MARKER = "##";

	public static boolean isBlank(String value) {
		return (value == null) || value.trim().equals(EMPTY);
	}

	public static boolean isBlank(Map dataMap, String param) {
		String tmp = getStringFromMap(dataMap, param);
		return isBlank(tmp);
	}

	public static boolean isEqual(String input, String value) {
		return (input != null) && (value != null) && input.trim().equals(value);
	}

	public static String getYesNo(boolean value) {
		return (value) ? TRUE : FALSE;
	}

	public static String getStringFromMap(Map dataMap, String param) {
		return getStringFromMap(dataMap, param, EMPTY);
	}

	public static String getStringFromMap(Map dataMap, String param, String defaultValue) {
		String value = defaultValue;
		if (dataMap != null) {
			Object obj = dataMap.get(param);
			value = getString(obj, value);
		}
		return value;
	}

	public static byte[] copyBytes(byte src[], int nSize) {
		return copyBytes(src, nSize, nSize);
	}

	public static byte[] copyBytes(byte src[], int nSize, int nCopy) {
		return copyBytes(src, 0, 0, nSize, nCopy);
	}

	public static byte[] copyBytes(byte src[], int srcOffset, int dstOffset, int nSize, int nCopy) {
		byte nByte[] = new byte[nSize];
		System.arraycopy(src, srcOffset, nByte, dstOffset, nCopy);
		return nByte;
	}

	public static void copyData(Map dstMap, Map srcMap, String keys[], boolean isCopyNull, boolean isBackup) {
		if (keys != null && srcMap != null && dstMap != null) {
			for (int i = 0; i < keys.length; i++) {
				String key = keys[i];
				Object tmp = srcMap.get(key);
				if (tmp != null || (isCopyNull && dstMap.containsKey(key)) || (isBackup && srcMap.containsKey(key))) {
					dstMap.put(key, tmp);
				}
			}
		}
	}

	public static void copyData(Map dstMap, Map srcMap, String keys[], boolean isCopyNull) {
		copyData(dstMap, srcMap, keys, isCopyNull, false);
	}

	public static void copyData(Map dstMap, Map srcMap, String keys[]) {
		copyData(dstMap, srcMap, keys, false);
	}

	public static void removeData(Map dstMap, String keys[]) {
		if (dstMap != null && keys != null) {
			for (int i = 0; i < keys.length; i++) {
				dstMap.remove(keys[i]);
			}
		}
	}

	public static Map backupData(Map srcMap, String keys[]) {
		return backupData(null, srcMap, keys);
	}

	public static Map backupData(Map dstMap, Map srcMap, String keys[]) {
		dstMap = getSafeMap((HashMap) dstMap);
		copyData(dstMap, srcMap, keys, false, true);
		return dstMap;
	}

	public static void restoreData(Map dstMap, Map srcMap, String keys[]) {
		removeData(dstMap, keys);
		copyData(dstMap, srcMap, keys, false, true);
	}

	public static String getString(Object obj) {
		return getString(obj, EMPTY);
	}

	public static HashMap getSafeMap(HashMap srcMap) {
		return (srcMap == null) ? new HashMap() : srcMap;
	}

	public static ArrayList getSafeList(ArrayList srcList) {
		return (srcList == null) ? new ArrayList() : srcList;
	}

	public static HashMap addToMap(Object key, Object value) {
		return addToMap(null, key, value);
	}

	public static HashMap addToMap(HashMap srcMap, Object key, Object value) {
		srcMap = getSafeMap(srcMap);
		if (key != null) {
			srcMap.put(key, value);
		}
		return srcMap;
	}

	public static ArrayList addToList(Object obj) {
		return addToList(null, obj);
	}

	public static ArrayList addToList(ArrayList srcList, Object obj) {
		srcList = getSafeList(srcList);
		if (obj != null) {
			srcList.add(obj);
		}
		return srcList;
	}

	public static HashMap getKeyMap(HashMap srcMap, Object key) {
		HashMap result = null;
		if (srcMap != null && key != null) {
			result = getSafeMap((HashMap) srcMap.get(key));
			srcMap.put(key, result);
		}
		return result;
	}

	public static ArrayList getKeyList(HashMap srcMap, Object key) {
		ArrayList result = null;
		if (srcMap != null && key != null) {
			result = getSafeList((ArrayList) srcMap.get(key));
			srcMap.put(key, result);
		}
		return result;
	}

	public static boolean isEmpty(Map obj) {
		return obj == null || obj.isEmpty();
	}

	public static boolean isEmpty(Collection obj) {
		return obj == null || obj.isEmpty();
	}

	public static String getString(Object obj, String defaultValue) {
		String value = defaultValue;
		if (obj != null) {
			if (obj instanceof String) {
				// value = (String)obj;
				String tmp = (String) obj;
				if (!isBlank(tmp)) {
					value = tmp;
				}
			} else if (obj instanceof Double) {
				value = formatDoubleWithTwoDecimals((Double) obj);
			} else {
				value = obj.toString();
			}
		}
		return value;
	}

	public static Double getDouble(Object obj) {
		Double value = new Double(STR_ZERO);
		if (obj != null) {
			if (obj instanceof Double) {
				return (Double) obj;
			}
			try {
				if (obj instanceof Long || obj instanceof Short || obj instanceof Float || obj instanceof Integer || obj instanceof BigInteger || obj instanceof BigDecimal || obj instanceof String) {
					value = new Double(obj.toString());
				}
			} catch (Exception e) {
			}
		}
		return value;
	}

	public static double getDouble(Map dataMap, String key) {
		if (dataMap != null) {
			Object obj = dataMap.get(key);
			if (obj != null) {
				return getDouble(obj).doubleValue();
			}
		}
		return 0;
	}

	/**
	 * Format the Double value to be with 2 decimal places.
	 *
	 * @param d
	 *            The Double value to be formatted.
	 *
	 * @return String
	 */
	public static String formatDoubleWithTwoDecimals(Double d) {
		return formatDoubleWithTwoDecimals(d, true);
	}

	/**
	 * Format the double value to be with 2 decimal places.
	 *
	 * @param d
	 *            The double value to be formatted.
	 *
	 * @return String
	 */
	public static String formatDoubleWithTwoDecimals(double d) {
		return formatDoubleWithTwoDecimals(new Double(d), true);
	}

	/**
	 * Format the Double value to be with 2 decimal places.
	 *
	 * @param d
	 *            The Double value to be formatted
	 * @param needTailZeros
	 *            Whether to show the tail zeros, if exists
	 *
	 * @return String
	 */
	public static String formatDoubleWithTwoDecimals(Double d, boolean needTailZeros) {
		String result = "0.00";
		if (d == null) {
			result = "0.00";
		} else {
			DecimalFormat two_places = new DecimalFormat(result);
			result = two_places.format(d.doubleValue());
		}
		if (!needTailZeros) {
			String tail = result.substring(result.indexOf(DOT) + 1);
			if (tail.equals("00")) {
				result = result.substring(0, result.indexOf(DOT));
			}
		}
		return result;
	}

	public static HashMap string2Map(String data[]) {
		HashMap result = new HashMap();
		if (data != null) {
			for (int i = 0; i < data.length; i++) {
				result.put(data[i], EMPTY);
			}
		}
		return result;
	}

	public static String toJsonObj(HashMap data) {
		return toJsonObj(data, null, null);
	}

	public static String toJsonObj(HashMap data, String key[]) {
		return toJsonObj(data, string2Map(key), null);
	}

	public static String toJsonObj(HashMap data, HashMap keyMap, String reserved) {
		String jsonData = EMPTY;
		HashMap tmpMap = (keyMap != null && !keyMap.isEmpty()) ? keyMap : data;
		for (Iterator it = tmpMap.keySet().iterator(); it.hasNext();) {
			String key = (String) it.next();
			String value = getStringFromMap(data, key);
			if (!isBlank(value)) {
				value = replaceString(value, "'", "\\'");
				jsonData += ", " + key + COLON + "'" + value + "'";
			}
		}
		return "{" + ((jsonData.length() > 0) ? jsonData.substring(2) : jsonData) + "}";
	}

	/**
	 * Replace all instances of a String in a String, with another String.
	 * 
	 * @param s
	 *            String to alter.
	 * @param f
	 *            String to look for.
	 * @param r
	 *            String to replace it with, or null to just remove it.
	 */
	public static String replaceString(String s, String f, String r) {
		if (s == null || f == null) {
			return s;
		}
		if (r == null) {
			r = EMPTY;
		}
		int skip = f.length();
		int oldindex = 0;
		int newindex = s.indexOf(f);
		StringBuffer sb = new StringBuffer();
		while (newindex != -1) {
			if (newindex > 0) {
				sb.append(s.substring(oldindex, newindex));
			}
			sb.append(r);
			oldindex = newindex + skip;
			newindex = s.indexOf(f, oldindex);
		}
		if (oldindex == 0) {
			return s;
		} else {
			sb.append(s.substring(oldindex));
			return sb.toString();
		}
	}

	public static String padLeft(String input, String padChar, int length) {
		String tmp = EMPTY;
		input = (input != null) ? input : EMPTY;
		padChar = (padChar != null && padChar.length() > 0) ? padChar : STR_ZERO;
		for (int i = input.length(); i < length; i++) {
			tmp += padChar;
		}
		return tmp + input;
	}

	public static String fillData(String input, Map dataMap) {
		if (input != null && !isEmpty(dataMap)) {
			int idx = 0;
			StringBuffer result = new StringBuffer();
			for (Iterator it = dataMap.keySet().iterator(); it.hasNext();) {
				String mapKey = (String) it.next();
				String key = MARKER + mapKey + MARKER;
				while ((idx = input.indexOf(key)) != -1) {
					if (input.substring(0, idx).indexOf(MARKER) != -1) {
						input = input.substring(0, idx) + dataMap.get(mapKey) + input.substring(idx + key.length());
					} else {
						result.append(input.substring(0, idx) + dataMap.get(mapKey));
						input = input.substring(idx + key.length());
					}
				}
			}
			return result.append(input).toString();
		}
		return input;
	}

	public static BigDecimal getBigDecimal(Object obj) {
		return getBigDecimal(obj, ZERO);
	}

	public static BigDecimal getBigDecimal(Object obj, BigDecimal defVal) {
		if (obj != null) {
			if (obj instanceof BigDecimal) {
				return (BigDecimal) obj;
			} else if (obj instanceof Float) {
				return new BigDecimal(((Float) obj).doubleValue());
			} else if (obj instanceof Double) {
				return new BigDecimal(((Double) obj).doubleValue());
			} else if (obj instanceof BigInteger) {
				return new BigDecimal(((BigInteger) obj).longValue());
			} else if (obj instanceof Long) {
				return new BigDecimal(((Long) obj).longValue());
			} else if (obj instanceof Short) {
				return new BigDecimal(obj.toString());
			} else if (obj instanceof Integer) {
				return new BigDecimal(obj.toString());
			} else if (obj instanceof String) {
				try {
					String tmp = (String) obj;
					if (tmp.length() > 0) {
						return new BigDecimal(tmp);
					}
				} catch (NumberFormatException e) {
					// log.error("Number format exception (key: " + param +
					// ", value: " + obj.toString() + ") ==> return 0") ;
				}
			}
		}
		return defVal;
	}

	public static BigDecimal getBigDecimalFromMap(Map map, String param) {
		return getBigDecimalFromMap(map, param, ZERO);
	}

	public static BigDecimal getBigDecimalFromMap(Map map, String param, BigDecimal defVal) {
		if (map != null) {
			Object obj = map.get(param);
			return getBigDecimal(obj, defVal);
		}
		return defVal;
	}

	public static java.sql.Date getDateFromMap(Map map, String param) {
		java.sql.Date result = null;
		if (map != null && param != null) {
			Object obj = map.get(param);
			if (obj != null) {
				if (obj instanceof java.sql.Date) {
					result = (java.sql.Date) obj;
				} else if (obj instanceof java.sql.Timestamp) {
					result = new java.sql.Date(((java.sql.Timestamp) obj).getTime());
				} else if (obj instanceof java.util.Date) {
					result = new java.sql.Date(((java.util.Date) obj).getTime());
				}
			}
		}
		return result;
	}

	public static java.sql.Timestamp getTimestampFromMap(Map map, String param) {
		java.sql.Timestamp result = null;
		if (map != null && param != null) {
			Object obj = map.get(param);
			if (obj != null) {
				if (obj instanceof java.sql.Timestamp) {
					result = (java.sql.Timestamp) obj;
				} else if (obj instanceof java.sql.Date) {
					result = new java.sql.Timestamp(((java.sql.Date) obj).getTime());
				} else if (obj instanceof java.util.Date) {
					result = new java.sql.Timestamp(((java.util.Date) obj).getTime());
				}
			}
		}
		return result;
	}

	public static int getInt(Map dataMap, String key) {
		return getInt(dataMap, key, 0);
	}

	public static int getInt(Map dataMap, String key, int defVal) {
		int result = defVal;
		Integer tmp = getInteger(dataMap, key, null);
		if (tmp != null) {
			result = tmp.intValue();
		}
		return result;
	}

	public static Integer getInteger(Map dataMap, String key) {
		return getInteger(dataMap, key, INT_ZERO);
	}

	public static Integer getInteger(Map dataMap, String key, Integer defVal) {
		Integer result = defVal;
		if (dataMap != null) {
			Object obj = dataMap.get(key);
			if (obj != null) {
				if (obj instanceof Integer) {
					result = (Integer) obj;
				} else if (obj instanceof Long) {
					result = new Integer(((Long) obj).intValue());
				} else if (obj instanceof Short) {
					result = new Integer(((Short) obj).intValue());
				} else if (obj instanceof Float) {
					result = new Integer(((Float) obj).intValue());
				} else if (obj instanceof Double) {
					result = new Integer(((Double) obj).intValue());
				} else if (obj instanceof BigInteger) {
					result = new Integer(((BigInteger) obj).intValue());
				} else if (obj instanceof BigDecimal) {
					result = new Integer(((BigDecimal) obj).intValue());
				} else if (obj instanceof String) {
					String tmp = ((String) obj);
					if (tmp.trim().length() > 0) {
						try {
							result = new Integer(tmp);
						} catch (NumberFormatException nfe) {
						} catch (Exception e) {
						}
					}
				}
			}
		}
		return result;
	}

	public static boolean hasSameMapKey(HashMap mapA, HashMap mapB, boolean isCheckValue) {
		if (mapA != null && mapB != null) {
			if (mapA.size() != mapB.size()) {

				return false;
			}
			for (Iterator it = mapA.keySet().iterator(); it.hasNext();) {
				Object key = it.next();
				if (!mapB.containsKey(key)) {

					return false;
				}
				if (isCheckValue) {
					if (!isSameObj(mapA.get(key), mapB.get(key))) {

						if (mapA.get(key) != null && mapB.get(key) != null) {

						}
						return false;
					}
				}
			}
		} else if (mapA == null && mapB == null) {
			return true;
		} else {
			return false;
		}
		return true;
	}

	public static boolean isSameObj(Object objA, Object objB) {
		if (objA != null && objB != null) {
			String classA = objA.getClass().getName();
			String classB = objB.getClass().getName();
			if (!classA.equals(classB)) {
				return false;
			}
			if (objA instanceof String) {
				if (((String) objA).compareTo((String) objB) != 0) {
					return false;
				}
			} else if (objA instanceof Long) {
				if (((Long) objA).compareTo((Long) objB) != 0) {
					return false;
				}
			} else if (objA instanceof Short) {
				if (((Short) objA).compareTo((Short) objB) != 0) {
					return false;
				}
			} else if (objA instanceof Float) {
				if (((Float) objA).compareTo((Float) objB) != 0) {
					return false;
				}
			} else if (objA instanceof Double) {
				if (((Double) objA).compareTo((Double) objB) != 0) {
					return false;
				}
			} else if (objA instanceof Integer) {
				if (((Integer) objA).compareTo((Integer) objB) != 0) {
					return false;
				}
			} else if (objA instanceof BigInteger) {
				if (((BigInteger) objA).compareTo((BigInteger) objB) != 0) {
					return false;
				}
			} else if (objA instanceof BigDecimal) {
				if (((BigDecimal) objA).compareTo((BigDecimal) objB) != 0) {
					return false;
				}
			} else if (objA instanceof java.util.Date) {
				if (((java.util.Date) objA).compareTo((java.util.Date) objB) != 0) {
					return false;
				}
			} else if (objA instanceof java.sql.Date) {
				if (((java.sql.Date) objA).compareTo((java.sql.Date) objB) != 0) {
					return false;
				}
			} else if (objA instanceof java.sql.Timestamp) {
				if (((java.sql.Timestamp) objA).compareTo((java.sql.Timestamp) objB) != 0) {
					return false;
				}
			} else if (objA instanceof HashMap) {
				if (!hasSameMapKey((HashMap) objA, (HashMap) objB, true)) {
					return false;
				}
			} else if (objA instanceof Collection) {
				if (((Collection) objA).size() != ((Collection) objB).size()) {
					return false;
				}
				Iterator itA = ((Collection) objA).iterator();
				for (Iterator itB = ((Collection) objB).iterator(); itB.hasNext();) {
					if (!isSameObj(itA.next(), itB.next())) {
						return false;
					}
				}
			}
		} else if (objA == null && objB == null) {
			return true;
		} else {
			return false;
		}
		return true;
	}

	public static void fillData(Map dst, Map src, final String keys[], final String defValues[], boolean isSpec) {
		if (src != null && dst != null && keys != null) {
			for (int i = 0; i < keys.length; i++) {
				String key = keys[i];
				Object value = (src.containsKey(key)) ? src.get(key) : ((defValues != null) ? defValues[i] : null);
				// Special Handle
				if (isSpec && value != null && isBlank(value.toString())) {
					value = null;
				}
				dst.put(key, value);
			}
		}
	}

	public static String concat(String cur, String input, String delim) {
		String result = getString(cur);
		// if(input != null && input.length() > 0) {
		// delim = (delim != null) ? delim : EMPTY;
		// result += (result.length() > 0 ? delim : EMPTY) + input;
		// }
		if (!isBlank(input)) {
			if (delim == null) {
				delim = EMPTY;
			}
			result += (!isBlank(result) ? delim : EMPTY) + input;
		}
		return result;
	}

	public static String concat(List input, String delim) {
		String result = EMPTY;
		if (!isEmpty(input)) {
			if (delim == null) {
				delim = EMPTY;
			}
			for (int i = 0; i < input.size(); i++) {
				String tmp = getString(input.get(i));
				result = concat(result, tmp, delim);
			}
		}
		return result;
	}

	public static String getInValue(Object obj) {
		String result = EMPTY;
		if (obj != null && obj instanceof List) {
			List tmp = (List) obj;
			if (!tmp.isEmpty()) {
				result = concat(tmp, SQLMAP_LIST_DELIM);
			}
		}
		return result;
	}

	public static HashMap addPrefixSuffix(HashMap data, String prefix, String suffix) {
		prefix = getString(prefix);
		suffix = getString(suffix);
		HashMap result = new HashMap();
		if (data != null && !data.isEmpty()) {
			if (isBlank(prefix) && isBlank(suffix)) {
				result.putAll(data);
			} else {
				for (Iterator it = data.keySet().iterator(); it.hasNext();) {
					String key = (String) it.next();
					result.put(prefix + key + suffix, data.get(key));
				}
			}
		}
		return result;
	}

	public static boolean isEqual(Map data, String key, String value) {
		return MiscUtils.getStringFromMap(data, key).equals(value);
	}

	public static boolean isTrue(Map data, String key) {
		return isEqual(data, key, TRUE);
	}

	public static String isTrueEx(Map data, String key) {
		return getYesNo(isTrue(data, key));
	}

	public static void cloneList(ArrayList input) {
		if (!isEmpty(input)) {
			ArrayList result = new ArrayList();
			for (int i = 0; i < input.size(); i++) {
				HashMap tmp = (HashMap) input.get(i);
				result.add(new HashMap(tmp));
			}
			input.clear();
			input.addAll(result);
		}
	}

	public static HashMap clone(HashMap data) {
		HashMap result = new HashMap();
		if (!isEmpty(data)) {
			for (Iterator it = data.keySet().iterator(); it.hasNext();) {
				Object key = it.next();
				Object value = data.get(key);
				if (value != null) {
					if (value instanceof ArrayList) {
						value = clone((ArrayList) value);
					} else if (value instanceof HashMap) {
						value = clone((HashMap) value);
					}
				}
				result.put(key, value);
			}
		}
		return result;
	}

	public static ArrayList clone(ArrayList data) {
		ArrayList result = new ArrayList();
		if (!isEmpty(data)) {
			for (int i = 0; i < data.size(); i++) {
				Object value = data.get(i);
				if (value != null) {
					if (value instanceof ArrayList) {
						value = clone((ArrayList) value);
					} else if (value instanceof HashMap) {
						value = clone((HashMap) value);
					}
				}
				result.add(value);
			}
		}
		return result;
	}

	public static String getError(Throwable t) {
		String result = EMPTY;
		StringWriter sw = null;
		PrintWriter pw = null;
		try {
			sw = new StringWriter();
			pw = new PrintWriter(sw);
			t.printStackTrace(pw);
			result = sw.toString();
		} finally {
			close(sw);
			close(pw);
		}
		return result;
	}

	public static void close(Closeable stream) {
		if (stream != null) {
			try {
				stream.close();
			} catch (IOException e) {
			}
		}
	}

	/**
	 * Get Index From a Pre-Sort Array
	 */
	public static int getIdx(Comparable arr[], Comparable value) {
		if (arr != null && arr.length > 0 && value != null) {
			int mid;
			int low = 0;
			int len = arr.length;
			int high = len;
			while (low < high) {
				mid = low + ((high - low) / 2);
				if (arr[mid].compareTo(value) < 0) {
					low = mid + 1;
				} else {
					high = mid;
				}
			}
			if ((low < len) && (arr[low].compareTo(value) == 0)) {
				return low;
			}
		}
		return -1;
	}

	public static int getIdx(int arr[], int value) {
		if (arr != null && arr.length > 0) {
			int mid;
			int low = 0;
			int len = arr.length;
			int high = len;
			while (low < high) {
				mid = low + ((high - low) / 2);
				if (arr[mid] < value) {
					low = mid + 1;
				} else {
					high = mid;
				}
			}
			if ((low < len) && (arr[low] == value)) {
				return low;
			}
		}
		return -1;
	}

	public static boolean contains(String keys[], String value) {
		if (keys != null && value != null) {
			return Arrays.asList(keys).indexOf(value) != -1;
		}
		return false;
	}

	public static void addBreakPoint() {
		int i = 1;
		if (i == 1) {
			throw new RuntimeException("addBreakPoint :: Testing");
		}
	}

	public static String getKeyForCompare(Map dataMap, String keys[], String padChar, int padLen) {
		String result = EMPTY;
		if (keys != null) {
			// p.s. The padding left used to handle integer comparison
			for (int j = 0; j < keys.length; j++) {
				String key = keys[j];
				String tmp = (key == null) ? EMPTY : getStringFromMap(dataMap, key);
				result += FILE_SEP + (padLen > 0 ? padLeft(tmp, padChar, padLen) : tmp);
			}
		}
		return result;
	}

	public static HashMap getKeyIdx(List dataList, String keys[]) {
		HashMap result = new HashMap();
		if (!isEmpty(dataList) && keys != null && keys.length > 0) {
			for (int i = 0; i < dataList.size(); i++) {
				HashMap tmp = (HashMap) dataList.get(i);
				String key = getKeyForCompare(tmp, keys, null, 0);
				result.put(key, new Integer(i));
			}
		}
		return result;
	}

	public static void sort(List dataList, final String keys[], final String padChar, final int padLen, final boolean sortAsc) {
		if (!isEmpty(dataList) && dataList.size() > 1) {
			Collections.sort(dataList, new Comparator() {
				public int compare(Object o1, Object o2) {
					String q1 = getKeyForCompare((HashMap) o1, keys, padChar, padLen);
					String q2 = getKeyForCompare((HashMap) o2, keys, padChar, padLen);
					int tmp = q1.compareTo(q2);
					return (tmp == 0 || sortAsc) ? tmp : (-1 * tmp);
				}
			});
		}
	}

	public static void prepareListMapForCompare(List nList, List oList, final String keys[]) {
		prepareListMapForCompare(nList, oList, keys, null, 10, true);
	}

	public static void prepareListMapForCompare(List nList, List oList, final String keys[], final String padChar, final int padLen, final boolean sortAsc) {
		if (nList != null && oList != null && keys != null) {
			HashMap nMap = getKeyIdx(nList, keys);
			HashMap oMap = getKeyIdx(oList, keys);
			if (!isEmpty(nMap) || !isEmpty(oMap)) {
				ArrayList oToAdd = new ArrayList();
				ArrayList nToAdd = new ArrayList();
				for (Iterator it = nMap.keySet().iterator(); it.hasNext();) {
					Object key = it.next();
					// Remove Duplicate
					if (oMap.containsKey(key)) {
						oMap.remove(key);
					}
					// Add oList missing item
					else {
						HashMap tmp = new HashMap();
						Integer idx = (Integer) nMap.get(key);
						HashMap nData = (HashMap) nList.get(idx.intValue());
						copyData(tmp, nData, keys);
						oToAdd.add(tmp);
					}
				}
				for (Iterator it = oMap.keySet().iterator(); it.hasNext();) {
					Object key = it.next();
					// Add nList missing item
					HashMap tmp = new HashMap();
					Integer idx = (Integer) oMap.get(key);
					HashMap oData = (HashMap) oList.get(idx.intValue());
					copyData(tmp, oData, keys);
					nToAdd.add(tmp);
				}
				nList.addAll(nToAdd);
				oList.addAll(oToAdd);
				sort(nList, keys, padChar, padLen, sortAsc);
				sort(oList, keys, padChar, padLen, sortAsc);
			}
		}
	}

	/**
	 * 获取map中key的集合
	 * 
	 * @param map
	 * @return
	 */
	public static List<String> getKeysList(Map map) {
		List list = new ArrayList();
		java.util.Iterator it = map.entrySet().iterator();
		while (it.hasNext()) {
			java.util.Map.Entry entry = (java.util.Map.Entry) it.next();
			Object key = entry.getKey().toString(); // 返回对应的键
			list.add(key);
		}
		return list;
	}

	public static List<String> getValuesList(Map map) {
		List list = new ArrayList();
		java.util.Iterator it = map.entrySet().iterator();
		while (it.hasNext()) {
			java.util.Map.Entry entry = (java.util.Map.Entry) it.next();
			Object value = entry.getValue().toString(); // 返回对应的键
			list.add(value);
		}
		return list;
	}

	public static Object mapToBean(Map<String, Object> map, Class<?> clazz) throws Exception {
		Object obj = clazz.newInstance();
		if (map != null && map.size() > 0) {
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				String propertyName = entry.getKey(); // 属性名
				Object value = entry.getValue();
				String setMethodName = "set" + propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);
				Field field = getClassField(clazz, propertyName);
				if (field == null)
					continue;
				Class<?> fieldTypeClass = field.getType();
				value = convertValType(value, fieldTypeClass);
				try {
					clazz.getMethod(setMethodName, field.getType()).invoke(obj, value);
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				}
			}
		}
		return obj;
	}

	private static Field getClassField(Class<?> clazz, String fieldName) {
		if (Object.class.getName().equals(clazz.getName())) {
			return null;
		}
		Field[] declaredFields = clazz.getDeclaredFields();
		for (Field field : declaredFields) {
			if (field.getName().equals(fieldName)) {
				return field;
			}
		}

		Class<?> superClass = clazz.getSuperclass();
		if (superClass != null) {// 简单的递归一下
			return getClassField(superClass, fieldName);
		}
		return null;
	}

	/**
	 * 将Object类型的值，转换成bean对象属性里对应的类型值
	 * 
	 * @param value
	 *            Object对象值
	 * @param fieldTypeClass
	 *            属性的类型
	 * @return 转换后的值
	 */
	private static Object convertValType(Object value, Class<?> fieldTypeClass) {
		Object retVal = null;
		if (Long.class.getName().equals(fieldTypeClass.getName()) || long.class.getName().equals(fieldTypeClass.getName())) {
			retVal = Long.parseLong(value.toString());
		} else if (Integer.class.getName().equals(fieldTypeClass.getName()) || int.class.getName().equals(fieldTypeClass.getName())) {
			retVal = Integer.parseInt(value.toString());
		} else if (Float.class.getName().equals(fieldTypeClass.getName()) || float.class.getName().equals(fieldTypeClass.getName())) {
			retVal = Float.parseFloat(value.toString());
		} else if (Double.class.getName().equals(fieldTypeClass.getName()) || double.class.getName().equals(fieldTypeClass.getName())) {
			retVal = Double.parseDouble(value.toString());
		} else {
			retVal = value;
		}
		return retVal;
	}

}