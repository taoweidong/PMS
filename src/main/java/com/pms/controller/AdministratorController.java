package com.pms.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 管理员控制器
 * @author Taowd
 * @version 2018年9月2日
 * @see AdministratorController
 */
@RestController
public class AdministratorController {

	@GetMapping("/hello")
	public String getInfo() {
		return "Hello";
	}

}
