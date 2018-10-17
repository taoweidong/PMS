package com.pms.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

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

import com.alibaba.fastjson.JSON;
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

			System.out.println(JSON.toJSON(admin));

			result = adminService.updateAdmin(admin);
		}
		return result;
	}

	/** Default constructor */
	public PersionInfoController() {
	}
}
