package com.fang.doit.algo.lc.page1;

/**
 * 并查集，连通性问题
 *
 * @author fangfeiyue
 * @Date 2020/12/19 4:43 下午
 */
class UF {

    /**
     * 核心（防止懂了又忘了）：判断两两之间是否联通可找到这两者是否归属于一个父亲节点。
     */

    /**
     * 记录连通分量个数
     */
    private int count;

    /**
     * 存储若干棵树
     */
    private int[] parent;


    /**
     * 记录树的“重量”
     */
    private int[] size;

    public UF(int n) {
        this.count = n;
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    /* 将 p 和 q 连通 */
    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) {
            return;
        }
        // TODO 优化1：小树接到大树下面，较平衡
        if (size[rootP] > size[rootQ]) {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
        } else {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
        }
        count--;
    }

    /* 判断 p 和 q 是否互相连通 */
    public boolean connected(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        // 处于同一棵树上的节点，相互连通
        return rootP == rootQ;
    }

    /* 返回节点 x 的根节点 */
    private int find(int x) {
        while (parent[x] != x) {
            // TODO 优化2：路径压缩。避免不断不断向上遍历，直接全部挂在根节点下面
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }

    public int count() {
        return count;
    }
}