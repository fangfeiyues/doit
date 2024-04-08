package com.fang.doit.algo.lc.top;

import com.fang.doit.algo.dst.linked.ListNode;
import com.fang.doit.algo.tree.TreeNode;

import java.util.*;

/**
 * @author : fangfeiyue
 * @version V1.0
 * @Project: doit
 * @Package com.fang.doit.algo.lc.test
 * @Description: 2023.05~2024.05刷题记录
 * @date Date : 2023-05-07 4:21 下午
 */
public class Top {


    /**
     * 343. 给定一个正整数 n ，将其拆分为 k 个 正整数 的和（ k >= 2 ），并使这些整数的乘积最大化。返回 你可以获得的最大乘积
     *
     * n = 10 ==> 10 = 3 + 3 + 4, 3 × 3 × 4 = 36
     *
     * @param n
     * @return
     */
    public int integerBreak(int n) {

        return 0;
    }

    /**
     * 340.给你一个字符串 s 和一个整数 k ，请你找出 至多 包含 k 个 不同 字符的最长子串 并返回该子串的长度
     * s = "eceba", k = 2 ==> 3 "ece"
     * s = "aa", k = 1 ==> 2 "aa"
     *
     * @param s
     * @param k
     * @return
     */
    public int lengthOfLongestSubstringKDistinct(String s, int k) {

        return 0;
    }

    /**
     * 314. 二叉树的垂直遍历
     * root = [3,9,20,null,null,15,7] ==》 [[9],[3,15],[20],[7]]
     *
     * @param root
     * @return
     */
    public List<List<Integer>> verticalOrder(TreeNode root) {
        return null;
    }

    /**
     * 316. 给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证 返回结果的
     * 字典序 最小（要求不能打乱其他字符的相对位置）
     *
     * s = "bcabc" ==> abc
     * s = "cbacdcbc" ==> acdb
     * @param s
     * @return
     */
    public String removeDuplicateLetters(String s) {
        return null;
    }

    /**
     * 315.给你一个整数数组 nums ，按要求返回一个新数组 counts 。数组 counts 有该性质： counts[i] 的值是  nums[i] 右侧小于 nums[i] 的元素的数量
     *
     * nums = [5,2,6,1] ==> [2,1,1,0]
     *
     * 5 的右侧有 2 个更小的元素 (2 和 1)
     * 2 的右侧仅有 1 个更小的元素 (1)
     * 6 的右侧有 1 个更小的元素 (1)
     * 1 的右侧有 0 个更小的元素
     * @param nums
     * @return
     */
    public List<Integer> countSmaller(int[] nums) {
        // TODO 归并插入有点难
        return null;
    }

    /**
     * 334.整数数组 nums ，判断这个数组中是否存在长度为3的递增子序列（递增不限于连续）
     * nums = [1,2,3,4,5] ==> true
     * nums = [5,4,3,2,1] ==> false
     * nums = [2,1,5,0,4,6] ==> true
     *
     * @param nums
     * @return
     */
    public boolean x_increasingTriplet_334(int[] nums) {
        int n = nums.length;
        if (n < 3) {
            return false;
        }
        // 表示在某个位置i左侧的最小值
        int[] leftMin = new int[n];
        leftMin[0] = nums[0];
        for (int i = 1; i < n; i++) {
            leftMin[i] = Math.min(leftMin[i - 1], nums[i]);
        }
        // 表示在某个位置i右侧的最大值
        int[] rightMax = new int[n];
        rightMax[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], nums[i]);
        }
        // 当i的位置 大于左边最小值 且 小于右边最大值 的时候说明可行
        for (int i = 1; i < n - 1; i++) {
            if (nums[i] > leftMin[i - 1] && nums[i] < rightMax[i + 1]) {
                return true;
            }
        }
        return false;
    }


    /**
     * 333. 给定一个二叉树，找到其中最大的二叉搜索树（BST）子树并返回该子树的大小（最大指的是子树节点数最多的）
     * <p>
     * 输入：root = [10,5,15,1,8,null,7] ==> 输出：3
     * 输入：root = [4,2,7,2,3,5,null,2,null,null,null,null,null,1] ==> 输出：2
     *
     * @param root
     * @return
     */
    int largestBSTSubtreeRes = 0;
    public int xxx_largestBSTSubtree_333(TreeNode root) {
        if (root == null) {
            return 0;
        }
        dfs(root);
        return largestBSTSubtreeRes;
    }

    private Node dfs(TreeNode root) {
        if (root.left == null && root.right == null) {
            largestBSTSubtreeRes = Math.max(largestBSTSubtreeRes, 1);
            return new Node(root.val, root.val, 1);
        }
        int min = root.val;
        int max = root.val;
        int size = 1;
        boolean valid = true;
        // 左子树：存在子树 且 跟节点大于子树最大值 则重置节点size、L、R
        if (root.left != null) {
            Node L = dfs(root.left);
            if (L.size != -1 && root.val > L.max) {
                size += L.size;
                min = L.min;
            } else {
                valid = false;
            }
        }
        // 右子树：存在子树 且 跟节点小于子树最小值 则重置节点size、L、R
        if (root.right != null) {
            Node R = dfs(root.right);
            if (R.size != -1 && root.val < R.min) {
                size += R.size;
                max = R.max;
            } else {
                valid = false;
            }
        }
        // 如果左右子树任意一个非BST，那么根节点都不是BST
        if (valid) {
            largestBSTSubtreeRes = Math.max(size, largestBSTSubtreeRes);
            return new Node(min, max, size);
        }
        return new Node(-1, -1, -1);
    }

    class Node {
        //最小值
        int min;
        //最大值
        int max;
        //节点个数，如果不是bst则为-1
        int size;

        Node(int min, int max, int size) {
            this.min = min;
            this.max = max;
            this.size = size;
        }
    }

//    public static void main(String[] args) {
//        TreeNode root = new TreeNode(10, new TreeNode(5,new TreeNode(1),new TreeNode(8)), new TreeNode(15,null,new TreeNode(7)));
//        Top top = new Top();
//        System.out.println(top.xxx_largestBSTSubtree_333(root));
//    }


    //    public int largestBSTSubtree_ee(TreeNode root) {
//        return largestBSTSubtreeDFS(root,0);
//    }
//
//    public int largestBSTSubtreeDFS(TreeNode root, int path) {
//        if (root == null) {
//            return path;
//        }
//        // 后序遍历
//        int left = largestBSTSubtreeDFS(root.left, path);
//        int right = largestBSTSubtreeDFS(root.right, path);
//        // 叶子节点：只有一个
//        if (root.right == null && root.left == null) {
//            return 1;
//        }
//        // 左右节点都行：
//        if ((root.left != null && root.val > root.left.val)
//                && (root.right != null && root.val < root.right.val)) {
//            return left + right + 1;
//        }
//        return 0;
//    }


    /**
     * 329.给定一个 m x n 整数矩阵 matrix，找出其中最长递增路径的长度（对于每个单元格，你可以往上下左右四个方向移动）
     * matrix = [[9,9,4],[6,6,8],[2,1,1]]  输出：4 解释：最长递增路径为 [1, 2, 6, 9]
     * matrix = [[3,4,5],[3,2,6],[2,2,1]] 输出：4 解释：最长递增路径是 [3, 4, 5, 6]
     *
     * @param matrix
     * @return
     */
    int[][] moves = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    int longestIncreasingPath = 0;

    public int x_longestIncreasingPath_329(int[][] matrix) {
        if (matrix.length == 0) {
            return 0;
        }
        // ⚠️ 每个节点前后左右递归判断 -- 超出时间限制!!!
        int[][] used = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                int path = longestIncreasingPathDFS(i, j, matrix, used, 1);
                used[i][j] = path;
                longestIncreasingPath = Math.max(longestIncreasingPath, path);
            }
        }
        return longestIncreasingPath;
    }

    private int longestIncreasingPathDFS(int row, int col, int[][] matrix, int[][] used, int path) {
        if (used[row][col] > 0) {
            return path + used[row][col] - 1;
        }
        int next = path;
        for (int i = 0; i < moves.length; i++) {
            int[] move = moves[i];
            // 最上面不能在往上走
            if (row == 0 && move[0] < 0) {
                continue;
            }
            // 最下面不能在往下走
            if (row == matrix.length - 1 && move[0] > 0) {
                continue;
            }
            // 最左面不能在往左走
            if (col == 0 && move[1] < 0) {
                continue;
            }
            // 最右面不能在往右走
            if (col == matrix[0].length - 1 && move[1] > 0) {
                continue;
            }
            int newRow = row + move[0];
            int newCol = col + move[1];
            if (matrix[newRow][newCol] <= matrix[row][col]) {
                continue;
            }
            path = Math.max(path, longestIncreasingPathDFS(newRow, newCol, matrix, used, next + 1));
        }
        return path;
    }


//    public static void main(String[] args) {
//        Top top = new Top();
//        // [[3,4,5],[3,2,6],[2,2,1]]
//        // [[9,9,4],[6,6,8],[2,1,1]]
//        // [[7,7,5],[2,4,6],[8,2,0]]
//        // int[][] matrix = {{3, 4, 5}, {3, 2, 6}, {2, 2, 1}};
//        // int[][] matrix = {{9, 9, 4}, {6, 6, 8}, {2, 1, 1}};
//        int[][] matrix = {{7, 7, 5}, {2, 4, 6}, {8, 2, 0}};
//        System.out.println(top.longestIncreasingPath_329(matrix));
//    }

    /**
     * 328.给定单链表的头节点 head ，将所有索引为奇数的节点和索引为偶数的节点分别组合在一起，然后返回重新排序的列表。
     *
     * 第一个节点的索引被认为是奇数 ， 第二个节点的索引为偶数 ，以此类推。
     *
     * 请注意，偶数组和奇数组内部的相对顺序应该与输入时保持一致。
     *
     * 你必须在 O(1) 的额外空间复杂度和 O(n) 的时间复杂度下解决这个问题
     *
     *  head = [1,2,3,4,5] ==> [1,3,5,2,4]
     *
     *  head = [2,1,3,5,6,4,7] ==> [2,3,6,7,1,5,4]
     *
     * @param head
     * @return
     */
    public ListNode oddEvenList(ListNode head) {

        return null;
    }

    /**
     * 325.给定一个数组 nums 和一个目标值 k，找到和等于 k 的最长连续子数组长度。如果不存在任意一个符合要求的子数组，则返回 0
     * <p>
     * nums = [1,-1,5,-2,3], k = 3
     *
     * @param nums
     * @param k
     * @return
     */
    private static int maxSubArrayLen_ee(int[] nums, int k) {
        // 滑动窗口啊
        int windowSum = 0;
        int max = 0;
        Queue<Integer> queue = new LinkedList<>();
        for (int j : nums) {
            queue.add(j);
            windowSum = windowSum + j;
            // windowSum存在负数的话，就不满足滑动窗口，不能保证窗口内的一定是最大值
            while (queue.size() > 0 && windowSum > k) {
                int num = queue.poll();
                windowSum -= num;
            }
            if (windowSum == k) {
                max = Math.max(max, queue.size());
            }
        }
        return max;
    }

