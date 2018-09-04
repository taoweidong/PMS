package com.pms.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PersionInfoManageController {
	/**
	 * 日志工具
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

	/**
	 * 个人信息管理.
	 * @return
	 */
	@RequestMapping("/persionInfoManage")
	public String persionInfoManage() {
		// 如果你的spring mvc配置文件中配置了跳转后缀则不需要加.jsp后缀
		// 即直接return "demo/pagefile";
		LOGGER.debug("个人信息管理");
		return "PersionInfoManage";
	}
}
