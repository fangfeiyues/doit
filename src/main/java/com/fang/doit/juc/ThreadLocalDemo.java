package com.fang.doit.juc;

/**
 * @author : fangfeiyue
 * @version V1.0
 * @Project: doit
 * @Package com.fang.doit.juc
 * @Description:
 * @date Date : 2024-06-18 2:17 上午
 */
public class ThreadLocalDemo {

    private static final ThreadLocal<Integer> LOCAL = new ThreadLocal();

    // 下一个序列号
    public int getNextNum() {
        LOCAL.set(LOCAL.get() + 1);
        return LOCAL.get();
    }

    private static class TestClient extends Thread {
        private ThreadLocalDemo sn;
        public TestClient(ThreadLocalDemo sn) {
            this.sn = sn;
        }
        // 线程变量加加
        @Override
        public void run() {
            for (int i = 0; i < 3; i++) {
                System.out.println(Thread.currentThread().getName()  +"  Num:" +  sn.getNextNum());
            }
        }
    }

    public static void main(String[] args) {
        ThreadLocalDemo test = new ThreadLocalDemo();
        new TestClient(test).start();
        new TestClient(test).start();
        new TestClient(test).start();

    }
}
