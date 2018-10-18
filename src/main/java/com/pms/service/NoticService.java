package com.pms.service;

import java.util.List;
import java.util.Map;

import com.pms.entity.Notice;
import com.pms.entity.ReturnData;

public interface NoticService {

	Map<String, Object> queryNoticInfo(Integer page, Integer rows,String  title,String name,String startDate,String endDate);

	Notice selectNoticeById(String trimToEmpty);

	ReturnData updateNotice(Notice notice);

	ReturnData addNotice(Notice notice);

	ReturnData deleteNotice(String ids);

	List<Map<String, Object>> comboUser();

}
