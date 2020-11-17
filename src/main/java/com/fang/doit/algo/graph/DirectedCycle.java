package com.fang.doit.algo.graph;

import java.util.Stack;

/**
 * 有向图是否有环检测：一旦我们找到了一条有向边v->w且w已经存在于栈中就找到一个环了
 *
 * @author by Feiyue on 2019/12/4 4:29 PM
 */
public class DirectedCycle {

    private boolean[] marked;

    private int[] edgeTo;

    private Stack<Integer> cycle;

    private boolean[] onStack;


    private void dfs(Digraph G, int v) {

        onStack[v] = true;
        marked[v] = true;

        // 点的
        for (int w : G.adj(v)) {
            if (hasCycle()) {
                return;

            } else if (!marked[w]) {
                // 还没被标记的
                edgeTo[w] = v;
                dfs(G, w);

            } else if (onStack[w]) {

                // 如果已经被标记说明走过了 即edgeTo[w] = v 且 onStack 存在环了
                cycle = new Stack<>();
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }

                cycle.push(w);
                cycle.push(v);
            }
        }

        onStack[v] = false;

    }

    private boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }

}
