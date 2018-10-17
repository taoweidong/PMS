package com.pms.service;

import java.util.List;
import java.util.Map;

import com.pms.entity.Department;
import com.pms.entity.ReturnData;

public interface DepartmentService {

	Map<String, Object> queryDepartment(Integer page, Integer rows, Map<String, Object> paramMap);

	ReturnData updateDepartment(Department department);

	ReturnData deleteDepartment(String ids);

	ReturnData addDepartment(Department department);

	/**
	 * 根据部门id查询部门信息.
	 * @param depNo
	 * @return
	 */
	Department findDepartmentById(String depNo);

	/**
	 * 通过领导工号查询部门信息.
	 * @param empNo 领导工号
	 * @return 部门信息
	 */
	boolean findDepartmentByNo(String empNo);

	/**
	 * 下拉框获取部门信息.
	 * @return
	 */
	List<Map<String, Object>> cboDepartmentList();

}
