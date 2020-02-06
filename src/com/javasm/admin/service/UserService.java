package com.javasm.admin.service;

import com.javasm.admin.entity.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @Description: 用户服务接口
 * @Author cjh
 * @Date 2020/2/4
 * @Version 1.0
 **/
public interface UserService {

    public User getUser(Integer userid);

    public User checkUser(User orUser);

    public List<User> selectUser();

    public boolean updateUser(User newUser);

    boolean insUser(User newUser);


    boolean delUser(Integer userId);
}
