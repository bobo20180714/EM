/**   
 * @Title: LoginControler.java
 * @Package org.sdyy.yee.ac.ac_operator.controler
 * @Description: TODO(用一句话描述该文件做什么)
 * @author caojian   2013-5-21 下午1:53:20
 */
package com.em.core.auth.operator.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.em.common.consts.CommonConsts;
import com.em.common.consts.GlobMessageKeys;
import com.em.common.controller.BaseController;
import com.em.common.retobj.RetObj;
import com.em.common.service.ConfigService;
import com.em.common.utils.RSAUtil;
import com.em.common.utils.StringUtils;
import com.em.core.auth.function.service.IAuthFunctionService;
import com.em.core.auth.home.module.service.IAuthHomeModuleService;
import com.em.core.auth.menu.custom.entity.AuthMenuCustom;
import com.em.core.auth.menu.custom.service.IAuthMenuCustomService;
import com.em.core.auth.menu.service.IAuthMenuService;
import com.em.core.auth.operator.entity.AuthOperator;
import com.em.core.auth.operator.service.IAuthOperatorService;
import com.em.core.org.employee.entity.OrgEmployee;
import com.em.core.org.employee.service.IOrgEmployeeService;

/**
 * @ClassName: LoginControler
 * @Description: 控制登录相关
 * @author caojian 2015年8月21日18:33:44
 */
@Controller
public class LoginController extends BaseController {
	@Autowired
	private IAuthOperatorService authOperatorService;
	@Autowired
	private IOrgEmployeeService orgEmployeeService;

	@Autowired
	private IAuthMenuService authMenuService;
	@Autowired
	private IAuthFunctionService authFunctionService;
	@Autowired
	private IAuthHomeModuleService authHomeModuleService;
	@Autowired
	private IAuthMenuCustomService authCustomMenuService;

