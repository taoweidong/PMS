package com.pms.service;

import java.util.List;
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

	List<Administrator> selectAdminListById(String id);

	ReturnData updateAdmin(Administrator admin);

	ReturnData deleteAdmin(String ids);

	List<Map<String, Object>> comboAdmin();

	/**
	 * 设置超级管理员.
	 * @param ids
	 * @return
	 */
	ReturnData setSuperAdmin(String ids);

	/**
	 * 取消超级管理员.
	 * @param ids
	 * @return
	 */
	ReturnData cancelSuperAdmin(String ids);

	/**
	 * 新增管理员.
	 * @param admin
	 * @return
	 */
	ReturnData addAdmin(Administrator admin);

	Map<String, Object> dicAdmin();
}
