package com.fang.doit.algo.tree.depth;

import com.fang.doit.algo.tree.TreeNode;
import com.fang.doit.algo.tree.search.BinaryTreeForeach;

import java.util.List;
import java.util.Stack;

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

        // 2.比较左右节点两边的节点数是否大于k

        return 0;
    }


    /**
     * 中序排列之后取出k小即可。时间复杂度O(N)，空间复杂度O(N) 其中复杂度可使用Stack优化
     *
     * @param root
     * @param k
     * @return
     * @see BinaryTreeForeach#inorderTraversal(com.fang.doit.algo.tree.search.BinaryTreeForeach.TreeNode)
     */
    private int kthSmallestByMiddle(TreeNode root, int k) {
        // 使用Stack优化之后不用再遍历整棵树O(N)，只需要O(k)
        Stack<TreeNode> stack = new Stack<>();
        while (root != null) {
            while (root != null) {
                stack.add(root);
                root = root.left;
            }
            TreeNode node = stack.pop();
            if (k-- == 0) {
                return node.val;
            }
            // 左边没有再找右边
            root = node.right;
        }
        return 0;

    }


}
