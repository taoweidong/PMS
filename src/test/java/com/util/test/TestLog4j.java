package com.util.test;

import org.junit.Test;

import com.pms.util.Log4jHelper;

public class TestLog4j {
	/**
	 * Author:Taowd
	 * 功能：测试Log4j日志配置
	 * 开发日期：2017-4-21-下午1:48:07
	 * @param args
	 */
	@Test
	public void testLog4j() {

		Log4jHelper.info("测试--");
		Log4jHelper.error("测试错误信息--");
	}

}
