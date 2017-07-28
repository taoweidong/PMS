package com.util.test;

import org.junit.Test;

import com.pms.util.PinYinUtils;

public class TestPinyin {
	@Test
	public void TestPinyin1() {

		System.out.println("×ªÆ´Òô£º" + PinYinUtils.getPingYin("Íõçù ÷Ò÷Ñ       tom 12345 %^%^   å¹Ç«  µ¥ÐÛÐÅ"));

		System.out.println("×ªÆ´Òô£º" + PinYinUtils.getFirstSpell("Íõçù ÷Ò÷Ñ           tom 12345 %^%^             Çì¸£Ô° µ¥ÐÛÐÅ"));

		System.out.println("×ªÆ´Òô£º" + PinYinUtils.getFullSpell("Íõçù ÷Ò÷Ñ             tom 12345 %^%^           å¹Ç«  µ¥ÐÛÐÅ"));
	}
}
