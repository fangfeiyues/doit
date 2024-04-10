package com.fang.doit.algo.classes.tree.width;

import com.fang.doit.algo.classes.tree.TreeNode;

/**
 * @author : fangfeiyue
 * @version V1.0
 * @Project: doit
 * @Package com.fang.doit.algo.classes.tree.width
 * @Description:
 * @date Date : 2022-02-24 11:54 PM
 */
public class CBTInserter {

    private TreeNode root;

    /**
     * 043 往完全二叉树添加节点
     * https://leetcode-cn.com/problems/complete-binary-tree-inserter/
     *
     * @param root
     */
    public CBTInserter(TreeNode root) {
        root = root;
    }

    /**
     * 向树中插入一个值为 Node.val == val的新节点 TreeNode
     *
     * @param v
     * @return 返回插入节点 TreeNode 的父节点的值
     */
    public int insert(int v) {
        if (root == null) {
            root = new TreeNode(v);
            return 0;
        }

        // 宽度遍历？？？


        return 0;
    }


    public TreeNode get_root() {
        return root;
    }
}
