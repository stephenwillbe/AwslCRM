package com.javasm.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description: 日期转换工具类
 * @Author 陈嘉浩
 * @Date 2020/2/5
 * @Version 1.0
 **/
public class DateUtil {

    private DateUtil(){}

    /*
     * @Description //字符串转日期
     * @Param [str]
     * @return java.util.Date
     **/
    public static Date StrToDate(String str){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
             date = simpleDateFormat.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
    /*
     * @Description //日期格式化
     * @Param [date]
     * @return java.lang.String
     **/
    public static String DateToStr(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = simpleDateFormat.format(date);
        return str;
    }
}
