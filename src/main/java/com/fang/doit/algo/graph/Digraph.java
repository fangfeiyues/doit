package com.fang.doit.algo.graph;

import java.util.Iterator;

/**
 * 有向图
 *
 * @author by Feiyue on 2019/12/4 4:05 PM
 */
public class Digraph {

    private final int V;
    private int E;
    private Bag<Integer>[] adj;

    public Digraph(int V) {
        this.V = V;
        this.E = 0;

        adj = (Bag<Integer>[]) new Bag[V];

        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<Integer>();
        }

    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
        E++;
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    /**
     * 有向图取反
     *
     * @return
     */
    public Digraph reverse() {
        Digraph R = new Digraph(V);

        for (int v = 0; v < V; v++) {
            for (int w : adj(v)) {
                R.addEdge(w, v);
            }
        }
        return R;
    }

    class Bag<T> implements Iterable {

        @Override
        public Iterator iterator() {

            return null;
        }

        public void add(T w) {

        }
    }
}
