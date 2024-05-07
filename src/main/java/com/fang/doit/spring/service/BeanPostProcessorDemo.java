package com.fang.doit.spring.service;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @Author Feiyue
 * @Description:
 * @Date: Created in 2019/5/22 14:30
 */
@Component
public class BeanPostProcessorDemo implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        System.out.println("Bean [" + beanName + "]postProcessBeforeInitialization");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        System.out.println("Bean [" + beanName + "]postProcessAfterInitialization");
        return bean;
    }

    public void display() {
        System.out.println("hello BeanPostProcessor!!!");
    }
}
