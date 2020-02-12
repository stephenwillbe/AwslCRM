package com.javasm.admin.handler;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.javasm.admin.entity.User;
import com.javasm.admin.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: 用户控制，用户数据的增删改查
 * @Author 陈嘉浩
 * @Date 2020/2/4
 * @Version 1.0
 **/
@Controller
@RequestMapping("/um")
public class UserHandler {

    @Resource
    private UserService us;

    /*
     * @Description //分页获取用户数据
     * @Param 当前页码，当页记录数
     * @return 用户和分页信息
     **/
    @RequestMapping("/getAllUsers")
    @ResponseBody
    public List<User> getUserListByPage(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "10") int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> userList = us.selectUser();
        PageInfo<User> info = new PageInfo<>(userList);
        return userList;
    }

    /*
     * @Description //跟新用户数据
     * @Param 更改数据后的用户
     * @return java.lang.String
     **/
    @RequestMapping("/uptUser")
    @ResponseBody
    public String uptUser(User uptUser) {
        boolean flag = us.updateUser(uptUser);
        if (flag) {
            return "success";
        } else {
            return "failure";
        }
    }

    /*
     * @Description //用户修改密码
     * @Param [newPass]
     * @return java.lang.String
     **/
    @RequestMapping("/uptUserPass")
    @ResponseBody
    public String uptUserPass(String newPass){
        return "failure";
    }

    /*
     * @Description //新增用户
     * @Param [user]
     * @return java.lang.String
     **/
    @RequestMapping("/insUser")
    @ResponseBody
    public String insUser(User newUser){
        boolean flag = us.insUser(newUser);
        if(flag){
            return "success";
        }else {
            return "failure";
        }
    }

    /*
     * @Description //根据id删除用户
     * @Param [userId]
     * @return java.lang.String
     **/
    @RequestMapping("/delUser")
    @ResponseBody
    public String delUser(Integer userId){
        return "failure";
    }

    /*
     * @Description //根据用户手机号返回用户的信息
     * @Param [uphone]
     * @return com.javasm.admin.entity.User
     **/
    @PostMapping("/getUser/{userPhone}")
    @ResponseBody
    public User getUser(@PathVariable("userPhone") String uphone){
        User userByPhone = us.getUserByPhone(uphone);
        return userByPhone;
    }


}
