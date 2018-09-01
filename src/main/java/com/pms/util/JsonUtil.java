package com.pms.util;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 将数据集转成JSON串
 * @author Taowd
 * @version 2018年8月31日
 * @see JsonUtil
 */
public class JsonUtil {

	/**
	 * 功能：数据集封装在json数组中
	 * @param rs
	 * @return
	 * @throws Exception
	 */
	public static List<Map<String, Object>> formatRsToJsonArray(ResultSet rs) throws Exception {
		// 得到ResultSet对象中列类型和属性信息对象
		ResultSetMetaData md = rs.getMetaData();
		// 得到列总数
		int num = md.getColumnCount();

		List<Map<String, Object>> array = Lists.newArrayList();
		Map<String, Object> mapOfColValues = null;
		while (rs.next()) {
			mapOfColValues = Maps.newHashMap();
			for (int i = 1; i <= num; i++) {
				Object o = rs.getObject(i);
				if (o instanceof Date) {
					mapOfColValues.put(md.getColumnName(i),
							DateUtil.formatDate((Date) o, "yyyy-MM-dd"));
				} else {
					mapOfColValues.put(md.getColumnName(i), rs.getObject(i));
				}
			}
			array.add(mapOfColValues);// json数组添加json对象
		}

		Log4jHelper.info("返回页面的JSON数据格式：" + JSON.toJSONString(array));

		return array;
	}

	/** Prevent instantiation */
	private JsonUtil() {
	}
}