//    public static void main(String[] args) {
//        int[] nums = {3, 1, -1, 5, -2, 3};
//        System.out.println(xxx_maxSubArrayLen_325(nums, 3));
//    }

    public static int xxx_maxSubArrayLen_325(int[] nums, int k) {
        int prefixSum = 0;
        int longestSubarray = 0;
        // <前缀和，位置i>
        HashMap<Integer, Integer> indices = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];
            // 最长的连续子数组为k的可能性有二
            // 1、从头开始连续大小为k
            if (prefixSum == k) {
                longestSubarray = i + 1;
            }
            // 2、某一段区间大小为k，即[prefixSum - k, prefixSum]
            if (indices.containsKey(prefixSum - k)) {
                longestSubarray = Math.max(longestSubarray, i - indices.get(prefixSum - k));
            }
            // 不更新prefixSum，保证区间值最大
            if (!indices.containsKey(prefixSum)) {
                indices.put(prefixSum, i);
            }
        }
        return longestSubarray;
    }


    /**
     * 324.摆动排序 给你一个整数数组 nums，将它重新排列成 nums[0] < nums[1] > nums[2] < nums[3]... 的顺序
     * <p>
     * nums = [1,5,1,1,6,4] ==> [1,6,1,5,1,4]
     * 解释：[1,4,1,5,1,6] 同样是符合题目要求的结果，可以被判题程序接受
     *
     * @param nums
     */
    public void wiggleSort_324(int[] nums) {
        // 暴力点：排序后分成两组，然后合并插入即可O(n^2)
        Arrays.sort(nums);

        // 归并排序：merge的时候直接按照小大来？-- 归并的优势在哪
        // 小1大1小1大1 小2大2小2大2

        // 三指针重新排序
        int[] arr = nums.clone();
        Arrays.sort(arr);
        int n = nums.length;
        int x = (n + 1) / 2;
        // 两个点：一个从中间开始，一个从最后开始，依次往前填塞
        for (int i = 0, j = x - 1, k = n - 1; i < n; i += 2, j--, k--) {
            nums[i] = arr[j];
            if (i + 1 < n) {
                nums[i + 1] = arr[k];
            }
        }
    }

    /**
     * 323：无向图中连通分量的数目
     * 你有一个包含 n 个节点的图。给定一个整数 n 和一个数组 edges ，其中 edges[i] = [ai, bi] 表示图中 ai 和 bi 之间有一条边
     * n = 5, [[0, 1], [1, 2], [3, 4]] ==> 2
     * n = 5, [[0,1], [1,2], [2,3], [3,4]] ==> 1
     *
     * n=5 [[0,1],[0,4],[1,4],[2,3]]
     * @param n
     * @param edges
     * @return 图中已连接分量的数目
     */
    private static int ee_countComponents_323(int n, int[][] edges) {
        // 排序后，然后遍历
        Arrays.sort(edges, Comparator.comparingInt(v -> v[0]));
        int pre = edges[0][1];
        int count = 1;
        for (int i = 1; i < edges.length; i++) {
            if (edges[i][0] > pre) {
                count++;
            }
            pre = edges[i][1];
        }
        return count;
    }

    public int xxx_countComponents_323(int n, int[][] edges) {
        int components = 0;
        int[] visited = new int[n];
        // 数组列表 或者 哈希列表都可
        List<Integer>[] adjList = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<>();
        }
        for (int i = 0; i < edges.length; i++) {
            // 双向联通的 a->b b->a
            adjList[edges[i][0]].add(edges[i][1]);
            adjList[edges[i][1]].add(edges[i][0]);
        }

        for (int i = 0; i < n; i++) {
            if (visited[i] == 0) {
                // 这么简单的DFS.. 怎么想不到呢
                components++;
                countComponentsDFS(adjList, visited, i);
            }
        }
        return components;
    }

    private void countComponentsDFS(List<Integer>[] adjList, int[] visited, int startNode) {
        visited[startNode] = 1;
        for (int i = 0; i < adjList[startNode].size(); i++) {
            if (visited[adjList[startNode].get(i)] == 0) {
                countComponentsDFS(adjList, visited, adjList[startNode].get(i));
            }
        }
    }


    public static void main(String[] args) {
        // [[0,1],[0,4],[1,4],[2,3]]
//        int[][] edges = {{0, 1}, {1, 2}, {3, 4}};
//        System.out.println(ee_countComponents_323(5, edges));
    }

    /**
     * 322. 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
     * <p>
     * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
     * <p>
     * 你可以认为每种硬币的数量是无限的
     * <p>
     * <p>
     * 输入：coins = [1, 2, 5], amount = 11
     * 输出：3
     * 解释：11 = 5 + 5 + 1
     *
     * @param coins
     * @param amount
     * @return
     */
    public static int coinChange_322(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        Arrays.sort(coins);
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] > i) {
                    break;
                }
                dp[i] = Math.min(dp[i - coins[j]] + 1, dp[i]);
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

//    public static void main(String[] args) {
//        int[] coins = {1,2,5};
//        System.out.println(coinChange_322(coins, 11));
//    }

    /**
     * 319. 灯泡开关
     *
     * 初始时有 n 个灯泡处于关闭状态。第一轮，你将会打开所有灯泡。接下来的第二轮，你将会每两个灯泡关闭第二个。
     *
     * 第三轮，你每三个灯泡就切换第三个灯泡的开关（即，打开变关闭，关闭变打开）。第 i 轮，你每 i 个灯泡就切换第 i 个灯泡的开关。直到第 n 轮，你只需要切换最后一个灯泡的开关。
     *
     * 找出并返回 n 轮后有多少个亮着的灯泡
     *
     * 输入：n = 3
     * 输出：1
     * 解释：
     * 初始时, 灯泡状态 [关闭, 关闭, 关闭].
     * 第一轮后, 灯泡状态 [开启, 开启, 开启].
     * 第二轮后, 灯泡状态 [开启, 关闭, 开启].
     * 第三轮后, 灯泡状态 [开启, 关闭, 关闭].
     * @param n
     * @return
     */
    public int bulbSwitch_319(int n) {
        // 暴力循环 1:开；0：关 -- 直接超出时间限制
        int[] nums = new int[n];
        Arrays.fill(nums, 1);
        for (int i = 1; i < n; i++) {
            // 时间复杂度:O(n^2)
            for (int j = 0; j < n; j++) {
                // 整除 /；取余 %
                if ((j + 1) % (i + 1) == 0) {
                    nums[j]++;
                }
            }
        }
        int num = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] % 2 == 1) {
                num++;
            }
        }

        // 看一个数被[1,n]能整除多少次即可 --
        // 只有当 k 是「完全平方数」时，它才会有奇数个约数，否则一定有偶数个约数 ？？
        return (int) Math.sqrt(n + 0.5);
    }


    /**
     * 313.超级丑数 是一个正整数，并满足其所有质因数都出现在质数数组 primes 中，给你一个整数 n 和一个整数数组 primes ，
     *
     * n = 12, primes = [2,7,13,19] ==》32 ，12 个超级丑数序列为：[1,2,4,7,8,13,14,16,19,26,28,32]
     * @param n
     * @param primes
     * @return 返回第 n 个 超级丑数
     * @see
     */
    public int xxx_nthSuperUglyNumber(int n, int[] primes) {
        // 记录第n次的最小数
        int[] dp = new int[n + 1];
        int m = primes.length;
        // 记录丑数列表的每个数字累加了多少次
        int[] pointers = new int[m];
        // 记录在point次后该位数的最小值
        int[] nums = new int[m];
        Arrays.fill(nums, 1);
        for (int i = 1; i <= n; i++) {
            int minNum = Arrays.stream(nums).min().getAsInt();
            dp[i] = minNum;
            // 循环找到本次的最小值是谁，然后把最小值继续推进：
            for (int j = 0; j < m; j++) {
                if (nums[j] == minNum) {
                    pointers[j]++;
                    // pointers[j]: j指针执行次数； dp[pointers[j]] j指针执行几次后，dp产生的最小值？？？（死记吧）
                    nums[j] = dp[pointers[j]] * primes[j];
                    // nums[j] = pointers[j] * primes[j]; // 错误：不是乘积的关系，是幂的关系
                }
            }
        }
        return dp[n];
    }

//    public static void main(String[] args) {
//        Top top = new Top();
//        int[] nums = {2,7,13,19};
//        System.out.println(top.xxx_nthSuperUglyNumber(12, nums));
//    }


    /**
     * 264.一个整数n，返回第n个丑数（ 丑数就是质因子只包含 2、3 和 5 的正整数 ）
     * n = 10 ==> 12 [1, 2, 3, 4, 5, 6, 8, 9, 10, 12] 丑数组成的前10列表
     *
     * @param n
     * @return
     */
    public int xxx_nthUglyNumber_264(int n) {
        int[] dp = new int[n];
        dp[0] = 1;
        int p2 = 0, p3 = 0, p5 = 0;
        for (int i = 1; i < n; i++) {
            // 2*2*2.. ｜ 3*3*3..  | 5*5*5.. 之间的比较
            int num2 = dp[p2] * 2, num3 = dp[p3] * 3, num5 = dp[p5] * 5;
            dp[i] = Math.min(num2, Math.min(num3, num5));
            System.out.println(dp[i] + " ");
            if (dp[i] == num2) {
                p2++;
            }
            if (dp[i] == num3) {
                p3++;
            }
            if (dp[i] == num5) {
                p5++;
            }
        }
        return dp[n - 1];
    }

