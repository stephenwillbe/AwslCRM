package com.javasm.util;

import java.util.UUID;

/**
 * @Description: ID生成工具类
 * @Author 陈嘉浩
 * @Date 2020/2/7
 * @Version 1.0
 **/
public class IdGenerateUtil {

    private IdGenerateUtil(){}

    /*
     * @Description //生成唯一id
     * @Param []
     * @return java.lang.String
     **/
    public static String getPrimaryKey(){
        return UUID.randomUUID().toString();
    }

}
