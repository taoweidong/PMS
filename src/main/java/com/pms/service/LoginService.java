package com.pms.service;

public interface LoginService {

	/**
	 * 登录功能.
	 * @param userName
	 * @param password
	 * @param role
	 * @return
	 */
	boolean login(final String userName, final String password, final String role);
}
