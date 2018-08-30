package com.pms.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.pms.model.PageBean;
import com.pms.model.PoliticalStatusBean;
import com.pms.util.DateUtil;
import com.pms.util.Log4jHelper;
import com.pms.util.StringUtil;


public class PoliticalStatusDao
{

    /**
     * 功能：政治面貌查询列表
     * 
     * @param con
     * @param pageBean
     * @param grade
     * @return
     * @throws Exception
     */
    public static ResultSet politicalStatusList(Connection con, PageBean pageBean,
                                                PoliticalStatusBean ps)
        throws Exception
    {
        StringBuffer sb = new StringBuffer("SELECT * FROM pms.t_politicalstatus");
        if (ps != null && StringUtil.isNotEmpty(ps.getPs_type()))
        {
            sb.append(" and t_politicalstatus.PS_TYPE like '%" + ps.getPs_type() + "%'");
        }
        if (ps != null && StringUtil.isNotEmpty(ps.getPs_name()))
        {
            sb.append(" and t_politicalstatus.PS_Name like '%" + ps.getPs_name() + "%'");
        }
        if (pageBean != null)
        {
            sb.append(" limit " + pageBean.getStart() + "," + pageBean.getRows());
        }

        PreparedStatement pstmt = con.prepareStatement(sb.toString().replaceFirst("and", "where"));

        Log4jHelper.info("查询政治面貌：" + pstmt.toString());
        return pstmt.executeQuery();
    }

    /**
     * 功能：获取总记录数
     * 
     * @param con
     * @param grade
     * @return
     * @throws Exception
     */
    public static int PoliticalStatusCount(Connection con, PoliticalStatusBean ps)
        throws Exception
    {
        StringBuffer sb = new StringBuffer("select count(*) as total from pms.t_politicalstatus");
        if (ps != null && StringUtil.isNotEmpty(ps.getPs_type()))
        {
            sb.append(" and t_politicalstatus.PS_TYPE like '%" + ps.getPs_type() + "%'");
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
     * 功能：删除政治面貌记录
     * 
     * @param con
     * @param delIds
     * @return
     * @throws Exception
     */
    public static int PoliticalStatusDelete(Connection con, String delIds)
        throws Exception
    {
        String sql = "delete from pms.t_politicalstatus where t_politicalstatus.PS_TYPE in("
                     + delIds + ")";
        PreparedStatement pstmt = con.prepareStatement(sql);
        Log4jHelper.info("删除政治面貌记录：" + pstmt.toString());
        return pstmt.executeUpdate();
    }

    /**
     * 功能：新增一个政治面貌类型
     * 
     * @param con
     * @param grade
     * @return
     * @throws Exception
     */
    public static int PoliticalStatusAdd(Connection con, PoliticalStatusBean ps)
        throws Exception
    {
        String sql = "INSERT INTO pms.t_politicalstatus VALUES(?,?,?,?,?)";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, ps.getPs_type());
        pstmt.setString(2, ps.getPs_name());
        // 备用字段：此处当做新增时间处理
        pstmt.setString(3, DateUtil.getCurrentDateStr());
        pstmt.setString(4, ps.getExt2());
        pstmt.setString(5, ps.getExt3());

        Log4jHelper.info("新增一个政治面貌类型" + pstmt.toString());
        return pstmt.executeUpdate();
    }

    /**
     * 功能：修改政治面貌类型
     * 
     * @param con
     * @param grade
     * @return
     * @throws Exception
     */
    public static int PoliticalStatusModify(Connection con, PoliticalStatusBean ps)
        throws Exception
    {
        String sql = "UPDATE pms.t_politicalstatus SET t_politicalstatus.PS_Name=?,t_politicalstatus.Ext1=?,t_politicalstatus.Ext2=?,t_politicalstatus.Ext3=? WHERE t_politicalstatus.PS_TYPE=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(5, ps.getPs_type());
        pstmt.setString(1, ps.getPs_name());
        pstmt.setString(2, DateUtil.getCurrentDateStr());
        pstmt.setString(3, ps.getExt2());
        pstmt.setString(4, ps.getExt3());
        Log4jHelper.info("修改政治面貌类型" + pstmt.toString());
        return pstmt.executeUpdate();
    }

    /**
     * Author:Taowd 功能：判断该类型编号的是否存在 开发日期：2017-4-9-上午11:01:46
     * 
     * @param con
     * @param psb
     * @return
     * @throws SQLException
     */
    public static boolean IsExistence(Connection con, String ps_Type)
        throws SQLException
    {
        String sql = "SELECT * FROM pms.t_politicalstatus WHERE t_politicalstatus.PS_TYPE =?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, ps_Type);
        Log4jHelper.info("检查类型编号：" + pstmt.toString());
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
     * Author:Taowd 功能：查询该角色下的员工信息 开发日期：2017-4-10-下午1:36:10
     * 
     * @param con
     * @param string
     * @return
     * @throws SQLException
     */
    public static boolean getEmpByPsType(Connection con, String psType)
        throws SQLException
    {
        String sql = "SELECT * FROM pms.t_employee WHERE PS_ID =?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, psType);
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
    private PoliticalStatusDao()
    {}
}
