package com.fang.doit.algo.lc.stage1;

import com.fang.doit.algo.tree.TreeNode;

import java.util.*;

/**
 * �����������
 *
 * @author created by fang on 2021/7/11/011 1:29
 */
public class DepthTree {


    /**
     *  ------------------- ������������Ľ��⼼�ɣ����� -----------------
     *  1.�������ı�����ʽ��Ҫ�����֡� �ݹ� & ��ջ ��
     *    ** �ݹ�ĺ������ڣ����ݹ鷽����ʼ&����ʱ�����ĺ��� ��ǰ���ڵ��µ���������
     *    ** ��ջ�ĺ������ڣ�
     *
     *  ------------------------------------------------------
     */


    /**
     * 230������һ�������������ĸ��ڵ� root����һ������ k �������һ���㷨�������е� k ����СԪ�أ��� 1 ��ʼ������
     * ���ף���������������������޸ģ�����/ɾ����������������ҪƵ���ز��ҵ� k С��ֵ���㽫����Ż��㷨��
     *
     * @param root
     * @param k
     * @return
     */
    public int kthSmallest_01(TreeNode root, int k) {

        kthSmallestByMiddle(root, k);

        // TODO 2.�Ƚ����ҽڵ����ߵĽڵ����Ƿ����k
        return 0;
    }

    private int kthSmallestByMiddle(TreeNode root, int k) {
        // ++ �����������С�������γ�ջ�����ҵ���kС��Ԫ��
        Stack<TreeNode> stack = new Stack<>();
        while (true) {
            while (root != null) {
                stack.add(root);
                root = root.left;
            }
            if (stack.isEmpty()) {
                return 0;
            }
            TreeNode node = stack.pop();
            if (--k == 0) {
                return node.val;
            }
            root = node.right;
        }
    }


    /**
     * 113������������ĸ��ڵ� root ��һ������Ŀ��� targetSum ���ҳ����� ���ڵ�->Ҷ�ӽڵ� ·���ܺ͵��ڸ���Ŀ��͵�·��
     *
     * @param root
     * @param targetSum
     * @return
     */

    public List<List<Integer>> pathSum_02(TreeNode root, int targetSum) {
        pathSumDfs(root, targetSum);
        return ret;
    }

    List<List<Integer>> ret = new LinkedList<>();
    Deque<Integer> path = new LinkedList<>();

