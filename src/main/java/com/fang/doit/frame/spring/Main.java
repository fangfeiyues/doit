package com.fang.doit.frame.spring;

import com.alibaba.fastjson.JSON;
import com.fang.doit.frame.spring.mybatis.Grade;
import com.fang.doit.frame.spring.mybatis.User;
import com.fang.doit.frame.spring.service.DemoServiceImpl;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

/**
 * @Author Feiyue
 * @Description:
 * @Date: Created in 2019/5/22 14:01
 */
public class Main {

    public static void main(String[] args) {
        invokeBean();
    }


    private static void invokeBean() {
        //  Spring StartUp 1
        ClassPathResource resource = new ClassPathResource("application.xml");
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(resource);
        ApplicationContextAwareDemoService applicationAware = (ApplicationContextAwareDemoService) factory.getBean(
                "applicationContextAwareDemoService");
        applicationAware.display();


        //  Spring StartUp 2
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application.xml");
        DemoServiceImpl demoService = (DemoServiceImpl) applicationContext.getBean(
                "demoServiceImpl");
        Grade grade = demoService.getUserGrade("fang");
        System.out.println(grade.getGrade());
        User user = demoService.getUserWithGrades("fang");
        System.out.println(JSON.toJSONString(user));

    }
}
