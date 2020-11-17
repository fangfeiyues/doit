package com.fang.doit.algo.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * created by fang on 2019/4/22/022 23:19
 */
class Solution {
    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> result = new ArrayList<Interval>();
        Collections.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval a, Interval b) {
                return a.start - b.start;
            }
        });

        Interval temp = intervals.get(0);

        for (int i = 1; i < intervals.size(); i++) {
            Interval now = intervals.get(i);
            if (temp.end >= now.start) {
                temp.end = Math.max(temp.end, now.end);

            } else {
                result.add(temp);
                temp = intervals.get(i);
            }

        }

        result.add(temp);
        return result;
    }

    public class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }
    }

    public static void main(String[] args) {
        List<Interval> list = new ArrayList<>();
        Interval interval = new Solution().new Interval(1,5);
        list.add(interval);
        System.out.println(new Solution().merge(list).get(0));
    }
}
