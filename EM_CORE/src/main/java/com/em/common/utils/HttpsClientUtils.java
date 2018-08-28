package com.em.common.utils;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

public class HttpsClientUtils {
	private static Logger logger = LoggerFactory.getLogger(HttpsClientUtils.class);
	static CloseableHttpClient httpClient;
	static CloseableHttpResponse httpResponse;

	public static CloseableHttpClient createSSLClientDefault() {
		try {
			SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
				// 信任所有
				public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
					return true;
				}
			}).build();
			HostnameVerifier hostnameVerifier = NoopHostnameVerifier.INSTANCE;
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, hostnameVerifier);
			return HttpClients.custom().setSSLSocketFactory(sslsf).build();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyStoreException e) {
			e.printStackTrace();
		}
		return HttpClients.createDefault();

	}

	/*****
	 * 发送https的post请求，request的content-type为application/json 返回值为string
	 * 
	 * @param params
	 * @param url
	 * @return
	 */
	public static String httpPost(String url, JSONObject jsonParam) {
		HttpPost httpPost = new HttpPost(url);
		String result = "";
		try {
			StringEntity entity = null;
			if (!StringUtils.isEmpty(jsonParam)) {
				logger.info("创建请求httpPost-URL={},params={}", jsonParam);
				// 解决中文乱码问题
				entity = new StringEntity(jsonParam.toString(), "utf-8");
				entity.setContentEncoding("UTF-8");
				entity.setContentType("application/json");
				httpPost.setEntity(entity);
			}
			httpClient = HttpsClientUtils.createSSLClientDefault();
			httpResponse = httpClient.execute(httpPost);
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				try {
					// 读取服务器返回过来的json字符串数据
					result = EntityUtils.toString(httpResponse.getEntity(), "utf-8");
				} catch (Exception e) {
					logger.error("post请求提交失败:" + url, e);
				}
			}
		} catch (Exception e) {
			logger.error("post请求提交失败:" + url, e);
		} finally {
			try {
				httpResponse.close();
				httpClient.close();
				httpPost.releaseConnection();
				logger.info("请求流关闭完成");
			} catch (IOException e) {
				logger.info("请求流关闭出错");
				e.printStackTrace();
			}
		}
		return result;
	}

	/*****
	 * 发送https的post请求，request的content-type为application/x-www-formurlencode
	 * 返回值为string post请求传输String参数 例如：name=Jack&sex=1&type=2
	 * 
	 * @param params
	 * @param url
	 * @return
	 */
	public static String httpPost(String url, String param) {
		HttpPost httpPost = new HttpPost(url);
		String result = "";
		try {
			StringEntity entity = null;
			if (!StringUtils.isEmpty(param)) {
				logger.info("创建请求httpPost-URL={},params={}", param);
				// 解决中文乱码问题
				entity = new StringEntity(param.toString(), "utf-8");
				entity.setContentEncoding("UTF-8");
				entity.setContentType("application/x-www-form-urlencoded");
				httpPost.setEntity(entity);
			}
			httpClient = HttpsClientUtils.createSSLClientDefault();
			httpResponse = httpClient.execute(httpPost);
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				try {
					// 读取服务器返回过来的json字符串数据
					result = EntityUtils.toString(httpResponse.getEntity(), "utf-8");
				} catch (Exception e) {
					logger.error("post请求提交失败:" + url, e);
				}
			}
		} catch (Exception e) {
			logger.error("post请求提交失败:" + url, e);
		} finally {
			try {
				httpResponse.close();
				httpClient.close();
				httpPost.releaseConnection();
				logger.info("请求流关闭完成");
			} catch (IOException e) {
				logger.info("请求流关闭出错");
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 发送get请求,返回string类型
	 * 
	 * @param url
	 *            路径
	 * @return
	 */
	public static String httpGet(String url) {
		// get请求返回结果
		String result = null;
		// 发送get请求
		HttpGet request = new HttpGet(url);
		try {
			httpClient = HttpsClientUtils.createSSLClientDefault();
			httpResponse = httpClient.execute(request);

			// 请求发送成功，并得到响应
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				// 读取服务器返回过来的json字符串数据
				HttpEntity entity = httpResponse.getEntity();
				result = EntityUtils.toString(entity, "utf-8");

			} else {
				logger.error("get请求提交失败:" + url);
			}
		} catch (IOException e) {
			logger.error("get请求提交失败:" + url, e);
		} finally {
			request.releaseConnection();
		}
		return result;
	}

	public static void main(String[] args) throws Exception {
		System.out.println(HttpsClientUtils.httpGet("https://58.59.9.251/nykjcgzyzhxxw/qiyejigoutuijiexinxichaxun?iw-apikey=123&iw-cmd=qiyejigoutuijiexinxichaxun"));
	}
}