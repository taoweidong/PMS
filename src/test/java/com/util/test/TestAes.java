package com.util.test;

import org.junit.Test;

import com.pms.util.AESUtil;

public class TestAes {

	@Test
	public void TestAES()
	{
		
		System.out.println(AESUtil.parseByte2HexStr(AESUtil
					.encrypt("123456")));
	}
}
