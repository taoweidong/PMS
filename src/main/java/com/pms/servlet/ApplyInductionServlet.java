package com.pms.servlet;


import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.pms.dao.InductionInfoDao;
import com.pms.model.Employee;
import com.pms.model.InductionInfoBean;
import com.pms.model.PageBean;
import com.pms.util.BaseServlet;
import com.pms.util.DateUtil;
import com.pms.util.DbUtils;
import com.pms.util.JsonUtil;
import com.pms.util.Log4jHelper;
import com.pms.util.ResponseUtil;
import com.pms.util.StringUtil;


/**
 * @author Taowd 功 能：岗位申请的处理控制器，报错职位申请，修改，删除，提交，审批等操作 编写时间：2017-5-7-下午2:34:57
 */
public class ApplyInductionServlet extends BaseServlet
{

    private static final long serialVersionUID = 1L;

    InductionInfoDao inductionDao = new InductionInfoDao();

    /**
     * Author:Taowd 功能：查询管理员信息-只查询已经提交的信息 开发日期：2017-5-7-下午7:09:49
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void AdminApproveInfoList(HttpServletRequest request, HttpServletResponse response)
        throws ServletException,
        IOException
    {

        request.setCharacterEncoding("utf-8");
        String page = request.getParameter("page");// 取得请求的参数
        String rows = request.getParameter("rows");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String EMP_NO = request.getParameter("EMP_NO");
        String approveState = request.getParameter("approveState");
        String IND_STATE = request.getParameter("IND_STATE");
        String POS_NAME = request.getParameter("POS_NAME");

        InductionInfoBean inductionBean = new InductionInfoBean();

        inductionBean.setEmp_No(EMP_NO);
        inductionBean.setExt1(approveState);
        inductionBean.setInd_State(IND_STATE);
        inductionBean.setPos_Name(POS_NAME);

        // 管理员加载数据时将
        inductionBean.setExt1(approveState);
        PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
        Connection con = null;
        try
        {
            con = DbUtils.getConnection();
            JSONObject result = new JSONObject();
            JSONArray jsonArray = JsonUtil.formatRsToJsonArray(
                InductionInfoDao.AdminInductionInfoList(con, pageBean, inductionBean, startDate,
                    endDate));// 取得json数据
            int total = InductionInfoDao.InductionInfoCount(con, inductionBean);// 总记录数
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
     * Author:Taowd 功能：个人获取信息列表 开发日期：2017-5-7-下午7:02:23
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void UserApproveInfoList(HttpServletRequest request, HttpServletResponse response)
        throws ServletException,
        IOException
    {

        request.setCharacterEncoding("utf-8");
        String page = request.getParameter("page");// 取得请求的参数
        String rows = request.getParameter("rows");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String EMP_NO = request.getParameter("EMP_NO");
        String approveState = request.getParameter("approveState");
        String IND_STATE = request.getParameter("IND_STATE");
        String POS_NAME = request.getParameter("POS_NAME");

        HttpSession session = request.getSession();

        InductionInfoBean inductionBean = new InductionInfoBean();

        inductionBean.setEmp_No(EMP_NO);
        inductionBean.setExt1(approveState);
        inductionBean.setInd_State(IND_STATE);
        inductionBean.setPos_Name(POS_NAME);

        Employee userInfo = (Employee)session.getAttribute("currentUser");

        inductionBean.setEmp_No(userInfo.getEmp_no());
        PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
        Connection con = null;
        try
        {
            con = DbUtils.getConnection();
            JSONObject result = new JSONObject();
            JSONArray jsonArray = JsonUtil.formatRsToJsonArray(InductionInfoDao.InductionInfoList(
                con, pageBean, inductionBean, startDate, endDate));// 取得json数据
            int total = InductionInfoDao.InductionInfoCount(con, inductionBean);// 总记录数
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
     * Author:Taowd 功能：普通用户提交申请信息 开发日期：2017-5-7-下午2:38:35
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void UserApprove(HttpServletRequest request, HttpServletResponse response)
        throws ServletException,
        IOException
    {
        Log4jHelper.info("普通用户提交申请信息");
        request.setCharacterEncoding("utf-8");
        InductionInfoBean induction = new InductionInfoBean();
        String delIds = request.getParameter("delIds");// 取得删除的id字符串集合
        try
        {
            String[] str = delIds.split(",");
            JSONObject result = new JSONObject();
            int intData = str.length;
            for (int i = 0; i < str.length; i++ )
            {
                // 为true可删除
                boolean f = InductionInfoDao.CheckApproveState(str[i]);
                if (!f)
                {
                    result.put("success", false);
                    result.put("errorIndex", i);
                    result.put("errorMsg", "该申请已提交,不能重复提交");
                    intData-- ;
                }
                else
                {
                    induction.setInd_Id(str[i]);
                    // 00-已提交
                    induction.setExt1("00");
                    int delNums = InductionInfoDao.ApplyInductionApprove(induction);// 返回批量删除的数量
                    if (delNums > 0)
                    {
                        result.put("success", true);
                        result.put("delNums", intData);
                    }
                    else
                    {
                        result.put("success", false);
                        result.put("errorMsg", "提交失败");
                        intData-- ;
                    }
                }
            }
            ResponseUtil.write(response, result);// 发送到客户端

        }
        catch (Exception e)
        {
            Log4jHelper.exception(e);
        }

    }

    /**
     * Author:Taowd 功能：删除个人的申请信息 开发日期：2017-5-7-下午3:46:49
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void DeleteUserApplyInduction(HttpServletRequest request, HttpServletResponse response)
        throws ServletException,
        IOException
    {
        Log4jHelper.info("***************进入删除控制器***********************");
        request.setCharacterEncoding("utf-8");
        String delIds = request.getParameter("delIds");// 取得删除的id字符串集合
        try
        {

            String[] str = delIds.split(",");
            JSONObject result = new JSONObject();
            for (int i = 0; i < str.length; i++ )
            {
                // 为true可删除
                boolean f = InductionInfoDao.CheckApproveState(str[i]);
                if (!f)
                {
                    result.put("success", false);
                    result.put("errorIndex", i);
                    result.put("errorMsg", "申请已提交,不能删除");
                    ResponseUtil.write(response, result);
                    return;

                }
            }
            int delNums = InductionInfoDao.InductionDelete(StringUtil.FormatDeleteDelIds(delIds));// 返回批量删除的数量
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

    }

    /**
     * Author:Taowd 功能：管理员删除申请信息 开发日期：2017-5-7-下午4:04:02
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void DeleteAdminApplyInduction(HttpServletRequest request, HttpServletResponse response)
        throws ServletException,
        IOException
    {
        request.setCharacterEncoding("utf-8");
        String delIds = request.getParameter("delIds");// 取得删除的id字符串集合
        try
        {
            JSONObject result = new JSONObject();
            int delNums = InductionInfoDao.InductionDelete(StringUtil.FormatDeleteDelIds(delIds));// 返回批量删除的数量
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
    }

    /**
     * Author:Taowd 功能：个人职位信息新增--即增加入职申请 开发日期：2017-5-7-下午5:17:19
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void AddUserApplyInduction(HttpServletRequest request, HttpServletResponse response)
        throws ServletException,
        IOException
    {
        Log4jHelper.info("个人职位申请信息的新增逻辑");
        // DEP_ID
        request.setCharacterEncoding("utf-8");
        String pos_Id = request.getParameter("POS_ID");
        String ind_ID = request.getParameter("IND_ID");
        String applyStyle = request.getParameter("EXT3");

        InductionInfoBean induction = new InductionInfoBean();
        induction.setPos_Id(pos_Id);
        induction.setInd_Id(ind_ID);
        HttpSession session = request.getSession();
        Employee userInfo = (Employee)session.getAttribute("currentUser");
        induction.setEmp_No(userInfo.getEmp_no());
        // 新增时审批状态为：33-未提交
        induction.setExt1("33");
        // 入职类别：IN-入职申请
        if (applyStyle.equals("IN"))
        {
            induction.setExt3("IN");
        }
        else
        {
            induction.setExt3("OUT");
        }

        Log4jHelper.info("新增信息：" + induction.toString());

        try
        {
            int saveNums = 0;
            boolean isExistenceFlag = false;
            if (StringUtil.isNotEmpty(induction.getInd_Id()))
            {
                // 检查该部门是否已经申请过了，如果申请过了，就不允许再次申请
                isExistenceFlag = InductionInfoDao.IsExistence(induction);
            }

            JSONObject result = new JSONObject();
            // 检查新增员工号是否已经注册
            if (isExistenceFlag)
            {
                result.put("success", false);
                result.put("errorMsg", "该部门此申请类别已经申请，不能重复申请！");
            }
            else
            {
                // 新增员工，返回成功的行数
                saveNums = InductionInfoDao.ApplyInduction(induction);
                if (saveNums > 0)
                {
                    result.put("success", true);
                }
                else
                {
                    result.put("success", false);
                    result.put("errorMsg", "新增申请信息失败");
                }
            }
            ResponseUtil.write(response, result);// 发送到客户端

        }
        catch (Exception e)
        {
            Log4jHelper.exception(e);
        }

    }

    /**
     * Author:Taowd 功能：更新用户的申请信息-指未提交前可进行修改 开发日期：2017-5-7-下午4:51:40
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void UpdateUserApplyApprove(HttpServletRequest request, HttpServletResponse response)
        throws ServletException,
        IOException
    {
        Log4jHelper.info("个人职位申请信息的修改逻辑");
        // DEP_ID
        request.setCharacterEncoding("utf-8");
        String pos_Id = request.getParameter("POS_ID");
        String ind_ID = request.getParameter("IND_ID");
        String applyStyle = request.getParameter("EXT3");

        InductionInfoBean induction = new InductionInfoBean();
        induction.setPos_Id(pos_Id);
        induction.setInd_Id(ind_ID);
        HttpSession session = request.getSession();
        Employee userInfo = (Employee)session.getAttribute("currentUser");
        induction.setEmp_No(userInfo.getEmp_no());
        // 新增时审批状态为：33-未提交
        induction.setExt1("33");
        // 入职类别：IN-入职申请
        if (applyStyle.equals("IN"))
        {
            induction.setExt3("IN");
        }
        else
        {
            induction.setExt3("OUT");
        }

        Log4jHelper.info("保存信息：" + induction.toString());

        try
        {

            int saveNums = 0;
            // 检查该部门是否已经申请过了，如果申请过了，就不允许再次申请
            boolean isExistenceFlag = InductionInfoDao.IsExistence(induction);
            JSONObject result = new JSONObject();
            // 检查申请状态是否可修改：即ext1=="33"??
            if (!InductionInfoDao.CheckApproveState(induction.getInd_Id()))
            {
                result.put("success", false);
                result.put("errorMsg", "该申请已经提交，不能修改！");

            }
            else
            {
                if (isExistenceFlag)
                {
                    result.put("success", false);
                    result.put("errorMsg", "该部门此申请类别已经申请，不能重复申请！");

                }
                else
                {
                    // 如果是修改的话，返回修改成功的行数
                    saveNums = InductionInfoDao.ApplyInductionModify(induction);
                    if (saveNums > 0)
                    {
                        result.put("success", true);
                    }
                    else
                    {
                        result.put("success", false);
                        result.put("errorMsg", "申请信息修改失败！");
                    }
                }
            }
            ResponseUtil.write(response, result);// 发送到客户端

        }
        catch (Exception e)
        {
            Log4jHelper.exception(e);
        }

    }

    /**
     * Author:Taowd 功能：管理员修改审批信息 开发日期：2017-5-7-下午4:39:24
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void AdminUpdateApplyApprove(HttpServletRequest request, HttpServletResponse response)
        throws ServletException,
        IOException
    {
        Log4jHelper.info("管理员修改审批信息");
        request.setCharacterEncoding("utf-8");
        InductionInfoBean induction = new InductionInfoBean();
        String delIds = request.getParameter("delIds");
        String approvalComments = request.getParameter("EXT1");

        try
        {

            String[] str = delIds.split(",");
            JSONObject result = new JSONObject();
            for (int i = 0; i < str.length; i++ )
            {

                induction.setInd_Id(str[i]);
                induction.setExt1(approvalComments);
                induction.setExt2(DateUtil.getCurrentDateStr());
                // 修改时 不需要检查 是否可以进行审批操作
                // 管理员进行审批申请
                int delNums = InductionInfoDao.ApplyInductionApprove(induction);
                if (delNums > 0)
                {
                    result.put("success", true);
                    result.put("delNums", delNums);
                }
                else
                {
                    result.put("success", false);
                    result.put("errorMsg", "提交失败");
                }

            }
            ResponseUtil.write(response, result);// 发送到客户端
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Author:Taowd 功能：管理员审批申请 开发日期：2017-5-7-下午3:05:43
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void AdminApplyApprove(HttpServletRequest request, HttpServletResponse response)
        throws ServletException,
        IOException
    {

        request.setCharacterEncoding("utf-8");
        InductionInfoBean induction = new InductionInfoBean();
        String delIds = request.getParameter("delIds");
        String approvalComments = request.getParameter("EXT1");
        // 如果是修改则会接收到 update 新增不会接收到任何字符串
        String saveFlag = request.getParameter("saveFlag");

        try
        {

            String[] str = delIds.split(",");
            JSONObject result = new JSONObject();
            for (int i = 0; i < str.length; i++ )
            {
                // 为true可进行审批操作
                boolean f = InductionInfoDao.IsApproveState(str[i]);
                if (!f && !"update".equals(saveFlag))
                {
                    result.put("success", false);
                    result.put("errorIndex", i);
                    result.put("errorMsg", "该申请已审批,不能重复审批");
                }
                else
                {
                    induction.setInd_Id(str[i]);
                    induction.setExt1(approvalComments);
                    induction.setExt2(DateUtil.getCurrentDateStr());
                    // 管理员进行审批申请
                    int delNums = InductionInfoDao.ApplyInductionApprove(induction);
                    if (delNums > 0)
                    {
                        result.put("success", true);
                        result.put("delNums", delNums);
                    }
                    else
                    {
                        result.put("success", false);
                        result.put("errorMsg", "提交失败");
                    }
                }
            }
            ResponseUtil.write(response, result);// 发送到客户端
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

}
