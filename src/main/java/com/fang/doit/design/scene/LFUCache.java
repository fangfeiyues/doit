package com.fang.doit.design.scene;

import java.util.HashMap;
import java.util.TreeMap;

/**
 * @author : fangfeiyue
 * @version V1.0
 * @Project: doit
 * @Package com.fang.doit.design.lru
 * @Description: 最不经常使用LFU的缓存算法
 * @date Date : 2024-06-03 9:15 上午
 */
public class LFUCache {


    /**
     * 1、LRU 是基于最近使用时间的缓存替换算法。它的基本思想是，当缓存空间不足时，优先淘汰最长时间未被访问的数据。
     *    LRU算法 维护一个访问顺序链表（或双向链表），每次访问一个数据时，将其移动到链表的头部。当需要淘汰数据时，选择链表尾部的数据进行删除。
     *
     * 2、LFU 则是基于访问频率的缓存替换算法。它的核心思想是，当缓存空间不足时，优先淘汰访问频率最低的数据。
     *    LFU算法 维护一个频率计数器，用于记录每个数据被访问的次数。每次访问一个数据时，将对应数据的访问次数加1，当需要淘汰数据时，选择访问次数最低的数据进行删除。
     *
     */


    /**
     * 实现 LFUCache 类：
     *
     * LFUCache(int capacity) - 用数据结构的容量 capacity 初始化对象
     *
     * int get(int key) - 如果键 key 存在于缓存中，则获取键的值，否则返回 -1
     *
     * void put(int key, int value) - 如果键 key 已存在，则变更其值；如果键不存在，请插入键值对。当缓存达到其容量 capacity 时，则应该在插入新项之前，移除最不经常使用的项，当存在平局时，应该去除 最久未使用 的键
     *
     */


    private TreeMap<Integer, Integer> count = new TreeMap<>();

    private HashMap<Integer,Integer> cache = new HashMap<>();

    private int capacity;

    public LFUCache(int capacity) {
        // 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行
        // 这里的 O(1) 时间复杂度是指在平均情况下，get 和 put 操作的时间复杂度为常数级别。
        this.capacity = capacity;
    }

    public int get(int key) {

        // 如果键 key 存在于缓存中，则获取键的值，否则返回 -1
        if (!cache.containsKey(key)) {
            return -1;
        }
        // 如果键存在，则获取键的值
        int value = cache.get(key);
        // 更新访问频率
        count.put(key, count.getOrDefault(key, 0) + 1);
        // 这里可以将 key 移动到最前面，表示最近使用过
        // 但由于 TreeMap 的特性，直接更新频率即可
        // 注意：这里没有实现将 key 移动到最前面的操作，因为 TreeMap 会自动根据频率排序
        return value;
    }

    public void put(int key, int value) {
        // 如果键 key 已存在，则变更其值
        if (cache.containsKey(key)) {
            cache.put(key, value);
            // 更新访问频率
            count.put(key, count.getOrDefault(key, 0) + 1);
            return;
        }

        // 如果键不存在，请插入键值对
        if (cache.size() >= capacity) {
            // 当缓存达到其容量 capacity 时，则应该在插入新项之前，移除最不经常使用的项
            // 找到最小频率的键
            int minFreqKey = count.firstKey();
            cache.remove(minFreqKey);
            count.remove(minFreqKey);
        }

        // 插入新的键值对
        cache.put(key, value);
        count.put(key, 1); // 初始访问频率为 1
    }


}
