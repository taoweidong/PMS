package com.pms.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import javax.sql.DataSource;

import org.apache.commons.dbutils.ResultSetHandler;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 数据库连接的工具类
 * @author Taowd
 * @version 2018年8月31日
 * @see DbUtils
 */
public class DbUtils {

	/**
	 * 获取一个ResultSetHandler对象返回结果集为ResultSet
	 */
	public static final ResultSetHandler<ResultSet> GetRSH = new ResultSetHandler<ResultSet>() {
		public ResultSet handle(ResultSet rs) throws SQLException {
			return rs;
		}
	};

	/**
	 * 使用默认配置获取一个数据库连接对象
	 */
	private static final ComboPooledDataSource dataSource = new ComboPooledDataSource(
			"mysql-config");

	/**
	 * Author:Taowd 功能：获取一个数据库连接对象 开发日期：2017-5-4-下午12:44:15
	 * @return Connection
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}

	/**
	 * Author:Taowd 功能：关闭数据库连接 开发日期：2017-5-4-下午12:52:36
	 * @param conn
	 *                 Connection
	 * @throws SQLException
	 */
	public static void CloseConn(Connection conn) throws SQLException {
		if (conn != null) {
			conn.close();
		}
	}

	/**
	 * Author:Taowd 功能：返回一个连接池对象 开发日期：2017-5-4-下午12:48:36
	 * @return
	 */

	public static DataSource getDataSource() {
		return dataSource;
	}

	/**
	 * Author:Taowd 功能：生成一个UUID 开发日期：2017-4-19-下午8:20:01
	 * @return
	 * @throws Exception
	 */
	public static String GetUUID() throws Exception {
		return UUID.randomUUID().toString().replace("-", "");
	}

	/**
	 * Author:Taowd 功能：测试数据库连接 开发日期：2017-5-4-下午12:53:53
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			DbUtils.getConnection();
			System.out.println("数据库连接成功！");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/** Prevent instantiation */
	private DbUtils() {
	}

}
