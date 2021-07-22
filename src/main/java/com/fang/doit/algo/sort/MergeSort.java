package com.fang.doit.algo.sort;

import com.fang.doit.algo.dst.linked.ListNode;

/**
 * created by fang on 2018/12/17/017 21:18
 * 归并排序：先把数组从中间分成前后两部分然后对前后部分分别排序再将排序好的重新组合在一起。这用到了分治的思想即分而治之，将一个大问题分解为多个小问题来解决
 * 相同的两个数可以保持稳定的相对位置
 * 时间复杂度：T(n) = 2*T(n/2) + K   ===>  任何情况下都是 O(nlogn)
 * 空间复杂度：
 */
public class MergeSort {


    /**
     * 23:合并K个升序链表。给你一个链表数组每个链表都已经按升序排列，请你将所有链表合并到一个升序链表中，返回合并后的链表
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {

        return null;
    }

}
