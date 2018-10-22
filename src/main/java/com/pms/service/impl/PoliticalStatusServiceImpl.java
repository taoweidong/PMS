package com.pms.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.pms.entity.Politicalstatus;
import com.pms.entity.ReturnData;
import com.pms.mapper.PoliticalstatusMapper;
import com.pms.service.EmployeeService;
import com.pms.service.PoliticalStatusService;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class PoliticalStatusServiceImpl implements PoliticalStatusService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Autowired
	private PoliticalstatusMapper politicalstatusMapper;

	@Autowired
	private EmployeeService employeeService;

	@Override
	public Map<String, Object> queryPoliticalStatus(Integer page, Integer rows, String type,
			String name) {
		Map<String, Object> result = Maps.newHashMap();

		Example example = new Example(Politicalstatus.class);
		Example.Criteria criteria = example.createCriteria();
		// 模糊查询
		if (!StringUtils.isEmpty(name)) {
			criteria.andLike("name", "%" + name + "%");
		}

		if (!StringUtils.isEmpty(type)) {
			criteria.andLike("type", "%" + type + "%");
		}

		PageHelper.startPage(page, rows);
		List<Politicalstatus> list = politicalstatusMapper.selectByExample(example);
		PageInfo<Politicalstatus> pageInfo = new PageInfo<Politicalstatus>(list);

		result.put("total", pageInfo.getTotal());
		result.put("rows", list);

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

	@Override
	public ReturnData deletePoliticalStatus(String ids) {
		StringBuffer fail = new StringBuffer();

		List<String> list = Lists.newArrayList(StringUtils.split(ids, ","));
		for (String id : list) {

			// 检查是否作为部门领导
			if (employeeService.findPoliticalstatusByType(id)) {
				fail.append("[" + id + "]正在使用，无法删除;");
				continue;
			}
			Politicalstatus admin = new Politicalstatus();
			admin.setType(id);
			int returnDate = politicalstatusMapper.delete(admin);
			if (returnDate <= 0) {
				fail.append("[" + id + "]删除错误;");
			}
		}

		if (StringUtils.isEmpty(fail.toString())) {
			return ReturnData.success();
		} else {
			return ReturnData.fail(StringUtils.removeEnd(fail.toString(), ";"));
		}
	}

	/**
	 * 新增实体
	 */
	@Override
	public ReturnData addPoliticalStatus(Politicalstatus politicalstatus) {
		// 检查该类型是否存在
		Example example = new Example(Politicalstatus.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("name", politicalstatus.getName());
		List<Politicalstatus> returnList = politicalstatusMapper.selectByExample(example);
		if (!CollectionUtils.isEmpty(returnList)) {
			return ReturnData.fail("该类型已存在!");
		}

		int result = politicalstatusMapper.insert(politicalstatus);
		if (result > 0) {
			return ReturnData.success();
		} else {
			return ReturnData.fail("新增失败!");
		}
	}

	/**
	 * 查询单个实体
	 */
	@Override
	public Politicalstatus selectPoliticalStatusByType(String type) {
		return politicalstatusMapper.selectByPrimaryKey(type);
	}

	/**
	 * 更新操作
	 */
	@Override
	public ReturnData updatePoliticalStatus(Politicalstatus politicalstatus) {
		try {
			int result = politicalstatusMapper.updateByPrimaryKey(politicalstatus);
			if (result > 0) {
				return ReturnData.success();
			}
		} catch (Exception e) {
			LOGGER.error("更新异常!", e);
			return ReturnData.fail("更新异常!");
		}

		return ReturnData.success();
	}

}
