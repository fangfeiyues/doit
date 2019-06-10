package com.fang.doit.spring.mybatis;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author Feiyue
 * @Description:
 * @Date: Created in 2019/6/6 11:55
 */
@Repository
public interface UserMapper {

    User selectUser(String username);

    Grade getUserGrade(String username);

    User getUserWithGrades(String username);

}