    private void pathSumDfs(TreeNode root, int targetSum) {
        if (root == null) {
            return;
        }
        targetSum = targetSum - root.val;
        path.offerLast(root.val);
        // ++ ���ڵ㿪ʼ�������ǰ�ڵ������ӽڵ㶼�ǿ���targetSum��Ϊ0 �����path������
        if (root.left == null && root.right == null && targetSum == 0) {
            ret.add(new LinkedList<>(path));
        }
        pathSumDfs(root.left, targetSum);
        pathSumDfs(root.right, targetSum);
        // ++ ���ڵ�������ѵ�ǰ�ڵ��Ƴ�����ʼ������һ���ڵ�
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

    public int sumNumbers_03(TreeNode root) {
        sumNumberDfs(root, "");
        return all;
    }

    private void sumNumberDfs(TreeNode root, String split) {
        if (root == null) {
            return;
        }
        split = split + "" + root.val;
        if (root.left == null && root.right == null) {
            all = all + Integer.parseInt(split);
        }
        sumNumberDfs(root.left, split);
        sumNumberDfs(root.right, split);
    }


    /**
     * 236:***** �������������������
     * ���ɹ�: ִ�к�ʱ:50 ms,������5.54% ��Java�û� �ڴ�����:43.3 MB,������5.00% ��Java�û�
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    Deque<TreeNode> deque = new LinkedList<>();

    public TreeNode lowestCommonAncestor_04(TreeNode root, TreeNode p, TreeNode q) {
        commonAncestorDfs(root, p, q);
        return deque.pollFirst();
    }

    // ��εݹ��е������.. -- ���ݹ鿪ʼ�ͽ�������ĺ���
    private int commonAncestorDfs(TreeNode root, TreeNode p, TreeNode q) {
        int num = 0;
        if (root == null) {
            return 0;
        }
        // ++ ��ǰ�ڵ㿪ʼ������ڵ��������ڵ���+1
        if (root.val == p.val || root.val == q.val) {
            num = num + 1;
        }
        int leftNums = commonAncestorDfs(root.left, p, q);
        int rightNums = commonAncestorDfs(root.right, p, q);
        // ++ ��ǰ�ڵ��������ǰ�ڵ� + �������ڵ� + ������·���ڵ��������Ӵ��ڵ���2��˵�����������������ڵ㣡
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

    public int networkDelayTime_05(int[][] times, int n, int k) {
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
                map.put(start, new ArrayList<>());
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
        // ++ ��������������forѭ���ݹ���һ����������������ݹ���һ��
        for (int[] point : nexts) {
            int next = point[0];
            int value = point[1];
            netDfs(map, next, allTime + value);
        }
    }


    /**
     * ����һ���������ĸ��ڵ� root ���ж����Ƿ���һ����Ч�Ķ���������
     * ��Ч �����������������£�
     * 1���ڵ��������ֻ���� С�� ��ǰ�ڵ������
     * 2���ڵ��������ֻ���� ���� ��ǰ�ڵ������
     * 3���������������������������Ҳ�Ƕ�����������
     *
     * @param root
     * @return
     */
    public boolean isValidBST_06_dd(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean validBSTDfs_error01(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return true;
        }
        // -- ���Ǻ͵�ǰ�ڵ�Ĵ�С�� ���Ǹ��������ĸ��ڵ��
        if (root.left != null && root.val <= root.left.val) {
            return false;
        }
        if (root.right != null && root.val >= root.right.val) {
            return false;
        }
        boolean left = validBSTDfs_error01(root.left);
        boolean right = validBSTDfs_error01(root.right);
        // ˼����������������������ϵ�ǰ�ڵ���ܳ��� ��[5,4,6,null,null,3,7]
        return left && right;
    }

    private boolean isValidBST(TreeNode node, long lower, long upper) {
        if (node == null) {
            return true;
        }
        // ++��ǰ�ڵ㿪ʼ���ڵ��С������(l,u)֮��
        if (node.val <= lower || node.val >= upper) {
            return false;
        }
        // ��������max����node.val,min����intrger.min ; ͬ�������� -- �е������!!!
        boolean left = isValidBST(node.left, lower, node.val);
        boolean right = isValidBST(node.right, node.val, upper);
        // ++��ǰ�ڵ��������������������
        return left && right;
    }


    /**
     * 114. ����������ĸ���� root �����㽫��չ��Ϊһ��������
     * 1��չ����ĵ�����Ӧ��ͬ��ʹ�� TreeNode ������ right ��ָ��ָ����������һ����㣬������ָ��ʼ��Ϊ null ��
     * 2��չ����ĵ�����Ӧ��������� ������� ˳����ͬ��
     *
     * @param root
     */
    public void flatten_07(TreeNode root) {
        // ++ ����������Ԫ�ض��ŵ��б��У��ٴ��б�ת������
        List<TreeNode> list = new ArrayList<>();
        preorderTraversal(root, list);
        int size = list.size();
        for (int i = 1; i < size; i++) {
            TreeNode prev = list.get(i - 1), curr = list.get(i);
            prev.left = null;
            prev.right = curr;
        }
    }

    private void preorderTraversal(TreeNode root, List<TreeNode> list) {
        if (root != null) {
            list.add(root);
            preorderTraversal(root.left, list);
            preorderTraversal(root.right, list);
        }
    }

    /**
     * 207.�����ѧ�ڱ���ѡ�� numCourses �ſγ̣���Ϊ 0 �� numCourses - 1
     * ��ѡ��ĳЩ�γ�֮ǰ��ҪһЩ���޿γ̡� ���޿γ̰����� prerequisites ���������� prerequisites[i] = [ai, bi] ����ʾ���Ҫѧϰ�γ� ai �� ���� ��ѧϰ�γ�  bi
     * <p>
     * eg.numCourses = 2, prerequisites = [[1,0],[0,1]] ==> false
     *
     * @param numCourses
     * @param prerequisites
     * @return �����ж��Ƿ����������пγ̵�ѧϰ
     */
    List<List<Integer>> edges;
    int[] visited;
    boolean valid = true;

    public boolean canFinish_08(int numCourses, int[][] prerequisites) {
        edges = new ArrayList<>();
        for (int i = 0; i < numCourses; ++i) {
            edges.add(new ArrayList<>());
        }
        visited = new int[numCourses];
        for (int[] info : prerequisites) {
            // ++ [[1,0],[0,1]] ������ת��ΪList������������ǿɸ��õģ�
            edges.get(info[1]).add(info[0]);
        }
        for (int i = 0; i < numCourses && valid; ++i) {
            if (visited[i] == 0) {
                dfs(i);
            }
        }
        return valid;
    }

    private void dfs(int u) {
        visited[u] = 1;
        for (int v : edges.get(u)) {
            if (visited[v] == 0) {
                dfs(v);
                if (!valid) {
                    return;
                }
            } else if (visited[v] == 1) {
                valid = false;
                return;
            }
        }
        visited[u] = 2;
    }

    /**
     * 333.����һ�����������ҵ��������Ķ�����������BST�������������ظ������Ĵ�С ���У����ָ���������ڵ�������
     * ������������BST���е����нڵ㶼�߱��������ԣ�
     * 1����������ֵС���丸�������ڵ��ֵ
     * 2����������ֵ�����丸�������ڵ��ֵ
     * <p>
     * ����: ������� O(n) ʱ�临�ӶȵĽⷨ��
     *
     * @param root
     * @return
     */
    public int largestBSTSubtree_09(TreeNode root) {
        return 0;
    }


    // f(o) ��ʾѡ�� o �ڵ������£�o �ڵ�������ϱ�ѡ��Ľڵ�����Ȩֵ��
    Map<TreeNode, Integer> f = new HashMap<>();
    // g(o) ��ʾ��ѡ�� o �ڵ������£�o �ڵ�������ϱ�ѡ��Ľڵ�����Ȩֵ��
    Map<TreeNode, Integer> g = new HashMap<>();

    /**
     * ���� root ֮�⣬ÿ����������ֻ��һ��������������֮������һ�����֮�󣬴�����С͵��ʶ��������ط������з��ݵ�����������һ�ö��������� ��� ����ֱ�������ķ�����ͬһ�����ϱ���� �����ݽ��Զ�����
     * ������������ root ������ �ڲ���������������� ��С͵�ܹ���ȡ����߽�� ��
     *
     * @param root
     * @return
     */
    public int rob_10_d(TreeNode root) {
        dfs(root);
        return Math.max(f.getOrDefault(root, 0), g.getOrDefault(root, 0));
    }

    private void dfs(TreeNode node) {
        if (node == null) {
            return;
        }
        dfs(node.left);
        dfs(node.right);
        // +++ ����֪����ǰ�����ڵ��Ƿ�Ҫ��ѡ�񣬹�ÿ���ڵ㶼������ѡ��
        f.put(node, node.val + g.getOrDefault(node.left, 0) + g.getOrDefault(node.right, 0));
        g.put(node, Math.max(f.getOrDefault(node.left, 0), g.getOrDefault(node.left, 0)) + Math.max(f.getOrDefault(node.right, 0), g.getOrDefault(node.right, 0)));
    }


    // ��ǰ�ڵ��Ƿ����������������ڵ��������ֵ���ڵ�������Сֵ
    private boolean is_BST_DFS(TreeNode root, Integer lower, Integer upper) {
        if (root == null) {
            return true;
        }
        //
        if (root.val < lower || root.val > upper) {
            return false;
        }
        boolean left = is_BST_DFS(root.left, lower, root.val);
        boolean right = is_BST_DFS(root.right, root.val, upper);
        return left & right;
    }


    /**
     * 386. ����һ������ n �����ֵ��򷵻ط�Χ [1, n] ����������, ��������һ��ʱ�临�Ӷ�Ϊ O(n) ��ʹ�� O(1) ����ռ���㷨
     * eg.���룺n = 13
     * �����[1,10,11,12,13,2,3,4,5,6,7,8,9]
     *
     * @param n
     * @return
     */
    public List<Integer> lexicalOrder_11(int n) {

        return null;
    }


    /**
     * 417.����һ�� m x n ���������� heights �� heights[r][c] ��ʾ���� (r, c) �ϵ�Ԫ�� ���ں�ƽ��ĸ߶�
     * ������������ result �� 2D �б� ������ result[i] = [ri, ci] ��ʾ��ˮ�ӵ�Ԫ�� (ri, ci) ���� �ȿ�����̫ƽ��Ҳ����������� ��
     *
     * @param heights
     * @return
     */
    // ++ ǰ�������ĸ���λ
    static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    int[][] heights;
    int m, n;

    public List<List<Integer>> pacificAtlantic_12_d(int[][] heights) {
        this.heights = heights;
        this.m = heights.length;
        this.n = heights[0].length;
        boolean[][] pacific = new boolean[m][n];
        boolean[][] atlantic = new boolean[m][n];
        // �Ϻ᷽λ
        for (int i = 0; i < m; i++) {
            pacificAtlanticDFS(i, 0, pacific);
        }
        // ������λ
        for (int j = 1; j < n; j++) {
            pacificAtlanticDFS(0, j, pacific);
        }
        // ������λ
        for (int i = 0; i < m; i++) {
            pacificAtlanticDFS(i, n - 1, atlantic);
        }
        // �º᷽λ
        for (int j = 0; j < n - 1; j++) {
            pacificAtlanticDFS(m - 1, j, atlantic);
        }
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    List<Integer> cell = new ArrayList<>();
                    cell.add(i);
                    cell.add(j);
                    result.add(cell);
                }
            }
        }
        return result;
    }

    private void pacificAtlanticDFS(int row, int col, boolean[][] ocean) {
        if (ocean[row][col]) {
            return;
        }
        ocean[row][col] = true;
        for (int[] dir : dirs) {
            int newRow = row + dir[0], newCol = col + dir[1];
            if (newRow >= 0 && newRow < m && newCol >= 0 && newCol < n && heights[newRow][newCol] >= heights[row][col]) {
                pacificAtlanticDFS(newRow, newCol, ocean);
            }
        }
    }


    /**
     * ���� ����һЩ���ڵ� 1 (��������) ���ɵ���ϣ�����ġ����ڡ�Ҫ������ 1 ������ ˮƽ������ֱ���ĸ������� ����
     * ����Լ��� grid ���ĸ���Ե���� 0������ˮ����Χ��
     *
     * @param grid
     * @return ���㲢���� grid �����ĵ������
     */
    int[][] pairs = new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    int maxArea = 0;
    int islandM, islandN;
    int[][] visitedArea;

    public int maxAreaOfIsland(int[][] grid) {
        islandM = grid.length;
        islandN = grid[0].length;
        // �ĸ���λ��ʼ
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                int max = 0;
                maxAreaDFS(grid, i, j, max);
                maxArea = Math.max(max, maxArea);
            }
        }
        return maxArea;
    }

    private void maxAreaDFS(int[][] grid, int row, int col, int max) {
        if (visitedArea[row][col] == 1) {
            return;
        }
        for (int[] pair : pairs) {
            int i = row - pair[0];
            int j = col - pair[1];
            if (i < islandM && j < islandN && i >= 0 && j >= 0 && grid[row - i][col - j] == 1) {
                max++;
                maxAreaDFS(grid, i, j, max);
            }
        }
    }


    public static void main(String[] args) {

        TreeNode treeNode6 = new TreeNode(6);
        TreeNode treeNode5 = new TreeNode(5, null, treeNode6);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode2 = new TreeNode(2, treeNode3, treeNode4);
        TreeNode treeNode1 = new TreeNode(1, treeNode2, treeNode5);

        DepthTree depthTree = new DepthTree();
        depthTree.flatten_07(treeNode1);
    }


}
