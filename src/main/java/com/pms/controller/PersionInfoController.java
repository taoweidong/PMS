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
import com.pms.entity.ReturnData;
import com.pms.service.PersionInfoService;

@Controller
public class PersionInfoController {
	/**
	 * 日志工具
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

	@Autowired
	private PersionInfoService persionInfoManageService;

	/**
	 * 个人信息管理.
	 * @return
	 */
	@RequestMapping("/persionInfoManage")
	public String persionInfoManage() {
		// 如果你的spring mvc配置文件中配置了跳转后缀则不需要加.jsp后缀
		// 即直接return "demo/pagefile";
		LOGGER.debug("个人信息管理");

		return "PersionInfoManage";
	}

	/**
	 * 查询所有信息.
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/PersionInfo", method = RequestMethod.POST)
	public Map<String, Object> queryPersionInfo(@RequestParam("page") Integer page,
			@RequestParam("rows") Integer rows) {

		System.out.println("rows:" + rows + "   page:" + page);
		Map<String, Object> result = persionInfoManageService.queryPersionInfo(page, rows);

		System.out.println(JSON.toJSONString(result));

		return result;

	}

	@ResponseBody
	@RequestMapping(value = "/updatePersional", method = RequestMethod.POST)
	public ReturnData updatePersional(@RequestParam("no") String no,
			@RequestParam("name") String name, @RequestParam("phone") String phone,
			@RequestParam("ext1") String ext1) throws Exception {

		// if (!CheckInfo.isMobileNO(StringUtils.trimToEmpty(phone))) {
		// return ReturnData.fail("手机号格式不正确!");
		// }
		//
		// Administrator admin = new Administrator();
		// admin.setId(StringUtil.GetUUID());
		// admin.setNo(no);
		// admin.setName(name);
		// admin.setPhone(phone);
		// admin.setExt1(ext1);
		// admin.setExt2(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date().getTime()));
		// admin.setExt3(Constant.GENERAL_MANAGER);
		//
		// ReturnData result = adminService.addAdmin(admin);
		return ReturnData.success();
	}

	/** Default constructor */
	public PersionInfoController() {
	}
}
