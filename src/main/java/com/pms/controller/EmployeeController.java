package com.pms.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
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
import com.pms.entity.Employee;
import com.pms.entity.ReturnData;
import com.pms.service.EmployeeService;
import com.pms.util.AESUtil;
import com.pms.util.CheckInfo;
import com.pms.util.DateUtil;

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
			@RequestParam("rows") Integer rows,
			@RequestParam(value = "no", defaultValue = StringUtils.EMPTY) String no,
			@RequestParam(value = "name", defaultValue = StringUtils.EMPTY) String name,
			@RequestParam(value = "startBirthday", defaultValue = StringUtils.EMPTY) String startBirthday,
			@RequestParam(value = "sex", defaultValue = StringUtils.EMPTY) String sex,
			@RequestParam(value = "cboPsType", defaultValue = StringUtils.EMPTY) String cboPsType) {
		Map<String, Object> result = Maps.newHashMap();
		Employee employee = new Employee();
		try {
			employee.setNo(StringUtils.trimToNull(no));
			employee.setName(StringUtils.trimToNull(name));
			if (!StringUtils.isEmpty(startBirthday)) {
				employee.setBirthday(
						DateUtils.parseDate(StringUtils.trimToEmpty(startBirthday), "yyyy-MM-dd"));
			}

			employee.setSex(StringUtils.trimToNull(sex));
			employee.setPsId(StringUtils.trimToNull(cboPsType));

			result = employeeService.queryEmployee(page, rows, employee);

		} catch (Exception e) {
			LOGGER.error("查询异常信息", e);
		}

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

		return returnData;

	}

	/**
	 * 更新信息.
	 * @param no
	 * @return
	 */
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

		ReturnData result = employeeService.deleteEmployee(ids);

		return result;

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
	public ReturnData updateEmployee(
			@RequestParam(value = "no", defaultValue = StringUtils.EMPTY) String no,
			@RequestParam(value = "name", defaultValue = StringUtils.EMPTY) String name,
			@RequestParam(value = "passwd", defaultValue = StringUtils.EMPTY) String passwd,
			@RequestParam(value = "sex", defaultValue = StringUtils.EMPTY) String sex,
			@RequestParam(value = "birthday", defaultValue = StringUtils.EMPTY) String birthday,
			@RequestParam(value = "psId", defaultValue = StringUtils.EMPTY) String psId,
			@RequestParam(value = "phone", defaultValue = StringUtils.EMPTY) String phone,
			@RequestParam(value = "address", defaultValue = StringUtils.EMPTY) String address,
			@RequestParam(value = "ext1", defaultValue = StringUtils.EMPTY) String ext1) {

		if (StringUtils.isEmpty(passwd)) {
			return ReturnData.fail("密码不能为空!");
		}

		if (!CheckInfo.isMobileNO(StringUtils.trimToEmpty(phone))) {
			return ReturnData.fail("手机号格式不正确!");
		}

		Employee employee = new Employee();
		try {
			employee.setNo(StringUtils.trimToEmpty(no));
			employee.setName(StringUtils.trimToEmpty(name));
			employee.setSex(StringUtils.trimToEmpty(sex));
			employee.setPwd(AESUtil.parseByte2HexStr(AESUtil.encrypt(passwd)));
			employee.setBirthday(DateUtils.parseDate(birthday, "yyyy-MM-dd"));
			employee.setPsId(StringUtils.trimToEmpty(psId));
			employee.setPhone(StringUtils.trimToEmpty(phone));
			employee.setAddress(StringUtils.trimToEmpty(address));
			employee.setExt1(StringUtils.trimToEmpty(ext1));
			employee.setExt2(DateUtil.getCurrentDateStr());

			return employeeService.updateEmployee(employee);

		} catch (Exception e) {

			LOGGER.error("更新发生异常", e);
			return ReturnData.fail("更新发生异常");
		}
	}

	/**
	 * 添加用户信息.
	 * @param no
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/addEmployee", method = RequestMethod.POST)
	public ReturnData addEmployee(
			@RequestParam(value = "no", defaultValue = StringUtils.EMPTY) String no,
			@RequestParam(value = "name", defaultValue = StringUtils.EMPTY) String name,
			@RequestParam(value = "passwd", defaultValue = StringUtils.EMPTY) String passwd,
			@RequestParam(value = "sex", defaultValue = StringUtils.EMPTY) String sex,
			@RequestParam(value = "birthday", defaultValue = StringUtils.EMPTY) String birthday,
			@RequestParam(value = "psId", defaultValue = StringUtils.EMPTY) String psId,
			@RequestParam(value = "phone", defaultValue = StringUtils.EMPTY) String phone,
			@RequestParam(value = "address", defaultValue = StringUtils.EMPTY) String address,
			@RequestParam(value = "ext1", defaultValue = StringUtils.EMPTY) String ext1) {

		if (StringUtils.isEmpty(passwd)) {
			return ReturnData.fail("密码不能为空!");
		}

		if (!CheckInfo.isMobileNO(StringUtils.trimToEmpty(phone))) {
			return ReturnData.fail("手机号格式不正确!");
		}

		Employee employee = new Employee();
		try {
			employee.setNo(StringUtils.trimToEmpty(no));
			employee.setName(StringUtils.trimToEmpty(name));
			employee.setSex(StringUtils.trimToEmpty(sex));
			employee.setPwd(AESUtil.parseByte2HexStr(AESUtil.encrypt(passwd)));
			employee.setBirthday(DateUtils.parseDate(birthday, "yyyy-MM-dd"));
			employee.setPsId(StringUtils.trimToEmpty(psId));
			employee.setPhone(StringUtils.trimToEmpty(phone));
			employee.setAddress(StringUtils.trimToEmpty(address));
			employee.setExt1(StringUtils.trimToEmpty(ext1));
			employee.setExt2(DateUtil.getCurrentDateStr());

			return employeeService.addEmployee(employee);

		} catch (Exception e) {
			LOGGER.error("新增发生异常", e);
			return ReturnData.fail("新增发生异常");
		}

	}

	/** Default constructor */
	public EmployeeController() {
	}
}
