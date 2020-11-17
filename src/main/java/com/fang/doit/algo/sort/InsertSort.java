package com.fang.doit.algo.sort;

/**
 * created by fang on 2018/12/17/017 20:48
 * 插入排序：把一组数据分为已排序和未排序的两部分，不断的从未排序部分插入到已排序
 */
public class InsertSort {

    private static void sort(int[] a) {
        if (a.length == 0) {
            return;
        }
        int length = a.length;
        // 从数组的第二个开始
        for (int i = 1; i < length; i++) {
            int value = a[i];
            // 不断和前一个比较
            int j = i - 1;
            // !!! 当j==0时说明已经比较到了最前面了
            for (; j >= 0; j--) {
                if (a[j] > value) {
                    a[j + 1] = a[j];
                } else {
                    break;
                }
            }
            a[j + 1] = value;
        }

    }

    private static void print(int[] arrays) {
        for (int i = 0; i < arrays.length; i++) {
            System.out.println(arrays[i]);
        }

    }

    public static void main(String[] args) {
        int[] arrays = {3, 5, 4, 6, 1, 9};
        sort(arrays);
        print(arrays);
    }

}
