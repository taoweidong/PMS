package com.pms.util;

import java.util.UUID;

/**
 * String工具类
 * @author Taowd
 * @version 2018年8月31日
 * @see StringUtil
 */
public class StringUtil {

	public static String[] chars = new String[] { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
			"k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0",
			"1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H",
			"I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y",
			"Z" };

	/**
	 * 生成8位UUID
	 * @return 8位UUID
	 */
	public static String generateShortUuid() {
		StringBuffer shortBuffer = new StringBuffer();
		String uuid = UUID.randomUUID().toString().replace("-", "");
		for (int i = 0; i < 8; i++) {
			String str = uuid.substring(i * 4, i * 4 + 4);
			int x = Integer.parseInt(str, 16);
			shortBuffer.append(chars[x % 0x3E]);
		}
		return shortBuffer.toString().toUpperCase();
	}

	/**
	 * 生成一个UUID.
	 * @return
	 * @throws Exception
	 */
	public static String GetUUID() throws Exception {
		return UUID.randomUUID().toString().replace("-", "").toUpperCase();
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
