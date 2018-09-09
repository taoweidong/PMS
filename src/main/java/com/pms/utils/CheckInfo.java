package com.pms.utils;

import org.apache.commons.lang3.StringUtils;

public class CheckInfo {

	public static boolean isMobileNO(String mobiles) {
		String telRegex = "[1][3578]\\d{9}";
		// "[1]"代表第1位为数字1，"[3578]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
		if (StringUtils.isEmpty(mobiles)) {
			return false;
		} else
			return mobiles.matches(telRegex);
	}

}
