package com.pms.util;

import java.util.Map;

import com.google.common.collect.Maps;

public class DictionaryConversion {

	public static final Map<String, String> sexDic = Maps.newHashMap();
	static {
		sexDic.put("M", "男");
		sexDic.put("F", "女");
	}

	/**
	 * 性别转换，将code代码转为汉字描述.
	 * @param code
	 * @return
	 */
	public static String sexDictionaryToDesc(String code) {
		if (sexDic.containsKey(code)) {
			return sexDic.get(code);
		} else {
			return code;
		}

	}

}
