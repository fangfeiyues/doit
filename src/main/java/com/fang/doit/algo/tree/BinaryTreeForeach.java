package com.fang.doit.algo.tree;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * 遍历二叉树
 * created by fang on 2019/4/7/007 19:32
 */
public class BinaryTreeForeach {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 非递归中序遍历: 构建栈
     *
     * @param root
     * @return
     */
    private static List<Integer> inorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> list = new ArrayList<>();
        while (true) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                if (stack.isEmpty()) {
                    return list;
                }
                root = stack.pop();
                list.add(root.val);
                root = root.right;
            }
        }

    }

    /**
     * 前序遍历
     *
     * @param root
     * @return
     */
    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (true) {
            if (root != null) {
                list.add(root.val);
                stack.add(root);
                root = root.left;
            } else {
                if (stack.isEmpty()) {
                    return list;
                }
                root = stack.pop();
                root = root.right;
            }
        }
    }

    /**
     * 后序遍历
     *
     * @param root
     * @return
     */
    public static List<Integer> aftorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        int left = 1;
        int right = 2;
        Stack<TreeNode> stack = new Stack<>();
        Stack<Integer> point = new Stack<>();
        while (true) {
            if (root != null) {
                stack.add(root);
                root = root.left;
                point.add(left);
            } else {
                if (stack.isEmpty()) {
                    return list;
                }
                root = stack.pop();
                // 左右孩子都没有的时候再拿出来加入list
                if (root.right == null) {
                    list.add(root.val);
                }
                root = root.right;
            }
        }
    }

    //public static void postOrderStack(TreeNode root) {
    //    if (root == null) { return; }
    //    Stack<TreeNode> s = new Stack<TreeNode>();
    //    Map<TreeNode, Boolean> map = new HashMap<TreeNode, Boolean>();
    //    s.push(root);
    //    while (!s.isEmpty()) {
    //        TreeNode temp = s.peek();
    //        if (temp.left != null && !map.containsKey(temp.left)) {
    //            temp = temp.left;
    //            while (temp != null) {
    //                if (map.containsKey(temp)) { break; } else { s.push(temp); }
    //                temp = temp.left;
    //            }
    //            continue;
    //        }
    //        if (temp.right != null && !map.containsKey(temp.right)) {
    //            s.push(temp.right);
    //            continue;
    //        }
    //        TreeNode t = s.pop();
    //        map.put(t, true);
    //        System.out.println(t.val);
    //    }
    //}

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(10);
        TreeNode node2 = new TreeNode(7);
        TreeNode node3 = new TreeNode(12);
        TreeNode node4 = new TreeNode(6);
        TreeNode node5 = new TreeNode(11);
        TreeNode node6 = new TreeNode(9);
        TreeNode node7 = new TreeNode(13);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node5.right = node7;
        System.out.println(JSON.toJSONString(postorderTraversal2(node1)));

    }

    // -----------------------------------------------------

    public static List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node == null) {
                continue;
            }
            ret.add(node.val);
            // 先右后左，保证左子树先遍历
            stack.push(node.right);
            stack.push(node.left);
        }
        return ret;
    }

    /**
     * 1,2d的思想一样的 就是后序的比较方便
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        if (root == null) {
            return ret;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            TreeNode node = stack.pop();
            ret.add(node.val);
            cur = node.right;
        }
        return ret;
    }

    /**
     * 前序：root-left-right; 后序：left-right-root 如果把后序改成root-right-left就和前序加入顺序相反
     *
     * @param root
     * @return
     */
    public static List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node == null) {
                continue;
            }
            ret.add(node.val);
            stack.push(node.left);
            stack.push(node.right);
        }
        Collections.reverse(ret);
        return ret;
    }

}
