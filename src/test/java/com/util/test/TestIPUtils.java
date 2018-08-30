package com.util.test;


import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.pms.util.IPUtils;


/**
 * <测试获取IP地址>
 * 
 * @author Taowd
 * @version 2018年8月30日
 * @see TestIPUtils
 */
public class TestIPUtils
{
    @Test
    public void testIp()
    {
        assertEquals("Taowd-PC", IPUtils.HOST_NAME);
    }
}
