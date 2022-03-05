package com.fang.doit.algo.lc.page1;

import java.util.*;

/**
 * 贪心算法：保证每次操作都是局部最优的并且最后得到的结果是全局最优的
 * 核心思想：找到能处理问题的最小单元，如n+1，通过最大值找承载
 *
 * @author fangfeiyue
 * @Date 2020/12/1 12:02 下午
 */
public class Greedy03 {

    /**
     * 621：任务调度器
     * 调度最活跃的用户但又不能总是同一个人，所以加上个冷冻期
     *
     * @param tasks 任务列表
     * @param n     冷冻时间的单位长度
     * @return
     */
//    public int leastInterval(char[] tasks, int n) {
//        // 问题的核心是在于找到贪心算法里面的局部最优解 -- 即每次以n+1为一个任务的轮回并将其从大到小排序避免大的重复轮训到。
//        int[] map = new int[26];
//        for (char c : tasks) {
//            map[c - 'A']++;
//        }
//        // 维护一个大顶推，由大到小
//        PriorityQueue<Integer> queue = new PriorityQueue<>(26, Collections.reverseOrder());
//        for (int i = 0; i < map.length; i++) {
//            if (map[i] > 0) {
//                queue.add(map[i]);
//            }
//        }
//        int times = 0;
//        while (!queue.isEmpty()) {
//            int i = 0;
//            List<Integer> temp = new ArrayList<>(n);
//            // 这里每个任务的次数是n+1
//            while (i <= n) {
//                if (!queue.isEmpty()) {
//                    if (queue.peek() > 1) {
//                        temp.add(queue.poll() - 1);
//                    } else {
//                        queue.poll();
//                    }
//                }
//                times++;
//                // 一次queue已经没了则直接跳出
//                if (queue.isEmpty() && temp.size() == 0) {
//                    break;
//                }
//                i++;
//            }
//            for (Integer t : temp) {
//                queue.add(t);
//            }
//        }
//        return times;
//
//        /**
//         * 解答成功: 执行耗时:37 ms,击败了12.02% 的Java用户 内存消耗:39.4 MB,击败了93.68% 的Java用户
//         */
//    }


    // ------------------------

    /**
     * TODO 如果每艘船能承载N人
     * 881：第 i 个人的体重为 people[i]，每艘船可以承载的最大重量为 limit，每艘船最多可同时载两人
     *
     * @param people
     * @param limit
     * @return
     */
    public int numRescueBoats(int[] people, int limit) {
//        贪心选择:
//        从最大值开始考虑，最大值能否和最小值配(即体重最大的能否和人同乘坐一条船)，
//        如果可以匹配问题变成了求原数组people减去两个人后最少需要几条船。
//        如果不可以匹配，问题就是变成原数组people减去一个人后最少需要几条船。以此类推，规模不断缩小，最后得到答案
        /**
         * 解答成功: 执行耗时:18 ms,击败了62.77% 的Java用户 内存消耗:47.2 MB,击败了67.46% 的Java用户
         */
        Arrays.sort(people);
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
     * 解答失败: 测试用例:["A","A","A","B","B","B"] 2 测试结果:30 期望结果:8 stdout:
     *
     * @param args
     */
    public static void main(String[] args) {
        Greedy03 greedy03 = new Greedy03();
        int[] aa = {3, 2, 2, 1};
        System.out.println(greedy03.numRescueBoats(aa, 3));
    }

}
