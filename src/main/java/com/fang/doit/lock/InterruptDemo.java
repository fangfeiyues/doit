package com.fang.doit.lock;

/**
 * @Author Feiyue
 * @Description:
 * @Date: Created in 2019/5/30 10:11
 */
public class InterruptDemo {

    /**
     * �̵߳��ж� ���� --
     *
     * @param args
     */
    //public static void main(String[] args) {
    //
    //    NThread nThread = new NThread();
    //    System.out.println("interruptִ��ǰ");
    //    nThread.start();
    //    try {
    //        Thread.sleep(3000);
    //    } catch (InterruptedException e) {
    //        e.printStackTrace();
    //    }
    //    nThread.interrupt();
    //    System.out.println("interruptִ�к�");
    //}
    //
    ///**
    // * ���Զ��̵߳��жϻ���
    // */
    //static class NThread extends Thread {
    //    @Override
    //    public void run() {
    //        super.run();
    //        while (true) {
    //            System.out.println("��Ȼ���...");
    //            try {
    //                Thread.sleep(1000);
    //            } catch (InterruptedException e) {
    //                e.printStackTrace();
    //            }
    //        }
    //    }
    //}

    /**
     * Created by Zero on 2017/8/17.
     */
    public static void main(String[] args) {
        NThread nThread = new NThread();
        nThread.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("interruptִ��ǰ" + System.currentTimeMillis());
        nThread.interrupt();
    }

    /**
     * ���Զ��̵߳��жϻ���
     */
    static class NThread extends Thread {

        @Override
        public void run() {
            /**
             * ���ڷ������е��̣߳�ֻ�Ǹı����ж�״̬����Thread.isInterrupted() ������true��
             * ���ڿ�ȡ��������״̬�е��̣߳�����ȴ�����Щ�����ϵ��̣߳�Thread.sleep()��Object.wait()��Thread.join(), ����߳��յ��ж��źź󣬻��׳�InterruptedException��ͬʱ����ж�״̬�û�Ϊtrue��
             * ������Thread.interrupted()
             * ����ж�״̬���и�λ��
             */
            while (!interrupted()) {
                System.out.println("��Ȼ���...");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {

                    //�׳�InterruptedException ��ͬʱ���̵߳��жϱ�־�������
                    System.out.println("InterruptedException");

                    // ������ﲻ��interrupt()����һֱ��ȥ ��Σ�գ�
                    Thread.currentThread().interrupt();
                }
            }
            System.out.println("interruptִ�к�" + System.currentTimeMillis());
        }
    }
}


