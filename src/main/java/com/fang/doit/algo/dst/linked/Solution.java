package com.fang.doit.algo.dst.linked;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author created by fang on 2020/5/17/017 20:28
 */
public class Solution {

    /**
     * 给你一个链表，每k个节点一组进行翻转，请你返回翻转后的链表（k是一个正整数，它的值小于或等于链表的长度）
     * 如果节点总数不是k的整数倍，那么请将最后剩余的节点保持原有顺序
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {

        // 1.压栈
        Deque<ListNode> stack = new ArrayDeque<ListNode>();
        ListNode dummy = new ListNode(0);
//
//        ListNode p = dummy;
//        while (true) {
//            int count = 0;
//            ListNode tmp = head;
//            while (tmp != null && count < k) {
//                stack.add(tmp);
//                tmp = tmp.next;
//                count++;
//            }
//            if (count != k) {
//                p.next = head;
//                break;
//            }
//            while (!stack.isEmpty()) {
//                p.next = stack.pollLast();
//                p = p.next;
//            }
//            p.next = tmp;
//            head = tmp;
//        }
//        return dummy.next;


        // 2.尾插法：我们尾插法的意思就是,依次把cur移到tail后面
//        dummy.next = head;
//        ListNode pre = dummy;
//        ListNode tail = dummy;
//        while (true) {
//            int count = 0;
//            while (tail != null && count != k) {
//                count++;
//                tail = tail.next;
//            }
//            if (tail == null) {
//                break;
//            }
//            ListNode head1 = pre.next;
//            while (pre.next != tail) {
//                ListNode cur = pre.next;
//                pre.next = cur.next;
//                // 在tail后插入元素cur
//                cur.next = tail.next;
//                tail.next = cur;
//            }
//            // pre
//            pre = head1;
//            tail = head1;
//        }
//        return dummy.next;

        // 3.递归法
        ListNode cur = head;
        int count = 0;
        while (cur != null && count != k) {
            cur = cur.next;
            count++;
        }
        if (count == k) {
            cur = reverseKGroup(cur, k);
            while (count != 0) {
                count--;
                ListNode tmp = head.next;
                head.next = cur;
                cur = head;
                head = tmp;
            }
            head = cur;
        }
        return head;

    }


}
