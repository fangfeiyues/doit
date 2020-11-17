package com.fang.doit.algo.graph;

import java.util.Iterator;

/**
 * @author by Feiyue on 2019/12/4 3:06 PM
 */
public class Graph {
    /**
     * ������
     */
    private final int v;

    /**
     * �ߵ���Ŀ
     */
    private int E;

    /**
     * �ڽӱ�
     */
    private Bag<Integer>[] adj;


    public Graph(int V) {
        this.v = V;
        this.E = 0;

        // �����ڽӱ��ҳ�ʼ��Ϊ��
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
