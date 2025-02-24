package com.fang.doit.algo.lc;


import java.util.*;

/**
 * @author : fangfeiyue
 * @version V1.0
 * @Project: doit
 * @Package com.fang.doit.algo.lc
 * @Description:
 * @date Date : 2025-02-24 17:43
 */
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
     * 输入： s = "DIIIDDDDI"
     * 输出： [2,1,3,4,5,9,8,7,6,10]
     *
     * @param s
     * @return
     */
    public static int[] findPermutation(String s) {
        int[] res = new int[s.length() + 1];

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


        return 0;
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
    public int findMaxConsecutiveOnes(int[] nums) {

        return 0;
    }


    /**
     * 239.给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧，你只可以看到在滑动窗口内的 k 个数字，滑动窗口每次只向右移动一位,返回滑动窗口中的最大值
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
        int[] ans = new int[n - k + 1];


        return ans;
    }


    /**
     * 76.给你一个字符串 s 、一个字符串 t，返回 s 中涵盖 t 所有字符的最小子串，如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 ""
     * <p>
     * s = "ADOBECODEBANC", t = "ABC" ==> "BANC"
     * s = "a", t = "aa" ==> ""
     *
     * @param s
     * @param t
     * @return
     */
    public String minWindow(String s, String t) {

        return null;
    }


}
