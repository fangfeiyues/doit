package com.fang.doit.algo.dst.linked;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author created by fang on 2020/5/17/017 20:28
 */
public class Solution {

    /**
     * ����һ������ÿk���ڵ�һ����з�ת�����㷵�ط�ת�������k��һ��������������ֵС�ڻ��������ĳ��ȣ�
     * ����ڵ���������k������������ô�뽫���ʣ��Ľڵ㱣��ԭ��˳��
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {

        // 1.ѹջ
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


        // 2.β�巨������β�巨����˼����,���ΰ�cur�Ƶ�tail����
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
//                // ��tail�����Ԫ��cur
//                cur.next = tail.next;
//                tail.next = cur;
//            }
//            // pre
//            pre = head1;
//            tail = head1;
//        }
//        return dummy.next;

        // 3.�ݹ鷨
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
