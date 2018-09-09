package com.pms.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 权限配置
 * @author Taowd
 * @version 2018年9月7日
 * @see PermissionInterceptor
 */
public class PermissionInterceptor extends HandlerInterceptorAdapter {

	// private static final String LOGIN_URL = "/PMS/login";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o)
			throws Exception {
		System.out.println("preHandle...");
		// 这里可以根据session的用户来判断角色的权限，根据权限来转发不同的页面
		if (request.getSession().getAttribute("user") == null) {
			// request.getRequestDispatcher("index.jsp").forward(request, response);
			// System.out.println(request.getContextPath() + "/index");
			// response.sendRedirect(request.getContextPath() + "/");
			return true;
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView)
			throws Exception {
		System.out.println("postHandle...");
	}

	@Override
	public void afterCompletion(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
		System.out.println("afterCompletion...");
	}
}
