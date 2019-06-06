package com.fang.doit.spring;

import com.fang.doit.spring.mybatis.User;
import com.fang.doit.spring.service.DemoServiceImpl;
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
        ClassPathResource resource = new ClassPathResource("application.xml");
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(resource);

        //ApplicationContextAwareDemo applicationAware = (ApplicationContextAwareDemo)factory.getBean(
        //    "applicationContextAwareDemo");
        //applicationAware.display();

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application.xml");
        DemoServiceImpl demoService = (DemoServiceImpl)applicationContext.getBean(
            "demoServiceImpl");
        User user = demoService.getUserByUsername("fang");
        System.out.println(user.getName());
        //BeanPostProcessorDemo test = (BeanPostProcessorDemo) factory.getBean("beanPostProcessorDemo");
        //BeanPostProcessorDemo test = new BeanPostProcessorDemo();
        //factory.addBeanPostProcessor(test);
        //test.display();
    }
}
