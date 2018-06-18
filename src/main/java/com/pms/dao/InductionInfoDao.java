package com.pms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.lang.StringUtils;

import com.pms.model.InductionInfoBean;
import com.pms.model.PageBean;
import com.pms.util.DateUtil;
import com.pms.util.DbUtils;
import com.pms.util.Log4jHelper;
import com.pms.util.StringUtil;

/**
 * 
 * @author Taowd
 * 功        能：入职信息
 * 编写时间：2017-4-11-下午8:38:05
 */
public class InductionInfoDao {
	/**
	 * 
	 * Author:Taowd
	 * 功能：查询所有员工的入职信息--管理员权限才可以--即入职审批时的界面
	 * 开发日期：2017-4-11-下午8:39:05
	 * @param con
	 * @param pageBean
	 * @param demp
	 * @return
	 * @throws Exception
	 */
	public static ResultSet InductionInfoList(Connection con,
			PageBean pageBean, InductionInfoBean demp, String startDate,
			String endDate) throws Exception {
		StringBuffer sb = new StringBuffer(
				"SELECT ii.IND_ID,emp.EMP_NO,emp.EMP_NAME,pos.POS_ID,pos.POS_NAME,ii.IND_DATE,ii.IND_STATE AS stateCode,(case WHEN ii.IND_STATE='1'then '入职' WHEN ii.IND_STATE='0'then '离职' END ) as stateName ,ii.IND_ENDDATE,ii.IND_Reasons,ii.EXT1 as approveState,(case WHEN ii.EXT1='11'then '已审批通过' WHEN ii.EXT1='00'then '申请中'  WHEN ii.EXT1='22'then '审批未通过' WHEN ii.EXT1='33'then '未提交' END ) as approveName ,ii.EXT2,ii.EXT3,(case WHEN ii.EXT3='IN'then '入职申请' WHEN ii.EXT3='OUT'then '离职申请' END ) as typeName  FROM pms.t_inductioninfo ii,pms.t_employee emp,pms.t_positionsinfo pos WHERE ii.EMP_NO=emp.EMP_NO and ii.POS_ID=pos.POS_ID ");
		if (demp != null && StringUtil.isNotEmpty(demp.getInd_Id())) {
			sb.append(" and ii.IND_ID like '%" + demp.getInd_Id() + "%'");
		}
		// 员工号
		if (demp != null && StringUtil.isNotEmpty(demp.getEmp_No())) {
			sb.append(" and emp.EMP_NO like '%" + demp.getEmp_No() + "%'");
		}
		// 审批状态
		if (demp != null && StringUtil.isNotEmpty(demp.getExt1())) {
			sb.append(" and ii.EXT1 like '%" + demp.getExt1() + "%'");
		}
		// 在职状态
		if (demp != null && StringUtil.isNotEmpty(demp.getInd_State())) {
			sb.append(" and ii.IND_STATE like '%" + demp.getInd_State() + "%'");
		}
		// 岗位编号
		if (demp != null && StringUtil.isNotEmpty(demp.getPos_Name())) {
			sb.append(" and pos.POS_NAME like '%" + demp.getPos_Name() + "%'");
		}
		// 申请日期
		if (StringUtil.isNotEmpty(startDate)) {
			sb.append(" and TO_DAYS(ii.IND_DATE) >= TO_DAYS('" + startDate
					+ "')");
		}
		if (StringUtil.isNotEmpty(endDate)) {
			sb.append(" and TO_DAYS(ii.IND_DATE) <= TO_DAYS('" + endDate + "')");
		}

		if (pageBean != null) {
			sb.append(" limit " + pageBean.getStart() + ","
					+ pageBean.getRows());
		}

		PreparedStatement pstmt = con.prepareStatement(sb.toString());

		Log4jHelper.info("查询员工的入职信息：" + pstmt.toString());
		return pstmt.executeQuery();
	}

