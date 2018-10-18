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

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.pms.entity.Administrator;
import com.pms.entity.Employee;
import com.pms.entity.Notice;
import com.pms.entity.ReturnData;
import com.pms.mapper.NoticeMapper;
import com.pms.service.AdminService;
import com.pms.service.EmployeeService;
import com.pms.service.NoticService;

import tk.mybatis.mapper.entity.Example;

@Service
public class NoticServiceImpl implements NoticService {

	private static final Logger LOGGER = LoggerFactory.getLogger(NoticServiceImpl.class);

	@Autowired
	private NoticeMapper noticeMapper;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private AdminService adminService;

	@Override
	public Map<String, Object> queryNoticInfo(Integer page, Integer rows, String title, String name,
			String startDate, String endDate) {
		Map<String, Object> result = Maps.newHashMap();

		Example example = new Example(Notice.class);
		Example.Criteria criteria = example.createCriteria();
		// 模糊查询
		if (!StringUtils.isEmpty(title)) {
			criteria.andLike("title", "%" + title + "%");
		}

		// 相等查询
		if (StringUtils.isNotBlank(name)) {
			criteria.andEqualTo("author", name);
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
		List<Notice> list = noticeMapper.selectByExample(example);

		list.forEach(x -> {
			// 设置领导名称
			Employee employee = employeeService.selectEmployeeById(x.getAuthor());
			if (employee != null) {
				x.setAuthorName(employee.getName());
			} else {
				Administrator admin = adminService.selectAdminById(x.getAuthor());
				if (admin != null) {
					x.setAuthorName(admin.getName());
				}
			}

		});

		PageInfo<Notice> pageInfo = new PageInfo<Notice>(list);

		result.put("total", pageInfo.getTotal());
		result.put("rows", list);

		LOGGER.info(JSON.toJSONString(result));

		return result;
	}

	@Override
	public Notice selectNoticeById(String id) {
		return noticeMapper.selectByPrimaryKey(id);
	}

	@Override
	public ReturnData updateNotice(Notice notice) {
		try {
			int result = noticeMapper.updateByPrimaryKey(notice);
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
	public ReturnData addNotice(Notice notice) {
		try {
			int result = noticeMapper.insert(notice);
			if (result > 0) {
				return ReturnData.success();
			}
		} catch (Exception e) {
			LOGGER.error("新增异常!", e);
			return ReturnData.fail("新增异常!");
		}

		return ReturnData.success();
	}

	@Override
	public ReturnData deleteNotice(String ids) {
		StringBuffer fail = new StringBuffer();

		List<String> list = Lists.newArrayList(StringUtils.split(ids, ","));
		for (String id : list) {
			Notice admin = new Notice();
			admin.setId(id);
			int returnDate = noticeMapper.delete(admin);
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
	public List<Map<String, Object>> comboUser() {
		List<Map<String, Object>> returnData = Lists.newArrayList();

		Map<String, Object> param = Maps.newHashMap();
		param.put("EMP_NO", "");
		param.put("EMP_NAME", "请选择...");
		returnData.add(param);

		List<String> listUser = noticeMapper.queryAuthor();

		if (listUser == null || listUser.size() == 0) {
			return returnData;
		}

		Map<String, Object> result = employeeService.dicEmployee();
		Map<String, Object> result2 = adminService.dicAdmin();

		for (String item : listUser) {
			Map<String, Object> paramItem = Maps.newHashMap();

			if (StringUtils.isNotBlank(item) && result.containsKey(item)) {
				paramItem.put("EMP_NO", item);
				paramItem.put("EMP_NAME", result.get(item));
				returnData.add(paramItem);
				paramItem = null;
				continue;
			}

			if (StringUtils.isNotBlank(item) && result2.containsKey(item)) {
				paramItem.put("EMP_NO", item);
				paramItem.put("EMP_NAME", result2.get(item));
				returnData.add(paramItem);
				paramItem = null;
				continue;
			}

		}

		return returnData;
	}

}
