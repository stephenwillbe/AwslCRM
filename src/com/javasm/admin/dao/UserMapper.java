package com.javasm.admin.dao;

import com.javasm.admin.entity.User;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    List<User> selectUsers(User orUser);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}