package com.fang.doit.frame.spring;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

/**
 * @Author Feiyue
 * @Description:
 * @Date: Created in 2019/5/22 14:01
 */
public class Main {

    public static void main(String[] args) {
        ClassPathResource resource = new ClassPathResource("application.xml");
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(resource);
        ApplicationContextAwareDemoService applicationAware = (ApplicationContextAwareDemoService)factory.getBean(
                "applicationContextAwareDemoService");
        applicationAware.display();

//        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application.xml");
//        DemoServiceImpl demoService = (DemoServiceImpl)applicationContext.getBean(
//            "demoServiceImpl");
//        User user = demoService.getUserByUsername("fang");
//        Grade grade = demoService.getUserGrade("fang");
//        System.out.println(grade.getGrade());
//        User user = demoService.getUserWithGrades("fang");
//        System.out.println(JSON.toJSONString(user));


        //BeanPostProcessorDemo test = (BeanPostProcessorDemo) factory.getBean("beanPostProcessorDemo");
        //BeanPostProcessorDemo test = new BeanPostProcessorDemo();
        //factory.addBeanPostProcessor(test);
        //test.display();



    }
}
