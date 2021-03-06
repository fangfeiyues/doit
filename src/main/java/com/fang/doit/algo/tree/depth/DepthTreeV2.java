package com.fang.doit.algo.tree.depth;

import com.fang.doit.algo.dst.linked.Solution;
import com.fang.doit.algo.tree.TreeNode;
import com.fang.doit.algo.tree.search.BinaryTreeForeach;

import java.util.*;

/**
 * V2版本务必手写！！！
 *
 * @author created by fang on 2021/7/11/011 1:29
 */
public class DepthTreeV2 {

    /**
     * 230：给定一个二叉搜索树的根节点 root，和一个整数 k 请你设计一个算法查找其中第 k 个最小元素（从 1 开始计数）
     * 进阶：如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化算法？
     *
     * @param root
     * @param k
     * @return
     */
    public int kthSmallest(TreeNode root, int k) {

        kthSmallestByMiddle(root, k);

        // TODO 2.比较左右节点两边的节点数是否大于k
        return 0;
    }


    /**
     * 中序排列之后取出k小即可。时间复杂度O(N)，空间复杂度O(N) 其中复杂度可使用Stack优化
     * 解答成功: 执行耗时:0 ms,击败了100.00% 的Java用户 内存消耗:38.3 MB,击败了51.01% 的Java用户
     *
     * @param root
     * @param k
     * @return
     * @see BinaryTreeForeach#inorderTraversal(com.fang.doit.algo.tree.search.BinaryTreeForeach.TreeNode)
     */
    private int kthSmallestByMiddle(TreeNode root, int k) {
        // 使用Stack优化之后不用再遍历整棵树O(N)，只需要O(k)
        Stack<TreeNode> stack = new Stack<>();
        // FIXME 这里是while(true)无限循环要考虑里面不存在咋办
        while (true) {
            while (root != null) {
                stack.add(root);
                root = root.left;
            }
            if (stack.empty()) {
                return 0;
            }
            TreeNode node = stack.pop();
            if (--k == 0) {
                return node.val;
            }
            // 左边没有再找右边
            root = node.right;
        }
    }


    /**
     * 113：给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径
     *
     * @param root
     * @param targetSum
     * @return
     */
    List<List<Integer>> ret = new LinkedList<>();
    // FIXME Deque双向链表相对于List或Stack能力更多但也更复杂
    Deque<Integer> path = new LinkedList<>();

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        dfs(root, targetSum);
        return ret;
    }


    private void dfs(TreeNode root, int targetSum) {
        if (root == null) {
            return;
        }
        // FIXME targetSum的值怎么回溯.. 自动回溯??? -- val下层方法改变不会带回到上层
        targetSum = targetSum - root.val;
        path.offerLast(root.val);
        if (root.left == null && root.right == null && targetSum == 0) {
            ret.add(new LinkedList<>(path));
        }
        dfs(root.left, targetSum);
        dfs(root.right, targetSum);
        path.pollLast();
    }


    public static void main(String[] args) {
//运行失败: Line 35: error: reached end of file while parsing } ^ 测试用例:null stdout: null
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2, treeNode1, null);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode3 = new TreeNode(3, treeNode2, treeNode4);
        TreeNode treeNode6 = new TreeNode(6);
        TreeNode treeNode5 = new TreeNode(5, treeNode3, treeNode6);
//        System.out.println(kthSmallestByMiddle(treeNode5, 3));
        DepthTreeV2 depthTreeV2 = new DepthTreeV2();
        System.out.println(depthTreeV2.pathSum(treeNode5, 12));
    }


}
