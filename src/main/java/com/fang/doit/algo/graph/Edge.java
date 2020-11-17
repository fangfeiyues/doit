package com.fang.doit.algo.graph;

/**
 * ´øÈ¨±ß
 *
 * @author by Feiyue on 2019/12/26 7:21 PM
 */
public class Edge implements Comparable<Edge> {

    private final int v;

    private final int w;

    private final double weight;

    public Edge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge that) {
        if (this.weight < that.weight) {
            return -1;
        }

        return 0;
    }


}
