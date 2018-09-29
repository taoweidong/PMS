package com.pms.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 权限配置
 * @author Taowd
 * @version 2018年9月7日
 * @see PermissionInterceptor
 */
public class PermissionInterceptor extends HandlerInterceptorAdapter {

	/**
	 * 日志工具.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(PermissionInterceptor.class);

	/**
	 * 业务处理器处理请求之前被调用。预处理，可以进行编码、安全控制等处理；
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o)
			throws Exception {

		String requestUri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String url = requestUri.substring(contextPath.length());

		LOGGER.debug("requestUri:" + requestUri);
		LOGGER.debug("contextPath:" + contextPath);
		LOGGER.debug("url:" + url);

		// 登录页面的请求不进行拦截
		if (StringUtils.equals("/PMS/login", requestUri)
				|| StringUtils.equals("/PMS/PoliticalStatusServlet", requestUri)
				|| StringUtils.equals("/PMS/", requestUri)) {
			return true;
		}

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String role = (String) request.getSession().getAttribute("role");
		if (role == null) {
			LOGGER.debug("Interceptor：跳转到login页面！");
			request.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(request, response);
			return false;
		} else {
			return true;
		}

	}

	/**
	 * 业务处理器处理请求执行完成后，生成视图之前执行。 后处理（调用了Service并返回ModelAndView，但未进行页面渲染），有机会修改ModelAndView；
	 */
	@Override
	public void postHandle(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView)
			throws Exception {
		System.out.println("postHandle...");

		// String requestUri = httpServletRequest.getRequestURI();

		// 销毁session
		// 登录页面的请求不进行拦截
		// if (StringUtils.equals("/PMS/login", requestUri)
		// || StringUtils.equals("/PMS/PoliticalStatusServlet", requestUri)
		// || StringUtils.equals("/PMS/", requestUri)) {
		// httpServletRequest.getSession().invalidate();
		// }

	}

	/**
	 * 在DispatcherServlet完全处理完请求后被调用，可用于清理资源等。 返回处理（已经渲染了页面），可以根据ex是否为null判断是否发生了异常，进行日志记录；
	 */
	@Override
	public void afterCompletion(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
		System.out.println("afterCompletion...");
	}
}
