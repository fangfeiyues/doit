package com.fang.doit.algo.lc.stage2;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author : fangfeiyue
 * @version V1.0
 * @Project: doit
 * @Package com.fang.doit.algo.dst.stack
 * @Description:
 * @date Date : 2022-02-24 11:43 PM
 */
public class LeetCodeStack {

    /**
     * 请根据每日 气温 列表 temperatures ，重新生成一个列表，要求其对应位置的输出为：要想观测到更高的气温，至少需要等待的天数。
     * 如果气温在这之后都不会升高，请在该位置用 0 来代替
     * https://leetcode-cn.com/problems/daily-temperatures/
     *
     * @param temperatures
     * @return
     */
    public int[] dailyTemperatures(int[] temperatures) {
        int length = temperatures.length;
        Deque<Integer> stack = new LinkedList<>();
        int[] ans = new int[length];
        for (int i = 0; i < length; i++) {
            int num = temperatures[i];
            stack.add(num);
            while (!stack.isEmpty() && num > temperatures[stack.peek()]) {
                int prevIndex = stack.pop();
                ans[prevIndex] = i - prevIndex;
            }
            stack.push(i);
        }
        return ans;
    }

}
