package com.fang.doit.spring.service;

import com.fang.doit.spring.mybatis.Grade;
import com.fang.doit.spring.mybatis.User;
import com.fang.doit.spring.mybatis.UserMapper;

import javax.annotation.Resource;

/**
 * @author by Feiyue on 2020/1/9 7:54 PM
 */
public abstract class AbstractDemoService implements DemoService{

    @Resource
    private UserMapper userMapper;

    @Override
    public String hello() {
        return "world";
    }

    @Override
    public User getUserByUsername(String username) {
        return userMapper.selectUser(username);
    }

    @Override
    public Grade getUserGrade(String username) {

        return userMapper.getUserGrade(username);
    }

    @Override
    public User getUserWithGrades(String username){
        return userMapper.getUserWithGrades(username);
    }
}
