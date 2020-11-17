package com.fang.doit.algo.sort;

/**
 * created by fang on 2018/12/17/017 21:18
 * 归并排序：先把数组从中间分成前后两部分然后对前后部分分别排序再将排序好的重新组合在一起。这用到了分治的思想即分而治之，将一个大问题分解为多个小问题来解决
 * 相同的两个数可以保持稳定的相对位置
 * 时间复杂度：T(n) = 2*T(n/2) + K   ===>  任何情况下都是 O(nlogn)
 * 空间复杂度：
 */
public class MergeSort {

    // merge_sort(p...r) = merge(merge_sort(p...q),merge_sort(q...r)) 且 q=(p+r)/2
    // 终止条件：p>=r

    public void merge_sort(int[] array) {

        merge_sort_c(array, 0, array.length);


    }

    private void merge_sort_c(int[] a, int p, int r) {

        if (p >= r) {
            return;
        }

        int q = (p + r) / 2;

        merge_sort_c(a, p, q);
        merge_sort_c(a, q + 1, r);

        // 从最低层的分割开始 合并的过程就是排序
        merge(a, p, q, r);

    }


    private void merge(int[] a, int p, int q, int r) {
        // 两个游标
        int i = p;
        int j = q + 1;
        //作为temp的临时存储下标
        int k = 0;
        int[] temp = new int[r - q + 1];

        // 两个数组比较并存放到temp
        while (i < q & j < r) {
            if (a[i] <= a[j]) {
                temp[k++] = a[i++];
            } else {
                temp[k++] = a[j++];
            }
        }

        // 判断哪个数组有剩余
        int start = i;
        int end = q;
        if (j < i) {
            start = j;
            end = r;
        }
        while (start <= end) {
            temp[k++] = temp[start++];
        }

        // 将temp数据考回到a[p...r]数组
        for (i = 0; i <= r - p; ++i) {
//            ArrayIndexOutOfBoundsException
            a[p + i] = temp[i];
        }
    }


    public static void main(String[] args) {
        int[] a = {6, 3, 8, 20, 9, 4, 6, 10, 35};
        MergeSort mergeSort = new MergeSort();
        mergeSort.merge_sort(a);
        System.out.println(a);

    }

}
