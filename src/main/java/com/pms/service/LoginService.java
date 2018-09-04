package com.pms.service;

public interface LoginService {

	/**
	 * 登录功能.
	 * @param userName
	 * @param password
	 * @param role
	 * @return
	 */
	boolean login(String userName, String password, String role);
}
