package com.pms.service;

import java.util.Map;

public interface PositionsService {

	Map<String, Object> queryPositions(Integer page, Integer rows, String name, String depId,
			String startDate, String endDate);

}