//    public static void main(String[] args) {
//        Top top = new Top();
//        top.xxx_nthUglyNumber_264(20);
//    }

    /**
     * 309. 给定一个整数数组prices，其中第  prices[i] 表示第 i 天的股票价格。设计一个算法计算出最大利润 在满足以下约束条件下，
     * 你可以尽可能地完成更多的交易（多次买卖一支股票） 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）
     * <p>
     * eg.prices = [1,2,3,0,2] ==> 3 [买入, 卖出, 冷冻期, 买入, 卖出]
     *
     * @param prices
     * @return
     */
    public int xxx_maxProfit_309(int[] prices) {
        // 核心是：
        if (prices.length == 0) {
            return 0;
        }
        // f[i][0]: 手上持有股票的最大收益
        // f[i][1]: 手上不持有股票，并且处于冷冻期中的累计最大收益
        // f[i][2]: 手上不持有股票，并且不在冷冻期中的累计最大收益
        int n = prices.length;
        int[][] f = new int[n][3];
        f[0][0] = -prices[0];
        for (int i = 1; i < n; ++i) {
            // 第i天持有股票：第i-1持有的 或者 第i天买入（则第i-1就不能买入且不处于冷冻期）
            f[i][0] = Math.max(f[i - 1][0], f[i - 1][2] - prices[i]);
            // 不持有&在冷冻期：第i-1天持有&卖出
            f[i][1] = f[i - 1][0] + prices[i];
            // 不持有&不在冷冻：第i-1天没操作，可能在冷冻也可能不在
            f[i][2] = Math.max(f[i - 1][1], f[i - 1][2]);
        }
        // 最后一天肯定不会持有股票
        return Math.max(f[n - 1][1], f[n - 1][2]);
    }


    /**
     * 306.累加数 是一个字符串，组成它的数字可以形成累加序列。
     *
     * 一个有效的 累加序列 必须 至少 包含 3 个数。除了最开始的两个数以外，序列中的每个后续数字必须是它之前两个数字之和。
     *
     * 给你一个只包含数字 '0'-'9' 的字符串，编写一个算法来判断给定输入是否是 累加数 。如果是，返回 true ；否则，返回 false
     *
     *
     * 112358 -->true
     * 199100199 --> true
     * @param num
     * @return
     */
    public boolean isAdditiveNumber(String num) {

        return false;
    }


    /**
     * 304.给定一个二维矩阵 matrix，以下类型的多个请求
     *
     * 计算其子矩形范围内元素的总和，该子矩阵的 左上角 为 (row1, col1) ，右下角 为 (row2, col2)
     *
     * [[[[3,0,1,4,2],[5,6,3,2,1],[1,2,0,1,5],[4,1,0,1,7],[1,0,3,0,5]]],[2,1,4,3],[1,1,2,2],[1,2,2,4]]
     *  ==>
     * numMatrix.sumRegion(2, 1, 4, 3); // return 8 (红色矩形框的元素总和)
     * numMatrix.sumRegion(1, 1, 2, 2); // return 11 (绿色矩形框的元素总和)
     * numMatrix.sumRegion(1, 2, 2, 4); // return 12 (蓝色矩形框的元素总和)
     */



    /**
     * 301.给你一个由若干括号和字母组成的字符串 s ，删除最小数量的无效括号，使得输入的字符串有效
     *
     * s = "()())()" ==》["(())()","()()()"]
     * @param s
     * @return
     */
    public List<String> removeInvalidParentheses(String s) {
        return null;
    }


    /**
     * 300. 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度
     * <p>
     * nums = [10,9,2,3,7,101,18] ==》4 [2,3,7,101]
     *
     * @param nums
     * @return
     */
    public static int lengthOfLIS(int[] nums) {
        // 左右指针
        int left = 0, right = 0;
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > nums[right]) {
                right++;
            } else {
                max = Math.max(max, right - left);
                right = i;
                left = i;
            }
        }
        return max > 0 ? max + 1 : max;
    }

    /**
     * 287. 给定一个包含n + 1个整数的数组 nums ，其数字都在[1, n]范围内（包括 1 和 n），可知至少存在一个重复的整数
     * [3,2,5,4,6,1,4] ==> 4
     *
     * @param nums
     * @return 假设nums只有一个重复的整数 ，返回这个重复的数
     */
    public int xxx_findDuplicate_287(int[] nums) {
        // 解决方案必须不修改数组nums 且 只用常量级O(1)的额外空间
        // 二分法：findDuplicate_4_mid -- 常量级的时间复杂度和空间复杂
        // 快慢指针：findDuplicate_4_slow -- 保证了在快慢指针能在重复节点相遇
        return 0;
    }

    private int findDuplicate_4_mid(int[] nums) {
        int left = 1;
        int right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            int cnt = 0;
            for (int num : nums) {
                if (num <= mid) {
                    cnt++;
                }
            }
            // cnt > mid 说明重复数在 mid 的左边，则值在 [1,mid]
            if (cnt > mid) {
                right = mid;
            } else {
                // cnt = mid 说明在左侧或自己，left可以返回
                left = mid + 1;
            }
        }
        return left;
    }

    private int findDuplicate_4_slow(int[] nums) {
        // 通过nums[n] -> nums[nums[n]]的指向构建成一个环（不在意环的位置是否是链表最后只要保证环存在即可）
        int slow = nums[0];
        int fast = nums[nums[0]];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        fast = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }

    /**
     * 285.给定一棵二叉搜索树和其中的一个节点 p ，找到该节点在树中的中序后继。如果节点没有中序后继，请返回 null
     * root = [2,1,3], p = 1 ==》2
     * root = [5,3,6,2,4,null,null,1], p = 6  ==> null
     *
     * @param root
     * @param p
     * @return
     */
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        // 中序遍历放到stack，然后找到该节点
        Stack<TreeNode> stack = new Stack<>();
        inorderSuccessor(root, stack);
        TreeNode pre = null;
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node == p) {
                return pre;
            }
            pre = node;
        }
        return pre;
    }
    private void inorderSuccessor(TreeNode root, Stack<TreeNode> stack) {
        if (root == null) {
            return;
        }
        inorderSuccessor(root.left, stack);
        stack.push(root);
        inorderSuccessor(root.right, stack);
    }

//    public static void main(String[] args) {
//        TreeNode find = new TreeNode(1);
//        TreeNode root = new TreeNode(2, find, new TreeNode(3));
//        Top top = new Top();
//        TreeNode result = top.inorderSuccessor(root, find);
//        System.out.println(result == null ? null : result.val);
//    }


    /**
     * 279.给你一个整数 n ，返回和为 n 的完全平方数的最少数量
     * （完全平方数是其值等于一个整数自乘的积，例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是 ）
     * n = 12 ==> 3 4+4+4
     *
     * @param n
     * @return
     */
    public int xxx_numSquares_279(int n) {
        // ** 核心：之前的动态规划都是正向推进如dp[i] = dp[i-1]+nums[i]，这个是倒着推前一个最小的从而找到心在最大的 **
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        // 状态转移方程：找到所有 [n-i*i] --> n的，然后比较各个值最小的
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[n];
    }

//    public static void main(String[] args) {
//        Top service = new Top();
//        System.out.println(service.xxx_numSquares_279(12));
//    }

    /**
     * 多线程控制ABC循环打印
     */


    /**
     * 271.请你设计一个算法，可以将一个 字符串列表 编码成为一个 字符串。这个编码后的字符串是可以通过网络进行高效传送的，并且可以在接收端被解码回原来的字符串列表
     */


    /**
     * 267. 给定一个字符串 s ，返回 其重新排列组合后可能构成的所有回文字符串，并去除重复的组合
     * s = "aabb" ==> ["abba", "baab"]
     *
     * @param s
     * @return
     */
    public List<String> generatePalindromes(String s) {

        return null;
    }

    /**
     * 260.给你一个整数数组 nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。
     * 找出只出现一次的那两个元素。你可以按 任意顺序 返回答案
     *
     * [1,2,1,3,2,5] => [3,5] / [5, 3]
     * @param nums
     * @return
     */
    public int[] singleNumber(int[] nums) {

        return null;
    }

    /**
     * 255.给定一个无重复元素的整数数组preorder，如果它是以二叉搜索树的先序遍历排列返回true
     * <p>
     * preorder = [5,2,1,3,6] ==> true
     * preorder = [5,2,6,1,3] ==> false
     *
     * @param preorder
     * @return
     */
    public boolean xxx_verifyPreorder_255(int[] preorder) {
        // ** 核心是找到左右子树的分界点，然后判断右子树是否都大于左子树的根节点。另外二叉树的核心一般都是用栈来实现 **
        Stack<Integer> stack = new Stack<>();
        int max = Integer.MIN_VALUE;
        for (int cur : preorder) {
            // 先序拐点在根节点：后续右子树节点都要大于此节点
            while (!stack.isEmpty() && cur > stack.peek()) {
                max = stack.pop();
            }
            // 右子树的节点都应该大于左子树的根节点
            if (cur < max) {
                return false;
            }
            stack.push(cur);
        }
        return true;
    }

    /**
     * 如果它是以二叉搜索树的后序遍历排列返回true
     * @param preorder
     * @return
     */
    private boolean verifyPreorderV2(int[] preorder) {
        if (preorder == null || preorder.length == 0) {
            return false;
        }
        Stack<Integer> stack = new Stack<>();
        int max = Integer.MIN_VALUE;
        for (int cur : preorder) {
            if (cur < max) {
                return false;
            }
            // 后续拐点在根节点：后续右子树节点都要大于此节点
            while (!stack.isEmpty() && stack.peek() < cur) {
                max = stack.pop();
            }
            stack.push(cur);
        }
        return true;
    }

    /**
     * 254.整数可以被看作是其因子的乘积
     * <p>
     * 12 ==> [
     * [2, 6],
     * [2, 2, 3],
     * [3, 4]
     * ]
     *
     * @param n
     * @return
     */
    List<List<Integer>> factors_res = new ArrayList<>();
    public List<List<Integer>> getFactors_254(int n) {
        // 注意： 你可以假定 n 为永远为正数 因子必须大于 1 并且小于 n
        //  递归的定义：dfs(n, start, path) 表示 n 的因子列表，因子的起始位置为 start，已经选择的因子列表为 path
        dfs(n, 2, new ArrayList<>());
        return factors_res;
    }
    private void dfs(int n, int start, List<Integer> path) {
        if (n == 1) {
            if (path.size() > 1) {
                factors_res.add(new ArrayList<>(path));
            }
            return;
        }
        for (int i = start; i <= n; i++) {
            if (n % i == 0) {
                path.add(i);
                dfs(n / i, i, path);
                path.remove(path.size() - 1);
            }
        }
    }

    /**
     * 253.给你一个会议时间安排的数组intervals ，每个会议时间都会包括开始和结束的时间intervals[i] = [starti, endi]
     * intervals = [[0,30],[5,10],[15,20]] ==> 2
     *
     * @param intervals
     * @return 所需会议室的最小数量
     */
    public int xxx_minMeetingRooms_253(int[][] intervals) {
        // ** 看的是思路：小顶堆维护的是当前进行的会议室 **
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        // 从定义排序按照进入时间排序
        Arrays.sort(intervals, Comparator.comparingInt(v -> v[0]));
        // 定义一个小顶堆的优先队列，存放当前进行的会议室
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int meetingCount = 0;
        for (int[] meeting : intervals) {
            // 每次新的会议进来则遍历判断当前正在进行的是否可以结束，否则加1
            while (!heap.isEmpty() && meeting[0] >= heap.peek()) {
                heap.poll();
            }
            heap.add(meeting[1]);
            meetingCount = Math.max(meetingCount, heap.size());
        }
        return meetingCount;
    }

    /**
     * 247。给定一个整数 n ，返回所有长度为 n 的 中心对称数 。你可以以 任何顺序 返回答案
     * n = 2 ==》 ["11","69","88","96"]
     * @param n
     * @return
     */
    public List<String> findStrobogrammatic(int n) {
        // 中心对称数是一个数字在旋转了 180 度之后看起来依旧相同的数字（或者上下颠倒地看）
        return null;
    }

    /**
     * 240.编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
     * 每行的元素从左到右升序排列。
     * 每列的元素从上到下升序排列。
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix_240(int[][] matrix, int target) {
        // 一个比较巧妙的解法：通过大小的增减
        int row = matrix.length - 1;
        int col = 0;
        while (row >= 0 && col < matrix[0].length) {
            if (matrix[row][col] > target) {
                row--;
            } else if (matrix[row][col] < target) {
                col++;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * 239.给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧，你只可以看到在滑动窗口内的 k 个数字
     * <p>
     * [1,3,-1,-3,5,3,6,7] k = 3 ==> [3,3,5,5,6,7]
     *
     * @param nums
     * @param k
     * @return 滑动窗口中的最大值
     */
    public int[] maxSlidingWindow_239(int[] nums, int k) {
        // 将一个元素放入优先队列的时间复杂度为 O(logn)，因此总时间复杂度为 O(nlogn)
        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> o1[0] != o2[0] ? o2[0] - o1[0] : o2[1] - o1[1]);
        for (int i = 0; i < k; i++) {
            queue.offer(new int[]{nums[i], i});
        }
        int[] target = new int[nums.length - k + 1];
        target[0] = queue.peek()[0];
        for (int i = k; i < nums.length; i++) {
            queue.offer(new int[]{nums[i], i});
            // 队列中位置不在 i-k 范围内的数据都被移除
            while (queue.peek()[1] <= i - k) {
                queue.poll();
            }
            target[i - k + 1] = queue.peek()[0];
        }
        return target;
    }

    public int[] xxx_maxSlidingWindow_v2(int[] nums, int k) {
        // 滑动窗口的核心在于通过 左右指针/双端队列 控制窗口数据保持队头是当前窗口的最大值。总时间复杂度为 O(n)
        int n = nums.length;
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < k; ++i) {
            // 1、新加入的值如果大于队头，则代表在后面的窗口内队头数据再也不会是最大值; 同时一直循环比较移除队尾次从而保证队列递减
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
        }
        int[] ans = new int[n - k + 1];
        ans[0] = nums[deque.peekFirst()];
        for (int i = k; i < n; ++i) {
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
            // 2、只需要看最大值在不在区间内即可
            while (deque.peekFirst() <= i - k) {
                deque.pollFirst();
            }
            ans[i - k + 1] = nums[deque.peekFirst()];
        }
        return ans;
    }

