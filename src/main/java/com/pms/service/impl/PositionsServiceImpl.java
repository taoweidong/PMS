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
import com.google.common.collect.Maps;
import com.pms.entity.Positionsinfo;
import com.pms.mapper.PositionsinfoMapper;
import com.pms.service.PositionsService;

@Service
public class PositionsServiceImpl implements PositionsService {

	private static final Logger LOGGER = LoggerFactory.getLogger(PositionsServiceImpl.class);

	@Autowired
	private PositionsinfoMapper positionsinfoMapper;

	@Override
	public Map<String, Object> queryPositions(Integer page, Integer rows) {
		Map<String, Object> result = Maps.newHashMap();

		PageHelper.startPage(page, rows);
		List<Positionsinfo> list = positionsinfoMapper.selectAll();
		PageInfo<Positionsinfo> pageInfo = new PageInfo<Positionsinfo>(list);

		result.put("total", pageInfo.getTotal());
		result.put("rows", list);

		LOGGER.info(JSON.toJSONString(result));

		return result;
	}

}
