package com.pms.util;

import java.util.UUID;

/**
 * String工具类
 * @author Taowd
 * @version 2018年8月31日
 * @see StringUtil
 */
public class StringUtil {
	/**
	 * Author:Taowd 功能：生成一个UUID 开发日期：2017-4-19-下午8:20:01
	 * @return
	 * @throws Exception
	 */
	public static String GetUUID() throws Exception {
		return UUID.randomUUID().toString().replace("-", "");
	}

	/**
	 * 格式化删除数据 delIds.
	 * @param delIds
	 * @return
	 */
	public static String FormatDeleteDelIds(String delIds) {
		String[] str = delIds.split(",");
		String deleteStr = "";
		for (int i = 0; i < str.length; i++) {
			if (i == str.length - 1) {
				deleteStr += "'" + str[i] + "'";
			} else {
				deleteStr += "'" + str[i] + "',";
			}
		}

		return deleteStr;
	}

	/** Prevent instantiation */
	private StringUtil() {
	}
}
