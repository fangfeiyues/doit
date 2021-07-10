package com.fang.doit.algo.tree.depth;

import com.fang.doit.algo.tree.TreeNode;
import com.fang.doit.algo.tree.search.BinaryTreeForeach;

import java.util.List;
import java.util.Stack;

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

        // 2.�Ƚ����ҽڵ����ߵĽڵ����Ƿ����k

        return 0;
    }


    /**
     * ��������֮��ȡ��kС���ɡ�ʱ�临�Ӷ�O(N)���ռ临�Ӷ�O(N) ���и��Ӷȿ�ʹ��Stack�Ż�
     *
     * @param root
     * @param k
     * @return
     * @see BinaryTreeForeach#inorderTraversal(com.fang.doit.algo.tree.search.BinaryTreeForeach.TreeNode)
     */
    private int kthSmallestByMiddle(TreeNode root, int k) {
        // ʹ��Stack�Ż�֮�����ٱ���������O(N)��ֻ��ҪO(k)
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
            // ���û�������ұ�
            root = node.right;
        }
        return 0;

    }


}
