package com.jdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.alibaba.fastjson.JSON;
import com.pms.entity.TAdministrator;

/**
 * 通过JdbcTemplate实现查询操作
 * @author Taowd
 * @version 2018年9月2日
 * @see JdbcTemplateDemo
 */
public class JdbcTemplateDemo {

	// JdbcTemplate使用步骤：
	// 1、导入jar包；2、设置数据库信息；3、设置数据源；4、调用jdbcTemplate对象中的方法实现操作
	private JdbcTemplate jdbcTemplate = new JdbcTemplate();

	@Before
	public void initDataSource() {
		// JDBC模板依赖于连接池来获得数据的连接，所以必须先要构造连接池
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/pms");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		// 这里也可以使用构造方法
		jdbcTemplate.setDataSource(dataSource);
	}

	/**
	 * 查询总数.
	 */
	@Test
	public void test1() {

		// sql语句
		String sql = "select count(*)  from t_administrator";
		Long num = (long) jdbcTemplate.queryForObject(sql, Long.class);

		System.out.println(num);

	}

	@Test
	public void testQuery() {
		// sql语句
		String sql = "select * from t_administrator";
		List<TAdministrator> users = jdbcTemplate.query(sql, new AdministratorMapper());

		for (TAdministrator u : users) {
			System.out.println(JSON.toJSONString(u));
		}
	}

}

class AdministratorMapper implements RowMapper<TAdministrator> {

	@Override
	public TAdministrator mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		TAdministrator user = new TAdministrator();
		user.setAdminId(resultSet.getString(1));
		user.setAdminName(resultSet.getString(2));

		return user;
	}
}
