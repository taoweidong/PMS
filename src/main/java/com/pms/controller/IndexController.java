package com.pms.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.pms.service.LoginService;

/**
 * 主页控制器
 * @author Taowd
 * @version 2018年9月2日
 * @see IndexController
 */
@Controller
// 注意 这里不要定义@RequestMapping
public class IndexController {

	/**
	 * 日志工具
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

	@Autowired
	private LoginService loginService;

	/**
	 * 主页导航功能.
	 * @return
	 */
	@RequestMapping("/")
	public String index() {
		// 如果你的spring mvc配置文件中配置了跳转后缀则不需要加.jsp后缀
		// 即直接return "demo/pagefile";
		LOGGER.debug("登录主页");
		return "index";
	}

	/**
	 * 登录功能.
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam(name = "userName") String userName,
			@RequestParam(name = "password") String password,
			@RequestParam(name = "role") String role) {
		// 检查用户信息

		System.out.println("登录功能访问userName------->" + userName);
		System.out.println("登录功能访问password------->" + password);
		System.out.println("登录功能访问role------->" + role);

		loginService.login(userName, password, role);

		return "main";
	}

}
