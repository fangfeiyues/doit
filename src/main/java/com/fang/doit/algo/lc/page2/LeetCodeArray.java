package com.fang.doit.algo.lc.page2;

import java.util.HashMap;
import java.util.List;

/**
 * @author : fangfeiyue
 * @version V1.0
 * @Project: doit
 * @Package com.fang.doit.algo.dst.array
 * @Description:
 * @date Date : 2022-02-15 1:35 AM
 */
public class LeetCodeArray {

    /**
     * 只出现一次的数字：https://leetcode-cn.com/problems/single-number-ii/
     * 进阶：你的算法应该具有线性时间复杂度O(n)， 你可以不使用额外空间来实现吗？
     *
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {

        // 1. HashMap暴力解决法. 时间复杂度O(n),空间复杂度O(n)

        // 2. 位运算？

        return 0;
    }


    /**
     * https://leetcode-cn.com/problems/maximum-product-of-word-lengths/
     * 给定一个字符串数组 words，请计算当两个字符串 words[i] 和 words[j] 不包含相同字符时，它们长度的乘积的最大值。
     * 假设字符串中只包含英语的小写字母如果没有不包含相同字符的一对字符串，返回 0
     *
     * @param words
     * @return
     */
    public int maxProduct(String[] words) {

        // 1.HashMap<String,List<String>>暴力求解. 时间复杂度：O()

        // 2.位运算？

        return 0;
    }


    /**
     * https://leetcode-cn.com/problems/3sum/
     * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a ，b ，c ，使得 a + b + c = 0 ？
     * 请找出所有和为 0 且不重复 的三元组
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {


        return null;
    }


    /**
     * https://leetcode-cn.com/problems/minimum-size-subarray-sum/
     * 给定一个含有 n 个正整数的数组和一个正整数 target
     * 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0
     *
     * @param target
     * @param nums
     * @return
     */
    public int minSubArrayLen(int target, int[] nums) {

        return 0;
    }


    /**
     * 【前缀和】
     * 525 给定一个二进制数组 nums , 找到含有相同数量的 0 和 1 的最长连续子数组，并返回该子数组的长度
     * https://leetcode-cn.com/problems/contiguous-array/Related
     *
     * @param nums
     * @return
     */
    public int findMaxLength(int[] nums) {
        // 前缀和思想：把0作为-1，1作为+1那么只要区间数组相加为0则表示他们的0和1的数量相同.
        // 翻译出来就是preSum[start]和preSum[end]的大小相等即可

        // <值(最开始的),index>
        int sum = 0;
        int ans = 0;
        HashMap<Integer, Integer> map = new HashMap<>(nums.length);
        map.put(sum, -1);
        for (int i = 0; i < nums.length; i++) {
            int number = nums[i];
            if (number == 0) {
                sum--;
            } else if (number == 1) {
                sum++;
            }
            if (map.containsKey(sum)) {
                // 包含则说明之前有过相同值，那么他们之间的差距就是相加为0的结果
                Integer start = map.get(sum);
                ans = Math.max(i - start, ans);
            } else {
                map.put(sum, i);
            }
        }
        return ans;
    }


}
