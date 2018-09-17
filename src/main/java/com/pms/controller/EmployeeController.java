package com.pms.controller;

import java.util.List;
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
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.pms.entity.ReturnData;
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
			@RequestParam("rows") Integer rows, @RequestParam("no") String no,
			@RequestParam("name") String name, @RequestParam("birthday") String birthday,
			@RequestParam("sex") String sex, @RequestParam("cboPsType") String cboPsType) {

		Map<String, Object> result = employeeService.queryEmployee(page, rows);

		System.out.println(JSON.toJSONString(result));

		return result;

	}

	/**
	 * 下拉框获取用户信息.
	 * @param page
	 * @param rows
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/comboListEmployee", method = RequestMethod.POST)
	public List<Map<String, Object>> comboListEmployee() {
		List<Map<String, Object>> returnData = Lists.newArrayList();

		Map<String, Object> param = Maps.newHashMap();
		param.put("EMP_NO", "");
		param.put("EMP_NAME", "请选择...");

		returnData.add(param);

		List<Map<String, Object>> result = employeeService.comboEmployee();

		returnData.addAll(result);

		System.out.println(JSON.toJSONString(returnData));

		return returnData;

	}

	@ResponseBody
	@RequestMapping(value = "/EmployeeSave", method = RequestMethod.POST)
	public Map<String, Object> employeeSave(@RequestParam("no") String no) {

		// Employee employee = new Employee();

		Map<String, Object> result = Maps.newHashMap();

		System.out.println(JSON.toJSONString(no));

		return result;

	}

	/**
	 * 删除员工信息.
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteEmployee", method = RequestMethod.POST)
	public ReturnData deleteEmployee(@RequestParam("ids") String ids) {

		// ReturnData result = new ReturnData();

		return ReturnData.success();

	}

	/**
	 * 更新信息.
	 * @param no
	 * @param name
	 * @param passwd
	 * @param sex
	 * @param birthday
	 * @param psId
	 * @param phone
	 * @param address
	 * @param ext1
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updateEmployee", method = RequestMethod.POST)
	public ReturnData updateEmployee(@RequestParam("no") String no,
			@RequestParam("name") String name, @RequestParam("passwd") String passwd,
			@RequestParam("sex") String sex, @RequestParam("birthday") String birthday,
			@RequestParam("psId") String psId, @RequestParam("phone") String phone,
			@RequestParam("address") String address, @RequestParam("ext1") String ext1) {

		// ReturnData result = new ReturnData();

		return ReturnData.success();

	}

	/**
	 * 添加用户信息.
	 * @param no
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/addEmployee", method = RequestMethod.POST)
	public ReturnData addEmployee(@RequestParam("no") String no) {

		// ReturnData result = new ReturnData();

		return ReturnData.success();

	}

	/** Default constructor */
	public EmployeeController() {
	}
}
