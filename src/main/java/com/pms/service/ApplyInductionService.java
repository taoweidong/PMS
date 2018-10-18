package com.pms.service;

import java.util.Map;

public interface ApplyInductionService {

	Map<String, Object> queryApplyInduction(Integer page, Integer rows);

	/**
	 * 
	 * 检查岗位是否在使用.
	 * @param id
	 * @return
	 */
	boolean findInductionByPosId(String id);

}
