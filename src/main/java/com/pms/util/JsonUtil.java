package com.pms.util;


import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * @author Taowd 功 能：将数据集转成JSON串 编写时间：2017-4-25-下午7:31:34
 */
public class JsonUtil
{

    /**
     * 功能：数据集封装在json数组中
     * 
     * @param rs
     * @return
     * @throws Exception
     */
    public static JSONArray formatRsToJsonArray(ResultSet rs)
        throws Exception
    {
        ResultSetMetaData md = rs.getMetaData();// 得到ResultSet对象中列类型和属性信息对象
        int num = md.getColumnCount();// 得到列总数
        JSONArray array = new JSONArray();// 得到json数组对象
        while (rs.next())
        {
            JSONObject mapOfColValues = new JSONObject();// Json对象
            for (int i = 1; i <= num; i++ )
            {
                Object o = rs.getObject(i);
                if (o instanceof Date)
                {
                    mapOfColValues.put(md.getColumnName(i),
                        DateUtil.formatDate((Date)o, "yyyy-MM-dd"));
                }
                else
                {
                    mapOfColValues.put(md.getColumnName(i), rs.getObject(i));
                }
            }
            array.add(mapOfColValues);// json数组添加json对象
        }

        Log4jHelper.info("返回页面的JSON数据格式：" + array.toString());

        return array;
    }

    /** Prevent instantiation */
    private JsonUtil()
    {}
}
