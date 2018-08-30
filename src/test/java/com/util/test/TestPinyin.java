package com.util.test;


import com.pms.util.PinYinUtils;
import org.junit.Test;


public class TestPinyin
{
    @Test
    public void testPinyin1()
    {

        System.out.println(
            "转拼音：" + PinYinUtils.getPingYin("王琦 饕餮       tom 12345 %^%^   骞谦  单雄信"));

        System.out.println("转拼音：" + PinYinUtils.getFirstSpell(
            "王琦 饕餮           tom 12345 %^%^             庆福园 单雄信"));

        System.out.println("转拼音：" + PinYinUtils.getFullSpell(
            "王琦 饕餮             tom 12345 %^%^           骞谦  单雄信"));
    }
}
