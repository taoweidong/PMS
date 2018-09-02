package com.pms.mapper;

import java.util.List;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.pms.entity.TAdministrator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class TAdministratorMapperTest {

	@Autowired
	public TAdministratorMapper tAdministratorMapper;

	/**
	 * 测试查询功能.
	 */
	@Test
	public void testQuery() {

		List<TAdministrator> result = tAdministratorMapper.selectAll();

		System.out.println(JSON.toJSONString(result));
	}

	@Test
	public void testAdd() {

		final String id = UUID.randomUUID().toString();

		TAdministrator admin = new TAdministrator();
		admin.setAdminName("testMybatisAdd");
		admin.setAdminId(id);

		tAdministratorMapper.insert(admin);

		System.out.println(JSON.toJSONString(tAdministratorMapper.selectByPrimaryKey(id)));
	}

}
