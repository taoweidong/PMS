package com.pms.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.pms.entity.Administrator;
import com.pms.mapper.AdministratorMapper;
import com.pms.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AdminServiceImpl.class);

	@Autowired
	private AdministratorMapper administratorMapper;

	/**
	 * 登录功能
	 */
	@Override
	public Administrator checkAdmin(String userName, String password, String role) {
		Administrator admin = null;
		try {

			Administrator record = new Administrator();
			record.setNo(userName);
			record.setPwd(password);

			admin = administratorMapper.selectOne(record);

			System.out.println(JSON.toJSONString(admin));
		} catch (Exception e) {
			LOGGER.error("checkAdmin exception", e);
		}

		return admin;
	}

	/** Default constructor */
	public AdminServiceImpl() {
	}

	@Override
	public Map<String, Object> queryAdmin(Integer page, Integer rows) {
		Map<String, Object> result = Maps.newHashMap();

		PageHelper.startPage(page, rows);
		List<Administrator> list = administratorMapper.selectAll();
		PageInfo<Administrator> pageInfo = new PageInfo<Administrator>(list);

		result.put("total", pageInfo.getTotal());
		result.put("rows", list);

		LOGGER.info(JSON.toJSONString(result));

		return result;
	}

}