//    public static void main(String[] args) {
//        Top top = new Top();
//        int[] nums = {1, 3, 2, 5, 4, 3, 7, 5, 6, 8};
//        int[] res = top.xxx_maxSlidingWindow_v2(nums, 3);
//        for (int i = 0; i < res.length; i++) {
//            System.out.println(res[i]);
//        }
//    }
    /**
     * 236.给定一个二叉树, 找到该树中两个指定节点的最近公共祖先
     *
     * @param cur
     * @param p
     * @param q
     * @return
     */
    public TreeNode xxx_lowestCommonAncestor_263(TreeNode cur, TreeNode p, TreeNode q) {
        if (cur == null || cur == p || cur == q) {
            return cur;
        }
        // 1、先左侧节点一撸到底；2、再逐步回塑右侧节点；3、在当前节点视角判断子树下是否存在指定节点，存在两则返回cur存在一则返回left/right都不存在则null
        TreeNode left = xxx_lowestCommonAncestor_263(cur.left, p, q);
        TreeNode right = xxx_lowestCommonAncestor_263(cur.right, p, q);
        // ⚠️ ！！！这两个判断&返回的是核心：cur节点的左右子树是否存在指定节点，不存在即返回另一个节点都存在则返回当前节点。然后一直把节点往上回溯比较！！！
        if (left == null) {
            // left为空：说明当前节点的左子树一撸到底没有遇到指定节点，那就在右节点则直接返回右节点值
            return right;
        }
        if (right == null) {
            // right为空：说明当前节点的右子树一撸到底没有遇到指定节点，那就在左节点则直接返回左节点值
            return left;
        }
        // 如果left和right都不为空：说明这两个节点一个在cur的左子树上一个在cur的右子树上
        // return的数值代表啥？？？ -- 代表当前节点可能所存在的树
        return cur;
    }

    /**
     * 235.给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先
     * @param cur
     * @param p
     * @param q
     * @return 好题：深度了解树的遍历
     */
    public TreeNode lowestCommonAncestor(TreeNode cur, TreeNode p, TreeNode q) {
        // 二叉搜索树有排序的特征，可以利用这个特性
        if (cur == null || (cur.val < p.val && cur.val < q.val)) {
            return null;
        }
        if (cur.val > p.val) {
            return p;
        }
        if (cur.val > q.val) {
            return q;
        }
        TreeNode left = lowestCommonAncestor(cur.left, p, q);
        TreeNode right = lowestCommonAncestor(cur.right, p, q);
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }
        return cur;
    }

//    public static void main(String[] args) {
//        TreeNode treeNode7 = new TreeNode(7);
//        TreeNode treeNode8 = new TreeNode(8);
//        TreeNode treeNode6 = new TreeNode(6);
//        TreeNode treeNode5 = new TreeNode(5, treeNode7, treeNode8);
//        TreeNode treeNode4 = new TreeNode(4,null, treeNode6);
//        TreeNode treeNode3 = new TreeNode(3);
//        TreeNode treeNode2 = new TreeNode(2, treeNode4, treeNode5);
//        TreeNode treeNode1 = new TreeNode(1, treeNode2, treeNode3);
//        Top top = new Top();
//        System.out.println(top.xxx_lowestCommonAncestor(treeNode1, treeNode3, treeNode8).val);
//    }

    /**
     * 230.给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 个最小元素（从 1 开始计数）
     * @param root
     * @param k
     * @return
     */
    private int kth_smallest_res = 0;
    int rank = 0;
    private int kthSmallest_230(TreeNode root, int k) {
        if (root == null) {
            return 0;
        }
        traverse(root, k);
        return kth_smallest_res;
    }

    private void traverse(TreeNode root, int k) {
        if (root == null) {
            return;
        }
        traverse(root.left, k);
        // 核心在于不能把左节点看作左节点，而是一个中间节点
        rank++;
        if (k == rank) {
            kth_smallest_res = root.val;
            return;
        }
        traverse(root.right, k);
    }

    /**
     * 221.在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积
     *
     * @param matrix
     * @return
     */
    public int maximalSquare(char[][] matrix) {
        int m = matrix.length, n = matrix[0].length, a = 0;
//        二维数组dp，dp(i,j)表示以matrix(i,j)为右下角的只包含“1“的正方形的最大边长
//        如果i==0或者j==0，则dp(i,j)为对应的0或1
//        如果matrix(i,j)==0，则dp(i,j)=0
//        否则，dp(i,j) = min(dp(i-1,j),dp(i,j-1),dp(i-1,j-1))+1
//        即正方形的右下角要取决于其上方、左侧、左上方三个单元格的情况。是三者所构成正方形最大边长的最小值+1
//        时间复杂度O(mn)，空间复杂度O(mn)
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = matrix[i][j] - '0';
                } else if (matrix[i][j] == '1') {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j])) + 1;
                }
                a = Math.max(a, dp[i][j]);
            }
        }
        return a * a;
    }

    /**
     * 216.找出所有相加之和为 n 的 k 个数的组合，且满足下列条件：
     * 1、只使用数字1到9;
     * 2、每个数字最多使用一次
     *
     * @param k
     * @param n
     * @return 所有可能的有效组合的列表 。该列表不能包含相同的组合两次，组合可以以任何顺序返回
     */
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> list = new ArrayList<>();

    public List<List<Integer>> combinationSum3_216(int k, int n) {
        for (int j = 1; j < 9; j++) {
            list.add(j);
            combinationSum3DFS(n - j, k - 1, j, list);
            list.remove(list.size() - 1);
        }
        return res;
    }

    private void combinationSum3DFS(int n, int k, int i, List<Integer> list) {
        if (n < 0) {
            return;
        }
        if (n == 0 && k == 0) {
            res.add(new ArrayList<>(list));
            return;
        }
        if (k == 0) {
            return;
        }
        for (int j = i + 1; j < 9; j++) {
            if (n < j) {
                continue;
            }
            list.add(j);
            combinationSum3DFS(n - j, k - 1, j, list);
            list.remove(list.size() - 1);
        }
    }

//    public static void main(String[] args) {
//        System.out.println(JSON.toJSONString(combinationSum3(3, 9)));
//    }

    /**
     * 214. 给定一个字符串 s，你可以通过在字符串前面添加字符将其转换为回文串。找到并返回可以用这种方式转换的最短回文串
     * "aacecaaa" ==》"aaacecaaa"
     * "abcd" ==》"dcbabcd"
     *
     * @param s
     * @return
     */
    public String xxx_shortestPalindrome(String s) {
        // https://blog.csdn.net/weixin_45333934/article/details/108356016
        return shortestPalindrome_v1(s);
    }


    public String shortestPalindrome_v1(String s) {
        // 双向指针s[i]==s[j]则i++,j--；否则j--
        int i = 0, j = s.length() - 1;
        char[] c = s.toCharArray();
        while (j >= 0) {
            if (i == j) {
                continue;
            }
            if (c[i] == c[j]) {
                i++;
            }
            j--;
        }
        //此时代表整个字符串是回文串
        if (i == s.length()) {
            return s;
        }
        //后缀
        String suffix = s.substring(i);
        //后缀倒置
        String reverse = new StringBuilder(suffix).reverse().toString();
        //加到开头
        return reverse + s;
    }


    /**
     * 337.打家劫舍III 除了 root 之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。
     * 如果两个直接相连的房子在同一天晚上被打劫 ，房屋将自动报警
     * <p>
     * root = [3,2,3,null,3,null,1] ==> 3 + 3 + 1 = 7
     * root = [3,4,5,1,3,null,1] ==> 4 + 5 = 9
     *
     * @param root 二叉树的 root
     * @return 在不触动警报的情况下 ，小偷能够盗取的最高金额
     */
    public int x_rob_iii(TreeNode root) {
        int[] rootStatus = rob_dfs(root);
        return Math.max(rootStatus[0], rootStatus[1]);
    }

    public int[] rob_dfs(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0};
        }
        // 后续遍历：先看左右节点，在看根节点
        int[] l = rob_dfs(node.left);
        int[] r = rob_dfs(node.right);
        // 节点选中：当前节点值 + 左未 + 右未
        int selected = node.val + l[1] + r[1];
        // 节点未选中：左可选中亦可未选中，右可选中亦可未选中
        int notSelected = Math.max(l[0], l[1]) + Math.max(r[0], r[1]);
        return new int[]{selected, notSelected};
    }

    /**
     * 213. 第一个房屋和最后一个房屋是紧挨着的，同时相邻的房屋装有相互连通的防盗系统。如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警
     * [1,3,1] ==> 3
     *
     * @param nums
     * @return 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额
     */
    public static int x_rob_213(int[] nums) {
        if (nums.length < 2) {
            return nums[0];
        }
        // 动态规划看n-2的大小
        int[] dp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            dp[i] = nums[i];
        }
        int max = Math.max(dp[0], dp[1]);
        dp[1] = max;
        for (int i = 2; i < nums.length; i++) {
            // 对于第i晚有两个选择：不偷跟着前一晚的，或者 偷了+前前一晚（感觉绕进去了想的有点复杂了）
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
            max = Math.max(max, dp[i]);
        }
        return max;
    }

