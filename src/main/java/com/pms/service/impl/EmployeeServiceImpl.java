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
import com.pms.entity.Employee;
import com.pms.mapper.EmployeeMapper;
import com.pms.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Autowired
	private EmployeeMapper employeeMapper;

	@Override
	public Employee checkEmployee(String userName, String password, String role) {
		Employee employee = new Employee();
		try {

			Employee record = new Employee();
			record.setNo(userName);
			record.setPwd(password);

			employee = employeeMapper.selectOne(record);

			System.out.println(JSON.toJSONString(employee));
		} catch (Exception e) {
			LOGGER.error("checkEmployee exception", e);
		}

		return employee;
	}

	@Override
	public Map<String, Object> queryEmployee(Integer page, Integer rows) {

		Map<String, Object> paramMap = Maps.newHashMap();

		Map<String, Object> result = Maps.newHashMap();

		PageHelper.startPage(page, rows);
		List<Employee> list = employeeMapper.queryEmployeeAll(paramMap);
		PageInfo<Employee> pageInfo = new PageInfo<Employee>(list);

		result.put("total", pageInfo.getTotal());
		result.put("rows", list);

		LOGGER.info(JSON.toJSONString(result));

		return result;
	}

}
