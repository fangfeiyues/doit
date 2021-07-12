package com.fang.doit.algo.tree.depth;

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
    Deque<Integer> path = new LinkedList<>();

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        dfs(root, targetSum);
        return ret;
    }

    public void dfs(TreeNode root, int targetSum) {
        if (root == null) {
            return;
        }
        path.offerLast(root.val);
        // TODO targetSum��ֵ��ô����.. �Զ�����???
        targetSum -= root.val;
        if (root.left == null && root.right == null && targetSum == 0) {
            ret.add(new LinkedList<>(path));
        }
        dfs(root.left, targetSum);
        dfs(root.right, targetSum);
        path.pollLast();
    }


    public static void main(String[] args) {
//����ʧ��: Line 35: error: reached end of file while parsing } ^ ��������:null stdout: null
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
