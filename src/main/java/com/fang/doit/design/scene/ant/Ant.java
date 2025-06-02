package com.fang.doit.design.scene.ant;

import java.util.concurrent.locks.ReentrantLock;

public class Ant {

    public static volatile int count = 1;

   public static final ReentrantLock lock = new ReentrantLock();

//    public static void main(String[] args) {
//        // 读写锁
//        List<Condition> conditionList = new ArrayList();
//        Condition condition1 = lock.newCondition();
//        conditionList.add(condition1);
//
//        Condition condition2 = lock.newCondition();
//        conditionList.add(condition2);
//
//        new ConditionWorker(0, conditionList).start();
//        new ConditionWorker(1, conditionList).start();
//    }
//
//
//    static class ConditionWorker extends Thread {
//        int index;
//        List<Condition> conditions;
//
//        public ConditionWorker(int index, List<Condition> conditions) {
//            this.index = index;
//            this.conditions = conditions;
//        }
//
//        @Override
//        public void run() {
//            // 当在index的时候
//            lock.lock();
//            try {
//                while (count < 100) {
//                    if (count % 2 != index) {
//                        // 该等待，直到被唤醒
////                        System.out.println("Thread-" + index + "-wait-" + count);
//                        conditions.get(index).await();
//                    }
//                    System.out.println("Thread-" + index + "打印" + count);
//                    count++;
//                    // 唤醒下一个
//                    conditions.get((index + 1) % 2).signal();
//                }
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            } finally {
//                lock.unlock();
//            }
//        }
//    }



//    public List<String> randomoker(){
//
//        // 54张分成4组
//        List<String> colorList = Lists.newArrayList("梅花","方块","黑桃","红桃");
//        List<String> numsList = Lists.newArrayList("A","2","3","4","5","6","7","8","9");
//        List<String> poker = new ArrayList();
//        for(String color:colorList){
//            for(String nums:numsList){
//                poker.add(color + nums);
//            }
//        }
//        poker.add("大王");
//        poker.add("小王");
//
//        // 开始洗牌，共54张随机输出
//        Collections.shuffle(poker);
//
//        return poker;
//    }
//
//
//    public static void main(String[] args) {
//        Test test = new Test();
//        System.out.println(test.randomoker());
//
//    }
}