//    public static void main(String[] args) {
//        System.out.println(x_rob_213(new int[]{1, 3, 4, 1}));
//    }


    /**
     * 207. 你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。 prerequisites[i] = [ai, bi] ，表示如果要学习课程ai则必须先学习课程bi
     * numCourses = 2, prerequisites = [[1,0]] ==> true
     * numCourses = 2, prerequisites = [[1,0],[0,1]] ==> false
     *
     * @param numCourses
     * @param prerequisites
     * @return 是否可能完成所有课程的学习
     */
    List<List<Integer>> edges;
    int[] visited;
    boolean valid = true;

    public boolean xxx_canFinish_207(int numCourses, int[][] prerequisites) {
        edges = new ArrayList<>();
        for (int i = 0; i < numCourses; ++i) {
            edges.add(new ArrayList<>());
        }
        visited = new int[numCourses];
        for (int[] info : prerequisites) {
            edges.get(info[1]).add(info[0]);
        }
        // 「题目理解错误..是这几门课序号都要完成」
        for (int i = 0; i < numCourses && valid; ++i) {
            if (visited[i] == 0) {
                dfs(i);
            }
        }
        return valid;
    }

    public void dfs(int u) {
        // 课程完成前、完成中、完成后
        visited[u] = 1;
        for (int v : edges.get(u)) {
            if (visited[v] == 0) {
                dfs(v);
                if (!valid) {
                    return;
                }
            } else if (visited[v] == 1) {
                // 在完成中再次访问节点说明有环存在
                valid = false;
                return;
            }
        }
        visited[u] = 2;
    }

    // 广度遍历算法
    int[] indeg;
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        edges = new ArrayList<>();
        for (int i = 0; i < numCourses; ++i) {
            edges.add(new ArrayList<>());
        }
        indeg = new int[numCourses];
        for (int[] info : prerequisites) {
            edges.get(info[1]).add(info[0]);
            ++indeg[info[0]];
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; ++i) {
            if (indeg[i] == 0) {
                queue.offer(i);
            }
        }
        int visited = 0;
        while (!queue.isEmpty()) {
            ++visited;
            int u = queue.poll();
            for (int v: edges.get(u)) {
                --indeg[v];
                if (indeg[v] == 0) {
                    queue.offer(v);
                }
            }
        }
        return visited == numCourses;
    }


    /**
     * 199.给定一个二叉树的根节点root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值
     * [1,2,3,null,5,null,4] ==> [1,3,4]
     *
     * @param root 2024-02-27
     * @return
     */
    public List<Integer> x_rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> dq = new LinkedList<>();
        dq.offer(root);
        while (!dq.isEmpty()) {
            int size = dq.size();
            while (size > 0) {
                TreeNode node = dq.poll();
                if (node.left != null) {
                    dq.offer(node.left);
                }
                if (node.right != null) {
                    dq.offer(node.right);
                }
                if (size == 1) {
                    res.add(node.val);
                }
                size--;
            }
        }
        return res;
    }


    /**
     * 172.给定一个整数 n ，返回 n! 结果中尾随零的数量
     *
     * @param n 2024-02-26
     * @return
     */
    public int trailingZeroes_172(int n) {
        // 找到数字中因子5的数量就是后面0的次数，因为2*5=10而2的肯定会比5多，所以看5的就行
        int ans = 0;
        for (int i = 5; i <= n; i += 5) {
            int temp = i;
            while (temp % 5 == 0) {
                ans++;
                temp /= 5;
            }
        }
        return ans;
    }

    /**
     * 167. 给你一个下标从 1 开始的整数数组 numbers ，该数组已按非递减顺序排列，请你从数组中找出满足相加之和等于目标数target的两个数
     * numbers = [2,7,11,15], target = 9 ==> [1,2]
     * numbers = [2,3,4], target = 6 ==> [1,3]
     *
     * @param numbers 2024-02-26
     * @param target
     * @return
     */
    public int[] twoSum_167(int[] numbers, int target) {
        for (int i = 0; i < numbers.length - 1; i++) {
            int left = i + 1, right = numbers.length - 1, mid;
            while (left <= right) {
                mid = (right - left) / 2 + left;
                if (numbers[mid] > target - numbers[i]) {
                    right = mid - 1;
                } else if (numbers[mid] < target - numbers[i]) {
                    left = mid + 1;
                } else {
                    return new int[]{i + 1, mid + 1};
                }
            }
        }
        return new int[0];
    }

    public int[] twoSum_v2(int[] numbers, int target) {
        int left = 0, right = numbers.length - 1;
        while (numbers[left] != target - numbers[right]) {
            if (numbers[left] > target - numbers[right]) {
                right--;
            } else {
                left++;
            }
        }
        return new int[]{left + 1, right + 1};
    }

    /**
     * 164.给定一个无序的数组 nums，返回 数组在排序之后，相邻元素之间最大的差值 。如果数组元素个数小于 2，则返回 0 。
     * <p>
     * [3,6,9,1] -> 3
     *
     * @param nums
     * @return
     */
    public int maximumGap(int[] nums) {
        // 必须编写一个在「线性时间」内运行并使用「线性额外空间」的算法
        // 桶排序。。
        return 0;
    }

    /**
     * 162.峰值元素是指其值严格大于左右相邻值的元素。给你一个整数数组 nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可
     * 你可以假设 nums[-1] = nums[n] = -∞ 。
     * [1,2,3,1] --> 2
     * [1,2,1,3,5,6,4] -> 1 或 5
     *
     * @param nums
     * @return
     */
    public int x_findPeakElement_162(int[] nums) {
        // 必须实现时间复杂度为 O(log n) 的算法来解决此问题。
        // 二分法：往上走的一定有波峰
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            // 往大一侧走，一定能找到波峰
            if (nums[mid] > nums[mid + 1]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }

    /**
     * 159.给你一个字符串 s ，请你找出至多包含两个不同字符的最长子串，并返回该子串的长度
     * "eceba" --> 3 "ece"
     * ccaabbb --> 5 "aabbb"
     *
     * @param s
     * @return
     */
    public int xxx_lengthOfLongestSubstringTwoDistinct_159(String s) {
        // 暴力循环：每个节点不断回退前两个不同点，时间复杂度O(n^2)
        // 左右指针：保证left-right指针区间内的数据类型==2,通过左右指针差求最大值
        // 滑动窗口:利用数组集合代替map,保持窗口内类型在2否则一直移动 -- 题目有点精妙
        int[] window = new int[128];
        int length = s.length();
        int left = 0, right = 0;
        // count代表当前窗口的字符类型多少
        for (int count = 0; right < length; ) {
            // 左侧字符进窗口：如果不在窗口区则窗口数量和字符数都加1，否则数量加1
            if (window[s.charAt(right++)]++ == 0) {
                count++;
            }
            // 如果字符数量大于2：则窗口不断移动直到数量清零
            if (count > 2) {
                if (--window[s.charAt(left++)] == 0) {
                    count--;
                }
            }
        }
        // 要在right-left间保持最大值，也就是说窗口只会大不会小。如果右侧进来相同的右移动，否则左右都移动
        return right - left;
    }

    /**
     * 156.上下翻转二叉树；左节点->根节点，根节点->右节点，右节点->左节点
     *
     * @param root
     * @return
     */
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        return null;
    }


    /**
     * 154.数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次的结果为数组 [a[n-2],a[n-1], a[0], a[1], a[2], ..., a[n-3]]
     * <p> [ 5,4,3,2,1] [ 5,4,3,2,1,6]
     * 给你一个元素值互不相同的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的最小元素
     *
     * @param nums 2024-02-22
     * @return
     */
    public static int xxx_findMin_154(int[] nums) {
        // 你必须设计一个时间复杂度为 O(log n) 的算法解决此问题
        // 二分法：找到节点的左右侧都大于它的或者它最小的
//        int low = 0;
//        int high = nums.length - 1;
//        int middle = (low + high) / 2;
//        while (low < high) {
//            // 先保证middle不是最小值
//            if (nums[middle + 1] > nums[middle] && nums[middle - 1] > nums[middle]) {
//                return nums[middle];
//            }
//            // 右边大，往左找
//            if (nums[middle + 1] > nums[middle]) {
//                high = middle - 1;
//            }
//            // 左边大，往右找
//            if (nums[middle - 1] > nums[middle]) {
//                low = middle + 1;
//            }
//            // ⚠️ 二分法查找一定要避免出现middle一直不变的死循环
//            middle = (low + high) / 2;
//        }
//        return nums[middle];

        // 这么简单吗。。。是我想复杂了？？？
        int low = 0;
        int high = nums.length - 1;
        while (low < high) {
            // 不用和下一个比，和最后一个比或者第一个比。保证在这个区间是否升降级
            int pivot = low + (high - low) / 2;
            if (nums[pivot] < nums[high]) {
                high = pivot;
            } else if (nums[pivot] > nums[high]) {
                low = pivot + 1;
            } else {
                // 如果pivot和high一样大小，从high位置向前走！！！（二分就是要把各种可能都想一边 各种变种）
                high -= 1;
            }
        }
        return nums[low];
    }

//    public static void main(String[] args) {
//        int[] nums = {1, 3, 3};
//        System.out.println(findMin_154(nums));
//    }


    /**
     * 152.给你一个整数数组 nums ，请你找出数组中乘积最大的非空连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积
     * nums = [2,3,-2,4] ==> 6(2 * 3)
     * [-3,-1,-1]
     * [-2,3,-4]
     *
     * @param nums 2024-02-22
     * @return
     */
    public static int x_maxProduct_152(int[] nums) {
        int maxF = nums[0], minF = nums[0], ans = nums[0];
        int length = nums.length;
        for (int i = 1; i < length; ++i) {
            // 为了解决负负得正问题，最大和最小值都乘上nums[i]然后比较大小
            int mx = maxF, mn = minF;
            maxF = Math.max(mx * nums[i], Math.max(nums[i], mn * nums[i]));
            minF = Math.min(mn * nums[i], Math.min(nums[i], mx * nums[i]));
            ans = Math.max(maxF, ans);
        }
        return ans;
    }

    /**
     * 151.给你一个字符串 s ，请你反转字符串中 单词 的顺序（空格隔开）
     * s = "the sky is blue" ==》 "blue is sky the"
     *
     * @param s 2024-02-22
     * @return
     */
    public static String reverseWords_151(String s) {
        // 除去开头和末尾的空白字符
        s = s.trim();
        // 正则匹配连续的空白字符作为分隔符分割
        List<String> wordList = Arrays.asList(s.split("\\s+"));
        Collections.reverse(wordList);
        return String.join(" ", wordList);
    }

