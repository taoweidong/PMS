package com.pms.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pms.entity.Administrator;
import com.pms.entity.Employee;
import com.pms.entity.ReturnData;
import com.pms.service.AdminService;
import com.pms.service.EmployeeService;
import com.pms.util.AESUtil;
import com.pms.util.CheckInfo;
import com.pms.util.DateUtil;

@Controller
public class PersionInfoController {
	/**
	 * 日志工具
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(PersionInfoController.class);

	@Autowired
	private AdminService adminService;

	@Autowired
	private EmployeeService employeeService;

	/**
	 * 个人信息管理.
	 * @return
	 */
	@RequestMapping("/persionInfoManage")
	public String persionInfoManage(HttpSession httpSession) {
		// 如果你的spring mvc配置文件中配置了跳转后缀则不需要加.jsp后缀
		// 即直接return "demo/pagefile";
		LOGGER.debug("个人信息管理");

		// 更新session中的数据
		String role = (String) httpSession.getAttribute("role");
		if (StringUtils.equals(role, "user")) {// 普通用户的保存

			// session中的数据获取id
			Employee emp = (Employee) httpSession.getAttribute("user");
			// 获取最新数据，更新session
			Employee employee = employeeService.selectEmployeeById(emp.getNo());

			// 设置session
			httpSession.setAttribute("user", employee);
			httpSession.setAttribute("role", role);

		} else if (StringUtils.equals(role, "admin") || StringUtils.equals(role, "superAdmin")) { // 检查管理员信息

		}

		return "PersionInfoManage";
	}

	/**
	 */
	@ResponseBody
	@RequestMapping(value = "/checkPwd", method = RequestMethod.POST)
	public ReturnData checkPwd(@RequestParam("password") String password,
			HttpServletRequest request) throws Exception {

		Object user = request.getSession().getAttribute("user");
		if (user instanceof Administrator) {
			if (StringUtils.equals(((Administrator) user).getPwd(),
					AESUtil.parseByte2HexStr(AESUtil.encrypt(password)))) {
				return ReturnData.success();
			}
		} else if (user instanceof Employee) {
			if (StringUtils.equals(((Employee) user).getPwd(),
					AESUtil.parseByte2HexStr(AESUtil.encrypt(password)))) {
				return ReturnData.success();
			}
		}

		return ReturnData.fail("密码错误!");
	}

	/**
	 * 更新密码.
	 * @param password
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/passwordModify", method = RequestMethod.POST)
	public ReturnData passwordModify(@RequestParam("newPassword") String newPassword,
			@RequestParam("newPassword2") String newPassword2, HttpServletRequest request)
			throws Exception {

		if (!StringUtils.equals(newPassword, newPassword2)) {
			return ReturnData.fail("密码不一致!");
		}

		if (StringUtils.isBlank(newPassword)) {
			return ReturnData.fail("密码不能为空!");
		}

		Object user = request.getSession().getAttribute("user");
		if (user instanceof Administrator) {
			Administrator admin = (Administrator) user;
			admin.setPwd(AESUtil.parseByte2HexStr(AESUtil.encrypt(newPassword)));
			return adminService.updateAdmin(admin);
		} else if (user instanceof Employee) {
			Employee employee = (Employee) user;
			employee.setPwd(AESUtil.parseByte2HexStr(AESUtil.encrypt(newPassword)));
			return employeeService.updateEmployee(employee);
		}

		return ReturnData.fail("修改失败!");
	}

	/**
	 * 更新个人信息.
	 * @param id
	 * @param no
	 * @param name
	 * @param newPassword
	 * @param phone
	 * @param ext1
	 * @param httpSession
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/updatePersion", method = RequestMethod.POST)
	public ReturnData updatePersion(@RequestParam("id") String id, @RequestParam("no") String no,
			@RequestParam("name") String name, @RequestParam("newPassword") String newPassword,
			@RequestParam("phone") String phone, @RequestParam("ext1") String ext1,
			HttpSession httpSession) throws Exception {
		ReturnData result = new ReturnData();
		// 角色
		String role = (String) httpSession.getAttribute("role");
		if (StringUtils.equals(role, "user")) {// 普通用户的保存

			Employee employee = employeeService.selectEmployeeById(no);
			employee.setName(StringUtils.trimToEmpty(name));
			employee.setPwd(AESUtil.parseByte2HexStr(AESUtil.encrypt(newPassword)));
			employee.setPhone(StringUtils.trimToEmpty(phone));
			employee.setExt1(StringUtils.trimToEmpty(ext1));
			employee.setExt2(DateUtil.getCurrentDateStr());

			result = employeeService.updateEmployee(employee);

			// 清除旧数据
			httpSession.removeAttribute("user");
			httpSession.setAttribute("user", employee);

		} else// 管理员更新
		{

			if (!CheckInfo.isMobileNO(StringUtils.trimToEmpty(phone))) {
				return ReturnData.fail("手机号格式不正确!");
			}

			Administrator admin = adminService.selectAdminById(id);
			admin.setNo(no);
			admin.setPwd(AESUtil.parseByte2HexStr(AESUtil.encrypt(newPassword)));
			admin.setName(name);
			admin.setPhone(phone);
			admin.setExt1(StringUtils.trimToEmpty(ext1));
			admin.setExt2(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date().getTime()));

			result = adminService.updateAdmin(admin);

			// 清除旧数据
			httpSession.removeAttribute("user");
			httpSession.setAttribute("user", admin);
		}
		return result;
	}

}
