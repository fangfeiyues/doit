package com.fang.doit.algo.classes.search;

/**
 * created by fang on 2019/3/3/003 21:19
 * 二分查找法：O(logn)这是一个很快的时间复杂度。如针对42亿次的数据只需要32次的查找就可以这时候比O(n)还快。
 * 局限性：
 * 1.只依赖于数组
 * 2.针对的事有序数据同时要求插入操作不是那么频繁 (二叉树)
 * 3.数据量大的时候优势明显
 * 4.如果存在太大的则连续的内存数组存储也吃力
 */
public class BinarySearch implements Search {

    @Override
    public int bsearch(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        // low <= high 这里用的 <= 而不是 < 是因为可能因为 low==high 而跳出
        while (low <= high) {
            // 可能存在最大值的溢出问题  int mid = (low + high) / 2;
            int mid = low + (high - low) / 2;
            if (a[mid] == value) {
                return mid;
            } else if (a[mid] < value) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }


}
