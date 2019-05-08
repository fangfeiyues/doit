package com.fang.doit.base;

/**
 * created by fang on 2019/5/8/008 23:22
 */
public class MyClassLoader extends ClassLoader {

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        return super.findClass(name);
    }
}
