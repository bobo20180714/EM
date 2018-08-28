/**
 * @Title: Md5Util.java
 * @Package org.zxgs.jweb.utils
 * @Description: TODO(用一句话描述该文件做什么)
 * @author 曹建
 * @date 2012-11-9 下午9:26:06
 * @version V1.0
 */
package com.em.common.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

/**
 * @Title: Md5Util.java
 * @Package org.zxgs.jweb.utils
 * @Description: TODO(用一句话描述该文件做什么)
 * @author 曹建
 * @date 2012-11-9 下午9:26:06
 * @version V1.0
 */
public class Md5Util {

	/***
	 * MD5加码 生成32位md5码
	 */
	public static String string2MD5(String inStr) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			// System.out.println(e.toString());
			e.printStackTrace();
			return "";
		}
		char[] charArray = inStr.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++)
			byteArray[i] = (byte) charArray[i];
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();

	}
}
