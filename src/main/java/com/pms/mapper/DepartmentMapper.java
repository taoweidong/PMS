package com.pms.mapper;

import java.util.List;
import java.util.Map;

import com.pms.entity.Department;

import tk.mybatis.mapper.common.Mapper;

public interface DepartmentMapper extends Mapper<Department> {

	List<Department> queryDepartment(Map<String, Object> paramMap);
}