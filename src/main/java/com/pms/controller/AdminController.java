package com.pms.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pms.entity.Administrator;
import com.pms.entity.ReturnData;
import com.pms.service.AdminService;
import com.pms.util.AESUtil;
import com.pms.util.Constant;
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
	public Map<String, Object> queryAdmin(
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "rows", defaultValue = "15") Integer rows,
			@RequestParam(value = "no", defaultValue = "") String no,
			@RequestParam(value = "name", defaultValue = "") String name,
			@RequestParam(value = "phone", defaultValue = "") String phone) {

		Administrator administrator = new Administrator();
		administrator.setNo(StringUtils.trimToNull(no));
		administrator.setName(StringUtils.trimToNull(name));
		administrator.setPhone(StringUtils.trimToNull(phone));

		Map<String, Object> result = adminService.queryAdmin(page, rows, administrator);
		return result;

	}

	/**
	 * 删除管理员.
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteAdmin", method = RequestMethod.POST)
	public ReturnData deleteAdmin(@RequestParam("ids") String ids) {

		ReturnData result = adminService.deleteAdmin(ids);

		return result;
	}

	/**
	 * 设置超级管理员.
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/setSuperAdmin", method = RequestMethod.POST)
	public ReturnData setSuperAdmin(@RequestParam("ids") String ids) {

		ReturnData result = adminService.setSuperAdmin(ids);

		return result;
	}

	/**
	 * 取消超级管理员.
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/cancelSuperAdmin", method = RequestMethod.POST)
	public ReturnData cancelSuperAdmin(@RequestParam("ids") String ids) {

		ReturnData result = adminService.cancelSuperAdmin(ids);

		return result;
	}

	/**
	 * 添加超级管理员.
	 * @param no
	 * @param name
	 * @param newPassword
	 * @param phone
	 * @param ext1
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/addAdmin", method = RequestMethod.POST)
	public ReturnData addAdmin(@RequestParam("no") String no, @RequestParam("name") String name,
			@RequestParam("newPassword") String newPassword, @RequestParam("phone") String phone,
			@RequestParam("ext1") String ext1) throws Exception {

		if (!CheckInfo.isMobileNO(StringUtils.trimToEmpty(phone))) {
			return ReturnData.fail("手机号格式不正确!");
		}

		Administrator admin = new Administrator();
		admin.setNo(no);
		admin.setName(name);
		admin.setPwd(AESUtil.parseByte2HexStr(AESUtil.encrypt(newPassword)));
		admin.setPhone(phone);
		admin.setExt1(ext1);
		admin.setExt2(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date().getTime()));
		admin.setExt3(Constant.GENERAL_MANAGER);

		ReturnData result = adminService.updateAdmin(admin);
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

		List<Administrator> listAdmin = adminService.selectAdminListById(no);
		if (listAdmin != null && listAdmin.size() > 1) {
			return ReturnData.fail("该登录账号已存在!");
		}

		Administrator admin = adminService.selectAdminById(id);
		admin.setId(id);
		admin.setNo(no);
		admin.setName(name);
		admin.setPhone(phone);
		admin.setExt1(ext1);
		admin.setExt2(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date().getTime()));

		ReturnData result = adminService.updateAdmin(admin);
		return result;

	}

	/** Default constructor */
	public AdminController() {
	}

}
