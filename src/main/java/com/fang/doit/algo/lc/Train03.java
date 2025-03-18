package com.fang.doit.algo.lc;

import com.fang.doit.design.lru.LRUCache;
import com.fang.doit.design.lru.TwoWayListNode;

import java.util.*;
import java.util.stream.Collectors;


public class Train03 {

    // 03.17 ~ 05.17 挑战 Mid*2

    /**
     * 22.
     *
     * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
     *
     *
     *
     * 示例 1：
     *
     * 输入：n = 3
     * 输出：["((()))","(()())","(())()","()(())","()()()"]
     * 示例 2：
     *
     * 输入：n = 1
     * 输出：["()"]
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {

        return null;
    }

    /**
     * 207. 你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
     * 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。
     *
     * 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
     * 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false
     *
     * 示例 1：
     *
     * 输入：numCourses = 2, prerequisites = [[1,0]]
     * 输出：true
     * 解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。
     * 示例 2：
     *
     * 输入：numCourses = 2, prerequisites = [[1,0],[0,1]]
     * 输出：false
     * 解释：总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0 ；并且学习课程 0 之前，你还应先完成课程 1 。这是不可能的。
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {

        return false;
    }


    /**
     * 208. Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。这一数据结构有相当多的应用情景，例如自动补全和拼写检查。
     *
     * 请你实现 Trie 类：
     *
     * Trie() 初始化前缀树对象。
     * void insert(String word) 向前缀树中插入字符串 word 。
     * boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false 。
     * boolean startsWith(String prefix) 如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true ；否则，返回 false 。
     *
     * 输入
     * ["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
     * [[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
     * 输出
     * [null, null, true, false, true, null, true]
     *
     * 解释
     * Trie trie = new Trie();
     * trie.insert("apple");
     * trie.search("apple");   // 返回 True
     * trie.search("app");     // 返回 False
     * trie.startsWith("app"); // 返回 True
     * trie.insert("app");
     * trie.search("app");     // 返回 True
     *
     */

    static class Trie {

        public Trie() {

        }

        public void insert(String word) {

        }

        public boolean search(String word) {

        }

        public boolean startsWith(String prefix) {

        }
    }


    /**
     *
     * 输入：
     * ["MinStack","push","push","push","getMin","pop","top","getMin"]
     * [[],[-2],[0],[-3],[],[],[],[]]
     *
     * 输出：
     * [null,null,null,null,-3,null,0,-2]
     *
     * 解释：
     * MinStack minStack = new MinStack();
     * minStack.push(-2);
     * minStack.push(0);
     * minStack.push(-3);
     * minStack.getMin();   --> 返回 -3.
     * minStack.pop();
     * minStack.top();      --> 返回 0.
     * minStack.getMin();   --> 返回 -2.
     *
     */

    static class MinStack {

        public MinStack() {

        }

        public void push(int val) {

        }

        public void pop() {

            //删除堆栈顶部的元素。
        }

        public int top() {

            //  获取堆栈顶部的元素。
            return 0;
        }

        public int getMin() {
            return 0;
        }
    }

    /**
     * 138.给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
     * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题
     * <p>
     * nums = [100,4,200,1,3,2] -> 最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4
     * nums = [0,3,7,2,5,8,4,6,0,1] -> 9
     *
     * @param nums
     * @return
     */

    public int longestConsecutive(int[] nums) {
        // TODO 执行效率超时
        if(nums.length ==0){
            return 0;
        }
        List<Integer> list = Arrays.stream(nums).boxed().collect(Collectors.toList());
        int max = 1;
        for (int i = 0; i < nums.length; i++) {
            int k = 1;
            while (list.contains(++nums[i])) {
                k++;
            }
            max = Math.max(k, max);
        }
        return max;
    }


// 139.给你一个字符串 s 和 一个字符串列表 wordDict 作为字典。如果可以利用字典中出现的一个或多个单词拼接出 s 则返回 true。
// 注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。
//
//    输入: s = "leetcode", wordDict = ["leet", "code"]
//    输出: true
//    解释: 返回 true 因为 "leetcode" 可以由 "leet" 和 "code" 拼接成。
//
//    输入: s = "applepenapple", wordDict = ["apple", "pen"]
//    输出: true
//    解释: 返回 true 因为 "applepenapple" 可以由 "apple" "pen" "apple" 拼接成。
//    注意，你可以重复使用字典中的单词。
//
//    输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
//    输出: false

    public boolean wordBreakFlag = false;

    public boolean wordBreak(String s, List<String> wordDict) {
        // 不错的题，通过深度一一遍历枚举包含的字符
        wordBreakDFS(0, s, wordDict);
        return wordBreakFlag;
    }

