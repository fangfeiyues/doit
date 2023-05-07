package com.fang.doit.algo.lc.stage1;

import java.util.*;

/**
 * 贪心算法：保证每次操作都是局部最优的，并且最后得到的结果是全局最优的
 * 核心思想：找到能处理问题的最小单元，如n+1，通过最大值找承载
 *
 * @author fangfeiyue
 * @Date 2020/12/1 12:02 下午
 */
public class Greedy {


    /**
     *  ------------------- 贪心算法的解题技巧 ！！！ -----------------
     * 1、从前3题来看都是在当下局部（最多载几人、最多装多少水、最多赚多少）找到最优解，然后用一些双节点推进思路（大的船节点移动保证剩下的可两人坐一条、小的端点移动保证期间最大值、上坡节点推进等）向后延伸！！！
     *
     *
     *  ------------------------------------------------------
     */


    /**
     * 881：第 i 个人的体重为 people[i]，每艘船可以承载的最大重量为 limit，每艘船最多可同时载两人
     * --> 如果每艘船能承载N人
     *
     * @param people
     * @param limit
     * @return 承载所有人所需的最小船数
     */
    public int numRescueBoats_01_ddd(int[] people, int limit) {
//        贪心选择:
//        从最大值开始考虑，最大值能否和最小值配(即体重最大的能否和人同乘坐一条船)，
//        如果可以匹配问题变成了求原数组people减去两个人后最少需要几条船。
//        如果不可以匹配，问题就是变成原数组people减去一个人后最少需要几条船。以此类推，规模不断缩小，最后得到答案
        /**
         * 解答成功: 执行耗时:18 ms,击败了62.77% 的Java用户 内存消耗:47.2 MB,击败了67.46% 的Java用户
         */
        // 先排序数组
        Arrays.sort(people);
        // 在做贪心解法(局部最优解)
        int low = 0;
        int high = people.length - 1;
        int boats = 0;
        while (low <= high) {
            // 两个游标 i,j
            if (people[high] + people[low] <= limit) {
                low++;
            }
            boats++;
            high--;
        }
        return boats;
    }


    /**
     * 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水 返回容器可以储存的最大水量。
     *
     * @param height
     * @return
     */
    public int maxArea_02(int[] height) {
        // 1、暴力for循环 O(n*n)
        // 2、双指针O(n)：为什么是要小的指针移动？如果移动大的指针那么min(x,y1)*t1的「 min(x,y1)<=x 」即小值x一定会是后续的天花板而由于移动了t1<t的，所以 min(x,y) < min(x,y)∗t
        int l = 0, r = height.length - 1, ans = 0;
        while (l <= r) {
            int result = (r - l) * Math.min(height[l], height[r]);
            ans = Math.max(ans, result);
            if (height[l] > height[r]) {
                r--;
            } else {
                l++;
            }
        }
        return ans;
    }

    /**
     * 122.给你一个整数数组 prices ，其中 prices[i] 表示某支股票第 i 天的价格 在每一天你可以决定是否购买和/或出售股票。你在任何时候 最多 只能持有 一股 股票。你也可以先购买，然后在 同一天 出售。
     *
     * @param arr
     * @return 你能获得的 最大 利润 。
     */
    public int maxProfit_03(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return 0;
        }
        int ans = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > arr[i - 1]) {
                // 卖出有利可图
                ans += (arr[i] - arr[i - 1]);
            }
        }
        return ans;
    }



    /**
     * 解答失败: 测试用例:["A","A","A","B","B","B"] 2 测试结果:30 期望结果:8 stdout:
     *
     * @param args
     */
    public static void main(String[] args) {
        Greedy greedy = new Greedy();
        int[] aa = {3, 2, 2, 1};
//        System.out.println(greedy03.numRescueBoats(aa, 3));
    }

}
