package com.fang.doit.algo.classes.list;

import java.util.Iterator;

/**
 * 实现MyArrayList
 * 1.数组保存数据容量
 * 2.数组的扩容机制
 * 3.get和set方法
 * 4.提供size，isEmpty和clear等例程
 * 5.实现Iterator接口
 * <p>
 * created by fang on 2018/5/19/019 15:36
 */

public class MyArrayList<AnyType> implements Iterable<AnyType> {

    //
    private static final int DEFAULT_CAPACITY = 10;

    // 数组位置的指针
    private int theSize;

    // 存储对象数组总大小
    private AnyType[] theItems;


    public MyArrayList() {
        clear();
    }

    /**
     * 实现短例程
     */
    public void clear() {
        doClear();
    }

    /**
     * 清楚数组：1.初始指针的位置；2.初始数组大小
     */
    public void doClear() {
        theSize = 0;
        ensureCapcity(DEFAULT_CAPACITY);
    }

    public int size() {
        return theSize;
    }

    public boolean isEmpty() {
        return true;
    }

    public void trimToSize() {

    }

    public AnyType get(int idx) {
        return null;
    }

    public void set(int idx, AnyType anyType) {

    }

    /**
     * 扩容:
     */
    public void ensureCapcity(int newCapacity) {
        if (newCapacity < theSize) {
            throw new ArrayIndexOutOfBoundsException();
        }
        AnyType[] old = theItems;
        theItems = (AnyType[]) new Object[newCapacity];

        // old数组有JVM自己回收
        for (int i = 0; i < size(); i++) {
            theItems[i] = old[i];
        }
    }


    /**
     * 两个 add方法 + remove方法
     */
    public boolean add(AnyType x) {
        add(size(), x);
        return true;
    }

    public void add(int idx, AnyType anyType) {
        if (theItems.length == size()) {
            // 当前指针在数组最后即数组快满的时候进行扩容处理
            ensureCapcity(size() * 2 + 1);
        }

        for (int i = theSize; i > idx; i--) {
            theItems[i] = theItems[i - 1];
        }
        theItems[idx] = anyType;
        theSize++;
    }


    public AnyType remove(int idx) {
        return null;
    }


    @Override
    public Iterator<AnyType> iterator() {
        return new ArrayListIterator();
    }


    /**
     * 实现Iterator的成员内部类
     */
    protected class ArrayListIterator implements Iterator<AnyType> {

        private int current = 0;

//        private MyArrayList<AnyType> theList;
//
//        public ArrayListIterator(MyArrayList myArrayList) {
//            theList = myArrayList;
//        }

        @Override
        public boolean hasNext() {
            return current < size();
        }

        /**
         * 这里直接使用theItems[current++]是没有意义的因为不可能读取到theItems[]数据
         */
        @Override
        public AnyType next() {
            return theItems[current++];
        }

        /**
         * 当内部类需要访问外部类的相同方法时则 外部类.this.方法
         */
        @Override
        public void remove() {
            MyArrayList.this.remove(--current);
        }
    }


    static class A2 {
        public int test() {
            // return theSize;
            return 0;
        }

    }


    public void test() {
        String a = "a";
        class Inners {
            public void in() {
                System.out.println(a);
            }
        }
    }

    public void test(final int b) {
        int a = 10;
        new Thread() {
            @Override
            public void run() {
                System.out.println(a);
                System.out.println(b);
            }
        }.start();
    }

}
