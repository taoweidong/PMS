package com.pms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.pms.entity.Administrator;
import com.pms.service.AdminService;
import com.pms.util.AESUtil;

/**
 * 主页控制器
 * @author Taowd
 * @version 2018年9月2日
 * @see IndexController
 */
@Controller
// 注意 这里不要定义@RequestMapping
public class IndexController {

	/**
	 * 日志工具
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

	@Autowired
	private AdminService adminService;

	private static final String USER = "user";
	private static final String ROLE = "role";
	private static final String ERROR = "error";

	/**
	 * 主页导航功能.
	 * @return 登录主页地址
	 */
	@RequestMapping("/")
	public String index() {
		// 如果你的spring mvc配置文件中配置了跳转后缀则不需要加.jsp后缀
		// 即直接return "demo/pagefile";
		LOGGER.debug("登录主页");
		return "index";
	}

	/**
	 * 登录功能 此处使用重定向方式，注意：重定向可以修改URL地址，放置表单重复提交..
	 * @param userName 用户名
	 * @param password 密码
	 * @param role     角色
	 * @return 主页地址
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(final Model model, final @RequestParam(name = "userName") String userName,
			final @RequestParam(name = "password") String password,
			final @RequestParam(name = "role") String role, HttpSession session) {
		try {
			// 检查用户信息
			if (StringUtils.equals(role, "user")) {

			} else if (StringUtils.equals(role, "admin")
					|| StringUtils.equals(role, "superAdmin")) { // 检查管理员信息
				// 验证登录功能
				System.out.println(password);
				System.out.println(AESUtil.parseByte2HexStr(AESUtil.encrypt(password)));

				Administrator admin = adminService.checkAdmin(userName,
						AESUtil.parseByte2HexStr(AESUtil.encrypt(password)), role);
				if (admin == null) {// 登录失败
					LOGGER.error("管理员用户校验失败：user" + userName);
					model.addAttribute(ERROR, "用户名或密码输入错误!");
					return "index";
				} else {
					model.addAttribute(USER, admin.getName());
					model.addAttribute(ROLE, role);
				}
			} else {
				model.addAttribute(ERROR, "角色不存在");
				return "index";
			}

		} catch (Exception e) {
			LOGGER.error("登录发生异常，请联系管理员!", e);
			model.addAttribute(ERROR, "登录发生异常，请联系管理员!");
			return "index";
		}

		return "redirect:main";
	}

	/**
	 * 访问主页地址，封装参数.
	 * @param model   model实体
	 * @param request http请求
	 * @return 主页地址
	 */
	@RequestMapping("/main")
	public String main(final Model model, final HttpServletRequest request) {

		// HttpSession session = request.getSession();
		// model.addAttribute("user", "诸葛小坏");
		// model.addAttribute("role", "管理员");

		// model.addAttribute("user", admin);
		return "main";
	}
}
