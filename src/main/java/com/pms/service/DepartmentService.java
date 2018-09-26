package com.pms.service;

import java.util.Map;

import com.pms.entity.Department;
import com.pms.entity.ReturnData;

public interface DepartmentService {

	Map<String, Object> queryDepartment(Integer page, Integer rows, Map<String, Object> paramMap);

	ReturnData updateDepartment(Department department);

	ReturnData deleteDepartment(String ids);

	ReturnData addDepartment(Department department);

}
