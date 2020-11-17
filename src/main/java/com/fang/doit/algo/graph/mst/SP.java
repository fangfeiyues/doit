package com.fang.doit.algo.graph.mst;

import edu.princeton.cs.algs4.EdgeWeightedDigraph;

import java.util.Iterator;

/**
 * 最短路径
 *
 * @author by Feiyue on 2020/1/2 3:50 PM
 */
public class SP {

    private double[] distTo;


    SP(EdgeWeightedDigraph G, int s) {


    }

    /**
     * 放松边v->w 意味着检查s到w的最短路径是否是先从s到v，然后再由v到w。如果是，则根据这个情况更新数据结构的内容
     * 实现放松从给定顶点的所有边
     * @param e
     */
    private void relax(DirectedEdge e) {
        int v = e.from(), w = e.to();
        if (distTo[w] > distTo[v] + e.weight()) {
            distTo[w] = distTo[v] + e.weight();
        }

    }

    /**
     * 从顶点 s 到 v 的距离，如果不存在 则路径为无穷大
     *
     * @param v
     * @return
     */
    public double distTo(int v) {

        return 0L;
    }


    /**
     * 是否存在从顶点 s 到 v 的路径
     *
     * @param v
     * @return
     */
    public boolean hasPathTo(int v) {

        return true;
    }

    /**
     * 从顶点 s 到 v 的路径，如果不存在 则为 null
     *
     * @param v
     * @return
     */
    public Iterable<DirectedEdge> pathTo(int v) {

        return new Iterable<DirectedEdge>() {

            @Override
            public Iterator<DirectedEdge> iterator() {

                return null;
            }

        };

    }
}
