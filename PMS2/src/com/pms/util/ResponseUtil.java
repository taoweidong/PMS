package com.pms.util;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author 陶伟东
 * TODO：封装json数据方法，返回 客户端
 * 编写时间：下午9:53:45
 */
public class ResponseUtil {

	/**
	 * 封装json数据方法，返回 客户端
	 * @param response
	 * @param o
	 * @throws Exception
	 */
	public static void write(HttpServletResponse response, Object o)
			throws Exception {
		response.setContentType("text/plain;charset=utf-8");
		PrintWriter out = response.getWriter();
		System.out.println("返回页面的JSON数据：" + o.toString());
		out.println(o.toString());
		out.flush();
		out.close();
	}
}
