package com.fang.doit.algo.lc.stage1;

import com.fang.doit.algo.tree.TreeNode;

import java.util.*;

/**
 * 深度优先搜索
 *
 * @author created by fang on 2021/7/11/011 1:29
 */
public class DepthTree {


    /**
     *  ------------------- 深度优先搜索的解题技巧！！！ -----------------
     *  1.二叉树的遍历方式主要有两种「 递归 & 堆栈 」
     *    ** 递归的核心在于：理解递归方法开始&结束时候代表的含义 当前树节点下的所有子树
     *    ** 堆栈的核心在于：
     *
     *  ------------------------------------------------------
     */


    /**
     * 230：给定一个二叉搜索树的根节点 root，和一个整数 k 请你设计一个算法查找其中第 k 个最小元素（从 1 开始计数）
     * 进阶：如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化算法？
     *
     * @param root
     * @param k
     * @return
     */
    public int kthSmallest_01(TreeNode root, int k) {

        kthSmallestByMiddle(root, k);

        // TODO 2.比较左右节点两边的节点数是否大于k
        return 0;
    }

    private int kthSmallestByMiddle(TreeNode root, int k) {
        // ++ 中序遍历：从小到大依次出栈即可找到第k小的元素
        Stack<TreeNode> stack = new Stack<>();
        while (true) {
            while (root != null) {
                stack.add(root);
                root = root.left;
            }
            if (stack.isEmpty()) {
                return 0;
            }
            TreeNode node = stack.pop();
            if (--k == 0) {
                return node.val;
            }
            root = node.right;
        }
    }


    /**
     * 113：给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 根节点->叶子节点 路径总和等于给定目标和的路径
     *
     * @param root
     * @param targetSum
     * @return
     */

    public List<List<Integer>> pathSum_02(TreeNode root, int targetSum) {
        pathSumDfs(root, targetSum);
        return ret;
    }

    List<List<Integer>> ret = new LinkedList<>();
    Deque<Integer> path = new LinkedList<>();

    private void pathSumDfs(TreeNode root, int targetSum) {
        if (root == null) {
            return;
        }
        targetSum = targetSum - root.val;
        path.offerLast(root.val);
        // ++ 树节点开始：如果当前节点左右子节点都是空且targetSum减为0 则代表path满足了
        if (root.left == null && root.right == null && targetSum == 0) {
            ret.add(new LinkedList<>(path));
        }
        pathSumDfs(root.left, targetSum);
        pathSumDfs(root.right, targetSum);
        // ++ 树节点结束：把当前节点移除，开始处理上一个节点
        path.pollLast();
    }


    /**
     * 129:求根结点到叶子节点数字之和
     * 解答成功: 执行耗时:8 ms,击败了5.22% 的Java用户 内存消耗:37 MB,击败了5.05% 的Java用户
     *
     * @param root
     * @return
     */
    private int all = 0;

    public int sumNumbers_03(TreeNode root) {
        sumNumberDfs(root, "");
        return all;
    }

    private void sumNumberDfs(TreeNode root, String split) {
        if (root == null) {
            return;
        }
        split = split + "" + root.val;
        if (root.left == null && root.right == null) {
            all = all + Integer.parseInt(split);
        }
        sumNumberDfs(root.left, split);
        sumNumberDfs(root.right, split);
    }