//    public static void main(String[] args) {
//        System.out.println(reverseWords("  hello world  ").length());
//    }

    /**
     * 148.链表的头结点 head ，请将其按升序排列并返回排序后的链表
     * [4,2,7,5,9] -> [2,4,5,7,9]
     *
     * @param head 2024-02-22 again
     * @return
     */
    public ListNode xxx_sortList_148(ListNode head) {
        // 归并排序：数组不断按中间节点分割成最小，然后递归合并排序。简称先归一再合并
        if (head == null || head.next == null) {
            return head;
        }
        ListNode middle = findMiddleNode(head);
        ListNode rightHead = middle.next;
        // 中间侧断开保证下一次遍历只有前半部分（由于后续不会再用到这个链表所以不用担心链表接不上的问题）
        middle.next = null;
        ListNode left = xxx_sortList_148(head);
        ListNode right = xxx_sortList_148(rightHead);
        return mergeListNode(left, right);
    }

    private ListNode findMiddleNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow = head;
        // 因为要从中间分成两截，跟正常的快慢不一样，fast提前走保证slow在中间点左侧
        ListNode fast = head.next.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private ListNode mergeListNode(ListNode a, ListNode b) {
        ListNode sentry = new ListNode(-1);
        ListNode curr = sentry;
        while (a != null && b != null) {
            if (a.val < b.val) {
                curr.next = a;
                a = a.next;
            } else {
                curr.next = b;
                b = b.next;
            }
            curr = curr.next;
        }
        curr.next = a == null ? b : a;
        return sentry.next;
    }

//    public static void main(String[] args) {
//        Top top = new Top();
//        top.sortList_148(new ListNode(3,new ListNode(5,new ListNode(2,new ListNode(1))))).print();
//    }

    /**
     * 139.给你一个字符串 s 和一个字符串列表 wordDict 作为字典。如果可以利用字典中出现的一个或多个单词拼接出 s 则返回 true
     * <p>
     * s = "leetcode", wordDict = ["leet", "code"] ==》 true
     *
     * @param s 2024-02-21
     * @param wordDict
     * @return
     */
    static boolean wordBreak = false;

    public static boolean wordBreak_139(String s, List<String> wordDict) {
        // 不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用
        if (s == null || "".equals(s)) {
            return true;
        }
        if (wordDict == null || wordDict.size() == 0) {
            return false;
        }
        // 树型递归:只有要一条链路都包含在字典内即可
        dfs(s, 0, wordDict);
        return wordBreak;
    }

    private static void dfs(String s, Integer start, List<String> wordDict) {
        if (start == s.length()) {
            wordBreak = true;
            return;
        }
        for (int i = start; i < s.length(); i++) {
            String word = s.substring(start, i + 1);
            if (!wordDict.contains(word)) {
                continue;
            }
            // 不能直接return返回，否则会导致for后面的遍历跳过
            dfs(s, i + 1, wordDict);
        }
    }


//    public static void main(String[] args) {
//        System.out.println(wordBreak("catsandog", Lists.newArrayList("cats","dog","sand","and","cat")));
//    }


    /**
     * 143.重排链表 L0 → L1 → … → Ln - 1 → Ln  ===》 L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 →
     *
     * @param head 2024-02-21
     */
    public static void reorderList_143(ListNode head) {
        // 涉及到链表3个知识：先通过快慢指针找到中间点、再反转后半部分链表、最后做前后插入
        // 1、找到中间节点
        ListNode middle = findMiddle(new ListNode(0, head));
        // 2、反转slow后到到尾部链表
        ListNode last = reversal(middle);
        // 3、插入
        insertLast(head, last);
    }
    private static ListNode findMiddle(ListNode dummy){
        ListNode fast = dummy;
        ListNode slow = dummy;
        while (fast.next != null) {
            if (fast.next.next != null) {
                fast = fast.next.next;
            } else {
                fast = fast.next;
            }
            slow = slow.next;
        }
        return slow;
    }
    private static ListNode reversal(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode cur = head;
        // 反转pre节点设置为null，否则会进入节点反向依赖
        ListNode pre = null;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
    private static void insertLast(ListNode head, ListNode last) {
        ListNode node = head;
        while (node != null) {
            ListNode cur = node.next;
            node.next = last;
            ListNode temp = last;
            last = last.next;
            temp.next = cur;
            node = cur;
        }
    }

//    public static void main(String[] args) {
//        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
//        reorderList(head);
//        while (head != null) {
//            System.out.println(head.val);
//            head = head.next;
//        }
//    }

    /**
     * 142. 给定一个链表的头节点 head ，返回链表开始入环的第一个节点。 如果链表无环，则返回 null
     *
     * @param head 2024-02-21
     * @return
     */
    public ListNode detectCycle_142(ListNode head) {
        // 1、哈希表
        ListNode pos = head;
        Set<ListNode> visited = new HashSet<>();
        while (pos != null) {
            if (visited.contains(pos)) {
                return pos;
            } else {
                visited.add(pos);
            }
            pos = pos.next;
        }

        // 2、快慢指针
        return null;
    }

    /**
     * 137.给你一个整数数组 nums ，除某个元素仅出现一次外，其余每个元素都恰出现 三次
     *
     * @param nums 2024-02-22
     * @return 返回那个只出现了一次的元素
     */
    public int singleNumber_137(int[] nums) {
        // 实现线性时间复杂度的算法且使用常数级空间来解决此问题
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        int ans = 0;
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            int num = entry.getKey(), occ = entry.getValue();
            if (occ == 1) {
                ans = num;
                break;
            }
        }
        return ans;
    }

    /**
     * 131.给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是回文串
     * <p>
     * s = "aab" ==》[["a","a","b"],["aa","b"]]
     *
     * @param s 2024-02-20
     * @return s 所有可能的分割方案
     */

    boolean[][] f;
    List<List<String>> ret = new ArrayList<>();
    List<String> ans = new ArrayList<>();
    int n;

    public List<List<String>> xxx_partition_131(String s) {
        n = s.length();
        f = new boolean[n][n];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(f[i], true);
        }
        // 1、找到字符的回文串（对比下面方式，推荐这种方式 简答明了）
        for (int i = n - 1; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                f[i][j] = (s.charAt(i) == s.charAt(j)) && f[i + 1][j - 1];
            }
        }
        // 2、深度递归树找到[i,j]
        dfs(s, 0);
        return ret;
    }

//     for (int L = 2; L <= len; L++) {
//        for (int i = 0; i < len; i++) {
//            int j = i + L - 1;
//        }
//     }

    private void dfs(String s, int i) {
        if (i == n) {
            ret.add(new ArrayList<>(ans));
            return;
        }
        // for循环下一个树节点是从上一个的尾开始
        for (int j = i; j < n; ++j) {
            if (f[i][j]) {
                ans.add(s.substring(i, j + 1));
                dfs(s, j + 1);
                // ans作为唯一一个list存储，下一层节点结束的时候要移除再返回上一层
                ans.remove(ans.size() - 1);
            }
        }
    }

//    public static void main(String[] args) {
//        String s = "aab";
//        partition_131(s);
//    }

    /**
     * 128.给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度
     * [100,4,200,1,3,2] ==》 4 [1, 2, 3, 4]
     *
     * @param nums 2024-02-20
     * @return O(n) 的算法解决此问题
     */
    public int x_longestConsecutive_128(int[] nums) {
        // 连续数字..（遇到一下子没思路的题的时候可以尝试从暴力解法入手）
        Map<Integer, Boolean> map = new HashMap<>();
        for (int j : nums) {
            map.put(j, true);
        }
        // 遍历到每个数字的时候都看看后面一位的数字是否存在，保证时间复杂度在O(n)
        int max = 0;
        for (Map.Entry<Integer, Boolean> entry : map.entrySet()) {
            Integer num = entry.getKey();
            int length = 1;
            int stack = num;
            if (!map.containsKey(num - 1)) {
                while (map.containsKey(stack + 1)) {
                    length = length + 1;
                    stack = stack + 1;
                }
            }
            max = Math.max(max, length);
        }
        return max;
    }

//    public static void main(String[] args) {
//        int[] nums = {100,4,200,1,3,3,2};
//        LcTop202305 lcTop202305 = new LcTop202305();
//        System.out.println(lcTop202305.longestConsecutive(nums));
//    }


    /**
     * 122.整数数组 prices ，其中 prices[i] 表示某支股票第 i 天的价格
     * 在每一天，你可以决定是否购买和/或出售股票。你在任何时候最多只能持有一股股票
     * <p>
     * [7,1,5,3,6,4] ==> 7（1-5，3-6）
     * [1,2,3,4,5] ==> 4（1买5卖）
     * [7,6,4,3,1] ==> 0
     *
     * @param prices 2024-02-20
     * @return 最大利润
     */
    public int maxProfit_122(int[] prices) {
        // 动态规划：dp[i] = dp[i-1] + 第i天赚的最大值
        int[] dp = new int[prices.length];
        dp[0] = 0;
        for (int i = 1; i < prices.length; i++) {
            dp[i] = dp[i - 1] + Math.max(0, prices[i] - prices[i - 1]);
        }
        return dp[prices.length - 1];
    }

//    public static void main(String[] args) {
//        Top lcTop202305 = new Top();
//        int[] prices = {7,1,5,3,6,4};
//        System.out.println(lcTop202305.maxProfit_122(prices));
//    }


    /**
     * 120.三角形 triangle ，找出自顶向下的最小路径和，每一步只能移动到下一行中相邻的结点上
     * 如：triangle = [[2],[3,4],[6,5,7],[4,1,8,3]] ==> 11（2-3-5-1）
     *
     * @param triangle 2024-02-19
     * @return
     */
    public int minimumTotal_120(List<List<Integer>> triangle) {
        // 相邻结点即如果正位于当前行的下标 i 那么下一步可以移动到下一行的下标 i 或 i + 1
        if (triangle == null || triangle.size() == 0) {
            return 0;
        }
        // 最优解法：1、动态规划时间复杂度高；
        int columns = triangle.size();
        int row = triangle.get(columns - 1).size();
        int[][] dp = new int[row][columns];
        for (int i = 0; i < columns; i++) {
            List<Integer> c_list = triangle.get(i);
            for (int j = 0; j < triangle.get(i).size(); j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = c_list.get(j);
                } else if (j == 0) {
                    // 即最左边数字来源只能是正上
                    dp[i][j] = dp[i - 1][j] + c_list.get(j);
                } else if (j + 1 > triangle.get(i - 1).size()) {
                    // 即正上没值，只能走斜上
                    dp[i][j] = dp[i - 1][j - 1] + c_list.get(j);
                } else {
                    // 即(i,j)点只能是 正上(i-1,j) 或 斜上(i-1,j-1) 来的
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - 1]) + c_list.get(j);
                }
            }
        }
        int result = dp[columns - 1][0];
        for (int k = 1; k < row; k++) {
            result = Math.min(dp[columns - 1][k], result);
        }
        return result;
    }

