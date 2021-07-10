package com.fang.doit.algo.dst.linked;

/**
 * @author by Feiyue on 2020/7/28 9:51 AM
 */
public class LeetCodeLink01 {

    /**
     * https://leetcode-cn.com/problems/palindrome-linked-list/ 链表是否是回文
     * 1->2 false ; 1->2->2->1 true
     * <p>
     * 用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题
     *
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        // 第一次拿到 leetCode 的题 根本不知道如何下手...

        // 1. 想到的第一种方法是两个链表比较
//        if (head == null || head.next == null) {
//            return true;
//        }
//        ListNode reversal = reversal(head);

        // 2. 放到数组双向比较 【 时间复杂度：O(n)；空间复杂度 O(n) 】

//        List<Integer> vals = new ArrayList<>();
//        ListNode currentNode = head;
//        while (currentNode != null) {
//            vals.add(currentNode.val);
//            currentNode = currentNode.next;
//        }
//
//        int front = 0;
//        int back = vals.size() - 1;
//        while (front < back) {
//            if (!vals.get(front).equals(vals.get(back))) {
//                return false;
//            }
//            front++;
//            back--;
//        }

        // 3.
        // 3.1 链表后半段反转，保证O(1)的性能
        // 3.2 前后半段比较，如果不一致失败
        // 3.3 恢复链表

        ListNode half = head;
        ListNode halfHalf = head;
        while (halfHalf.next.next != null) {
            half = half.next;
            halfHalf = halfHalf.next.next;
        }

        ListNode end;
        if (halfHalf.next != null) {
            end = halfHalf.next;
        } else {
            end = halfHalf;
        }

        reversal(half);

        while (head.next != null) {
            if (head.val != end.val) {
                return false;
            }
            head = head.next;
            end = end.next;
        }
        return true;
    }

    /**
     * 反转
     *
     * @param head
     * @return
     */
    public static ListNode reversal(ListNode head) {
        // 1. 把链表反转，再和之前的比较（ 空间复杂度就不是O(1)了 ）
        ListNode pre = head;
        ListNode cur = pre.next;
        while (cur.next != null) {
            ListNode temp = cur.next;
            cur.setNext(pre);
            pre = cur;
            cur = temp;
        }
        // 第一个&最后一个特殊处理
        head.setNext(null);
        cur.setNext(pre);

        return cur;
    }


    private ListNode head;

    public LeetCodeLink01(int size) {
        ListNode head = new ListNode(0);
        ListNode cur = head;
        for (int i = 1; i < size; i++) {
            ListNode tmp = new ListNode(i);
            cur.setNext(tmp);
            cur = tmp;
        }
        this.head = head;
    }

    public static void printChain(ListNode head) {
        StringBuilder sb = new StringBuilder();
        ListNode cur = head;
        sb.append(cur.getVal());
        while (null != cur.getNext()) {
            sb.append(" -> ");
            sb.append(cur.getNext().getVal());
            cur = cur.getNext();
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        LeetCodeLink01 leet = new LeetCodeLink01(10);
        printChain(reversal(leet.head));
    }

}