    /**
     * 236:***** 二叉树的最近公共祖先
     * 解答成功: 执行耗时:50 ms,击败了5.54% 的Java用户 内存消耗:43.3 MB,击败了5.00% 的Java用户
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    Deque<TreeNode> deque = new LinkedList<>();

    public TreeNode lowestCommonAncestor_04(TreeNode root, TreeNode p, TreeNode q) {
        commonAncestorDfs(root, p, q);
        return deque.pollFirst();
    }

    // 这段递归有点难理解.. -- 理解递归开始和结束代表的含义
    private int commonAncestorDfs(TreeNode root, TreeNode p, TreeNode q) {
        int num = 0;
        if (root == null) {
            return 0;
        }
        // ++ 当前节点开始：如果节点就是所求节点则+1
        if (root.val == p.val || root.val == q.val) {
            num = num + 1;
        }
        int leftNums = commonAncestorDfs(root.left, p, q);
        int rightNums = commonAncestorDfs(root.right, p, q);
        // ++ 当前节点结束：当前节点 + 左子树节点 + 右子树路过节点的数量相加大于等于2则说明其子树下有两个节点！
        int alls = num + leftNums + rightNums;
        if (alls >= 2) {
            deque.addLast(root);
        }
        return alls;
    }


    /**
     * 743：网络延迟时间。从某个节点 K 发出一个信号。需要多久才能使所有节点都收到信号？如果不能使所有节点收到信号，返回 -1
     *
     * @param times
     * @param n
     * @param k
     * @return
     */

    int maxTimes = Integer.MIN_VALUE;
    List<Integer> has = new ArrayList<>();

    public int networkDelayTime_05(int[][] times, int n, int k) {
        if (times.length == 0) {
            return 0;
        }
        // 要进行转换Map<> FIXME int[][] 这里的edge = int[0]那么edge[0]就是第一个一维数组的第一位
        Map<Integer, List<int[]>> map = new HashMap<>();
        for (int i = 0; i < times.length; i++) {
            int start = times[i][0];
            int end = times[i][1];
            int value = times[i][2];
            if (!map.containsKey(start)) {
                map.put(start, new ArrayList<>());
            }
            map.get(start).add(new int[]{end, value});
        }
        netDfs(map, k, 0);
        if (has.size() < n) {
            return -1;
        }
        return maxTimes;
    }

    private void netDfs(Map<Integer, List<int[]>> map, int k, int allTime) {
        List<int[]> nexts = map.get(k);
        if (has.contains(k)) {
            return;
        }
        has.add(k);
        if (allTime > maxTimes) {
            maxTimes = allTime;
        }
        if (nexts == null) {
            return;
        }
        // ++ 核心在于这里用for循环递归下一层代替了左右子树递归下一层
        for (int[] point : nexts) {
            int next = point[0];
            int value = point[1];
            netDfs(map, next, allTime + value);
        }
    }


    /**
     * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树
     * 有效 二叉搜索树定义如下：
     * 1、节点的左子树只包含 小于 当前节点的数。
     * 2、节点的右子树只包含 大于 当前节点的数。
     * 3、所有左子树和右子树自身必须也是二叉搜索树。
     *
     * @param root
     * @return
     */
    public boolean isValidBST_06_dd(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean validBSTDfs_error01(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return true;
        }
        // -- 不是和当前节点的大小比 而是跟整颗树的根节点比
        if (root.left != null && root.val <= root.left.val) {
            return false;
        }
        if (root.right != null && root.val >= root.right.val) {
            return false;
        }
        boolean left = validBSTDfs_error01(root.left);
        boolean right = validBSTDfs_error01(root.right);
        // 思考错误：子树成立不代表加上当前节点就能成立 如[5,4,6,null,null,3,7]
        return left && right;
    }

    private boolean isValidBST(TreeNode node, long lower, long upper) {
        if (node == null) {
            return true;
        }
        // ++当前节点开始：节点大小必须在(l,u)之间
        if (node.val <= lower || node.val >= upper) {
            return false;
        }
        // 左子树的max就是node.val,min就是intrger.min ; 同理右子树 -- 有点难理解!!!
        boolean left = isValidBST(node.left, lower, node.val);
        boolean right = isValidBST(node.right, node.val, upper);
        // ++当前节点结束：左右子树都满足
        return left && right;
    }


    /**
     * 114. 给你二叉树的根结点 root ，请你将它展开为一个单链表
     * 1、展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
     * 2、展开后的单链表应该与二叉树 先序遍历 顺序相同。
     *
     * @param root
     */
    public void flatten_07(TreeNode root) {
        // ++ 先序遍历后把元素都放到列表中；再从列表转成链表
        List<TreeNode> list = new ArrayList<>();
        preorderTraversal(root, list);
        int size = list.size();
        for (int i = 1; i < size; i++) {
            TreeNode prev = list.get(i - 1), curr = list.get(i);
            prev.left = null;
            prev.right = curr;
        }
    }

