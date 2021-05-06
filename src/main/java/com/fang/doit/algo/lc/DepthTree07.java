package com.fang.doit.algo.lc;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 深度优先算法
 *
 * @author fangfeiyue
 * @Date 2020/12/13 3:22 下午
 */
public class DepthTree07 {

    /**
     * 对于深度
     */

    String[] letter_map = {" ", "*", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    List<String> res = new ArrayList<>();

    /**
     * 17:给定一个仅包含数字2-9 的字符串，返回所有它能表示的字母组合
     *
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {
        // 思考：利用不断的向下遍历的思想进行递归运算
        //注意边界条件
        if (digits == null || digits.length() == 0) {
            return new ArrayList<>();
        }
        iterStr(digits, new StringBuilder(), 0);
        return res;
    }

    private void iterStr(String str, StringBuilder letter, int index) {
        // index为给定字符串的深度的时候即为最底层就没了
        if (index == str.length()) {
            res.add(letter.toString());
            return;
        }
        char c = str.charAt(index);
        int pos = c - '0';
        String map_string = letter_map[pos];
        for (int i = 0; i < map_string.length(); i++) {
            letter.append(map_string.charAt(i));
            // 向深处遍历
            iterStr(str, letter, index++);
            // 这里是啥？？？
            letter.deleteCharAt(letter.length() - 1);
        }
    }


    List<List<Integer>> ret = new LinkedList<>();
    // 双端队列容器(两端都可进出)，主要满足这里的先进后出的效果。用Depue代替Stack!!!
    Deque<Integer> path = new LinkedList<>();

    /**
     * 113 路径总和。给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径
     *
     * @param root
     * @param sum
     * @return
     */
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        dfs(root, sum);
        return ret;
    }

    private void dfs(TreeNode root, int sum) {
        if (root == null) {
            return;
        }
        path.addLast(root.val);
        sum = sum - root.val;
        // 要到叶子节点
        if (sum == 0 && root.left == null && root.right == null) {
            ret.add(new LinkedList<>(path));
        }

        // 循环一直要到叶子节点才行
        dfs(root.right, sum);
        dfs(root.left, sum);

        // 这里到了叶子节点，需要移除
        path.pollLast();
    }


    /**
     * 124: 二叉树中的最大路径和且不一定经过根节点
     *
     * @param root
     * @return
     */
    int max = 0;

    public int maxPathSum(TreeNode root) {
        //思路：找到每个节点下的最大路径
        return dfsForMax(root);
    }

    private int dfsForMax(TreeNode node) {
        if (node == null) {
            return max;
        }
        if (node.left == null && node.right == null) {
            if (node.val > max) {
                max = node.val;
            }
        } else {
            int leftValue = dfsForMax(node.left);
            int rightValue = dfsForMax(node.right);
            int value = leftValue + rightValue + node.val;
            if (value > max) {
                max = value;
            }
        }
        return max;
    }


