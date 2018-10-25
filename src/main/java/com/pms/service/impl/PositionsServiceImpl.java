package com.pms.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.pms.entity.Department;
import com.pms.entity.Employee;
import com.pms.entity.Positionsinfo;
import com.pms.entity.ReturnData;
import com.pms.mapper.PositionsinfoMapper;
import com.pms.service.ApplyInductionService;
import com.pms.service.DepartmentService;
import com.pms.service.EmployeeService;
import com.pms.service.PositionsService;

import tk.mybatis.mapper.entity.Example;

/**
 * 岗位信息服务.
 * @author Taowd
 * @version 2018年10月18日
 * @see PositionsServiceImpl
 */
@Service
public class PositionsServiceImpl implements PositionsService {

	/**
	 * 日志工具.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(PositionsServiceImpl.class);

	@Autowired
	private PositionsinfoMapper positionsinfoMapper;

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private ApplyInductionService applyInductionService;

	/**
	 * 查询岗位详情
	 */
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

	/**
	 * 根据id查询岗位信息
	 */
	@Override
	public Positionsinfo selectPositionsInfoById(String id) {
		return positionsinfoMapper.selectByPrimaryKey(id);
	}

	/**
	 * 更新操作
	 */
	@Override
	public ReturnData updatePositionsInfo(Positionsinfo positionsInfo) {
		try {
			int result = positionsinfoMapper.updateByPrimaryKey(positionsInfo);
			if (result > 0) {
				return ReturnData.success();
			}
		} catch (Exception e) {
			LOGGER.error("更新异常!", e);
			return ReturnData.fail("更新异常!");
		}

		return ReturnData.success();
	}

	/**
	 * 新增实体.
	 */
	@Override
	public ReturnData addPositionsinfo(Positionsinfo positionsInfo) {
		try {
			int result = positionsinfoMapper.insert(positionsInfo);
			if (result > 0) {
				return ReturnData.success();
			}
		} catch (Exception e) {
			LOGGER.error("新增异常!", e);
			return ReturnData.fail("新增异常!");
		}

		return ReturnData.success();
	}

	/**
	 * 删除操作
	 */
	@Override
	public ReturnData deletePositionsinfo(String ids) {
		StringBuffer fail = new StringBuffer();

		List<String> list = Lists.newArrayList(StringUtils.split(ids, ","));
		for (String id : list) {

			// 检查岗位是否在使用中
			if (applyInductionService.findInductionByPosId(id)) {
				fail.append("[" + id + "]正在使用，无法删除;");
				continue;
			}
			Positionsinfo admin = new Positionsinfo();
			admin.setId(id);
			int returnDate = positionsinfoMapper.delete(admin);
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

	@Override
	public List<Map<String, Object>> cboPositionsinfoList() {
		List<Map<String, Object>> resultMap = Lists.newArrayList();

		List<Positionsinfo> listEmployee = positionsinfoMapper.selectAll();
		for (Positionsinfo item : listEmployee) {
			Map<String, Object> param = Maps.newHashMap();
			param.put("id", item.getId());
			param.put("name", item.getName());
			resultMap.add(param);
			param = null;
		}

		return resultMap;
	}

}
