package com.pms.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.pms.dao.PoliticalStatusDao;
import com.pms.model.PageBean;
import com.pms.model.PoliticalStatusBean;
import com.pms.util.BaseServlet;
import com.pms.util.DbUtils;
import com.pms.util.JsonUtil;
import com.pms.util.Log4jHelper;
import com.pms.util.ResponseUtil;
import com.pms.util.StringUtil;

public class PoliticalStatusServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * Author:Taowd
	 * 功能：新增政治面貌信息
	 * 开发日期：2017-5-9-下午1:15:27
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void AddPoliticalStatus(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Log4jHelper.info("新增政治面貌信息");
		request.setCharacterEncoding("utf-8");
		String ps_Type = request.getParameter("PS_TYPE");
		String ps_Name = request.getParameter("PS_Name");
		String ext1 = request.getParameter("ext1");
		String ext2 = request.getParameter("Ext2");
		String ext3 = request.getParameter("ext3");

		PoliticalStatusBean psb = new PoliticalStatusBean(ps_Type, ps_Name,
				ext1, ext2, ext3);
		Log4jHelper.info("保存信息：" + psb.toString());

		Connection con = null;
		try {
			con = DbUtils.getConnection();
			int saveNums = 0;
			JSONObject result = new JSONObject();
			// 检查新增员工号是否已经注册
			if (PoliticalStatusDao.IsExistence(con, ps_Type)) {
				result.put("success", false);
				result.put("errorMsg", "该类型已存在！");
			} else {
				// 新增员工，返回成功的行数
				saveNums = PoliticalStatusDao.PoliticalStatusAdd(con, psb);
				if (saveNums > 0) {
					result.put("success", true);
				} else {
					result.put("success", false);
					result.put("errorMsg", "新增政治面貌信息失败");
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
	 * 功能：删除政治面貌信息
	 * 开发日期：2017-5-9-下午1:17:09
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void DeletePoliticalStatus(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Log4jHelper.info("删除政治面貌信息");

		request.setCharacterEncoding("utf-8");
		String delIds = request.getParameter("delIds");// 取得删除的id字符串集合
		Connection con = null;
		try {
			con = DbUtils.getConnection();
			String[] str = delIds.split(",");
			JSONObject result = new JSONObject();
			for (int i = 0; i < str.length; i++) {
				boolean f = PoliticalStatusDao.getEmpByPsType(con, str[i]);
				if (f) {
					result.put("success", false);
					result.put("errorIndex", i);
					result.put("errorMsg", "该角色下有职工,不能删除");
					ResponseUtil.write(response, result);
					return;
				}
			}
			int delNums = PoliticalStatusDao.PoliticalStatusDelete(con,
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
	 * 功能：修改政治面貌信息
	 * 开发日期：2017-5-9-下午1:17:38
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void UpdatePoliticalStatus(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Log4jHelper.info("修改政治面貌信息");

		request.setCharacterEncoding("utf-8");
		String ps_Type = request.getParameter("PS_TYPE");
		String ps_Name = request.getParameter("PS_Name");
		String ext1 = request.getParameter("ext1");
		String ext2 = request.getParameter("Ext2");
		String ext3 = request.getParameter("ext3");

		PoliticalStatusBean psb = new PoliticalStatusBean(ps_Type, ps_Name,
				ext1, ext2, ext3);
		Log4jHelper.info("修改信息：" + psb.toString());

		Connection con = null;
		try {
			con = DbUtils.getConnection();
			int saveNums = 0;
			JSONObject result = new JSONObject();
			// 如果是修改的话，返回修改成功的行数
			saveNums = PoliticalStatusDao.PoliticalStatusModify(con, psb);
			if (saveNums > 0) {
				result.put("success", true);
			} else {
				result.put("success", false);
				result.put("errorMsg", "修改政治面貌信息失败！");
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
	 * 功能：查询政治面貌信息列表
	 * 开发日期：2017-5-9-下午1:18:49
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void PoliticalStatusListInfo(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Log4jHelper.info("查询政治面貌信息列表");
		String page = request.getParameter("page");// 取得请求的参数
		String rows = request.getParameter("rows");
		String PS_Name = request.getParameter("PS_Name");
		String PS_TYPE = request.getParameter("PS_TYPE");
		String Ext1 = request.getParameter("Ext1");
		String Ext2 = request.getParameter("Ext2");
		String Ext3 = request.getParameter("Ext3");
		if (PS_Name == null) {
			PS_Name = "";
		}
		PoliticalStatusBean grade = new PoliticalStatusBean();
		grade.setPs_name(PS_Name);
		grade.setPs_type(PS_TYPE);
		grade.setExt1(Ext1);
		grade.setExt2(Ext2);
		grade.setExt3(Ext3);
		PageBean pageBean = new PageBean(Integer.parseInt(page),
				Integer.parseInt(rows));
		Connection con = null;
		try {
			con = DbUtils.getConnection();
			JSONObject result = new JSONObject();
			JSONArray jsonArray = JsonUtil
					.formatRsToJsonArray(PoliticalStatusDao
							.politicalStatusList(con, pageBean, grade));// 取得json数据
			int total = PoliticalStatusDao.PoliticalStatusCount(con, grade);// 总记录数
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
	 * 功能：查询政治面貌下拉框信息
	 * 开发日期：2017-5-9-下午1:19:22
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void PoliticalStatusComboboxInfo(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Log4jHelper.info("查询政治面貌下拉框信息");

		Connection con = null;
		try {
			con = DbUtils.getConnection();
			JSONArray jsonArray = new JSONArray();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("PS_TYPE", "");
			jsonObject.put("PS_Name", "请选择...");
			jsonArray.add(jsonObject);
			// 加入整个集合
			jsonArray.addAll(JsonUtil.formatRsToJsonArray(PoliticalStatusDao
					.politicalStatusList(con, null, null)));// 取得json数据
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
