package com.fang.doit.algo.classes.search;

/**
 * created by fang on 2019/3/3/003 22:23
 * 二分查找法因为二叉树等更优的查找方法并不是很常用，更多的场景是：
 * 1.查找第一个值等于给定值值得元素
 * 2.查找最后一个值等于给定值的元素
 * 3.查找第一个大于等于给定元素的值（例）
 * 4.查找最后一个小于等于给定值得元素
 */
public class BinarySearchPlus implements Search {


    /**
     * 第一个给定值的，向前看
     *
     * @param a
     * @param n
     * @param value
     * @return
     */
    public int bsearch(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (a[mid] < value) {
                low = mid + 1;
            } else if (a[mid] > value) {
                high = mid - 1;
            } else {
                if (mid == 0 || a[mid - 1] != value) {
                    return mid;
                }

                //
                high = mid - 1;
            }
        }
        return -1;
    }


    /**
     * 查找第一个大于等于给定元素的值: 如果这个值等于大于给定值则查看前一个值是否小于
     *
     * @param a
     * @param n
     * @param value
     * @return
     */
    public int bsearchFirstOver(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (a[mid] < value) {
                low = mid + 1;
            } else {

                if (mid == 0 || a[mid - 1] < value) {
                    return value;
                }
                high = mid - 1;
            }
        }

        return -1;
    }
}