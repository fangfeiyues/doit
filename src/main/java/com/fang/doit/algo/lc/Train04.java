package com.fang.doit.algo.lc;


import com.fang.doit.algo.classes.linked.ListNode;

import java.util.*;

/**
 * @author : fangfeiyue
 * @version V1.0
 * @Project: doit
 * @Package com.fang.doit.algo.lc
 * @Description:
 * @date Date : 2025-05-16 10:01
 */
public class Train04 {


    //  ------- ！！！2025.05月～06月，一场漫长的战役，摆正心态、积极面对 ！！！------------


    /**
     * 36.有效的数独
     * 请你判断一个 9 x 9 的数独是否有效。只需要 根据以下规则 ，验证已经填入的数字是否有效即可。
     *
     * 数字 1-9 在每一行只能出现一次。
     * 数字 1-9 在每一列只能出现一次。
     * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图）
     *
     *
     *
     * @param board
     * @return
     */
    public boolean isValidSudoku(char[][] board) {

        return false;
    }

    /**
     * 30. 串联所有单词的子串
     *
     * 给定一个字符串 s 和一个字符串数组 words。 words 中所有字符串 长度相同。
     *
     *  s 中的 串联子串 是指一个包含  words 中所有字符串以任意顺序排列连接起来的子串。
     *
     * 例如，如果 words = ["ab","cd","ef"]， 那么 "abcdef"， "abefcd"，"cdabef"， "cdefab"，"efabcd"， 和 "efcdab" 都是串联子串。 "acdbef" 不是串联子串，因为他不是任何 words 排列的连接。
     * 返回所有串联子串在 s 中的开始索引。你可以以 任意顺序 返回答案
     *
     * 输入：s = "barfoothefoobarman", words = ["foo","bar"]
     * 输出：[0,9]
     * 解释：因为 words.length == 2 同时 words[i].length == 3，连接的子字符串的长度必须为 6。
     * 子串 "barfoo" 开始位置是 0。它是 words 中以 ["bar","foo"] 顺序排列的连接。
     * 子串 "foobar" 开始位置是 9。它是 words 中以 ["foo","bar"] 顺序排列的连接。
     * 输出顺序无关紧要。返回 [9,0] 也是可以的。
     *
     * @param s
     * @param words
     * @return
     */
    public List<Integer> findSubstring(String s, String[] words) {

        return null;
    }

