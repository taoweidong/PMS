package com.pms.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.pms.model.PageBean;
import com.pms.model.PositionsInfoBean;
import com.pms.util.DateUtil;
import com.pms.util.Log4jHelper;
import com.pms.util.StringUtil;


/**
 * @author Taowd 功 能：岗位信息处理类 编写时间：2017-4-8-上午10:41:38
 */
public class PositionsInfoDao
{

    /**
     * 功能：岗位信息查询列表
     * 
     * @param con
     * @param pageBean
     * @param grade
     * @return
     * @throws Exception
     */
    public static ResultSet PositionsInfoList(Connection con, PageBean pageBean,
                                              PositionsInfoBean grade)
        throws Exception
    {
        Log4jHelper.info("查询数据：" + grade.toString());
        StringBuffer sb = new StringBuffer(
            "SELECT pi.POS_ID,pi.POS_NAME,dep.DEP_ID,DEP_NAME,dep.DEP_LEADER,pi.POS_CONTENT,pi.POS_SALARY,pi.POS_ALLOWANCE,pi.POS_PERQUISITES,pi.EXT1,pi.EXT2,pi.EXT3 FROM pms.t_positionsinfo pi,pms.t_department dep WHERE pi.DEP_ID = dep.DEP_ID ");
        if (grade != null && StringUtil.isNotEmpty(grade.getPos_Name()))
        {
            sb.append("and pi.POS_NAME like '%" + grade.getPos_Name() + "%'");
        }
        if (grade != null && StringUtil.isNotEmpty(grade.getDep_Id()))
        {
            sb.append("and dep.DEP_ID like '%" + grade.getDep_Id() + "%'");
        }
        if (pageBean != null)
        {
            sb.append(" limit " + pageBean.getStart() + "," + pageBean.getRows());
        }
        PreparedStatement pstmt = con.prepareStatement(sb.toString());

        Log4jHelper.info("查询岗位信息列表" + pstmt.toString());
        return pstmt.executeQuery();
    }

    /**
     * Author:Taowd 功能：取得总记录数 开发日期：2017-4-8-上午10:43:52
     * 
     * @param con
     * @param grade
     * @return
     * @throws Exception
     */
    public static int PositionsInfoCount(Connection con, PositionsInfoBean grade)
        throws Exception
    {
        StringBuffer sb = new StringBuffer(
            "SELECT count(*) as total FROM pms.t_positionsinfo pi,pms.t_department dep WHERE pi.DEP_ID = dep.DEP_ID");
        if (StringUtil.isNotEmpty(grade.getPos_Name()))
        {
            sb.append(" and pi.POS_NAME like '%" + grade.getPos_Name() + "%'");
        }
        PreparedStatement pstmt = con.prepareStatement(sb.toString());

        Log4jHelper.info("查询岗位信息列表，获取总记录：" + pstmt.toString());
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
     * Author:Taowd 功能：删除岗位信息 开发日期：2017-4-8-上午10:44:22
     * 
     * @param con
     * @param delIds
     * @return
     * @throws Exception
     */
    public static int PositionsInfoDelete(Connection con, String delIds)
        throws Exception
    {
        String sql = "delete from pms.t_positionsinfo where t_positionsinfo.POS_ID in(" + delIds
                     + ")";

        Log4jHelper.info("删除岗位信息" + sql);
        PreparedStatement pstmt = con.prepareStatement(sql);
        return pstmt.executeUpdate();
    }

    /**
     * 功能：查询该岗位的的员工信息
     * 
     * @param con
     * @param gradeId
     * @return
     * @throws Exception
     */
    public static boolean getEmpByPosId(Connection con, String pos_Id)
        throws Exception
    {
        Log4jHelper.info("待检查的岗位编号：" + pos_Id);
        String sql = "SELECT * FROM pms.t_inductioninfo WHERE t_inductioninfo.POS_ID=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, pos_Id);
        Log4jHelper.info("检查岗位下是否有员工信息：" + pstmt.toString());
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
     * Author:Taowd 功能：增加岗位信息 开发日期：2017-4-8-上午10:44:39
     * 
     * @param con
     * @param grade
     * @return
     * @throws Exception
     */
    public static int PositionsInfoAdd(Connection con, PositionsInfoBean grade)
        throws Exception
    {
        String sql = "INSERT INTO pms.t_positionsinfo VALUES(?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, grade.getPos_Id());
        pstmt.setString(2, grade.getDep_Id());
        pstmt.setString(3, grade.getPos_Name());
        pstmt.setString(4, grade.getPos_Content());
        pstmt.setDouble(5, grade.getPos_Salary());
        pstmt.setDouble(6, grade.getPos_Allowance());
        pstmt.setDouble(7, grade.getPos_Perquisites());
        pstmt.setString(8, grade.getExt1());
        pstmt.setString(9, DateUtil.getCurrentDateStr());
        pstmt.setString(10, grade.getExt3());
        Log4jHelper.info("新增岗位信息：" + pstmt.toString());
        return pstmt.executeUpdate();
    }

    /**
     * Author:Taowd 功能：修改岗位信息 开发日期：2017-4-8-上午10:54:27
     * 
     * @param con
     * @param grade
     * @return
     * @throws Exception
     */
    public static int PositionsInfoModify(Connection con, PositionsInfoBean grade)
        throws Exception
    {
        String sql = "UPDATE pms.t_positionsinfo SET t_positionsinfo.DEP_ID=?,t_positionsinfo.POS_NAME=?,t_positionsinfo.POS_CONTENT=?,t_positionsinfo.POS_SALARY=?,t_positionsinfo.POS_ALLOWANCE=?,t_positionsinfo.POS_PERQUISITES=?,t_positionsinfo.EXT1=?,t_positionsinfo.EXT2=?,t_positionsinfo.EXT3=? WHERE t_positionsinfo.POS_ID=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(10, grade.getPos_Id());
        pstmt.setString(1, grade.getDep_Id());
        pstmt.setString(2, grade.getPos_Name());
        pstmt.setString(3, grade.getPos_Content());
        pstmt.setDouble(4, grade.getPos_Salary());
        pstmt.setDouble(5, grade.getPos_Allowance());
        pstmt.setDouble(6, grade.getPos_Perquisites());
        pstmt.setString(7, grade.getExt1());
        pstmt.setString(8, DateUtil.getCurrentDateStr());
        pstmt.setString(9, grade.getExt3());

        Log4jHelper.info("修改岗位信息：" + pstmt.toString());
        return pstmt.executeUpdate();
    }

    /**
     * Author:Taowd 功能：检查岗位是否存在 开发日期：2017-4-9-下午12:04:22
     * 
     * @param con
     * @param pos_Id
     * @return
     * @throws SQLException
     */
    public static boolean IsExistence(Connection con, String pos_Id)
        throws SQLException
    {
        String sql = "SELECT * FROM pms.t_positionsinfo WHERE t_positionsinfo.POS_ID = ? ";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, pos_Id);
        Log4jHelper.info("检查岗位是否存在：" + pstmt.toString());
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
    private PositionsInfoDao()
    {}
}
