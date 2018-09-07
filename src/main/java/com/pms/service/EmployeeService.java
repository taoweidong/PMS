package com.pms.service;

import com.pms.entity.Employee;

public interface EmployeeService {

	/**
	 * 检查用户是否争取.
	 * @param userName
	 * @param password
	 * @param role
	 * @return
	 */
	Employee checkEmployee(final String userName, final String password, final String role);

}
