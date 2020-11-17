package com.fang.doit.algo.assignment.wordnet;


import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.List;

/**
 * https://blog.csdn.net/qq_29672495/article/details/81710773
 *
 * @author by Feiyue on 2019/12/9 4:43 PM
 */
public class SAP {

    private final Digraph G;

    private Ancestor minAncestor = null;

    private Ancestor minAncestorIter = null;

    /**
     * constructor takes a digraph (not necessarily a DAG)
     *
     * @param G
     */
    public SAP(Digraph G) {

        if (G == null) {
            throw new IllegalArgumentException("Arguments Error");
        }

        this.G = G;

    }

    /**
     * length of shortest ancestral path between v and w; -1 if no such path
     *
     * @param v
     * @param w
     * @return
     */
    public int length(int v, int w) {

        int total = G.V();
        if (v < 0 || w < 0 || v >= total || w >= total) {
            throw new IllegalArgumentException("Arguments Error");
        }

        List<Ancestor> ancestorList = new ArrayList<>();

        // Breadth v
        Queue<Integer> vQueue = new Queue<>();
        vQueue.enqueue(v);

        boolean[] vMarked = new boolean[total];
        vMarked[v] = true;

        int[] vLength = new int[total];
        vLength[v] = 0;

        while (!vQueue.isEmpty()) {
            Integer n = vQueue.dequeue();
            for (int x : G.adj(n)) {
                if (!vMarked[x]) {
                    vMarked[x] = true;
                    vLength[x] = vLength[n] + 1;
                    vQueue.enqueue(x);
                }
            }
        }

        // Breadth w
        Queue<Integer> wQueue = new Queue<>();
        vQueue.enqueue(w);

        boolean[] wMarked = new boolean[total];
        vMarked[w] = true;

        int[] wLength = new int[total];
        wLength[v] = 0;

        minAncestor = new Ancestor(0, Integer.MAX_VALUE);
        while (!wQueue.isEmpty()) {
            Integer n = wQueue.dequeue();
            for (int y : G.adj(n)) {

                if (!wMarked[y]) {
                    wMarked[y] = true;
                    wLength[y] = wLength[n] + 1;
                    wQueue.enqueue(y);
                }

                // ancestor
                if (vMarked[y]) {

                    Ancestor ancestor = new Ancestor(y, vLength[y] + wLength[y]);
                    ancestorList.add(ancestor);
                    if (minAncestor.getLength() > ancestor.getLength()) {
                        minAncestor = ancestor;
                    }
                }

            }
        }

        if (ancestorList.isEmpty()) {
            return -1;
        }

        return minAncestor.getLength();
    }


    private class Ancestor {

        private int vertices;

        private int length;

        public Ancestor(int vertices, int length) {
            this.vertices = vertices;
            this.length = length;
        }

        public int getVertices() {
            return vertices;
        }

        public void setVertices(int vertices) {
            this.vertices = vertices;
        }

        public int getLength() {
            return length;
        }

        public void setLength(int length) {
            this.length = length;
        }
    }

    /**
     * a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
     *
     * @param v
     * @param w
     * @return
     */
    public int ancestor(int v, int w) {

        length(v, w);

        if (minAncestor == null) {
            return -1;
        }

        return minAncestor.getVertices();
    }

    /**
     * length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
     *
     * @param v
     * @param w
     * @return
     */
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        if (v == null || w == null) {
            throw new IllegalArgumentException("params error");
        }


        Ancestor ancestor = null;

        ancestor = new Ancestor(0, Integer.MAX_VALUE);
        for (int v1 : v) {
            if (v1 < 0 || v1 >= G.V()) {
                throw new IllegalArgumentException("params error");
            }

            for (int w1 : w) {

                if (w1 < 0 || w1 >= G.V()) {
                    throw new IllegalArgumentException("params error");
                }

                length(v1, w1);
                if (minAncestor == null) {
                    continue;
                }


                if (ancestor.getLength() > minAncestor.getLength()) {
                    ancestor = minAncestor;
                }

            }

        }

        if (ancestor.getLength() != Integer.MAX_VALUE) {
            minAncestorIter = ancestor;
            return minAncestorIter.getLength();
        }
        return -1;
    }

    /**
     * a common ancestor that participates in shortest ancestral path; -1 if no such path
     *
     * @param v
     * @param w
     * @return
     */
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {

        length(v, w);

        if (minAncestorIter == null) {
            return -1;
        }

        return minAncestorIter.getVertices();
    }


    /**
     * do unit testing of this class
     *
     * @param args
     */
    public static void main(String[] args) {

        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);

        while (!StdIn.isEmpty()) {
            int v = StdIn.readInt();
            int w = StdIn.readInt();
            int length = sap.length(v, w);
            int ancestor = sap.ancestor(v, w);
            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
        }

    }
}
