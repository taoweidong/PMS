package com.pms.test;

import com.pms.util.PinYinUtils;

public class TestPinyin {
	public static void main(String[] args) {

		System.out.println("×ªÆ´Òô£º"
				+ PinYinUtils
						.getPingYin("Íõçù ÷Ò÷Ñ       tom 12345 %^%^   å¹Ç«  µ¥ÐÛÐÅ"));

		System.out
				.println("×ªÆ´Òô£º"
						+ PinYinUtils
								.getFirstSpell("Íõçù ÷Ò÷Ñ           tom 12345 %^%^             å¹Ç«  µ¥ÐÛÐÅ"));

		System.out
				.println("×ªÆ´Òô£º"
						+ PinYinUtils
								.getFullSpell("Íõçù ÷Ò÷Ñ             tom 12345 %^%^           å¹Ç«  µ¥ÐÛÐÅ"));
	}
}
