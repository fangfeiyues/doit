package com.fang.doit.algo.sort;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 图
 * created by fang on 2019/1/12/012 19:41
 */
public class Graph {

    // 顶点个数
    private int v;

    // 临接表
    private LinkedList<Integer> adj[];

    private ArrayList<Integer> add[];

    public Graph(int v) {
        this.v = v;
        adj = new LinkedList[v];

        // 每个顶点构建一个链表(考虑优化红黑树)
        for (int i = 0; i < v; ++i) {
            adj[i] = new LinkedList<Integer>();
        }
    }

    /**
     * 无向图
     *
     * @param s
     * @param t
     */
    public void addEdge(int s, int t) {
        adj[s].add(t);
        adj[t].add(s);
    }

    /**
     * 广度优先搜索
     *
     * @param s
     * @param t
     */
    public void bfs(int s, int t) {


    }

    /**
     * 深度优先搜索（回溯）
     *
     * @param s
     * @param t
     */
    public void dfs(int s, int t) {


    }


}































