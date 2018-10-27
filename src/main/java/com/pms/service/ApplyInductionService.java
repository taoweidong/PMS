package com.pms.service;

import java.util.List;
import java.util.Map;

import com.pms.entity.Inductioninfo;
import com.pms.entity.ReturnData;

/**
 * 入职信息
 * @author Taowd
 * @version 2018年10月25日
 * @see ApplyInductionService
 */
public interface ApplyInductionService {

	Map<String, Object> queryApplyInduction(Integer page, Integer rows, String startDate, String endDate, String role,Inductioninfo inductioninfo);

	/**
	 * 检查岗位是否在使用.
	 * @param id
	 * @return
	 */
	boolean findInductionByPosId(String id);

	/**
	 * 刪除申请记录.
	 * @param ids
	 * @return
	 */
	ReturnData deleteUserApplyInduction(String ids, String role);

	/**
	 * 根据ID查询记录.
	 * @param id
	 * @return
	 */
	Inductioninfo findInductionById(String id);

	/**
	 * 新增申请记录.
	 * @param inductioninfo
	 * @return
	 */
	ReturnData addUserApplyInduction(Inductioninfo inductioninfo);

	/**
	 * 更新操作.
	 * @param inductioninfo
	 * @return
	 */
	ReturnData updateUserApplyApprove(Inductioninfo inductioninfo);

	List<Map<String, Object>> getUserNameComboList();

}
