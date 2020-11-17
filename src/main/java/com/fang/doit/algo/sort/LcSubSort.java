package com.fang.doit.algo.sort;

import com.alibaba.fastjson.JSON;

/**
 * 可应用场景：对某段数组内的非顺序的数字进行排序（场景较少）
 * 如果是要抽取这段非顺序的数字或做判断还可以
 *
 * @author created by fang on 2020/9/28/028 0:52
 */
public class LcSubSort {

    /**
     * https://leetcode-cn.com/problems/sub-sort-lcci/
     * <p>
     * 给定一个整数数组，编写一个函数找出索引m和n，只要将索引区间[m,n]的元素排好序，整个数组就是有序的
     * 注意：n-m尽量最小，也就是说，找出符合条件的最短序列。函数返回值为[m,n]，若不存在这样的m和n（例如整个数组是有序的），请返回[-1,-1]
     * <p>
     * 输入： [1,2,4,7,10,11,7,12,6,7,16,18,19]
     * 输出： [3,9]
     * <p>
     *
     * @param array
     * @return
     */
    public int[] subSort(int[] array) {
        // 符合不断拆解区间, 两个指针... 想多了
        return subSortV1(array);
    }

    private static int[] subSortV1(int[] array) {
        if (array == null || array.length == 0) {
            return new int[]{-1, -1};
        }
        int last = -1, first = -1;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        int len = array.length;
        for (int i = 0; i < len; i++) {
            // 从前到后找最大值即最大之后的排序都是错的，定义为first
            if (array[i] > max) {
                // 大则OK
                max = array[i];
            } else {
                // 小则到i重新排序(默认升序规则),后面还有小于max的继续后推范围扩大
                last = i;
            }

            // 从后到前找最小的值即最小的之前排序都是错的, 定义为last
            if (array[len - i - 1] < min) {
                min = array[len - i - 1];
            } else {
                // 到first这里都是有问题的
                first = len - i - 1;
            }
        }
        return new int[]{first, last};
    }


    public static void main(String[] args) {
        int[] test = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        System.out.println(JSON.toJSONString(subSortV1(test)));
    }
}
