package com.javasm.admin.service.impl;

import com.javasm.util.MD5Util;
import com.javasm.admin.dao.UserMapper;
import com.javasm.admin.entity.User;
import com.javasm.admin.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Description: 用户服务实现
 * @Author 陈嘉浩
 * @Date 2020/2/4
 * @Version 1.0
 **/
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper ud;

    /*
     * @Description //获取单个用户的信息
     * @Param 用户id
     * @return User
     **/
    @Override
    public User getUser(Integer userid) {
        User user = ud.selectByPrimaryKey(userid);
        return user;
    }

    /*
     * @Description //验证密码，返回是否存在用户，并更新登陆时间
     * @Param 待验证密码
     * @return com.javasm.admin.entity.User
     **/
    @Transactional
    @Override
    public User checkUser(User orUser) {
        if (orUser != null) {
            String orPass = orUser.getUserPass();
            orPass = MD5Util.getMD5Pass(orPass);
            orUser.setUserPass(orPass);
            List<User> userList = ud.selectUsers(orUser);
            if (userList != null && userList.size() == 1) {
                //跟新登陆时间
                User verUser = userList.get(0);
                verUser.setUpdateTime(new Date());
                return userList.get(0);
            }
            return null;
        } else {
            return null;
        }
    }

    /*
     * @Description //获取全部用户信息
     * @Param []
     * @return java.util.List<com.javasm.admin.entity.User>
     **/
    @Override
    public List<User> selectUser() {
        return ud.selectUsers(null);
    }

    /*
     * @Description //跟新用户信息
     * @Param [newUser]
     * @return boolean
     **/
    @Transactional
    @Override
    public boolean updateUser(User newUser) {
        if (newUser != null) {
            int num = ud.updateByPrimaryKeySelective(newUser);
            if (num > 0) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    /*
     * @Description //新增用户
     * @Param [newUser]
     * @return boolean
     **/
    @Transactional
    @Override
    public boolean insUser(User newUser){
        if (newUser != null) {
            int num = ud.insertSelective(newUser);
            if (num > 0) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    /*
     * @Description //清除用户
     * @Param [userId]
     * @return boolean
     **/
    @Transactional
    @Override
    public boolean delUser(Integer userId){
        if(userId!=null){
            int num = ud.deleteByPrimaryKey(userId);
            if(num>0){
                return true;
            }
            return false;
        }
       return false;
    }
}
