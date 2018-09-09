package com.pms.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.pms.entity.Administrator;
import com.pms.entity.ReturnData;
import com.pms.service.AdminService;
import com.pms.utils.CheckInfo;

/**
 * 管理员控制器
 * @author Taowd
 * @version 2018年9月2日
 * @see AdminController
 */
@Controller
public class AdminController {

	@Autowired
	private AdminService adminService;

	@RequestMapping("/adminManager")
	public String index() {
		return "AdminManager";
	}

	@ResponseBody
	@RequestMapping(value = "/queryAdmin", method = RequestMethod.POST)
	public Map<String, Object> queryAdmin(@RequestParam("page") Integer page,
			@RequestParam("rows") Integer rows) {
		System.out.println("rows:" + rows + "   page:" + page);
		Map<String, Object> result = adminService.queryAdmin(page, rows);

		System.out.println(JSON.toJSONString(result));

		return result;

	}

	/**
	 * 更新管理信息.
	 * @param id
	 * @param no
	 * @param name
	 * @param phone
	 * @param ext1
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updateAdmin", method = RequestMethod.POST)
	public ReturnData updateAdmin(@RequestParam("id") String id, @RequestParam("no") String no,
			@RequestParam("name") String name, @RequestParam("phone") String phone,
			@RequestParam("ext1") String ext1) {

		if (!CheckInfo.isMobileNO(StringUtils.trimToEmpty(phone))) {
			return ReturnData.fail("手机号格式不正确!");
		}

		Administrator admin = adminService.selectAdminById(id);
		admin.setId(id);
		admin.setNo(no);
		admin.setName(name);
		admin.setPhone(phone);
		admin.setExt1(ext1);
		admin.setExt2(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date().getTime()));

		if (adminService.updateAdmin(admin)) {
			return ReturnData.success();

		} else {
			return ReturnData.fail("操作失败！");
		}
	}

	/** Default constructor */
	public AdminController() {
	}

}
