package com.fang.doit.algo.sort;

import java.util.PriorityQueue;
import java.util.Random;

/**
 * created by fang on 2019/4/3/003 23:02
 * 1亿个数里面最小的10个
 */
public class KTop {

    private static Random random = new Random();

    // 1. 构建大顶堆


    public static void main(String[] args) {

        for (int i = 0; i < 1000000000; i++) {
            int tempNum = random.nextInt(Integer.MAX_VALUE);
        }
    }

    public int findKthLargest(int[] nums, int k) {
        // 小顶堆
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int val : nums) {
            pq.add(val);
            // 维护堆的大小为 K
            if (pq.size() > k) {
                pq.poll();
            }

        }
        return pq.peek();
    }

}
