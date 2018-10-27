package com.pms.mapper;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class InductioninfoMapperTest {
	
	@Autowired
	public InductioninfoMapper inductioninfoMapper;

	/**
	 * 测试查询功能.
	 */
	@Test
	public void testQuery() {

		List<Map<String, Object>> list = inductioninfoMapper.getUserNameComboList();

		System.out.println(JSON.toJSONString(list));
	}
}
