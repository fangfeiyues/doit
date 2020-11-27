package com.fang.doit.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Author Feiyue
 * @Description:
 * @Date: Created in 2019/5/16 13:58
 */
@Component
public class ApplicationContextAwareDemoService
    implements BeanNameAware, BeanFactoryAware, BeanClassLoaderAware, ApplicationContextAware {

    private String beanName;

    private BeanFactory beanFactory;

    private ClassLoader classLoader;

    private ApplicationContext applicationContext;

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("������ BeanClassLoaderAware �� setBeanClassLoader ����");
        this.classLoader = classLoader;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("������ BeanFactoryAware �� setBeanFactory ����");
        this.beanFactory = beanFactory;
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("������ BeanNameAware �� setBeanName ����");
        this.beanName = name;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        System.out.println("������ ApplicationContextAware �� setApplicationContext ����");
        this.applicationContext = applicationContext;
    }

    public void display() {
        System.out.println("beanName:" + beanName);
        System.out.println("�Ƿ�Ϊ������" + beanFactory.isSingleton(beanName));
//        System.out.println("ϵͳ����Ϊ��" + applicationContext.getEnvironment());
    }

    public static Boolean checkZero(Long data) {
        return data != null && data < 0;
    }

    public static void main(String[] args) {
        System.out.println(checkZero(-1L));
    }
}
