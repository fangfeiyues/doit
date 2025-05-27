package com.fang.doit.algo.lc;


import com.fang.doit.algo.classes.tree.TreeNode;

import java.util.*;
import java.util.stream.Collectors;
        
/**
 * @author : fangfeiyue
 * @version V1.0
 * @Project: doit
 * @Package com.fang.doit.algo.lc
 * @Description:
 * @date Date : 2024-06-17 1:39 上午
 */
public class Train02 {

    /**
     * -- 2024.06.17 开始挑战!!! 工作日 * 2，休息日 * 5 --
     */

    public static void main(String[] args) {
        Train02 hot100 = new Train02();
//        System.out.println(hot100.trap(new int[]{4, 2, 0, 3, 2, 5}));
//        System.out.println(hot100.shortestWay("abc","abcbc"));
//        System.out.println(hot100.lengthOfLongestSubstringTwoDistinct("eceba"));
//        System.out.println(hot100.subarraySum_xxx(new int[]{1, 2, 3}, 3));
//        System.out.println(hot100.findMaxConsecutiveOnes(new int[]{1, 1, 0, 1}));
//        System.out.println(numKLenSubstrNoRepeats("havefunonleetcode", 5));
//        System.out.println(JSON.toJSONString(maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3)));

//        char[][] a = {{'W','W','B'},{'W','B','W'},{'B','W','W'}};
//        System.out.println(findLonelyPixel(a));

//        System.out.println(firstMissingPositive_xxx(new int[]{-1, 2, 4, 1}));
//
//        int[][] intervals = {{7,10},{2,4}};
//        System.out.println(minMeetingRooms(intervals));

//        int[][] intervals = {{-5,-4},{-3,-2},{1,2},{3,5},{8,9}};
//        int[] toBeRemoved = {-1,4};
//        System.out.println(removeInterval(intervals, toBeRemoved));

//         TreeNode treeNode = new TreeNode(1,null,new TreeNode(3,new TreeNode(2),new TreeNode(4,null,new TreeNode(5))));
//        TreeNode treeNode = new TreeNode(2,null,new TreeNode(3,new TreeNode(2,new TreeNode(1),null),null));
//        System.out.println(hot100.longestConsecutive(treeNode));

//        String s = "ID";
//        System.out.println(JSON.toJSONString(findPermutation_stack(s)));

//        TreeNode treeNode = new TreeNode(3,new TreeNode(9,new TreeNode(4),new TreeNode(0)),new TreeNode(8,new TreeNode(1),new TreeNode(7)));
//        System.out.println(hot100.verticalOrder(treeNode));

        System.out.println(hot100.parseTernary("T ? T ? F : 5 : 3"));

    }

    /**
     * 531.给你一个大小为 m x n 的图像 picture ，图像由黑白像素组成，'B' 表示黑色像素，'W' 表示白色像素，请你统计并返回图像中黑色孤独像素的数量
     * 黑色孤独像素的定义为：如果黑色像素 'B' 所在的同一行和同一列不存在其他黑色像素，那么这个黑色像素就是黑色孤独像素
     * <p>
     * 输入：picture = [["W","W","B"],["W","B","W"],["B","W","W"]] ==》3
     * 输入：picture = [["B","B","B"],["B","B","W"],["B","B","B"]] ==》0
     *
     * @param picture
     * @return
     */
    public static int findLonelyPixel(char[][] picture) {
        // 时间复杂度：O(M*N)
        int m = picture.length, n = picture[0].length;
        int[] row = new int[m];
        int[] col = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (picture[i][j] == 'B') {
                    row[i]++;
                    col[j]++;
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < m; i++) {
            if (row[i] == 1) {
                for (int j = 0; j < n; j++) {
                    if (col[j] == 1) {
                        ans++;
                    }
                }
            }
        }
        return ans;
    }

    /**
     * 1055. 对于任何字符串，我们可以通过删除其中一些字符（也可能不删除）来构造该字符串的子序列 (例如，“ace” 是 “abcde” 的子序列，而 “aec” 不是)
     * 给定源字符串 source 和目标字符串 target，返回 源字符串 source 中能通过串联形成目标字符串 target 的 子序列 的最小数量 ，
     * 如果无法通过串联源字符串中的子序列来构造目标字符串，则返回 -1
     *
     * <p>
     * source = "abc", target = "abcbc" ==> 2 目标字符串 "abcbc" 可以由 "abc" 和 "bc" 形成，它们都是源字符串 "abc" 的子序列
     * source = "abc", target = "acdbc" ==> -1 由于目标字符串中包含字符 "d"，所以无法由源字符串的子序列构建目标字符串
     * source = "xyz", target = "xzyxz" ==> 3  "xz" + "y" + "xz"。
     *
     * @param source
     * @param target
     * @return
     */

    public int xxx_shortestWay(String source, String target) {
        // 标记源的所有字符的布尔数组
        boolean[] sourceChars = new boolean[26];
        for (char c : source.toCharArray()) {
            sourceChars[c - 'a'] = true;
        }
        for (char c : target.toCharArray()) {
            if (!sourceChars[c - 'a']) {
                return -1;
            }
        }
        int m = source.length(), sourceIterator = 0, count = 0;
        for (char c : target.toCharArray()) {
            // 每次回到source原点，说明一次寻找子序列过程结束，下次count开始加1
            if (sourceIterator == 0) {
                count++;
            }

            // 一直在source中找到那个匹配target的元素
            while (source.charAt(sourceIterator) != c) {
                sourceIterator = (sourceIterator + 1) % m;
                // 一圈下来，必然会有几个字符匹配上目标字符串
                if (sourceIterator == 0) {
                    count++;
                }
            }
            sourceIterator = (sourceIterator + 1) % m;
        }
        return count;
    }

