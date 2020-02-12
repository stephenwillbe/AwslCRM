package com.javasm.admin.service.impl;

import com.alibaba.fastjson.JSON;
import com.javasm.util.IdGenerateUtil;
import com.javasm.util.MD5Util;
import com.javasm.admin.dao.UserMapper;
import com.javasm.admin.entity.User;
import com.javasm.admin.service.UserService;
import com.javasm.util.RedisService;
import com.javasm.util.StringUtil;
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

    @Resource
    private RedisService rs;

    private String userinfokey = "userinfo:";
//

    /*
     * @Description //获取单个用户的信息
     * @Param 用户id
     * @return User
     **/
    @Override
    public User getUser(String userid) {
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
     * @Description //根据用户手机号获得用户信息
     * @Param [uPhone]
     * @return com.javasm.admin.entity.User
     **/
    @Transactional
    @Override
    public User getUserByPhone(String uPhone) {
        //在redis实例中根据手机号获得用户的信息，可以存string或者hash
        String key = userinfokey+uPhone;
        String userinfostr = rs.get(key);
        if(userinfostr==null){
            User u = new User();
            u.setUserPhone(uPhone);
            //从sql数据库查询
            User userinfo = ud.selectUserBySelective(u);
            if(userinfo!=null){
            //放入redis
            rs.set(key, JSON.toJSONString(userinfo));
            return userinfo;
            }else {
                //未注册
                return null;
            }
        }else{
            //userinfostr转对象
            User userinfo = JSON.parseObject(userinfostr, User.class);
            return userinfo;
        }
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
    public boolean delUser(String userId){
        if(userId!=null){
            int num = ud.deleteByPrimaryKey(userId);
            if(num>0){
                return true;
            }
            return false;
        }
       return false;
    }

    /*
     * @Description //根据手机号快速注册账号,设置默认用户名为手机号
     * @Param [userPhone]
     * @return boolean
     **/
    @Transactional
    @Override
    public User registUser(String userPhone) {
        if(StringUtil.isMobile(userPhone)&&StringUtil.isNotEmpty(userPhone)){
            User newUser = new User();
            newUser.setUserPhone(userPhone);
            newUser.setUserId(IdGenerateUtil.getPrimaryKey());
            newUser.setUserName(userPhone);
            int num = ud.insertSelective(newUser);
            if(num>0){
                return newUser;
            }else{
                return null;
            }
        }
        return null;
    }
}
