package com.javasm.admin.entity;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Permission {
    private Integer perId;

    private String perName;

    private String perUrl;

    private Integer parentId;

    private Date createTime;

    private Date updateTime;

}