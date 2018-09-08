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
import com.pms.entity.Administrator;
import com.pms.mapper.AdministratorMapper;
import com.pms.service.PersionInfoService;

@Service
public class PersionInfoServiceImpl implements PersionInfoService {

	/**
	 * 日志工具.
	 */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(PersionInfoServiceImpl.class);

	/**
	 * 管理员mapper.
	 */
	@Autowired
	private AdministratorMapper administratorMapper;

	@Override
	public Map<String, Object> queryPersionInfo(int pageNum, int pageSize) {

		Map<String, Object> result = Maps.newHashMap();

		PageHelper.startPage(pageNum, pageSize);
		List<Administrator> list = administratorMapper.selectAll();
		PageInfo<Administrator> pageInfo = new PageInfo<Administrator>(list);

		result.put("total", pageInfo.getTotal());
		result.put("rows", list);

		LOGGER.info(JSON.toJSONString(result));

		return result;
	}

	/** Default constructor */
	public PersionInfoServiceImpl() {
	}

}
