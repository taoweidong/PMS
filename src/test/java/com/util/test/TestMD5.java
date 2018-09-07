package com.util.test;

import org.junit.Test;

import com.pms.util.MD5Utils;

public class TestMD5 {

	/**
	 * Author:Taowd 功能： 开发日期：2017-4-25-下午7:37:07
	 * @param args
	 */
	@Test
	public void testMD5() {
		System.out.println(MD5Utils.getStringMD5("admin"));

		System.out.println(MD5Utils.hash("1214210135"));
	}

	/** Default constructor */
	public TestMD5() {
	}

}