    /**
     * 439.三元表达式解析器，给定一个表示任意嵌套三元表达式的字符串 expression ，求值并返回其结果，你可以总是假设给定的表达式是有效的，并且只包含数字 '?'、':'、'T' 和 'F' ，其中'T'为真'F'为假
     * 表达式中的所有数字都是一位数(即在 [0,9] 范围内)，条件表达式从右到左分组(大多数语言中都是这样)，表达式的结果总是为数字 'T' 或 'F'
     * <p>
     * expression = "T ? 2 : 3" ==》"2"
     * expression = "F ? 1 : T ? 4 : 5" ==》"4"   "(F ? 1 : (T ? 4 : 5))" --> "(F ? 1 : 4)" --> "4"
     * expression = "T ? T ? F ： 5 : 3" ==》"F"   "T ? （T ? F ： 5 ）: 3" --> "F"
     * @param expression
     * @return
     */
    public String parseTernary(String expression) {
        // 关键是遇到"?"的最后一个，才能开始 ？？
        // 堆栈的艺术，搞不定
        // 遇到 : 往前找 ?
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == ' ') {
                continue;
            }
            Character put = expression.charAt(i);
            if (!stack.isEmpty() && stack.peek() == ':') {
                // 找 express 和 left
                stack.pop();
                Character left = stack.pop();
                stack.pop();
                Character express = stack.pop();
                if (express == 'T') {
                    put = left;
                    if(stack.isEmpty()){
                        // 说明没有 ？ 则直接返回
                        return put.toString();
                    }
                }
            }
            stack.push(put);
        }
        return stack.pop().toString();
    }


    /**
     * 549 二叉树最长连续序列II，给定二叉树的根 root ，返回树中最长连续路径的长度，连续路径是路径中相邻节点的值相差 1 的路径，此路径可以是增加或减少，另一方面，路径可以是子-父-子顺序，不一定是父子顺序
     *
     * @param root
     * @return
     */
    public int longestConsecutiveII(TreeNode root) {

        return 0;
    }


    /**
     * 314,二叉树的垂直遍历，给你一个二叉树的根结点，返回其结点按垂直方向（从上到下，逐列）遍历的结果，如果两个结点在同一行和列，那么顺序则为从左到右
     *
     * 输入：root = [3,9,20,null,null,15,7]
     * 输出：[[9],[3,15],[20],[7]]
     *
     * 输入：root = [3,9,8,4,0,1,7]
     * 输出：[[4],[9],[3,0,1],[8],[7]]
     *
     * 输入：root = [3,9,8,4,0,1,7,null,null,null,2,5]
     * 输出：[[4],[9,5],[3,0,1],[8,2],[7]]
     *
     * @param root
     * @return
     */

    Map<Integer, List<Integer>> verticalOrderMap = new TreeMap<>();
    public List<List<Integer>> verticalOrder(TreeNode root) {
        // 通过根节点左减右加来分组
        verticalOrderMap.computeIfAbsent(0,v -> new ArrayList<>()).add(root.val);
        verticalOrderDFS(0,root);
        return new ArrayList<>(verticalOrderMap.values());
    }

    private void verticalOrderDFS(int value, TreeNode root) {
        if (root == null) {
            return;
        }
        // 深度遍历会有层级的问题
        if (root.left != null) {
            verticalOrderMap.computeIfAbsent(value - 1,v -> new ArrayList<>()).add(root.left.val);
            verticalOrderDFS(value - 1, root.left);
        }

        if (root.right != null) {
            verticalOrderMap.computeIfAbsent(value + 1, v -> new ArrayList<>()).add(root.right.val);
            verticalOrderDFS(value + 1, root.right);
        }
    }


