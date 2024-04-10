package com.fang.doit.algo.classes.tree.sort;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author by Feiyue on 2020/10/3 11:14 AM
 */
public class LcMergeSort {

    /**
     * https://leetcode-cn.com/problems/merge-intervals/
     * ����: intervals = [[1,3],[2,6],[8,10],[15,18]]
     * ���: [[1,6],[8,10],[15,18]]
     * ����: ���� [1,3] �� [2,6] �ص�, �����Ǻϲ�Ϊ [1,6]
     *
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        return mergeV1(intervals);
    }

    public int[][] mergeV1(int[][] intervals) {
        // �ҵ�ÿ���������˵㣬���뵽merged�б������������һ�������Ҷ˵����
        if (intervals.length == 0) {
            return null;
        }
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] interval1, int[] interval2) {
                return interval1[0] - interval2[0];
            }
        });

//        Arrays.sort(intervals, (interval1, interval2) -> interval1[0] - interval2[0]);

        List<int[]> merged = Lists.newArrayList();
        for (int i = 0; i < intervals.length; ++i) {
            int L = intervals[i][0];
            int R = intervals[i][1];
            if (merged.size() == 0 || merged.get(merged.size() - 1)[1] < L) {
                merged.add(new int[]{L, R});
            } else {
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], R);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }

}
