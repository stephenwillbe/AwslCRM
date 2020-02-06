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
     * @Description //获取全部用户的信息
     * @Param []
     * @return 全部用户
     **/
    @Override
   public User getUser(Integer userid){
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
        if(orUser!=null){
            String orPass = orUser.getUserPass();
            orPass = MD5Util.getMD5Pass(orPass);
            orUser.setUserPass(orPass);
            List<User> userList = ud.selectUsers(orUser);
            if(userList!=null&&userList.size()==1){
                //跟新登陆时间
                User verUser = userList.get(0);
                verUser.setUpdateTime(new Date());
                return userList.get(0);
            }
            return null;
        }else{
            return null;
        }
    }
}
