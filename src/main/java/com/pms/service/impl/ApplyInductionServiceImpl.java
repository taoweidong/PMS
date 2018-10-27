package com.pms.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
import com.pms.entity.Administrator;
import com.pms.entity.Employee;
import com.pms.entity.Inductioninfo;
import com.pms.entity.Positionsinfo;
import com.pms.entity.ReturnData;
import com.pms.mapper.InductioninfoMapper;
import com.pms.service.AdminService;
import com.pms.service.ApplyInductionService;
import com.pms.service.EmployeeService;
import com.pms.service.PositionsService;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class ApplyInductionServiceImpl implements ApplyInductionService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ApplyInductionServiceImpl.class);

	@Autowired
	private InductioninfoMapper inductioninfoMapper;

	@Autowired
	private PositionsService positionsService;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private AdminService adminService;

	@Override
	public Map<String, Object> queryApplyInduction(Integer page, Integer rows, String startDate,
			String endDate, String role, Inductioninfo inductioninfo) {
		Map<String, Object> result = Maps.newHashMap();

		Example example = new Example(Inductioninfo.class);
		Example.Criteria criteria = example.createCriteria();

		// 相等查询
		if (!StringUtils.equals(role, "user")) {
			criteria.andNotEqualTo("ext1", "33");// 管理員只查詢已经提交的信息
		} else {
			if (StringUtils.isNotBlank(inductioninfo.getExt1())) {
				criteria.andEqualTo("ext1", inductioninfo.getExt1());
			}
		}

		// 相等查询
		if (StringUtils.isNotBlank(inductioninfo.getPosId())) {
			criteria.andEqualTo("posId", inductioninfo.getPosId());
		}
		// 相等查询
		if (StringUtils.isNotBlank(inductioninfo.getExt3())) {
			criteria.andEqualTo("ext3", inductioninfo.getExt3());
		}
		// 相等查询
		if (StringUtils.isNotBlank(inductioninfo.getEmpNo())) {
			criteria.andEqualTo("empNo", inductioninfo.getEmpNo());
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
				e.printStackTrace();
			}
			Calendar cal = Calendar.getInstance();
			cal.setTime(dateEnd);
			cal.add(Calendar.DATE, 1);
			criteria.andBetween("date", dateBegin, dateEnd);
		}

		PageHelper.startPage(page, rows);
		List<Inductioninfo> list = inductioninfoMapper.selectByExample(example);

		list.forEach(x -> {
			// 设置岗位信息
			Positionsinfo department = positionsService.selectPositionsInfoById(x.getPosId());
			x.setPosName(department.getName());

			// 设置领导名称
			Employee employee = employeeService.selectEmployeeById(x.getEmpNo());
			if (employee != null) {
				x.setEmpName(employee.getName());
			} else {
				Administrator admin = adminService.selectAdminById(x.getEmpNo());
				if (admin != null) {
					x.setEmpName(admin.getName());
				}
			}

			if (StringUtils.equals(x.getState(), "0")) {
				x.setStateDesc("在职");
			} else if (StringUtils.equals(x.getState(), "1")) {
				x.setStateDesc("解雇");
			} else {
				x.setStateDesc("未知");
			}

			// 00 申请中;11 已审批通过;22 审批未通过;33 未提交【员工已增加但是未提交】可删除
			if (StringUtils.equals(x.getExt1(), "00")) {
				x.setExt1Desc("申请中");
			} else if (StringUtils.equals(x.getExt1(), "11")) {
				x.setExt1Desc("已审批通过");
			} else if (StringUtils.equals(x.getExt1(), "22")) {
				x.setExt1Desc("审批未通过");
			} else if (StringUtils.equals(x.getExt1(), "33")) {
				x.setExt1Desc("未提交");
			} else {
				x.setExt1Desc("未知");
			}

			// IN 入职申请 OUT 离职申请
			if (StringUtils.equals(x.getExt3(), "IN")) {
				x.setExt3Desc("入职申请");
			} else if (StringUtils.equals(x.getExt3(), "OUT")) {
				x.setExt3Desc("离职申请");
			} else {
				x.setExt3Desc("未知");
			}

		});

		PageInfo<Inductioninfo> pageInfo = new PageInfo<Inductioninfo>(list);

		result.put("total", pageInfo.getTotal());
		result.put("rows", list);

		LOGGER.info(JSON.toJSONString(result));

		return result;
	}

	@Override
	public boolean findInductionByPosId(String id) {
		boolean resultFlag = false;
		try {
			Example example = new Example(Inductioninfo.class);
			Criteria criteria = example.createCriteria();
			criteria.andCondition("POS_ID = " + id);
			List<Inductioninfo> returnList = inductioninfoMapper.selectByExample(example);
			if (!CollectionUtils.isEmpty(returnList)) {
				resultFlag = true;
			} else {
				resultFlag = false;
			}

		} catch (Exception e) {
			LOGGER.error("findInductionByPosId 发生异常!", e);
			resultFlag = false;
		}

		return resultFlag;
	}

	@Override
	public ReturnData deleteUserApplyInduction(String ids, String role) {
		StringBuffer fail = new StringBuffer();

		List<String> list = Lists.newArrayList(StringUtils.split(ids, ","));
		for (String id : list) {
			Inductioninfo department = inductioninfoMapper.selectByPrimaryKey(id);
			if (StringUtils.equals(role, "user")
					&& !StringUtils.equals("33", department.getExt1())) {
				fail.append("[" + id + "]该状态不允许删除;");
				continue;
			}

			int returnDate = inductioninfoMapper.delete(department);
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
	 * 根据id查询信息
	 */
	@Override
	public Inductioninfo findInductionById(String id) {
		return inductioninfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public ReturnData addUserApplyInduction(Inductioninfo inductioninfo) {
		// 新增前检查是否已经申请该类型的记录：职位+类型
		Example example = new Example(Inductioninfo.class);
		Example.Criteria criteria = example.createCriteria();
		// 相等查询
		criteria.andEqualTo("empNo", inductioninfo.getEmpNo());
		criteria.andEqualTo("posId", inductioninfo.getPosId());
		criteria.andEqualTo("ext3", inductioninfo.getExt3());
		List<Inductioninfo> list = inductioninfoMapper.selectByExample(example);

		if (list != null && list.size() > 0) {
			return ReturnData.fail("该申请类型已经存在!");
		}

		int result = inductioninfoMapper.insert(inductioninfo);
		if (result > 0) {
			return ReturnData.success();
		} else {
			return ReturnData.fail("新增失败!");
		}
	}

	@Override
	public ReturnData updateUserApplyApprove(Inductioninfo inductioninfo) {
		try {
			int result = inductioninfoMapper.updateByPrimaryKey(inductioninfo);
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
	public List<Map<String, Object>> getUserNameComboList() {
		return inductioninfoMapper.getUserNameComboList();
	}

}
