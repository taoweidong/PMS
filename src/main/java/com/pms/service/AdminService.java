package com.pms.service;

import java.util.Map;

import com.pms.entity.Administrator;
import com.pms.entity.ReturnData;

public interface AdminService {

	/**
	 * 登录功能.
	 * @param userName
	 * @param password
	 * @param role
	 * @return
	 */
	Administrator checkAdmin(final String userName, final String password, final String role);

	Map<String, Object> queryAdmin(Integer page, Integer rows, Administrator administrator);

	Administrator selectAdminById(String id);

	ReturnData updateAdmin(Administrator admin);

	ReturnData deleteAdmin(String ids);
}
