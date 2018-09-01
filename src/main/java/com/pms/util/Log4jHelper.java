package com.pms.util;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * Log4j的封装类
 * @author Taowd
 * @version 2018年8月31日
 * @see Log4jHelper
 */
public class Log4jHelper {

	/**
	 * 加载log4j的配置文件:默认加载src目录下的log4j.properties配置文件
	 */
	static {
		// DOMConfigurator.configure("/log4j.properties");
	}

	/**
	 * 输出正常信息.
	 * @param msg
	 */
	public static void info(Object msg) {
		StackTraceElement stack[] = (new Throwable()).getStackTrace();
		Logger logger = Logger.getLogger(stack[1].getClassName());
		logger.log(Log4jHelper.class.getName(), Level.INFO, msg, null);
	}

	/**
	 * Author:Taowd 功能：输出错误日志 开发日期：2017-4-25-下午5:16:33
	 * @param msg
	 */
	public static void error(Object msg) {
		StackTraceElement stack[] = (new Throwable()).getStackTrace();
		Logger logger = Logger.getLogger(stack[1].getClassName());
		logger.log(Log4jHelper.class.getName(), Level.ERROR, msg, null);
	}

	/**
	 * Author:Taowd 功能：输出Debug信息 开发日期：2017-4-25-下午7:15:07
	 * @param msg
	 */
	public static void debug(Object msg) {
		StackTraceElement stack[] = (new Throwable()).getStackTrace();
		Logger logger = Logger.getLogger(stack[1].getClassName());
		logger.log(Log4jHelper.class.getName(), Level.DEBUG, msg, null);
	}

	/**
	 * Author:Taowd 功能：输出警告信息 开发日期：2017-4-25-下午7:15:48
	 * @param msg
	 */
	public static void warn(Object msg) {
		StackTraceElement stack[] = (new Throwable()).getStackTrace();
		Logger logger = Logger.getLogger(stack[1].getClassName());
		logger.log(Log4jHelper.class.getName(), Level.WARN, msg, null);
	}

	/**
	 * Author:Taowd 功能：输出异常信息 开发日期：2017-4-25-下午7:25:26
	 * @param e
	 */
	public static void exception(Exception e) {
		StackTraceElement stack[] = (new Throwable()).getStackTrace();
		Logger logger = Logger.getLogger(stack[1].getClassName());
		logger.log(Log4jHelper.class.getName(), Level.ERROR, getSatckTrace(e), null);
	}

	/**
	 * Author:Taowd 功能：获取堆栈信息 开发日期：2017-4-25-下午7:23:29
	 * @param e
	 * @return
	 */
	private static String getSatckTrace(Exception e) {
		StringWriter write = new StringWriter();
		e.printStackTrace(new PrintWriter(write, true));
		return write.toString();
	}

	/** Prevent instantiation */
	private Log4jHelper() {
	}

}
