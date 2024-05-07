package com.fang.doit.design.proxy.jdk;

import com.alibaba.fastjson.JSON;

import java.lang.reflect.Proxy;

/**
 * 动态代理的方式，编写计算器功能同时需要在方法执行的时候记录日志
 */
public class LogJdkDynProxy {

    //目标对象
    private Object target;

    public Object bind(Object object) {
        this.target = object;

        // 这里第一个参数使用的是代理类的classLoader
        return Proxy.newProxyInstance(this.target.getClass().getClassLoader(), this.target.getClass().getInterfaces(), (proxy, method, args) -> {
            String name = method.getName();
            System.out.println("动态代理方法:" + name + " 真实对象:" + JSON.toJSONString(proxy));

            // springaop jdk源码中如果没有拦截器链的情况直接 执行要处理对象的原本方法
            Object invoke = method.invoke(proxy, args);
            System.out.println("方法执行结束返回值:" + invoke);
            return invoke;
        });
    }

    public static void main(String[] args) {

//        ClassLoader c  = LogJdkDynProxy.class.getClassLoader();
//        System.out.println(c);
//        ClassLoader c1 = c.getParent();
//        System.out.println(c1);
//        ClassLoader c2 = c1.getParent();
//        System.out.println(c2);

        IHello hello = (IHello) new LogJdkDynProxy().bind(new Hello());
        hello.hello();
        hello.helloV2();
    }


}

























