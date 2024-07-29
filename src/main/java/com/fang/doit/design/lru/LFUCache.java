package com.fang.doit.design.lru;

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
     *  LFU（Least Frequently Used） and LRU 是常见的缓存替换算法
     *
     * 1、LRU是基于最近使用时间的缓存替换算法。它的基本思想是，当缓存空间不足时，优先淘汰最长时间未被访问的数据。
     *    LRU算法维护一个访问顺序链表（或双向链表），每次访问一个数据时，将其移动到链表的头部。当需要淘汰数据时，选择链表尾部的数据进行删除。
     *
     * 2、LFU则是基于访问频率的缓存替换算法。它的核心思想是，当缓存空间不足时，优先淘汰访问频率最低的数据。
     *    LFU算法维护一个频率计数器，用于记录每个数据被访问的次数。每次访问一个数据时，将对应数据的访问次数加1，当需要淘汰数据时，选择访问次数最低的数据进行删除。
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


    public LFUCache(int capacity) {

        // 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行



    }

    public int get(int key) {

        return 0;
    }

    public void put(int key, int value) {


    }


}
