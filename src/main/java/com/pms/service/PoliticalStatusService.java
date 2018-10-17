package com.pms.service;

import java.util.List;
import java.util.Map;
/**
 * 
 * 查询政治面貌信息.
 * @author Taowd
 * @version 2018年10月15日
 * @see PoliticalStatusService
 */
public interface PoliticalStatusService {

	Map<String, Object> queryPoliticalStatus(Integer page, Integer rows);

	/**
	 * 
	 * 查詢政治面貌信息.
	 * @return 返回集合資源
	 */
	List<Map<String, Object>> queryPoliticalStatusList();
	
	/**
	 * 
	 * 獲取字典方式的數據 .
	 * @return
	 */
	Map<String, Object> queryPoliticalStatusCbo();

}