	@RequestMapping("/loginCheck")
	@ResponseBody
	public RetObj loginCheck(HttpServletRequest request, HttpServletResponse response, String userId, String password, Boolean remember, String validCode) {

		// 先注销
		HttpSession session = request.getSession();
		/* ServletContext application =session.getServletContext(); */
		AuthOperator operator = null;
		try {
			/*String pwd;
			System.out.println("原文加密后为：");
			System.out.println(password);
			// byte[] en_result = new BigInteger(password, 16).toByteArray();
			byte[] en_result = hexStringToBytes(password);
			// System.out.println("转成byte[]" + new String(en_result));
			byte[] de_result = RSAUtil.decrypt(RSAUtil.getKeyPair().getPrivate(), en_result);
			System.out.println("还原密文：");
			System.out.println(new String(de_result));
			StringBuffer sb = new StringBuffer();
			sb.append(new String(de_result));
			pwd = sb.reverse().toString();
			System.out.println(sb);
			System.out.println("=================================");
			pwd = URLDecoder.decode(pwd, "UTF-8");//
			System.out.println(pwd);*/

			operator = authOperatorService.checkLogin(userId, password);
			String checkcode = validCode;
			String s_checkcode = (String) request.getSession().getAttribute("validateCode");

			if (operator == null) {
				return new RetObj(false, GlobMessageKeys.LOGIN_WRONG_INFO, request, null);
			} else if (!CommonConsts.OPERATOR_STATUS_NORMAL.equals(operator.getStatus())) {// 判断只有在岗的用户才可以登录
				return new RetObj(false, GlobMessageKeys.LOGIN_NO_PERMISSION, request, null);
			} else if (validCode != null && checkcode != null && !(validCode.equalsIgnoreCase("hasCookie")) && !(checkcode.equals(s_checkcode))) {
				return new RetObj(false, GlobMessageKeys.VALIDATE_CODE_ERR, request, null);
			} else {
				/** 保存用户session信息 */
				// 账号登录信息
				session.setAttribute(LOGIN_IN_OPERATOR_SESSION, operator);
				session.setAttribute(LOGIN_IN_OPERATOR_ORIGINAL_PASSWORD, password);
				// //对应EMP信息
				// Map paramMap = new HashMap();
				// String operatorId =
				// acOperatorMap.get("OPERATOR_ID").toString();
				// paramMap.put("OPERATOR_ID", operatorId);
				//
				// List employeeMapList = omEmployeeService.query(paramMap);
				// if(!CollectionUtils.isEmpty(employeeMapList)) {
				// session.setAttribute(LOGIN_IN_EMP_SESSION,
				// employeeMapList.get(0));
				// }
				// //拥有的资源
				// List permittedFuncionts =
				// acFunctionService.queryPermitted(paramMap);
				// session.setAttribute(LOGIN_IN_OPERATOR_RESOURCES_SESSION,
				// permittedFuncionts);

			}
		} catch (Exception e) {
			e.printStackTrace();
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
			return new RetObj(false, GlobMessageKeys.LOGIN_SYS_ERR, request, "");
		}
		/*
		 * String layout="layout7"; session.setAttribute("layout", layout);
		 */
		String projectName = request.getServletContext().getContextPath();
		if (remember) {// 保存Cookie,有效期7天
			Cookie cookieuser = new Cookie("user", userId + "-" + password);
			// 设定有效时间 以秒(s)为单位
			cookieuser.setMaxAge(60 * 60 * 24 * 7);
			// 设置Cookie路径和域名
			cookieuser.setPath(projectName);
			response.addCookie(cookieuser);
			// 若记住密码未勾选则默认这次取消记住密码，将原本存密码的Cookies生存期设为现在，则会自动销毁
		} else {
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie coo : cookies) {
					if (coo.getName().equals("user")) {
						coo.setMaxAge(0);
						coo.setPath(projectName);
						response.addCookie(coo);
					}
				}
			}

		}

		return new RetObj(true, "");
	}

	private void reloadSession(HttpServletRequest request) throws IOException {
		HttpSession session = request.getSession();
		// 账号登录信息
		AuthOperator operator = (AuthOperator) session.getAttribute(LOGIN_IN_OPERATOR_SESSION);
		// 对应EMP信息
		Map paramMap = new HashMap();
		String operatorId = operator.getOperatorId();
		paramMap.put("OPERATOR_ID", operatorId);

		List<OrgEmployee> employeeMapList = orgEmployeeService.queryByMap(paramMap);
		if (!CollectionUtils.isEmpty(employeeMapList)) {
			session.setAttribute(LOGIN_IN_EMP_SESSION, employeeMapList.get(0));
		}
		// 拥有的资源

		// 超级管理员特别处理
		String userId = getUserIdFromRequest(request);
		if ("superadmin".equals(userId)) {
			paramMap.put("OPERATOR_ID", userId);
		}

		List permittedFuncionts = authFunctionService.queryPermitted(paramMap);
		session.setAttribute(LOGIN_IN_OPERATOR_RESOURCES_SESSION, permittedFuncionts);
		// 设置主题
		session.setAttribute(LOGIN_IN_OPERATOR_LAYOUT, ConfigService.getConfigRealTime(LOGIN_IN_OPERATOR_LAYOUT));
	}

	@RequestMapping("/login")
	public String login(HttpSession session) {
		if (session != null) {
			session.removeAttribute(LOGIN_IN_OPERATOR_SESSION);
			session.removeAttribute(LOGIN_IN_EMP_SESSION);
			session.removeAttribute(LOGIN_IN_OPERATOR_RESOURCES_SESSION);
			session.invalidate();
		}
		return "login";
	}

	@RequestMapping("/test")
	public String test(HttpServletRequest request, HttpSession session) throws IOException {
		String layout = (String) session.getAttribute(LOGIN_IN_OPERATOR_LAYOUT);
		return "admin/" + layout + "/test";
	}

	@RequestMapping("/testUeditorByModal")
	public String testUeditorByModal(HttpServletRequest request, HttpSession session) throws IOException {
		String layout = (String) session.getAttribute(LOGIN_IN_OPERATOR_LAYOUT);
		return "admin/" + layout + "/testUeditorByModal";
	}

	@RequestMapping("/test1")
	public String test1(HttpServletRequest request, HttpSession session) throws IOException {
		String layout = (String) session.getAttribute(LOGIN_IN_OPERATOR_LAYOUT);
		return "admin/" + layout + "/test1";
	}

	@RequestMapping("/testAjax")
	@ResponseBody
	public RetObj testAjax() {
		return new RetObj(true, "成功");
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping("/main")
	public String main(HttpServletRequest request, HttpSession session) throws IOException {
		reloadSession(request);
		String layout = (String) session.getAttribute(LOGIN_IN_OPERATOR_LAYOUT);

		/* 此处或可增加layout判断，根据不同的layout生成不同的数据,抽出方法 */
		// 获取可访问菜单
		Map paramMap = new HashMap();
		String operatorId = getOperatorIdFromRequest(request);
		paramMap.put("OPERATOR_ID", operatorId);

		// 获取密码作为参数进行单点登录
		String originalPassword = session.getAttribute(LOGIN_IN_OPERATOR_ORIGINAL_PASSWORD).toString();

		// 超级管理员特别处理
		String userId = getUserIdFromRequest(request);
		if ("superadmin".equals(userId)) {
			paramMap.put("OPERATOR_ID", userId);
		}
		// paramMap.put("APP_ID", appId);
		// 此处可有APP_ID获取，设为查询参数
		List<Map> permMenuList = authMenuService.queryPermitted(paramMap);
		request.setAttribute("permMenuList", permMenuList);

		List<Map> permMenuListLevel1 = new ArrayList<Map>();
		List<Map> permMenuListLevel2 = new ArrayList<Map>();
		List<Map> permMenuListLevel3 = new ArrayList<Map>();
		List<Map> permMenuListLevel4 = new ArrayList<Map>();
		List<AuthMenuCustom> permCustomMenuListLevel1 = new ArrayList<AuthMenuCustom>();
		List<AuthMenuCustom> permCustomMenuListLevel2 = new ArrayList<AuthMenuCustom>();
		String menuLevel = "-1";
		for (Map menuRecord : permMenuList) {
			// 参数中加入用户名与密码进行单点登录(OPEN_MODE为1代表单点登录的情况)
			String openMode = StringUtils.getTrimStr(menuRecord.get("OPEN_MODE"));
			String para = StringUtils.getTrimStr(menuRecord.get("PARAMETER"));
			if (!para.isEmpty() && para != "" && ("1".equals(openMode))) {
				// OPEN_MODE为1是单点登录的打开方式，接下来参数拼接用户名密码
				para = "&USER_ID=" + userId + "&PASSWORD=" + originalPassword;
				menuRecord.put("PARAMETER", para);
			} else if (!para.isEmpty() && para != "" && !("1".equals(openMode))) {
				menuRecord.put("PARAMETER", para);
			}
			menuLevel = StringUtils.getTrimStr(menuRecord.get("MENU_LEVEL"));
			if ("1".equals(menuLevel)) {
				permMenuListLevel1.add(menuRecord);
			} else if ("2".equals(menuLevel)) {
				permMenuListLevel2.add(menuRecord);
			} else if ("3".equals(menuLevel)) {
				permMenuListLevel3.add(menuRecord);
			} else if ("4".equals(menuLevel)) {
				permMenuListLevel4.add(menuRecord);
			}
		}
		request.setAttribute("permMenuListLevel1", permMenuListLevel1);
		request.setAttribute("permMenuListLevel2", permMenuListLevel2);
		request.setAttribute("permMenuListLevel3", permMenuListLevel3);
		request.setAttribute("permMenuListLevel4", permMenuListLevel4);

		// 首页模块
		initHomeModules(request);

		// 自定义菜单模块
		// 获取所有状态为'1'的
		Map customMenuMap = new HashMap();
		customMenuMap.put("OPERATOR_ID", operatorId);
		customMenuMap.put("IS_USE", "1");
		List<AuthMenuCustom> acCustomMenuList = authCustomMenuService.queryAuthMenuCustomByMap(customMenuMap);
		for (int i = 0; i < acCustomMenuList.size(); i++) {
			AuthMenuCustom acCutomMenuMap = acCustomMenuList.get(i);
			String menuOperatorCustomMenu = (String) acCutomMenuMap.getOrgMenuOperatorCustomCode();
			AuthMenuCustom temp = new AuthMenuCustom();
			temp.setOrgMenuOperatorCustomCode(menuOperatorCustomMenu);
			permCustomMenuListLevel1.add(temp);
			String menuIds = acCutomMenuMap.getMenuIds();
			// 转换String为List of Map
			if (!StringUtils.isEmpty(menuIds)) {
				if (menuIds.contains(",")) {
					String[] text = menuIds.split(",");
					for (String str : text) {
						AuthMenuCustom menucustom = authCustomMenuService.getAuthMenuCustom(str);
						menucustom.setMenuOperatorCustomCode(menuOperatorCustomMenu);
						permCustomMenuListLevel2.add(menucustom);
					}
				} else {
					AuthMenuCustom menucustom = authCustomMenuService.getAuthMenuCustom(menuOperatorCustomMenu);
					menucustom.setMenuOperatorCustomCode(menuOperatorCustomMenu);
					permCustomMenuListLevel2.add(menucustom);
				}
			}
		}
		request.setAttribute("permCustomMenuListLevel1", permCustomMenuListLevel1);
		request.setAttribute("permCustomMenuListLevel2", permCustomMenuListLevel2);

		return "admin/" + layout + "/main";
	}

	@RequestMapping("/rightTemplateForIFrame")
	public String rightTemplateForIFrame(HttpServletRequest request, HttpSession session) throws IOException {
		String layout = (String) session.getAttribute(LOGIN_IN_OPERATOR_LAYOUT);
		return "admin/" + layout + "/rightTemplateForIFrame";
	}

	@RequestMapping("/home")
	public String home(HttpServletRequest request, HttpSession session) {
		String layout = (String) session.getAttribute(LOGIN_IN_OPERATOR_LAYOUT);
		// 首页模块
		initHomeModules(request);
		return "admin/" + layout + "/home";
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/top")
	public String top(HttpServletRequest request) {
		Map paramMap = new HashMap();
		paramMap.put("MENU_LEVEL", "1");

		String operatorId = getOperatorIdFromRequest(request);
		paramMap.put("OPERATOR_ID", operatorId);
		// paramMap.put("APP_ID", appId);
		// 此处应有APP_ID获取，设为查询参数

		List<Map> topMenuList = authMenuService.queryPermitted(paramMap);
		request.setAttribute("topMenuList", topMenuList);
		return "top";
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping("/left")
	public String left(HttpServletRequest request, String pId, String pName) {

		// request=HttpUtils.getCurrentRequest();

		Map paramMap = new HashMap();
		paramMap.put("PARENT_ID", pId);
		String operatorId = getOperatorIdFromRequest(request);
		paramMap.put("OPERATOR_ID", operatorId);
		// 此处应有APP_ID获取，设为查询参数
		if (!StringUtils.isEmpty(pId)) {
			List<Map> leftMenuList = authMenuService.queryPermitted(paramMap);
			request.setAttribute("leftMenuList", leftMenuList);
			request.setAttribute("pName", pName);
		} else {
			request.setAttribute("pName", "首页");
		}
		HttpSession session = request.getSession();
		String layout = (String) session.getAttribute(LOGIN_IN_OPERATOR_LAYOUT);
		return "admin/" + layout + "/left";
	}

	/* @RequestMapping("/homeBak") */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void initHomeModules(HttpServletRequest request) {
		String operatorId = getOperatorIdFromRequest(request);
		Map paramMap = new HashMap();
		paramMap.put("OPERATOR_ID", operatorId);

		// 1、获取自定义列表
		List<Map> operHomeModuleViewList = authHomeModuleService.queryOperHomeModule(paramMap);
		List<Map> allPermHomeModuleViewList = new ArrayList<Map>();
		if (CollectionUtils.isEmpty(operHomeModuleViewList)) {
			operHomeModuleViewList = authHomeModuleService.queryOperRoleHomeModule(paramMap);
			allPermHomeModuleViewList = new ArrayList<Map>(operHomeModuleViewList);
			// Collections.copy(allPermHomeModuleViewList,
			// operHomeModuleViewList);
			for (Map record : allPermHomeModuleViewList) {
				record.put("CHECKED", "true");
			}
		} else {
			allPermHomeModuleViewList = authHomeModuleService.queryOperRoleHomeModule(paramMap);
			for (Map recorda : allPermHomeModuleViewList) {
				for (Map recordo : operHomeModuleViewList) {
					if (recordo.get("HOME_MODULE_ID").equals(recorda.get("HOME_MODULE_ID"))) {
						recorda.put("CHECKED", "true");
						break;
					}
				}

			}
		}
		request.setAttribute("operHomeModuleViewList", operHomeModuleViewList);
		request.setAttribute("allPermHomeModuleViewList", allPermHomeModuleViewList);
		// 2、编辑时 获取权限列表 已设置check

	}

	@RequestMapping("/validateImg")
	public void validateImg(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("image/png");
		HttpSession session = request.getSession(true);
		String v = "";
		OutputStream output = response.getOutputStream();
		;
		// FileInputStream in = new FileInputStream(new File());
		try {
			v = RandomGraphic.createInstance(4).drawNumber(RandomGraphic.GRAPHIC_PNG, output);
			session.setAttribute("validateCode", v);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			output.flush();
			output.close();
		}

	}

	@RequestMapping("/right")
	public String right() {
		return "right";
	}

	@RequestMapping("/index")
	public String index() {
		return "index";
	}

	@RequestMapping("/footer")
	public String footer() {
		return "footer";
	}

	@RequestMapping("/sessionTimeOut")
	public String sessionTimeOut(HttpServletRequest request) {
		return "sessionTimeOut";
	}

	@RequestMapping("/noPermission")
	public String noPermission(HttpServletRequest request) {
		return "noPermission";
	}

	public static byte[] hexStringToBytes(String hexString) {
		if (hexString == null || hexString.equals("")) {
			return null;
		}
		hexString = hexString.toUpperCase();
		int length = hexString.length() / 2;
		char[] hexChars = hexString.toCharArray();
		byte[] d = new byte[length];
		for (int i = 0; i < length; i++) {
			int pos = i * 2;
			d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
		}
		return d;
	}

	/**
	 * Convert char to byte
	 * 
	 * @param c
	 *            char
	 * @return byte
	 */
	private static byte charToByte(char c) {
		return (byte) "0123456789ABCDEF".indexOf(c);
	}
	
	/***
	 * test upload
	 */
	@RequestMapping(value = "testupload", method = RequestMethod.POST)
	public void testupload(HttpServletRequest request, HttpServletResponse response, MultipartFile file) {
		String savePath = "d:/";//存储路径 
	      String retMsg = "";//定义将返回给客户端的信息 
	      HttpServletRequest req = (HttpServletRequest)request;
	      try { 
	        if (true) { 
	          List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(req); 
	          for (FileItem item : items) { 
	            if (!item.isFormField()) {// 过滤掉表单中非文件域 
	              String fileType = item.getName().substring(item.getName().lastIndexOf(".") + 1).toLowerCase();//文件类型 
	              String fileName = new Date().getTime() + "." + fileType; //保存的文件名 
	              String filePath = savePath + "\\" + fileName; //保存的文件路径 
	              BufferedInputStream in = new BufferedInputStream(item.getInputStream());// 获得文件输入流 
	              BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(filePath)));// 获得文件输出流 
	              org.apache.commons.fileupload.util.Streams.copy(in, out, true);// 开始把文件写到指定的上传文件夹 
	              retMsg += "上传文件成功！"; 
	              in.close(); 
	              out.close(); 
	            }  
	          } 
	        } 
	        response.setContentType("text/html;charset=utf8"); 
	        PrintWriter pw = response.getWriter(); 
	        pw.print(retMsg); 
	        pw.flush(); 
	        pw.close(); 
	        //根据自己需要返回页面一个 retMs
	      } catch (Exception e) { 
	        e.printStackTrace(); 
	      }
	}

}