    private void preorderTraversal(TreeNode root, List<TreeNode> list) {
        if (root != null) {
            list.add(root);
            preorderTraversal(root.left, list);
            preorderTraversal(root.right, list);
        }
    }

    /**
     * 207.你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1
     * 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi
     * <p>
     * eg.numCourses = 2, prerequisites = [[1,0],[0,1]] ==> false
     *
     * @param numCourses
     * @param prerequisites
     * @return 请你判断是否可能完成所有课程的学习
     */
    List<List<Integer>> edges;
    int[] visited;
    boolean valid = true;

    public boolean canFinish_08(int numCourses, int[][] prerequisites) {
        edges = new ArrayList<>();
        for (int i = 0; i < numCourses; ++i) {
            edges.add(new ArrayList<>());
        }
        visited = new int[numCourses];
        for (int[] info : prerequisites) {
            // ++ [[1,0],[0,1]] 把数组转换为List（这里的数字是可复用的）
            edges.get(info[1]).add(info[0]);
        }
        for (int i = 0; i < numCourses && valid; ++i) {
            if (visited[i] == 0) {
                dfs(i);
            }
        }
        return valid;
    }

    private void dfs(int u) {
        visited[u] = 1;
        for (int v : edges.get(u)) {
            if (visited[v] == 0) {
                dfs(v);
                if (!valid) {
                    return;
                }
            } else if (visited[v] == 1) {
                valid = false;
                return;
            }
        }
        visited[u] = 2;
    }

    /**
     * 333.给定一个二叉树，找到其中最大的二叉搜索树（BST）子树，并返回该子树的大小 其中，最大指的是子树节点数最多的
     * 二叉搜索树（BST）中的所有节点都具备以下属性：
     * 1、左子树的值小于其父（根）节点的值
     * 2、右子树的值大于其父（根）节点的值
     * <p>
     * 进阶: 你能想出 O(n) 时间复杂度的解法吗
     *
     * @param root
     * @return
     */
    public int largestBSTSubtree_09(TreeNode root) {
        return 0;
    }


    // f(o) 表示选择 o 节点的情况下，o 节点的子树上被选择的节点的最大权值和
    Map<TreeNode, Integer> f = new HashMap<>();
    // g(o) 表示不选择 o 节点的情况下，o 节点的子树上被选择的节点的最大权值和
    Map<TreeNode, Integer> g = new HashMap<>();

    /**
     * 除了 root 之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果 两个直接相连的房子在同一天晚上被打劫 ，房屋将自动报警
     * 给定二叉树的 root 。返回 在不触动警报的情况下 ，小偷能够盗取的最高金额 。
     *
     * @param root
     * @return
     */
    public int rob_10_d(TreeNode root) {
        dfs(root);
        return Math.max(f.getOrDefault(root, 0), g.getOrDefault(root, 0));
    }

    private void dfs(TreeNode node) {
        if (node == null) {
            return;
        }
        dfs(node.left);
        dfs(node.right);
        // +++ 并不知道当前的树节点是否要被选择，故每个节点都有两种选择！
        f.put(node, node.val + g.getOrDefault(node.left, 0) + g.getOrDefault(node.right, 0));
        g.put(node, Math.max(f.getOrDefault(node.left, 0), g.getOrDefault(node.left, 0)) + Math.max(f.getOrDefault(node.right, 0), g.getOrDefault(node.right, 0)));
    }


    // 当前节点是否是搜索二叉树；节点树的最大值，节点树的最小值
    private boolean is_BST_DFS(TreeNode root, Integer lower, Integer upper) {
        if (root == null) {
            return true;
        }
        //
        if (root.val < lower || root.val > upper) {
            return false;
        }
        boolean left = is_BST_DFS(root.left, lower, root.val);
        boolean right = is_BST_DFS(root.right, root.val, upper);
        return left & right;
    }


