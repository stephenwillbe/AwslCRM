package com.javasm.util;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description: 字符串工具类
 * @Author 陈嘉浩
 * @Date 2020/2/7
 * @Version 1.0
 **/
public class StringUtil {

    private StringUtil(){}

    /*
     * @Description //判断是否为空
     * @Param [str]
     * @return boolean
     **/
    public static boolean isEmpty(String str){
        if (str == null || "".equals(str.trim())) {
            return true;
        } else {
            return false;
        }
    }

    /*
     * @Description //判断不为空
     * @Param [str]
     * @return boolean
     **/
    public static boolean isNotEmpty(String str){
        if ((str != null) && !"".equals(str.trim())) {
            return true;
        } else {
            return false;
        }
    }
    /*
     * @Description //判断是否为手机号码
     * @Param 手机号
     * @return boolean
     **/
    public static boolean isMobile(final String phone) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$"); // 验证手机号
        m = p.matcher(phone);
        b = m.matches();
        return b;
    }

    /*
     * @Description //将字符串转化为模糊格式
     * @Param [str]
     * @return java.lang.String
     **/
    public String formatLike(String str){
        if(isNotEmpty(str)){
            return "%"+str+"%";
        }
        return null;
    }

    /*
     * @Description //生成随机数字
     * @Param 几位随机数
     * @return java.lang.String
     **/
    public static String randomStr(int num){
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        for(int i=0;i<num;i++){
            stringBuilder.append(random.nextInt(num));
        }
        return stringBuilder.toString();
    }
}