//    private List<List<Integer>> verticalOrderBFS(TreeNode root) {
//        List<List<Integer>> output = new ArrayList();
//        if (root == null) {
//            return output;
//        }
//        Map<Integer, ArrayList> columnTable = new HashMap();
//        Queue<Pair<TreeNode, Integer>> queue = new ArrayDeque();
//        int column = 0;
//        queue.offer(new Pair(root, column));
//        while (!queue.isEmpty()) {
//            Pair<TreeNode, Integer> p = queue.poll();
//            root = p.getKey();
//            column = p.getValue();
//
//            if (root != null) {
//                if (!columnTable.containsKey(column)) {
//                    columnTable.put(column, new ArrayList<Integer>());
//                }
//                columnTable.get(column).add(root.val);
//
//                queue.offer(new Pair(root.left, column - 1));
//                queue.offer(new Pair(root.right, column + 1));
//            }
//        }
//        List<Integer> sortedKeys = new ArrayList<>(columnTable.keySet());
//        Collections.sort(sortedKeys);
//        for(int k : sortedKeys) {
//            output.add(columnTable.get(k));
//        }
//
//        return output;
//    }
    /**
     * 484.寻找排列：由范围 [1,n] 内所有整数组成的 n 个整数的排列 perm 可以表示为长度为 n - 1 的字符串 s ，其中:
     * 如果 perm[i] < perm[i + 1] ，那么 s[i] == 'I'，如果 perm[i] > perm[i + 1] ，那么 s[i] == 'D'，给定一个字符串 s ，重构字典序上最小的排列 perm 并返回它
     *
     * 输入： s = "I"
     * 输出： [1,2]
     * 解释： [1,2] 是唯一合法的可以生成秘密签名 "I" 的特定串，数字 1 和 2 构成递增关系
     *
     * 输入： s = "DIIIDDDDI"
     * 输出： [2,1,3,4,5,9,8,7,6,10]
     *
     * @param s
     * @return
     */
    public static int[] xxx_findPermutation(String s) {
        // 有意思的题目，好难，没有思路..
        int[] res = new int[s.length() + 1];
        Stack<Integer> stack = new Stack<>();
        int j = 0;
        for (int i = 1; i <= s.length(); i++) {
            if (s.charAt(i - 1) == 'I') {
                stack.push(i);
                while (!stack.isEmpty()) {
                    res[j++] = stack.pop();
                }
            } else {
                stack.push(i);
            }
        }
        stack.push(s.length() + 1);
        while (!stack.isEmpty()) {
            res[j++] = stack.pop();
        }
        return res;
    }

    private static int[] findPermutation_stack(String s) {
        int[] res = new int[s.length() + 1];
        int point = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 1; i <= s.length(); i++) {
            // 如果前一个是 D 则说明这个要下降
            if (s.charAt(i - 1) == 'D') {
                stack.push(i);
            } else {
                // 如果前一个是 I 则先入栈，再出栈
                stack.push(i);
                while (!stack.isEmpty()) {
                    res[point++] = stack.pop();
                }
            }
        }
        // 不管是多少，都把最后一个PUSH进去
        stack.push(s.length() + 1);
        while (!stack.isEmpty()) {
            // 最后可能 D 结尾
            res[point++] = stack.pop();
        }
        return res;
    }

    private int[] findPermutation_reverse(String s){
        // 反转数组（反转两个I之间的D）
        return new int[]{};
    }


    /**
     * 298. 二叉树最长连续序列，给你一棵指定的二叉树的根节点 root ，请你计算其中最长连续序列路径的长度，
     * 最长连续序列路径 是依次递增 1 的路径，该路径，可以是从某个初始节点到树中任意节点，通过「父 - 子」关系连接而产生的任意路径，且必须从父节点到子节点，反过来是不可以的
     * <p>
     * 输入：root = [1,null,3,2,4,null,null,null,5]
     * 输出：3
     * 解释：当中，最长连续序列是 3-4-5 ，所以返回结果为 3 。
     * <p>
     * 输入：root = [2,null,3,2,null,1]
     * 输出：2
     * 解释：当中，最长连续序列是 2-3 。注意，不是 3-2-1，所以返回 2
     *
     * @param root
     * @return
     */
    int longestConsecutive_left = 1;
    int longestConsecutive_right = 1;

    public int longestConsecutive(TreeNode root) {
        // 二叉树，而非搜索二叉树
        longestConsecutiveDFS(root, 1);
        return Math.max(longestConsecutive_left, longestConsecutive_right);
    }

    public void longestConsecutiveDFS(TreeNode root, int count) {
        if (root.right == null && root.left == null) {
            return;
        }
        if (root.left != null) {
            if (root.left.val - root.val == 1) {
                longestConsecutive_left = Math.max(longestConsecutive_left, count + 1);
                longestConsecutiveDFS(root.left, count + 1);
            } else {
                longestConsecutiveDFS(root.left, 1);
            }
        }
        if (root.right != null) {
            if (root.right.val - root.val == 1) {
                longestConsecutive_right = Math.max(longestConsecutive_right, count + 1);
                longestConsecutiveDFS(root.right, count + 1);
            } else {
                longestConsecutiveDFS(root.right, 1);
            }
        }
    }

    /**
     * 1272. 删除区间
     * <p>
     * 返回一组实数，该实数表示intervals 中删除了 toBeRemoved 的部分 ，换句话说返回实数集合，并满足集合中的每个实数 x 都在 intervals 中，但不在 toBeRemoved 中，你的答案应该是一个如上所述的有序的不相连的间隔列表
     * 输入：intervals = [[0,2],[3,4],[5,7]], toBeRemoved = [1,6]
     * 输出：[[0,1],[6,7]]
     * <p>
     * 输入：intervals = [[0,5]], toBeRemoved = [2,3]
     * 输出：[[0,2],[3,5]]
     * <p>
     * 输入：intervals = [[-5,-4],[-3,-2],[1,2],[3,5],[8,9]], toBeRemoved = [-1,4]
     * 输出：[[-5,-4],[-3,-2],[4,5],[8,9]]
     *
     * @param intervals
     * @param toBeRemoved
     * @return
     */
    public List<List<Integer>> removeInterval(int[][] intervals, int[] toBeRemoved) {
        List<List<Integer>> result = new ArrayList<>();
        for (int[] interval : intervals) {
            // 如果没有重叠，则按原样将间隔添加到列表中。
            if (interval[0] > toBeRemoved[1] || interval[1] < toBeRemoved[0]) {
                result.add(Arrays.asList(interval[0], interval[1]));
            } else {
                // 需要保留左区间吗？
                if (interval[0] < toBeRemoved[0]) {
                    result.add(Arrays.asList(interval[0], toBeRemoved[0]));
                }
                // 需要保留右区间吗？
                if (interval[1] > toBeRemoved[1]) {
                    result.add(Arrays.asList(toBeRemoved[1], interval[1]));
                }
            }
        }
        return result;
    }

