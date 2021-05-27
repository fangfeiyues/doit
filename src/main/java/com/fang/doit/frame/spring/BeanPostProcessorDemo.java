package com.fang.doit.frame.spring;

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
        System.out.println("Bean [" + beanName + "] ��ʼ��ʼ��");
        // ����һ��Ҫ���� bean�����ܷ��� null
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        System.out.println("Bean [" + beanName + "] ��ɳ�ʼ��");
        return bean;
    }

    public void display() {
        System.out.println("hello BeanPostProcessor!!!");
    }
}
