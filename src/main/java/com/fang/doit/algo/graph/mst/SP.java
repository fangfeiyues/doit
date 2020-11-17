package com.fang.doit.algo.graph.mst;

import edu.princeton.cs.algs4.EdgeWeightedDigraph;

import java.util.Iterator;

/**
 * ���·��
 *
 * @author by Feiyue on 2020/1/2 3:50 PM
 */
public class SP {

    private double[] distTo;


    SP(EdgeWeightedDigraph G, int s) {


    }

    /**
     * ���ɱ�v->w ��ζ�ż��s��w�����·���Ƿ����ȴ�s��v��Ȼ������v��w������ǣ�������������������ݽṹ������
     * ʵ�ַ��ɴӸ�����������б�
     * @param e
     */
    private void relax(DirectedEdge e) {
        int v = e.from(), w = e.to();
        if (distTo[w] > distTo[v] + e.weight()) {
            distTo[w] = distTo[v] + e.weight();
        }

    }

    /**
     * �Ӷ��� s �� v �ľ��룬��������� ��·��Ϊ�����
     *
     * @param v
     * @return
     */
    public double distTo(int v) {

        return 0L;
    }


    /**
     * �Ƿ���ڴӶ��� s �� v ��·��
     *
     * @param v
     * @return
     */
    public boolean hasPathTo(int v) {

        return true;
    }

    /**
     * �Ӷ��� s �� v ��·������������� ��Ϊ null
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
