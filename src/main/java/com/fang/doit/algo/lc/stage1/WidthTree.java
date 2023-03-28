package com.fang.doit.algo.lc.stage1;

import com.fang.doit.algo.tree.TreeNode;
import com.fang.doit.algo.tree.search.TreeSearch;
import com.google.common.collect.Maps;
import com.sun.jmx.remote.internal.ArrayQueue;

import java.util.*;

/**
 * 树的宽度&广度实现
 *
 * @author fangfeiyue
 * @Date 2020/12/15 7:05 下午
 * @see TreeSearch
 */
public class WidthTree {

    // DFS在时间复杂度和空间复杂度上都会比BFS好些，但有些场景是只有BFS能满足的比如：层级遍历、最短路径

    /**
     *  ------------------- 宽度优先搜索的解题技巧！！！ -----------------
     *  1、BFS和DFS遍历的区别：层级遍历是Queue先进先出，深度遍历是Stack先进后出
     *  2、距离某些节点的最短距离：
     *  ------------------------------------------------------
     */


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
    public int findCheapestPrice_01(int n, int[][] flights, int src, int dst, int K) {
        // 1.小顶推：遍历所有的路径取其中最短的
        findCheapestPriceByHeap(n, flights, src, dst, K);

        // 2.广度优先

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
    public int networkDelayTime_02(int[][] times, int N, int K) {

        // 1. 维护一个大Map，每条路线整个大顶推


        // 2. 深度优先，回溯for方式比较遍历整棵树并取其中最大的一个
        networkDelayTimeByDfs(times, N, K);

        // 3. 广度优先，给每条路径记上时间。如果想直到具体的路径的话
        networkDelayTimeByDjikstra(times, N, K);

        return 0;

    }


    Map<Integer, Integer> dist;

    private int networkDelayTimeByDfs(int[][] times, int N, int K) {
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
    private void dfs(Map<Integer, List<int[]>> graph, int node, int elapsed) {
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
     * 典型的广度优先算法 - Djikstra最短路径算法
     *
     * @param times 邻接矩阵（原来这才是邻接表啊...）
     * @param N
     * @param K
     * @return
     */
    private int networkDelayTimeByDjikstra(int[][] times, int N, int K) {
        Map<Integer, List<int[]>> graph = new HashMap();
        for (int[] edge : times) {
            if (!graph.containsKey(edge[0])) {
                graph.put(edge[0], new ArrayList<>());
            }
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
     * 102.给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）。
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder_03_d(TreeNode root) {
        List<List<Integer>> levelList = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        if (root != null) {
            queue.add(root);
        }
        while (!queue.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node == null) {
                    break;
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
                list.add(node.val);
            }
            levelList.add(list);
        }

        return levelList;
    }


    /**
     * 你现在手里有一份大小为 n x n 的 网格 grid，上面的每个 单元格 都用 0 和 1 标记好了。其中 0 代表海洋，1 代表陆地
     * <p>
     * 请你找出一个海洋单元格，这个海洋单元格到离它最近的陆地单元格的距离是最大的，并返回该距离。如果网格上只有陆地或者海洋，请返回 -1
     *
     * @param grid
     * @return 这道题要找的是距离陆地最远的海洋格子。假设网格中只有一个陆地格子，我们可以从这个陆地格子出发做层序遍历，直到所有格子都遍历完。最终遍历了几层，海洋格子的最远距离就是几。
     */
    public int maxDistance_04_ddd(int[][] grid) {
        int N = grid.length;
        // ++ 把固定的点用作一个一维数组放到队列
        Queue<int[]> queue = new ArrayDeque<>();
        // 将所有的陆地格子加入队列
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j] == 1) {
                    queue.add(new int[]{i, j});
                }
            }
        }

        if (queue.isEmpty() || queue.size() == N * N) {
            return -1;
        }

        int[][] moves = {
                {-1, 0}, {1, 0}, {0, -1}, {0, 1},
        };

        int distance = -1;
        while (!queue.isEmpty()) {
            distance++;
            int n = queue.size();
            for (int i = 0; i < n; i++) {
                int[] node = queue.poll();
                int r = node[0];
                int c = node[1];
                for (int[] move : moves) {
                    int r2 = r + move[0];
                    int c2 = c + move[1];
                    if (inArea(grid, r2, c2) && grid[r2][c2] == 0) {
                        grid[r2][c2] = 2;
                        queue.add(new int[]{r2, c2});
                    }
                }
            }
        }

        return distance;
    }

    // 判断坐标 (r, c) 是否在网格中
    boolean inArea(int[][] grid, int r, int c) {
        return 0 <= r && r < grid.length
                && 0 <= c && c < grid[0].length;
    }


    /**
     * 103.给你二叉树的根节点 root ，返回其节点值的 锯齿形层序遍历 （即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）
     *
     * @param root
     * @return
     */
    public List<List<Integer>> zigzagLevelOrder_05_d(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        boolean isOrderLeft = true;
        while (!queue.isEmpty()) {
            Deque<Integer> levelList = new LinkedList<>();
            // ++ 遍历queue队列的每一层的时候 依次把节点放到双向队列的头部或尾部
            for (int i = 0; i < queue.size(); i++) {
                TreeNode node = queue.poll();
                if (isOrderLeft) {
                    levelList.offerLast(node.val);
                } else {
                    levelList.offerFirst(node.val);
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            result.add(new LinkedList<>(levelList));
            isOrderLeft = !isOrderLeft;
        }
        return result;
    }


    /**
     * 322. 零钱兑换
     * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1
     * 你可以认为每种硬币的数量是无限的
     *
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange_06_dd(int[] coins, int amount) {
        // 大顶推 从大到小依次处理即可
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o));
        for (int i = 0; i < coins.length; i++) {
            priorityQueue.offer(coins[i]);
        }
        int min = 0;
        while (!priorityQueue.isEmpty()) {
            Integer coin = priorityQueue.poll();

        }
        return 0;
    }
    

}
