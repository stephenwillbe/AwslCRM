package com.javasm.admin.handler;

import com.javasm.admin.service.PermissionService;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * @Description: 权限控制
 * @Author 陈嘉浩
 * @Date 2020/2/7
 * @Version 1.0
 **/
@Controller
public class PermissionHandler {

    @Resource
    private PermissionService ps;



}
