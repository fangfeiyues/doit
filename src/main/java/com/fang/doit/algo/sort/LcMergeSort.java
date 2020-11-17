package com.fang.doit.algo.sort;

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
     * 输入: intervals = [[1,3],[2,6],[8,10],[15,18]]
     * 输出: [[1,6],[8,10],[15,18]]
     * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6]
     *
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        return mergeV1(intervals);
    }

    public int[][] mergeV1(int[][] intervals) {
        // 找到每个数组的左端点，加入到merged列表；如果区间的最后一个数组右端点大于
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
