package com.fang.doit.algo.lc.stage1;

import com.fang.doit.algo.dst.linked.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author fangfeiyue
 * @Date 2021/1/6 5:19 下午
 */
public class Linked17 {

    /**
     * 24：节点前后两两交换链表中的节点
     * TODO 递归的方式还是有点难理解..,但是应该能想到这种思路
     *
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        // 递归的方式不断向下交换
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = head.next;
        // 头节点交换后的下一个节点就是 下一次进行交换的
        head.next = swapPairs(newHead.next);
        newHead.next = head;
        return newHead;
    }

    /**
     * 142:环形链表，返回入环的第一个节点
     *
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        // Hash
        detectCycleByHash(head);

        // 快慢指针
        detectCycleBySlow(head);

        return null;
    }

    private ListNode detectCycleBySlow(ListNode head) {
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
            // 快慢节点相遇后再额外使用一个指针ptr指向head节点。它和slow节点同时后移那么即将会在入口处相遇
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


    private ListNode detectCycleByHash(ListNode head) {
        ListNode pos = head;
        Set<ListNode> visited = new HashSet<>();
        while (pos != null) {
            if (visited.contains(pos)) {
                return pos;
            } else {
                visited.add(pos);
            }
            pos = pos.next;
        }
        return null;
    }


    /**
     * 147:对链表进行插入排序（保持前部分是有序的后面节点不断比较插入到合适的位置）
     * 插入操作的时间复杂度是O(1)，但是找到插入位置节点的时间复杂度是0(n)所以总体时间复杂度是O(n^2)
     *
     * @param head
     * @return
     */
    public ListNode insertionSortList(ListNode head) {
        if (head == null) {
            return head;
        }
        // 哑节点，防止头节点之前有插入
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        // 有序排列的最后一个节点
        ListNode lastSorted = head, curr = head.next;
        // 当前要插入的节点
        while (curr != null) {
            // 大于排序的最大节点则直接插入其后面
            if (lastSorted.val <= curr.val) {
                lastSorted = lastSorted.next;
            } else {
                // 否则从头开始遍历 一直找到比当前大的
                ListNode prev = dummyHead;
                while (prev.next.val <= curr.val) {
                    prev = prev.next;
                }
                // 然后插入
                lastSorted.next = curr.next;
                curr.next = prev.next;
                prev.next = curr;
            }
            curr = lastSorted.next;
        }
        return dummyHead.next;
    }


    /**
     * 148:链表排序。要求时间复杂度在O(n log n) 这样时间复杂度的有快排，归并，堆排序
     *
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {

        // 快排思想

        // TODO 归并了解下
        sortList(head, null);

        return null;
    }

    /**
     * 自顶向下归并
     *
     * @param head
     * @param tail
     * @return
     */
    public ListNode sortList(ListNode head, ListNode tail) {
        if (head == null) {
            return head;
        }
        if (head.next == tail) {
            head.next = null;
            return head;
        }
        ListNode slow = head, fast = head;
        while (fast != tail) {
            slow = slow.next;
            fast = fast.next;
            if (fast != tail) {
                fast = fast.next;
            }
        }
        ListNode mid = slow;
        ListNode list1 = sortList(head, mid);
        ListNode list2 = sortList(mid, tail);
        ListNode sorted = merge(list1, list2);
        return sorted;
    }

    public ListNode merge(ListNode head1, ListNode head2) {
        ListNode dummyHead = new ListNode(0);
        ListNode temp = dummyHead, temp1 = head1, temp2 = head2;
        while (temp1 != null && temp2 != null) {
            if (temp1.val <= temp2.val) {
                temp.next = temp1;
                temp1 = temp1.next;
            } else {
                temp.next = temp2;
                temp2 = temp2.next;
            }
            temp = temp.next;
        }
        if (temp1 != null) {
            temp.next = temp1;
        } else if (temp2 != null) {
            temp.next = temp2;
        }
        return dummyHead.next;
    }

}
