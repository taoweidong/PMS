package com.pms.service;

import java.util.Map;

public interface PersionInfoService {
	/**
	 * 管理员个人信息查询.
	 * @return
	 */
	Map<String, Object> queryPersionInfo(final int pageNum, final int pageSize);
}
