package com.pms.util;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

/**
 * 封装json数据方法，返回客户端
 * @author Taowd
 * @version 2018年8月31日
 * @see ResponseUtil
 */
public class ResponseUtil {

	/**
	 * 封装json数据方法，返回 客户端
	 * @param response
	 * @param o
	 * @throws Exception
	 */
	public static void write(HttpServletResponse response, Object o) throws Exception {
		response.setContentType("text/plain;charset=utf-8");
		PrintWriter out = response.getWriter();
		System.out.println("返回页面的JSON数据：" + o.toString());
		out.println(o.toString());
		out.flush();
		out.close();
	}

	/**
	 * 私有构造函数
	 */
	private ResponseUtil() {
	}
}
