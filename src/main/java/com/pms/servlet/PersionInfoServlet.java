package com.pms.servlet;


import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.pms.dao.AdministratorDao;
import com.pms.dao.EmployeeDao;
import com.pms.model.Administrator;
import com.pms.model.Employee;
import com.pms.model.PageBean;
import com.pms.util.DbUtils;
import com.pms.util.JsonUtil;
import com.pms.util.Log4jHelper;
import com.pms.util.ResponseUtil;


/**
 * @author Taowd 功 能：获取个人信息 编写时间：2017-4-11-下午9:09:20
 */
public class PersionInfoServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    EmployeeDao empDao = new EmployeeDao();

    AdministratorDao adminDao = new AdministratorDao();

    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException,
        IOException
    {

        this.doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException,
        IOException
    {
        Log4jHelper.info("获取个人信息----");
        HttpSession session = request.getSession();
        String ro = (String)session.getAttribute("userRole");

        if ("user".equals(ro))
        {
            Employee userInfo = (Employee)session.getAttribute("currentUser");
            Employee emp = new Employee();
            emp.setEmp_no(userInfo.getEmp_no());
            String page = request.getParameter("page");// 取得请求的参数
            String rows = request.getParameter("rows");
            PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
            Connection con = null;
            try
            {
                con = DbUtils.getConnection();
                JSONObject result = new JSONObject();
                JSONArray jsonArray = JsonUtil.formatRsToJsonArray(
                    EmployeeDao.EmployeetPersionInfo(con, pageBean, emp));// 取得json数据
                int total = EmployeeDao.EmployeeCount(con, emp, null, null);// 总记录数
                result.put("rows", jsonArray);// 封装数据
                result.put("total", total);
                ResponseUtil.write(response, result);// 发送到客户端
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            finally
            {
                try
                {
                    DbUtils.CloseConn(con);// 关闭连接
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
        else if ("admin".equals(ro) || "superAdmin".equals(ro))
        {
            Administrator adminInfo = (Administrator)session.getAttribute("currentUser");
            String page = request.getParameter("page");// 取得请求的参数
            String rows = request.getParameter("rows");
            PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
            Connection con = null;
            try
            {
                con = DbUtils.getConnection();
                JSONObject result = new JSONObject();
                JSONArray jsonArray = JsonUtil.formatRsToJsonArray(
                    adminDao.getAdminInfo(con, pageBean, adminInfo));// 取得json数据
                int total = adminDao.getAdminCount(adminInfo);// 总记录数
                result.put("rows", jsonArray);// 封装数据
                result.put("total", total);
                ResponseUtil.write(response, result);// 发送到客户端
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            finally
            {
                try
                {
                    DbUtils.CloseConn(con);// 关闭连接
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }

        }
    }

}
