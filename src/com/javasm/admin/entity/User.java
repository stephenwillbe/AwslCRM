package com.javasm.admin.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class User {
    private String userId;

    private String userName;

    private String userPass;

    private Integer userAge;

    private String userDept;

    private String userPhone;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    public User(String userName, String userPass) {
        this.userName = userName;
        this.userPass = userPass;
    }

    public User(String userName, String userPass, Integer userAge,  String userDept , String userPhone) {
        this.userName = userName;
        this.userPass = userPass;
        this.userAge = userAge;
        this.userDept = userDept;
        this.userPhone = userPhone;
    }
}