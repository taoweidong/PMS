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
import com.google.common.collect.Maps;
import com.pms.entity.Employee;
import com.pms.entity.Inductioninfo;
import com.pms.entity.Positionsinfo;
import com.pms.mapper.InductioninfoMapper;
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

	@Override
	public Map<String, Object> queryApplyInduction(Integer page, Integer rows, String posName,
			String approveState, String startDate, String endDate, String role, String userId) {
		Map<String, Object> result = Maps.newHashMap();

		Example example = new Example(Inductioninfo.class);
		Example.Criteria criteria = example.createCriteria();

		// 相等查询
		if (StringUtils.isNotBlank(approveState)) {
			criteria.andEqualTo("ext1", approveState);
		}
		// 相等查询
		if (StringUtils.isNotBlank(posName)) {
			criteria.andEqualTo("posId", posName);
		}
		// 相等查询
		if (StringUtils.equals(role, "user")) {
			criteria.andEqualTo("empNo", userId);
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
			x.setEmpName(employee.getName());

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

}