    /**
     * 515:需要在二叉树的每一行中找到最大的值
     *
     * @param root
     * @return
     */
    public List<Integer> largestValues(TreeNode root) {
        // LinkedList实现队列
        Queue<TreeNode> queue = new LinkedList<>();
        List<Integer> values = new ArrayList<>();
        if (root != null) {
            //入队
            queue.add(root);
        }
        while (!queue.isEmpty()) {
            int max = Integer.MIN_VALUE;
            //每一层的数量
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                //出队
                TreeNode node = queue.poll();
                //记录每层的最大值
                max = Math.max(max, node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            values.add(max);
        }
        return values;
    }


    /**
     * 1026：节点与其祖先之间的最大差值
     *
     * @param root
     * @return
     */
    private int ans = 0;

    public int maxAncestorDiff(TreeNode root) {
        int min = 10001, max = -1;
        Dfs(root, min, max);
        return ans;
    }

    private void Dfs(TreeNode root, int min, int max) {
        if (root == null) {
            return;
        }
        // 获取到整个树的最大值和最小值,然后进行比较
        if (root.val < min) {
            min = root.val;
        }
        if (root.val > max) {
            max = root.val;
        }
        if (root.left == null && root.right == null) {
            if (max - min > ans) {
                ans = max - min;
            }
        } else {
            // 这里的深度递归
            Dfs(root.left, min, max);
            Dfs(root.right, min, max);
        }
    }


    /**
     * 837:最大人工岛。0代表海洋1代表陆地我们最多只能将一格 0海洋变成 1变成陆地。进行填海之后地图上最大的岛屿面积是多少？（上、下、左、右四个方向相连的 1 可形成岛屿）
     *
     * @param grid 二维数组
     * @return
     */
    int maxIsland = 0;

    public int largestIsland(int[][] grid) {
        // 每个以自己为中心 向四边深度衍生。有点难..先过吧
        return 0;
    }


    /**
     * 200:给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
     * 岛屿总是被水包围并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
     *
     * @param grid
     * @return
     */
    Map<String, Boolean> exist = new HashMap<>();
    int num = 0;

    public int numIslands(char[][] grid) {
        // 找到'1'的子节点都是'0'即可
        for (int j = 0; j < grid[0].length; j++) {
            for (int i = 0; i < grid.length; i++) {
                boolean result = islandsDfs(grid, i, j);
                if (result) {
                    num++;
                }
            }
        }
        return num;
    }

    private boolean islandsDfs(char[][] grid, int i, int j) {
        char position = grid[i][j];
        String key = i + "-" + j;
        if (exist.containsKey(key)) {
            return false;
        }
        exist.put(key, true);

        if (position == '0') {
            return false;
        }
        // 上
        if (i - 1 >= 0) {
            islandsDfs(grid, i - 1, j);
        }

        if (i + 1 < grid.length) {
            islandsDfs(grid, i + 1, j);
        }
        if (j - 1 >= 0) {
            islandsDfs(grid, i, j - 1);
        }
        if (j + 1 < grid[0].length) {
            islandsDfs(grid, i, j + 1);
        }
        return true;
    }


    /**
     * 721: 账号合并
     *
     * @param accounts
     * @return
     */
    public List<List<String>> accountsMerge(List<List<String>> accounts) {

        // 1. Map<email,List<user>> 暴力破解只要List中有任意两个相等即可合并此两个账号

        // 2. 并查集Union，题目的大意就是要把两个账号进行合并通过并查级找到公共合并即可

        return null;
    }

    public List<List<String>> accountsMergeByUF(List<List<String>> accounts) {
        Map<String, Integer> emailToIndex = new HashMap<String, Integer>();
        Map<String, String> emailToName = new HashMap<String, String>();
        int emailsCount = 0;
        for (List<String> account : accounts) {
            String name = account.get(0);
            int size = account.size();
            for (int i = 1; i < size; i++) {
                String email = account.get(i);
                if (!emailToIndex.containsKey(email)) {
                    // 两个List<String> 账号列表之间有关联，在合并的时候就能找到公共的祖先进行合并
                    emailToIndex.put(email, emailsCount++);
                    emailToName.put(email, name);
                }
            }
        }
        UnionFind uf = new UnionFind(emailsCount);
        for (List<String> account : accounts) {
            String firstEmail = account.get(1);
            int firstIndex = emailToIndex.get(firstEmail);
            int size = account.size();
            for (int i = 2; i < size; i++) {
                String nextEmail = account.get(i);
                int nextIndex = emailToIndex.get(nextEmail);
                // 同一个人下面的账号进行合并
                uf.union(firstIndex, nextIndex);
            }
        }
        Map<Integer, List<String>> indexToEmails = new HashMap<>();
        for (String email : emailToIndex.keySet()) {
            int index = uf.find(emailToIndex.get(email));
            List<String> account = indexToEmails.getOrDefault(index, new ArrayList<String>());
            account.add(email);
            indexToEmails.put(index, account);
        }
        List<List<String>> merged = new ArrayList<List<String>>();
        for (List<String> emails : indexToEmails.values()) {
            Collections.sort(emails);
            String name = emailToName.get(emails.get(0));
            List<String> account = new ArrayList<String>();
            account.add(name);
            account.addAll(emails);
            merged.add(account);
        }
        return merged;
    }

    class UnionFind {
        int[] parent;

        public UnionFind(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public void union(int index1, int index2) {
            parent[find(index2)] = find(index1);
        }

        public int find(int index) {
            // 默认自己父亲节点就是自己
            if (parent[index] != index) {
                parent[index] = find(parent[index]);
            }
            return parent[index];
        }
    }


    /**
     * 1631:每次可以往上下左右四个方向之一移动，你想要找到耗费体力最小的一条路径（一条路径耗费的体力值是路径上相邻格子之间高度差绝对值的最大值决定的）
     *
     * @param heights
     * @return
     */
    public int minimumEffortPath(int[][] heights) {

        return 0;
    }


    /**
     * 473：输入为小女孩拥有火柴的数目每根火柴用其长度表示。输出即为是否能用所有的火柴拼成正方形
     *
     * @param nums
     * @return
     */
    public List<Integer> nums;
    public int[] sums;
    public int possibleSquareSide;

    public boolean makeSquare(int[] nums) {
        // 解题思路有点不拘一格..
        if (nums == null || nums.length == 0) {
            return false;
        }
        int L = nums.length;
        int perimeter = 0;
        for (int i = 0; i < L; i++) {
            perimeter += nums[i];
        }
        this.possibleSquareSide = perimeter / 4;
        if (this.possibleSquareSide * 4 != perimeter) {
            return false;
        }
        this.nums = Arrays.stream(nums).boxed().collect(Collectors.toList());
        Collections.sort(this.nums, Collections.reverseOrder());
        return this.dfs(0);
    }

    public boolean dfs(int index) {
        if (index == this.nums.size()) {
            return (sums[0] == sums[1]) && (sums[1] == sums[2]) && (sums[2] == sums[3]);
        }
        int element = this.nums.get(index);
        // 这里相当于把每根火柴都在4个组内都深度操作了一遍（除非这个组的大小大于perimeter / 4）
        for (int i = 0; i < 4; i++) {
            if (this.sums[i] + element <= this.possibleSquareSide) {
                this.sums[i] += element;
                if (this.dfs(index + 1)) {
                    return true;
                }
                // 如果不合适则重新推出
                this.sums[i] -= element;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        long a = 1;
        double b = (double)a;
        System.out.println(b);
    }

}
