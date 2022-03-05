package com.fang.doit.algo.lc.page2;

import com.fang.doit.algo.dst.linked.ListNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author by Feiyue on 2020/7/28 9:51 AM
 */
public class LeetCodeLinked {

    /**
     * https://leetcode-cn.com/problems/palindrome-linked-list/ �����Ƿ��ǻ���
     * 1->2 false ; 1->2->2->1 true
     * <p>
     * �� O(n) ʱ�临�ӶȺ� O(1) �ռ临�ӶȽ������
     *
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        // ��һ���õ� leetCode ���� ������֪���������...

        // 1. �뵽�ĵ�һ�ַ�������������Ƚ�
//        if (head == null || head.next == null) {
//            return true;
//        }
//        ListNode reversal = reversal(head);

        // 2. �ŵ�����˫��Ƚ� �� ʱ�临�Ӷȣ�O(n)���ռ临�Ӷ� O(n) ��

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
        // 3.1 ������η�ת����֤O(1)������
        // 3.2 ǰ���αȽϣ������һ��ʧ��
        // 3.3 �ָ�����

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
     * ��ת
     *
     * @param head
     * @return
     */
    public static ListNode reversal(ListNode head) {
        // 1. ������ת���ٺ�֮ǰ�ıȽϣ� �ռ临�ӶȾͲ���O(1)�� ��
        ListNode pre = head;
        ListNode cur = pre.next;
        while (cur.next != null) {
            ListNode temp = cur.next;
            cur.setNext(pre);
            pre = cur;
            cur = temp;
        }
        // ��һ��&���һ�����⴦��
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


    /**
     * ����һ������ɾ������ĵ����� n ����㣬���ҷ��������ͷ���.
     * https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/
     * ���ף��ܳ���ʹ��һ��ɨ��ʵ����
     *
     * @param head
     * @param n
     * @return 2022-02-21
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (n == 0 || head == null) {
            return head;
        }
        // ˼·��ǰ�������ڵ����n��������
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
        //ɾ���ڵ� before.next
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
     * �����л�����ڽڵ�
     * https://leetcode-cn.com/problems/linked-list-cycle-ii/
     * ������ָ�룺���������㵽�绷����� == ��㵽�绷����롿
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
     * ������������ֻ�ǵ����ĸı�ڵ��ڲ���ֵ��������Ҫʵ�ʵĽ��нڵ㽻����[1,2,3,4] --> [1,4,2,3]
     * https://leetcode-cn.com/problems/reorder-list/
     *
     * @param head 2022-02-21
     */
    public void reorderList(ListNode head) {

        // �����ռ�ŵ�HashMap��ʱ�临�Ӷ�O(n) �ռ临�Ӷ�O(n)

        // ��벿�ַ�ת����ƴ��. ʱ�临�Ӷ�O(n) �ռ临�Ӷ�O(1)

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
     * ������ģ����2-3�м�Ͽ������Ϲ���
     * �����ѵ㣺�����ں�=�����裬��˼��ǰ�ߵ�ֵ����Ϊ���ߡ�curr.next = pre ���ǰ�pre�����curr.next��ônextֵ����pre
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
     * 1721�����������еĽڵ�
     * ���� ���������� k ���ڵ�͵����� k ���ڵ��ֵ�󣬷��������ͷ�ڵ㣨���� �� 1 ��ʼ����
     * ������˫ָ�롿��ô�ҵ�������k���ڵ㣿����˫ָ��
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode swapNodes(ListNode head, int k) {
        ListNode left = head;
        ListNode right = head;
        // ������k���ڵ�
        for (int i = 1; i < k; i++) {
            left = left.next;
        }
        ListNode cur = left;
        while (cur.next != null) {
            right = right.next;
            cur = cur.next;
        }
        // �������������ڵ��ֵ
        int m = right.val;
        right.val = left.val;
        left.val = m;
        return head;
    }

    /**
     * 1669���ϲ������������㽫 list1 ���±�� a �� b ��ȫ���ڵ㶼ɾ��������list2 ���ڱ�ɾ���ڵ��λ��
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
