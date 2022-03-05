package com.fang.doit.algo.lc.page1;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author created by fang on 2020/11/18/018 1:15
 */
public class CanCompleteCircuit {

    /**
     * 134 ����վ
     *
     * @param gas
     * @param cost
     * @return
     */
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        //
        return canCompleteCircuitByViolence(gas, cost);
    }

    /**
     * 435 ���ص�����
     * ִ�к�ʱ:7 ms,������12.18% ��Java�û� �ڴ�����:39 MB,������20.05% ��Java�û�
     *
     * @param intervals
     * @return
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }
        // ������������һλ���� βԽС��������Ŀռ�Խ��
        Arrays.sort(intervals, Comparator.comparing(o -> o[1]));
        // ����0�������1λ
        int end = intervals[0][1];
        int counter = 1;
        for (int i = 1; i < intervals.length; i++) {
            // ��һ����ͷС��ǰһ����β�� ��Ҫ�Ƴ�
            if (intervals[i][0] < end) {
                continue;
            }
            end = intervals[i][1];
            counter++;
        }

        // ���ʧ��: ��������:[[1,2],[1,2]] ���Խ��:1 �������:0 stdout:
        return intervals.length - counter;
    }


    private static int canCompleteCircuitByViolence(int[] gas, int[] cost) {
        // �������: ��ִ�к�ʱ:27 ms,������38.61% ��Java�û� �ڴ�����:38.4 MB,������97.91% ��Java�û���
        if (getAll(gas) < getAll(cost)) {
            return -1;
        }
        for (int i = 0; i < gas.length; i++) {
            int remain = 0;
            for (int l = i % gas.length; l < gas.length; l++) {
                remain = remain + (gas[l] - cost[l]);
                if (remain < 0) {
                    break;
                }
            }
            // һ�����껹��ʣ���������˵��������
            if (remain >= 0) {
                return i;
            }
        }
        return -1;
    }

    private static int getAll(int[] a) {
        int all = 0;
        for (int i = 0; i < a.length; i++) {
            all = all + a[i];
        }
        return all;
    }

    public static void main(String[] args) {
//        ���ʧ��: ��������:[5,1,2,3,4] [4,4,1,5,1] ���Խ��:2 �������:4 stdout:
        int[] gas = new int[]{5, 1, 2, 3, 4};
        int[] cost = new int[]{4, 4, 1, 5, 1};
        System.out.println(canCompleteCircuit(gas, cost));
    }

}
