package com.pms.service;

import java.util.List;
import java.util.Map;

import com.pms.entity.Employee;
import com.pms.entity.ReturnData;

public interface EmployeeService {

	/**
	 * 检查用户是否争取.
	 * @param userName
	 * @param password
	 * @param role
	 * @return
	 */
	Employee checkEmployee(final String userName, final String password, final String role);

	Map<String, Object> queryEmployee(Integer page, Integer rows, Employee employee);

	/**
	 * 更新用户信息.
	 * @param employee
	 * @return
	 */
	ReturnData updateEmployee(Employee employee);

	/**
	 * 新增.
	 * @param employee
	 * @return
	 */
	ReturnData addEmployee(Employee employee);

	List<Map<String, Object>> comboEmployee();

	/**
	 * 删除用户信息.
	 * @param ids
	 * @return
	 */
	ReturnData deleteEmployee(String ids);

}
