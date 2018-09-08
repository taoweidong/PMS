package com.pms.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.pms.service.AdminService;

/**
 * 管理员控制器
 * @author Taowd
 * @version 2018年9月2日
 * @see AdminController
 */
@Controller
public class AdminController {

	@Autowired
	private AdminService adminService;

	@RequestMapping("/adminManager")
	public String index() {
		return "AdminManager";
	}

	@ResponseBody
	@RequestMapping(value = "/queryAdmin", method = RequestMethod.POST)
	public Map<String, Object> queryAdmin(@RequestParam("page") Integer page,
			@RequestParam("rows") Integer rows) {
		System.out.println("rows:" + rows + "   page:" + page);
		Map<String, Object> result = adminService.queryAdmin(page, rows);

		System.out.println(JSON.toJSONString(result));

		return result;

	}

	/** Default constructor */
	public AdminController() {
	}

}
