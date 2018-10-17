package com.pms.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.pms.entity.Politicalstatus;
import com.pms.mapper.PoliticalstatusMapper;
import com.pms.service.PoliticalStatusService;

@Service
public class PoliticalStatusServiceImpl implements PoliticalStatusService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Autowired
	private PoliticalstatusMapper politicalstatusMapper;

	@Override
	public Map<String, Object> queryPoliticalStatus(Integer page, Integer rows) {
		Map<String, Object> result = Maps.newHashMap();

		PageHelper.startPage(page, rows);
		List<Politicalstatus> list = politicalstatusMapper.selectAll();
		PageInfo<Politicalstatus> pageInfo = new PageInfo<Politicalstatus>(list);

		result.put("total", pageInfo.getTotal());
		result.put("rows", list);

		LOGGER.info(JSON.toJSONString(result));

		return result;
	}

	/**
	 * 查询下拉框数据
	 */
	@Override
	public List<Map<String, Object>> queryPoliticalStatusList() {
		List<Map<String, Object>> resultMap = Lists.newArrayList();

		List<Politicalstatus> listEmployee = politicalstatusMapper.selectAll();
		for (Politicalstatus item : listEmployee) {
			Map<String, Object> param = Maps.newHashMap();
			param.put("psType", item.getType());
			param.put("psName", item.getName());
			resultMap.add(param);
			param = null;
		}

		return resultMap;
	}

	@Override
	public Map<String, Object> queryPoliticalStatusCbo() {
		
		Map<String, Object> param = Maps.newHashMap();
		List<Politicalstatus> listEmployee = politicalstatusMapper.selectAll();
		for (Politicalstatus item : listEmployee) {
			param.put(item.getType(), item.getName());
		}
		return param;
	}

}
