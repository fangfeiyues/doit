package com.fang.doit.algo.graph;

import java.util.Iterator;

/**
 * @author by Feiyue on 2019/12/4 3:06 PM
 */
public class Graph {
    /**
     * 顶点数
     */
    private final int v;

    /**
     * 边的数目
     */
    private int E;

    /**
     * 邻接表
     */
    private Bag<Integer>[] adj;


    public Graph(int V) {
        this.v = V;
        this.E = 0;

        // 创建邻接表且初始化为空
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<Integer>();
        }

    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }


    class Bag<T> implements Iterable {

        @Override
        public Iterator iterator() {

            return null;
        }
    }
}
