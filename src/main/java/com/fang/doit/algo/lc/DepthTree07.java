package com.fang.doit.algo.lc;

import java.util.*;

/**
 * 深度优先算法
 *
 * @author fangfeiyue
 * @Date 2020/12/13 3:22 下午
 */
public class DepthTree07 {

    String[] letter_map = {" ", "*", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    List<String> res = new ArrayList<>();

    /**
     * 17:给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合
     *
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {
        //注意边界条件
        if (digits == null || digits.length() == 0) {
            return new ArrayList<>();
        }
        iterStr(digits, new StringBuilder(), 0);
        return res;
    }


    /**
     * 递归：利用不断的向下遍历的思想进行递归运算
     * TODO fangfeiyue递归的思想还要再体会下
     *
     * @param str
     */
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
    Deque<Integer> path = new LinkedList<>();

    /**
     * 113 路径总和
     * TODO 递归还是写不来... 这个递归有点精妙
     *
     * @param root
     * @param sum
     * @return
     */
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        dfs(root, sum);
        return ret;
    }

    public void dfs(TreeNode root, int sum) {
        if (root == null) {
            return;
        }
        path.offerLast(root.val);
        sum -= root.val;
        if (root.left == null && root.right == null && sum == 0) {
            ret.add(new LinkedList<>(path));
        }
        dfs(root.left, sum);
        dfs(root.right, sum);
        // 这里有个回退的过程
        path.pollLast();
    }


    Map<TreeNode, TreeNode> map = new HashMap<>();

    public List<List<Integer>> pathSumByWidth(TreeNode root, int sum) {
        if (root == null) {
            return ret;
        }
        Queue<TreeNode> queueNode = new LinkedList<>();
        Queue<Integer> queueSum = new LinkedList<>();
        queueNode.offer(root);
        queueSum.offer(0);
        while (!queueNode.isEmpty()) {
            TreeNode node = queueNode.poll();
            int rec = queueSum.poll() + node.val;
            if (node.left == null && node.right == null) {
                if (rec == sum) {
                    getPath(node);
                }
            } else {
                if (node.left != null) {
                    map.put(node.left, node);
                    queueNode.offer(node.left);
                    queueSum.offer(rec);
                }
                if (node.right != null) {
                    map.put(node.right, node);
                    queueNode.offer(node.right);
                    queueSum.offer(rec);
                }
            }
        }
        return ret;
    }

    public void getPath(TreeNode node) {
        List<Integer> temp = new LinkedList<>();
        while (node != null) {
            temp.add(node.val);
            // map获取上级节点
            node = map.get(node);
        }
        Collections.reverse(temp);
        ret.add(new LinkedList<>(temp));
    }


    /**
     * 124: 二叉树中的最大路径和且不一定经过根节点
     *
     * @param root
     * @return
     */
    public int maxPathSum(TreeNode root) {

        return 0;
    }


    /**
     * 515:在每个树行中找最大值
     *
     * @param root
     * @return
     */
    public List<Integer> largestValues(TreeNode root) {

        // 广度优先策略



        return null;
    }


}
