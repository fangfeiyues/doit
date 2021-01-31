package com.fang.doit.algo.sort;

import static org.apache.ibatis.ognl.OgnlOps.less;

/**
 * 归并：是将两个有序的子数组分别排序并将有序的子数组归并以将整个数组排序
 * 快排：当两个子数组有序时整个数组也就有序了
 * 不稳定 在最后替换的时候发生
 * @author created by fang on 2019/11/2/002 12:01
 */
public class QuickSort {

    public static void sort(Comparable[] a) {
        sort(a, 0, a.length - 1);
    }


    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int j = partition(a, lo, hi);
        sort(a, lo, j);
        sort(a, j + 1, hi);
    }

    /**
     * 切分 保证切分元素的左右有序
     *
     * @param a
     * @param lo
     * @param hi
     * @return
     */
    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo;
        int j = hi;
        Comparable partition = a[lo];
        while (true) {
            // lo, hi点一起移动 知道遇见大于/小于的 开始交换
            while (less(a[++i], partition)) {
                if (i == hi) {
                    break;
                }
            }
            while (less(partition, a[--j])) {
                if (j == lo) {
                    break;
                }
            }
            if (j <= i) {
                break;
            }
            swap(a, i, j);
        }

        // 置换
        swap(a, lo, j);
        return j;
    }

    public static void swap(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

}
