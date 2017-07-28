package com.pms.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.pms.dao.PositionsInfoDao;
import com.pms.model.PageBean;
import com.pms.model.PositionsInfoBean;
import com.pms.util.BaseServlet;
import com.pms.util.DbUtils;
import com.pms.util.JsonUtil;
import com.pms.util.Log4jHelper;
import com.pms.util.ResponseUtil;
import com.pms.util.StringUtil;

public class PositionsServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * Author:Taowd
	 * 功能：新增岗位信息信息
	 * 开发日期：2017-5-9-下午1:15:27
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void AddPositions(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Log4jHelper.info("新增岗位信息信息");

		request.setCharacterEncoding("utf-8");
		String pos_Id = request.getParameter("POS_ID");
		String dep_Id = request.getParameter("DEP_ID");
		String pos_Name = request.getParameter("POS_NAME");
		String pos_Content = request.getParameter("POS_CONTENT");
		String pos_Salary = request.getParameter("POS_SALARY");
		String pos_Allowance = request.getParameter("POS_ALLOWANCE");
		String pos_Perquisites = request.getParameter("POS_PERQUISITES");
		String ext1 = request.getParameter("EXT1");
		// String ext2 = request.getParameter("Ext2");
		// String ext3 = request.getParameter("Ext3");

		PositionsInfoBean pib = new PositionsInfoBean(pos_Id, dep_Id, pos_Name,
				pos_Content, Double.parseDouble(pos_Salary),
				Double.parseDouble(pos_Allowance),
				Double.parseDouble(pos_Perquisites), ext1, null, null);

		Connection con = null;
		try {
			con = DbUtils.getConnection();
			int saveNums = 0;
			JSONObject result = new JSONObject();
			// 检查新增岗位是否已经存在
			if (PositionsInfoDao.IsExistence(con, pos_Id)) {
				result.put("success", false);
				result.put("errorMsg", "岗位号已存在！");
			} else {
				// 新增岗位，返回成功的行数
				saveNums = PositionsInfoDao.PositionsInfoAdd(con, pib);
				if (saveNums > 0) {
					result.put("success", true);
				} else {
					result.put("success", false);
					result.put("errorMsg", "新增岗位信息失败");
				}
			}
			ResponseUtil.write(response, result);// 发送到客户端
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
	 * 
	 * Author:Taowd
	 * 功能：删除岗位信息信息
	 * 开发日期：2017-5-9-下午1:17:09
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void DeletePositions(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Log4jHelper.info("删除岗位信息信息");

		String delIds = request.getParameter("delIds");// 取得删除的id字符串集合

		Connection con = null;
		try {
			con = DbUtils.getConnection();
			String[] str = delIds.split(",");
			JSONObject result = new JSONObject();
			for (int i = 0; i < str.length; i++) {
				boolean f = PositionsInfoDao.getEmpByPosId(con, str[i]);
				if (f) {
					result.put("success", false);
					result.put("errorIndex", i);
					result.put("errorMsg", "岗位下有职工,不能删除");
					ResponseUtil.write(response, result);
					return;
				}
			}
			int delNums = PositionsInfoDao.PositionsInfoDelete(con,
					StringUtil.FormatDeleteDelIds(delIds));// 返回批量删除的数量
			if (delNums > 0) {
				result.put("success", true);
				result.put("delNums", delNums);
			} else {
				result.put("success", false);
				result.put("errorMsg", "删除失败");
			}
			ResponseUtil.write(response, result);// 发送到客户端
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
	 * 
	 * Author:Taowd
	 * 功能：修改岗位信息信息
	 * 开发日期：2017-5-9-下午1:17:38
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void UpdatePositions(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Log4jHelper.info("修改岗位信息信息");

		request.setCharacterEncoding("utf-8");
		String pos_Id = request.getParameter("POS_ID");
		String dep_Id = request.getParameter("DEP_ID");
		String pos_Name = request.getParameter("POS_NAME");
		String pos_Content = request.getParameter("POS_CONTENT");
		String pos_Salary = request.getParameter("POS_SALARY");
		String pos_Allowance = request.getParameter("POS_ALLOWANCE");
		String pos_Perquisites = request.getParameter("POS_PERQUISITES");
		String ext1 = request.getParameter("EXT1");
		// String ext2 = request.getParameter("Ext2");
		// String ext3 = request.getParameter("Ext3");

		PositionsInfoBean pib = new PositionsInfoBean(pos_Id, dep_Id, pos_Name,
				pos_Content, Double.parseDouble(pos_Salary),
				Double.parseDouble(pos_Allowance),
				Double.parseDouble(pos_Perquisites), ext1, null, null);

		Connection con = null;
		try {
			con = DbUtils.getConnection();
			int saveNums = 0;
			JSONObject result = new JSONObject();
			// 如果是修改的话，返回修改成功的行数
			saveNums = PositionsInfoDao.PositionsInfoModify(con, pib);
			if (saveNums > 0) {
				result.put("success", true);
			} else {
				result.put("success", false);
				result.put("errorMsg", "修改岗位信息失败！");
			}
			ResponseUtil.write(response, result);// 发送到客户端
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
	 * 
	 * Author:Taowd
	 * 功能：查询岗位信息信息列表
	 * 开发日期：2017-5-9-下午1:18:49
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void PositionsListInfo(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Log4jHelper.info("查询岗位信息信息列表");

		request.setCharacterEncoding("utf-8");
		String page = request.getParameter("page");// 取得请求的参数
		String rows = request.getParameter("rows");
		String pos_Id = request.getParameter("POS_ID");
		String pos_Name = request.getParameter("POS_NAME");
		String DEP_ID = request.getParameter("DEP_ID");

		PositionsInfoBean grade = new PositionsInfoBean();
		grade.setPos_Name(pos_Id);
		grade.setPos_Name(pos_Name);
		grade.setDep_Id(DEP_ID);

		PageBean pageBean = new PageBean(Integer.parseInt(page),
				Integer.parseInt(rows));
		Connection con = null;
		try {
			con = DbUtils.getConnection();
			JSONObject result = new JSONObject();
			JSONArray jsonArray = JsonUtil.formatRsToJsonArray(PositionsInfoDao
					.PositionsInfoList(con, pageBean, grade));// 取得json数据
			int total = PositionsInfoDao.PositionsInfoCount(con, grade);// 总记录数
			result.put("rows", jsonArray);// 封装数据
			result.put("total", total);
			ResponseUtil.write(response, result);// 发送到客户端
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
	 * 
	 * Author:Taowd
	 * 功能：查询岗位信息下拉框信息
	 * 开发日期：2017-5-9-下午1:19:22
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void PositionsComboboxInfo(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Log4jHelper.info("查询岗位信息下拉框信息");
		Connection con = null;
		try {
			con = DbUtils.getConnection();
			JSONArray jsonArray = new JSONArray();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("POS_ID", "");
			jsonObject.put("POS_NAME", "请选择...");
			jsonArray.add(jsonObject);
			// 加入整个集合
			jsonArray.addAll(JsonUtil.formatRsToJsonArray(PositionsInfoDao
					.PositionsInfoList(con, null, new PositionsInfoBean())));// 取得json数据
			ResponseUtil.write(response, jsonArray);// 发送到客户端
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

}
