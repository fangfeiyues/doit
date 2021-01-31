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
     * TODO 递归的思想还要再体会下
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
    // 双端队列容器(两端都可进出)，主要满足这里的先进后出的效果。用Depue代替Stack!!!
    Deque<Integer> path = new LinkedList<>();

    /**
     * 113 路径总和。给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径
     * 深度递归遍历所有的叶子节点 把满足条件的加入到列表里。
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
     * 515:需要在二叉树的每一行中找到最大的值
     * 一层一层的遍历即可 很简单
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
        // 获取到整个树的最大值和最小值 然后进行比较
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

}
