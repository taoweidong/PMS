package com.pms.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.pms.entity.Inductioninfo;
import com.pms.mapper.InductioninfoMapper;
import com.pms.service.ApplyInductionService;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class ApplyInductionServiceImpl implements ApplyInductionService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ApplyInductionServiceImpl.class);

	@Autowired
	private InductioninfoMapper inductioninfoMapper;

	@Override
	public Map<String, Object> queryApplyInduction(Integer page, Integer rows) {
		Map<String, Object> result = Maps.newHashMap();

		PageHelper.startPage(page, rows);
		List<Inductioninfo> list = inductioninfoMapper.selectAll();
		PageInfo<Inductioninfo> pageInfo = new PageInfo<Inductioninfo>(list);

		result.put("total", pageInfo.getTotal());
		result.put("rows", list);

		LOGGER.info(JSON.toJSONString(result));

		return result;
	}

	@Override
	public boolean findInductionByPosId(String id) {
		boolean resultFlag = false;
		try {
			Example example = new Example(Inductioninfo.class);
			Criteria criteria = example.createCriteria();
			criteria.andCondition("POS_ID = " + id);
			List<Inductioninfo> returnList = inductioninfoMapper.selectByExample(example);
			if (!CollectionUtils.isEmpty(returnList)) {
				resultFlag = true;
			} else {
				resultFlag = false;
			}

		} catch (Exception e) {
			LOGGER.error("findInductionByPosId 发生异常!", e);
			resultFlag = false;
		}

		return resultFlag;
	}

}
