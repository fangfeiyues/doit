package com.fang.doit.algo.lc.stage2;

import com.fang.doit.algo.tree.TreeNode;

import java.util.*;

/**
 * @author : fangfeiyue
 * @version V1.0
 * @Project: doit
 * @Package com.fang.doit.algo.tree.width
 * @Description:
 * @date Date : 2022-02-24 11:54 PM
 */
public class LeetCodeTree {

    /**
     * 给定一棵二叉树的根节点 root ，请找出该二叉树中每一层的最大值
     * https://leetcode-cn.com/problems/find-largest-value-in-each-tree-row/
     *
     * @param root
     * @return
     */
    public List<Integer> largestValues(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        ArrayList preList = new ArrayList<>();
        preList.add(root);
        result.add(root.val);
        largestValuesLeve(preList, result);
        return result;
    }

    /**
     * 执行耗时:4 ms,击败了13.98% 的Java用户
     * 内存消耗:41.8 MB,击败了5.66% 的Java用户
     *
     * @param preNodes
     * @param results
     */
    private void largestValuesLeve(List<TreeNode> preNodes, List<Integer> results) {
        if (preNodes == null || preNodes.size() == 0) {
            return;
        }
        List<TreeNode> currentNodes = new ArrayList<>();
        List<Integer> maxList = new ArrayList<>();
        for (TreeNode node : preNodes) {
            if (node.left != null) {
                maxList.add(node.left.val);
                currentNodes.add(node.left);
            }
            if (node.right != null) {
                maxList.add(node.right.val);
                currentNodes.add(node.right);
            }
        }
        Optional<Integer> optional = maxList.stream().max(Integer::compareTo);
        optional.ifPresent(results::add);

        // 用递归就能用栈
        largestValuesLeve(currentNodes, results);
    }

    public static List<Integer> largestValuesByQueue(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        List<Integer> maxList = new ArrayList<>();
        if (root == null) {
            return maxList;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            // 记录当前一层的节点个数
            int size = queue.size();
            // 将下一层的所有孩子节点均加入队列
            while (size > 0) {
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
                size--;
            }
            // 求出这一层的最大值
            Integer max = list.stream().max(Integer::compareTo).get();
            maxList.add(max);
            list.clear();
        }
        return maxList;
    }


    /**
     * 【好题】给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值
     * https://leetcode-cn.com/problems/binary-tree-right-side-view/
     *
     * @param root
     * @return
     */
    public List<Integer> rightSideViewByBFS(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        // 宽度遍历树可「递归 + 队列先进先出」不断遍历某一个层级数据
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                if (i == size - 1) {
                    //将当前层的最后一个节点放入结果列表
                    res.add(node.val);
                }
            }
        }
        return res;
    }

    List<Integer> res = new ArrayList<>();

    public List<Integer> rightSideViewByDFS(TreeNode root) {
        dfs(root, 0);
        // 从根节点开始访问，根节点深度是0
        return res;
    }

    private void dfs(TreeNode root, int depth) {
        if (root == null) {
            return;
        }
        // 先访问 当前节点，再递归地访问 右子树 和 左子树。
        if (depth == res.size()) {
            // 如果当前节点所在深度还没有出现在res里，说明在该深度下当前节点是第一个被访问的节点，因此将当前节点加入res中。
            res.add(root.val);
        }
        depth++;
        dfs(root.right, depth);
        dfs(root.left, depth);
    }


    /**
     * 二叉树剪枝
     * https://leetcode-cn.com/problems/binary-tree-pruning/Related
     * 【递归】FIXME 向下递归的思想没理解透.
     *
     * @param root
     * @return
     */
    public TreeNode pruneTree(TreeNode root) {
//        return containsOne(root) ? root : null;
        return null;
    }
    //
