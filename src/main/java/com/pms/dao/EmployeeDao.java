package com.pms.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.pms.model.Employee;
import com.pms.model.PageBean;
import com.pms.util.DateUtil;
import com.pms.util.Log4jHelper;
import com.pms.util.StringUtil;


public class EmployeeDao
{
    /**
     * 功能：检查用户的密码
     * 
     * @param con
     * @param emp
     * @return 查询到该用户时，返回该用户的信息实体，没有查到时返回null
     * @throws Exception
     */
    public Employee CheckPwd(Connection con, Employee emp)
    {
        Employee resultUser = null;
        String sql = "SELECT * FROM pms.t_employee WHERE t_employee.EMP_NO =? and t_employee.EMP_PWD=?";
        PreparedStatement pstmt;
        try
        {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, emp.getEmp_no());
            pstmt.setString(2, emp.getEmp_pwd());
            Log4jHelper.info("普通用户检查密码：" + pstmt.toString());
            ResultSet rs = pstmt.executeQuery();
            if (rs.next())
            {
                resultUser = new Employee();
                resultUser.setEmp_no(rs.getString(("EMP_NO")));
                resultUser.setEmp_pwd(rs.getString("EMP_PWD"));
                resultUser.setEmp_name(rs.getString("EMP_NAME"));
                resultUser.setEmp_sex(rs.getString("EMP_SEX"));
                resultUser.setEmp_birthday(rs.getDate("EMP_Birthday"));
                resultUser.setPs_id(rs.getString("PS_ID"));
                resultUser.setEmp_phone(rs.getString("EMP_Phone"));
                resultUser.setEmp_address(rs.getString("EMP_Address"));
            }
        }
        catch (SQLException e)
        {
            Log4jHelper.exception(e);
        }

