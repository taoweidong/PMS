package com.pms.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
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

}
