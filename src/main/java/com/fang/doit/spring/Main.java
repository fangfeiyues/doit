package com.fang.doit.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author Feiyue
 * @Description:
 * @Date: Created in 2019/5/22 14:01
 */
public class Main {

    public static void main(String[] args) {
//        ClassPathResource resource = new ClassPathResource("application.xml");
//        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
//        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
//        reader.loadBeanDefinitions(resource);

        //ApplicationContextAwareDemo applicationAware = (ApplicationContextAwareDemo)factory.getBean(
        //    "applicationContextAwareDemo");
        //applicationAware.display();

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application.xml");
//        DemoServiceImpl demoService = (DemoServiceImpl)applicationContext.getBean(
//            "demoServiceImpl");
        //User user = demoService.getUserByUsername("fang");
        //Grade grade = demoService.getUserGrade("fang");
        //System.out.println(grade.getGrade());
//        User user = demoService.getUserWithGrades("fang");
//        System.out.println(JSON.toJSONString(user));

        //BeanPostProcessorDemo test = (BeanPostProcessorDemo) factory.getBean("beanPostProcessorDemo");
        //BeanPostProcessorDemo test = new BeanPostProcessorDemo();
        //factory.addBeanPostProcessor(test);
        //test.display();



    }
}
