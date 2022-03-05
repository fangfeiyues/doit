package com.fang.doit.algo.dst.linked;

/**
 * @author created by fang on 2020/5/17/017 20:28
 */
public class ListNode {

    public  int val;

    public ListNode next;

    public ListNode(int x) {
        val = x;
    }

    public ListNode(int x,ListNode next) {
        val = x;
        next = next;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public ListNode getNext() {
        return next;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }
}
