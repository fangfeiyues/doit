package com.fang.doit.algo.classes.list;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;


/**
 * 1.LinkedList 包含
 * created by fang on 2018/5/20/020 18:09
 */
public class MyLinkedList<AnyType> implements Iterable<AnyType> {


    private int theSize;

    /**
     * 每次的add或remove都将更新modCount
     */
    private transient int modCount = 0;

    private Node<AnyType> beginMaker;

    private Node<AnyType> endMarker;

    /**
     * 节点
     *
     * @param <AnyType>
     */
    private static class Node<AnyType> {
        public Node(AnyType d, Node<AnyType> p, Node<AnyType> n) {
            data = d;
            prev = p;
            next = n;
        }

        public AnyType data;
        public Node<AnyType> prev;
        public Node<AnyType> next;
    }

    public MyLinkedList() {
        doClear();
    }

    private void doClear() {
        beginMaker = new Node<AnyType>(null, null, null);
        endMarker = new Node<AnyType>(null, beginMaker, null);
        beginMaker.next = endMarker;

        theSize = 0;
        modCount++;
    }


    /**
     * 添加：定位需要添加在链表中的位置size();
     * @param x
     * @return
     */
    public boolean add(AnyType x) {
        add(size(), x);
        return true;
    }

    public void add(int index, AnyType x) {
        addBefore(getNode(index, 0, size()), x);
    }

    /**
     * add和remove总体来说对前后节点都需要交代一下
     *
     * @param p
     * @param x
     */
    private void addBefore(Node<AnyType> p, AnyType x) {
        //新的节点
        Node<AnyType> node = new Node<AnyType>(x, p.prev, p);
        node.prev.next = node;
        p.prev = node;

        theSize++;
        modCount++;
    }

    private AnyType remove(Node<AnyType> p) {
        p.prev.next = p.next;
        p.next.prev = p.prev;
        theSize--;
        modCount++;
        return p.data;
    }

    public int size() {
        return theSize;
    }

    private Node<AnyType> getNode(int idx) {
        return getNode(idx, 0, size() - 1);
    }

    /**
     * 对于链表来说get()是一个o(N)的操作，利用二分法从前/后进行遍历
     *
     * @param idx
     * @param lower
     * @param upper
     * @return
     */
    private Node<AnyType> getNode(int idx, int lower, int upper) {
        Node<AnyType> p;
        if (idx < lower || idx > upper) {
            throw new IndexOutOfBoundsException();
        }

        if (idx < size() / 2) {
            p = beginMaker.next;
            for (int i = 0; i < idx; i++) {
                p = p.next;
            }
        } else {
            p = endMarker;
            for (int i = size(); i > idx; i--) {
                p = p.next;
            }
        }
        return p;
    }

    /**
     * 习题
     *
     * @param anyType
     * @return
     */
    public boolean contain(AnyType anyType) {
        if (indexOf(anyType) != -1) {
            return true;
        }
        return false;
    }

    public int indexOf(AnyType o) {
        int index = 0;
        if (o == null) {
            for (Node<AnyType> x = beginMaker; x != null; x = x.next) {
                if (x.data == null) {
                    return index;
                }
                index++;
            }
        } else {
            for (Node<AnyType> x = beginMaker; x != null; x = x.next) {
                if (o.equals(x.data)) {
                    return index;
                }
                index++;
            }
        }
        return -1;
    }

    @Override
    public Iterator<AnyType> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<AnyType> {

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public AnyType next() {
            return null;
        }

        @Override
        public void remove() {

        }
    }


    @Override
    public void forEach(Consumer<? super AnyType> action) {

    }

    @Override
    public Spliterator<AnyType> spliterator() {
        return null;
    }
}
