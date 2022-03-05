package com.fang.doit.algo.sort;

import java.util.PriorityQueue;

import static org.apache.ibatis.ognl.OgnlOps.less;

/**
 * 归并：是将两个有序的子数组分别排序并将有序的子数组归并以将整个数组排序
 * 快排：当两个子数组有序时整个数组也就有序了
 * 不稳定 在最后替换的时候发生
 *
 * @author created by fang on 2019/11/2/002 12:01
 */
public class QuickSort {

//    public static void sort(Comparable[] a) {
//        sort(a, 0, a.length - 1);
//    }
//
//
//    private static void sort(Comparable[] a, int lo, int hi) {
//        if (hi <= lo) {
//            return;
//        }
//        int j = partition(a, lo, hi);
//        sort(a, lo, j);
//        sort(a, j + 1, hi);
//    }
//
//    /**
//     * 切分 保证切分元素的左右有序
//     *
//     * @param a
//     * @param lo
//     * @param hi
//     * @return
//     */
//    private static int partition(Comparable[] a, int lo, int hi) {
//        int i = lo;
//        int j = hi;
//        Comparable partition = a[lo];
//        while (true) {
//            // lo, hi点一起移动 知道遇见大于/小于的 开始交换
//            while (less(a[++i], partition)) {
//                if (i == hi) {
//                    break;
//                }
//            }
//            while (less(partition, a[--j])) {
//                if (j == lo) {
//                    break;
//                }
//            }
//            if (j <= i) {
//                break;
//            }
//            swap(a, i, j);
//        }
//
//        // 置换
//        swap(a, lo, j);
//        return j;
//    }

    public static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }


    /**
     * 215：数组中的第K个最大元素。给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素
     * TODO 执行耗时:15 ms,击败了18.39% 的Java用户,内存消耗:39.3 MB,击败了5.01% 的Java用户【有点拉垮..】
     *
     * @param nums
     * @param k
     * @return
     */
    public static int findKthLargest(int[] nums, int k) {
        if (nums.length == 0 || k < 0) {
            return 0;
        }
        int topK = 0;
        // 1. 快排求得topK的值
        topK = topKPartition(nums, 0, nums.length - 1, k);

        return nums[topK];
    }

    private static int topKPartition(int[] nums, int low, int high, int k) {
        int partition = nums[low];
        // FIXME 定义
        //  1.轴值：作为第一个元素不动.最后与中间位置交换；
        //  2.i：作为小于轴值分界线左偏向的数；
        //  3.j：不断后移比较
        int i = low;
        for (int j = i + 1; j <= high; j++) {
            if (nums[j] < partition) {
                swap(nums, ++i, j);
            }
        }
        swap(nums, low, i);
        // FIXME 分区点 position: 表示i的前面有num-k个位置
        int beforePoint = nums.length - k;
        if (beforePoint == i) {
            return i;
        }
        if (low == high) {
            return low;
        }
        if (beforePoint > i) {
            return topKPartition(nums, i + 1, high, k);
        } else {
            // 在区分点之前
            return topKPartition(nums, low, i - 1, k);
        }
    }

    public int findKthLargestByQueue(int[] nums, int k) {
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

    public static void main(String[] args) {
        // [3,2,3,1,2,4,5,5,6] 和 k = 4  ==》 4
        // [3,2,1,5,6,4] 和 k = 2 ==》5
        int[] nums = {3, 2, 1, 5, 6, 4};
//        int[] nums = {3};
        System.out.println(findKthLargest(nums, 3));
    }

}
