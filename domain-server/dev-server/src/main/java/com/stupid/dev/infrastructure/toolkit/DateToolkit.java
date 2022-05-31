package com.stupid.dev.infrastructure.toolkit;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * 时间转换工具类
 */
public class DateToolkit {

    private static final String yMdHms = "yyyy-MM-dd HH:mm:ss";

    private static ThreadLocal<DateFormat> threadLocal = new ThreadLocal<>();

    public static DateFormat getDateFormat(){
        DateFormat df = threadLocal.get();
        if(df == null){
            df = new SimpleDateFormat(yMdHms);
            threadLocal.set(df);
        }
        return df;
    }

    /**
     * 格式化时间 安全
     */
    public static String formatDate(Date date){
        if(date!=null){
            return getDateFormat().format(date);
        }
        return null;
    }

    /**
     * 格式化时间 安全
     */
    public static Date parse(String dateStr) throws ParseException {
        return getDateFormat().parse(dateStr);
    }

    /**
     * 获取当前时区时间
     * 2022-05-26T14:08:44.432+08:00[Asia/Shanghai]
     */
    public static String getZonedDateTime(){
        ZonedDateTime zdt = ZonedDateTime.now();
        return zdt.toString();
    }

}
