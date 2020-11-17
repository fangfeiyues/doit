package com.fang.doit.algo.graph.mst;

import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.IndexMinPQ;

/**
 * @author by Feiyue on 2019/12/30 8:39 PM
 */
public class PrimMST {

    private Edge[] adgeTo;

    private double[] distTo;

    private boolean[] marked;

    private IndexMinPQ<Double> pq;

    public PrimMST(EdgeWeightedGraph G) {

        adgeTo = new Edge[G.V()];

        distTo = new double[G.V()];

        marked = new boolean[G.V()];

        pq = new IndexMinPQ<Double>(G.V());

        for (int v = 0; v < G.V(); v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[0] = 0.0;

        pq.insert(0, 0.0);

        while (!pq.isEmpty()) {

            // 最近的顶点加到树中
            visit(G, pq.delMin());
        }
    }

    private void visit(EdgeWeightedGraph G, int v) {
        marked[v] = true;
        for (Edge e : G.adj(v)) {
            int w = e.either();
            if (marked[w]) {
                continue;
            }

        }
    }


}
