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
import com.pms.service.DepartmentService;

@Controller
public class DepartmentController {
	/**
	 * 日志工具
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

	@Autowired
	private DepartmentService departmentService;

	@RequestMapping("/departmentManage")
	public String index() {

		LOGGER.debug("访问主页:DepartmentManage");
		return "DepartmentManage";
	}

	@ResponseBody
	@RequestMapping(value = "/queryDepartment", method = RequestMethod.POST)
	public Map<String, Object> queryDepartment(@RequestParam("page") Integer page,
			@RequestParam("rows") Integer rows, @RequestParam("DEP_NAME") Integer depName,
			@RequestParam("DEP_ID") Integer empId, @RequestParam("DEP_LEADER") Integer depNo) {

		Map<String, Object> result = departmentService.queryDepartment(page, rows);

		System.out.println(JSON.toJSONString(result));

		return result;

	}

	/** Default constructor */
	public DepartmentController() {
	}
}
