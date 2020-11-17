package com.fang.doit.algo.graph;

import edu.princeton.cs.algs4.Queue;

import java.util.Stack;

/**
 * �����������
 * �ҵ���̵��߼������㰴�����Ǻ�s�ľ��������뿪���У�ֻҪ��һ�α�ǹ��ľ������·����
 *
 * @author by Feiyue on 2019/12/4 3:00 PM
 */
public class BreadthFirstPaths {

    private boolean[] marked;

    private int[] edgeTo;

    private final int s = 0; // ���


    public boolean hasPathTo(int v) {
        return marked[v];
    }

    private void bfs(Graph G, int s) throws InterruptedException {
        // ��v��ʼ���ڽӱ����
        Queue<Integer> queue = new Queue<Integer>();
        marked[s] = true;
        queue.enqueue(s);
        while (!queue.isEmpty()) {
            int v = queue.dequeue();
            for (Integer w : G.adj(v)) {
                if (!marked[w]) {
                    marked[w] = true;
                    // ��ȥw����v
                    edgeTo[w] = v;
                    queue.enqueue(w);
                }
            }
        }

    }

    public Iterable<Integer> pathTo(int v) {

        // �����s��ʼ��ɢ
        if (!hasPathTo(v)) {
            return null;
        }

        Stack<Integer> path = new Stack<>();

        //��v��ʼ��ǰ���� �ﵽv(edge[v])����˭ ֱ�����s
        for (int x = v; x != s; x = edgeTo[x]) {
            // �����Ƚ����
            path.push(x);
        }

        path.push(s);
        return path;
    }
}
