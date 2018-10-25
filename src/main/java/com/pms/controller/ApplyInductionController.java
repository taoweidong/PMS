package com.pms.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.pms.entity.Administrator;
import com.pms.entity.Employee;
import com.pms.service.ApplyInductionService;

@Controller
public class ApplyInductionController {
	/**
	 * 日志工具
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

	@Autowired
	private ApplyInductionService applyInductionService;

	@RequestMapping("/applyInduction")
	public String index() {

		LOGGER.debug("访问主页:ApplyInduction");
		return "ApplyInduction";
	}

	@ResponseBody
	@RequestMapping(value = "/queryApplyInduction", method = RequestMethod.POST)
	public Map<String, Object> queryApplyInduction(HttpServletRequest request,
			@RequestParam("page") Integer page, @RequestParam("rows") Integer rows,
			@RequestParam(value = "posName", defaultValue = StringUtils.EMPTY) String posName,
			@RequestParam(value = "startDate", defaultValue = StringUtils.EMPTY) String startDate,
			@RequestParam(value = "endDate", defaultValue = StringUtils.EMPTY) String endDate,
			@RequestParam(value = "approveState", defaultValue = StringUtils.EMPTY) String approveState) {

		String role = request.getSession().getAttribute("role").toString();
		String userId = StringUtils.EMPTY;// 工号
		// 设置发布人工号，从session中获取数据
		Object user = request.getSession().getAttribute("user");
		if (user instanceof Administrator) {
			userId = ((Administrator) user).getId();
		} else if (user instanceof Employee) {
			userId = ((Employee) user).getNo();
		}

		Map<String, Object> result = applyInductionService.queryApplyInduction(page, rows, posName,
				approveState, startDate, endDate, role, userId);

		System.out.println(JSON.toJSONString(result));

		return result;

	}
}
