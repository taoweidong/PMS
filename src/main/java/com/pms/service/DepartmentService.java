package com.pms.service;

import java.util.Map;

public interface DepartmentService {

	Map<String, Object> queryDepartment(Integer page, Integer rows, Map<String, Object> paramMap);

}
