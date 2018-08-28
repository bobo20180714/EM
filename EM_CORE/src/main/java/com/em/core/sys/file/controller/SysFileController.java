/**
 * Copyright2015-2019 em All Rights Reserved.
 */
package com.em.core.sys.file.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.em.common.retobj.RetObj;
import com.em.common.service.ConfigService;
import com.em.common.utils.DateUtils;
import com.em.common.utils.StringUtils;
import com.em.core.sys.file.entity.SysFile;
import com.em.core.sys.file.service.ISysFileService;

/**
 * @ClassName: SysFileController
 * @Description: 文件管理：上传、下载、文件表操作
 * @author: liuyx
 * @date: 2015年10月10日上午8:50:22
 */
@Controller
@RequestMapping("/sysFile")
public class SysFileController {
	public final Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private ISysFileService sysFileService;

	@PostConstruct
	public void init() throws ServletException {
		String tempDir = ConfigService.getConfig(ConfigService.TMP_DIR);
		String uploadDir = ConfigService.getConfig(ConfigService.UPLOAD_DIR);
		if (!new File(tempDir).exists()) {
			new File(tempDir).mkdirs();
		}
		if (!new File(uploadDir).exists()) {
			new File(uploadDir).mkdirs();
		}
	}

	@RequestMapping("/forDemo")
	public String forDemo() {
		return "base/sys/sys_file/sys_file_forDemo";
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @param chunk分割块数
	 * @param chunks总分割数
	 * @param uuid分割时防止几个人同时上传相同文件名的文件
	 * 
	 * @return
	 */
	@RequestMapping(value = "batchUpload", method = RequestMethod.POST)
	public void batchUpload(HttpServletRequest request, HttpServletResponse response, @RequestParam ArrayList<MultipartFile> multipartFiles, Integer chunk, Integer chunks, String uuid) {

		RetObj ret = new RetObj();
		String name = null;// 文件名
		try {
			String fileId = null;
			int size = 0;
			String newFileName = null;
			String fileName = null;
			String refileName = "";
			String fileType = "";
			String now = DateUtils.getCurrentDate();
			String ymd = DateUtils.getCurrentDate("yyyyMMdd");
			String uploadDir = ConfigService.getConfig(ConfigService.UPLOAD_DIR) + "/" + ymd + "/";
			// 创建文件夹
			File file = new File(uploadDir);
			if (!file.exists()) {
				file.mkdirs();
			}
			List<Map> fileInfoMaps = new ArrayList<Map>();
			Map fileInfo = new HashMap();
			for (MultipartFile multipartFile : multipartFiles) {
				name = multipartFile.getOriginalFilename();
				fileType = FilenameUtils.getExtension(name);

				fileId = UUID.randomUUID().toString().replace("-", "");
				newFileName = fileId.concat(".").concat(fileType);
				String nFname = newFileName;
				if (chunk != null) {
					nFname = uuid + "_" + chunk + "_" + name;
				}
				File savedFile = new File(uploadDir, nFname);

				multipartFile.transferTo(savedFile);
				// //判断上传文件类型是否符合要求
				// if(!StringUtils.equals("RAR2", FileUtil.getType(uploadDir +
				// nFname).toString())) {
				// savedFile.delete();
				// throw new Exception("文件类型不正确");
				// }
				if (chunk != null && chunk + 1 == chunks) {
					try (BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(new File(uploadDir, newFileName)));) {
						// 遍历文件合并
						for (int i = 0; i < chunks; i++) {
							File tempFile = new File(uploadDir, uuid + "_" + i + "_" + name);
							byte[] bytes = FileUtils.readFileToByteArray(tempFile);
							outputStream.write(bytes);
							outputStream.flush();
							tempFile.delete();
						}
						outputStream.flush();
					}
				}

				fileInfo = new HashMap();
				fileInfo.put("FILE_ID", fileId);
				fileInfo.put("MAIN_ID", null);
				fileInfo.put("FILE_NAME", name);
				fileInfo.put("FILE_REAL_NAME", newFileName);
				fileInfo.put("FILE_TYPE", fileType);
				fileInfo.put("FILE_LOCATION", uploadDir);
				fileInfo.put("UPLOAD_TIME", now);
				fileInfo.put("UPLOADER", null);
				fileInfo.put("UPDATE_TIME", null);
				fileInfo.put("UPDATER", null);
				fileInfo.put("INVALID_TIME", null);
				fileInfo.put("INVALIDER", null);
				fileInfo.put("STATUS", "0");
				fileInfo.put("REMARK", null);

				size = 0;

				try (FileInputStream inputStream = new FileInputStream(new File(ConfigService.getConfig(ConfigService.UPLOAD_DIR), newFileName));) {
					size = inputStream.available() / 1024;
				} catch (FileNotFoundException ex) {
					// 因文件分片此处可能报nofilefound异常 忽略
					// ex.printStackTrace();
				}

				fileInfo.put("SIZE", size);

				fileInfoMaps.add(fileInfo);

			}

			sysFileService.batchInsert(fileInfoMaps);

			ret = new RetObj(true, "操作成功", fileInfoMaps);
		} catch (Exception e) {
			e.printStackTrace();
			ret = new RetObj(false, "操作失败");
		}
		response.setHeader("Content-type", "application/json; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		try (PrintWriter pw = response.getWriter();) {

			String retJsonStr = JSON.toJSONString(ret);
			pw.write(retJsonStr);
			pw.flush();
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*****
	 * 判断文件类型是否符合要求
	 * 
	 * @return
	 */
	private boolean validFileType() {
		// TODO Auto-generated method stub
		return false;
	}

	@RequestMapping(value = "/downloadFile")
	public void downloadFile(HttpServletResponse response, HttpServletRequest request) {
		String fileId = request.getParameter("FILE_ID");
		SysFile sysfile;
		String fileLocation = "";
		String fileName = "";
		String fileRealName = "";
		try {
			sysfile = sysFileService.getSysFile(fileId);
			if (sysfile != null) {
				fileLocation = sysfile.getFileLocation();
//				fileName = fileMap.get("FILE_NAME").toString();
//				fileRealName = fileMap.get("FILE_REAL_NAME").toString();
				fileName = sysfile.getFileName();
				fileRealName = sysfile.getFileRealName();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 创建file对象
		File file = new File(fileLocation + fileRealName);
		try (OutputStream myout = response.getOutputStream(); FileInputStream fis = new FileInputStream(file); BufferedInputStream buff = new BufferedInputStream(fis);) {

			// 设置response的编码方式
			response.setContentType("application/x-msdownload");
			// 写明要下载的文件的大小
			response.setContentLength((int) file.length());
			// 解决中文乱码
			response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("gbk"), "iso-8859-1"));

			// 读出文件到i/o流

			byte[] b = new byte[1024];// 相当于我们的缓存
			long k = 0;// 该值用于计算当前实际下载了多少字节
			// 从response对象中得到输出流,准备下载
			// 开始循环下载
			while (k < file.length()) {

				int j = buff.read(b, 0, 1024);
				k += j;
				// 将b中的数据写到客户端的内存
				myout.write(b, 0, j);

			}
			// 将写入到客户端的内存的数据,刷新到磁盘
			myout.flush();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/getFile")
	public void getFileByType(HttpServletResponse response, HttpServletRequest request) {
		String fileId = request.getParameter("FILE_ID");
//		Map fileMap;
		SysFile sysFile;
		String fileLocation = "";
		String fileName = "";
		String fileRealName = "";
		String fileUrl = "";
		String fileLocWithName = "";
		try {
			sysFile = sysFileService.getSysFile(fileId);
			if (sysFile != null) {
				fileLocation = sysFile.getFileLocation();
				fileName = sysFile.getFileName();
				fileRealName = sysFile.getFileRealName();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fileLocWithName = fileLocation + fileRealName;
		// 创建file对象
		File file = new File(fileLocWithName);
		try (OutputStream myout = response.getOutputStream();// 读出文件到i/o流
				FileInputStream fis = new FileInputStream(file);
				BufferedInputStream buff = new BufferedInputStream(fis);) {

			fileUrl = "file://" + fileLocWithName;
			FileNameMap fileNameMap = URLConnection.getFileNameMap();
			String type = fileNameMap.getContentTypeFor(fileUrl);

			if (type == null) {
				try {
					type = Files.probeContentType(Paths.get(file.toURI()));
					response.setContentType(type);
				} catch (IOException e) {
					logger.error("文件MIME获取错误:" + fileLocWithName, e);
					// 设置response的编码方式
					response.setContentType("application/x-msdownload");
					// 写明要下载的文件的大小
					response.setContentLength((int) file.length());
					// 解决中文乱码
					response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("gbk"), "iso-8859-1"));
				}
			} else {
				// 设置response的编码方式
				response.setContentType(type);
			}

			byte[] b = new byte[1024];// 相当于我们的缓存
			long k = 0;// 该值用于计算当前实际下载了多少字节
			// 从response对象中得到输出流,准备下载
			// 开始循环下载
			while (k < file.length()) {

				int j = buff.read(b, 0, 1024);
				k += j;
				// 将b中的数据写到客户端的内存
				myout.write(b, 0, j);

			}
			// 将写入到客户端的内存的数据,刷新到磁盘
			myout.flush();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// ---------------------------软航上传相关内容start ----------------------------
	/**
	 * 
	 * @Title: ntkoUpload
	 * @author：刘宇祥
	 * @date：2016年10月17日上午11:36:56
	 * @Description: 软航office专用单文件上传 fileId 新建不用传，修改必须传 fileName
	 *               文件展示名，修改如果不改文件名可以不传,新增必须 fileType
	 *               文件后缀名，修改如果不改文件名后缀可以不传,新增必须
	 * @param request
	 * @param response
	 * @throws UnsupportedEncodingException
	 *             返回值 json格式的RetObj，如果正常返回则RetObj中的obj对象包含详细文件信息；flag为true，
	 *             如果异常返回则obj对象为null；flag为false
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "ntkoSingleUpload", method = RequestMethod.POST)
	public void ntkoSingleUpload(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		// 返回消息
		RetObj ret = new RetObj();
		// 文件ID
		String fileId = null;
		// 文件大小
		long size = 0;
		// 根据ID重命名文件后的新文件名
		String newFileName = null;
		// 原文件名
		String fileName = null;
		// 文件类型
		String fileType = "";
		String now = DateUtils.getCurrentDate();
		String ymd = DateUtils.getCurrentDate("yyyyMMdd");
		String uploadDir = ConfigService.getConfig(ConfigService.UPLOAD_DIR) + "/" + ymd + "/";
		String tempFileDir = ConfigService.getConfig(ConfigService.TMP_DIR);
		// 用于存放文件信息
		List fileInfoMaps = new ArrayList();
		// 创建文件夹
		File fileForDir = new File(uploadDir);
		if (!fileForDir.exists()) {
			fileForDir.mkdirs();
		}
		fileForDir = new File(tempFileDir);
		if (!fileForDir.exists()) {
			fileForDir.mkdirs();
		}
		response.setHeader("Content-type", "application/json; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		try (PrintWriter out = response.getWriter();) {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// 设置最多只允许在内存中存储的数据,单位:字节
			factory.setSizeThreshold(4096);
			// 设置一旦文件大小超过setSizeThreshold()的值时数据存放在硬盘的目录
			factory.setRepository(new File(tempFileDir));
			ServletFileUpload upload = new ServletFileUpload(factory);
			// 设置允许用户上传文件大小,单位:字节
			upload.setSizeMax(1024 * 1024 * 4);
			List fileItems = null;
			try {
				fileItems = upload.parseRequest(request);
			} catch (FileUploadException e) {
				e.printStackTrace();
				writeResponsePrintWriter(new RetObj(false, "文件超出4M"), out);
				return;
			}
			Iterator iter = fileItems.iterator();
			FileItem item = null;
			// 遍历所有属性
			while (iter.hasNext()) {
				item = (FileItem) iter.next();

				if (item.isFormField()) {
					// 如果是一个普通属性
					if (item.getFieldName().equalsIgnoreCase("fileId")) {
						fileId = item.getString().trim();
					}
					if (item.getFieldName().equalsIgnoreCase("fileName")) {
						fileName = item.getString("utf-8").trim();
					}
					if (item.getFieldName().equalsIgnoreCase("fileType")) {
						fileType = item.getString("utf-8").trim();
					}
				} else {
					// 如果是一个文件
					if (StringUtils.isEmpty(fileId)) {
						// 新增上传
						fileId = StringUtils.uniqueKey();
						newFileName = fileId.concat(".").concat(fileType);
						File savedFile = new File(uploadDir, newFileName);
						try {
							item.write(savedFile);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						// 文件信息插入数据库
						Map fileInfo = new HashMap();
						fileInfo.put("FILE_ID", fileId);
						fileInfo.put("MAIN_ID", null);
						fileInfo.put("FILE_NAME", fileName);
						fileInfo.put("FILE_REAL_NAME", newFileName);
						fileInfo.put("FILE_TYPE", fileType);
						fileInfo.put("FILE_LOCATION", uploadDir);
						fileInfo.put("UPLOAD_TIME", now);
						fileInfo.put("UPLOADER", null);
						fileInfo.put("UPDATE_TIME", null);
						fileInfo.put("UPDATER", null);
						fileInfo.put("INVALID_TIME", null);
						fileInfo.put("INVALIDER", null);
						fileInfo.put("STATUS", "0");
						fileInfo.put("REMARK", null);

						size = 0;

						try (FileInputStream inputStream = new FileInputStream(new File(ConfigService.getConfig(ConfigService.UPLOAD_DIR), newFileName));) {
							size = inputStream.available() / 1024;
						} catch (FileNotFoundException ex) {
							// 因文件分片此处可能报nofilefound异常 忽略
							// ex.printStackTrace();
						}
						fileInfo.put("SIZE", size);
						fileInfoMaps.add(fileInfo);
						sysFileService.batchInsert(fileInfoMaps);

					} else {
						// 修改上传
						SysFile fileInfo = sysFileService.getSysFile(fileId);
						String fileRealName = fileInfo.getFileRealName();
						File savedFile = new File(uploadDir, newFileName);
						// 先把原来的旧文件删除
						if (savedFile.exists()) {
							if (!savedFile.delete()) {
								if (!savedFile.delete()) {
									writeResponsePrintWriter(new RetObj(false, "原文件无法删除"), out);
									return;
								}
							} else {
								// 删除成功后上传新文件
								try {
									item.write(savedFile);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
									writeResponsePrintWriter(new RetObj(false, "新文件上传失败"), out);
									return;
								}

								// 更新数据库信息
								if (StringUtils.isNotEmpty(fileName)) {
									fileInfo.setFileName(fileName);
//									map.put("FILE_NAME", fileName);
								}
								if (StringUtils.isNotEmpty(fileType)) {
									fileInfo.setFileType(fileType);
//									map.put("FILE_TYPE", fileType);
								}
								size = 0;
								try (FileInputStream inputStream = new FileInputStream(new File(ConfigService.getConfig(ConfigService.UPLOAD_DIR), newFileName));) {
									size = inputStream.available() / 1024;
								} catch (FileNotFoundException ex) {
									// 因文件分片此处可能报nofilefound异常 忽略
									// ex.printStackTrace();
								}
//								map.put("SIZE", size);
								fileInfo.setSize(size);
								sysFileService.updateSysFile(fileInfo);
								fileInfoMaps.add(fileInfo);
							}
						}
					}
				}
			}
			writeResponsePrintWriter(new RetObj(true, "操作成功", fileInfoMaps), out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void writeResponsePrintWriter(RetObj ret, PrintWriter pw) {
		String retJsonStr = JSON.toJSONString(ret);
		pw.write(retJsonStr);
		pw.flush();
		pw.close();
	}
	// ---------------------------软航上传相关内容end ----------------------------
}
