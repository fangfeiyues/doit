package com.fang.doit.algo.tree.depth;

import com.alibaba.fastjson.JSON;
import com.fang.doit.algo.dst.linked.Solution;
import com.fang.doit.algo.tree.TreeNode;
import com.fang.doit.algo.tree.search.BinaryTreeForeach;

import java.util.*;

/**
 * V2�汾�����д������
 *
 * @author created by fang on 2021/7/11/011 1:29
 */
public class DepthTreeV2 {

    /**
     * 230������һ�������������ĸ��ڵ� root����һ������ k �������һ���㷨�������е� k ����СԪ�أ��� 1 ��ʼ������
     * ���ף���������������������޸ģ�����/ɾ����������������ҪƵ���ز��ҵ� k С��ֵ���㽫����Ż��㷨��
     *
     * @param root
     * @param k
     * @return
     */
    public int kthSmallest(TreeNode root, int k) {

        kthSmallestByMiddle(root, k);

        // TODO 2.�Ƚ����ҽڵ����ߵĽڵ����Ƿ����k
        return 0;
    }


    /**
     * ��������֮��ȡ��kС���ɡ�ʱ�临�Ӷ�O(N)���ռ临�Ӷ�O(N) ���и��Ӷȿ�ʹ��Stack�Ż�
     * ���ɹ�: ִ�к�ʱ:0 ms,������100.00% ��Java�û� �ڴ�����:38.3 MB,������51.01% ��Java�û�
     *
     * @param root
     * @param k
     * @return
     * @see BinaryTreeForeach#inorderTraversal(com.fang.doit.algo.tree.search.BinaryTreeForeach.TreeNode)
     */
    private int kthSmallestByMiddle(TreeNode root, int k) {
        // ʹ��Stack�Ż�֮�����ٱ���������O(N)��ֻ��ҪO(k)
        Stack<TreeNode> stack = new Stack<>();
        // FIXME ������while(true)����ѭ��Ҫ�������治����զ��
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
            // ���û�������ұ�
            root = node.right;
        }
    }


    /**
     * 113������������ĸ��ڵ� root ��һ������Ŀ��� targetSum ���ҳ����� �Ӹ��ڵ㵽Ҷ�ӽڵ� ·���ܺ͵��ڸ���Ŀ��͵�·��
     *
     * @param root
     * @param targetSum
     * @return
     */
    List<List<Integer>> ret = new LinkedList<>();
    // FIXME Deque˫�����������List��Stack�������൫Ҳ������
    Deque<Integer> path = new LinkedList<>();

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        dfs(root, targetSum);
        return ret;
    }


    private void dfs(TreeNode root, int targetSum) {
        if (root == null) {
            return;
        }
        // FIXME targetSum��ֵ��ô����.. �Զ�����??? -- val�²㷽���ı䲻����ص��ϲ�
        targetSum = targetSum - root.val;
        path.offerLast(root.val);
        if (root.left == null && root.right == null && targetSum == 0) {
            ret.add(new LinkedList<>(path));
        }
        dfs(root.left, targetSum);
        dfs(root.right, targetSum);
        path.pollLast();
    }


    /**
     * 129:�����㵽Ҷ�ӽڵ�����֮��
     * ���ɹ�: ִ�к�ʱ:8 ms,������5.22% ��Java�û� �ڴ�����:37 MB,������5.05% ��Java�û�
     *
     * @param root
     * @return
     */
    private int all = 0;

    public int sumNumbers(TreeNode root) {
        dfs(root, "");
        return all;
    }

    private void dfs(TreeNode root, String split) {
        if (root == null) {
            return;
        }
        split = split + "" + root.val;
        if (root.left == null && root.right == null) {
            System.out.println(JSON.toJSONString(root) + ":" + split);
            all = all + Integer.parseInt(split);
        }
        dfs(root.left, split);
        dfs(root.right, split);
    }


    /**
     * 236:�������������������
     * ���ɹ�: ִ�к�ʱ:50 ms,������5.54% ��Java�û� �ڴ�����:43.3 MB,������5.00% ��Java�û�
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    Deque<TreeNode> deque = new LinkedList<>();

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 1. �����ƽⷨ�����������ڵ���ϲ�ڵ�
        //
        dfs(root, p, q);
        return deque.pollFirst();
    }


    private int dfs(TreeNode root, TreeNode p, TreeNode q) {
        int num = 0;
        if (root == null) {
            return 0;
        }
        if (root.val == p.val || root.val == q.val) {
            num = num + 1;
        }
        int leftNums = dfs(root.left, p, q);
        int rightNums = dfs(root.right, p, q);
        // ĳ���ڵ������м��������ڵ�ͳ�� FIXME ���˵ݹ����㻹��û������ʽ
        int alls = num + leftNums + rightNums;
        if (alls >= 2) {
            deque.addLast(root);
        }
        return alls;
    }


    /**
     * 743�������ӳ�ʱ�䡣��ĳ���ڵ� K ����һ���źš���Ҫ��ò���ʹ���нڵ㶼�յ��źţ��������ʹ���нڵ��յ��źţ����� -1
     *
     * @param times
     * @param n
     * @param k
     * @return
     */
    List<Integer> visited = new ArrayList<>();

    int allTimes = 0;

    public int networkDelayTime(int[][] times, int n, int k) {
        visited.add(k);

        return 0;
    }


    private void netDfs(int[][] times, int k) {
        if (visited.contains(k)) {
            return;
        }
        int[] next = times[k];
        for (int i = 0; i < next.length; i++) {
            int time = next[i];
            allTimes = allTimes + time;
            visited.add(k);
            netDfs(times,ti);
        }
    }

    public static void main(String[] args) {
//        TreeNode treeNode1 = new TreeNode(1);
//        TreeNode treeNode2 = new TreeNode(2, treeNode1, null);
//        TreeNode treeNode4 = new TreeNode(4);
//        TreeNode treeNode3 = new TreeNode(3, treeNode2, treeNode4);
//        TreeNode treeNode6 = new TreeNode(6);
//        TreeNode treeNode5 = new TreeNode(5, treeNode3, treeNode6);

        TreeNode treeNode6 = new TreeNode(6);
        TreeNode treeNode7 = new TreeNode(7);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode0 = new TreeNode(0);
        TreeNode treeNode8 = new TreeNode(8);
        TreeNode treeNode2 = new TreeNode(2, treeNode7, treeNode4);
        TreeNode treeNode5 = new TreeNode(5, treeNode6, treeNode2);
        TreeNode treeNode1 = new TreeNode(1, treeNode0, treeNode8);
        TreeNode treeNode3 = new TreeNode(3, treeNode1, treeNode5);

        DepthTreeV2 depthTreeV2 = new DepthTreeV2();
        System.out.println(JSON.toJSONString(depthTreeV2.lowestCommonAncestor(treeNode3, treeNode7, treeNode4).val));
    }


}
