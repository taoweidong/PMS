package com.pms.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.pms.entity.Administrator;
import com.pms.entity.ReturnData;
import com.pms.mapper.AdministratorMapper;
import com.pms.service.AdminService;
import com.pms.util.Constant;
import com.pms.util.StringUtil;

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

	@Override
	public Map<String, Object> queryAdmin(Integer page, Integer rows, Administrator administrator) {
		Map<String, Object> result = Maps.newHashMap();

		PageHelper.startPage(page, rows);
		List<Administrator> list = administratorMapper.select(administrator);
		PageInfo<Administrator> pageInfo = new PageInfo<Administrator>(list);

		result.put("total", pageInfo.getTotal());
		result.put("rows", list);

		LOGGER.info(JSON.toJSONString(result));

		return result;
	}

	@Override
	public Administrator selectAdminById(String id) {
		return administratorMapper.selectByPrimaryKey(id);
	}

	/**
	 * 更新
	 */
	@Override
	public ReturnData updateAdmin(Administrator admin) {
		try {
			int returnInt = 0;
			if (StringUtils.isEmpty(admin.getId())) {

				Administrator addAdmin = new Administrator();
				addAdmin.setNo(admin.getNo());

				// 检查是否已存在
				List<Administrator> adminList = administratorMapper.select(addAdmin);
				if (adminList != null && adminList.size() > 0) {
					return ReturnData.fail("该工号已存在！");
				}

				admin.setId(StringUtil.GetUUID());
				returnInt = administratorMapper.insert(admin);
			} else {
				returnInt = administratorMapper.updateByPrimaryKey(admin);
			}

			if (returnInt > 0) {
				return ReturnData.success();
			} else {
				return ReturnData.fail("更新失败！");
			}
		} catch (Exception e) {
			LOGGER.error("更新发生异常", e);
			return ReturnData.fail("更新发生异常！");
		}
	}

	/**
	 * 删除管理员
	 */
	@Override
	public ReturnData deleteAdmin(String ids) {

		StringBuffer fail = new StringBuffer();

		List<String> list = Lists.newArrayList(StringUtils.split(ids, ","));
		for (String id : list) {
			Administrator admin = new Administrator();
			admin.setId(id);
			int returnDate = administratorMapper.delete(admin);
			if (returnDate <= 0) {
				fail.append("[" + id + "]删除错误;");
			}
		}

		if (StringUtils.isEmpty(fail.toString())) {
			return ReturnData.success();
		} else {
			return ReturnData.fail(StringUtils.removeEnd(fail.toString(), ";"));
		}

	}

	/**
	 * 设置超级管理员
	 */
	@Override
	public ReturnData setSuperAdmin(String ids) {
		StringBuffer fail = new StringBuffer();

		List<String> list = Lists.newArrayList(StringUtils.split(ids, ","));
		for (String id : list) {
			Administrator admin = new Administrator();
			admin.setId(id);
			admin = administratorMapper.selectOne(admin);
			if (admin == null) {
				fail.append("[" + id + "]取消失败;");
				continue;
			}
			admin.setExt3(Constant.SUPER_MANAGER);
			int returnDate = administratorMapper.updateByPrimaryKey(admin);
			if (returnDate <= 0) {
				fail.append("[" + id + "]设置发生错误;");
			}
		}

		if (StringUtils.isEmpty(fail.toString())) {
			return ReturnData.success();
		} else {
			return ReturnData.fail(StringUtils.removeEnd(fail.toString(), ";"));
		}
	}

	@Override
	public ReturnData cancelSuperAdmin(String ids) {
		StringBuffer fail = new StringBuffer();

		List<String> list = Lists.newArrayList(StringUtils.split(ids, ","));
		for (String id : list) {
			Administrator admin = new Administrator();
			admin.setId(id);
			admin = administratorMapper.selectOne(admin);
			if (admin == null) {
				fail.append("[" + id + "]取消失败;");
				continue;
			}
			admin.setExt3(Constant.GENERAL_MANAGER);
			int returnDate = administratorMapper.updateByPrimaryKey(admin);
			if (returnDate <= 0) {
				fail.append("[" + id + "]取消失败;");
			}
		}

		if (StringUtils.isEmpty(fail.toString())) {
			return ReturnData.success();
		} else {
			return ReturnData.fail(StringUtils.removeEnd(fail.toString(), ";"));
		}
	}

	@Override
	public List<Administrator> selectAdminListById(String id) {
		Administrator admin = new Administrator();
		admin.setNo(id);
		List<Administrator> list = administratorMapper.select(admin);

		return list;
	}

	@Override
	public ReturnData addAdmin(Administrator admin) {
		// 检查该用户是否已经存在
		Administrator one = new Administrator();
		one.setNo(admin.getNo());
		Administrator flag = administratorMapper.selectOne(one);
		if (flag != null) {
			return ReturnData.fail("该员工已存在!");
		}

		int result = administratorMapper.insertSelective(admin);
		if (result > 0) {
			return ReturnData.success();
		} else {
			return ReturnData.fail("新增失败!");
		}
	}

	@Override
	public List<Map<String, Object>> comboAdmin() {
		List<Map<String, Object>> resultMap = Lists.newArrayList();

		List<Administrator> listEmployee = administratorMapper.selectAll();
		for (Administrator item : listEmployee) {
			Map<String, Object> param = Maps.newHashMap();
			param.put("EMP_NO", item.getId());
			param.put("EMP_NAME", item.getName());
			resultMap.add(param);
			param = null;
		}

		return resultMap;
	}

	@Override
	public Map<String, Object> dicAdmin() {
		Map<String, Object> param = Maps.newHashMap();

		List<Administrator> listEmployee = administratorMapper.selectAll();
		for (Administrator item : listEmployee) {
			param.put(item.getId(), item.getName());
		}

		return param;
	}

}
