package com.pms.service;

import java.util.List;
import java.util.Map;

import com.pms.entity.Politicalstatus;
import com.pms.entity.ReturnData;

/**
 * 查询政治面貌信息.
 * @author Taowd
 * @version 2018年10月15日
 * @see PoliticalStatusService
 */
public interface PoliticalStatusService {

	Map<String, Object> queryPoliticalStatus(Integer page, Integer rows, String type, String name);

	/**
	 * 查詢政治面貌信息.
	 * @return 返回集合資源
	 */
	List<Map<String, Object>> queryPoliticalStatusList();

	/**
	 * 獲取字典方式的數據 .
	 * @return
	 */
	Map<String, Object> queryPoliticalStatusCbo();

	/**
	 * 删除政治面貌.
	 * @param ids id信息
	 * @return 结果集
	 */
	ReturnData deletePoliticalStatus(String ids);

	/**
	 * 新增实体.
	 * @param politicalstatus
	 * @return
	 */
	ReturnData addPoliticalStatus(Politicalstatus politicalstatus);

	/**
	 * 根据type查询实体信息.
	 * @param type
	 * @return
	 */
	Politicalstatus selectPoliticalStatusByType(String type);

	/**
	 * 更新信息
	 * @param politicalstatus
	 * @return
	 */
	ReturnData updatePoliticalStatus(Politicalstatus politicalstatus);

}
