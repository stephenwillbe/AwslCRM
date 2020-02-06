package com.javasm.admin.service.impl;

import com.javasm.admin.dao.PermissionMapper;
import com.javasm.admin.service.PermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description: TODO
 * @Author 陈嘉浩
 * @Date 2020/2/7
 * @Version 1.0
 **/
@Service
public class PermissionServiceImpl implements PermissionService {

    @Resource
    private PermissionMapper pm;

}
