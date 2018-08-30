package com.pms.servlet;


import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import com.pms.dao.AdministratorDao;
import com.pms.dao.EmployeeDao;
import com.pms.model.Administrator;
import com.pms.model.Employee;
import com.pms.util.AESUtil;
import com.pms.util.DbUtils;
import com.pms.util.Log4jHelper;
import com.pms.util.ResponseUtil;


/**
 * @author Taowd 功 能：修改用户的密码的逻辑 编写时间：2017-4-12-下午1:38:24
 */
public class PasswordModifyServlet extends HttpServlet
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
        Log4jHelper.info("--------------------修改密码控制器-----------------------");
        String passwd = request.getParameter("newPassword");
        HttpSession session = request.getSession();
        String ro = (String)session.getAttribute("userRole");

        int saveNums = 0;
        if ("user".equals(ro))// 普通用户修改密码
        {
            Employee userInfo = (Employee)session.getAttribute("currentUser");
            userInfo.setEmp_pwd(AESUtil.parseByte2HexStr(AESUtil.encrypt(passwd)));

            Connection con = null;
            JSONObject result = new JSONObject();
            try
            {
                con = DbUtils.getConnection();
                saveNums = EmployeeDao.EmployeeModify(con, userInfo);
                if (saveNums > 0)
                {
                    result.put("success", true);
                }
                else
                {
                    result.put("success", false);
                    result.put("errorMsg", "修改密码失败！");
                }
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
            Log4jHelper.info("普通用户信息：" + userInfo.toString());

        }
        else // 管理员修改密码逻辑
        {
            Administrator adminInfo = (Administrator)session.getAttribute("currentUser");
            adminInfo.setAdmin_pwd(AESUtil.parseByte2HexStr(AESUtil.encrypt(passwd)));
            Connection con = null;
            JSONObject result = new JSONObject();
            try
            {
                con = DbUtils.getConnection();
                saveNums = adminDao.adminModifyPasswd(adminInfo);
                if (saveNums > 0)
                {
                    result.put("success", true);
                }
                else
                {
                    result.put("success", false);
                    result.put("errorMsg", "修改密码失败！");
                }
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
            Log4jHelper.info("管理员用户信息：" + adminInfo.toString());
        }
    }
}
