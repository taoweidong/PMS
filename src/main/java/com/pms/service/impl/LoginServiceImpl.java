package com.pms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.pms.entity.Administrator;
import com.pms.mapper.AdministratorMapper;
import com.pms.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private AdministratorMapper administratorMapper;

	/**
	 * 登录功能
	 */
	@Override
	public boolean login(String userName, String password, String role) {

		Administrator record = new Administrator();
		record.setNo(userName);
		record.setPwd(password);

		Administrator result = administratorMapper.selectOne(record);

		System.out.println(JSON.toJSONString(result));

		return false;
	}

}
