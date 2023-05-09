package com.fang.doit.algo.lc.test;

import com.fang.doit.algo.dst.linked.ListNode;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

/**
 * @author : fangfeiyue
 * @version V1.0
 * @Project: doit
 * @Package com.fang.doit.algo.lc.test
 * @Description: 2023.05月刷题记录
 * @date Date : 2023-05-07 4:21 下午
 */
public class LcTop202305 {

    /**
     *  ------------------- 每天写几道是保持代码手感最好的方式！！！ -----------------
     *  1、**** 每天2道，每周20（=2*5+5*2），每月80道
     *  2、***** 每行代码都要自己手写，哪怕理解思路读懂代码
     *
     *  ------------------------------------------------------
     */

    /**
     * 2.两数相加：两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字
     * eg. 输入：l1 = [2,4,3], l2 = [5,6,4] ==》 输出：[7,0,8] ； 342 + 465 = 807.
     *
     * @param l1
     * @param l2
     * @return 将两个数相加，并以相同形式返回一个表示和的链表
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 1、暴力求解：遍历两个链表求和后再反转结果，时间复杂度：O(n) 空间复杂度：O(1)
        if (l1 == null || l2 == null) {
            return null;
        }
        Stack<Integer> resultStack = new Stack<>();
        int last = 0;
        while (l1 != null || l2 != null) {
            int v1 = l1 != null ? l1.val : 0;
            int v2 = l2 != null ? l2.val : 0;
            int value = v1 + v2;
            value = value + last;
            last = value / 10;
            resultStack.add(value % 10);
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
        }
        if (last > 0) {
            resultStack.add(last);
        }
        // FIXME 加了堆栈之后这里的空间复杂度就增加不少了..
        ListNode next = new ListNode(resultStack.pop());
        while (!resultStack.isEmpty()) {
            next = new ListNode(resultStack.pop(), next);

        }
        return next;

        // 2、还没想到什么更好的解法..
    }

    /**
     * 给你一个字符串 s，找到 s 中最长的回文子串  如果字符串的反序与原始字符串相同，则该字符串称为回文字符串。
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        // 1、暴力求解：每个字符循环一遍比较 时间复杂度：O(n^2)
        return "";
    }

    public String longestPalindrome_dp(String s) {
        // 1、动态规划：P(i,j) = P(i+1,j−1) && Si == Sj
        int length = s.length();


        return "";
    }


    public static void main(String[] args) {


    }
}