//    public static void main(String[] args) {
//        List<Integer> l1 = Lists.newArrayList(2);
//        List<Integer> l2 = Lists.newArrayList(3,4);
//        List<Integer> l3 = Lists.newArrayList(6,5,7);
//        List<Integer> l4 = Lists.newArrayList(4,1,8,3);
//        List<List<Integer>> triangle = Lists.newArrayList(l1,l2,l3,l4);
//        LcTop202305 lcTop202305 = new LcTop202305();
//        System.out.println(lcTop202305.minimumTotal(triangle));
//    }

    /**
     * 103. root返回其节点值的锯齿形层序遍历（蛇形走位）
     * root = [3,9,20,null,null,15,7] ==》[[3],[20,9],[15,7]]
     *
     * @param root 2024-02-19
     * @return
     */
    public static List<List<Integer>> xxx_zigzagLevelOrder_103(TreeNode root) {
        // note：不用自己处理锯齿，借用双端队列的offerLast、offerFirst来处理左右端节点
        List<List<Integer>> ans = new LinkedList<>();
        if (root == null) {
            return ans;
        }
        boolean isOrderLeft = true;
        // tips: Queue队列一般用LinkedList实现，有可快速插入删除支持大数据等好处
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.offer(root);
        while (!nodeQueue.isEmpty()) {
            // step1：用了一个双向队列Deque存储结果，都是从左到右遍历 然后 利用双端队列选择插左还是插右
            Deque<Integer> levelList = new LinkedList<>();
            int size = nodeQueue.size();
            for (int i = 0; i < size; ++i) {
                TreeNode curNode = nodeQueue.poll();
                if (isOrderLeft) {
                    levelList.offerLast(curNode.val);
                } else {
                    levelList.offerFirst(curNode.val);
                }
                if (curNode.left != null) {
                    nodeQueue.offer(curNode.left);
                }
                if (curNode.right != null) {
                    nodeQueue.offer(curNode.right);
                }
            }
            ans.add(new LinkedList<>(levelList));
            isOrderLeft = !isOrderLeft;
        }
        return ans;
    }

//    public static void main(String[] args) {
//        TreeNode root3 = new TreeNode(4);
//        TreeNode root4 = new TreeNode(5);
//        TreeNode root1 = new TreeNode(2, root3, null);
//        TreeNode root2 = new TreeNode(3, null, root4);
//        TreeNode root = new TreeNode(1, root1, root2);
//        List<List<Integer>> result = zigzagLevelOrder_103(root);
//        System.out.println(JSON.toJSONString(result));
//    }

    /**
     * 92. 单链表的头指针head和两个整数left和right ，其中 left <= right ，反转从位置left到位置right的链表节点
     *
     * @param head
     * @param left
     * @param right
     * @return
     */
    public ListNode reverseBetween_92(ListNode head, int left, int right) {
        // 因为头节点有可能发生变化，使用虚拟头节点可以避免复杂的分类讨论
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode pre = dummyNode;
        // 第 1 步：从虚拟头节点走 left - 1 步，来到 left 节点的前一个节点
        // 建议写在 for 循环里，语义清晰
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }
        // 第 2 步：从 pre 再走 right - left + 1 步，来到画 right 节点
        ListNode rightNode = pre;
        for (int i = 0; i < right - left + 1; i++) {
            rightNode = rightNode.next;
        }
        // 第 3 步：切断出一个子链表（截取链表）
        ListNode leftNode = pre.next;
        ListNode curr = rightNode.next;
        // 注意：切断链接
        pre.next = null;
        rightNode.next = null;
        // 第 4 步：同第 206 题，反转链表的子区间
        reverseLinkedList(leftNode);
        // 第 5 步：接回到原来的链表中
        pre.next = rightNode;
        leftNode.next = curr;
        return dummyNode.next;
    }

    private void reverseLinkedList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
    }


    /**
     * 77. 组合 : 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的k个数的组合
     *
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine_77(int n, int k) {
        // *** 核心在于用 树型结构 深度递归 ***
        Deque<Integer> path = new ArrayDeque<>();
        List<List<Integer>> ans = new ArrayList<>();
        combineDFS(n, k, 1, path, ans);
        return ans;
    }
    private void combineDFS(int n, int k, int begin, Deque<Integer> path, List<List<Integer>> ans) {
        if (k == 0) {
            ans.add(new ArrayList<>(path));
            return;
        }
        for (int i = begin; i <= n; i++) {
            path.addLast(i);
            k = k - 1;
            combineDFS(n, k, i + 1, path, ans);
            path.removeLast();
            k = k + 1;
        }
    }

//    public static void main(String[] args) {
//        Top top = new Top();
//        System.out.println(top.combine_77(5, 2));
//    }

    /**
     * 75. 颜色分类， 0、 1 和 2 分别表示红色、白色和蓝色并按照红色、白色、蓝色顺序排列
     * 输入：nums = [2,0,2,1,1,0] ==> 输出：[0,0,1,1,2,2]
     *
     * @param nums
     */
    public void sortColors_20(int[] nums) {

    }

    /**
     * 64. 最小路径和：给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小
     * 例如：grid = [[1,3,1],[1,5,1],[4,2,1]] ==> 7
     *
     * @param grid
     * @return
     */

    public int minPathSum_64_ww(int[][] grid) {
        // 动态规划：dp核心在于根据当前的dp[][]值，结合各种情况推演规划出下一步的结果，最终求得最后值
        // 这里就很符合这个条件，当前这一步的值可以根据上一步结果推演：由于从左上角到右下角只能向下或者向右，则dp[i][j] = Math.min(dp[i-1,j],dp[i][j-1]) + grid[i][j]
        return minPathSum_dp(grid);
    }

    private int minPathSum_dp(int[][] grid) {
        int columns = grid[0].length;
        int row = grid.length;
        int[][] dp = new int[row][columns];
        dp[0][0] = grid[0][0];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < columns; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                if (i == 0) {
                    // row == 0的必定是最顶上横着移动
                    dp[i][j] = dp[i][j - 1] + grid[i][j];
                } else if (j == 0) {
                    // columns == 0的必定是最左上横着移动
                    dp[i][j] = dp[i - 1][j] + grid[i][j];
                } else {
                    // 每次过来要么是从上要么是从左，其实和背包问题有异曲同工之妙
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
                }
            }
        }
        return dp[grid.length - 1][grid[0].length - 1];
    }

