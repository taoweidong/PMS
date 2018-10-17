package com.pms.controller;

import java.util.List;
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
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.pms.entity.Department;
import com.pms.entity.ReturnData;
import com.pms.service.DepartmentService;
import com.pms.util.StringUtil;

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

	@ResponseBody
	@RequestMapping(value = "/updateDepartment", method = RequestMethod.POST)
	public ReturnData updateDepartment(
			@RequestParam(value = "depId", defaultValue = StringUtils.EMPTY) String depId,
			@RequestParam(value = "name", defaultValue = StringUtils.EMPTY) String name,
			@RequestParam(value = "leader", defaultValue = StringUtils.EMPTY) String leader) {

		Department department = new Department();
		department.setId(depId);
		department.setName(name);
		department.setLeader(leader);

		try {

			return departmentService.updateDepartment(department);

		} catch (Exception e) {

			LOGGER.error("更新发生异常", e);
			return ReturnData.fail("更新发生异常");
		}
	}

	@ResponseBody
	@RequestMapping(value = "/deleteDepartment", method = RequestMethod.POST)
	public ReturnData deleteDepartment(@RequestParam("ids") String ids) {

		ReturnData result = departmentService.deleteDepartment(ids);

		return result;

	}

	@ResponseBody
	@RequestMapping(value = "/addDepartment", method = RequestMethod.POST)
	public ReturnData addDepartment(
			@RequestParam(value = "name", defaultValue = StringUtils.EMPTY) String name,
			@RequestParam(value = "leader", defaultValue = StringUtils.EMPTY) String leader) {
		Department department = new Department();
		try {
			department.setId(StringUtil.generateShortUuid());
			department.setName(name);
			department.setLeader(leader);

			return departmentService.addDepartment(department);

		} catch (Exception e) {
			LOGGER.error("添加异常", e);
		}

		return ReturnData.fail("添加失败");

	}

	/**
	 * 下拉框获取部门信息.
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/cboDepartmentList", method = RequestMethod.POST)
	public List<Map<String, Object>> cboDepartmentList() {
		List<Map<String, Object>> returnData = Lists.newArrayList();

		Map<String, Object> param = Maps.newHashMap();
		param.put("id", "");
		param.put("name", "请选择...");

		returnData.add(param);

		List<Map<String, Object>> result = departmentService.cboDepartmentList();

		returnData.addAll(result);

		return returnData;

	}

	/** Default constructor */
	public DepartmentController() {
	}
}
