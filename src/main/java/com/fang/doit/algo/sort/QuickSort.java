package com.fang.doit.algo.sort;

import static org.apache.ibatis.ognl.OgnlOps.less;

/**
 * �鲢���ǽ����������������ֱ����򲢽������������鲢�Խ�������������
 * ���ţ�����������������ʱ��������Ҳ��������
 * ���ȶ� ������滻��ʱ����
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
     * �з� ��֤�з�Ԫ�ص���������
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
            // lo, hi��һ���ƶ� ֪����������/С�ڵ� ��ʼ����
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

        // �û�
        swap(a, lo, j);
        return j;
    }

    public static void swap(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

}