//    public static List<List<Integer>> removeInterval(int[][] intervals, int[] toBeRemoved) {
//        // 排序后，删除即可？
//        // TODO [[0,100]] + [0,50] -> 没有解决边界问题
//        Arrays.sort(intervals, Comparator.comparingInt(v -> v[0]));
//        List<List<Integer>> res = new ArrayList<>(intervals.length);
//        int left = toBeRemoved[0];
//        int right = toBeRemoved[1];
//        for (int i = 0; i < intervals.length; i++) {
//            int start = intervals[i][0];
//            int end = intervals[i][1];
//            // 都在删除区间
//            if (start >= left && end <= right) {
//                continue;
//            }
//            // 删除区间在里
//            if (start <= left && end > right) {
//                res.add(Arrays.asList(start, left));
//                res.add(Arrays.asList(right, end));
//                continue;
//            }
//            // 左在，右不在
//            if (start > left && start < right && end > right) {
//                start = right;
//            }
//            // 左不在，右在
//            if (start < left && end > left && end < right) {
//                end = left;
//            }
//            // 都不在
//            res.add(Arrays.asList(start, end));
//        }
//        return res;
//    }





    public boolean match(String S, int i, int j, String T) {
        for (int k = i; k < j; ++k) {
            if (k >= S.length() || S.charAt(k) != T.charAt(k - i)) {
                return false;
            }
        }
        return true;
    }


    /**
     * 253. 给你一个会议时间安排的数组 intervals ，每个会议时间都会包括开始和结束的时间 intervals[i] = [starti, endi] ，返回所需会议室的最小数量
     * <p>
     * [[0,30],[5,10],[15,20]] ==》2
     * [[7,10],[2,4]] ==> 1
     *
     * @param intervals
     * @return
     */
    public static int minMeetingRooms(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(v -> v[0]));
        // 维护一个小顶堆，如果新的开始时间<老的结束时间，则说明需要多个会议室,否则更新最大的结束时间
        PriorityQueue<Integer> priorityQueue = new PriorityQueue();
        int res = 0;
        int len = intervals.length;
        for (int i = 0; i < len; i++) {
            int start = intervals[i][0];
            int end = intervals[i][1];
            // start开始时，里多个会议进行，新会议的开始时间 > 老会议结束时间，则移除老会议，代表这些会议可以结束了
            while (!priorityQueue.isEmpty() && start >= priorityQueue.peek()) {
                priorityQueue.poll();
            }
            priorityQueue.offer(end);
            res = Math.max(priorityQueue.size(),res);
        }
        return res;
    }

    /**
     * 311. 给定两个稀疏矩阵 ：大小为 m x k 的稀疏矩阵 mat1 和 大小为 k x n 的稀疏矩阵 mat2 ，返回 mat1 x mat2 的结果，你可以假设乘法总是可能的
     * <p>
     * 输入：mat1 = [[1,0,0],[-1,0,3]], mat2 = [[7,0,0],[0,0,0],[0,0,1]] ==》[[7,0,0],[-7,0,3]]
     *
     * @param mat1
     * @param mat2
     * @return
     */
    public int[][] multiply(int[][] mat1, int[][] mat2) {
        int r1 = mat1.length, r2 = mat2.length;
        int c1 = mat1[0].length, c2 = mat2[0].length;
        int[][] res = new int[r1][r2];
        for (int i = 0; i < r1; i++) {
            for (int j = 0; j < c2; j++) {
                for (int k = 0; k < c1; k++) {
                    res[i][j] += (mat1[i][k] * mat2[k][j]);
                }
            }
        }
        return res;
    }


    /**
     * 238. 给你一个整数数组 nums，返回数组 answer ，其中 answer[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积，不要使用除法，且在 O(n) 时间复杂度内完成此题
     * <p>
     * nums = [1,2,3,4] ==》 [24,12,8,6]
     * nums = [-1,1,0,-3,3] ==》[0,0,9,0,0]
     *
     * @param nums 2024-06-26
     * @return
     */
    public int[] productExceptSelf(int[] nums) {
        int length = nums.length;
        // L 和 R 分别表示左右两侧的乘积列表（前缀积）
        int[] L = new int[length];
        int[] R = new int[length];

        // L[i] 为索引 i 左侧所有元素的乘积，对于索引为 '0' 的元素，因为左侧没有元素，所以 L[0] = 1
        L[0] = 1;
        for (int i = 1; i < length; i++) {
            L[i] = nums[i - 1] * L[i - 1];
        }
        // R[i] 为索引 i 右侧所有元素的乘积
        R[length - 1] = 1;
        for (int i = length - 2; i >= 0; i--) {
            R[i] = nums[i + 1] * R[i + 1];
        }
        // 对于索引 i，除 nums[i] 之外其余各元素的乘积就是左侧所有元素的乘积乘以右侧所有元素的乘积
        int[] answer = new int[length];
        for (int i = 0; i < length; i++) {
            answer[i] = L[i] * R[i];
        }
        return answer;
    }




    /**
     * 41.给你一个未排序的整数数组 nums ，找出其中没有出现的最小正整数，实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案
     * <p>
     * [2,4,0,5] ==> 1
     * [-1, 2, 4, 1] ==> 4
     *
     * @param nums
     * @return
     */
    public static int xxx_firstMissingPositive(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            if (nums[i] <= 0) {
                nums[i] = n + 1;
            }
        }
        for (int i = 0; i < n; ++i) {
            int num = Math.abs(nums[i]);
            if (num <= n) {
                nums[num - 1] = -Math.abs(nums[num - 1]);
            }
        }
        for (int i = 0; i < n; ++i) {
            // [-5,-2,4,-1]
            if (nums[i] > 0) {
                return i + 1;
            }
        }
        return n + 1;
    }

    /**
     * 249. 对字符串进行 “移位” 的操作,移动 "abc" 来形成序列：... <-> "abc" <-> "bcd" <-> ... <-> "xyz" <-> "yza" <-> ... <-> "zab" <-> "abc" <-> ...
     * 给定一个字符串数组 strings，将属于相同移位序列的所有 strings[i] 进行分组。你可以以 任意顺序 返回答案
     * <p>
     * ["abc","bcd","acef","xyz","az","ba","a","z"] ==》[["acef"],["a","z"],["abc","bcd","xyz"],["az","ba"]]
     * ["a"] ==》 [["a"]]
     *
     * @param strings
     * @return
     */
    public List<List<String>> groupStrings(String[] strings) {
        Map<String, List<String>> groupMap = new HashMap<>();
        for (String string : strings) {
            // 核心：找到两个字符之间的距离即可，如1-2-4，那么所有此区间差的都是相同移位
            String groupStr = Arrays.toString(getGroup(string));
            List<String> groupList = groupMap.getOrDefault(groupStr, new ArrayList<>());
            groupList.add(string);
            groupMap.put(groupStr, groupList);
        }

        List<List<String>> groupStrings = new ArrayList<>();
        Set<String> keySet = groupMap.keySet();
        for (String key : keySet) {
            List<String> groupList = groupMap.get(key);
            groupStrings.add(groupList);
        }
        return groupStrings;
    }

    private int[] getGroup(String string) {
        int length = string.length() - 1;
        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            int difference = string.charAt(i + 1) - string.charAt(i);
            if (difference < 0) {
                difference += 26;
            }
            array[i] = difference;
        }
        return array;
    }


    /**
     * 1198.给你一个 m x n 的矩阵 mat，其中每一行的元素均符合严格递增 ，请返回所有行中的最小公共元素，如果矩阵中没有这样的公共元素，就请返回 -1
     * <p>
     * 输入：mat = [[1,2,3,4,5],[2,4,5,8,10],[3,5,7,9,11],[1,3,5,7,9]] ==》5
     * 输入：mat = [[1,2,3],[2,3,4],[2,3,5]] ==》  2
     *
     * @param mat
     * @return
     */
    public int smallestCommonElement(int[][] mat) {
        int n = mat.length, m = mat[0].length;
        for (int j = 0; j < m; ++j) {
            boolean found = true;
            // 轮询比较，时间复杂度 O(MN)
            for (int i = 1; i < n && found; ++i) {
                found = Arrays.binarySearch(mat[i], mat[0][j]) >= 0;
            }
            if (found) {
                return mat[0][j];
            }
        }
        return -1;
    }


    public int smallestCommonElement_v2(int[][] mat) {
        int n = mat.length, m = mat[0].length;
        int pos[] = new int[n], cur_max = 0, cnt = 0;
        while (true) {
            for (int i = 0; i < n; ++i) {
                while (pos[i] < m && mat[i][pos[i]] < cur_max) {
                    ++pos[i];
                }
                if (pos[i] >= m) {
                    return -1;
                }
                if (mat[i][pos[i]] != cur_max) {
                    cnt = 1;
                    cur_max = mat[i][pos[i]];
                } else if (++cnt == n) {
                    return cur_max;
                }
            }
        }
    }

    /**
     * 189.给定一个整数数组 nums，将数组中的元素向右轮转 k 个位置，其中 k 是非负数
     * <p>
     * 输入: nums = [1,2,3,4,5,6,7], k = 3 ==> [5,6,7,1,2,3,4]
     * 输入：nums = [-1,-100,3,99], k = 2 ==>[3,99,-1,-100]
     *
     * @param nums
     * @param k
     */
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        int[] newArr = new int[n];
        for (int i = 0; i < n; ++i) {
            // 好注意：通过[i + k]全部后移几位
            newArr[(i + k) % n] = nums[i];
        }
        System.arraycopy(newArr, 0, nums, 0, n);
    }

    public void rotate_v2(int[] nums, int k) {
        k %= nums.length;
        // 先全部反转，再左右两段反转
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start += 1;
            end -= 1;
        }
    }

    /**
     * 487. 给定一个二进制数组 nums ，如果最多可以翻转一个 0 ，则返回数组中连续 1 的最大个数
     * <p>
     * 输入：nums = [1,0,1,1,0] ==》4 翻转第一个 0 可以得到最长的连续 1
     * 输入：nums = [1,0,1,1,0,1] ==》4
     *
     * @param nums
     * @return
     */
