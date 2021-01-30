package com.fang.doit.algo.lc;

import com.google.common.collect.Maps;

import java.util.*;

/**
 * @author fangfeiyue
 * @Date 2020/12/15 7:05 下午
 * @see TreeSearch 树的宽度&广度实现
 */
public class WidthTree08 {

    /**
     * 787: 站中转内最便宜的航班
     *
     * @param n       n个城市
     * @param flights 中转航班信息 （ [[0,1,100],[1,2,500]] 对于这个的嵌套数组就是一个二维数组 ）
     * @param src     出发地点
     * @param dst     终点
     * @param K       最多经过k站
     * @return 最便宜的价格
     */
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        // 1.小顶推：遍历所有的路径取其中最短的
        findCheapestPriceByHeap(n, flights, src, dst, K);

        // 2.广度优先
        //

        // 3.动态规划dynamic programming = dp
        int[][] dp = new int[2][n];
        // cost最大值是10000，且n最大值是100，2者相乘就是路径总和的MAX
        int MAX_EDGE_SUM = 10001 * n;
        Arrays.fill(dp[0], MAX_EDGE_SUM);
        Arrays.fill(dp[1], MAX_EDGE_SUM);
        dp[0][src] = 0;
        dp[1][src] = 0;
        for (int i = 0; i <= K; i++) {
            // 迭代K + 1次，这里迭代次数很关键，错1次迭代结果就天差地别了。要正确理解i = 0和i == K的边界含义
            for (int[] flight : flights) {
                int flightSrc = flight[0];
                int flightDst = flight[1];
                int cost = flight[2];
                int cur = i & 1;
                int pre = ~i & 1;
                dp[cur][flightDst] = Math.min(dp[cur][flightDst], dp[pre][flightSrc] + cost);
            }
        }
        return dp[K & 1][dst] > MAX_EDGE_SUM ? -1 : dp[K & 1][dst];
    }


    private int findCheapestPriceByHeap(int n, int[][] flights, int src, int dst, int K) {
        // 3.小顶推
        Map<Integer, Map<Integer, Integer>> nextFlightMap = Maps.newHashMap();
        for (int[] flight : flights) {
            // 1个u，可能有多条边，可能对应多个v
            nextFlightMap.computeIfAbsent(flight[0], key -> Maps.newHashMap()).put(flight[1], flight[2]);
        }
        // 遍历每条路线 价格放到小顶推里
        PriorityQueue<CityInfo> heap = new PriorityQueue((Comparator<CityInfo>) (city1, city2) -> city1.cost - city2.cost);
        heap.add(new CityInfo(0, src, K + 1));
        while (!heap.isEmpty()) {
            // 当前最小的顶堆
            CityInfo cityInfo = heap.poll();
            if (cityInfo.hop > K + 1) {
                // 此路不通
                continue;
            }
            if (cityInfo.city == dst) {
                return cityInfo.cost;
            }
            // 还未到达则继续向下遍历
            Map<Integer, Integer> neighbors = nextFlightMap.getOrDefault(cityInfo.city, Maps.newHashMap());
            for (Map.Entry<Integer, Integer> neighbor : neighbors.entrySet()) {
                heap.add(new CityInfo(cityInfo.cost + neighbor.getValue(), neighbor.getKey(), cityInfo.hop + 1));
            }
        }
        return -1;
    }

    private class CityInfo {
        // 价格
        int cost;
        // 经过的站数
        int hop;
        // 最后一站的城市
        int city;

        public CityInfo(int cost, int city, int hop) {
            this.cost = cost;
            this.city = city;
            this.hop = hop;
        }
    }

    // n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]] , src = 0, dst = 2, k = 0  ==> 输出: 500


    /**
     * 743:我们从某个节点 K 发出一个信号。需要多久才能使所有节点都收到信号
     * <p>
     * 输入：times = [[2,1,1],[2,3,1],[3,4,1]], N = 4, K = 2  ==》输出：2
     *
     * @param times 网络时间
     * @param N     节点数
     * @param K     出发节点
     * @return 需要多久才能使所有节点都收到信号
     */
    public int networkDelayTime(int[][] times, int N, int K) {

        // 1. 维护一个大Map，每条路线整个大顶推


        // 2. 深度优先，回溯for方式比较遍历整棵树并取其中最大的一个
        networkDelayTimeByDfs(times, N, K);

        // 3. 广度优先，给每条路径记上时间。如果想直到具体的路径的话
        networkDelayTimeByDjikstra(times, N, K);

        return 0;

    }


    Map<Integer, Integer> dist;

    public int networkDelayTimeByDfs(int[][] times, int N, int K) {
        Map<Integer, List<int[]>> graph = new HashMap();
        for (int[] edge : times) {
            if (!graph.containsKey(edge[0])) {
                graph.put(edge[0], new ArrayList<>());
            }
            graph.get(edge[0]).add(new int[]{edge[2], edge[1]});
        }
        for (int node : graph.keySet()) {
            Collections.sort(graph.get(node), (a, b) -> a[0] - b[0]);
        }
        dist = new HashMap();
        for (int node = 1; node <= N; ++node) {
            dist.put(node, Integer.MAX_VALUE);
        }
        dfs(graph, K, 0);
        int ans = 0;
        for (int cand : dist.values()) {
            if (cand == Integer.MAX_VALUE) {
                return -1;
            }
            ans = Math.max(ans, cand);
        }
        return ans;
    }

    /**
     * 深度递归的典型操作
     *
     * @param graph
     * @param node
     * @param elapsed
     */
    public void dfs(Map<Integer, List<int[]>> graph, int node, int elapsed) {
        // 距离起点收到信号的时间，一个点存在多条路径如果后者到达的时间更长直接丢弃
        if (elapsed >= dist.get(node)) {
            return;
        }
        dist.put(node, elapsed);
        if (graph.containsKey(node)) {
            // 节点的所有子节点深度遍历 并取其中最大的路径长
            for (int[] info : graph.get(node)) {
                dfs(graph, info[1], elapsed + info[0]);
            }
        }
    }

    /**
     * TODO 典型的广度优先算法
     * 怎么理解Djikstra最短路径算法？
     *
     * @param times 邻接矩阵（原来这才是邻接表啊...）
     * @param N
     * @param K
     * @return
     */
    public int networkDelayTimeByDjikstra(int[][] times, int N, int K) {
        Map<Integer, List<int[]>> graph = new HashMap();
        for (int[] edge : times) {
            if (!graph.containsKey(edge[0])) {
                graph.put(edge[0], new ArrayList<>());
            }
            // 写的有点奇怪...为什么就是edge[1],edge[2]这两个节点？
            graph.get(edge[0]).add(new int[]{edge[1], edge[2]});
        }
        dist = new HashMap();
        for (int node = 1; node <= N; ++node) {
            dist.put(node, Integer.MAX_VALUE);
        }
        // 节点距离K的大小
        dist.put(K, 0);
        boolean[] seen = new boolean[N + 1];

        while (true) {
            int candNode = -1;
            int candDist = Integer.MAX_VALUE;
            for (int i = 1; i <= N; ++i) {
                // 在没有遍历定点中找到距离vs最近的定点
                if (!seen[i] && dist.get(i) < candDist) {
                    candDist = dist.get(i);
                    candNode = i;
                }
            }
            if (candNode < 0) {
                break;
            }
            seen[candNode] = true;
            if (graph.containsKey(candNode)) {
                for (int[] info : graph.get(candNode)) {
                    dist.put(info[0], Math.min(dist.get(info[0]), dist.get(candNode) + info[1]));
                }
            }
        }
        int ans = 0;
        for (int cand : dist.values()) {
            if (cand == Integer.MAX_VALUE) {
                return -1;
            }
            ans = Math.max(ans, cand);
        }
        return ans;
    }


    /**
     * 1306: 跳跃游戏
     *
     * @param arr
     * @param start
     * @return
     */
    public boolean canReach(int[] arr, int start) {

        return true;
    }

}
