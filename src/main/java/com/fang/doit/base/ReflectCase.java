package com.fang.doit.base;

import java.lang.reflect.Method;

/**
 * @Author Feiyue
 * @Description:
 * @Date: Created in 2019/5/8 20:14
 */
public class ReflectCase {

    public static void main(String[] args) throws Exception {
        Proxy target = new Proxy();
        Method method = Proxy.class.getDeclaredMethod("run",Integer.class);
        method.setAccessible(true);
        method.invoke(target);
    }

    static class Proxy {
        private void run(int a, String b) {
            System.out.println("runing" + a + b);
        }
    }

}
