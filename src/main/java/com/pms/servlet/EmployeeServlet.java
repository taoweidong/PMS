package com.pms.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.pms.dao.EmployeeDao;
import com.pms.model.Employee;
import com.pms.model.PageBean;
import com.pms.util.AESUtil;
import com.pms.util.BaseServlet;
import com.pms.util.DateUtil;
import com.pms.util.DbUtils;
import com.pms.util.JsonUtil;
import com.pms.util.Log4jHelper;
import com.pms.util.ResponseUtil;
import com.pms.util.StringUtil;

public class EmployeeServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * 新增普通员工信息.
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void AddEmployee(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Log4jHelper.info("-----------进入保存员工信息的控制器-----------");
		request.setCharacterEncoding("utf-8");
		String stuNo = request.getParameter("EMP_NO");
		String passwd = request.getParameter("passwd");
		String stuName = request.getParameter("EMP_NAME");
		String sex = request.getParameter("EMP_SEX");
		String birthday = request.getParameter("EMP_Birthday");
		String ps_id = request.getParameter("PS_TYPE");
		String emp_phone = request.getParameter("EMP_Phone");
		String emp_address = request.getParameter("EMP_Address");
		String ext1 = request.getParameter("ext1");
		String ext2 = request.getParameter("ext2");
		String ext3 = request.getParameter("ext3");

		String encyPasswd = "";
		if (!StringUtils.isEmpty(passwd)) {
			encyPasswd = AESUtil.parseByte2HexStr(AESUtil.encrypt(passwd));
		}

		Employee empBean = null;
		try {
			empBean = new Employee(stuNo, encyPasswd, stuName, sex,
					DateUtil.formatString(birthday, "yyyy-MM-dd"), ps_id, emp_phone, emp_address,
					ext1, ext2, ext3);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		Connection con = null;
		try {
			con = DbUtils.getConnection();
			int saveNums = 0;
			// 检查stuNo是否已经存在
			boolean isExistenceFlag = EmployeeDao.IsExistence(con, stuNo);
			JSONObject result = new JSONObject();

			if (isExistenceFlag) {
				result.put("success", false);
				result.put("errorMsg", "员工号已存在！");
			} else {
				// 新增员工，返回成功的行数
				saveNums = EmployeeDao.EmployeeAdd(con, empBean);
				if (saveNums > 0) {
					result.put("success", true);
				} else {
					result.put("success", false);
					result.put("errorMsg", "新增员工信息失败");
				}
			}
			ResponseUtil.write(response, result);// 发送到客户端

		} catch (Exception e) {
			Log4jHelper.exception(e);

		}

	}

	/**
	 * Author:Taowd 功能：普通员工注册信息 开发日期：2017-5-7-下午7:26:00
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void RegisterEmployee(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String empNo = request.getParameter("stuNo");
		String passwd = request.getParameter("passwd");
		String stuName = request.getParameter("stuName");
		String sex = request.getParameter("sex");
		String birthday = request.getParameter("birthday");
		String ps_id = request.getParameter("ps_id");
		String emp_phone = request.getParameter("phone");
		String emp_address = request.getParameter("address");
		String ext1 = request.getParameter("ext1");
		String ext2 = request.getParameter("ext2");
		String ext3 = request.getParameter("ext3");

		Employee Emp = null;
		try {
			// 2017年4月25日19:33:33：新增功能，密码使用MD5进行加密
			Emp = new Employee(empNo, AESUtil.parseByte2HexStr(AESUtil.encrypt(passwd)), stuName,
					sex, DateUtil.formatString(birthday, "yyyy-MM-dd"), ps_id, emp_phone,
					emp_address, ext1, ext2, ext3);
		} catch (Exception e) {
			Log4jHelper.exception(e);
		}

		Connection con = null;
		try {
			con = DbUtils.getConnection();
			boolean isExistenceFlag = false;
			JSONObject result = new JSONObject();
			isExistenceFlag = EmployeeDao.IsExistence(con, empNo);// 返回插入成功的数据
			if (!isExistenceFlag) {
				// 可以进行注册
				int saveNums = 0;
				saveNums = EmployeeDao.EmployeeRegister(con, Emp);// 返回插入成功的数据
				if (saveNums > 0) {
					result.put("success", true);
				} else {
					result.put("success", false);
					result.put("errorMsg", "保存失败");
				}
				ResponseUtil.write(response, result);// 发送到客户端
			} else {
				// 不可以进行注册，返回报错
				result.put("success", false);
				result.put("errorMsg", "该用户已存在");
				ResponseUtil.write(response, result);// 发送到客户端
			}

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
	 * Author:Taowd 功能：删除普通员工信息 开发日期：2017-5-7-下午7:27:05
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void DeleteEmployee(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String delIds = request.getParameter("delIds");// 取得删除的id字符串集合

		Log4jHelper.info("删除的员工工号：" + delIds);
		Connection con = null;
		try {
			con = DbUtils.getConnection();
			String[] str = delIds.split(",");
			JSONObject result = new JSONObject();
			for (int i = 0; i < str.length; i++) {
				// 检查是否入职，其他条件的就可以删除，比如：新增了入职申请未提交，管理员可强制删除
				boolean f = EmployeeDao.IsInduction(con, str[i]);
				if (f) {
					result.put("success", false);
					result.put("errorIndex", i);
					result.put("errorMsg", "入职申请已审批通过，不允许删除");
					ResponseUtil.write(response, result);
					return;
				}
				// 检查是否为部门领导
				boolean f2 = EmployeeDao.IsLeader(con, str[i]);
				if (f2) {
					result.put("success", false);
					result.put("errorIndex", i);
					result.put("errorMsg", "该员工为部门领导，不允许删除");
					ResponseUtil.write(response, result);
					return;
				}
			}

			int delNums = EmployeeDao.EmlopyeeDelete(con, StringUtil.FormatDeleteDelIds(delIds));// 返回批量删除的数量
			if (delNums > 0) {
				result.put("success", true);
				result.put("delNums", delNums);
			} else {
				result.put("success", false);
				result.put("errorMsg", "删除失败");
			}
			ResponseUtil.write(response, result);// 发送到客户端
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DbUtils.CloseConn(con);// 关闭连接
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * Author:Taowd 功能：更新普通员工信息 开发日期：2017-5-7-下午7:26:51
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void UpdateEmployee(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Log4jHelper.info("-----------进入保存员工信息的控制器-----------");
		request.setCharacterEncoding("utf-8");
		String stuNo = request.getParameter("EMP_NO");
		String passwd = request.getParameter("passwd");
		String stuName = request.getParameter("EMP_NAME");
		String sex = request.getParameter("EMP_SEX");
		String birthday = request.getParameter("EMP_Birthday");
		String ps_id = request.getParameter("PS_TYPE");
		String emp_phone = request.getParameter("EMP_Phone");
		String emp_address = request.getParameter("EMP_Address");
		String ext1 = request.getParameter("ext1");
		String ext2 = request.getParameter("ext2");
		String ext3 = request.getParameter("ext3");

		String encyPasswd = "";
		if (!StringUtils.isEmpty(passwd)) {
			encyPasswd = AESUtil.parseByte2HexStr(AESUtil.encrypt(passwd));
		}

		Employee empBean = null;
		try {
			empBean = new Employee(stuNo, encyPasswd, stuName, sex,
					DateUtil.formatString(birthday, "yyyy-MM-dd"), ps_id, emp_phone, emp_address,
					ext1, ext2, ext3);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		Connection con = null;
		try {
			con = DbUtils.getConnection();
			int saveNums = 0;
			JSONObject result = new JSONObject();
			// 如果是修改的话，返回修改成功的行数
			saveNums = EmployeeDao.EmployeeModify(con, empBean);
			if (saveNums > 0) {
				result.put("success", true);
			} else {
				result.put("success", false);
				result.put("errorMsg", "修改员工信息失败！");
			}
			// 发送到客户端
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			Log4jHelper.exception(e);
		}

	}

	/**
	 * 1、获取普通员工下拉列表--只获取员工号和员工姓名.<br>
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @see
	 */
	public void ComboListEmployee(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Log4jHelper.info("进入，获取获取部门领导下拉数据的处理逻辑");
		Connection con = null;
		try {
			con = DbUtils.getConnection();
			JSONArray jsonArray = new JSONArray();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("EMP_NO", "");
			jsonObject.put("EMP_NAME", "请选择...");
			jsonArray.add(jsonObject);
			// 加入整个集合
			// jsonArray.addAll(JsonUtil
			// .formatRsToJsonArray(EmployeeDao.EmployeetList(con, null, null, null, null)));//
			// 取得json数据
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

	/**
	 * Author:Taowd 功能：获取员工列表信息 开发日期：2017-5-7-下午7:29:11
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void EmployeeListInfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String stuNo = request.getParameter("EMP_NO");
		String stuName = request.getParameter("EMP_NAME");
		String sex = request.getParameter("EMP_SEX");
		String bbirthday = request.getParameter("bbirthday");
		String ebirthday = request.getParameter("ebirthday");
		String gradeId = request.getParameter("PS_TYPE");
		// String emp_phone = request.getParameter("EMP_Phone");
		// String emp_address = request.getParameter("EMP_Address");

		Employee emp = new Employee();
		if (gradeId != null) {
			emp.setEmp_no(stuNo);
			emp.setEmp_name(stuName);
			emp.setEmp_sex(sex);
			if (StringUtils.isNotBlank(gradeId)) {
				emp.setPs_id(gradeId);
			}
		}

		String page = request.getParameter("page");// 取得请求的参数
		String rows = request.getParameter("rows");
		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		Connection con = null;
		try {
			con = DbUtils.getConnection();
			JSONObject result = new JSONObject();
			Object jsonArray = JsonUtil.formatRsToJsonArray(
					EmployeeDao.EmployeetList(con, pageBean, emp, bbirthday, ebirthday));// 取得json数据
			int total = EmployeeDao.EmployeeCount(con, emp, bbirthday, ebirthday);// 总记录数
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
