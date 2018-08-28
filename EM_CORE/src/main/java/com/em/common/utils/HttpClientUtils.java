package com.em.common.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

/**
 * HttpClient4.3工具类 抛弃3.x namevaluepair设置参数的方式，改用4.X setentity设置参数方式
 * 
 * @author tech
 * @date 2016-03-29
 * 
 */
public class HttpClientUtils {
	private static Logger logger = LoggerFactory.getLogger(HttpClientUtils.class); // 日志记录
	static CloseableHttpClient httpClient;
	static CloseableHttpResponse httpResponse;

	/**
	 * post请求传输json参数
	 * 
	 * @param url
	 *            url地址
	 * @param json
	 *            参数
	 * @return
	 */
	public static JSONObject httpPost(String url, JSONObject jsonParam) {
		// post请求返回结果
		JSONObject jsonResult = null;
		HttpPost httpPost = new HttpPost(url);
		try {
			httpClient = HttpClients.createDefault();
			if (null != jsonParam) {
				// 解决中文乱码问题
				StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");
				entity.setContentEncoding("UTF-8");
				entity.setContentType("application/json");
				httpPost.setEntity(entity);
			}
			httpResponse = httpClient.execute(httpPost);
			// 请求发送成功，并得到响应
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				String str = "";
				try {
					// 读取服务器返回过来的json字符串数据
					str = EntityUtils.toString(httpResponse.getEntity(), "utf-8");
					// 把json字符串转换成json对象
					jsonResult = JSONObject.parseObject(str);
				} catch (Exception e) {
					logger.error("post请求提交失败:" + url, e);
				}
			}
		} catch (IOException e) {
			logger.error("post请求提交失败:" + url, e);
		} finally {
			httpPost.releaseConnection();
		}
		return jsonResult;
	}

	/**
	 * post请求传输json参数 返回string
	 * 
	 * @param url
	 *            url地址
	 * @param json
	 *            参数
	 * @return
	 */
	public static String httpPost_s(String url, JSONObject jsonParam) {
		// post请求返回结果
		String stringResult = null;
		HttpPost httpPost = new HttpPost(url);
		try {
			httpClient = HttpClients.createDefault();
			if (null != jsonParam) {
				// 解决中文乱码问题
				StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");
				entity.setContentEncoding("UTF-8");
				entity.setContentType("application/json");
				httpPost.setEntity(entity);
			}
			httpResponse = httpClient.execute(httpPost);
			// 请求发送成功，并得到响应
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				String str = "";
				try {
					// 读取服务器返回过来的json字符串数据
					stringResult = EntityUtils.toString(httpResponse.getEntity(), "utf-8");

				} catch (Exception e) {
					logger.error("post请求提交失败:" + url, e);
				}
			}
		} catch (IOException e) {
			logger.error("post请求提交失败:" + url, e);
		} finally {
			httpPost.releaseConnection();
		}
		return stringResult;
	}

	/**
	 * post请求传输String参数 例如：name=Jack&sex=1&type=2
	 * Content-type:application/x-www-form-urlencoded
	 * 
	 * @param url
	 *            url地址
	 * @param strParam
	 *            参数
	 * @param noNeedResponse
	 *            不需要返回结果
	 * @return
	 */
	public static JSONObject httpPost(String url, String strParam) {
		// post请求返回结果
		JSONObject jsonResult = null;
		HttpPost httpPost = new HttpPost(url);
		try {
			httpClient = HttpClients.createDefault();
			if (null != strParam) {
				// 解决中文乱码问题
				StringEntity entity = new StringEntity(strParam, "utf-8");
				entity.setContentEncoding("UTF-8");
				entity.setContentType("application/x-www-form-urlencoded");
				httpPost.setEntity(entity);
			}
			httpResponse = httpClient.execute(httpPost);
			// 请求发送成功，并得到响应
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				String str = "";
				try {
					// 读取服务器返回过来的json字符串数据
					str = EntityUtils.toString(httpResponse.getEntity(), "utf-8");
					// 把json字符串转换成json对象
					jsonResult = JSONObject.parseObject(str);
				} catch (Exception e) {
					logger.error("post请求提交失败:" + url, e);
				}
			}
		} catch (IOException e) {
			logger.error("post请求提交失败:" + url, e);
		} finally {
			httpPost.releaseConnection();
		}
		return jsonResult;
	}

	/**
	 * post请求传输String参数 例如：name=Jack&sex=1&type=2
	 * Content-type:application/x-www-form-urlencoded 返回结果为string
	 * 
	 * @param url
	 *            url地址
	 * @param strParam
	 *            参数
	 * @param noNeedResponse
	 *            不需要返回结果
	 * @return
	 */
	public static String httpPost_s(String url, String strParam) {
		// post请求返回结果
		String stringResult = null;
		HttpPost httpPost = new HttpPost(url);
		try {
			httpClient = HttpClients.createDefault();
			if (null != strParam) {
				// 解决中文乱码问题
				StringEntity entity = new StringEntity(strParam, "utf-8");
				entity.setContentEncoding("UTF-8");
				entity.setContentType("application/x-www-form-urlencoded");
				httpPost.setEntity(entity);
			}
			httpResponse = httpClient.execute(httpPost);
			// 请求发送成功，并得到响应
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				String str = "";
				try {
					// 读取服务器返回过来的json字符串数据
					stringResult = EntityUtils.toString(httpResponse.getEntity(), "utf-8");

				} catch (Exception e) {
					logger.error("post请求提交失败:" + url, e);
				}
			}
		} catch (IOException e) {
			logger.error("post请求提交失败:" + url, e);
		} finally {
			httpPost.releaseConnection();
		}
		return stringResult;
	}

	/**
	 * 发送get请求
	 * 
	 * @param url
	 *            路径
	 * @return
	 */
	public static JSONObject httpGet(String url) {
		// get请求返回结果
		JSONObject jsonResult = null;
		// 发送get请求
		HttpGet request = new HttpGet(url);
		try {
			httpClient = HttpClients.createDefault();
			httpResponse = httpClient.execute(request);

			// 请求发送成功，并得到响应
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				// 读取服务器返回过来的json字符串数据
				HttpEntity entity = httpResponse.getEntity();
				String strResult = EntityUtils.toString(entity, "utf-8");
				// 把json字符串转换成json对象
				jsonResult = JSONObject.parseObject(strResult);
			} else {
				logger.error("get请求提交失败:" + url);
			}
		} catch (IOException e) {
			logger.error("get请求提交失败:" + url, e);
		} finally {
			request.releaseConnection();
		}
		return jsonResult;
	}

	/**
	 * 发送get请求,返回string类型
	 * 
	 * @param url
	 *            路径
	 * @return
	 */
	public static String httpGet_s(String url) {
		// get请求返回结果
		String stringResult = null;
		// 发送get请求
		HttpGet request = new HttpGet(url);
		try {
			httpClient = HttpClients.createDefault();
			httpResponse = httpClient.execute(request);

			// 请求发送成功，并得到响应
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				// 读取服务器返回过来的json字符串数据
				HttpEntity entity = httpResponse.getEntity();
				stringResult = EntityUtils.toString(entity, "utf-8");

			} else {
				logger.error("get请求提交失败:" + url);
			}
		} catch (IOException e) {
			logger.error("get请求提交失败:" + url, e);
		} finally {
			request.releaseConnection();
		}
		return stringResult;
	}

	/**
	 * post请求传输xml参数 返回string 个性化需求
	 * 
	 * @param url
	 * 
	 * @param json
	 *            参数
	 * @return
	 */
	public static String httpPost_x_s(String url, String jsonParam) {
		// post请求返回结果
		String stringResult = null;
		HttpPost httpPost = new HttpPost(url);
		try {
			httpClient = HttpClients.createDefault();
			if (null != jsonParam) {
				// 解决中文乱码问题
				StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");
				entity.setContentEncoding("UTF-8");
				entity.setContentType("application/xml");
				httpPost.setEntity(entity);
			}
			httpResponse = httpClient.execute(httpPost);
			// 请求发送成功，并得到响应
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				String str = "";
				try {
					// 读取服务器返回过来的json字符串数据
					stringResult = EntityUtils.toString(httpResponse.getEntity(), "utf-8");

				} catch (Exception e) {
					logger.error("post请求提交失败:" + url, e);
				}
			}
		} catch (IOException e) {
			logger.error("post请求提交失败:" + url, e);
		} finally {
			httpPost.releaseConnection();
		}
		return stringResult;
	}

	/****
	 * 个性化需求，用于请求地址为下载文件流类型的
	 * 
	 * @param url
	 * @return
	 */
	public static String httpClient_download(String url) {
		try {
			httpClient = HttpClients.createDefault();
			HttpGet httpget = new HttpGet(url);
			httpResponse = httpClient.execute(httpget);

			HttpEntity entity = httpResponse.getEntity();
			InputStream is = entity.getContent();

			File file = new File(getFilePath(httpResponse));
			file.getParentFile().mkdirs();
			FileOutputStream fileout = new FileOutputStream(file);
			/**
			 * 根据实际运行效果 设置缓冲区大小
			 */
			byte[] buffer = new byte[10240];
			int ch = 0;
			while ((ch = is.read(buffer)) != -1) {
				fileout.write(buffer, 0, ch);
			}
			is.close();
			fileout.flush();
			fileout.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		// TODO 测试代码
		JSONObject json = httpGet("http://api.map.baidu.com/telematics/v3/weather?location=%E5%98%89%E5%85%B4&output=json&ak=5slgyqGDENN7Sy7pw29IUvrZ");
		System.out.println(json);
	}

	public static String getFilePath(HttpResponse response) {
		String filepath = "d:/";
		String filename = getFileName(response);

		if (filename != null) {
			filepath += filename;
		}
		return filepath;
	}

	/**
	 * 获取response header中Content-Disposition中的filename值
	 * 
	 * @param response
	 * @return
	 */
	public static String getFileName(HttpResponse response) {
		Header contentHeader = response.getFirstHeader("Content-Disposition");
		String filename = null;
		if (contentHeader != null) {
			HeaderElement[] values = contentHeader.getElements();
			if (values.length == 1) {
				NameValuePair param = values[0].getParameterByName("filename");
				if (param != null) {
					try {
						// filename = new
						// String(param.getValue().toString().getBytes(),
						// "utf-8");
						// filename=URLDecoder.decode(param.getValue(),"utf-8");
						filename = param.getValue();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return filename;
	}
}