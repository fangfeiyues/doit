package com.fang.doit.algo.lc.stage2;

/**
 * @author : fangfeiyue
 * @version V1.0
 * @Project: doit
 * @Package com.fang.doit.algo.lc.stage2
 * @Description:
 * @date Date : 2022-03-21 1:15 AM
 */
public class LeetCodeFind {

    /**
     * 排序数组中只出现一次的数字：给定一个只包含整数的有序数组 nums ，每个元素都会出现两次，唯有一个数只会出现一次，请找出这个唯一的数字
     * https://leetcode-cn.com/problems/single-element-in-a-sorted-array/
     * <p>
     * 进阶: 采用的方案可以在 O(log n) 时间复杂度和 O(1) 空间复杂度中运行吗？
     * 【二分查找】根据mid的奇偶性来判断下次在左还是在右二分查找
     *
     * @param nums
     * @return
     */
    public int singleNonDuplicate(int[] nums) {
        int low = 0, high = nums.length - 1;
        // 这里就不必加上low=mid 可直接在最后返回
        while (low < high) {
            int mid = low + (high - low) / 2;
            // 确保mid是偶数
            mid -= mid & 1;
            if (nums[mid] == nums[mid ^ 1]) {
                low = mid + 2;
            } else {
                high = mid;
            }
        }
        return nums[low];
    }

    /**
     * 吃香蕉
     * https://leetcode-cn.com/problems/koko-eating-bananas/
     *
     * @param piles N 堆香蕉，每小时最多吃其中的一堆
     * @param H     在H小时内吃完
     * @return 返回她可以在 H 小时内吃掉所有香蕉的最小速度 K（K 为整数）。
     */
    public int minEatingSpeed(int[] piles, int H) {
        int lo = 1;
        int hi = 1_000_000_000;
        while (lo < hi) {
            int mi = (lo + hi) / 2;
            if (!possible(piles, H, mi)) {
                lo = mi + 1;
            } else {
                hi = mi;
            }

        }

        return lo;
    }

    // Can Koko eat all bananas in H hours with eating speed K?
    public boolean possible(int[] piles, int H, int K) {
        int time = 0;
        for (int p : piles) {
            time += (p - 1) / K + 1;
        }
        return time <= H;
    }

}
