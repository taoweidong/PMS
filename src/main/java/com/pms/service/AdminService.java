package com.pms.service;

import java.util.Map;

import com.pms.entity.Administrator;

public interface AdminService {

	/**
	 * 登录功能.
	 * @param userName
	 * @param password
	 * @param role
	 * @return
	 */
	Administrator checkAdmin(final String userName, final String password, final String role);

	Map<String, Object> queryAdmin(Integer page, Integer rows);
}
