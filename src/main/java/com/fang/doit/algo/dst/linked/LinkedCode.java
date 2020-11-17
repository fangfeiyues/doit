package com.fang.doit.algo.dst.linked;

/**
 * created by fang on 2018/12/8/008 18:31
 */
public class LinkedCode {


    /**
     * 反转链表（非递归）: 主要是针对单向链表
     */
    public static void reverse(Node node) {
        Node pre = null;
        while (node.next != null) {
            Node next = node.next;
            node.next = pre;
            pre = node;
            node = next;
        }
    }

//    public static void main(String[] args) {
//        Node node = readyNode();
//        printAll(node);
//        reverse(node);
//        printAll(node);
//    }

    /**
     * 反转链表（递归）
     */
    public void reverseWithRecursion() {


    }


    /**
     * 链表中环的检测
     */
    public void checkRing() {


    }


    /**
     * 两个有序的链表合并
     */
    public void mergeSort() {


    }

    /**
     * 单向链表删除倒数第n个结点: 先顺序拿到第K个，同时另一个开始同时向后走
     */
    public void deleteLastK(Node node, int K) {
        int i = 0;
        while (node != null && i < K) {
            node = node.next;
        }
        if (node == null) {
            return;
        }


    }

    /**
     * 单向拿取链表的中间结点：一个取next另个取next.next
     */
    public static Node getMiddleNode(Node node) {
        Node slow = node;
        Node fast = node;
        while (slow.next != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            node = node.next;
        }
        return slow;
    }

    public static void main(String[] args) {
        Node node = readyNode();
        Node middle = getMiddleNode(node);
        System.out.println(middle.data);
    }

    static Node readyNode() {
        Node linkNode1 = new Node();
        linkNode1.data = 1;
        Node linkNode2 = new Node();
        linkNode2.data = 2;
        Node linkNode3 = new Node();
        linkNode3.data = 3;
        Node linkNode4 = new Node();
        linkNode4.data = 4;
        Node linkNode5 = new Node();
        linkNode5.data = 5;
        Node linkNode6 = new Node();
        linkNode6.data = 6;
        Node linkNode7 = new Node();
        linkNode7.data = 7;
        linkNode1.next = linkNode2;
        linkNode2.next = linkNode3;
        linkNode3.next = linkNode4;
        linkNode4.next = linkNode5;
        linkNode5.next = linkNode6;
        linkNode6.next = linkNode7;
        return linkNode1;
    }

    public static void printAll(Node node) {
        Node p = node;
        while (p != null) {
            System.out.println(p.data + " ");
            p = p.next;
        }
        System.out.println();
    }

}
