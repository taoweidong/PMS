package com.util.test;


import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.pms.util.AESUtil;


/**
 * <测试AES功能>
 * 
 * @author Taowd
 * @version 2018年8月30日
 * @see TestAes
 */
public class TestAes
{

    /**
     * Description: <br>
     * 1、测试AES<br>
     * 
     * @see
     */
    @Test
    public void testAES()
    {
        assertEquals("A9CBADCCCEAC5887D2518403369CD9AD",
            AESUtil.parseByte2HexStr(AESUtil.encrypt("123456")));
    }
}
