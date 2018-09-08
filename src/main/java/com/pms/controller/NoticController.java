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
import com.pms.service.NoticService;

@Controller
public class NoticController {

	/**
	 * 日志工具
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

	@Autowired
	private NoticService noticService;

	@RequestMapping("/noticManager")
	public String noticManager() {
		// 如果你的spring mvc配置文件中配置了跳转后缀则不需要加.jsp后缀
		// 即直接return "demo/pagefile";
		LOGGER.debug("访问主页");
		return "NoticManager";
	}

	@ResponseBody
	@RequestMapping(value = "/queryNotice", method = RequestMethod.POST)
	public Map<String, Object> queryPersionInfo(@RequestParam("page") Integer page,
			@RequestParam("rows") Integer rows) {

		System.out.println("rows:" + rows + "   page:" + page);
		Map<String, Object> result = noticService.queryNoticInfo(page, rows);

		System.out.println(JSON.toJSONString(result));

		return result;

	}

	/** Default constructor */
	public NoticController() {
	}
}