    private void wordBreakDFS(int i, String s, List<String> wordDict) {
        if (i == s.length()) {
            wordBreakFlag = true;
            return;
        }
        for (int k = i + 1; k < s.length(); k++) {
            String word = s.substring(i, k);
            if (wordDict.contains(word)) {
                wordBreakDFS(k, s, wordDict);
            }
        }

    }


//    146.请你设计并实现一个满足  LRU (最近最少使用) 缓存 约束的数据结构
//    函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。

    /**
     * @see com.fang.doit.design.lru.LRUCache
     */

    static class LRUCache {

        // HashMap 保证 get 查询
        private HashMap<Integer, Integer> map;

        //
        private int capacity, size;

        // 双向链表（或TreeMap）保证 get & put 在 O(1) 更新其位置
        TwoWayListNode head, tail;

        public LRUCache(int capacity) {

            // 以 正整数 作为容量 capacity 初始化 LRU 缓存

        }

        public int get(int key) {

            //  如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1

            return 0;
        }

        public void put(int key, int value) {

            // 如果关键字 key 已经存在，则变更其数据值 value ；如果不存在，则向缓存中插入该组 key-value
            // 如果插入操作导致关键字数量超过 capacity ，则应该 逐出 最久未使用的关键字

        }
    }

    /**
     * 484.寻找排列：由范围 [1,n] 内所有整数组成的 n 个整数的排列 perm 可以表示为长度为 n - 1 的字符串 s ，其中:
     * 如果 perm[i] < perm[i + 1] ，那么 s[i] == 'I'，如果 perm[i] > perm[i + 1] ，那么 s[i] == 'D'，给定一个字符串 s ，重构字典序上最小的排列 perm 并返回它
     * <p>
     * 输入： s = "I"
     * 输出： [1,2]
     * 解释： [1,2] 是唯一合法的可以生成秘密签名 "I" 的特定串，数字 1 和 2 构成递增关系
     * <p>
     * 输入： s = "DIIIIDDDI"
     * 输出： [2,1,3,4,5,9,8,7,6,10]
     *
     * @param s
     * @return
     */
    public static int[] findPermutation(String s) {
        int[] res = new int[s.length() + 1];
        int point = 0;
        Stack<Integer> stack = new Stack<>();
        // 从后一个开始，可以解决D的时候两个数字问题
        for (int i = 1; i <= s.length(); i++) {
            stack.push(i);
            if (s.charAt(i - 1) == 'I') {
                while (!stack.isEmpty()) {
                    res[point++] = stack.pop();
                }
            }
        }
        // 数字多一位
        stack.push(s.length() + 1);
        // 清空堆栈
        while (!stack.isEmpty()) {
            res[point++] = stack.pop();
        }
        return res;
    }


    /**
     * 41.给你一个未排序的整数数组 nums ，找出其中没有出现的最小正整数，实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案
     * <p>
     * [2,4,0,5] ==> 1
     * [-1, 2, 4, 1] ==> 4
     *
     * @param nums
     * @return
     */
    public static int firstMissingPositive(int[] nums) {
        int n = nums.length;
        // 先剪除负数
        for (int i = 0; i < n; ++i) {
            if (nums[i] <= 0) {
                nums[i] = n + 1;
            }
        }

        for (int i = 0; i < n; ++i) {
            int num = Math.abs(nums[i]);
            if (num <= n) {
                nums[num - 1] = -Math.abs(nums[num - 1]);
            }
        }
        for (int i = 0; i < n; ++i) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }
        return n + 1;
    }


    /**
     * 487. 给定一个二进制数组 nums ，如果最多可以翻转一个 0 ，则返回数组中连续 1 的最大个数
     * <p>
     * 输入：nums = [1,0,1,1,0] ==》4 翻转第一个 0 可以得到最长的连续 1
     * 输入：nums = [1,0,1,1,0,1] ==》4
     *
     * @param nums
     * @return
     */
    public static int findMaxConsecutiveOnes(int[] nums) {
        // 最长连续个数，保证窗口内最多只有一个0的时候最大字符串
        int left = 0, right = 0, position = -1,len = nums.length, max = 0;
        while (right < len) {
            if (nums[right] == 0) {
                if (position > 0) {
                    // 非第一次进 0,则left更新位置从上一个0的后一位开始
                    left = position;
                    left++;
                }
                // 更新的0位置
                position = right;
            }
            max = Math.max(max, right - left + 1);
            right++;
        }
        return max;
    }

