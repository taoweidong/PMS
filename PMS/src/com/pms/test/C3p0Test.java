package com.pms.test;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Test;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.pms.model.Administrator;
import com.pms.util.DbUtils;

public class C3p0Test {

	@Test
	public void fun1() throws PropertyVetoException, SQLException {
		// 创建数据库连接池对象
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		// 对池进行四大参数的配置
		dataSource.setDriverClass("com.mysql.jdbc.Driver");
		dataSource.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/PMS");
		dataSource.setUser("root");
		dataSource.setPassword("root");

		// 池配置
		dataSource.setAcquireIncrement(5);
		dataSource.setInitialPoolSize(20);
		dataSource.setMinPoolSize(2);
		dataSource.setMaxPoolSize(50);

		Connection con = dataSource.getConnection();
		System.out.println(con);
		con.close();

	}

	/**
	 * 
	 * Author:Taowd
	 * 功能：配置文件的默认配置
	 * 开发日期：2017-5-3-下午8:12:32
	 * @throws SQLException 
	 */
	@Test
	public void fun2() throws SQLException {
		// 创建数据库连接池对象
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		Connection con = dataSource.getConnection();
		System.out.println(con);
		con.close();
	}

	/**
	 * 
	 * Author:Taowd
	 * 功能：使用命名配置
	 * 开发日期：2017-5-4-下午12:37:30
	 * @throws SQLException
	 */
	@Test
	public void fun3() throws SQLException {
		// 创建数据库连接池对象
		ComboPooledDataSource dataSource = new ComboPooledDataSource(
				"oracle-config");
		Connection con = dataSource.getConnection();
		System.out.println(con);
		con.close();
	}

	@Test
	public void fun4() throws SQLException {
		Administrator admin = new Administrator("1", "asd", "123456", "测试",
				"15745456465", "asd", "1231", "asda");
		Update(admin);

	}

	/**
	 * 
	 * Author:Taowd
	 * 功能：测试Dbutils中的Query 返回为一个JavaBean对象  返回JavaBean
	 * 开发日期：2017-5-5-下午12:55:41
	 * @throws SQLException
	 */
	@Test
	public void fun5() throws SQLException {
		QueryRunner queryRun = new QueryRunner(DbUtils.getDataSource());
		String sql = "select * from t_administrator where ADMIN_ID=?";
		Object[] params = { "ww" };

		Administrator admin = queryRun.query(sql,
				new BeanHandler<Administrator>(Administrator.class), params);
		System.out.println(admin);

	}

	/**
	 * 
	 * Author:Taowd
	 * 功能：测试Dbutils返回多行javaBean的方法 List<JavaBean>
	 * 开发日期：2017-5-5-下午12:56:49
	 * @throws SQLException
	 */
	@Test
	public void fun6() throws SQLException {
		QueryRunner queryRun = new QueryRunner(DbUtils.getDataSource());
		String sql = "select * from t_administrator";

		List<Administrator> admin = queryRun.query(sql,
				new BeanListHandler<Administrator>(Administrator.class));
		System.out.println(admin);

	}

	/**
	 * 
	 * Author:Taowd
	 * 功能：测试新增--使用DbUtils工具类
	 * 开发日期：2017-5-4-下午1:32:22
	 * @throws SQLException 
	 */
	public void Update(Administrator admin) throws SQLException {

		QueryRunner queryRun = new QueryRunner(DbUtils.getDataSource());
		String sql = "INSERT INTO t_administrator VALUES(?,?,?,?,?,?,?,?) ";
		Object[] params = { admin.getAdmin_id(), admin.getAdmin_name(),
				admin.getAdmin_no(), admin.getAdmin_phone(),
				admin.getAdmin_pwd(), admin.getExt1(), admin.getExt2(),
				admin.getExt3() };
		System.out.println("执行成功行数：" + queryRun.update(sql, params));

	}
}
