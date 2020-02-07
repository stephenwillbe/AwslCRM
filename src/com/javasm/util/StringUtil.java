package com.javasm.util;

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
}
