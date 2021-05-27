package com.fang.doit.frame.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author by Feiyue on 2020/1/22 2:54 PM
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {

    private static ApplicationContext context = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        context = applicationContext;
    }

    /**
     * ���������� spring bean�ĳ�ʼ����ȻҪ��jvm���ʼ��֮ǰ���
     *
     * @param beanName
     * @param <T>
     * @return
     */
    public static <T> T getBean(String beanName) {
        return (T) context.getBean(beanName);
    }

    public static String getActiveProfile() {
        return context.getEnvironment().getActiveProfiles()[0];
    }
}


