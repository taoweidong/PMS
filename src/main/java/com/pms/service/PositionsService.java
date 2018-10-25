package com.pms.service;

import java.util.List;
import java.util.Map;

import com.pms.entity.Positionsinfo;
import com.pms.entity.ReturnData;

/**
 * 岗位信息服务
 * @author Taowd
 * @version 2018年10月18日
 * @see PositionsService
 */
public interface PositionsService {

	/**
	 * 根据条件查询岗位详情.
	 * @param page
	 * @param rows
	 * @param name
	 * @param depId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	Map<String, Object> queryPositions(Integer page, Integer rows, String name, String depId,
			String startDate, String endDate);

	/**
	 * 根据id查询岗位详情.
	 * @param id
	 * @return
	 */
	Positionsinfo selectPositionsInfoById(String id);

	/**
	 * 更新操作.
	 * @param positionsInfo
	 * @return
	 */
	ReturnData updatePositionsInfo(Positionsinfo positionsInfo);

	/**
	 * 新增操作.
	 * @param positionsInfo
	 * @return
	 */
	ReturnData addPositionsinfo(Positionsinfo positionsInfo);

	/**
	 * 删除操作.
	 * @param ids
	 * @return
	 */
	ReturnData deletePositionsinfo(String ids);

	List<Map<String, Object>> cboPositionsinfoList();

}
