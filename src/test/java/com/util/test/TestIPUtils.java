package com.util.test;

import org.junit.Test;

import com.pms.util.IPUtils;

/**
 * 
 * @author Taowd
 * 功        能：测试获取IP地址
 * 编写时间：2017-4-24-下午7:02:00
 */
public class TestIPUtils {
	@Test
	public void testIp() {
		System.out.println("获取IP地址：" + IPUtils.HOST_NAME);

	}
}
