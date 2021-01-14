package com.fang.doit.algo.lc;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 分治
 *
 * @author fangfeiyue
 * @Date 2020/12/26 2:22 下午
 */
public class Divide14 {

    /**
     * 251 数组中的第K个最大元素: 在未排序的数组中找到第 k 个最大的元素
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        // 1. K大的小顶推，堆顶即是
        findKthLargestByPri(nums, k);

        // 2. 快排的分治思想
        findKthLargestByQuick(nums, k);

        return -1;
    }

    public int findKthLargestByPri(int[] nums, int k) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(k, Comparator.comparingInt(a -> a));
        for (int num : nums) {
            Integer minNum = priorityQueue.peek();
            if (minNum == null || minNum < num) {
                priorityQueue.add(num);
            }
        }
        return priorityQueue.isEmpty() ? -1 : priorityQueue.poll();
    }

    public int findKthLargestByQuick(int[] nums, int k) {
        if (k < 1) {
            return -1;
        }
        // 快排主要是利用分治的思想。选折分区点然后左右排列，
        int provit = 0;
        while (provit < nums.length) {
            int point = quickSort(nums, provit);
            if (point == nums.length - k - 1) {
                return nums[point - 1];
            }
            // 比较point和k的大小
            provit = point - 1;
        }
        return -1;
    }

    public int quickSort(int[] nums, int provit) {
        // i,j两个游标。i标记i及以前的都是小于nums[k]的，j往前走
        // 首位标记为不能动
        if (provit > nums.length - 1) {
            return -1;
        }
        int i = provit;
        for (int j = provit; j < nums.length; j++) {
            if (nums[j] < nums[provit]) {
                // 交换 第i+1个(理论上大于)和第j个(小于)即可
                swap(nums, i + 1, j);
                i++;
            }
        }
        swap(nums, provit, i);
        return i + 1;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


    /**
     * 395 至少有K个重复字符的最长子串：找到给定字符串（由小写字符组成）中的最长子串 T 要求 T 中的每一字符出现次数都不少于 k
     *
     * @param s
     * @param k
     * @return 最长子串
     */
    public int longestSubstring(String s, int k) {
        // 滑动窗口枚举法子如k=2则枚举'aa','aabb','aabbcc'

        return -1;
    }

 /*   public static void main(String[] args) {
        Divide14 divide = new Divide14();
        int[] numbers = {5, 2, 3, 4, 6, 7};
        System.out.println(divide.quickSort(numbers, 5));
    }*/
}
