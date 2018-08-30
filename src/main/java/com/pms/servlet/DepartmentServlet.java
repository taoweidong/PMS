package com.pms.servlet;


import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.pms.dao.DepartmentDao;
import com.pms.model.DepartmentBean;
import com.pms.model.PageBean;
import com.pms.util.BaseServlet;
import com.pms.util.DbUtils;
import com.pms.util.JsonUtil;
import com.pms.util.Log4jHelper;
import com.pms.util.ResponseUtil;
import com.pms.util.StringUtil;


public class DepartmentServlet extends BaseServlet
{

    private static final long serialVersionUID = 1L;

    /**
     * Author:Taowd 功能：新增部门信息 开发日期：2017-5-8-下午5:17:13
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void addDepartment(HttpServletRequest request, HttpServletResponse response)
        throws ServletException,
        IOException
    {
        Log4jHelper.info("进入部门信息新增的操作");

        request.setCharacterEncoding("utf-8");
        String DEP_ID = request.getParameter("DEP_ID");
        String DEP_NAME = request.getParameter("DEP_NAME");
        String DEP_LEADER = request.getParameter("DEP_LEADER");

        DepartmentBean db = new DepartmentBean(DEP_ID, DEP_NAME, DEP_LEADER);

        if (DEP_LEADER == null || DEP_LEADER.equals(""))
        {
            db.setDep_Leader(null);
        }

        Connection con = null;
        try
        {
            con = DbUtils.getConnection();
            int saveNums = 0;
            JSONObject result = new JSONObject();
            // 检查部门信息是否已经增加，主要是根据部门ID进行检查
            if (DepartmentDao.isExistence(con, DEP_ID))
            {
                result.put("success", false);
                result.put("errorMsg", "该部门信息已存在！");
            }
            else
            {
                db.setDep_Id("POS" + StringUtil.GetUUID());
                // 新增部门信息，返回成功的行数
                saveNums = DepartmentDao.departmentAdd(con, db);
                if (saveNums > 0)
                {
                    result.put("success", true);
                }
                else
                {
                    result.put("success", false);
                    result.put("errorMsg", "新增部门信息失败");
                }
            }
            ResponseUtil.write(response, result);// 发送到客户端
        }
        catch (Exception e)
        {
            Log4jHelper.exception(e);
        }
        finally
        {
            try
            {
                DbUtils.CloseConn(con);// 关闭连接
            }
            catch (Exception e)
            {
                Log4jHelper.exception(e);
            }
        }
    }

    /**
     * Author:Taowd 功能：删除部门信息 开发日期：2017-5-8-下午5:18:27
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void DeleteDepartment(HttpServletRequest request, HttpServletResponse response)
        throws ServletException,
        IOException
    {
        String delIds = request.getParameter("delIds");// 取得删除的id字符串集合
        Log4jHelper.info("进入删除控制器");
        Connection con = null;
        try
        {
            con = DbUtils.getConnection();
            String[] str = delIds.split(",");
            JSONObject result = new JSONObject();
            for (int i = 0; i < str.length; i++ )
            {
                boolean f = DepartmentDao.getPosInfoByDepId(con, str[i]);
                if (f)
                {
                    result.put("success", false);
                    result.put("errorIndex", i);
                    result.put("errorMsg", "部门存在岗位信息，不能删除！");
                    ResponseUtil.write(response, result);
                    return;
                }
            }
            int delNums = DepartmentDao.departmentDelete(con,
                StringUtil.FormatDeleteDelIds(delIds));// 返回批量删除的数量
            if (delNums > 0)
            {
                result.put("success", true);
                result.put("delNums", delNums);
            }
            else
            {
                result.put("success", false);
                result.put("errorMsg", "删除失败");
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
    }

    /**
     * Author:Taowd 功能：修改部门信息 开发日期：2017-5-8-下午5:17:33
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void UpdateDepartment(HttpServletRequest request, HttpServletResponse response)
        throws ServletException,
        IOException
    {
        Log4jHelper.info("进入部门信息更新的操作");

        request.setCharacterEncoding("utf-8");
        String DEP_ID = request.getParameter("DEP_ID");
        String DEP_NAME = request.getParameter("DEP_NAME");
        String DEP_LEADER = request.getParameter("DEP_LEADER");

        DepartmentBean db = new DepartmentBean(DEP_ID, DEP_NAME, DEP_LEADER);

        if (DEP_LEADER == null || DEP_LEADER.equals(""))
        {
            db.setDep_Leader(null);
        }

        Connection con = null;
        try
        {
            con = DbUtils.getConnection();
            int saveNums = 0;
            JSONObject result = new JSONObject();
            // 如果是修改的话，返回修改成功的行数
            saveNums = DepartmentDao.departmentModify(con, db);
            if (saveNums > 0)
            {
                result.put("success", true);
            }
            else
            {
                result.put("success", false);
                result.put("errorMsg", "修改部门信息失败！");
            }
            ResponseUtil.write(response, result);// 发送到客户端
        }
        catch (Exception e)
        {
            Log4jHelper.exception(e);
        }
        finally
        {
            try
            {
                DbUtils.CloseConn(con);// 关闭连接
            }
            catch (Exception e)
            {
                Log4jHelper.exception(e);
            }
        }

    }

    /**
     * Author:Taowd 功能：部门信息列表 开发日期：2017-5-8-下午5:19:01
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void DepartmentListInfo(HttpServletRequest request, HttpServletResponse response)
        throws ServletException,
        IOException
    {
        Log4jHelper.info("进入获取部门信息列表的操作");
        request.setCharacterEncoding("utf-8");
        String page = request.getParameter("page");// 取得请求的参数
        String rows = request.getParameter("rows");
        String dep_Id = request.getParameter("DEP_ID");
        String dep_Name = request.getParameter("DEP_NAME");
        String dep_Leader = request.getParameter("DEP_LEADER");

        DepartmentBean grade = new DepartmentBean();
        grade.setDep_Id(dep_Id);
        grade.setDep_Name(dep_Name);
        if (dep_Leader != null && !dep_Leader.equals("请选择..."))
        {
            grade.setDep_Leader(dep_Leader);
        }

        PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
        Connection con = null;
        try
        {
            con = DbUtils.getConnection();
            JSONObject result = new JSONObject();
            JSONArray jsonArray = JsonUtil.formatRsToJsonArray(
                DepartmentDao.departmentList(con, pageBean, grade));// 取得json数据
            int total = DepartmentDao.departmentCount(con, grade);// 总记录数
            result.put("rows", jsonArray);// 封装数据
            result.put("total", total);
            ResponseUtil.write(response, result);// 发送到客户端
        }
        catch (Exception e)
        {
            Log4jHelper.exception(e);
        }
        finally
        {
            try
            {
                DbUtils.CloseConn(con);// 关闭连接
            }
            catch (Exception e)
            {
                Log4jHelper.exception(e);
            }
        }

    }

    /**
     * Author:Taowd 功能：获取下拉框部门信息 开发日期：2017-5-8-下午5:19:36
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void DepartmentComboboxInfo(HttpServletRequest request, HttpServletResponse response)
        throws ServletException,
        IOException
    {

        Log4jHelper.info("进入获取部门下拉框数据的操作");
        Connection con = null;
        try
        {
            con = DbUtils.getConnection();
            JSONArray jsonArray = new JSONArray();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("DEP_ID", "");
            jsonObject.put("DEP_NAME", "请选择...");
            jsonArray.add(jsonObject);
            // 加入整个集合
            jsonArray.addAll(
                JsonUtil.formatRsToJsonArray(DepartmentDao.departmentList(con, null, null)));// 取得json数据
            ResponseUtil.write(response, jsonArray);// 发送到客户端
        }
        catch (Exception e)
        {
            Log4jHelper.exception(e);
        }
        finally
        {
            try
            {
                DbUtils.CloseConn(con);// 关闭连接
            }
            catch (Exception e)
            {
                Log4jHelper.exception(e);
            }
        }

    }

}