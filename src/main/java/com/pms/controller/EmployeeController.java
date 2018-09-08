package com.pms.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.pms.service.EmployeeService;

@Controller
public class EmployeeController {
	/**
	 * 日志工具
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

	@Autowired
	private EmployeeService employeeService;

	@RequestMapping("/employeeManage")
	public String index() {

		LOGGER.debug("访问主页:EmployeeManage");
		return "EmployeeManage";
	}

	/**
	 * 查询用户信息 方法功能.
	 * @param page
	 * @param rows
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryEmployee", method = RequestMethod.POST)
	public Map<String, Object> queryPersionInfo(@RequestParam("page") Integer page,
			@RequestParam("rows") Integer rows) {
		System.out.println("rows:" + rows + "   page:" + page);
		Map<String, Object> result = employeeService.queryEmployee(page, rows);

		System.out.println(JSON.toJSONString(result));

		return result;

	}

	/** Default constructor */
	public EmployeeController() {
	}
}
