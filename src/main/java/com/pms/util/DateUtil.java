package com.pms.util;


import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;


/**
 * @author Taowd 功 能：日期工具类 编写时间：2017-4-11-下午2:27:37
 */
public class DateUtil
{
    /**
     * 一天的毫秒数
     */
    public static final long SECOND_OF_DAY = 86400000L;

    /**
     * Author:Taowd 功能：将日期转成指定格式的字符串 开发日期：2017-4-11-下午2:27:52
     * 
     * @param date
     * @param format
     * @return
     * @throws Exception
     */
    public static String formatDate(Date date, String format)
        throws Exception
    {
        String result = "";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        if (date != null)
        {
            result = sdf.format(date);
        }
        return result;
    }

    /**
     * Author:Taowd 功能：将指定格式的字符串转成日期 开发日期：2017-4-11-下午2:28:29
     * 
     * @param str
     * @param format
     * @return
     * @throws Exception
     */
    public static Date formatString(String str, String format)
        throws Exception
    {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.parse(str);
    }

    /**
     * Author:Taowd 功能：获取当前系统时间 开发日期：2017-4-11-下午9:49:50
     * 
     * @return 当前日期的字符串
     * @throws Exception
     */
    public static String getCurrentDateStr()
        throws Exception
    {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    /**
     * Author:Taowd 功能：计算两个日期间隔的天数，忽略时分秒 开发日期：2017-5-8-上午10:37:25
     * 
     * @param dateStart
     *            开始日期
     * @param dateEnd
     *            结束日期
     * @return 间隔天数
     */
    public static int dayLength(Date dateStart, Date dateEnd)
    {
        int sign = 1;
        // 判断开始时日期是否晚于结束日期
        if (dateStart.after(dateEnd))
        {
            Date dateTemp = dateStart;
            dateStart = dateEnd;
            dateEnd = dateTemp;
            sign = -1;
        }
        Calendar calendarStart = Calendar.getInstance();
        calendarStart.setTime(dateStart);
        calendarStart.set(Calendar.HOUR_OF_DAY, 1);
        calendarStart.set(Calendar.MINUTE, 0);
        calendarStart.set(Calendar.SECOND, 0);

        Calendar calendarEnd = Calendar.getInstance();
        calendarEnd.setTime(dateEnd);
        calendarEnd.set(Calendar.HOUR_OF_DAY, 2);
        calendarEnd.set(Calendar.MINUTE, 0);
        calendarEnd.set(Calendar.SECOND, 0);

        long diffScond = calendarEnd.getTimeInMillis() - calendarStart.getTimeInMillis();
        long diffDays = diffScond / SECOND_OF_DAY;

        return (int)diffDays * sign;

    }

    /** Prevent instantiation */
    private DateUtil()
    {}
}
