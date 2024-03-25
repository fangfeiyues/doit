package com.fang.doit.algo.lc.top;

import com.fang.doit.algo.lc.stage1.TwoWayListNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 设计一个最近最少使用缓存，是否可以在 O(1) 时间复杂度内完成这两种操作get&put
 * （ 最近最少使用算法，是一种内存数据淘汰策略，使用常见是当内存不足时，需要淘汰最近最少使用的数据 ）
 *
 * @author fangfeiyue
 * @Date 2020/12/19 10:53 下午
 */
public class LRUCache {

    /**
     * 哈希表：保证get O(1)的时间复杂度
     * 双向链表：？？？
     */
    private Map<Integer, TwoWayListNode> cache;
    TwoWayListNode head, tail;
    private int capacity, size;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.cache = new HashMap<>();

        this.head = new TwoWayListNode();
        this.tail = new TwoWayListNode();
        head.next = tail;
        tail.pre = head;
    }

    /**
     * 获取：缓存获取保证O(1)时间复杂度，值加入到队尾（ -头 ---> 尾）
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
        // 1、自己不会已经是队尾
        if (tail.next == node) {
            return;
        }
        // 2、把自己从当前节点摘除（注意双向链表pre、next都要处理）
        if (null != node.pre && null != node.next) {
            TwoWayListNode prev = node.pre;
            prev.next = node.next;
            prev.next.pre = prev;
        }
        // 3、把自己插入到tail尾节点（注意双向链表pre、next都要处理）
        TwoWayListNode prev = tail.pre;
        prev.next = node;
        node.pre = prev;
        node.next = tail;
        tail.pre = node;
    }


    /**
     * 存放：加入到队尾，并对超过队列长度的队头移除
     *
     * @param key
     * @param value
     */
    public void put(int key, int value) {
        TwoWayListNode node = cache.get(key);
        if (null == node) {
            // 1、不存在节点，则新建一个
            node = new TwoWayListNode(key, value);
            if (size == capacity) {
                // 1.2、如果节点数量超过队列长，则头节点移除
                removeHead();
            } else {
                size++;
            }
        } else {
            // 2、存在则直接更新节点值
            node.val = value;
        }
        // 3、节点加入队尾
        appendAtTail(node);
        // 4、更新缓存
        cache.put(key, node);
    }


    private void removeHead() {
        // 移除缓存cache、链表头节点
        TwoWayListNode next = head.next ;
        next.pre = null;
        head.next = null;
        tail = next;
        cache.remove(head.key);
    }


}
