package com.pms.service;

import java.util.Map;

public interface PositionsService {

	Map<String, Object> queryPositions(Integer page, Integer rows);

}
