package com.pms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 主页控制器
 * @author Taowd
 * @version 2018年9月2日
 * @see IndexController
 */
@Controller
// 注意 这里不要定义@RequestMapping
public class IndexController {

	@RequestMapping("/")
	public String index() {
		// 如果你的spring mvc配置文件中配置了跳转后缀则不需要加.jsp后缀
		// 即直接return "demo/pagefile";
		System.out.println("访问主页");
		return "index";
	}

}
