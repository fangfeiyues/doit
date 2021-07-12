package com.fang.doit.algo.tree;

/**
 * 树节点
 *
 * @author fangfeiyue
 * @Date 2020/12/7 7:26 下午
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

}
