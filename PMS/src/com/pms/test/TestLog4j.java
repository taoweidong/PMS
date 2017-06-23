package com.pms.test;

import com.pms.util.Log4jHelper;

public class TestLog4j {
	/**
	 * Author:Taowd
	 * 功能：测试Log4j日志配置
	 * 开发日期：2017-4-21-下午1:48:07
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 * System.setProperty("log4j.configuration", "log4j.properties");
		 * 
		 * logger.debug("记录Debug信息");
		 * 
		 * logger.info("输出info信息");
		 * 
		 * logger.error("输出error信息");
		 */

		Log4jHelper.info("测试--");
		Log4jHelper.error("测试错误信息--");

	}

}
