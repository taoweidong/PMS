package com.pms.controller;

import java.math.BigDecimal;
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

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.pms.entity.Positionsinfo;
import com.pms.entity.ReturnData;
import com.pms.service.PositionsService;
import com.pms.util.DateUtil;

@Controller
public class PositionsController {
	/**
	 * 日志工具
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

	@Autowired
	private PositionsService positionsService;

	@RequestMapping("/positionsManage")
	public String index() {

		LOGGER.debug("访问主页:PositionsManage");
		return "PositionsManage";
	}

	@ResponseBody
	@RequestMapping(value = "/queryPositions", method = RequestMethod.POST)
	public Map<String, Object> queryPositions(
			@RequestParam(value = "POS_NAME", defaultValue = StringUtils.EMPTY) String name,
			@RequestParam(value = "DEP_ID", defaultValue = StringUtils.EMPTY) String depId,
			@RequestParam(value = "startDate", defaultValue = StringUtils.EMPTY) String startDate,
			@RequestParam(value = "endDate", defaultValue = StringUtils.EMPTY) String endDate,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "rows", defaultValue = "15") Integer rows) {

		Map<String, Object> result = positionsService.queryPositions(page, rows, name, depId,
				startDate, endDate);

		return result;

	}

	@ResponseBody
	@RequestMapping(value = "/updatePositions", method = RequestMethod.POST)
	public ReturnData updatePositions(
			@RequestParam(value = "id", defaultValue = StringUtils.EMPTY) String id,
			@RequestParam(value = "name", defaultValue = StringUtils.EMPTY) String name,
			@RequestParam(value = "depId", defaultValue = StringUtils.EMPTY) String depId,
			@RequestParam(value = "salary", defaultValue = StringUtils.EMPTY) String salary,
			@RequestParam(value = "allowance", defaultValue = StringUtils.EMPTY) String allowance,
			@RequestParam(value = "perquisites", defaultValue = StringUtils.EMPTY) String perquisites,
			@RequestParam(value = "ext1", defaultValue = StringUtils.EMPTY) String ext1,
			@RequestParam(value = "content", defaultValue = StringUtils.EMPTY) String content) {

		Positionsinfo positionsInfo = positionsService.selectPositionsInfoById(id);

		try {

			positionsInfo.setId(StringUtils.trimToEmpty(id));
			positionsInfo.setName(StringUtils.trimToEmpty(name));
			positionsInfo.setDepId(StringUtils.trimToEmpty(depId));
			positionsInfo.setSalary(new BigDecimal(StringUtils.trimToEmpty(salary)));
			positionsInfo.setAllowance(new BigDecimal(StringUtils.trimToEmpty(allowance)));
			positionsInfo.setPerquisites(new BigDecimal(StringUtils.trimToEmpty(perquisites)));
			positionsInfo.setExt1(StringUtils.trimToEmpty(ext1));
			positionsInfo.setExt2(DateUtil.getCurrentDateStr());
			positionsInfo.setContent(StringUtils.trimToEmpty(content));

			return positionsService.updatePositionsInfo(positionsInfo);

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
	@RequestMapping(value = "/addPositions", method = RequestMethod.POST)
	public ReturnData addPositions(
			@RequestParam(value = "id", defaultValue = StringUtils.EMPTY) String id,
			@RequestParam(value = "name", defaultValue = StringUtils.EMPTY) String name,
			@RequestParam(value = "depId", defaultValue = StringUtils.EMPTY) String depId,
			@RequestParam(value = "salary", defaultValue = StringUtils.EMPTY) String salary,
			@RequestParam(value = "psId", defaultValue = StringUtils.EMPTY) String psId,
			@RequestParam(value = "allowance", defaultValue = StringUtils.EMPTY) String allowance,
			@RequestParam(value = "perquisites", defaultValue = StringUtils.EMPTY) String perquisites,
			@RequestParam(value = "ext1", defaultValue = StringUtils.EMPTY) String ext1,
			@RequestParam(value = "content", defaultValue = StringUtils.EMPTY) String content) {

		Positionsinfo positionsInfo = new Positionsinfo();
		try {
			positionsInfo.setId(StringUtils.trimToEmpty(id));
			positionsInfo.setName(StringUtils.trimToEmpty(name));
			positionsInfo.setDepId(StringUtils.trimToEmpty(depId));
			positionsInfo.setSalary(new BigDecimal(StringUtils.trimToEmpty(salary)));
			positionsInfo.setAllowance(new BigDecimal(StringUtils.trimToEmpty(allowance)));
			positionsInfo.setPerquisites(new BigDecimal(StringUtils.trimToEmpty(perquisites)));
			positionsInfo.setExt1(StringUtils.trimToEmpty(ext1));
			positionsInfo.setExt2(DateUtil.getCurrentDateStr());
			positionsInfo.setContent(StringUtils.trimToEmpty(content));

			return positionsService.addPositionsinfo(positionsInfo);

		} catch (Exception e) {
			LOGGER.error("新增发生异常", e);
			return ReturnData.fail("新增发生异常");
		}

	}

	@ResponseBody
	@RequestMapping(value = "/deletePositions", method = RequestMethod.POST)
	public ReturnData deletePositions(@RequestParam("ids") String ids) {

		ReturnData result = positionsService.deletePositionsinfo(ids);

		return result;

	}

	/**
	 * 岗位信息下拉框.
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/cboPositionsinfoList", method = RequestMethod.POST)
	public List<Map<String, Object>> cboPositionsinfoList() {
		List<Map<String, Object>> returnData = Lists.newArrayList();

		Map<String, Object> param = Maps.newHashMap();
		param.put("id", "");
		param.put("name", "请选择...");

		returnData.add(param);

		List<Map<String, Object>> result = positionsService.cboPositionsinfoList();

		returnData.addAll(result);

		return returnData;

	}

}
