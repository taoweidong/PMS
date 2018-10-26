package com.pms.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.pms.entity.Inductioninfo;
import com.pms.entity.ReturnData;
import com.pms.service.ApplyInductionService;
import com.pms.util.DateUtil;
import com.pms.util.IDGenerator;

@Controller
public class ApplyInductionController {
	/**
	 * 日志工具
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

	@Autowired
	private ApplyInductionService applyInductionService;

	@RequestMapping("/applyInduction")
	public String index() {

		LOGGER.debug("访问主页:ApplyInduction");
		return "ApplyInduction";
	}

	@ResponseBody
	@RequestMapping(value = "/queryApplyInduction", method = RequestMethod.POST)
	public Map<String, Object> queryApplyInduction(HttpServletRequest request,
			@RequestParam("page") Integer page, @RequestParam("rows") Integer rows,
			@RequestParam(value = "posName", defaultValue = StringUtils.EMPTY) String posName,
			@RequestParam(value = "startDate", defaultValue = StringUtils.EMPTY) String startDate,
			@RequestParam(value = "endDate", defaultValue = StringUtils.EMPTY) String endDate,
			@RequestParam(value = "approveState", defaultValue = StringUtils.EMPTY) String approveState) {

		String role = request.getSession().getAttribute("role").toString();
		String userId = StringUtils.EMPTY;// 工号
		// 设置发布人工号，从session中获取数据
		Object user = request.getSession().getAttribute("user");
		if (user instanceof Administrator) {
			userId = ((Administrator) user).getId();
		} else if (user instanceof Employee) {
			userId = ((Employee) user).getNo();
		}

		Map<String, Object> result = applyInductionService.queryApplyInduction(page, rows, posName,
				approveState, startDate, endDate, role, userId);

		return result;

	}

	/**
	 * 删除申请.
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteUserApplyInduction", method = RequestMethod.POST)
	public ReturnData deleteUserApplyInduction(HttpServletRequest request,
			@RequestParam("ids") String ids) {
		String role = request.getSession().getAttribute("role").toString();

		ReturnData result = applyInductionService.deleteUserApplyInduction(ids, role);

		return result;
	}

	/**
	 * 提交申请，修改状态.
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/addUserApprove", method = RequestMethod.POST)
	public ReturnData addUserApprove(@RequestParam("ids") String ids) {

		// 查询原数据
		Inductioninfo inductioninfo = applyInductionService.findInductionById(ids);
		inductioninfo.setExt1("00");

		// 更新状态
		ReturnData result = applyInductionService.updateUserApplyApprove(inductioninfo);

		return result;
	}

	/**
	 * 新增申请.
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/addUserApplyInduction", method = RequestMethod.POST)
	public ReturnData addUserApplyInduction(HttpServletRequest request,
			@RequestParam("posId") String posId, @RequestParam("ext3") String ext3) {

		ReturnData result = new ReturnData();
		try {
			// String role = request.getSession().getAttribute("role").toString();
			String userId = StringUtils.EMPTY;// 工号
			// 设置发布人工号，从session中获取数据
			Object user = request.getSession().getAttribute("user");
			if (user instanceof Administrator) {
				userId = ((Administrator) user).getId();
			} else if (user instanceof Employee) {
				userId = ((Employee) user).getNo();
			}

			Inductioninfo inductioninfo = new Inductioninfo();
			inductioninfo.setId(IDGenerator.getInstance().getID() + "");
			inductioninfo.setPosId(StringUtils.trimToEmpty(posId));
			inductioninfo.setExt3(StringUtils.trimToEmpty(ext3));
			inductioninfo.setEmpNo(userId);
			inductioninfo.setDate(DateUtil.getCurrentDateStr());
			inductioninfo.setExt1("33");

			result = applyInductionService.addUserApplyInduction(inductioninfo);

		} catch (Exception e) {
			LOGGER.error("新增申请发生异常", e);
		}
		return result;
	}

	/**
	 * 更新申请.
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/updateUserApplyApprove", method = RequestMethod.POST)
	public ReturnData updateUserApplyApprove(@RequestParam("id") String id,
			@RequestParam("posId") String posId, @RequestParam("ext3") String ext3)
			throws Exception {

		Inductioninfo inductioninfo = applyInductionService.findInductionById(id);
		inductioninfo.setPosId(StringUtils.trimToEmpty(posId));
		inductioninfo.setExt3(StringUtils.trimToEmpty(ext3));
		inductioninfo.setDate(DateUtil.getCurrentDateStr());

		ReturnData result = applyInductionService.updateUserApplyApprove(inductioninfo);

		return result;
	}
}
