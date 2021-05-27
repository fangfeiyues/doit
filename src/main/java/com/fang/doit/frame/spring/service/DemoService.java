package com.fang.doit.frame.spring.service;

import com.fang.doit.frame.spring.mybatis.Grade;
import com.fang.doit.frame.spring.mybatis.User;


public interface DemoService {

    String hello();

    User getUserByUsername(String username);

    Grade getUserGrade(String username);

    User getUserWithGrades(String username);
}
