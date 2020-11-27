package com.fang.doit.spring.ioc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author by Feiyue on 2020/6/28 6:53 PM
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface Metrics {
    /**
     * �ڷ����ɹ�ִ�к��㣬��¼������ִ��ʱ�䷢�͵�ָ��ϵͳ��Ĭ�Ͽ���
     *
     * @return
     */
    boolean recordSuccessMetrics() default true;

    /**
     * �ڷ����ɹ�ʧ�ܺ��㣬��¼������ִ��ʱ�䷢�͵�ָ��ϵͳ��Ĭ�Ͽ���
     *
     * @return
     */
    boolean recordFailMetrics() default true;

    /**
     * ͨ����־��¼���������Ĭ�Ͽ���
     *
     * @return
     */
    boolean logParameters() default true;

    /**
     * ͨ����־��¼��������ֵ��Ĭ�Ͽ���
     *
     * @return
     */
    boolean logReturn() default true;

    /**
     * �����쳣��ͨ����־��¼�쳣��Ϣ��Ĭ�Ͽ���
     *
     * @return
     */
    boolean logException() default true;

    /**
     * �����쳣������쳣����Ĭ��ֵ��Ĭ�Ϲر�
     *
     * @return
     */
    boolean ignoreException() default false;
}
