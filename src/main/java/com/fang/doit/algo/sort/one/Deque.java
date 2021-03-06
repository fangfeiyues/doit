package com.fang.doit.algo.sort.one;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author created by fang on 2019/10/27/027 18:59
 */
public class Deque<Item> implements Iterable<Item> {

    private Node first;

    private Node last;

    private Integer size;

    // construct an empty deque
    public Deque() {
        first = last = null;
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return (this.size == 0) || this.first == null || this.last == null;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("param is null");
        }
        Node node = new Node(item);
        if (isEmpty()) {
            this.first = this.last = node;
        } else {
            node.next = null;
            node.pre = this.first;
            this.first = node;
        }
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("param is null");
        }
        Node node = new Node(item);
        if (isEmpty()) {
            this.first = this.last = node;
        } else {
            node.pre = null;
            node.next = this.last;
            this.last = node;
            node.next.pre = node;
        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new IllegalArgumentException("size is empty");
        } else {
            Item item = this.first.value;

            Node nowFirst = this.first.pre;

            // only one
            if (nowFirst != null) {
                nowFirst.next = null;
            }

            this.first = nowFirst;

            size--;
            return item;
        }
    }


    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw new IllegalArgumentException("size is empty");
        } else {
            Item item = this.last.value;

            Node nowLast = this.last.next;
            if (nowLast != null) {
                nowLast.pre = null;
            }

            this.last = nowLast;
            size--;
            return item;
        }
    }

    // return an iterator over items in order from front to back
    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {

            @Override
            public boolean hasNext() {
                return (first != null);
            }

            @Override
            public Item next() {
                if (isEmpty()) {
                    throw new NoSuchElementException("there are no more items!");
                }

                Item item = first.value;
                first = first.pre;
                return item;
            }
        };
    }

    public static void main(String[] args) {
        Deque<String> deque = new Deque<String>();
        // 0.8, 0.0, 0.1, 0.0, 0.1, 0.0
        deque.addFirst("a");
        deque.addFirst("b");
        deque.addFirst("c");
        deque.addLast("d");
        deque.addLast("e");
        deque.removeFirst();
//        deque.removeLast();
        System.out.println(deque.size);
        Iterator<String> iterator = deque.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

    }


    private class Node {

        Node next;

        Node pre;

        Item value;

        public Node(Item item) {
            this.value = item;
        }

    }
}
