package com.pms.util;

import java.util.UUID;

/**
 * 
 * @author Taowd
 * 功        能：String工具类
 * 编写时间：2017-5-5-下午2:26:44
 */
public class StringUtil {
	/**
	 * 功能：判断字符串str是否为空或者null
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if ("".equals(str) || str == null) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isNotEmpty(String str) {
		if (!"".equals(str) && str != null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * Author:Taowd
	 * 功能：生成一个UUID
	 * 开发日期：2017-4-19-下午8:20:01
	 * @return
	 * @throws Exception 
	 */
	public static String GetUUID() throws Exception {
		return UUID.randomUUID().toString().replace("-", "");
	}

	/**
	 * 
	 * Author:Taowd
	 * 功能：格式化删除数据 delIds
	 * 开发日期：2017-4-19-下午8:56:09
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
}