    /**
     * 386. 给你一个整数 n ，按字典序返回范围 [1, n] 内所有整数, 你必须设计一个时间复杂度为 O(n) 且使用 O(1) 额外空间的算法
     * eg.输入：n = 13
     * 输出：[1,10,11,12,13,2,3,4,5,6,7,8,9]
     *
     * @param n
     * @return
     */
    public List<Integer> lexicalOrder_11(int n) {

        return null;
    }


    /**
     * 417.给定一个 m x n 的整数矩阵 heights ， heights[r][c] 表示坐标 (r, c) 上单元格 高于海平面的高度
     * 返回网格坐标 result 的 2D 列表 ，其中 result[i] = [ri, ci] 表示雨水从单元格 (ri, ci) 流动 既可流向太平洋也可流向大西洋 。
     *
     * @param heights
     * @return
     */
    // ++ 前后左右四个方位
    static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    int[][] heights;
    int m, n;

    public List<List<Integer>> pacificAtlantic_12_d(int[][] heights) {
        this.heights = heights;
        this.m = heights.length;
        this.n = heights[0].length;
        boolean[][] pacific = new boolean[m][n];
        boolean[][] atlantic = new boolean[m][n];
        // 上横方位
        for (int i = 0; i < m; i++) {
            pacificAtlanticDFS(i, 0, pacific);
        }
        // 左竖方位
        for (int j = 1; j < n; j++) {
            pacificAtlanticDFS(0, j, pacific);
        }
        // 右竖方位
        for (int i = 0; i < m; i++) {
            pacificAtlanticDFS(i, n - 1, atlantic);
        }
        // 下横方位
        for (int j = 0; j < n - 1; j++) {
            pacificAtlanticDFS(m - 1, j, atlantic);
        }
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    List<Integer> cell = new ArrayList<>();
                    cell.add(i);
                    cell.add(j);
                    result.add(cell);
                }
            }
        }
        return result;
    }

    private void pacificAtlanticDFS(int row, int col, boolean[][] ocean) {
        if (ocean[row][col]) {
            return;
        }
        ocean[row][col] = true;
        for (int[] dir : dirs) {
            int newRow = row + dir[0], newCol = col + dir[1];
            if (newRow >= 0 && newRow < m && newCol >= 0 && newCol < n && heights[newRow][newCol] >= heights[row][col]) {
                pacificAtlanticDFS(newRow, newCol, ocean);
            }
        }
    }


    /**
     * 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在 水平或者竖直的四个方向上 相邻
     * 你可以假设 grid 的四个边缘都被 0（代表水）包围着
     *
     * @param grid
     * @return 计算并返回 grid 中最大的岛屿面积
     */
    int[][] pairs = new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    int maxArea = 0;
    int islandM, islandN;
    int[][] visitedArea;

    public int maxAreaOfIsland(int[][] grid) {
        islandM = grid.length;
        islandN = grid[0].length;
        // 四个方位开始
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                int max = 0;
                maxAreaDFS(grid, i, j, max);
                maxArea = Math.max(max, maxArea);
            }
        }
        return maxArea;
    }

    private void maxAreaDFS(int[][] grid, int row, int col, int max) {
        if (visitedArea[row][col] == 1) {
            return;
        }
        for (int[] pair : pairs) {
            int i = row - pair[0];
            int j = col - pair[1];
            if (i < islandM && j < islandN && i >= 0 && j >= 0 && grid[row - i][col - j] == 1) {
                max++;
                maxAreaDFS(grid, i, j, max);
            }
        }
    }


    public static void main(String[] args) {

        TreeNode treeNode6 = new TreeNode(6);
        TreeNode treeNode5 = new TreeNode(5, null, treeNode6);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode2 = new TreeNode(2, treeNode3, treeNode4);
        TreeNode treeNode1 = new TreeNode(1, treeNode2, treeNode5);

        DepthTree depthTree = new DepthTree();
        depthTree.flatten_07(treeNode1);
    }


}
