package com.fang.doit.algo.dst.queue;

/**
 * created by fang on 2018/12/18/018 21:46
 */
public class CircularQueue {

    private String[] items;

    private int n = 0;

    private int head = 0;
    private int tail = 0;

    public CircularQueue(int capacity) {
        items = new String[capacity];
        n = capacity;
    }

    /**
     * 循环队列入队
     *
     * @param item
     */
    public boolean enqueue(String item) {
        if (head == (tail + 1) % n) {
            return false;
        }
        items[tail] = item;

        // 这里需要除以n求整，表示从头来过
        tail = (tail + 1) % n;
        return true;
    }

    /**
     * 出队
     *
     * @return
     */
    public String dequeue() {
        // 循环队列判断为空的条件
        if (tail == head) {
            return null;
        }
        String item = items[head];
        head = (head + 1) % n;
        return item;

    }

}
