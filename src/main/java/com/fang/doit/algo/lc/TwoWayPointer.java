package com.fang.doit.algo.lc;

/**
 * 双向指针
 *
 * @author fangfeiyue
 * @Date 2021/1/3 10:34 下午
 */
public class TwoWayPointer {

    /**
     * 19删除链表倒数第N个节点。能尝试使用一趟扫描实现？
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 第一个节点先行第二个节点间隔n个节点后再行，前者到达底部即可。

        return head;
    }


    /**
     * 142环形列表：给定一个链表返回链表开始入环的第一个节点如果链表无环则返回null
     *
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
//         链表成环：
//         1. 维护一个HashMap到第一个重复的即可。 时间复杂度O(N)
//         2. 快慢指针并计算可知到第一个重复点举例即他们在环中相遇时候点到第一个重复点距离
        return null;
    }


}
