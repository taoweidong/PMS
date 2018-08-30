package com.util.test;


import org.junit.Test;

import com.pms.util.Log4jHelper;


/**
 * <测试Log4j日志配置>
 * 
 * @author Taowd
 * @version 2018年8月30日
 * @see TestLog4j
 */
public class TestLog4j
{
    /**
     * Description: 测试Log4j日志配置<br>
     * 1、…<br>
     * 2、…<br>
     * Implement: <br>
     * 1、…<br>
     * 2、…<br>
     * 
     * @see
     */
    @Test
    public void testLog4j()
    {

        Log4jHelper.info("测试--");
        Log4jHelper.error("测试错误信息--");
    }

}
