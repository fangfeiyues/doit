package com.fang.doit.algo.lc;

import java.util.HashMap;
import java.util.Map;

/**
 * 设计一个最近最少使用缓存，是否可以在O(1)时间复杂度内完成这两种操作get&put
 *
 * @author fangfeiyue
 * @Date 2020/12/19 10:53 下午
 */
public class DesignLRUCache {

    private Map<Integer, TwoWayListNode> cache;
    private int capacity, size;
    TwoWayListNode head, tail;


    /**
     * Map + 队列
     * 链表-双向：
     * 查询？所以用Map;
     * 双向链表：可以删除时快速找到前置节点达到O(1)效果
     * 数组：删除节点时需要移动整体
     *
     * @param capacity
     */
    public DesignLRUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.cache = new HashMap<>();

        this.head = new TwoWayListNode();
        this.tail = new TwoWayListNode();
        head.next = tail;
        tail.pre = head;
    }

    /**
     * 获取缓存的值
     *
     * @param key
     * @return
     */
    public int get(int key) {
        TwoWayListNode node = cache.get(key);
        if (null == node) {
            return -1;
        }

        appendAtTail(node);
        return node.val;
    }

    private void appendAtTail(TwoWayListNode node) {
        // tail还能pre ???
        if (tail.next == node) {
            return;
        }

        if (null != node.pre && null != node.next) {
            // 最后一个删除：node.pre ; node.next
            TwoWayListNode prev = node.pre;
            prev.next = node.next;
            prev.next.pre = prev;
        }

        // 添加到尾巴 tail的pre加了个空的
        TwoWayListNode prev = tail.pre;
        prev.next = node;
        node.pre = prev;
        node.next = tail;
        tail.pre = node;
    }


    /**
     * 存放
     * 有点考验Array & Linked的基础
     *
     * @param key
     * @param value
     */
    public void put(int key, int value) {
        TwoWayListNode node = cache.get(key);
        if (null == node) {
            node = new TwoWayListNode(key, value);
            if (size == capacity) {
                // evict head node
                removeHead();
            } else {
                size++;
            }
        } else {
            // update value
            node.val = value;
        }
        appendAtTail(node);
        cache.put(key, node);
    }


    private void removeHead() {
//        cache.remove(head.pre.key);
//        head.next = dummy.next.next;
//        dummy.next.prev = dummy;
        cache.remove(head.pre.next);
//        head.pre.pre;
    }


}
