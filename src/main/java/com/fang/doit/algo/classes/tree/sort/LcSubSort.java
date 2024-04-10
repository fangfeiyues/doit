package com.fang.doit.algo.classes.tree.sort;

import com.alibaba.fastjson.JSON;

/**
 * ��Ӧ�ó�������ĳ�������ڵķ�˳������ֽ������򣨳������٣�
 * �����Ҫ��ȡ��η�˳������ֻ����жϻ�����
 *
 * @author created by fang on 2020/9/28/028 0:52
 */
public class LcSubSort {

    /**
     * https://leetcode-cn.com/problems/sub-sort-lcci/
     * <p>
     * ����һ���������飬��дһ�������ҳ�����m��n��ֻҪ����������[m,n]��Ԫ���ź�������������������
     * ע�⣺n-m������С��Ҳ����˵���ҳ�����������������С���������ֵΪ[m,n]����������������m��n��������������������ģ����뷵��[-1,-1]
     * <p>
     * ���룺 [1,2,4,7,10,11,7,12,6,7,16,18,19]
     * ����� [3,9]
     * <p>
     *
     * @param array
     * @return
     */
    public int[] subSort(int[] array) {
        // ���ϲ��ϲ������, ����ָ��... �����
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
            // ��ǰ���������ֵ�����֮��������Ǵ�ģ�����Ϊfirst
            if (array[i] > max) {
                // ����OK
                max = array[i];
            } else {
                // С��i��������(Ĭ���������),���滹��С��max�ļ������Ʒ�Χ����
                last = i;
            }

            // �Ӻ�ǰ����С��ֵ����С��֮ǰ�����Ǵ��, ����Ϊlast
            if (array[len - i - 1] < min) {
                min = array[len - i - 1];
            } else {
                // ��first���ﶼ���������
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
