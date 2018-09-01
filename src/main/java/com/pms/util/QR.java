package com.pms.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

public class QR<T> {

	private DataSource dataSource;

	public QR() {
		super();
	}

	public QR(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * insert，update,delete操作.
	 * @param sql
	 * @param params
	 * @return
	 */
	public int update(String sql, Object... params) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(sql);
			initParams(pstmt, params);
			return pstmt.executeUpdate();

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {

			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	/**
	 * Author:Taowd 功能：给参数赋值 开发日期：2017-5-4-下午1:26:42
	 * @param pstmt
	 * @param params
	 * @throws SQLException
	 */
	private void initParams(PreparedStatement pstmt, Object[] params) throws SQLException {
		for (int i = 0; i < params.length; i++) {
			pstmt.setObject(i + 1, params[i]);
		}

	}

	public T query(String sql, RsHandler<T> rh, Object... params) {
		return null;
	}
}

/**
 * @author Taowd 功 能：用于将结果结果集转成相应的对象 编写时间：2017-5-4-下午1:17:55
 * @param <T>
 */
interface RsHandler<T> {
	public T handler(ResultSet rs) throws SQLException;
}
