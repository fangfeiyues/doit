package com.fang.doit.spring.service;

import com.fang.doit.spring.mybatis.Grade;
import com.fang.doit.spring.mybatis.User;
import org.springframework.stereotype.Service;


public interface DemoService {

    String hello();

    User getUserByUsername(String username);

    Grade getUserGrade(String username);

    User getUserWithGrades(String username);
}
