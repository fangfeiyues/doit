package com.fang.doit.algo.lc.page1;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author created by fang on 2020/11/18/018 1:15
 */
public class CanCompleteCircuit {

    /**
     * 134 加油站
     *
     * @param gas
     * @param cost
     * @return
     */
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        //
        return canCompleteCircuitByViolence(gas, cost);
    }

    /**
     * 435 无重叠区间
     * 执行耗时:7 ms,击败了12.18% 的Java用户 内存消耗:39 MB,击败了20.05% 的Java用户
     *
     * @param intervals
     * @return
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }
        // 按照数组的最后一位排序 尾越小留给后面的空间越大
        Arrays.sort(intervals, Comparator.comparing(o -> o[1]));
        // 即第0个数组第1位
        int end = intervals[0][1];
        int counter = 1;
        for (int i = 1; i < intervals.length; i++) {
            // 后一个的头小于前一个的尾巴 需要移除
            if (intervals[i][0] < end) {
                continue;
            }
            end = intervals[i][1];
            counter++;
        }

        // 解答失败: 测试用例:[[1,2],[1,2]] 测试结果:1 期望结果:0 stdout:
        return intervals.length - counter;
    }


    private static int canCompleteCircuitByViolence(int[] gas, int[] cost) {
        // 暴力求解: 【执行耗时:27 ms,击败了38.61% 的Java用户 内存消耗:38.4 MB,击败了97.91% 的Java用户】
        if (getAll(gas) < getAll(cost)) {
            return -1;
        }
        for (int i = 0; i < gas.length; i++) {
            int remain = 0;
            for (int l = i % gas.length; l < gas.length; l++) {
                remain = remain + (gas[l] - cost[l]);
                if (remain < 0) {
                    break;
                }
            }
            // 一轮走完还有剩余的汽油则说明够用了
            if (remain >= 0) {
                return i;
            }
        }
        return -1;
    }

    private static int getAll(int[] a) {
        int all = 0;
        for (int i = 0; i < a.length; i++) {
            all = all + a[i];
        }
        return all;
    }

    public static void main(String[] args) {
//        解答失败: 测试用例:[5,1,2,3,4] [4,4,1,5,1] 测试结果:2 期望结果:4 stdout:
        int[] gas = new int[]{5, 1, 2, 3, 4};
        int[] cost = new int[]{4, 4, 1, 5, 1};
        System.out.println(canCompleteCircuit(gas, cost));
    }

}
