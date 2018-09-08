package com.pms.service;

import java.util.Map;

public interface PoliticalStatusService {

	Map<String, Object> queryPoliticalStatus(Integer page, Integer rows);

}
