package com.pms.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.pms.dao.AdministratorDao;
import com.pms.model.Administrator;
import com.pms.model.PageBean;
import com.pms.util.AESUtil;
import com.pms.util.BaseServlet;
import com.pms.util.DbUtils;
import com.pms.util.JsonUtil;
import com.pms.util.Log4jHelper;
import com.pms.util.ResponseUtil;

/**
 * 管理员处理器，处理管理员增加，修改，删除，获取信息列表，设置超级管理员，取消超级管理员等功能
 * @author Taowd
 * @version 2018年8月31日
 * @see AdminServlet
 */
public class AdminServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;

	AdministratorDao adminDao = new AdministratorDao();

	// 如果是修改的话，返回修改成功的行数
	int saveNums = 0;

	/**
	 * 更新管理员信息.
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void Update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Log4jHelper.info("更新管理员信息");
		request.setCharacterEncoding("utf-8");
		String ADMIN_NO = request.getParameter("ADMIN_NO");
		String ADMIN_ID = request.getParameter("ADMIN_ID");
		String ADMIN_PWD = request.getParameter("newPassword");
		String ADMIN_NAME = request.getParameter("ADMIN_NAME");
		String ADMIN_PHONE = request.getParameter("ADMIN_PHONE");
		String Ext1 = request.getParameter("Ext1");

		Administrator adminBean = new Administrator();
		adminBean.setAdmin_id(ADMIN_ID);
		adminBean.setAdmin_no(ADMIN_NO);
		if (!StringUtils.isEmpty(ADMIN_PWD)) {
			adminBean.setAdmin_pwd(AESUtil.parseByte2HexStr(AESUtil.encrypt(ADMIN_PWD)));
		}
		adminBean.setAdmin_name(ADMIN_NAME);
		adminBean.setAdmin_phone(ADMIN_PHONE);
		adminBean.setExt1(Ext1);

		try {
			saveNums = adminDao.adminModify(adminBean);

			Map<String, Object> result = Maps.newHashMap();

			if (saveNums > 0) {
				result.put("success", true);
			} else {
				result.put("success", false);
				result.put("errorMsg", "修改员工信息失败！");
			}
			ResponseUtil.write(response, JSON.toJSONString(result));// 发送到客户端
		} catch (Exception e) {
			Log4jHelper.exception(e);
		}

	}

	public void AdminPersionUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		Log4jHelper.info("更新管理员个人信息");
		request.setCharacterEncoding("utf-8");
		String ADMIN_NO = request.getParameter("ADMIN_NO");
		String ADMIN_ID = request.getParameter("ADMIN_ID");
		String ADMIN_PWD = request.getParameter("newPassword");
		String ADMIN_NAME = request.getParameter("ADMIN_NAME");
		String ADMIN_PHONE = request.getParameter("ADMIN_PHONE");
		String Ext1 = request.getParameter("Ext1");

		Administrator adminBean = new Administrator();
		adminBean.setAdmin_id(ADMIN_ID);
		adminBean.setAdmin_no(ADMIN_NO);
		if (!StringUtils.isEmpty(ADMIN_PWD)) {
			adminBean.setAdmin_pwd(AESUtil.parseByte2HexStr(AESUtil.encrypt(ADMIN_PWD)));
		}
		adminBean.setAdmin_name(ADMIN_NAME);
		adminBean.setAdmin_phone(ADMIN_PHONE);
		adminBean.setExt1(Ext1);

		// 如果是修改的话，返回修改成功的行数
		try {
			saveNums = adminDao.adminPersionModify(adminBean);
			JSONObject result = new JSONObject();
			if (saveNums > 0) {
				result.put("success", true);
			} else {
				result.put("success", false);
				result.put("errorMsg", "修改员工信息失败！");
			}
			ResponseUtil.write(response, result);// 发送到客户端
		} catch (Exception e) {
			Log4jHelper.exception(e);
		}

	}

	/**
	 * Author:Taowd 功能：管理员信息保存 开发日期：2017-5-5-下午8:42:45
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void AdminAdd(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("utf-8");
		Log4jHelper.info("新增管理员个人信息");
		request.setCharacterEncoding("utf-8");
		String ADMIN_NO = request.getParameter("ADMIN_NO");
		String ADMIN_ID = request.getParameter("ADMIN_ID");
		String ADMIN_PWD = request.getParameter("newPassword");
		String ADMIN_NAME = request.getParameter("ADMIN_NAME");
		String ADMIN_PHONE = request.getParameter("ADMIN_PHONE");
		String Ext1 = request.getParameter("Ext1");

		Administrator adminBean = new Administrator();
		adminBean.setAdmin_id(ADMIN_ID);
		adminBean.setAdmin_no(ADMIN_NO);
		if (!StringUtils.isEmpty(ADMIN_PWD)) {
			adminBean.setAdmin_pwd(AESUtil.parseByte2HexStr(AESUtil.encrypt(ADMIN_PWD)));
		}
		adminBean.setAdmin_name(ADMIN_NAME);
		adminBean.setAdmin_phone(ADMIN_PHONE);
		adminBean.setExt1(Ext1);

		// 检查stuNo是否已经存在
		boolean isExistenceFlag;
		JSONObject result = new JSONObject();
		try {
			isExistenceFlag = adminDao.isExistence(ADMIN_NO);
			// 检查新增员工号是否已经注册
			if (isExistenceFlag) {
				result.put("success", false);
				result.put("errorMsg", "员工号已存在！");
			} else {
				// 新增员工，返回成功的行数
				saveNums = adminDao.adminAdd(adminBean);
				if (saveNums > 0) {
					result.put("success", true);
				} else {
					result.put("success", false);
					result.put("errorMsg", "新增员工信息失败");
				}
			}
			ResponseUtil.write(response, result);// 发送到客户端
		} catch (SQLException e) {
			Log4jHelper.exception(e);
		}

	}

	/**
	 * Author:Taowd 功能：删除管理员信息 开发日期：2017-5-6-上午11:38:45
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void AdminDelete(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("utf-8");
		Log4jHelper.info("进入删除管理员的控制器");
		String delIds = request.getParameter("delIds");// 取得删除的id字符串集合
		String[] str = delIds.split(",");
		JSONObject result = new JSONObject();
		for (int i = 0; i < str.length; i++) {
			int delNums = adminDao.adminInfoDelete(str[i]);// 返回批量删除的数量
			if (delNums > 0) {
				result.put("success", true);
				result.put("delNums", delNums);
			} else {
				result.put("success", false);
				result.put("errorMsg", "删除失败");
			}
		}

		ResponseUtil.write(response, result);// 发送到客户端

	}

	/**
	 * Author:Taowd 功能：获取管理员信息列表 开发日期：2017-5-6-上午11:51:20
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void AdminInfoList(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("utf-8");
		Log4jHelper.info("获取管理员信息列表");
		Administrator adminInfo = new Administrator();
		String admin_no = request.getParameter("ADMIN_NO");
		String admin_name = request.getParameter("ADMIN_NAME");
		String admin_phone = request.getParameter("ADMIN_PHONE");
		adminInfo.setAdmin_no(admin_no);
		adminInfo.setAdmin_name(admin_name);
		adminInfo.setAdmin_phone(admin_phone);

		String page = request.getParameter("page");// 取得请求的参数
		String rows = request.getParameter("rows");
		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		Connection con = null;
		try {
			con = DbUtils.getConnection();
			JSONObject result = new JSONObject();
			Object jsonArray = JsonUtil
					.formatRsToJsonArray(adminDao.getAdminInfo(con, pageBean, adminInfo));// 取得json数据
			int total = adminDao.getAdminCount(adminInfo);// 总记录数
			result.put("rows", jsonArray);// 封装数据
			result.put("total", total);
			ResponseUtil.write(response, JSON.toJSONString(result));// 发送到客户端
		} catch (Exception e) {
			Log4jHelper.exception(e);
		} finally {
			try {
				DbUtils.CloseConn(con);// 关闭连接
			} catch (Exception e) {
				Log4jHelper.exception(e);
			}
		}

	}

	/**
	 * Author:Taowd 功能：取消超级管理员 开发日期：2017-5-6-下午12:08:40
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void SetCancelSuperAdmin(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("utf-8");

		Log4jHelper.info("取消超级管理员的设置");
		String delIds = request.getParameter("delIds");// 取得设置超级管理员的的id字符串集合
		try {

			String[] str = delIds.split(",");
			JSONObject result = new JSONObject();
			int successSum = str.length;
			for (int i = 0; i < str.length; i++) {

				boolean f = adminDao.isSuperAdmin(str[i]);
				if (!f) {
					result.put("success", false);
					result.put("errorIndex", i);
					result.put("errorMsg", "不是超级管理员，不能取消");
					ResponseUtil.write(response, result);
					return;
				}

				int delNums = adminDao.setCancelSuperAdmin(str[i]);// 返回批量修改的数量
				if (delNums > 0) {
					result.put("success", true);
					result.put("delNums", successSum);
				} else {
					successSum--;
					result.put("success", false);
					result.put("errorMsg", "设置失败");
				}
			}

			ResponseUtil.write(response, result);// 发送到客户端
		} catch (Exception e) {
			Log4jHelper.exception(e);
		}
	}

	/**
	 * Author:Taowd 功能：设置超级管理员 开发日期：2017-5-6-下午12:09:13
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void SetSuperAdmin(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("utf-8");
		Log4jHelper.info("设置超级管理员");
		String delIds = request.getParameter("delIds");// 取得设置超级管理员的的id字符串集合
		try {
			String[] str = delIds.split(",");
			JSONObject result = new JSONObject();
			int successSum = str.length;
			for (int i = 0; i < str.length; i++) {

				boolean f = adminDao.isSuperAdmin(str[i]);
				if (f) {
					result.put("success", false);
					result.put("errorIndex", i);
					result.put("errorMsg", "已经是超级管理员，不能重复设置");
					ResponseUtil.write(response, result);
					return;
				}

				int delNums = adminDao.setSuperAdmin(str[i]);// 返回批量删除的数量
				if (delNums > 0) {
					result.put("success", true);
					result.put("delNums", successSum);
				} else {
					successSum--;
					result.put("success", false);
					result.put("errorMsg", "设置失败");
				}
			}

			ResponseUtil.write(response, result);// 发送到客户端
		} catch (Exception e) {
			Log4jHelper.exception(e);
		}
	}

}
