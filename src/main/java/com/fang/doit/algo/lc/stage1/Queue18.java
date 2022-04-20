package com.fang.doit.algo.lc.stage1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 队列
 *
 * @author fangfeiyue
 * @Date 2021/1/13 11:39 下午
 */
public class Queue18 {

    /**
     * 622：设计循环队列
     * 循环队列的一个好处是我们可以利用这个队列之前用过的空间。在一个普通队列里，一旦一个队列满了，我们就不能插入下一个元素，即使在队列前面仍有空间。
     * 但是使用循环队列，我们能使用这些空间去存储新的值
     *
     * @param k
     * @see MyCircularQueue
     */
    public void MyCircularDeque(int k) {
        // index即是取摩结果
        MyCircularQueue deque = new MyCircularQueue(k);
        return;
    }


    /**
     * 输入：tasks = ["A","A","A","B","B","B"], n = 2
     * 输出：8
     * 解释：A -> B -> (待命) -> A -> B -> (待命) -> A -> B
     *      在本示例中，两个相同类型任务之间必须间隔长度为 n = 2 的冷却时间，而执行一个任务只需要一个单位时间，所以中间出现了（待命）状态。
     */
    /**
     * 621:任务调度器
     *
     * @param tasks 表示CPU需要执行的任务列表，每个任务都可以在1单位之间内完成
     * @param n     两个相同种类的任务之间必须有长度为n的冷却时间
     * @return 完成所有任务所需要的最短时间
     */
    public int leastInterval(char[] tasks, int n) {
        // 问题的核心是在于找到贪心算法里面的局部最优解 -- 即每次以n+1为一个任务的轮回并将其从大到小排序避免大的重复轮训到
        int[] map = new int[26];
        for (char c : tasks) {
            map[c - 'A']++;
        }
        // 维护一个大顶推
        PriorityQueue<Integer> queue = new PriorityQueue<>(26, Collections.reverseOrder());
        for (int i = 0; i < map.length; i++) {
            if (map[i] > 0) {
                queue.add(map[i]);
            }
        }
        int times = 0;
        while (!queue.isEmpty()) {
            int i = 0;
            List<Integer> temp = new ArrayList<>(n);
            while (i <= n) {
                // 把字母及出现的总次数放到队列内，每次从大顶推内poll。
                if (!queue.isEmpty()) {
                    if (queue.peek() > 1) {
                        // 这次遍历n个过后还剩余n-1 重新加入下次的
                        temp.add(queue.poll() - 1);
                    } else {
                        queue.poll();
                    }
                }
                // 即使queue.isEmpty()也要继续累加下去
                times++;

                // 如果大顶推没了说明n>max(出现次数) 且 temp也没了 说明整个数组遍历结束
                if (queue.isEmpty() && temp.size() == 0) {
                    break;
                }

                i++;
            }
            for (Integer t : temp) {
                queue.add(t);
            }
        }
        return times;
    }
}
