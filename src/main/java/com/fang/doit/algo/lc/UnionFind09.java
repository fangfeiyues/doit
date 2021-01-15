package com.fang.doit.algo.lc;

/**
 * 并查集Union-Find，解决两两连通性问题
 *
 * @author fangfeiyue
 * @Date 2020/12/19 4:02 下午
 */
public class UnionFind09 {


    /**
     * 547: 朋友圈 M[i][j]=1 表示i和j个小朋友在同一个朋友圈
     *
     * @param M i&j=1表示在同一个朋友圈
     * @return 返回公有多少个朋友圈
     */
    public int findCircleNumByUnion(int[][] M) {
        // 并查集合
        UF uf = new UF(M.length);
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < i; j++) {
                if (M[i][j] == 1) {
                    uf.union(i, j);
                }
            }
        }
        return uf.count();
    }


    public int findCircleNumByBFS(int[][] M) {
        // 广度优先算法 两两之间维护一个Map树 再通过遍历

        return 1;
    }
}
