package com.fang.doit.algo.graph;

/**
 * ����ͼ�Ŀɴ���
 *
 * @author by Feiyue on 2019/12/4 4:13 PM
 */
public class DirectedDFS {

    private boolean[] marked;

    private void dfs(Digraph G, int v) {
        marked[v] = true;

        // һֱ�ݹ�
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }

}
