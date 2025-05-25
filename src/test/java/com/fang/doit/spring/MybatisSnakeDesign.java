package com.fang.doit.spring;

import com.alibaba.fastjson.JSON;
import com.fang.doit.spring.mybatis.User;
import com.fang.doit.spring.mybatis.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

/**
 * @Author Feiyue
 * @Description:
 * @Date: Created in 2019/6/10 10:31
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class MybatisSnakeDesign {

    UserMapper mapper;

    @Before
    public void setUp() throws Exception {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        mapper = sqlSession.getMapper(UserMapper.class);
    }

    @Test
    public void getUser() throws Exception {
        //for (int i = 0; i < 2; i++) {
        //    User user = mapper.selectUser("fang");
        //    System.out.println(user.getName());
        //}

        User user = mapper.selectUser("fang;delete from user");
        System.out.println(JSON.toJSONString(user));
    }

}
