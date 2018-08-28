package com.em.common.druid;

import com.alibaba.druid.filter.config.ConfigTools;
import com.alibaba.druid.util.DruidPasswordCallback;
import com.em.common.service.ConfigService;
import com.em.common.utils.StringUtils;

import org.apache.catalina.security.SecurityUtil;

import java.util.Properties;

/**
 * 数据库密码回调解密
 */
@SuppressWarnings("serial")
public class DBPasswordCallback extends DruidPasswordCallback {
	public static final String PUBLIC_KEY_STRING = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAIbKRC+daEpGQ9ovbJLT2eTVDsmtdaznHBa9x5OYLPNsJ5kdLoVPELDKgmk4COGmLAxPzk38tMvFnSaft1Dud90CAwEAAQ==";
	public void setProperties(Properties properties) {
		String pwd = ConfigService.getConfig("jdbc_password");
		if (!StringUtils.isEmpty(pwd)) {
			try {
				String password = ConfigTools.decrypt(PUBLIC_KEY_STRING, pwd);
				setPassword(password.toCharArray());
			} catch (Exception e) {
				setPassword(null);
			}
		}
	}
}