package com.em.common.filters;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.web.util.matcher.RegexRequestMatcher;

import com.em.common.utils.StringUtils;

public class CsrfFilter implements Filter {
	private Pattern allowedMethods = Pattern.compile("^(GET|HEAD|TRACE|OPTIONS)$");
	private RegexRequestMatcher unprotectedMatcher = new RegexRequestMatcher("^/rest/.*", null);
	//需要添加token的地方太多，所以对于基础功能，直接放入白名单
	public static final String[] noFilters = new String[] { "/login", "/loginCheck", "/sessionTimeOut", "/logout", "/validateImg", "/authApplication","/authCustomMenu","/authFuncGroup","/authFunction","/authHomeModule","/authMenu","/authOperFunc","/authOperatorRole"
		,"/authOperator","/authRoleFunc","/authRole","/orgEmpGroup","/orgEmployee","/orgGroup","/orgOrganization","/orgPosition","/sysDict","/sysFile","/sysLog","/sysUtils","/sysJobTask", "/druid", "/testupload"};

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession s = req.getSession();

		// 从 session 中得到 csrftoken 属性
		String sToken = (String) s.getAttribute("csrftoken");
		if (sToken == null) {

			// 产生新的 token 放入 session 中
			sToken = generateToken();
			s.setAttribute("csrftoken", sToken);
			chain.doFilter(request, response);
		} else {
			if (unprotectedMatcher.matches(req)) {
				chain.doFilter(request, response);
			} else if (isWhiteReq(req.getRequestURI(), req.getContextPath())) {
				chain.doFilter(request, response);
			} else if (allowedMethods.matcher(req.getMethod()).matches()) {
				chain.doFilter(request, response);
			} else {
				// 从 HTTP 头中取得 csrftoken
				String xhrToken = req.getHeader("csrftoken");

				// 从请求参数中取得 csrftoken
				String pToken = req.getParameter("csrftoken");
				if (sToken != null && xhrToken != null && sToken.equals(xhrToken)) {
					chain.doFilter(request, response);
				} else if (sToken != null && pToken != null && sToken.equals(pToken)) {
					chain.doFilter(request, response);
				} else {
					String a = req.getContextPath();
					String basePath = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + a;
					res.sendRedirect(basePath + "/noPermission.do");
					// req.getRequestDispatcher(basePath +
					// "/noPermission.do").forward(request, response);
				}
			}

		}
	}

	private String generateToken() {
		String token = StringUtils.uniqueKey();
		return token;
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		//读取配置文件
	}

	/*
	 * 判断是否是白名单
	 */
	private boolean isWhiteReq(String uri, String path) {
		boolean pass = false;
		for (int i = 0; i < noFilters.length; i++) {
			if (uri.startsWith(path + noFilters[i])) {
				pass = true;
				break;
			}
		}

		return pass;
	}
}