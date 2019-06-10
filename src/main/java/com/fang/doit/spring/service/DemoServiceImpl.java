package com.fang.doit.spring.service;

import com.fang.doit.spring.mybatis.Grade;
import com.fang.doit.spring.mybatis.User;
import com.fang.doit.spring.mybatis.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * created by fang on 2019/4/20/020 21:18
 */
@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
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
