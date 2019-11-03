package com.fang.doit.algo.sort;

/**
 * �Զ���������鲢����: һ��ʼ������Ϊһ�������������������
 * �ȶ���
 * @author created by fang on 2019/11/1/001 22:46
 */
public class Merge1 {

    private Comparable[] aux;

    private void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        sort(a, 0, a.length - 1);
    }


    /**
     * �ݹ����
     *
     * @param a
     * @param lo
     * @param hi
     */
    private void sort(Comparable[] a, int lo, int hi) {
        if (lo >= hi) {
            return;
        }

        int mid = (lo + hi) / 2;
        sort(a, lo, mid);
        sort(a, mid + 1, hi);
        merge(a, mid, lo, hi);

    }

    /**
     * ԭ������
     *
     * @param a
     * @param mid
     * @param lo
     * @param hi
     */
    private void merge(Comparable[] a, int mid, int lo, int hi) {
        int i = lo;
        int j = hi;
        for (int k = i; k < hi; k++) {
            aux[k] = a[k];
        }

        for (int k = lo; k <= hi; k++) {

        }

    }

}