	/**
	 * 
	 * Author:Taowd
	 * 功能：个人申请职位调用的方法
	 * 开发日期：2017-4-13-下午9:05:21
	 * @param con
	 * @param induction
	 * @return
	 * @throws Exception 
	 */
	public static int ApplyInduction(InductionInfoBean induction)
			throws Exception {
		// 职位申请:
		// 生成全库唯一的UUID upper(replace(uuid(),'-',''))
		// 创建QueryRunner，需要提供数据库连接池对象
		QueryRunner qr = new QueryRunner(DbUtils.getDataSource());
		// 给出sql模板
		String sql = "INSERT INTO t_inductioninfo() VALUES(upper(replace(uuid(),'-','')),?,?,?,?,?,?,?,?,?) ";
		// 给出参数
		Object[] par = { induction.getEmp_No(), induction.getPos_Id(),
				DateUtil.getCurrentDateStr(), induction.getInd_State(),
				induction.getInd_Enddate(), induction.getInd_Reasons(),
				induction.getExt1(), induction.getExt2(), induction.getExt3() };
		// 执行Sql语句获取返回值
		return qr.update(sql, par);
	}

	/**
	 * 
	 * Author:Taowd
	 * 功能：获取信息总数
	 * 开发日期：2017-4-13-上午8:18:42
	 * @param con
	 * @param inductionBean
	 * @return
	 * @throws SQLException 
	 */
	public static int InductionInfoCount(Connection con,
			InductionInfoBean inductionBean) throws SQLException {
		Log4jHelper.info("参数：InductionInfoBean" + inductionBean.toString());
		StringBuffer sb = new StringBuffer(
				"SELECT count(*) as total FROM t_inductioninfo ii,t_employee emp,t_positionsinfo pos WHERE ii.EMP_NO=emp.EMP_NO and ii.POS_ID=pos.POS_ID ");
		if (inductionBean != null
				&& StringUtil.isNotEmpty(inductionBean.getInd_Id())) {
			sb.append(" and ii.IND_ID like '%" + inductionBean.getInd_Id()
					+ "%'");
		}
		if (inductionBean != null
				&& StringUtil.isNotEmpty(inductionBean.getEmp_No())) {
			sb.append(" and emp.EMP_NO like '%" + inductionBean.getEmp_No()
					+ "%'");
		}
		if (inductionBean != null
				&& StringUtil.isNotEmpty(inductionBean.getPos_Id())) {
			sb.append(" and pos.POS_ID like '%" + inductionBean.getPos_Id()
					+ "%'");
		}
		Log4jHelper.info(sb.toString());
		PreparedStatement pstmt = con.prepareStatement(sb.toString());
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			return rs.getInt("total");
		} else {
			return 0;
		}
	}

	/**
	 * 
	 * Author:Taowd
	 * 功能：检查该部门是否已经申请过了，如果申请过了，就不允许再次申请
	 * 开发日期：2017-4-14-下午1:18:28
	 * @param con
	 * @param pos_Id
	 * @return  true-已经申请过了  false-没有申请过
	 * @throws SQLException 
	 */
	public static boolean IsExistence(InductionInfoBean inductionBean)
			throws SQLException {
		// 创建QueryRunner，需要提供数据库连接池对象
		QueryRunner qr = new QueryRunner(DbUtils.getDataSource());
		// 给出sql模板
		String sql = "SELECT * FROM t_inductioninfo WHERE t_inductioninfo.EMP_NO=? AND t_inductioninfo.POS_ID=? AND t_inductioninfo.EXT3=?";
		// 给出参数
		Object[] par = { inductionBean.getEmp_No(), inductionBean.getPos_Id(),
				inductionBean.getExt3() };
		// 执行Sql语句获取返回值
		InductionInfoBean admin = qr.query(sql,
				new BeanHandler<InductionInfoBean>(InductionInfoBean.class),
				par);
		if (admin != null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * Author:Taowd
	 * 功能：修改已经申请，但是没有提交的信息
	 * 开发日期：2017-4-14-下午1:23:49
	 * @param con
	 * @param induction
	 * @return
	 * @throws SQLException 
	 */
	public static int ApplyInductionModify(InductionInfoBean induction)
			throws SQLException {
		// 创建QueryRunner，需要提供数据库连接池对象
		QueryRunner queryRun = new QueryRunner(DbUtils.getDataSource());
		// 给出sql模板
		String sql = "UPDATE t_inductioninfo SET t_inductioninfo.POS_ID=?  WHERE t_inductioninfo.IND_ID=? ";
		// 给出参数
		Object[] params = { induction.getPos_Id(), induction.getInd_Id() };
		// 执行Sql语句获取返回值
		return queryRun.update(sql, params);
	}

	/**
	 * 
	 * Author:Taowd
	 * 功能：提交申请的逻辑
	 * 开发日期：2017-4-14-下午8:25:31
	 * @param con
	 * @param induction
	 * @return
	 * @throws SQLException
	 */
	public static int ApplyInductionApprove(InductionInfoBean induction)
			throws SQLException {
		Log4jHelper.info("待审批信息：" + induction.toString());

		// 创建QueryRunner，需要提供数据库连接池对象
		QueryRunner qr = new QueryRunner(DbUtils.getDataSource());
		// 给出sql模板
		String sql1 = "select EXT3 from t_inductioninfo where ind_id =? ";
		// 给出参数
		Object[] par = { induction.getInd_Id() };
		// 执行Sql语句获取返回值
		InductionInfoBean admin = qr.query(sql1,
				new BeanHandler<InductionInfoBean>(InductionInfoBean.class),
				par);
		if (admin != null) {
			if ("11".equals(induction.getExt1()))// 审批入职通过
			{
				if (!StringUtils.isEmpty(admin.getExt3())
						&& "IN".equals(admin.getExt3())) {
					// 入职
					induction.setInd_State("1");
				} else {
					// 离职
					induction.setInd_State("0");
				}
			} else if ("22".equals(induction.getExt1()))// 审批不通过
			{
				induction.setInd_State("");
			}

			// 创建QueryRunner，需要提供数据库连接池对象
			QueryRunner queryRun = new QueryRunner(DbUtils.getDataSource());
			// 给出sql模板
			String sql = "UPDATE t_inductioninfo SET t_inductioninfo.EXT1=?,t_inductioninfo.EXT2=?,t_inductioninfo.IND_STATE=? WHERE t_inductioninfo.IND_ID=? ";
			// 给出参数
			Object[] params = { induction.getExt1(), induction.getExt2(),
					induction.getInd_State(), induction.getInd_Id() };
			// 执行Sql语句获取返回值
			return queryRun.update(sql, params);
		} else {
			return 0;
		}

	}

	/**
	 * 
	 * Author:Taowd
	 * 功能：检查申请状态是否可修改：即ext1=="33"??
	 * 开发日期：2017-4-14-下午1:58:58
	 * @param con
	 * @param induction
	 * @return
	 * @throws SQLException 
	 */
	public boolean CheckApproveState(Connection con, InductionInfoBean induction)
			throws SQLException {
		String sql = "SELECT * FROM t_inductioninfo WHERE t_inductioninfo.IND_ID=? and t_inductioninfo.EXT1 ='33' ";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, induction.getInd_Id());
		Log4jHelper.info("检查部门是否已经提交：" + pstmt.toString());
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * Author:Taowd
	 * 功能：检查当前状态
	 * 开发日期：2017-4-14-下午2:29:09
	 * @param con
	 * @param string
	 * @return
	 * @throws SQLException 
	 */
	public static boolean CheckApproveState(String IND_ID) throws SQLException {
		// 创建QueryRunner，需要提供数据库连接池对象
		QueryRunner queryRun = new QueryRunner(DbUtils.getDataSource());
		// 给出sql模板
		String sql = "SELECT * FROM t_inductioninfo WHERE t_inductioninfo.IND_ID=? and t_inductioninfo.EXT1 ='33' ";
		// 给出参数
		Object[] params = { IND_ID };
		// 执行Sql语句获取返回值
		InductionInfoBean admin = queryRun.query(sql,
				new BeanHandler<InductionInfoBean>(InductionInfoBean.class),
				params);
		if (admin != null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * Author:Taowd
	 * 功能：删除申请
	 * 开发日期：2017-4-14-下午2:31:26
	 * @param con
	 * @param delIds
	 * @return
	 * @throws SQLException 
	 */
	public static int InductionDelete(String delIds) throws SQLException {
		// 创建QueryRunner，需要提供数据库连接池对象
		QueryRunner queryRun = new QueryRunner(DbUtils.getDataSource());
		// 给出sql模板
		String sql = "delete from t_inductioninfo where t_inductioninfo.IND_ID in ( "
				+ delIds + ")";
		// 执行Sql语句获取返回值
		return queryRun.update(sql);
	}

	/**
	 * 
	 * Author:Taowd
	 * 功能：检查是否为可审批状态--即ext1=="00"
	 * 开发日期：2017-4-17-下午1:05:04
	 * @param con
	 * @param string
	 * @return
	 * @throws SQLException 
	 */
	public static boolean IsApproveState(String IND_ID) throws SQLException {
		// 创建QueryRunner，需要提供数据库连接池对象
		QueryRunner queryRun = new QueryRunner(DbUtils.getDataSource());
		// 给出sql模板
		String sql = "SELECT * FROM t_inductioninfo WHERE t_inductioninfo.IND_ID=? and t_inductioninfo.EXT1 ='00' ";
		// 给出参数
		Object[] params = { IND_ID };
		// 执行Sql语句获取返回值
		InductionInfoBean admin = queryRun.query(sql,
				new BeanHandler<InductionInfoBean>(InductionInfoBean.class),
				params);
		if (admin != null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * Author:Taowd
	 * 功能：管理员查询申请信息--特殊处理，排除未提交的申请
	 * 开发日期：2017-4-17-下午1:36:37
	 * @param con
	 * @param pageBean
	 * @param inductionBean
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws SQLException 
	 */
	public static ResultSet AdminInductionInfoList(Connection con,
			PageBean pageBean, InductionInfoBean demp, String startDate,
			String endDate) throws SQLException {
		Log4jHelper.info("查询参数：" + demp.toString());
		StringBuffer sb = new StringBuffer(
				"SELECT ii.IND_ID,emp.EMP_NO,emp.EMP_NAME,pos.POS_ID,pos.POS_NAME,ii.IND_DATE,ii.IND_STATE AS stateCode,(case WHEN ii.IND_STATE='1'then '入职' WHEN ii.IND_STATE='0'then '离职' END ) as stateName ,ii.IND_ENDDATE,ii.IND_Reasons,ii.EXT1 as approveState,(case WHEN ii.EXT1='11'then '已审批通过' WHEN ii.EXT1='00'then '申请中'  WHEN ii.EXT1='22'then '审批未通过' WHEN ii.EXT1='33'then '未提交' END ) as approveName ,ii.EXT2,ii.EXT3,(case WHEN ii.EXT3='IN'then '入职申请' WHEN ii.EXT3='OUT'then '离职申请' END ) as typeName  FROM t_inductioninfo ii,t_employee emp,t_positionsinfo pos WHERE ii.EMP_NO=emp.EMP_NO and ii.POS_ID=pos.POS_ID AND ii.EXT1!='33' ");
		if (demp != null && StringUtil.isNotEmpty(demp.getInd_Id())) {
			sb.append(" and ii.IND_ID like '%" + demp.getInd_Id() + "%'");
		}
		// 员工号
		if (demp != null && StringUtil.isNotEmpty(demp.getEmp_No())) {
			sb.append(" and emp.EMP_NO like '%" + demp.getEmp_No() + "%'");
		}
		// 审批状态
		if (demp != null && StringUtil.isNotEmpty(demp.getExt1())) {
			sb.append(" and ii.EXT1 like '%" + demp.getExt1() + "%'");
		}
		// 在职状态
		if (demp != null && StringUtil.isNotEmpty(demp.getInd_State())) {
			sb.append(" and ii.IND_STATE like '%" + demp.getInd_State() + "%'");
		}
		// 岗位编号
		if (demp != null && StringUtil.isNotEmpty(demp.getPos_Name())) {
			sb.append(" and pos.POS_NAME like '%" + demp.getPos_Name() + "%'");
		}
		// 申请日期
		if (StringUtil.isNotEmpty(startDate)) {
			sb.append(" and TO_DAYS(ii.IND_DATE) >= TO_DAYS('" + startDate
					+ "')");
		}
		if (StringUtil.isNotEmpty(endDate)) {
			sb.append(" and TO_DAYS(ii.IND_DATE) <= TO_DAYS('" + endDate + "')");
		}

		if (pageBean != null) {
			sb.append(" limit " + pageBean.getStart() + ","
					+ pageBean.getRows());
		}

		PreparedStatement pstmt = con.prepareStatement(sb.toString());

		Log4jHelper.info("查询员工的入职信息：" + pstmt.toString());
		return pstmt.executeQuery();
	}

}