//    public boolean containsOne(TreeNode node) {
//        if (node == null) {
//            return false;
//        }
//        boolean a1 = containsOne(node.left);
//        boolean a2 = containsOne(node.right);
//        if (!a1) {
//            node.left = null;
//        }
//        if (!a2) {
//            node.right = null;
//        }
//        return node.val == 1 || a1 || a2;
//    }


    /**
     * 从根节点到叶子节点的路径数字之和
     * https://leetcode-cn.com/problems/sum-root-to-leaf-numbers/
     *
     * @param root
     * @return
     */
    public int sumNumbers(TreeNode root) {
        return sumNumberDfs(root, 0);
    }

    public int sumNumberDfs(TreeNode root, int prevSum) {
        if (root == null) {
            return 0;
        }
        int sum = prevSum * 10 + root.val;
        if (root.left == null && root.right == null) {
            return sum;
        } else {
            return sumNumberDfs(root.left, sum) + sumNumberDfs(root.right, sum);
        }
    }

    /**
     * 向下的路径节点之和:给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目
     * 路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
     * 【穷举】https://leetcode-cn.com/problems/path-sum-iii/
     *
     * @param root
     * @param targetSum
     * @return
     */
    public int pathSumForAllV1(TreeNode root, int targetSum) {
        // 1.穷举法: 每个父节点向下搜索一次 时间复杂度：O(N^2),空间复杂度O(N)递归需要在栈上开辟空间
        if (root == null) {
            return 0;
        }
        int res = pathSumDfs(root, targetSum);
        res = res + pathSumForAllV1(root.right, targetSum);
        res = res + pathSumForAllV1(root.left, targetSum);
        return res;
    }

    // 以节点node向下遍历有多少中可能
    private int pathSumDfs(TreeNode node, int targetSum) {
        int res = 0;
        if (node == null) {
            return 0;
        }
        int val = node.val;
        if (targetSum == val) {
            // 这里为什么不直接返回1而是再将res++,因为下面的node.val==0那路径就会不断加长
            res++;
        }
        res = res + pathSumDfs(node.left, targetSum - val);
        res = res + pathSumDfs(node.right, targetSum - val);
        return res;
    }

    /**
     * 【前缀和】好题！！！
     * 遍历一次每次记下节点到根节点距离，两距离的差就是向下的路径节点之和。注意到根节点距离不能包括本身
     *
     * @return
     */
    public int pathSumForPrefixSumV2(TreeNode root, int targetSum) {
        HashMap<Integer, Integer> prefixSum = new HashMap<>();
        // key:前缀和； value:
        prefixSum.put(0, 1);
        return pathSumForPrefixSumV2DFS(root, targetSum, 0, prefixSum);
    }

    private int pathSumForPrefixSumV2DFS(TreeNode root,
                                         int targetSum,
                                         int curr,
                                         HashMap<Integer, Integer> prefix) {
        if (root == null) {
            return 0;
        }
        int ret = 0;
        // 当前节点的前缀和
        curr = curr + root.val;

        // 核心：距离当前节点距离是targetSum的节点，即前缀和是curr - targetSum的节点数量
        ret = prefix.getOrDefault(curr - targetSum, 0);

        // 把当前阶段的前缀和也到Map
        prefix.put(curr, prefix.getOrDefault(curr, 0) + 1);

        // 累加下级节点的
        ret += pathSumForPrefixSumV2DFS(root.left, targetSum, curr, prefix);
        ret += pathSumForPrefixSumV2DFS(root.right, targetSum, curr, prefix);

        // 重要：左右节点遍历完后，因为是求当前路径的下级所以回到本层后需要移除当前节点！！！
        prefix.put(curr, prefix.getOrDefault(curr, 0) - 1);
        return ret;
    }


    /**
     * 把二叉搜索树转换为累加树
     * https://leetcode-cn.com/problems/convert-bst-to-greater-tree/
     */
    int sum = 0;

    public TreeNode convertBST(TreeNode root) {
        // 先右-再中-再左
        if (root == null) {
            return null;
        }
        convertBST(root.right);
        sum = sum + root.val;
        root.val = sum;
        convertBST(root.left);
        return root;
    }


}
