package com.pms.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.pms.dao.NoticeDao;
import com.pms.model.Administrator;
import com.pms.model.NoticeBean;
import com.pms.model.PageBean;
import com.pms.util.BaseServlet;
import com.pms.util.DbUtils;
import com.pms.util.JsonUtil;
import com.pms.util.Log4jHelper;
import com.pms.util.ResponseUtil;
import com.pms.util.StringUtil;

/**
 * 公告信息处理类
 * @author Taowd
 * @version 2018年8月31日
 * @see NoticeServlet
 */
public class NoticeServlet extends BaseServlet {
	/**
	 * 序列化ID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Author:Taowd 功能：新增公告信息 开发日期：2017-5-9-上午8:25:01
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void AddNotice(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Log4jHelper.info("进入公告信息新增的操作");

		request.setCharacterEncoding("utf-8");
		String NOT_ID = request.getParameter("NOT_ID");
		// String NOT_AUTHOR = request.getParameter("NOT_AUTHOR");
		String NOT_TITLE = request.getParameter("NOT_TITLE");
		String NOT_CONTENT = request.getParameter("NOT_CONTENT");

		HttpSession session = request.getSession();
		Administrator adminInfo = (Administrator) session.getAttribute("currentUser");

		NoticeBean db = new NoticeBean(NOT_ID, NOT_TITLE, NOT_CONTENT, null,
				adminInfo.getAdmin_id(), null, null, null);
		Log4jHelper.info("保存信息：" + db.toString());

		Connection con = null;
		try {
			con = DbUtils.getConnection();
			int saveNums = 0;
			// 检查stuNo是否已经存在
			boolean isExistenceFlag = NoticeDao.IsExistence(con, NOT_ID);
			Map<String, Object> result = Maps.newHashMap();

			// 检查新增员工号是否已经注册
			if (isExistenceFlag) {
				result.put("success", false);
				result.put("errorMsg", "该公告ID已存在！");
			} else {
				db.setNot_Id(StringUtil.GetUUID());
				// 新增员工，返回成功的行数
				saveNums = NoticeDao.NoticeAdd(con, db);
				if (saveNums > 0) {
					result.put("success", true);
				} else {
					result.put("success", false);
					result.put("errorMsg", "新增公告信息失败");
				}
			}
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
	 * Author:Taowd 功能：删除公告信息 开发日期：2017-5-9-上午8:24:51
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void DeleteNotice(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Log4jHelper.info("删除公告信息");
		String delIds = request.getParameter("delIds");// 取得删除的id字符串集合

		Connection con = null;
		try {
			con = DbUtils.getConnection();
			JSONObject result = new JSONObject();
			int delNums = NoticeDao.NoticeDelete(con, StringUtil.FormatDeleteDelIds(delIds));// 返回批量删除的数量
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
	 * Author:Taowd 功能：修改公告信息 开发日期：2017-5-9-上午8:24:41
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void UpdateNotice(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Log4jHelper.info("进入公告信息修改的操作");

		request.setCharacterEncoding("utf-8");
		String NOT_ID = request.getParameter("NOT_ID");
		String NOT_TITLE = request.getParameter("NOT_TITLE");
		String NOT_CONTENT = request.getParameter("NOT_CONTENT");

		HttpSession session = request.getSession();
		Administrator adminInfo = (Administrator) session.getAttribute("currentUser");

		NoticeBean db = new NoticeBean(NOT_ID, NOT_TITLE, NOT_CONTENT, null,
				adminInfo.getAdmin_id(), null, null, null);
		Log4jHelper.info("修改信息：" + db.toString());

		Connection con = null;
		try {
			con = DbUtils.getConnection();
			int saveNums = 0;
			JSONObject result = new JSONObject();
			// 如果是修改的话，返回修改成功的行数
			saveNums = NoticeDao.NoticeModify(con, db);
			if (saveNums > 0) {
				result.put("success", true);
			} else {
				result.put("success", false);
				result.put("errorMsg", "修改公告信息失败！");
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
	 * Author:Taowd 功能：公告信息列表 开发日期：2017-5-9-上午8:24:20
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void NoticeListInfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String page = request.getParameter("page");// 取得请求的参数
		String rows = request.getParameter("rows");
		String not_id = request.getParameter("NOT_ID");
		String not_title = request.getParameter("NOT_TITLE");
		String not_author = request.getParameter("ADMIN_NAME");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String Ext1 = request.getParameter("Ext1");
		String Ext2 = request.getParameter("Ext2");
		String Ext3 = request.getParameter("Ext3");

		NoticeBean notice = new NoticeBean();
		notice.setNot_Id(not_id);
		notice.setNot_Title(not_title);
		notice.setNot_Author(not_author);
		notice.setNot_ext1(Ext1);
		notice.setNot_ext2(Ext2);
		notice.setNot_ext3(Ext3);

		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		Connection con = null;
		try {
			con = DbUtils.getConnection();
			JSONObject result = new JSONObject();
			Object jsonArray = JsonUtil.formatRsToJsonArray(
					NoticeDao.NoticeList(con, pageBean, notice, startDate, endDate));// 取得json数据
			int total = NoticeDao.NoticeCount(con, notice, startDate, endDate);// 总记录数
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

}
