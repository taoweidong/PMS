package com.pms.mapper;

import java.util.List;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.pms.entity.Administrator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class AdministratorMapperTest {

	@Autowired
	public AdministratorMapper administratorMapper;

	/**
	 * 测试查询功能.
	 */
	@Test
	public void testQuery() {

		List<Administrator> result = administratorMapper.selectAll();

		System.out.println(JSON.toJSONString(result));
	}

	@Test
	public void testAdd() {

		final String id = UUID.randomUUID().toString();

		Administrator admin = new Administrator();
		admin.setName("testMybatisAdd");
		admin.setId(id);

		administratorMapper.insert(admin);

		System.out.println(JSON.toJSONString(administratorMapper.selectByPrimaryKey(id)));
	}

}
