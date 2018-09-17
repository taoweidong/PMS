package com.pms.service;

import java.util.List;
import java.util.Map;

public interface PoliticalStatusService {

	Map<String, Object> queryPoliticalStatus(Integer page, Integer rows);

	List<Map<String, Object>> queryPoliticalStatusList();

}