//    public static void main(String[] args) {
//        LcTop202305 lcTop202305 = new LcTop202305();
//        int[][] grid = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
//        System.out.println(lcTop202305.minPathSum_64_ww(grid));
//    }

    /**
     * 56. 合并区间
     * 例如：intervals = [[1,3],[2,6],[8,10],[15,18]] ==> [[1,6],[8,10],[15,18]]
     *
     * @param intervals
     * @return
     */
    public int[][] merge_56(int[][] intervals) {
        // *** 死办法：左右区间大小比较合并 ***
        if (intervals.length == 0) {
            return new int[0][2];
        }
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        List<int[]> merged = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            int l = intervals[i][0], r = intervals[i][1];
            if (merged.size() == 0 || merged.get(merged.size() - 1)[1] < l) {
                merged.add(new int[]{l, r});
            } else {
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], r);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }

    /**
     * 53. 最大子数组和
     * 例如：nums = [-2,1,-3,4,-1,2,1,-5,4] ==》[4,-1,2,1] = 6
     *
     * @param nums
     * @return 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解
     */
    public int maxSubArray_17(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        // 贪心：：局部优解
//        maxSubArray4Greedy(nums);

        // 动态规划：dp[i]求解i位置的最大值 O(n)
        return maxSubArray4dp(nums);
    }
    private int maxSubArray4Greedy(int[] nums) {
        // 即当前元素之前的和小于0 则丢弃之前的元素。保证自己加上后不会结果比自己一个人更差(但不一定是最好)
        int preSum = 0;
        int max = nums[0];
        for (int i = 0; i < nums.length; i++) {
            if (preSum > 0) {
                preSum = preSum + nums[i];
            } else {
                preSum = nums[i];
            }
            max = Math.max(max, preSum);
        }
        return max;
    }
    private int maxSubArray4dp(int[] nums) {
        // 即dp[i]即代表在i位置的最大值
        int[] dp = new int[nums.length];
        int max = nums[0];
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && dp[i - 1] > 0) {
                dp[i] = dp[i - 1] + nums[i];
            } else {
                dp[i] = nums[i];
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    /**
     * 46. 全排列（经典的深度递归回溯算法）
     * 例如：nums = [1,2,3] ==> [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permute_46(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length == 0) {
            return res;
        }
        Deque<Integer> path = new ArrayDeque<>();
        boolean[] used = new boolean[nums.length];
        permuteDFS(nums, 0, used, path, res);
        return res;
    }
    private void permuteDFS(int[] nums, int depth, boolean[] used, Deque<Integer> path, List<List<Integer>> res) {
        if (depth == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        // 每个节点遍历一遍
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            // 在这一层的树上 防止出现 [1,2,2,1] 两次121的情况
            used[i] = true;
            path.addLast(nums[i]);
            permuteDFS(nums, depth + 1, used, path, res);
            used[i] = false;
            path.removeLast();
        }
    }

    /**
     * 43. 字符串相乘
     * 输入: num1 = "123", num2 = "456" ==> 输出: "56088"  (注意：不能使用任何内置的 BigInteger 库或直接将输入转换为整数)
     *
     * @param num1
     * @param num2
     * @return
     */
    public String multiply_15(String num1, String num2) {

        return "";
    }

    /**
     * 39. 组合总和
     * candidates = [2,3,6,7], target = 7 ==> [[2,2,3],[7]]
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum_39(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (candidates.length == 0) {
            return res;
        }
        // *** 树型结构的深度递归回溯+减枝 ***
        List<List<Integer>> ans = new ArrayList<>();
        // 第1步：排序。减少减枝的复杂度
        Arrays.sort(candidates);
        Deque<Integer> path = new ArrayDeque<>();
        combinationDFS(candidates, 0, target, ans, path);
        return ans;
    }
    private void combinationDFS(int[] candidates, int begin, int target, List<List<Integer>> ans, Deque<Integer> path) {
        if (target == 0) {
            ans.add(new ArrayList<>(path));
            return;
        }
        // 第2步：从begin开始，遍历树枝的时候可以把前面遍历过的剪了
        for (int index = begin; index < candidates.length; index++) {
            if (target < 0) {
                break;
            }
            path.addLast(candidates[index]);
            combinationDFS(candidates, index, target - candidates[index], ans, path);
            // 第3步：开始循环的下一个节点前把前面的移除了，因为这个节点已经添加到结果列表了
            path.removeLast();
        }
    }


    /**
     * 36. 有效的数独
     *
     * @param board
     * @return
     */
    public boolean isValidSudoku_13(char[][] board) {
        // 暴力遍历，时间复杂度O(1)；空间复杂度：O(n)
        return false;
    }

    /**
     * 在排序数组中查找元素的第一个和最后一个位置
     *
     * @param nums
     * @param target
     * @return 必须设计并实现时间复杂度为 O(log n) 的算法解决此问题
     */
    private int[] x_searchRange_12(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (target == nums[mid]) {
                int start = mid;
                for (int i = mid - 1; i >= 0; i--) {
                    if (nums[i] == target) {
                        start--;
                    }
                }
                int end = mid;
                for (int j = mid + 1; j < nums.length; j++) {
                    if (nums[j] == target) {
                        end++;
                    }
                }
                return new int[]{start, end};
            }
            if (nums[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return new int[]{-1, -1};
    }

    /**
     * 33.搜索旋转排序数组
     * 如 [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，
     * 则返回它的下标，否则返回 -1
     *
     * @param nums
     * @param target
     * @return 必须设计一个时间复杂度为 O(log n) 的算法解决此问题
     */
    public int search_33(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) {
            return -1;
        }
        if (n == 1) {
            return nums[0] == target ? 0 : -1;
        }
        // ** O(log n)想到二分法 **
        int l = 0, r = n - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            // 第1步：一切为二后任意一个节点是一定会有一侧是顺序的，也就是看nums[0]到nums[mid]是否顺序即可
            if (nums[0] <= nums[mid]) {
                if (nums[0] <= target && target < nums[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[n - 1]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return -1;
    }

    /**
     * 31.下一个排列：指其整数的下一个字典序更大的排列
     * 例如，arr = [1,2,3] 的下一个排列是 [1,3,2] 。 类似地，arr = [2,3,1] 的下一个排列是 [3,1,2]
     *
     * @param nums
     */
    public void nextPermutation_31_gg(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        if (i >= 0) {
            int j = nums.length - 1;
            while (j >= 0 && nums[i] >= nums[j]) {
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i + 1);
    }
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    private void reverse(int[] nums, int start) {
        int left = start, right = nums.length - 1;
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }


    /**
     * 24.两两交换链表中的节点
     * 给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）
     * 输入：head = [1,2,3,4] ==> 输出：[2,1,4,3]
     *
     * @param head
     * @return
     */
    public ListNode swapPairs_24(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = head.next;
        // 第1步：构造虚拟结点（涉及节点交换、移除的都可以考虑，这里主要第一个就要操作）
        ListNode dummy = new ListNode(0, head);
        // 第2步：dummy.next和next.next交换
        ListNode first;
        ListNode second;
        while ((first = dummy.next) != null && (second = dummy.next.next) != null) {
            dummy.next = second;
            ListNode temp = second.next;
            second.next = first;
            first.next = temp;
            dummy = first;
        }
        return newHead;
    }


    /**
     * 22.括号生成：数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合
     * 输入：n = 3 ==> 输出：["((()))","(()())","(())()","()(())","()()()"]
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesis_22_wait(int n) {
        List<String> combinations = new ArrayList<>();
        // 暴力求解法
        generateAll(new char[2 * n], 0, combinations);
        // 回溯法
        backtrack(combinations, new StringBuilder(), 0, 0, n);
        // 动态规划
        return combinations;
    }
    private void generateAll(char[] current, int pos, List<String> result) {
        if (pos == current.length) {
            if (valid(current)) {
                result.add(new String(current));
            }
        } else {
            current[pos] = '(';
            generateAll(current, pos + 1, result);
            current[pos] = ')';
            generateAll(current, pos + 1, result);
        }
    }
    private boolean valid(char[] current) {
        int balance = 0;
        for (char c : current) {
            if (c == '(') {
                ++balance;
            } else {
                --balance;
            }
            if (balance < 0) {
                return false;
            }
        }
        return balance == 0;
    }
    private void backtrack(List<String> ans, StringBuilder cur, int open, int close, int max) {
        if (cur.length() == max * 2) {
            ans.add(cur.toString());
            return;
        }
        if (open < max) {
            cur.append('(');
            backtrack(ans, cur, open + 1, close, max);
            cur.deleteCharAt(cur.length() - 1);
        }
        if (close < open) {
            cur.append(')');
            backtrack(ans, cur, open, close + 1, max);
            cur.deleteCharAt(cur.length() - 1);
        }
    }


    /**
     * 19：删除链表的倒数第 N 个结点 你能尝试使用一趟扫描实现吗
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd_19(ListNode head, int n) {
        // 第1步：虚拟个头节点。这是为了在落在N结点前一个（头指针为了数据）
        ListNode dummy = new ListNode(0, head);
        // 第2步：两个快慢指针
        ListNode fast = head;
        for (int i = 0; i < n; ++i) {
            fast = fast.next;
        }
        // 第3步：找到倒数第N+1个结点，并移除
        ListNode slow = dummy;
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }

    /**
     * 18.四数之和：
     *
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> fourSum_18(int[] nums, int target) {
        // 在三数之和的基础上扩展...
        return null;
    }


    /**
     * 17.电话号码d的字母组合
     *
     * @param digits
     * @return
     */
    public List<String> letterCombinations_17(String digits) {
        List<String> combinations = new ArrayList<>();
        if (digits.length() == 0) {
            return combinations;
        }
        Map<Character, String> phoneMap = new HashMap<Character, String>() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};
        backtrack(combinations, phoneMap, digits, 0, new StringBuffer());
        return combinations;
    }

    private void backtrack(List<String> combinations, Map<Character, String> phoneMap, String digits, int index, StringBuffer combination) {
        if (index == digits.length()) {
            combinations.add(combination.toString());
        } else {
            char digit = digits.charAt(index);
            String letters = phoneMap.get(digit);
            int lettersCount = letters.length();
            for (int i = 0; i < lettersCount; i++) {
                combination.append(letters.charAt(i));
                // 不断的递归回溯
                backtrack(combinations, phoneMap, digits, index + 1, combination);
                combination.deleteCharAt(index);
            }
        }
    }


    /**
     * 给你一个长度为 n 的整数数组 nums 和 一个目标值 target。请你从 nums 中选出三个整数，使它们的和与 target 最接近，返回这三个数的和
     *
     * @param nums
     * @param target
     * @return
     */
    public int threeSumClosest_05(int[] nums, int target) {
        int len = nums.length;
        if (len <= 3) {
            return nums[0] + nums[1] + nums[2];
        }
        // 第一步，先排序
        Arrays.sort(nums);
        int best = 1000000;
        for (int i = 0; i < len; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            // 第二步，找[起,止]位置
            int j = i + 1, k = len - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == target) {
                    return target;
                }
                // 第三步，根据总值与target的大小决定指针移动方向
                if (sum < target) {
                    j++;
                } else {
                    k--;
                }
                best = Math.min(best, Math.abs(sum - target));
            }
        }
        return best;
    }


    /**
     * 给你一个整数数组nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，同时还满足 nums[i] + nums[j] + nums[k] == 0
     *
     * @param nums
     * @return 返回所有和为 0 且 不重复的三元组
     */
    public List<List<Integer>> xxx_threeSum_15(int[] nums) {
        // *** 难点在于如何去除重复解：可以排序后，保证每一层的循环节点nums[n]!=nums[n-1]。正常的三层循环时间复杂度是：O(n^3)，但这里我们可以通过「三指针」解决复杂度 ***
        Arrays.sort(nums);
        int len = nums.length;
        List<List<Integer>> ans = new ArrayList<>();
        // 第1步：循环第一个节点
        for (int first = 0; first < len; ++first) {
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            int third = len - 1;
            int target = -nums[first];
            // 第2步：左右指针循环，减少复杂度
            for (int second = first + 1; second < len; ++second) {
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }
                while (second < third && nums[second] + nums[third] > target) {
                    --third;
                }
                // 第3步：第二个指针和第三个指针相同的时候，说明相碰了则没有满足的条件
                if (second == third) {
                    break;
                }
                if (nums[second] + nums[third] == target) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[first]);
                    list.add(nums[second]);
                    list.add(nums[third]);
                    ans.add(list);
                }
            }
        }
        return ans;
    }


    /**
     * 11. 盛最多水的容器
     * @param height
     * @return
     */
    public int maxArea_11(int[] height) {
        // 想在O(n)的事件范围内找到最大的，那么l、r的范围在缩小的时候就要小的前进一位保证结果最大化
        int l = 0, r = height.length - 1, ans = 0;
        while (l <= r) {
            int result = (r - l) * Math.min(height[l], height[r]);
            ans = Math.max(ans, result);
//            if (height[l] > height[r]) {
//                r--;
//            } else {
//                l++;
//            }
        }
        return ans;
    }


    /**
     * 一个字符串s，找到s中最长的回文子串（如果字符串的反序与原始字符串相同，则该字符串称为回文字符串）
     * qabcabcdd --> abcabc
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        // 1、暴力求解：每个字符循环一遍比较 时间复杂度：O(n^2)
        return longestPalindrome_dp(s);
    }

    private String longestPalindrome_dp(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }
        // *** 动态规划 dp[i][j] ==> s[i-1][j+1] ***
        // 第1步：所有单个dp[i][i]=true
        boolean[][] dp = new boolean[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }
        int begin = 0;
        int maxLen = 1;
        char[] charArray = s.toCharArray();
        // 第2步：start+L计算最大长度（时间复杂度有点高？？好像也没更好的办法）
        for (int L = 2; L <= len; L++) {
            for (int i = 0; i < len; i++) {
                int j = i + L - 1;
                if (j >= len) {
                    break;
                }
                if (charArray[i] != charArray[j]) {
                    dp[i][j] = false;
                } else {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        // 外面的看里面的是否回文。所以L循环得在外面保证L大的能兼容L小的（这样理论上L=5的一定能兼容L=3的）
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }
                // 第3步：只要 dp[i][L] == true 成立，就表示子串 s[i..L] 是回文，此时记录回文长度和起始位置
                if (dp[i][j] && j - i + 1 > maxLen) {
                    begin = i;
                    maxLen = j - i + 1;
                }

            }
        }
        return s.substring(begin, begin + maxLen);
    }

//    public static void main(String[] args) {
//        Top top = new Top();
//        System.out.println(top.longestPalindrome_dp("afabccba"));
//    }

    /**
     * 2.两数相加：两个非空的链表表示两个非负的整数。它们每位数字都是按照逆序的方式存储的，并且每个节点只能存储一位数字
     * [2,4,3] + [5,6,4] ==> [7,0,8] ； 342 + 465 = 807.然后反转
     *
     * @param l1
     * @param l2
     * @return 将两个数相加，并以相同形式返回一个表示和的链表
     */
    public ListNode addTwoNumbers_02(ListNode l1, ListNode l2) {
        // 1、暴力求解：遍历两个链表求和后再反转结果，时间复杂度：O(n) 空间复杂度：O(1)
        if (l1 == null || l2 == null) {
            return null;
        }
        Stack<Integer> resultStack = new Stack<>();
        int last = 0;
        while (l1 != null || l2 != null) {
            int v1 = l1 != null ? l1.val : 0;
            int v2 = l2 != null ? l2.val : 0;
            int value = v1 + v2 + last;
            last = value / 10;
            resultStack.add(value % 10);
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
        }
        if (last > 0) {
            resultStack.add(last);
        }
        // FIXME 加了堆栈之后这里的空间复杂度就增加不少了..
        ListNode next = new ListNode(resultStack.pop());
        while (!resultStack.isEmpty()) {
            next = new ListNode(resultStack.pop(), next);

        }
        return next;

        // 2、还没想到什么更好的解法..
    }

}
