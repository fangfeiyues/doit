package com.fang.doit.algo.dst.linked;

import java.util.Objects;

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
        this.next = next;
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

    public static void print(ListNode node) {
        while (node != null) {
            System.out.println(node.val);
            node = node.next;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListNode listNode = (ListNode) o;
        return val == listNode.val && Objects.equals(next, listNode.next);
    }

    @Override
    public int hashCode() {
        return Objects.hash(val, next);
    }

    public void print() {
        System.out.println(val);
        while (next != null) {
            System.out.println(" " + next.val);
            next = next.next;
        }
    }
}
