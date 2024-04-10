package com.fang.doit.algo.classes.list;

/**
 * created by fang on 2018/7/25/025 23:36
 */
public class MyArrayListTest {

    public static void main(String[] args) {

        /**
         创建静态内部类对象的一般形式为：  外部类类名.内部类类名 xxx = new 外部类类名.内部类类名()
         创建成员内部类对象的一般形式为：  外部类类名.内部类类名 xxx = 外部类对象名.new 内部类类名()
         */
       MyArrayList myArrayList = new MyArrayList();
//        MyArrayList.ArrayListIterator arrayListIterator = myArrayList.new MyArrayList.ArrayListIterator();
//
//        MyArrayList.A2 a2 = new MyArrayList.A2();

        myArrayList.test(2);


//        myArrayList.add("fang");
//        Iterator<String> iter = myArrayList.iterator();
//        while (iter.hasNext()) {
//            // String next = iter.next();
//            iter.remove();
//        }
//        System.out.println(".........." + JSON.toJSONString(myArrayList));
    }
}
