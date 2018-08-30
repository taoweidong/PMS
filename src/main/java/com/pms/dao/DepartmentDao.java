package com.pms.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.pms.model.DepartmentBean;
import com.pms.model.PageBean;
import com.pms.util.Log4jHelper;
import com.pms.util.StringUtil;


/**
 * 部门表
 * 
 * @author Taowd
 * @version 2018年8月30日
 * @see DepartmentDao
 */
public class DepartmentDao
{

    /**
     * Author:Taowd 功能：查询部门信息 开发日期：2017-4-8-下午5:46:53
     * 
     * @param con
     * @param pageBean
     * @param demp
     * @return
     * @throws Exception
     */
    public static ResultSet departmentList(Connection con, PageBean pageBean, DepartmentBean demp)
        throws Exception
    {
        StringBuffer sb = new StringBuffer(
            "SELECT dep.DEP_ID,dep.DEP_NAME,DEP_LEADER,emp.EMP_NAME,emp.EMP_Phone FROM pms.t_department dep LEFT JOIN pms.t_employee emp ON dep.DEP_LEADER = emp.EMP_NO ");
        if (demp != null && StringUtil.isNotEmpty(demp.getDep_Id()))
        {
            sb.append(" and dep.DEP_ID like '%" + demp.getDep_Id() + "%'");
        }
        if (demp != null && StringUtil.isNotEmpty(demp.getDep_Name()))
        {
            sb.append(" and dep.DEP_NAME like '%" + demp.getDep_Name() + "%'");
        }
        if (demp != null && StringUtil.isNotEmpty(demp.getDep_Leader()))
        {
            sb.append(" and emp.EMP_NAME like '%" + demp.getDep_Leader() + "%'");
        }
        if (pageBean != null)
        {
            sb.append(" limit " + pageBean.getStart() + "," + pageBean.getRows());
        }

        PreparedStatement pstmt = con.prepareStatement(sb.toString().replaceFirst("and", "where"));

        Log4jHelper.info("查询部门信息：" + pstmt.toString());
        return pstmt.executeQuery();
    }

    /**
     * Author:Taowd 功能：查询部门信息总记录 开发日期：2017-4-8-下午5:47:07
     * 
     * @param con
     * @param demp
     * @return
     * @throws Exception
     */
    public static int departmentCount(Connection con, DepartmentBean demp)
        throws Exception
    {
        StringBuffer sb = new StringBuffer("select count(*) as total from pms.t_department ");
        if (demp != null && StringUtil.isNotEmpty(demp.getDep_Id()))
        {
            sb.append(" and t_department.DEP_ID like '%" + demp.getDep_Id() + "%'");
        }
        if (demp != null && StringUtil.isNotEmpty(demp.getDep_Name()))
        {
            sb.append(" and t_department.DEP_NAME like '%" + demp.getDep_Name() + "%'");
        }
        if (demp != null && StringUtil.isNotEmpty(demp.getDep_Leader()))
        {
            sb.append(" and t_department.DEP_LEADER like '%" + demp.getDep_Leader() + "%'");
        }
        PreparedStatement pstmt = con.prepareStatement(sb.toString().replaceFirst("and", "where"));

        Log4jHelper.info("获取总记录数：" + pstmt.toString());
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
     * Author:Taowd 功能：删除部门信息 开发日期：2017-4-10-下午8:19:25
     * 
     * @param con
     * @param delIds
     * @return
     * @throws SQLException
     */
    public static int departmentDelete(Connection con, String delIds)
        throws SQLException
    {
        String sql = "DELETE FROM pms.t_department WHERE t_department.DEP_ID IN(" + delIds + ")";
        PreparedStatement pstmt = con.prepareStatement(sql);

        Log4jHelper.info("删除部门信息：" + pstmt.toString());
        return pstmt.executeUpdate();
    }

    /**
     * Author:Taowd 功能：部门是否已经存在 开发日期：2017-4-10-下午9:16:43
     * 
     * @param con
     * @param ps_Type
     * @return
     * @throws SQLException
     */
    public static boolean isExistence(Connection con, String ps_Type)
        throws SQLException
    {
        String sql = "SELECT * FROM pms.t_department WHERE t_department.DEP_ID=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, ps_Type);
        Log4jHelper.info("检查部门是否已经存在：" + pstmt.toString());
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
     * Author:Taowd 功能：修改部门信息 开发日期：2017-4-10-下午9:17:39
     * 
     * @param con
     * @param db
     * @return
     * @throws SQLException
     */
    public static int departmentModify(Connection con, DepartmentBean db)
        throws SQLException
    {
        String sql = "UPDATE pms.t_department SET t_department.DEP_NAME=?,t_department.DEP_LEADER=? WHERE t_department.DEP_ID=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, db.getDep_Name());
        pstmt.setString(2, db.getDep_Leader());
        // 修改条件
        pstmt.setString(3, db.getDep_Id());
        // 打印执行的Sql语句
        Log4jHelper.info("修改的Sql语句：" + pstmt.toString());
        return pstmt.executeUpdate();
    }

    /**
     * Author:Taowd 功能：新增部门信息 开发日期：2017-4-10-下午9:21:16
     * 
     * @param con
     * @param db
     * @return
     * @throws SQLException
     */
    public static int departmentAdd(Connection con, DepartmentBean db)
        throws SQLException
    {
        String sql = "INSERT INTO pms.t_department() VALUES(?,?,?)";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(2, db.getDep_Name());
        pstmt.setString(3, db.getDep_Leader());
        // 修改条件
        pstmt.setString(1, db.getDep_Id());
        Log4jHelper.info("员工注册：" + pstmt.toString());
        return pstmt.executeUpdate();
    }

    /**
     * Author:Taowd 功能：查看该部门是否有岗位信息 开发日期：2017-4-19-下午9:04:59
     * 
     * @param con
     * @param string
     * @return
     * @throws SQLException
     */
    public static boolean getPosInfoByDepId(Connection con, String DepId)
        throws SQLException
    {
        Log4jHelper.info("该部门是否有岗位信息：" + DepId);
        String sql = "SELECT * FROM pms.t_positionsinfo WHERE t_positionsinfo.DEP_ID=? ";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, DepId);
        Log4jHelper.info("该部门是否有岗位信息：" + pstmt.toString());
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

    /** Prevent instantiation */
    private DepartmentDao()
    {}

}
