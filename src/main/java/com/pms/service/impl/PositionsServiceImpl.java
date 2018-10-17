package com.pms.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.pms.entity.Department;
import com.pms.entity.Employee;
import com.pms.entity.Positionsinfo;
import com.pms.mapper.PositionsinfoMapper;
import com.pms.service.DepartmentService;
import com.pms.service.EmployeeService;
import com.pms.service.PositionsService;

import tk.mybatis.mapper.entity.Example;

@Service
public class PositionsServiceImpl implements PositionsService {

	@Autowired
	private PositionsinfoMapper positionsinfoMapper;

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private EmployeeService employeeService;

	@Override
	public Map<String, Object> queryPositions(Integer page, Integer rows, String name, String depId,
			String startDate, String endDate) {
		Map<String, Object> result = Maps.newHashMap();

		Example example = new Example(Positionsinfo.class);
		Example.Criteria criteria = example.createCriteria();
		// 模糊查询
		if (!StringUtils.isEmpty(name)) {
			criteria.andLike("name", "%" + name + "%");
		}
		// 相等查询
		if (StringUtils.isNotBlank(depId)) {
			criteria.andEqualTo("depId", depId);
		}
		// 筛选时间--在某个时间段内
		if (StringUtils.isNotBlank(startDate)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date dateBegin = null;
			Date dateEnd = null;
			try {
				dateBegin = sdf.parse(startDate);
				dateEnd = sdf.parse(endDate);
			} catch (java.text.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Calendar cal = Calendar.getInstance();
			cal.setTime(dateEnd);
			cal.add(Calendar.DATE, 1);
			criteria.andBetween("ext2", dateBegin, dateEnd);
		}

		PageHelper.startPage(page, rows);
		List<Positionsinfo> list = positionsinfoMapper.selectByExample(example);

		list.forEach(x -> {
			// 设置部门信息
			Department department = departmentService.findDepartmentById(x.getDepId());
			x.setDepName(department.getName());

			// 设置领导名称
			Employee employee = employeeService.selectEmployeeById(department.getLeader());
			x.setDepLeader(employee.getName());
		});

		PageInfo<Positionsinfo> pageInfo = new PageInfo<Positionsinfo>(list);

		result.put("total", pageInfo.getTotal());
		result.put("rows", list);

		return result;
	}

}
