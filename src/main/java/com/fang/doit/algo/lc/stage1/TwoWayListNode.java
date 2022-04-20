package com.fang.doit.algo.lc.stage1;

/**
 * 双向链表
 *
 * @author fangfeiyue
 * @Date 2020/12/21 7:54 下午
 */
public class TwoWayListNode {

    public int key, val;

    public TwoWayListNode pre, next;

    public TwoWayListNode() {
    }

    TwoWayListNode(int key, int val) {
        this.TwoWayListNode(key, val, null, null);
    }

    public void TwoWayListNode(int key, int val, TwoWayListNode pre, TwoWayListNode next) {
        this.key = key;
        this.val = val;
        this.pre = pre;
        this.next = next;
    }
}
