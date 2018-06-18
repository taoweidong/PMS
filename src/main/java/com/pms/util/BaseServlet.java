package com.pms.util;

import java.io.IOException;
import java.lang.reflect.Method;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author Taowd
 * 功        能：Servlet的一个处理基类
 * 编写时间：2017-5-3-下午2:05:21
 */
public abstract class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		/**
		 * 1、获取参数，用于识别用户想请求那个方法？
		 * 2、然后判断那个方法使我们想调用的，调用进行处理
		 */
		String methodName = request.getParameter("method");
		if (methodName == null || methodName.trim().isEmpty()) {
			throw new RuntimeException("你没有传递method参数！无法确定您想要调用的方法！");
		}

		/**
		 * 得到方法名，是否通过反射调用方法
		 */
		Class<? extends BaseServlet> c = this.getClass();
		Method method = null;
		try {
			method = c.getMethod(methodName, HttpServletRequest.class,
					HttpServletResponse.class);
		} catch (Exception e) {
			throw new RuntimeException("你要调用的方法:" + methodName + ":不存在");
		}

		try {
			Log4jHelper
					.info("进入调度Servlet处理类，处理成功，由：[" + methodName + "]方法进行执行");
			// 进行反射调用
			String result = (String) method.invoke(this, request, response);

			if (result == null || result.trim().isEmpty()) {
				return;
			}

			if (result.contains(":")) {
				int index = result.indexOf(":");
				String s = result.substring(0, index);// 取出冒号前缀
				String path = result.substring(index + 1);// 取出冒号后缀
				if ("r".equals(s)) {
					response.sendRedirect(request.getContextPath() + path);
				} else if ("f".equals(s)) {
					request.getRequestDispatcher(path).forward(request,
							response);
				} else {
					throw new RuntimeException("暂不支持");
				}

			} else// 没有冒号，默认转发
			{
				request.getRequestDispatcher(result).forward(request, response);
			}
		} catch (Exception e) {
			System.out.println("你要调用的方法:" + methodName + ":内部异常");
			throw new RuntimeException(e);
		}

	}
}