//    public int findMaxConsecutiveOnes(int[] nums) {
        // [1,1,0,1]
//        if (nums.length <= 1) {
//            return nums.length;
//        }
//        int pre = -1, max = 0;
//        for (int i = 0; i < nums.length; i++) {
//            if (nums[i] == 0) {
//                if (pre >= 0) {
//                    max = Math.max(max, i - pre);
//                    pre = i;
//                } else {
//                    max = i + 1;
//                    pre = 0;
//                }
//            }
//        }
//        return max == 0 ? nums.length : max;
//    }

    public int xx_findMaxConsecutiveOnes(int[] nums) {
        int l = 0, r = 0;
        int ans = 0, n = nums.length;
        boolean flag = true;
        int t = 0;
        while (r < n) {
            // 核心在于当 0 出现的时候，如果第一次，不更新左侧l
            if (nums[r] == 0) {
                if (flag) {
                    flag = false;
                } else {
                    ans = Math.max(ans, r - l);
                    l = t + 1;
                }
                t = r;
            }
            r++;
        }
        ans = Math.max(ans, r - l);
        return ans;
    }


    /**
     * 1100.给你一个字符串 S，找出所有长度为 K 且不含重复字符的子串，请你返回全部满足要求的子串的 数目
     * <p>
     * S = "havefunonleetcode", K = 5 ==> 6 分别是：'havef','avefu','vefun','efuno','etcod','tcode'
     * S = "home", K = 5 ==>  0
     *
     * @param s
     * @param k
     * @return
     */
    public static int numKLenSubstrNoRepeats(String s, int k) {
        if (s.length() < k) {
            return 0;
        }
        int[] window = new int[128];
        int left = 0, right = 0, sum = 0;
        while (right < s.length()) {
            // 新入的导致重复，left左移直到重复数据清除（煞笔了..先++）
            if (++window[s.charAt(right)] > 1) {
                while (window[s.charAt(right)] > 1) {
                    window[s.charAt(left++)]--;
                }
            }
            // 新入导致超长，left++
            if (right - left + 1 == k) {
                window[s.charAt(left++)]--;
                sum++;
            }

            right++;
        }
        return sum;
    }

    /**
     * 239.给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧，你只可以看到在滑动窗口内的 k 个数字，滑动窗口每次只向右移动一位,返回滑动窗口中的最大值
     *
     * 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3 ==》[3,3,5,5,6,7]
     * 输入：nums = [1], k = 1
     * 输出：[1]
     *
     *
     * @param nums
     * @param k
     * @return
     */
    public static int[] xxx_maxSlidingWindow(int[] nums, int k) {
        // 重点在于，维护递减序列，无论是 PriorityQueue 还是 Deque 都行
        int n = nums.length;
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < k; ++i) {
            // Last -> First
            while (!deque.isEmpty() && deque.peekLast() < nums[i]) {
                deque.pollLast();
            }
            deque.offerLast(i);
        }
        int[] ans = new int[n - k + 1];
        ans[0] = nums[deque.peekFirst()];
        for (int i = k; i < nums.length; i++) {
            // Last -> First
            while (!deque.isEmpty() && deque.peekLast() < nums[i]) {
                deque.pollLast();
            }
            deque.offerLast(i);
            // remove Last IMPORTMENT
            while (deque.peekLast() <= i - k) {
                deque.pollLast();
            }
            ans[i - k + 1] = nums[deque.peekFirst()];
        }
        return ans;
    }


    /**
     * 76.给你一个字符串 s 、一个字符串 t，返回 s 中涵盖 t 所有字符的最小子串，如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 ""
     * <p>
     * s = "ADOBECODEBANC", t = "ABC" ==> "BANC"
     * s = "a", t = "aa" ==> ""
     *
     * @param s
     * @param t
     * @return
     */

    Map<Character, Integer> ori = new HashMap<>();
    Map<Character, Integer> cnt = new HashMap<>();

    public String xxx_minWindow(String s, String t) {
        int tLen = t.length();
        for (int i = 0; i < tLen; i++) {
            char c = t.charAt(i);
            ori.put(c, ori.getOrDefault(c, 0) + 1);
        }
        int l = 0, r = -1;
        int len = Integer.MAX_VALUE, ansL = -1, ansR = -1;
        int sLen = s.length();
        while (r < sLen) {
            ++r;
            if (r < sLen && ori.containsKey(s.charAt(r))) {
                cnt.put(s.charAt(r), cnt.getOrDefault(s.charAt(r), 0) + 1);
            }
            // 满足涵盖条件，则更新长度len，并把L继续前进
            while (check() && l <= r) {
                // 更新涵盖的最小字串
                if (r - l + 1 < len) {
                    len = r - l + 1;
                    ansL = l;
                    ansR = l + len;
                }
                if (ori.containsKey(s.charAt(l))) {
                    cnt.put(s.charAt(l), cnt.getOrDefault(s.charAt(l), 0) - 1);
                }
                ++l;
            }
        }
        return ansL == -1 ? "" : s.substring(ansL, ansR);
    }

    private boolean check() {
        // 每次都要校验窗口内的字符，和需要的字符之间的数量？？？ -- 时间复杂度过高
        Iterator<Map.Entry<Character, Integer>> iter = ori.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = iter.next();
            Character key = (Character) entry.getKey();
            Integer val = (Integer) entry.getValue();
            if (cnt.getOrDefault(key, 0) < val) {
                return false;
            }
        }
        return true;
    }

