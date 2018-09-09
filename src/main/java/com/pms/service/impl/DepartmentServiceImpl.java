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
import com.pms.entity.Department;
import com.pms.mapper.DepartmentMapper;
import com.pms.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Autowired
	private DepartmentMapper departmentMapper;

	@Override
	public Map<String, Object> queryDepartment(Integer page, Integer rows) {
		Map<String, Object> result = Maps.newHashMap();
		Map<String, Object> paramMap = Maps.newHashMap();

		PageHelper.startPage(page, rows);
		List<Department> list = departmentMapper.queryDepartment(paramMap);
		PageInfo<Department> pageInfo = new PageInfo<Department>(list);

		result.put("total", pageInfo.getTotal());
		result.put("rows", list);

		LOGGER.info(JSON.toJSONString(result));

		return result;
	}

}
