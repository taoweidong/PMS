package com.pms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 管理员控制器
 * @author Taowd
 * @version 2018年9月2日
 * @see AdminManagerController
 */
@Controller
public class AdminManagerController {

	@RequestMapping("/adminManager")
	public String index() {
		return "AdminManager";
	}

}
