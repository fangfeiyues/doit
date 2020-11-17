package com.fang.doit.algo.graph;

/**
 * 有向图的可达性
 *
 * @author by Feiyue on 2019/12/4 4:13 PM
 */
public class DirectedDFS {

    private boolean[] marked;

    private void dfs(Digraph G, int v) {
        marked[v] = true;

        // 一直递归
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }

}
