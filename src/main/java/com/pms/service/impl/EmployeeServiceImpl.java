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
import com.pms.entity.Employee;
import com.pms.entity.ReturnData;
import com.pms.mapper.EmployeeMapper;
import com.pms.service.DepartmentService;
import com.pms.service.EmployeeService;
import com.pms.service.PoliticalStatusService;
import com.pms.util.DictionaryConversion;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Autowired
	private EmployeeMapper employeeMapper;

	@Autowired
	private PoliticalStatusService politicalStatusService;

	@Autowired
	private DepartmentService departmentService;

	@Override
	public Employee checkEmployee(String userName, String password, String role) {
		Employee employee = new Employee();
		try {

			Employee record = new Employee();
			record.setNo(userName);
			record.setPwd(password);

			employee = employeeMapper.selectOne(record);

			System.out.println(JSON.toJSONString(employee));
		} catch (Exception e) {
			LOGGER.error("checkEmployee exception", e);
		}

		return employee;
	}

	/**
	 * 查询
	 */
	@Override
	public Map<String, Object> queryEmployee(Integer page, Integer rows, Employee employee) {

		PageHelper.startPage(page, rows);
		List<Employee> list = employeeMapper.select(employee);

		// 性别转中文描述
		list.forEach(x -> {
			x.setSexDesc(DictionaryConversion.sexDictionaryToDesc(x.getSex()));
		});

		// 政治面貌转中文描述
		Map<String, Object> cboList = politicalStatusService.queryPoliticalStatusCbo();
		list.forEach(x -> {
			String key = x.getPsId();
			if (cboList.containsKey(key)) {
				x.setPsIdDesc(cboList.get(key).toString());
			}

		});

		PageInfo<Employee> pageInfo = new PageInfo<Employee>(list);

		return ReturnData.getEasyUIData(pageInfo.getTotal(), list);
	}

	@Override
	public ReturnData updateEmployee(Employee employee) {

		int result = employeeMapper.updateByPrimaryKey(employee);
		if (result > 0) {
			return ReturnData.success();
		} else {
			return ReturnData.fail("更新失败!");
		}

	}

	@Override
	public List<Map<String, Object>> comboEmployee() {
		List<Map<String, Object>> resultMap = Lists.newArrayList();

		List<Employee> listEmployee = employeeMapper.selectAll();
		for (Employee item : listEmployee) {
			Map<String, Object> param = Maps.newHashMap();
			param.put("EMP_NO", item.getNo());
			param.put("EMP_NAME", item.getName());
			resultMap.add(param);
			param = null;
		}

		return resultMap;

	}

	/**
	 * 删除用户信息.
	 */
	@Override
	public ReturnData deleteEmployee(String ids) {
		StringBuffer fail = new StringBuffer();

		List<String> list = Lists.newArrayList(StringUtils.split(ids, ","));
		for (String id : list) {

			// 检查是否作为部门领导
			if (departmentService.findDepartmentByNo(id)) {
				fail.append("[" + id + "]正在使用，无法删除;");
				continue;
			}
			Employee admin = new Employee();
			admin.setNo(id);
			int returnDate = employeeMapper.delete(admin);
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
	 * 新增用户信息.
	 */
	@Override
	public ReturnData addEmployee(Employee employee) {

		// 检查该用户是否已经存在
		Employee flag = employeeMapper.selectByPrimaryKey(employee.getNo());
		if (flag != null) {
			return ReturnData.fail("该员工已存在!");
		}

		int result = employeeMapper.insertSelective(employee);
		if (result > 0) {
			return ReturnData.success();
		} else {
			return ReturnData.fail("新增失败!");
		}
	}

	@Override
	public Employee selectEmployeeById(String no) {
		// TODO Auto-generated method stub
		return employeeMapper.selectByPrimaryKey(no);
	}

}
