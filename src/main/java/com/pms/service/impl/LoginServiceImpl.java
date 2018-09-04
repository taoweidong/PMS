package com.pms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.pms.entity.TAdministrator;
import com.pms.mapper.TAdministratorMapper;
import com.pms.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private TAdministratorMapper administratorMapper;

	/**
	 * 登录功能
	 */
	@Override
	public boolean login(String userName, String password, String role) {

		TAdministrator record = new TAdministrator();
		record.setAdminNo(userName);
		record.setAdminPwd(password);

		TAdministrator result = administratorMapper.selectOne(record);

		System.out.println(JSON.toJSONString(result));

		return false;
	}

}
