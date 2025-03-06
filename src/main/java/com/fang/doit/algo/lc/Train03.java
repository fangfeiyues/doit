package com.fang.doit.algo.lc;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.DelayQueue;

public class Train03 {

    // 2025-02-25 开始挑战 *2

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
                // 更新新的0位置
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


}