    /**
     * 32.最长有效括号,给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
     * <p>
     * 输入：s = "(()"
     * 输出：2
     * 解释：最长有效括号子串是 "()"
     * <p>
     * 输入：s = ")()())"
     * 输出：4
     * 解释：最长有效括号子串是 "()()"
     *
     * @param s
     * @return
     */
    public static int longestValidParentheses(String s) {

        // 堆栈队列：从"("开始，栈道内的 "(" 得大于 ")"，但堆栈不好分段统计最长
        // 滑动窗口：窗口内满足条件的，也不知道下一个左出还是右进
        // 动态规划：dp[i] 代表以 i 结尾的最长有效括号长度

        // 1. 初始化 dp 数组
        int[] dp = new int[s.length()];
        int max = 0;
        // 2. 遍历字符串
        for (int i = 1; i < s.length(); i++) {
            // 3. 如果是右括号
            if (s.charAt(i) == ')') {

                // 4. 如果前一个是左括号，说明可以组成有效括号
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;

                } else if (s.charAt(i - 1) == ')') {
                    // 5. 如果前一个是右括号，说明需要判断前面 是否有左括号
                    // i - dp[i - 1]：是什么意思？
                    if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                        dp[i] = dp[i - 1] + (i >= dp[i - 1] + 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                    }
                }
                max = Math.max(max, dp[i]);
            }
        }
        // 6. 返回最大值
        return max;
    }

    public static void main(String[] args) {
        System.out.println(longestValidParentheses(")()()()"));
    }

    /**
     * 23.给你一个链表数组，每个链表都已经按升序排列，请你将所有链表合并到一个升序链表中，返回合并后的链表
     * <p>
     * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
     * 输出：[1,1,2,3,4,4,5,6]
     * 解释：链表数组如下：[1->4->5,1->3->4,2->6]  将它们合并到一个有序链表中得到 1->1->2->3->4->4->5->6
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        // 逐步合并两个链表即可、
        if (lists == null || lists.length == 0) {
            return null;
        }
        ListNode res = lists[0];
        for (int i = 1; i < lists.length; i++) {
            res = mergeNode(res, lists[i]);
        }
        return res;
    }

    private ListNode mergeNode(ListNode node1, ListNode node2) {
        // 重点在于，这里有3个标签，两个链表的以及合并后总链的
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (node1 != null && node2 != null) {
            if (node1.val < node2.val) {
                cur.next = node1;
                node1 = node1.next;
            } else {
                cur.next = node2;
                node2 = node2.next;
            }
            cur = cur.next;
        }
        if (node1 != null) {
            cur.next = node1;
        } else {
            cur.next = node2;
        }
        return dummy.next;
    }



    /**
     * 25.给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表
     * <p>
     * k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序
     * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换
     *
     * <p>
     * 输入：head = [1,2,3,4,5], k = 2  ==> 输出：[2,1,4,3,5]
     * 输入：head = [1,2,3,4,5], k = 3  ==> 输出：[3,2,1,4,5]
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        // 1. 先遍历链表，找到长度
        ListNode cur = head;
        int len = 0;
        while (cur != null) {
            len++;
            cur = cur.next;
        }

        // 2. 计算需要翻转的次数
        int count = len / k;

        // 3. 遍历翻转
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode end;
        ListNode start;
        for (int i = 0; i < count; i++) {
            // 4. 找到翻转的起始和结束节点
            start = pre.next;
            end = pre;
            for (int j = 0; j < k; j++) {
                end = end.next;
            }
            // 5. 翻转，end 的下一个 next 在翻转之后的 pre 就是 start 了
            ListNode next = end.next;
            end.next = null;
            pre.next = reverseNode(start);
            start.next = next;
            pre = start;
        }

        // 6. 返回头节点
        return dummy.next;
    }

    public ListNode reverseNode(ListNode start) {
        // 1 -> 2 -> 3 ==》 1 <- 2 <- 3 把箭头反着过来
        ListNode pre = null;
        ListNode cur = start;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

//    public static void main(String[] args) {
//
//        Train04 train = new Train04();
//        train.reverseNode(new ListNode(1,new ListNode(2,new ListNode(3,new ListNode(4,new ListNode(5)))))).print();
//
//    }


    /**
     * 325.给定一个数组 nums 和一个目标值 k，找到和等于 k 的最长连续子数组长度。如果不存在任意一个符合要求的子数组，则返回 0
     * <p>
     * nums = [1,-1,5,-2,3], k = 3 ==》4 [1,-1,5,-2]
     *
     * @param nums
     * @param k
     * @return
     */
    public int maxSubArrayLen(int[] nums, int k) {
        // 前缀和，如果两个点之和为k，那么之间数组即满足条件
        HashMap<Integer, Integer> sumMap = new HashMap<>();
        int sum = 0, max = 0;
        for (int i = 0; i < nums.length; i++) {
            sum = sum + nums[i];
            int num = sum - k;
            if (sumMap.containsKey(num)) {
                max = Math.max(max, Math.abs(i - sumMap.get(num))) + 1;
            }
            sumMap.put(sum, i);
        }
        return max;
    }

