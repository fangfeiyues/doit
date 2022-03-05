package com.fang.doit.algo.tree.search;

import com.fang.doit.algo.tree.TreeNode;

import java.util.*;

/**
 * @author fangfeiyue
 * @Date 2020/12/7 7:26 下午
 */
public class Tree06 {

    /**
     * 中序遍历：左-中-右
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversalInRecursion(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        // 递归（栈的深度问题）
        // 解答成功: 执行耗时:0 ms,击败了100.00% 的Java用户 内存消耗:37 MB,击败了42.72% 的Java用户
        if (root.left != null) {
            list.addAll(inorderTraversalInRecursion(root.left));
        }
        list.add(root.val);
        list.addAll(inorderTraversalInRecursion(root.right));
        return list;
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stk = new LinkedList<>();
        // 两个while完事
        while (root != null || !stk.isEmpty()) {
            while (root != null) {
                stk.push(root);
                root = root.left;
            }
            root = stk.pop();
            res.add(root.val);
            root = root.right;
        }
        return res;
    }


    /**
     * 236:找到p和q的最近公共祖先
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 1.暴力破解：维护两条包含节点链路列表，并取出链表。这里可以优化下利用广度BFS发现到p q节点就没必要再继续向下遍历了

        // 2.递归的思想有点难。lowestCommonAncestorByRecursion(root,p,q)
        lowestCommonAncestorByRecursion(root, p, q);

        return null;
    }


    public TreeNode lowestCommonAncestorByRecursion(TreeNode cur, TreeNode p, TreeNode q) {
        if (cur == null || cur == p || cur == q) {
            return cur;
        }
        TreeNode left = lowestCommonAncestor(cur.left, p, q);
        TreeNode right = lowestCommonAncestor(cur.right, p, q);
        //如果left为空，说明这两个节点在cur结点的右子树上，我们只需要返回右子树查找的结果即可
        if (left == null) {
            return right;
        }
        //同上
        if (right == null) {
            return left;
        }
        //如果left和right都不为空，说明这两个节点一个在cur的左子树上一个在cur的右子树上，
        //我们只需要返回cur结点即可。
        return cur;
    }

    private TreeNode ans;

    private boolean dfs(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return false;
        }
        boolean lson = dfs(root.left, p, q);
        boolean rson = dfs(root.right, p, q);
        if ((lson && rson) ||
                ((root.val == p.val || root.val == q.val) && (lson || rson))) {
            ans = root;
        }
        return lson || rson || (root.val == p.val || root.val == q.val);
    }

    public TreeNode lowestCommonAncestorV2(TreeNode root, TreeNode p, TreeNode q) {
        this.dfs(root, p, q);
        return this.ans;
    }

    /**
     * 437：找出路径和等于给定数值的路径总数
     *
     * @param root
     * @param sum
     * @return
     */
    public int pathSum(TreeNode root, int sum) {
        // 1. 深度优先遍历

        return 0;
    }






    /**
     * 449：序列化和反序列化二叉搜索树
     *
     * @param root
     * @return
     */
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {

        return null;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        return null;
    }

    public static void main(String[] args) {
//        Map<Long, Long> map = Maps.newHashMap();
//        map.put(1L, null);
//        map.put(2L, null);
//        System.out.println(JSON.toJSONString(map));

        System.out.println(getCurrentQuarterEndTime().getTime());

        System.out.println(getCurrentQuarterStartTime().getTime());
    }


    public static Date getCurrentQuarterStartTime() {
        Calendar c = Calendar.getInstance();
        int currentMonth = c.get(Calendar.MONTH) + 1;
        try {
            if (currentMonth <= 3) {
                c.set(Calendar.MONTH, 0);
            } else if (currentMonth <= 6) {
                c.set(Calendar.MONTH, 3);
            } else if (currentMonth <= 9) {
                c.set(Calendar.MONTH, 6);
            } else if (currentMonth <= 12) {
                c.set(Calendar.MONTH, 9);
            }
            c.set(Calendar.DATE, 1);
            return c.getTime();
        } catch (Exception e) {
//            log.error("getCurrentQuarterStartTime exception:", e);
        }
        return null;
    }

    public static Date getCurrentQuarterEndTime() {
        Calendar c = Calendar.getInstance();
        int currentMonth = c.get(Calendar.MONTH) + 1;
        try {
            if (currentMonth <= 3) {
                c.set(Calendar.MONTH, 2);
                c.set(Calendar.DATE, 31);
            } else if (currentMonth <= 6) {
                c.set(Calendar.MONTH, 5);
                c.set(Calendar.DATE, 30);
            } else if (currentMonth <= 9) {
                c.set(Calendar.MONTH, 8);
                c.set(Calendar.DATE, 30);
            } else if (currentMonth <= 12) {
                c.set(Calendar.MONTH, 11);
                c.set(Calendar.DATE, 31);
            }
            return c.getTime();
        } catch (Exception e) {
//            log.error("getCurrentQuarterEndTime exception:", e);
        }
        return null;
    }

}
