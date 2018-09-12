package com.pms.controller;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
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
	public Map<String, Object> queryDepartment(
			@RequestParam(value = "page", defaultValue = "15") Integer page,
			@RequestParam(value = "rows", defaultValue = "1") Integer rows,
			@RequestParam(value = "DEP_NAME", defaultValue = "") String depName,
			@RequestParam(value = "DEP_ID", defaultValue = "") String depNo,
			@RequestParam(value = "DEP_LEADER", defaultValue = "") String leader) {

		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("depName", StringUtils.trimToEmpty(depName));
		paramMap.put("leader", StringUtils.trimToEmpty(leader));
		paramMap.put("depNo", StringUtils.trimToEmpty(depNo));

		Map<String, Object> result = departmentService.queryDepartment(page, rows, paramMap);

		System.out.println(JSON.toJSONString(result));

		return result;

	}

	/** Default constructor */
	public DepartmentController() {
	}
}
