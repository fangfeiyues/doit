package com.fang.doit.algo.sort;

import java.util.PriorityQueue;

import static org.apache.ibatis.ognl.OgnlOps.less;

/**
 * �鲢���ǽ����������������ֱ����򲢽������������鲢�Խ�������������
 * ���ţ�����������������ʱ��������Ҳ��������
 * ���ȶ� ������滻��ʱ����
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
//     * �з� ��֤�з�Ԫ�ص���������
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
//            // lo, hi��һ���ƶ� ֪����������/С�ڵ� ��ʼ����
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
//        // �û�
//        swap(a, lo, j);
//        return j;
//    }

    public static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }


    /**
     * 215�������еĵ�K�����Ԫ�ء������������� nums ������ k���뷵�������е� k ������Ԫ��
     * TODO ִ�к�ʱ:15 ms,������18.39% ��Java�û�,�ڴ�����:39.3 MB,������5.01% ��Java�û����е�����..��
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
        // 1. �������topK��ֵ
        topK = topKPartition(nums, 0, nums.length - 1, k);

        return nums[topK];
    }

    private static int topKPartition(int[] nums, int low, int high, int k) {
        int partition = nums[low];
        // FIXME ����
        //  1.��ֵ����Ϊ��һ��Ԫ�ز���.������м�λ�ý�����
        //  2.i����ΪС����ֵ�ֽ�����ƫ�������
        //  3.j�����Ϻ��ƱȽ�
        int i = low;
        for (int j = i + 1; j <= high; j++) {
            if (nums[j] < partition) {
                swap(nums, ++i, j);
            }
        }
        swap(nums, low, i);
        // FIXME ������ position: ��ʾi��ǰ����num-k��λ��
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
            // �����ֵ�֮ǰ
            return topKPartition(nums, low, i - 1, k);
        }
    }

    public int findKthLargestByQueue(int[] nums, int k) {
        // С����
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int val : nums) {
            pq.add(val);
            // ά���ѵĴ�СΪ K
            if (pq.size() > k) {
                pq.poll();
            }
        }
        return pq.peek();
    }

    public static void main(String[] args) {
        // [3,2,3,1,2,4,5,5,6] �� k = 4  ==�� 4
        // [3,2,1,5,6,4] �� k = 2 ==��5
        int[] nums = {3, 2, 1, 5, 6, 4};
//        int[] nums = {3};
        System.out.println(findKthLargest(nums, 3));
    }

}
