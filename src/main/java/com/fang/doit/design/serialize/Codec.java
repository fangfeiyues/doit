package com.fang.doit.design.serialize;

import com.fang.doit.algo.classes.tree.TreeNode;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * @author : fangfeiyue
 * @version V1.0
 * @Project: doit
 * @Package com.fang.doit.design.serialize
 * @Description:
 * @date Date : 2024-05-13 3:14 上午
 */
public class Codec {

    /**
     * Encodes a tree to a single string.
     *
     * @param root
     * @return
     */
    public String serialize(TreeNode root) {
        // 二叉树的前置序列化
        StringBuilder sb = new StringBuilder();
        preSerialize(root,sb);
        return sb.toString();
    }


    private void preSerialize(TreeNode root, StringBuilder sb) {
        if (root == null) {
            return;
        }
        sb.append(root.val).append("-");
        preSerialize(root.left, sb);
        preSerialize(root.right, sb);
    }


    /**
     * Decodes your encoded data to tree.
     *
     * @param data
     * @return
     */
    public TreeNode deserialize(String data) {
        // 前置二叉树的反序列化

        // 遇到大的，说明在右分支
        List<Integer> list = Arrays.stream(data.split("-")).map(Integer::valueOf).collect(Collectors.toList());
        Stack<TreeNode> stack = new Stack<>();
        TreeNode root = new TreeNode(list.get(0));
        stack.push(root);
        for (int i = 1; i < list.size(); i++) {
            int value = list.get(i);
            TreeNode pre = stack.peek();
            while (!stack.isEmpty() && stack.peek().val < value) {
                pre = stack.pop();
            }
            TreeNode news = new TreeNode(value);
            if (pre.val > value) {
                pre.left = news;
            } else {
                pre.right = news;
            }
            stack.push(news);
        }
        return root;
    }


    public static void main(String[] args) {
        Codec codec = new Codec();
        String s = "4-2-1-3-5-6-";
        TreeNode node = codec.deserialize(s);
        System.out.println(codec.serialize(node));
    }
}
