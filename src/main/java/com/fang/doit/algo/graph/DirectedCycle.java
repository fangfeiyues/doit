package com.fang.doit.algo.graph;

import java.util.Stack;

/**
 * ����ͼ�Ƿ��л���⣺һ�������ҵ���һ�������v->w��w�Ѿ�������ջ�о��ҵ�һ������
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

        // ���
        for (int w : G.adj(v)) {
            if (hasCycle()) {
                return;

            } else if (!marked[w]) {
                // ��û����ǵ�
                edgeTo[w] = v;
                dfs(G, w);

            } else if (onStack[w]) {

                // ����Ѿ������˵���߹��� ��edgeTo[w] = v �� onStack ���ڻ���
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
