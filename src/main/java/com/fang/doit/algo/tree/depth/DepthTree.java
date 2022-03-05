package com.fang.doit.algo.tree.depth;

import com.alibaba.fastjson.JSON;
import com.fang.doit.algo.tree.TreeNode;
import com.fang.doit.algo.tree.search.BinaryTreeForeach;

import java.util.*;

/**
 * V2�汾�����д������
 *
 * @author created by fang on 2021/7/11/011 1:29
 */
public class DepthTree {

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

    int maxTimes = Integer.MIN_VALUE;
    List<Integer> has = new ArrayList<>();

    public int networkDelayTime(int[][] times, int n, int k) {
        if (times.length == 0) {
            return 0;
        }
        // Ҫ����ת��Map<> FIXME int[][] �����edge = int[0]��ôedge[0]���ǵ�һ��һά����ĵ�һλ
        Map<Integer, List<int[]>> map = new HashMap<>();
        for (int i = 0; i < times.length; i++) {
            int start = times[i][0];
            int end = times[i][1];
            int value = times[i][2];
            if (!map.containsKey(start)) {
                map.put(start, new ArrayList<int[]>());
            }
            map.get(start).add(new int[]{end, value});
        }
        netDfs(map, k, 0);
        if (has.size() < n) {
            return -1;
        }
        return maxTimes;
    }


    private void netDfs(Map<Integer, List<int[]>> map, int k, int allTime) {
        List<int[]> nexts = map.get(k);
        if (has.contains(k)) {
            return;
        }
        has.add(k);
        if (allTime > maxTimes) {
            maxTimes = allTime;
        }
        if (nexts == null) {
            return;
        }
        for (int[] point : nexts) {
            int next = point[0];
            int value = point[1];
            netDfs(map, next, allTime + value);
        }
    }


    /**
     * 98.��֤����������
     *
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        return dfsForValid(root);
    }

    private boolean dfsForValid(TreeNode root) {
        if (root == null) {
            return true;
        }
        TreeNode left = root.left;
        TreeNode right = root.right;
        // FIXME ���������еĽڵ㣿����
        if (left != null && left.val >= root.val) {
            return false;
        }
        if (right != null && right.val <= root.val) {
            return false;
        }
        if (!dfsForValid(left)) {
            return false;
        }
        return dfsForValid(right);
    }


    public static void main(String[] args) {
        DepthTree depthTree = new DepthTree();
//        TreeNode treeNode1 = new TreeNode(1);
//        TreeNode treeNode2 = new TreeNode(2, treeNode1, null);
//        TreeNode treeNode4 = new TreeNode(4);
//        TreeNode treeNode3 = new TreeNode(3, treeNode2, treeNode4);
//        TreeNode treeNode6 = new TreeNode(6);
//        TreeNode treeNode5 = new TreeNode(5, treeNode3, treeNode6);
//        TreeNode treeNode6 = new TreeNode(6);
//        TreeNode treeNode7 = new TreeNode(7);
//        TreeNode treeNode4 = new TreeNode(4);
//        TreeNode treeNode0 = new TreeNode(0);
//        TreeNode treeNode8 = new TreeNode(8);
//        TreeNode treeNode2 = new TreeNode(2, treeNode7, treeNode4);
//        TreeNode treeNode5 = new TreeNode(5, treeNode6, treeNode2);
//        TreeNode treeNode1 = new TreeNode(1, treeNode0, treeNode8);
//        TreeNode treeNode3 = new TreeNode(3, treeNode1, treeNode5);

//        System.out.println(JSON.toJSONString(depthTreeV2.lowestCommonAncestor(treeNode3, treeNode7, treeNode4).val));

//        ����ʧ��: Time Limit Exceeded ��������:[[1,2,1],[2,1,3]] 2 2 stdout:
//        [2,1,1],[2,3,1],[3,4,1]] 4 2 stdout:

//        ���ʧ��: ��������:[[1,2,1],[2,3,2],[1,3,2]] 3 1 ���Խ��:3 �������:2 stdout:
        int[][] times = {{1, 2, 1}, {2, 3, 2}, {1, 3, 2}};
        System.out.println(depthTree.networkDelayTime(times, 3, 1));


    }


}