//    public static void main(String[] args) {
//        System.out.println(findMaxConsecutiveOnes(new int[]{1, 0, 1, 1, 0}));
//    }


    /**
     * 239.给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧，
     * 你只可以看到在滑动窗口内的 k 个数字，滑动窗口每次只向右移动一位,返回滑动窗口中的最大值
     * <p>
     * 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3 ==》[3,3,5,5,6,7]
     * 输入：nums = [1], k = 1
     * 输出：[1]
     *
     * @param nums
     * @param k
     * @return
     */
    public static int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;

        // 窗口内的最大值，每次保持窗口递增即可
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < k; i++) {
            // 这里注意First表示先入的队 Last表示后进的队
            if (!queue.isEmpty() && queue.peekLast() < nums[i]) {
                queue.pollLast();
            }
            queue.offerLast(i);
        }

        int[] ans = new int[n - k + 1];
        ans[0] = nums[queue.peekFirst()];

        for (int i = k + 1; i < nums.length; i++) {
            // 进：从尾部
            if (!queue.isEmpty() && queue.peekLast() < nums[i]) {
                queue.pollLast();
            }
            queue.offerLast(nums[i]);
            // 出：在头部大的
            if (!queue.isEmpty() && queue.peekFirst() <= i - k) {
                queue.pollFirst();
            }
            ans[i - k + 1] = nums[queue.peekFirst()];
        }
        return ans;
    }


    /**
     * 76.给你一个字符串 s 、一个字符串 t，返回 s 中涵盖 t 所有字符的最小子串，如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串""
     * <p>
     * s = "ADOBECODEBANC", t = "ABC" ==> "BANC"
     * s = "a", t = "aa" ==> ""
     *
     * @param s
     * @param t
     * @return
     */

    Map<Character, Integer> ori = new HashMap<>();
    Map<Character, Integer> cnt = new HashMap<>();

    public String minWindow(String s, String t) {

        // 滑动窗口，left 找到第一个满足条件的，right一直找到全部满足条件的
        int tLen = t.length();
        for (int i = 0; i < tLen; i++) {
            char c = t.charAt(i);
            ori.put(c, ori.getOrDefault(c, 0) + 1);
        }
        int l = 0, r = -1;
        int len = Integer.MAX_VALUE, ansL = -1, ansR = -1;
        int sLen = s.length();
        while (r < sLen) {
            ++r;
            if (r < sLen && ori.containsKey(s.charAt(r))) {
                cnt.put(s.charAt(r), cnt.getOrDefault(s.charAt(r), 0) + 1);
            }

            // 在满足覆盖的条件下，不断轮训直到 这点没想到!!!
            while (checkContains() && l <= r) {
                if (r - l + 1 < len) {
                    len = r - l + 1;
                    ansL = l;
                    ansR = l + len;
                }
                if (ori.containsKey(s.charAt(l))) {
                    cnt.put(s.charAt(l), cnt.getOrDefault(s.charAt(l), 0) - 1);
                }
                ++l;
            }
        }
        return ansL == -1 ? "" : s.substring(ansL, ansR);
    }

    private boolean checkContains() {
        Iterator<Map.Entry<Character, Integer>> iter = ori.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = iter.next();
            Character key = (Character) entry.getKey();
            Integer val = (Integer) entry.getValue();
            if (cnt.getOrDefault(key, 0) < val) {
                return false;
            }
        }
        return true;
    }


//    public static void main(String[] args) {
//        Train03 train03 = new Train03();
//        System.out.println(train03.minWindow("ADOBECODEBANC", "ABC"));
//    }

    /**
     * 402. 给你一个以字符串表示的非负整数 num 和 一个整数 k ，移除这个数中的 k 位数字，使得剩下的数字最小
     * <p>
     * num = "143219", k = 3 ==> 1225  移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219
     * num = "10200", k = 1 ==> "200" 移掉首位的 1 剩下的数字为 200. 注意输出不能有任何前导零
     *
     * @param num
     * @param k
     * @return
     */

    public String removeKdigit(String num, int k) {
        // 剩下的最小，保持递减，双端队列可以
        Deque<Character> deque = new ArrayDeque<>();
        for (int i = 0; i < num.length(); i++) {
            while (k > 0 && !deque.isEmpty() && deque.peekLast() > num.charAt(i)) {
                deque.removeLast();
                k--;
            }
            deque.offerLast(num.charAt(i));
        }

        // 如果此时 k > 0，则说明队列 deque 中的元素已经是单调递增了，只需将队尾元素移除 k 次
        for (int i = 0; i < k; i++) {
            deque.pollLast();
        }

        // 防止结果中出现前导 0，所以如果第一个元素为 0，则直接跳过
        boolean flag = true;
        StringBuilder res = new StringBuilder();
        while (!deque.isEmpty()) {
            Character c = deque.pollFirst();
            if (flag && c == '0') {
                continue;
            }
            flag = false;
            res.append(c);
        }
        return res.length() == 0 ? "0" : res.toString();
    }


}
