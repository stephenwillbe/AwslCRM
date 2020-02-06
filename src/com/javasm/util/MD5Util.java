package com.javasm.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Description: 加密工具类
 * @Author 陈嘉浩
 * @Date 2020/2/4
 * @Version 1.0
 **/
public class MD5Util {

    private MD5Util(){}

    /*
     * @Description //加密
     * @Param [原始密码]
     * @return 加密后的密码
     **/
    public static String getMD5Pass(String resource){
        String salt = "#$^@ste";
        resource = resource+salt;
        byte[] by =null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(resource.getBytes());
            by = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return new BigInteger(1,by).toString(16);
    }


    public static void main(String[] args) {
        System.out.println(MD5Util.getMD5Pass("admin"));
    }


}
