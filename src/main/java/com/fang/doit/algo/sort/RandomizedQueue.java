package com.fang.doit.algo.sort;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

/**
 *
 * @author created by fang on 2019/10/27/027 21:50
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    // array
    private Item[] itemArray;

    // current item index
    private int size;

    private int capacity = 16;

    // construct an empty randomized queue
    public RandomizedQueue() {
        itemArray = (Item[]) new Object[capacity];
        size = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("param is null");
        }
        if (capacity < size + 1) {
            resize(capacity * 2);
        }
        itemArray[size] = item;
        size++;
    }

    private void resize(int reCap) {
        Item[] tempArray = (Item[]) new Object[reCap];
        for (int i = 0; i < size; i++) {
            tempArray[i] = itemArray[i];
        }
        itemArray = tempArray;
    }

    // remove and return a random item
    public Item dequeue() {
        if (size == 0) {
            return null;
        }
        int r = StdRandom.uniform(0, size);
        Item item = itemArray[r];
        if (r == size - 1) {
            return item;
        }
        swap(itemArray, r, size - 1);
        itemArray[size - 1] = null;
        return item;

    }

    private void swap(Item[] array, int a, int b) {
        Item temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (size == 0) {
            return null;
        }
        return itemArray[StdRandom.uniform(0, size)];
    }

    // return an independent iterator over items in random order

    /**
     * must be mutually independent
     *
     * @return
     */
    @Override
    public Iterator<Item> iterator() {

        return new Iterator<Item>() {

            public int iter = size;

            @Override
            public boolean hasNext() {
                return iter > 0;
            }

            @Override
            public Item next() {
                Item item = itemArray[StdRandom.uniform(0, iter)];
                iter--;
                return item;
            }
        };
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        rq.enqueue("a");
        rq.enqueue("b");
        rq.enqueue("c");
        rq.enqueue("d");
        rq.enqueue("e");
        rq.enqueue("f");
        rq.enqueue("g");
        rq.dequeue();
        Iterator<String> iter1 = rq.iterator();
        Iterator<String> iter2 = rq.iterator();
        while (iter1.hasNext()) {
            System.out.print(iter1.next() + ",");
        }
        System.out.println();
        while (iter2.hasNext()) {
            System.out.print(iter2.next() + ",");
        }
        System.out.println();
    }

}
