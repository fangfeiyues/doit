package com.fang.doit.algo.classes.list;

/**
 * created by fang on 2018/7/25/025 23:36
 */
public class MyLinkedListTest {

    public static void main(String[] args) {
        MyLinkedList<String> myLinkedList = new MyLinkedList<String>();
        myLinkedList.add("f");
        myLinkedList.add("y");
        System.out.println(myLinkedList.contain("y"));
    }
}
