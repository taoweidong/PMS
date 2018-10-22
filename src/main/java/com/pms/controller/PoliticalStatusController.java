package com.pms.controller;

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
import com.pms.entity.Politicalstatus;
import com.pms.entity.ReturnData;
import com.pms.service.PoliticalStatusService;
import com.pms.util.DateUtil;
import com.pms.util.StringUtil;

@Controller
public class PoliticalStatusController {
	/**
	 * 日志工具
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

	@Autowired
	private PoliticalStatusService politicalStatusService;

	@RequestMapping("/politicalStatusManager")
	public String index() {
		LOGGER.debug("访问主页:PoliticalStatusManager");

		return "PoliticalStatusManager";
	}

	/**
	 * 查詢列表信息.
	 * @param page 页码
	 * @param rows 页数
	 * @return 列表集合
	 */
	@ResponseBody
	@RequestMapping(value = "/queryPoliticalStatus", method = RequestMethod.POST)
	public Map<String, Object> queryPoliticalStatus(@RequestParam("page") Integer page,
			@RequestParam("rows") Integer rows,
			@RequestParam(value = "type", defaultValue = StringUtils.EMPTY) String type,
			@RequestParam(value = "name", defaultValue = StringUtils.EMPTY) String name) {

		Map<String, Object> result = politicalStatusService.queryPoliticalStatus(page, rows, type,
				name);

		return result;

	}

	/**
	 * 查询状态.
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryPoliticalStatusList", method = RequestMethod.POST)
	public List<Map<String, Object>> queryPoliticalStatusList() {
		List<Map<String, Object>> returnData = Lists.newArrayList();

		Map<String, Object> param = Maps.newHashMap();
		param.put("psType", "");
		param.put("psName", "请选择...");

		returnData.add(param);

		List<Map<String, Object>> result = politicalStatusService.queryPoliticalStatusList();

		returnData.addAll(result);

		return returnData;

	}

	/**
	 * 删除信息.
	 * @param ids
	 * @return 結果集
	 */
	@ResponseBody
	@RequestMapping(value = "/deletePoliticalStatus", method = RequestMethod.POST)
	public ReturnData deletePoliticalStatus(@RequestParam("ids") String ids) {

		ReturnData result = politicalStatusService.deletePoliticalStatus(ids);

		return result;

	}

	@ResponseBody
	@RequestMapping(value = "/addPoliticalStatus", method = RequestMethod.POST)
	public ReturnData addPoliticalStatus(
			@RequestParam(value = "name", defaultValue = StringUtils.EMPTY) String name,
			@RequestParam(value = "ext2", defaultValue = StringUtils.EMPTY) String ext2) {

		if (StringUtils.isEmpty(name)) {
			return ReturnData.fail("名称不能为空!");
		}

		Politicalstatus politicalstatus = new Politicalstatus();
		try {
			politicalstatus.setType(StringUtil.generateShortUuid());
			politicalstatus.setName(StringUtils.trimToEmpty(name));
			politicalstatus.setExt1(DateUtil.getCurrentDateStr());
			politicalstatus.setExt2(StringUtils.trimToEmpty(ext2));

			return politicalStatusService.addPoliticalStatus(politicalstatus);

		} catch (Exception e) {
			LOGGER.error("新增发生异常", e);
			return ReturnData.fail("新增发生异常");
		}

	}

	@ResponseBody
	@RequestMapping(value = "/updatePoliticalStatus", method = RequestMethod.POST)
	public ReturnData updatePoliticalStatus(
			@RequestParam(value = "type", defaultValue = StringUtils.EMPTY) String type,
			@RequestParam(value = "name", defaultValue = StringUtils.EMPTY) String name,
			@RequestParam(value = "ext2", defaultValue = StringUtils.EMPTY) String ext2) {

		if (StringUtils.isEmpty(name)) {
			return ReturnData.fail("名称不能为空!");
		}

		Politicalstatus politicalstatus = politicalStatusService.selectPoliticalStatusByType(type);
		try {
			politicalstatus.setType(StringUtils.trimToEmpty(type));
			politicalstatus.setName(StringUtils.trimToEmpty(name));
			politicalstatus.setExt1(DateUtil.getCurrentDateStr());
			politicalstatus.setExt2(StringUtils.trimToEmpty(ext2));

			return politicalStatusService.updatePoliticalStatus(politicalstatus);

		} catch (Exception e) {

			LOGGER.error("更新发生异常", e);
			return ReturnData.fail("更新发生异常");
		}
	}

}
