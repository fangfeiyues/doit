package com.fang.doit.algo.design;

import java.util.*;

/**
 * @author : fangfeiyue
 * @version V1.0
 * @Project: doit
 * @Package com.fang.doit.algo.design
 * @Description:
 * @date Date : 2022-02-23 10:44 PM
 */
public class DesignOnlyGetAndRemoveTest {
    /**
     * 设计一个支持在平均 时间复杂度 O(1) 下，执行以下操作的数据结构
     * https://leetcode-cn.com/problems/insert-delete-getrandom-o1/
     * /
     * <p>
     * <p>
     * <p>
     * /**
     * HashMap能提供常数时间的删除和插入（也包括get）但不能解决几个问题
     * 1.getRandom(); key没有索引需要在hash之后经历链表
     * 2.insert(); hash找到槽以后直接插入到最后，几乎O(1)
     * 3.remove(); 同get，这里有个技巧就是把随机的和最后的交换后移除
     */

    Map<Integer, Integer> dict;
    List<Integer> list;
    Random rand = new Random();

    /**
     * Initialize your data structure here.
     */
    public DesignOnlyGetAndRemoveTest() {
        dict = new HashMap();
        list = new ArrayList();
    }

    /**
     * Inserts a value to the set. Returns true if the set did not already contain the specified element.
     */
    public boolean insert(int val) {
        if (dict.containsKey(val)) {
            return false;
        }
        dict.put(val, list.size());
        list.add(list.size(), val);
        return true;
    }

    /**
     * Removes a value from the set. Returns true if the set contained the specified element.
     */
    public boolean remove(int val) {
        if (!dict.containsKey(val)) {
            return false;
        }

        // move the last element to the place idx of the element to delete
        int lastElement = list.get(list.size() - 1);
        int idx = dict.get(val);
        list.set(idx, lastElement);
        dict.put(lastElement, idx);
        // delete the last element 直接覆盖了
        list.remove(list.size() - 1);
        dict.remove(val);
        return true;
    }

    /**
     * Get a random element from the set.
     */
    public int getRandom() {
        // 这里如果用map.keySet()需要把所有的key元素扫一遍即O(n)
        return list.get(rand.nextInt(list.size()));
    }

}
