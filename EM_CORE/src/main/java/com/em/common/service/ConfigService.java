package com.em.common.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

/**
 * 
 * @ClassName: ConfigService
 * @Description: 配置文件获取Service
 * @author: liuyx
 * @date: 2015年9月20日上午9:53:42
 */
@Service
public class ConfigService {
	public static final String JDBC_DRIVER_CLASS_NAME = "driverClassName";
	public static final String JDBC_URL = "jdbc_url";
	public static final String JDBC_USER_NAME = "jdbc_username";
	public static final String JDBC_PASSWORD = "jdbc_password";

	public static final String DEFAULT_PASSWORD = "default_operator_password";
	public static final String TMP_DIR = "tmpDir";
	public static final String UPLOAD_DIR = "uploadDir";
	public static final String DYNAMIC_LOAD_MYBATIS_XML_SWITCH = "dynamic_load_mybatis_xml_switch";
	public static final String PROJECT_NAME = "projectName";
	// public static final String PROJECT_IP = "projectIp";
	// public static final String PROJECT_URL = "PROJECT_URL";
	public static final String LICENSE = "license";

	// wei xin qi ye hao start
	public static final String WX_TOKEN = "WX_TOKEN";
	public static final String WX_ECORDING_AES_KEY = "WX_ECORDING_AES_KEY";
	public static final String WX_CORPID = "WX_CORPID";
	public static final String WX_ACCESS_TOKEN_CORPSECRET = "WX_ACCESS_TOKEN_CORPSECRET";
	// wei xin qi ye hao end

	// wei xin fu wu hao start
	public static final String WX_FWH_TOKEN = "WX_FWH_TOKEN";
	public static final String WX_FWH_ECORDING_AES_KEY = "WX_FWH_ECORDING_AES_KEY";
	public static final String WX_FWH_APPID = "WX_FWH_APPID";
	public static final String WX_FWH_APPSECRET = "WX_FWH_APPSECRET";
	// wei xin fu wu hao end

	private static Properties config = null;
	private static final Pattern pattern = Pattern.compile("\\$\\{([^\\}]+)\\}");

	public static String getConfig(String arg) {

		try {
			setConfig();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String result = config.getProperty(arg).trim();
		return getPatternReplacedStr(result);
	}

	@PostConstruct
	public void init() throws IOException {
		setConfig();
		System.out.println("config: " + config);
	}

	/**
	 * 实时取得配置文件中的配置
	 * 
	 * @param arg
	 * @return
	 * @throws IOException
	 */
	public static String getConfigRealTime(String arg) throws IOException {
		try {
			forceSetConfig();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Properties prop = new Properties();
		try (InputStream in = ConfigService.class.getClassLoader().getResourceAsStream("config.properties");) {
			prop.load(in);
		}
		String result = config.getProperty(arg).trim();
		return getPatternReplacedStr(result);
	}

	private static void forceSetConfig() throws IOException {
		Properties prop = new Properties();
		try (InputStream in = ConfigService.class.getClassLoader().getResourceAsStream("config.properties");) {
			prop.load(in);
		}
		config = prop;
	}

	private static void setConfig() throws IOException {
		if (config != null) {
			return;
		}
		Properties prop = new Properties();
		try (InputStream in = ConfigService.class.getClassLoader().getResourceAsStream("config.properties");) {
			prop.load(in);
		}
		config = prop;
	}

	/**
	 * @Title: getPatternReplacedStr
	 * @author：liuyx
	 * @date：2015年9月20日上午11:04:11
	 * @Description: TODO
	 * @param result
	 * @return
	 */
	private static String getPatternReplacedStr(String result) {
		Matcher mat = pattern.matcher(result);
		int i = 0;
		StringBuffer sb = new StringBuffer();
		boolean isFind = mat.find();
		while (isFind) {
			i++;
			String matche = mat.group(1);
			String changeTo = getConfig(matche);
			mat.appendReplacement(sb, changeTo);
			// 继续查找下一个匹配对象
			isFind = mat.find();
		}
		mat.appendTail(sb);
		return sb.toString();
	}
}