//    public String minWindow(String s, String t) {
//        if (s.length() < t.length()) {
//            return "";
//        }
//        String res = s;
//        Map<Character, Integer> map = new HashMap<>(t.length());
//        int left = 0, right = 0, count = 0;
//        while (right < s.length()) {
//            char c = s.charAt(right);
//            if (t.contains(String.valueOf(c))) {
//                int num = map.getOrDefault(c, 0) + 1;
//                if (num == 1) {
//                    count++;
//                }
//            }
//            if(count == t.length()){
//                res = res.length() > (right - left + 1) ? s.substring(left, right) : res;
//                // 达成一个，寻找下一个
//                while (!t.contains(String.valueOf(s.charAt(left)))){
//
//                }
//            }
//            right++;
//        }
//
//        return res;
//    }

    /**
     * 159. 给你一个字符串 s ，请你找出 至多 包含 两个不同字符 的最长子串，并返回该子串的长度
     * <p>
     * 输入：s = "eceba" ==》3 "ece"
     * 输入：s = "ccaabbb" ==> 5  "aabbb"
     *
     * @param s 2024.06.21
     * @return
     */
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if (s.length() < 2) {
            return s.length();
        }
        // 滑动窗口，统计窗口内的数量
        int[] window = new int[128];
        int left = 0, right = 0, count = 0, len = s.length();
        while (right < len) {
            if (++window[s.charAt(right++)] == 1) {
                count++;
            }
            if (count > 2 && (--window[s.charAt(left++)] == 0)) {
                count--;
            }
        }
        return right - left;
    }


    /**
     * 560. 给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数，子数组是数组中元素的连续非空序列
     * <p>
     * nums = [1,1,1], k = 2 ==> 2
     * nums = [1,2,3], k = 3 ==> 2
     *
     * @param nums 2024.06.21
     * @param k
     * @return
     */
    public int xxx_subarraySum(int[] nums, int k) {
        int count = 0, pre = 0;
        // Map<前缀和，该值的次数>
        HashMap<Integer, Integer> mp = new HashMap<>();
        mp.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            pre += nums[i];
            // 说明这段区间为k，即 m + k = pre ==》m = pre - k 翻译过来就是某个点只要是等于 pre-k 就都满足条件
            if (mp.containsKey(pre - k)) {
                count += mp.get(pre - k);
            }
            mp.put(pre, mp.getOrDefault(pre, 0) + 1);
        }
        return count;
    }


    /**
     * 438. 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引，不考虑答案输出的顺序。异位词 指由相同字母重排列形成的字符串（包括相同的字符串）
     *
     * 示例 1:
     *
     * 输入: s = "cbaebabacd", p = "abc"
     * 输出: [0,6]
     * 解释:
     * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
     * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
     *  示例 2:
     *
     * 输入: s = "abab", p = "ab"
     * 输出: [0,1,2]
     * 解释:
     * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
     * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
     * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
     *
     * @param s 2024.06.21
     * @param p
     * @return
     */
    public List<Integer> xxx_indAnagrams(String s, String p) {
        int sLen = s.length(), pLen = p.length();
        if (sLen < pLen) {
            return new ArrayList<>();
        }
        List<Integer> ans = new ArrayList<>();
        int[] sCount = new int[26];
        int[] pCount = new int[26];
        for (int i = 0; i < pLen; ++i) {
            ++sCount[s.charAt(i) - 'a'];
            ++pCount[p.charAt(i) - 'a'];
        }
        if (Arrays.equals(sCount, pCount)) {
            ans.add(0);
        }

        for (int i = 0; i < sLen - pLen; ++i) {
            // 维护一个长为 pLen 的一个滑动窗口，左进右出，保证两个窗口内容一样
            --sCount[s.charAt(i) - 'a'];
            ++sCount[s.charAt(i + pLen) - 'a'];
            // 直接通过数组对比
            if (Arrays.equals(sCount, pCount)) {
                ans.add(i + 1);
            }
        }
        return ans;
    }


    /**
     * 186.给你一个字符数组 s ，反转其中单词的顺序，单词的定义为：单词是一个由非空格字符组成的序列。s 中的单词将会由单个空格分隔，必须设计并实现原地解法来解决此问题，即不分配额外的空间
     *
     * 输入：s = ["t","h","e"," ","s","k","y"," ","i","s"," ","b","l","u","e"]
     * 输出：["b","l","u","e"," ","i","s"," ","s","k","y"," ","t","h","e"]
     *
     * 输入：s = ["a"]
     * 输出：["a"]
     *
     * @param s 2024.06.21
     */
    public void reverseWords(char[] s) {
        // 整个字符数组反转
        reverseCharacters(s);

        // 再反转每个单词
        reverseEachWord(s);
    }

    private void reverseCharacters(char[] s) {
        reverseCharacters(s, 0, s.length - 1);
    }

    private void reverseEachWord(char[] s) {
        int length = s.length;
        int begin = -1, end = -1;
        for (int i = 0; i < length; i++) {
            char c = s[i];
            if (c == ' ') {
                // 遇到空格，则反转之前的字符，并把start、end重置
                reverseCharacters(s, begin, end);
                begin = i + 1;
                end = i + 1;
            } else {
                if (begin < 0) {
                    begin = i;
                }
                end = i;
            }
        }
        reverseCharacters(s, begin, end);
    }

    private void reverseCharacters(char[] s, int low, int high) {
        while (low < high) {
            char c1 = s[low], c2 = s[high];
            s[low] = c2;
            s[high] = c1;
            low++;
            high--;
        }
    }



    /**
     * 161. 给定两个字符串 s 和 t ，如果它们的编辑距离为 1 ，则返回 true ，否则返回 false，字符串 s 和字符串 t 之间满足编辑距离等于 1 有三种可能的情形：
     *   往 s 中插入 恰好一个 字符得到 t
     *   从 s 中删除 恰好一个 字符得到 t
     *   在 s 中用 一个不同的字符 替换 恰好一个 字符得到 t
     *
     * s = "ab", t = "acb" ==》 true 可以将 'c' 插入字符串 s 来得到 t
     * s = "cab", t = "ad" ==》false
     *
     * @param s 2024.06.20
     * @param t
     * @return
     */
    public boolean isOneEditDistance(String s, String t) {
        int ns = s.length();
        int nt = t.length();

        // 确保 s 比 t 短
        if (ns > nt) {
            return isOneEditDistance(t, s);
        }

        // 如果长度差异大于1，则字符串不是一个编辑距离
        if (nt - ns > 1) {
            return false;
        }
        for (int i = 0; i < ns; i++) {
            if (s.charAt(i) != t.charAt(i)) {
                // 如果字符串具有相同的长度
                if (ns == nt) {
                    return s.substring(i + 1).equals(t.substring(i + 1));
                }
                // 如果字符串具有不同的长度
                else {
                    return s.substring(i).equals(t.substring(i + 1));
                }
            }
        }
        // 如果在 ns 距离上没有差异，则仅当 t 有多一个字符时，字符串才有一次编辑
        return (ns + 1 == nt);
    }

    /**
     * 3.给定一个字符串 s ，请你找出其中不含有重复字符的最长子串的长度
     * <p>
     * s = "abcabcbb" ==> 3 "abc"
     * s = "pwwkew" ==> 3 "wke"
     *
     * @param s 2024.06.20
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        if (s.length() <= 1) {
            return s.length();
        }
        // 滑动窗口，通过左右边界+int[]统计，确定最大范围
        int[] count = new int[128];
        int left = 0, right = 0, max = 0;
        while (right < s.length()) {
            if (++count[s.charAt(right)] > 1) {
                while (count[s.charAt(right)] > 1) {
                    count[s.charAt(left++)]--;
                }
            }
            right++;
            max = Math.max(right - left, max);
        }
        return max;
    }

    /**
     * 42.接雨水 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水
     * <p>
     * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1] ==> 输出：6
     * 输入：height = [4,2,0,3,2,5] ==> 输出：9
     *
     * @param height 2024.06.20
     * @return
     */
    public int trap(int[] height) {
        int len = height.length;
        // 动态规划dp[i]代表i柱之前的最大值
        int[] left = new int[len];
        left[0] = 0;
        for (int i = 1; i < len; i++) {
            left[i] = Math.max(left[i - 1], height[i - 1]);
        }

        int[] right = new int[len];
        right[len - 1] = 0;
        for (int j = len - 2; j > 0; j--) {
            right[j] = Math.max(right[j + 1], height[j + 1]);
        }

        int[] dp = new int[len];
        for (int k = 1; k < len - 1; k++) {
            dp[k] = dp[k - 1] + Math.max(0, Math.min(left[k], right[k]) - height[k]);
        }
        return dp[len - 2];
    }


    /**
     * 128.最长连续数组，给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度，请你设计并实现时间复杂度为 O(n) 的算法解决此问题
     *
     * nums = [100,4,200,1,3,2] ==> 4 [1, 2, 3, 4]
     * nums = [0,3,7,2,5,8,4,6,0,1] => 9
     *
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        // 时间复杂度要O(n)，放到list用空间复杂度换时间 TODO 超出时间限制
        if(nums.length ==0){
            return 0;
        }
        List<Integer> list = Arrays.stream(nums).boxed().collect(Collectors.toList());
        int max = 1;
        for (int i = 0; i < nums.length; i++) {
            int k = 1;
            while (list.contains(++nums[i])) {
                k++;
            }
            max = Math.max(k, max);
        }
        return max;
    }


    /**
     * 11.给定一个长度为 n 的整数数组 height , 有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) ，找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水,返回容器可以储存的最大水量
     * <p>
     * [1,8,6,2,5,4,8,3,7] ==> 49
     *
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int right = 0, left = height.length - 1;
        int max = 0;
        while (right < left) {
            // 谁小谁动
            int all = (left - right) * Math.min(height[right], height[left]);
            max = Math.max(max, all);
            if (height[right] > height[left]) {
                left++;
            } else {
                right++;
            }
        }
        return max;
    }


    /**
     * 15.三数之和，给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，同时还满足 nums[i] + nums[j] + nums[k] == 0
     * 返回所有和为0且不重复的三元组，注意：答案中不可以包含重复的三元组
     *
     * nums = [-1,0,1,2,-1,-4] ==》[[-1,-1,2],[-1,0,1]]
     *
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        for (int first = 0; first < nums.length; first++) {
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            int target = -nums[first];
            int third = nums.length -1;
            for (int second = first + 1; second < nums.length - 1; second++) {
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }
                while (second < third && nums[second] + nums[third] > target) {
                    --third;
                }
                if (second == third) {
                    break;
                }
                if (nums[second] + nums[third] == target) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[first]);
                    list.add(nums[second]);
                    list.add(nums[third]);
                    ans.add(list);
                }
            }
        }
        return ans;
    }

    /**
     * 624.给定 m 个数组，每个数组都已经按照升序排好序了。
     * 现在你需要从两个不同的数组中选择两个整数（每个数组选一个）并且计算它们的距离，两个整数 a 和 b 之间的距离定义为它们差的绝对值 |a-b| ，找到最大距离
     * <p>
     * [[1,2,3],[4,5],[1,2,3]] ==> 4
     *
     * @param arrays
     * @return
     */
    public int maxDistance(List<List<Integer>> arrays) {
        // 两个维度：最大值 + 其他数组的最小值，最小值 + 其他数组的最大值
        int res = 0;
        int min_val = arrays.get(0).get(0);
        int max_val = arrays.get(0).get(arrays.get(0).size() - 1);
        for (int i = 1; i < arrays.size(); i++) {
            int n = arrays.get(i).size();
            // 最大值就是 当前最大值-之前的最小值 或者 之前的最大值-当前最小值
            res = Math.max(res, Math.max(Math.abs(arrays.get(i).get(n - 1) - min_val), Math.abs(max_val - arrays.get(i).get(0))));
            // 然后再更新之前最大值与之前最小值
            min_val = Math.min(min_val, arrays.get(i).get(0));
            max_val = Math.max(max_val, arrays.get(i).get(n - 1));
        }
        return res;
    }


    /**
     * 280.摆动排序，给你一个的整数数组 nums, 将该数组重新排序后使 nums[0] <= nums[1] >= nums[2] <= nums[3]...
     * <p>
     * nums = [3,5,2,1,6,4] ==> [3,5,1,6,2,4]
     * nums = [6,6,5,6,3,8] ==> [6,6,5,6,3,8]
     *
     * @param nums
     */
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public void wiggleSort(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length - 1; i += 2) {
            swap(nums, i, i + 1);
        }
    }

    /**
     * 给定一个整数数组 nums 和 一个整数目标值 target，请你在该数组中找出和为目标值 target 的那两个整数，并返回它们的数组下标
     *
     * <p>
     * nums = [2,7,11,15], target = 9  ==>  [0,1]
     * nums = [3,2,4], target = 6  ==>  [1,2]
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        // 1、HashMap 的 containsKey
        Map<Integer, Integer> hashtable = new HashMap<>();
        for (int i = 0; i < nums.length; ++i) {
            if (hashtable.containsKey(target - nums[i])) {
                return new int[]{hashtable.get(target - nums[i]), i};
            }
            hashtable.put(nums[i], i);
        }

        // 2、排序后，左右指针判断
        return new int[0];
    }


    /**
     * 49. 给你一个字符串数组，请你将字母异位词相同的组合在一起，可以按任意顺序返回结果列表（字母异位词是由重新排列源单词的所有字母得到的一个新单词）
     * <p>
     * strs = ["eat", "tea", "tan", "ate", "nat", "bat"]  ==>  [["bat"],["nat","tan"],["ate","eat","tea"]]
     *
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>(strs.length);
        for (String s : strs) {
            char[] cs = s.toCharArray();
            // 排序字符
            Arrays.sort(cs);
            String strKey = String.valueOf(cs);
            if (!map.containsKey(strKey)) {
                map.put(strKey, new ArrayList<>());
            }
            map.get(strKey).add(s);
        }
        return new ArrayList<>(map.values());
    }


}
