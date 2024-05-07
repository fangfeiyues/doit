package com.fang.doit.algo.design.lru;

/**
 * @author : fangfeiyue
 * @version V1.0
 * @Project: doit
 * @Package com.fang.doit.algo.design.lru
 * @Description:
 * @date Date : 2024-04-10 5:11 下午
 */

public class TwoWayListNode {

    public TwoWayListNode pre, next;

    public int key,val;

    public TwoWayListNode(int key, int val) {
        this.key = key;
        this.val = val;
    }

    public TwoWayListNode() {
    }


    public TwoWayListNode getPre() {
        return pre;
    }

    public void setPre(TwoWayListNode pre) {
        this.pre = pre;
    }

    public TwoWayListNode getNext() {
        return next;
    }

    public void setNext(TwoWayListNode next) {
        this.next = next;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }
}
