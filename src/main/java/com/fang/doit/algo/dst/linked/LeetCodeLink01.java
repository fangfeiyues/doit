package com.fang.doit.algo.dst.linked;

/**
 * @author by Feiyue on 2020/7/28 9:51 AM
 */
public class LeetCodeLink01 {

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
