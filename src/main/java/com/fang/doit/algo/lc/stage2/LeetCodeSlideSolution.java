package com.fang.doit.algo.lc.stage2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author : fangfeiyue
 * @version V1.0
 * @Project: doit
 * @Package com.fang.doit.algo.slide
 * @Description:
 * @date Date : 2022-02-19 3:33 PM
 */
public class LeetCodeSlideSolution {

    /**
     * 给定一个正整数数组nums和整数 k ，请找出该数组内乘积小于 k 的连续的子数组的个数（相邻）
     * https://leetcode-cn.com/problems/subarray-product-less-than-k
     *
     * @param nums
     * @param k
     * @return
     */
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        // 暴力求解 时间复杂度O(n^2)

        // 滑动窗口 时间复杂度O(n)
        return numSubarrayProductLessThanKForSlide(nums, k);
    }

    /**
     * 跟下面相加的题目是一个意思：
     * 1. start到end范围内的数，是满足相乘结果最小数组结果
     * 2. 当结果不满足了则继续 start+1向前推进，且 sum/nums[start] 表示新的一轮开始
     *
     * @param nums
     * @param k
     * @return
     */
    public int numSubarrayProductLessThanKForSlide(int[] nums, int k) {
        if (k <= 1) {
            return 0;
        }
        int r = 0, l = 0;
        int ans = 0;
        int sum = 1;
        while (l < nums.length) {
            sum = sum * nums[r];
            if (sum >= k) {
                sum = sum / nums[l];
                l++;
            }
            ans = ans + (l - r + 1);
            l++;
        }
        return ans;
    }

    /**
     * 187 重复的DNA序列: https://leetcode-cn.com/problems/repeated-dna-sequences/
     *
     * @param s
     * @return 返回所有在 DNA 分子中出现不止一次的 长度为 10 的序列(子字符串)
     */
    public List<String> findRepeatedDnaSequences(String s) {

        // 滑动窗口穷举，时间复杂度：O(N*L)即每个字符理论上都会被判断10遍

        // FIXME slide=10的窗口滑动，问题出在怎么比较每个窗口的字符串是否一样？位运算

        return null;
    }


    /**
     * 209：给定一个含有 n 个正整数的数组和一个正整数 target
     * 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr]
     * 【滑动窗口】
     * @param target
     * @param nums
     * @return 返回其长度
     */
    public int minSubArrayLen(int target, int[] nums) {

        // 暴力法：对于每个nums开始的下标找到其连续最小大于target的数组，这里不确定滑动数组大小故O(n^2)

        // 通过滑动窗口start和end动态向前，时间复杂度O(n)
        return minSubArrayLenBySlide(target, nums);
    }

    public static int minSubArrayLenBySlide(int target, int[] nums) {
        int l = 0, r = 0;
        int ans = Integer.MAX_VALUE;
        int sum = nums[l];
        while (l < nums.length) {
            while (sum < target && r < nums.length - 1) {
                r = r + 1;
                sum = sum + nums[r];
            }
            if (sum >= target) {
                int length = r - l + 1;
                if (length < ans) {
                    ans = length;
                }
            }
            sum = sum - nums[l];
            l = l + 1;
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }


    /**
     * 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的某个变位词
     * https://leetcode-cn.com/problems/permutation-in-string/Related
     * 【滑动窗口】
     *
     * @param s1
     * @param s2
     * @return 2022-02-20
     */
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }
        // 一一收集变动词太多？？？滑动窗口
        // FIXME 打开思维：当si出现的字符个数和s2对应数组出现个数一样的时候说明符合要求！
        return checkInclusionBySlide(s1, s2);
    }

    /**
     * 要打开思路...
     *
     * @param s1
     * @param s2
     * @return
     */
    public boolean checkInclusionBySlide(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        if (n > m) {
            return false;
        }
        int[] cnt1 = new int[26];
        int[] cnt2 = new int[26];
        for (int i = 0; i < n; ++i) {
            ++cnt1[s1.charAt(i) - 'a'];
            ++cnt2[s2.charAt(i) - 'a'];
        }
        if (Arrays.equals(cnt1, cnt2)) {
            return true;
        }
        for (int i = n; i < m; ++i) {
            // 进来一个出去一个
            ++cnt2[s2.charAt(i) - 'a'];
            --cnt2[s2.charAt(i - n) - 'a'];
            if (Arrays.equals(cnt1, cnt2)) {
                return true;
            }
        }
        return false;
    }


    /**
     * 给定两个字符串 s 和 p，找到 s 中所有 p 的 变位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序
     * https://leetcode-cn.com/problems/find-all-anagrams-in-a-string/
     * 【滑动窗口】如上
     *
     * @param s
     * @param p
     * @return 2022-02-20
     */
    public List<Integer> findAnagrams(String s, String p) {
        int n = s.length(), m = p.length();
        if (m > n) {
            return new ArrayList<>();
        }
        List<Integer> ans = new ArrayList<Integer>();
        int[] s1 = new int[26];
        int[] p1 = new int[26];
        for (int i = 0; i < m; i++) {
            ++p1[p.charAt(i) - 'a'];
            ++s1[s.charAt(i) - 'a'];
        }
        if (Arrays.equals(s1, p1)) {
            ans.add(0);
        }
        return null;
    }


    /**
     * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长连续子字符串 的长度
     * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
     * FIXME 待继续
     * @param s
     * @return 2022-02-20
     */
    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }
        int ans = 1;
        HashMap<Integer, Integer> hashMap = new HashMap<>(s.length());
        int l = 0, r = 1;
        hashMap.put(s.charAt(1) - 'a', l);
        while (r < s.length()) {
            int num = s.charAt(r) - 'a';
            if (hashMap.containsKey(num)) {
                Integer lastIndex = hashMap.get(num);
                ans = Math.max(ans, l - r);
                l = lastIndex + 1;
            } else {
                hashMap.put(num, r);
                ans++;
            }
            r++;
        }
        return ans;
    }

    public static void main(String[] args) {
//        int[] nums = {1, 1, 1, 1, 1, 1, 1, 1};
        String s = "au";
        System.out.println(lengthOfLongestSubstring(s));
    }


}