//    public static void main(String[] args) {
//        Train04 train04 = new Train04();
//        System.out.println(train04.maxSubArrayLen(new int[]{1, -1, 5, -2, 3}, 3));
//    }


    /**
     * 22. 括号生成，数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且有效的括号组合
     * <p>
     * 输入：n = 3
     * 输出：["((()))","(()())","(())()","()(())","()()()"]
     * <p>
     * 输入：n = 1
     * 输出：["()"]
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        generateParenthesisDFS(res, new StringBuilder(), 0, 0, n);
        return res;
    }

    private void generateParenthesisDFS(List<String> res, StringBuilder sb, int open, int close, int n) {
        if (sb.length() == n * 2) {
            res.add(sb.toString());
            return;
        }
        if (open < n) {
            sb.append("(");
            generateParenthesisDFS(res, sb, open + 1, close, n);
            sb.deleteCharAt(sb.length() - 1);
        }
        // 重点在于，这里 close 要小于 open，而不是 close 小于 n 否则会造成 close > open 的情况
        if (close < open) {
            sb.append(")");
            generateParenthesisDFS(res, sb, open, close + 1, n);
            sb.deleteCharAt(sb.length() - 1);
        }
    }


//    public static void main(String[] args) {
//        Train04 train04 = new Train04();
//        System.out.println(train04.generateParenthesis(3));
//    }

    /**
     * 395.给你一个字符串 s 和一个整数 k ，请你找出 s 中的最长子串，要求该子串中的每个字符出现次数都不少于 k
     * <p>
     * s = "aaabb", k = 3 ==》3  aaa
     * s = "ababbc", k = 2 ==》5  ababb
     *
     * @param s
     * @param k
     * @return
     */
    public static int longestSubstring(String s, int k) {

        // 滑动窗口不行，内进出数量不好控制

        // 切割术可行，找到不满足条件的字符左右切割

        if (s == null || s.length() == 0) {
            return 0;
        }
        Map<Character, Integer> counter = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            counter.put(s.charAt(i), counter.getOrDefault(s.charAt(i), 0) + 1);
        }

        int max = 0;
        for (Map.Entry<Character, Integer> entry : counter.entrySet()) {
            Integer count = entry.getValue();
            Character ch = entry.getKey();
            if (count < k) {
                for (String ss : s.split(ch.toString())) {
                    max = Math.max(longestSubstring(ss, k), max);
                }
                return max;
            }
        }
        return s.length();
    }

//    public static void main(String[] args) {
//        System.out.println(longestSubstring("ababbc",2));
//    }

    /**
     *  31. 整数数组的一个 排列  就是将其所有成员以序列或线性顺序排列。 指其整数的下一个字典序更大的排列
     *
     *  例如，arr = [1,2,3] 的下一个排列是 [1,3,2] 。
     *  类似地，arr = [2,3,1] 的下一个排列是 [3,1,2] 。
     *  而 arr = [3,2,1] 的下一个排列是 [1,2,3] ，因为 [3,2,1] 不存在一个字典序更大的排列。
     *  给你一个整数数组 nums ，找出 nums 的下一个排列。
     *
     *  必须 原地 修改，只允许使用额外常数空间。
     *
     *  输入：nums = [1,2,3]
     *  输出：[1,3,2]
     *  示例 2：
     *
     *  输入：nums = [3,2,1]
     *  输出：[1,2,3]
     *  示例 3：
     *
     *  输入：nums = [1,1,5]
     *  输出：[1,5,1]
     *
     *
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        // 1. 从后往前遍历，找到第一个升序对
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        // 2. 如果没有升序对，说明已经是最大排列，直接反转
        if (i < 0) {
            reverseNums(nums, 0, nums.length - 1);
            return;
        }
        // 3. 找到第一个比nums[i]大的数，交换
        int j = nums.length - 1;
        while (j >= 0 && nums[j] <= nums[i]) {
            j--;
        }
        swap(nums, i, j);
        // 4. 反转i+1到最后的元素（说明i+1后的都是递减的那么只要直接反转就行）
        reverseNums(nums, i + 1, nums.length - 1);
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void reverseNums(int[] nums, int i, int i1) {
        while (i < i1) {
            swap(nums, i++, i1--);
        }
    }


    /**
     * 66. 给定一个整数数组 nums ，返回 所有可能的子集（幂集）。
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> subsets(int[] nums) {
        // 加入空集合
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        res.add(list);

        for(int i =0;i<nums.length;i++){
            // 往后深度遍历数字，如[1],[1,2],[1,2,3],[1,2,3,4] [1,3],[1,3,4],[1,4] ..
            list = new ArrayList<>();
            list.add(nums[i]);
            res.add(list);
            subsetsDFS(nums,i,res,list);
        }
        return res;
    }

    private static void subsetsDFS(int[] nums, int i, List<List<Integer>> res, List<Integer> list) {
        if (i == nums.length - 1) {
            return;
        }
        for (int j = i + 1; j < nums.length; j++) {
            // 把每一次的遍历结果加入到结果集
            List<Integer> list1 = new ArrayList<>(list);
            list1.add(nums[j]);
            res.add(list1);
            subsetsDFS(nums, j, res, list1);
            // 移出上一轮的
//			list.remove(list.size() - 1);
        }

    }

//    public static void main(String[] args) {
//        Train04 train04 = new Train04();
//        train04.subsets(new int[]{1,2,3}).stream().forEach(System.out::println);
//    }



}
