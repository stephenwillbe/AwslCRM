package com.javasm.admin.entity;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Role {
    private Integer roleId;

    private String roleName;

    private Date updateTime;

    private Date createTime;

}