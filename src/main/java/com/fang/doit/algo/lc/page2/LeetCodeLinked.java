package com.fang.doit.algo.lc.page2;

import com.fang.doit.algo.dst.linked.ListNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author by Feiyue on 2020/7/28 9:51 AM
 */
public class LeetCodeLinked {

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

    public LeetCodeLinked(int size) {
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
        if (head == null) {
            return;
        }
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


    /**
     * 给定一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点.
     * https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/
     * 进阶：能尝试使用一趟扫描实现吗？
     *
     * @param head
     * @param n
     * @return 2022-02-21
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (n == 0 || head == null) {
            return head;
        }
        // 思路：前后两个节点相差n个数即可
        ListNode before = head;
        ListNode after = head;
        while (after.next != null) {
            after = after.next;
            if (n == 0) {
                before = before.next;
            }
            if (n > 0) {
                n--;
            }
        }
        //删除节点 before.next
        ListNode delete = before.next;
        if (delete == null) {
            return null;
        }
        if (delete.next != null) {
            before.next = delete.next;
        } else {
            before.next = null;
        }
        return head;
    }

    public ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode first = head;
        ListNode second = dummy;
        for (int i = 0; i < n; ++i) {
            first = first.next;
        }
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        ListNode ans = dummy.next;
        return ans;
    }


    /**
     * 链表中环的入口节点
     * https://leetcode-cn.com/problems/linked-list-cycle-ii/
     * 【快慢指针：正向相遇点到如环点距离 == 起点到如环点距离】
     *
     * @param head
     * @return 2022-02-21
     */
    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode slow = head, fast = head;
        while (fast != null) {
            slow = slow.next;
            if (fast.next != null) {
                fast = fast.next.next;
            } else {
                return null;
            }
            if (fast == slow) {
                ListNode ptr = head;
                while (ptr != slow) {
                    ptr = ptr.next;
                    slow = slow.next;
                }
                return ptr;
            }
        }
        return null;
    }


    /**
     * 重排链表，不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。[1,2,3,4] --> [1,4,2,3]
     * https://leetcode-cn.com/problems/reorder-list/
     *
     * @param head 2022-02-21
     */
    public void reorderList(ListNode head) {

        // 牺牲空间放到HashMap，时间复杂度O(n) 空间复杂度O(n)

        // 后半部分反转，再拼接. 时间复杂度O(n) 空间复杂度O(1)

    }

    private ListNode findMiddleNode(ListNode head) {
        ListNode slow = head;
        ListNode quick = head;
        while (quick.next != null && quick.next.next != null) {
            slow = slow.next;
            quick = quick.next.next;
        }
        return slow;
    }

    /**
     * 1 <-- 2(pre)   3(curr) --> 4 --> 5
     * 解题核心：理解2-3中间断开又连上过程
     * 解题难点：理解等于号=：赋予，意思是前者的值重置为后者。curr.next = pre 就是把pre赋予给curr.next那么next值就是pre
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        ListNode curr = head;
        ListNode pre = null;
        while (curr != null) {
            ListNode tempNext = curr.next;
            curr.next = pre;
            pre = curr;
            curr = tempNext;
        }
        return pre;
    }

    /**
     * 1721：交换链表中的节点
     * 交换 链表正数第 k 个节点和倒数第 k 个节点的值后，返回链表的头节点（链表 从 1 开始索引
     * 【快慢双指针】怎么找到倒数第k个节点？快慢双指针
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode swapNodes(ListNode head, int k) {
        ListNode left = head;
        ListNode right = head;
        // 倒数第k个节点
        for (int i = 1; i < k; i++) {
            left = left.next;
        }
        ListNode cur = left;
        while (cur.next != null) {
            right = right.next;
            cur = cur.next;
        }
        // 交换左右两个节点的值
        int m = right.val;
        right.val = left.val;
        left.val = m;
        return head;
    }

    /**
     * 1669：合并两个链表，请你将 list1 中下标从 a 到 b 的全部节点都删除，并将list2 接在被删除节点的位置
     *
     * @param list1
     * @param a
     * @param b
     * @param list2
     * @return
     */
    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        int index = 1;
        ListNode node = list1;
        while (node != null) {
            ListNode start = node.next;
            if (index >= a) {

            }
            index++;
        }
        return null;
    }

    public static void main(String[] args) {
//        head = [1,2,3,4,5], n = 2
        ListNode head5 = new ListNode(5);
        ListNode head4 = new ListNode(4);
        head4.setNext(head5);
        ListNode head3 = new ListNode(3);
        head3.setNext(head4);
        ListNode head2 = new ListNode(2);
        head2.setNext(head3);
        ListNode head1 = new ListNode(1);
//        head1.setNext(head2);
        printChain(removeNthFromEnd(head1, 1));
    }

}
