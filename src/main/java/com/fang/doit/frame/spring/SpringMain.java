package com.fang.doit.frame.spring;

import com.fang.doit.frame.spring.aop.TransactionTest;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

/**
 * @Author Feiyue
 * @Description:
 * @Date: Created in 2019/5/22 14:01
 */
public class SpringMain {

    public static void main(String[] args) {
        invokeBean();
    }


    private static void invokeBean() {
        //  Spring StartUp 1
        ClassPathResource resource = new ClassPathResource("application.xml");
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(resource);
        TransactionTest applicationAware = (TransactionTest) factory.getBean("transactionTest");
        applicationAware.addUser();


        //  Spring StartUp 2
//        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application.xml");
//        DemoServiceImpl demoService = (DemoServiceImpl) applicationContext.getBean(
//                "demoServiceImpl");
//        Grade grade = demoService.getUserGrade("fang");
//        System.out.println(grade.getGrade());
//        User user = demoService.getUserWithGrades("fang");
//        System.out.println(JSON.toJSONString(user));

    }
}
