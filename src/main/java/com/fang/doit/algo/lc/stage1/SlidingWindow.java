package com.fang.doit.algo.lc.stage1;

import java.util.*;

/**
 * @author : fangfeiyue
 * @version V1.0
 * @Project: doit
 * @Package com.fang.doit.algo.lc.stage1
 * @Description: 滑动窗口
 * @date Date : 2023-02-28 12:44 上午
 */
public class SlidingWindow {

    /**
     *  ------------------- 滑动窗口的解题技巧 -----------------
     *  1、窗口左右控制：一般通过「 left/right + k 」 基本都在这节点上做文章比如加、减之类
     *  2、窗口内数据维护：一般使用 PriorityQueue(窗口内最大值) / HashMap(窗口内重复数据)
     *
     *  ------------------------------------------------------
     */


    /**
     * 3.给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度
     * 输入: s = "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        // 直接用hashmap.containsKey来表示
        if (s.length() == 0) {
            return 0;
        }
        HashMap<Character, Integer> map = new HashMap(s.length());
        int max = 0;
        int left = 0;
        for (int i = 0; i < s.length(); i++) {
            char a = s.charAt(i);
            if (map.containsKey(a)) {
                // 这里取max的原因：在如'abba'这种重复数字越来越小的情况下map.get(a)的大小会小也就是left可能表小
                left = Math.max(left, map.get(a) + 1);
            }
            map.put(a, i);
            max = Math.max(max, i - left + 1);
        }
        return max;
    }


    /**
     * 给定一个含有 n 个正整数的数组和一个正整数 target 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0
     * eg. target = 7, nums = [2,3,1,2,4,3] ==》 2
     *
     * @param target
     * @param nums
     * @return
     */
    public int minSubArrayLen(int target, int[] nums) {
        int min = Integer.MAX_VALUE;
        int sum = 0;
        int left = 0;
        for (int i = 0; i < nums.length; i++) {
            // 找到[left,i]之间相加小于target的临界值
            sum = sum + nums[i];
            while (sum >= target) {
                min = Math.min(min, i - left + 1);
                sum = sum - nums[left];
                left = left + 1;
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }

    /**
     * 如果你已经实现 O(n) 时间复杂度的解法, 请尝试设计一个 O(n log(n)) 时间复杂度的解法
     *
     * @param target
     * @param nums
     * @return
     */
    public int minSubArrayLen4Opt(int target, int[] nums) {
        return 0;
    }

    /**
     * 239.给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。
     * 你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。返回 滑动窗口中的最大值
     * <p>
     * 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
     * 输出：[3,3,5,5,6,7]
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        // 优先队列大顶堆：维护滑动队列 每次向队列
        // 核心在于：只要队列维护最大值，那它就是这个窗口内的最大值
        // 难点在于：当最大值在窗口的最左边时候需要被移除，优先队列就需要重新调整队列大小为k并找到新的最大值。可以用map或int[]来维护这样的一个key-value结构
        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> o1[0] != o2[0] ? o2[0] - o1[0] : o2[1] - o1[1]);
        for (int i = 0; i < k; i++) {
            queue.offer(new int[]{nums[i], i});
        }
        int[] target = new int[nums.length - k + 1];
        target[0] = queue.peek()[0];
        for (int i = k; i < nums.length; i++) {
            queue.offer(new int[]{nums[i], i});
            // ++ 只要不在i-k范围内的数据都被移除
            while (queue.peek()[1] <= i - k) {
                queue.poll();
            }
            target[i - k + 1] = queue.peek()[0];
        }
        return target;
    }


    /**
     * 从一个水果数组里面通过滑动窗口找到路过数据类型不大于2的最长路径（可以从任一点开始）
     * 输入：fruits = [1,2,3,2,2]
     * 输出：4
     * 解释：可以采摘 [2,3,2,2] 这四棵树。
     *
     * @param fruits
     * @return
     */
    @Deprecated
    public int totalFruit(int[] fruits) {
        int max = 0;
        int left = 0;
        HashMap<Integer, Integer> map = new HashMap<>(2);

        for (int i = 0; i < fruits.length; i++) {
            if (map.containsKey(fruits[i])) {
                map.put(fruits[i], map.get(fruits[i]) + 1);
            } else if (map.keySet().size() > 1) {
                // -- 思路有问题：通过移除map的方式不能保证map的value数据都在滑动窗口内，会有重复累加的情况
                map.remove(fruits[left]);
                left++;
                map.put(fruits[i], 1);
            } else {
                left++;
                map.put(fruits[i], 1);
            }
            max = Math.max(max, map.values().stream().reduce(Integer::sum).orElse(0));
        }
        return max;
    }

    public int totalFruit_V2(int[] fruits) {
        int n = fruits.length;
        Map<Integer, Integer> cnt = new HashMap<>();
        int left = 0, ans = 0;
        for (int right = 0; right < n; ++right) {
            cnt.put(fruits[right], cnt.getOrDefault(fruits[right], 0) + 1);
            // ++ 从left到right之间的数不断遍历减1 解决窗口之外的数量问题
            while (cnt.size() > 2) {
                cnt.put(fruits[left], cnt.get(fruits[left]) - 1);
                if (cnt.get(fruits[left]) == 0) {
                    cnt.remove(fruits[left]);
                }
                ++left;
            }
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }

    /**
     * **** 给你一个 长度为 n 的整型数组 nums 和一个数值 k ，返回 第 k 小的子数组和。
     *
     * @param nums
     * @param k
     * @return
     */
    public int kthSmallestSubarraySum(int[] nums, int k) {
        int min = Integer.MAX_VALUE, sum = 0;
        for (int num : nums) {
            min = Math.min(min, num);
            sum += num;
        }
        int low = min, high = sum;
        while (low < high) {
            int mid = (high - low) / 2 + low;
            // ++ 二分法+滑动窗口：看mid处于第几小，要保证小于mid的子数组有多少个. 复杂...
            int count = countSubarrays(nums, mid);
            if (count < k) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    private int countSubarrays(int[] nums, int threshold) {
        int count = 0;
        int sum = 0;
        int n = nums.length;
        int left = 0, right = 0;
        // 通过left&right的双向移动遍历 看小于threshold数的子数组有多少
        while (right < n) {
            sum += nums[right];
            while (sum > threshold) {
                sum -= nums[left];
                left++;
            }
            count += right - left + 1;
            right++;
        }
        return count;
    }


    /**
     * 1343. 给你一个整数数组 arr 和两个整数 k 和 threshold 请你返回长度为 k 且平均值大于等于 threshold 的子数组数目
     * eg. arr = [2,2,2,2,5,5,5,8], k = 3, threshold = 4 ==> 3
     *
     * @param arr
     * @param k
     * @param threshold
     * @return
     */
    public int numOfSubarrays(int[] arr, int k, int threshold) {
        // step1 ： 取出前k个数求和，然后减去k*threshold ，如果结果大于0，说明符合要求。
        // step2 ： 指针后移一位，用后移一位的值减去移动之前的第一位的值，再加上上次减法的结果，如果大于0，说明符合要求
        // 总结来说都是在窗口的两边指针上做文章
        return 0;
    }


    public static void main(String[] args) {
        SlidingWindow slidingWindow = new SlidingWindow();
        System.out.println(slidingWindow.totalFruit(new int[]{3, 3, 3, 1, 2, 1, 1, 2, 3, 3, 4}));

//        int[] result = slidingWindow.maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3);
//        for (int i = 0; i < result.length; i++) {
//            System.out.println(result[i]);
//        }
    }


}