        return resultUser;
    }

    /**
     * 功能：根据条件查询员工信息
     * 
     * @param con
     * @param pageBean
     * @param emp
     * @param bbirthday
     * @param ebirthday
     * @return
     * @throws Exception
     */
    public static ResultSet EmployeetList(Connection con, PageBean pageBean, Employee emp,
                                          String bbirthday, String ebirthday)
        throws Exception
    {
        StringBuffer sb = new StringBuffer(
            "SELECT  emp.EMP_NO,EMP_PWD,EMP_NAME,emp.emp_sex, (case WHEN emp_sex='F'then '女' WHEN emp_sex='M'then '男' END ) as sexName,emp.EMP_Birthday,zzmm.PS_TYPE,zzmm.PS_Name,EMP_Phone, emp.EMP_Address, emp.ext1,emp.ext2 FROM pms.t_politicalstatus zzmm, pms.t_employee emp WHERE zzmm.PS_TYPE = emp.PS_ID ");
        if (emp != null && StringUtil.isNotEmpty(emp.getEmp_name()))
        {
            sb.append(" AND emp.emp_name LIKE '%" + emp.getEmp_name() + "%'");
        }
        if (emp != null && StringUtil.isNotEmpty(emp.getEmp_no()))
        {
            sb.append("AND emp.emp_no LIKE '%" + emp.getEmp_no() + "%'");
        }
        if (emp != null && StringUtil.isNotEmpty(emp.getEmp_sex()))
        {
            sb.append("AND emp.emp_sex LIKE '%" + emp.getEmp_sex() + "%'");
        }
        if (emp != null && StringUtil.isNotEmpty(emp.getEmp_phone()))
        {
            sb.append("AND emp.EMP_Phone LIKE '%" + emp.getEmp_phone() + "%'");
        }
        if (emp != null && StringUtil.isNotEmpty(emp.getEmp_address()))
        {
            sb.append("AND emp.EMP_Address LIKE '%" + emp.getEmp_address() + "%'");
        }
        // and zzmm.PS_TYPE = '102'
        if (emp != null && StringUtil.isNotEmpty(emp.getPs_id()))
        {
            sb.append("AND zzmm.PS_TYPE LIKE '%" + emp.getPs_id() + "%'");
        }
        if (emp != null && StringUtil.isNotEmpty(bbirthday))
        {
            sb.append(" and TO_DAYS(emp.EMP_Birthday) >= TO_DAYS('" + bbirthday + "')");
        }
        if (emp != null && StringUtil.isNotEmpty(ebirthday))
        {
            sb.append(" and TO_DAYS(emp.EMP_Birthday) <= TO_DAYS('" + ebirthday + "')");
        }
        if (emp != null && pageBean != null)
        {
            sb.append(" limit " + pageBean.getStart() + "," + pageBean.getRows());
        }
        Log4jHelper.info("查询员工信息：" + sb.toString());
        PreparedStatement pstmt = con.prepareStatement(sb.toString());
        return pstmt.executeQuery();
    }

    /**
     * 功能：根据条件获取员工总数
     * 
     * @param con
     * @param student
     * @param bbirthday
     * @param ebirthday
     * @return 员工总数
     * @throws Exception
     */
    public static int EmployeeCount(Connection con, Employee student, String bbirthday,
                                    String ebirthday)
        throws Exception
    {
        Log4jHelper.info("参数：emp" + student.toString());
        StringBuffer sb = new StringBuffer(
            "SELECT  count(*) as total FROM pms.t_politicalstatus zzmm, pms.t_employee emp WHERE zzmm.PS_TYPE = emp.PS_ID ");
        if (StringUtil.isNotEmpty(student.getEmp_name()))
        {
            sb.append(" AND emp.emp_name LIKE '%" + student.getEmp_name() + "%'");
        }
        if (StringUtil.isNotEmpty(student.getEmp_no()))
        {
            sb.append("AND emp.emp_no LIKE '%" + student.getEmp_no() + "%'");
        }
        if (StringUtil.isNotEmpty(student.getEmp_sex()))
        {
            sb.append("AND emp.emp_sex LIKE '%" + student.getEmp_sex() + "%'");
        }
        if (StringUtil.isNotEmpty(student.getEmp_phone()))
        {
            sb.append("AND emp.EMP_Phone LIKE '%" + student.getEmp_phone() + "%'");
        }
        if (StringUtil.isNotEmpty(student.getEmp_address()))
        {
            sb.append("AND emp.EMP_Address LIKE '%" + student.getEmp_address() + "%'");
        }
        if (StringUtil.isNotEmpty(bbirthday))
        {
            sb.append(" and TO_DAYS(emp.EMP_Birthday) >= TO_DAYS('" + bbirthday + "')");
        }
        if (StringUtil.isNotEmpty(ebirthday))
        {
            sb.append(" and TO_DAYS(emp.EMP_Birthday) <= TO_DAYS('" + ebirthday + "')");
        }
        Log4jHelper.info(sb.toString());
        PreparedStatement pstmt = con.prepareStatement(sb.toString());
        ResultSet rs = pstmt.executeQuery();
        if (rs.next())
        {
            return rs.getInt("total");
        }
        else
        {
            return 0;
        }
    }

    /**
     * 功能：删除员工信息
     * 
     * @param con
     * @param delIds
     * @return 删除成功的行数
     * @throws Exception
     */
    public static int EmlopyeeDelete(Connection con, String delIds)
        throws Exception
    {

        // 检查t_induction 表中是否存在delIds用户的信息，如果存在则进行删除
        String querySql = "select * from t_inductioninfo where EMP_NO in ( " + delIds + ")";
        PreparedStatement queryPstmt = con.prepareStatement(querySql);
        Log4jHelper.info("检查t_induction中是否有delIds用户的信息：" + queryPstmt.toString());
        ResultSet rs = queryPstmt.executeQuery();
        if (rs.next())
        {
            // 删除t_induction表该用户的数据
            // 删除两种类型的数据： 新增入职申请未提交，入职申请提交审批未通过
            String sql1 = "delete from pms.t_inductioninfo where t_inductioninfo.EMP_NO in ( "
                          + delIds + ")";
            Log4jHelper.info("删除申请信息:" + sql1);
            PreparedStatement pstmt1 = con.prepareStatement(sql1);
            if (pstmt1.executeUpdate() > 0)
            {
                String sql = "DELETE FROM pms.t_employee WHERE t_employee.EMP_NO IN(" + delIds
                             + ")";
                PreparedStatement pstmt = con.prepareStatement(sql);
                Log4jHelper.info("删除员工信息：" + pstmt.toString());
                return pstmt.executeUpdate();
            }
            else
            {

                return 0;
            }
        }
        else
        {
            // 如果t_induction表中不存在该用户的信息，则直接删除
            String sql = "DELETE FROM pms.t_employee WHERE t_employee.EMP_NO IN(" + delIds + ")";
            PreparedStatement pstmt = con.prepareStatement(sql);
            Log4jHelper.info("删除员工信息：" + pstmt.toString());
            return pstmt.executeUpdate();
        }

    }

    /**
     * 功能：增加用户信息
     * 
     * @param con
     * @param student
     * @return
     * @throws Exception
     */
    public static int EmployeeAdd(Connection con, Employee emp)
        throws Exception
    {
        String sql = "INSERT INTO pms.t_employee() VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, emp.getEmp_no());
        pstmt.setString(2, emp.getEmp_pwd());
        pstmt.setString(3, emp.getEmp_name());
        pstmt.setString(4, emp.getEmp_sex());
        pstmt.setString(5, DateUtil.formatDate(emp.getEmp_birthday(), "yyyy-MM-dd"));
        pstmt.setString(6, emp.getPs_id());
        pstmt.setString(7, emp.getEmp_phone());
        pstmt.setString(8, emp.getEmp_address());
        pstmt.setString(9, emp.getExt1());
        pstmt.setString(10, DateUtil.getCurrentDateStr());
        pstmt.setString(11, emp.getExt3());

        Log4jHelper.info("新增员工信息：" + pstmt.toString());
        return pstmt.executeUpdate();
    }

    /**
     * 功能：职工注册
     * 
     * @param con
     * @param student
     * @return
     * @throws Exception
     */
    public static int EmployeeRegister(Connection con, Employee emp)
        throws Exception
    {
        String sql = "INSERT INTO pms.t_employee() VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pstmt = con.prepareStatement(sql);

        pstmt.setString(1, emp.getEmp_no());
        // 员工注册时，使用注册的密码
        pstmt.setString(2, emp.getEmp_pwd());
        pstmt.setString(3, emp.getEmp_name());
        pstmt.setString(4, emp.getEmp_sex());
        pstmt.setString(5, DateUtil.formatDate(emp.getEmp_birthday(), "yyyy-MM-dd"));
        pstmt.setString(6, emp.getPs_id());
        pstmt.setString(7, emp.getEmp_phone());
        pstmt.setString(8, emp.getEmp_address());
        pstmt.setString(9, emp.getExt1());
        // 更新日期
        pstmt.setString(10, DateUtil.getCurrentDateStr());
        pstmt.setString(11, emp.getExt3());
        Log4jHelper.info("员工注册：" + pstmt.toString());
        return pstmt.executeUpdate();
    }

    /**
     * 功能：修改用户信息
     * 
     * @param con
     * @param student
     * @return 修改成功返回行数
     * @throws Exception
     */
    public static int EmployeeModify(Connection con, Employee emp)
    {
        Log4jHelper.info("更新参数：" + emp.toString());
        try
        {
            if (emp != null && StringUtil.isNotEmpty(emp.getEmp_pwd()))
            {
                String sql = "UPDATE pms.t_employee SET t_employee.EMP_NAME=?,EMP_PWD=?,t_employee.EMP_SEX=?,t_employee.EMP_Birthday =?,t_employee.PS_ID=?,t_employee.EMP_Phone=?,t_employee.EMP_Address=?,t_employee.ext1=?,t_employee.ext2=?,t_employee.ext3=? WHERE t_employee.EMP_NO=?";
                PreparedStatement pstmt = con.prepareStatement(sql);
                pstmt.setString(1, emp.getEmp_name());
                pstmt.setString(2, emp.getEmp_pwd());
                pstmt.setString(3, emp.getEmp_sex());
                pstmt.setString(4, DateUtil.formatDate(emp.getEmp_birthday(), "yyyy-MM-dd"));
                pstmt.setString(5, emp.getPs_id());
                pstmt.setString(6, emp.getEmp_phone());
                pstmt.setString(7, emp.getEmp_address());
                pstmt.setString(8, emp.getExt1());
                pstmt.setString(9, DateUtil.getCurrentDateStr());
                pstmt.setString(10, emp.getExt3());
                // 修改条件
                pstmt.setString(11, emp.getEmp_no());
                // 打印执行的Sql语句
                Log4jHelper.info("修改的Sql语句：" + pstmt.toString());
                return pstmt.executeUpdate();
            }
            else
            {
                String sql = "UPDATE pms.t_employee SET t_employee.EMP_NAME=?,t_employee.EMP_SEX=?,t_employee.EMP_Birthday =?,t_employee.PS_ID=?,t_employee.EMP_Phone=?,t_employee.EMP_Address=?,t_employee.ext1=?,t_employee.ext2=?,t_employee.ext3=? WHERE t_employee.EMP_NO=?";
                PreparedStatement pstmt = con.prepareStatement(sql);
                pstmt.setString(1, emp.getEmp_name());
                pstmt.setString(2, emp.getEmp_sex());
                pstmt.setString(3, DateUtil.formatDate(emp.getEmp_birthday(), "yyyy-MM-dd"));
                pstmt.setString(4, emp.getPs_id());
                pstmt.setString(5, emp.getEmp_phone());
                pstmt.setString(6, emp.getEmp_address());
                pstmt.setString(7, emp.getExt1());
                pstmt.setString(8, DateUtil.getCurrentDateStr());
                pstmt.setString(9, emp.getExt3());
                // 修改条件
                pstmt.setString(10, emp.getEmp_no());
                // 打印执行的Sql语句
                Log4jHelper.info("修改的Sql语句：" + pstmt.toString());
                return pstmt.executeUpdate();
            }

        }
        catch (Exception e)
        {
            Log4jHelper.exception(e);
            return 0;
        }
    }

    /**
     * 功能：检查注册用户是否已经注册
     * 
     * @param con
     * @param stuNo
     *            员工工号
     * @return 不存在 返回false 存在返回true
     * @throws SQLException
     */
    public static boolean IsExistence(Connection con, String empNo)
        throws SQLException
    {
        String sql = "SELECT * FROM pms.t_employee WHERE t_employee.EMP_NO=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, empNo);
        Log4jHelper.info("检查员工号：" + pstmt.toString());
        ResultSet rs = pstmt.executeQuery();
        if (rs.next())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Author:Taowd 功能：检查是否入职 t_induction表中进行检查 开发日期：2017-4-25-下午1:26:26
     * 
     * @param con
     * @param string
     * @return
     * @throws SQLException
     */
    public static boolean IsInduction(Connection con, String emp_no)
        throws SQLException
    {
        // 检查：入职申请已审批通过，不允许删除
        String sql = "SELECT * FROM t_inductioninfo WHERE EMP_NO =? and EXT3='IN' and EXT1='11'";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, emp_no);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static boolean IsLeader(Connection con, String emp_no)
        throws SQLException
    {
        // 检查：该用户是不是部门领导
        String sql = "SELECT * FROM t_department WHERE DEP_LEADER =?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, emp_no);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Author:Taowd 功能：获取普通员工个人信息 开发日期：2017-5-7-下午8:09:40
     * 
     * @param con
     * @param pageBean
     * @param emp
     * @param object
     * @param object2
     * @return
     * @throws SQLException
     */
    public static ResultSet EmployeetPersionInfo(Connection con, PageBean pageBean, Employee emp)
        throws SQLException
    {
        StringBuffer sb = new StringBuffer(
            "SELECT  emp.EMP_NO,EMP_PWD,EMP_NAME,emp.emp_sex, (case WHEN emp_sex='F'then '女' WHEN emp_sex='M'then '男' END ) as sexName,emp.EMP_Birthday,zzmm.PS_TYPE,zzmm.PS_Name,EMP_Phone, emp.EMP_Address, emp.ext1,emp.ext2 FROM pms.t_politicalstatus zzmm, pms.t_employee emp WHERE zzmm.PS_TYPE = emp.PS_ID ");

        if (emp != null && StringUtil.isNotEmpty(emp.getEmp_no()))
        {
            sb.append("AND emp.emp_no =" + emp.getEmp_no() + "");
        }

        Log4jHelper.info("查询员工个人信息：" + sb.toString());
        PreparedStatement pstmt = con.prepareStatement(sb.toString());
        return pstmt.executeQuery();
    }

}
