package com.fang.doit.algo.classes.tree.search;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 搜索树
 *
 * @author fangfeiyue
 * @Date 2020/12/18 11:00 上午
 */
public class TreeSearch {

    private int[][] adj = new int[100][100];

    private int v = 100;

    boolean found = false;

    /**
     * 广度优先算法
     *
     * @param s
     * @param t
     */
    public void bfs(int s, int t) {
        if (s == t) {
            return;
        }
        // visited[] 记录已经访问的节点避免重复计算
        boolean[] visited = new boolean[v];
        visited[s] = true;
        // queue 记录当前正在访问的层级，依次来访问下一层的节点
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        // prev 节点的上层节点
        int[] prev = new int[v];
        for (int i = 0; i < v; ++i) {
            prev[i] = -1;
        }
        while (queue.size() != 0) {
            int w = queue.poll();
            // 遍历下一层或使用Map<Integer,Map<Integer,Integer>>
            for (int i = 0; i < adj[w].length; ++i) {
                int q = adj[w][i];
                if (!visited[q]) {
                    prev[q] = w;
                    if (q == t) {
                        print(prev, s, t);
                        return;
                    }
                    visited[q] = true;
                    queue.add(q);
                }
            }
        }
    }


    /**
     * 深度优先算法
     *
     * @param s
     * @param t
     */
    public void dfs(int s, int t) {
        found = false;
        boolean[] visited = new boolean[v];
        int[] prev = new int[v];
        for (int i = 0; i < v; ++i) {
            prev[i] = -1;
        }
        recurDfs(s, t, visited, prev);
        print(prev, s, t);
    }

    private void recurDfs(int w, int t, boolean[] visited, int[] prev) {
        // 怎么知道到达最深了？ -- for下面没有了
        if (found) {
            return;
        }
        visited[w] = true;
        if (w == t) {
            found = true;
            return;
        }
        // 如果分支找不到则回到循环！！！
        for (int i = 0; i < adj[w].length; ++i) {
            int q = adj[w][i];
            if (!visited[q]) {
                prev[q] = w;
                recurDfs(q, t, visited, prev);
            }
        }
    }


    private void print(int[] prev, int s, int t) {
        // 递归打印s->t的路径
        if (prev[t] != -1 && t != s) {
            print(prev, s, prev[t]);
        }
        System.out.print(t + " ");
    }


}
