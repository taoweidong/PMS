package com.pms.entity;

/**
 * 返回结果集.
 * @author Taowd
 * @version 2018年9月9日
 * @see ReturnData
 */
public class ReturnData {
	/**
	 * 成功标志.
	 */
	private boolean success;
	/**
	 * 错误信息.
	 */
	private String errorMsg;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public static ReturnData success() {
		ReturnData returnData = new ReturnData();
		returnData.setSuccess(true);
		returnData.setErrorMsg("操作成功！");
		return returnData;
	}

	/**
	 * 返回错误.
	 * @param errorMsg
	 * @return
	 */
	public static ReturnData fail(String errorMsg) {
		ReturnData returnData = new ReturnData();
		returnData.setSuccess(false);
		returnData.setErrorMsg(errorMsg);
		return returnData;
	}

}
