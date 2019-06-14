package com.fang.doit.base;

import java.io.InputStream;

/**
 * created by fang on 2019/5/8/008 23:22
 */
public class MyClassLoader extends ClassLoader {

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        return super.findClass(name);
    }

    public static void main(String[] args) {

//        ClassLoader classLoader = new ClassLoader() {
//            // ²»Ìá³«¸´Ð´loadClass
//            @Override
//            public Class<?> loadClass(String name) throws ClassNotFoundException {
//                try {
//                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
//                    InputStream is = getClass().getResourceAsStream(fileName);
//                    if (is == null) {
//                        return super.loadClass(name);
//                    }
//
//                    byte[] b = new byte[is.available()];
//                    is.read(b);
//                    return defineClass(name, b, 0, b.length);
//                } catch (Exception e) {
//                    throw new ClassNotFoundException(name);
//                }
//            }
//        };

        System.out.println(Thread.currentThread().getContextClassLoader());
        System.out.println(MyClassLoader.class.getClassLoader());

    }
}
