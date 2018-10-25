package com.pms.service;

import java.util.Map;

/**
 * 入职信息
 * @author Taowd
 * @version 2018年10月25日
 * @see ApplyInductionService
 */
public interface ApplyInductionService {

	Map<String, Object> queryApplyInduction(Integer page, Integer rows, String posName,
			String approveState, String startDate, String endDate, String role, String userId);

	/**
	 * 检查岗位是否在使用.
	 * @param id
	 * @return
	 */
	boolean findInductionByPosId(String id);

}
