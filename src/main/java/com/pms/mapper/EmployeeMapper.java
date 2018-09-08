package com.pms.mapper;

import java.util.List;
import java.util.Map;

import com.pms.entity.Employee;

import tk.mybatis.mapper.common.Mapper;

public interface EmployeeMapper extends Mapper<Employee> {

	List<Employee> queryEmployeeAll(Map<String, Object> paramMap);
}