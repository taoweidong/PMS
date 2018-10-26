package com.pms.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
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
import com.pms.entity.Department;
import com.pms.entity.ReturnData;
import com.pms.mapper.DepartmentMapper;
import com.pms.service.DepartmentService;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Autowired
	private DepartmentMapper departmentMapper;

	@Override
	public Map<String, Object> queryDepartment(Integer page, Integer rows,
			Map<String, Object> paramMap) {
		Map<String, Object> result = Maps.newHashMap();

		System.out.println(JSON.toJSONString(paramMap));

		PageHelper.startPage(page, rows);
		List<Department> list = departmentMapper.queryDepartment(paramMap);
		PageInfo<Department> pageInfo = new PageInfo<Department>(list);

		result.put("total", pageInfo.getTotal());
		result.put("rows", list);

		LOGGER.info(JSON.toJSONString(result));

		return result;
	}

	/**
	 * 更新操作
	 */
	@Override
	public ReturnData updateDepartment(Department department) {
		try {
			int result = departmentMapper.updateByPrimaryKey(department);
			if (result > 0) {
				return ReturnData.success();
			}
		} catch (Exception e) {
			LOGGER.error("更新异常!", e);
			return ReturnData.fail("更新异常!");
		}

		return ReturnData.success();
	}

	@Override
	public ReturnData deleteDepartment(String ids) {
		StringBuffer fail = new StringBuffer();

		List<String> list = Lists.newArrayList(StringUtils.split(ids, ","));
		for (String id : list) {
			Department department = new Department();
			department.setId(id);
			int returnDate = departmentMapper.delete(department);
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
	 * 新增
	 */
	@Override
	public ReturnData addDepartment(Department department) {

		int result = departmentMapper.insert(department);
		if (result > 0) {
			return ReturnData.success();
		} else {
			return ReturnData.fail("新增失败!");
		}

	}

	@Override
	public boolean findDepartmentByNo(String empNo) {
		boolean resultFlag = false;
		try {
			Example example = new Example(Department.class);
			Criteria criteria = example.createCriteria();
			criteria.andCondition("DEP_LEADER = " + empNo);
			List<Department> returnList = departmentMapper.selectByExample(example);
			if (!CollectionUtils.isEmpty(returnList)) {
				resultFlag = true;
			} else {
				resultFlag = false;
			}

		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error("findDepartmentByNo 发生异常!", e);
			resultFlag = false;
		}

		return resultFlag;
	}

	@Override
	public Department findDepartmentById(String depNo) {
		return departmentMapper.selectByPrimaryKey(depNo);
	}

	@Override
	public List<Map<String, Object>> cboDepartmentList() {
		List<Map<String, Object>> resultMap = Lists.newArrayList();

		List<Department> listEmployee = departmentMapper.selectAll();
		for (Department item : listEmployee) {
			Map<String, Object> param = Maps.newHashMap();
			param.put("id", item.getId());
			param.put("name", item.getName());
			resultMap.add(param);
			param = null;
		}

		return resultMap;
	}

}
