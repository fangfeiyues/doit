package com.fang.doit.algo.lc.test;

import com.fang.doit.algo.dst.linked.ListNode;

import java.util.*;

/**
 * @author : fangfeiyue
 * @version V1.0
 * @Project: doit
 * @Package com.fang.doit.algo.lc.test
 * @Description: 2023.05月刷题记录
 * @date Date : 2023-05-07 4:21 下午
 */
public class LcTop202305 {

    /**
     *  ------------------- 每天写几道是保持代码手感最好的方式！！！ -----------------
     *  1、**** 每天2道保持手感
     *  2、**** 每行代码都要手写，理解思路&读懂代码
     *  3、**** 每天review T-1的代码
     *
     *  ------------------------------------------------------
     */


    /**
     * 92. 单链表的头指针head和两个整数left和right ，其中 left <= right 。反转从位置left到位置right的链表节点
     *
     * @param head
     * @param left
     * @param right
     * @return
     */
    public ListNode reverseBetween_92(ListNode head, int left, int right) {
        // 因为头节点有可能发生变化，使用虚拟头节点可以避免复杂的分类讨论
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode pre = dummyNode;
        // 第 1 步：从虚拟头节点走 left - 1 步，来到 left 节点的前一个节点
        // 建议写在 for 循环里，语义清晰
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }
        // 第 2 步：从 pre 再走 right - left + 1 步，来到画 right 节点
        ListNode rightNode = pre;
        for (int i = 0; i < right - left + 1; i++) {
            rightNode = rightNode.next;
        }
        // 第 3 步：切断出一个子链表（截取链表）
        ListNode leftNode = pre.next;
        ListNode curr = rightNode.next;
        // 注意：切断链接
        pre.next = null;
        rightNode.next = null;
        // 第 4 步：同第 206 题，反转链表的子区间
        reverseLinkedList(leftNode);
        // 第 5 步：接回到原来的链表中
        pre.next = rightNode;
        leftNode.next = curr;
        return dummyNode.next;
    }

    private void reverseLinkedList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
    }


    /**
     * 77. 组合 : 给定两个整数n和k，返回范围 [1, n] 中所有可能的k个数的组合
     *
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine_77(int n, int k) {
        // *** 核心在于用树型结构深度递归 ***
        //
        Deque<Integer> path = new ArrayDeque<>();
        List<List<Integer>> ans = new ArrayList<>();
        combineDFS(n, k, 1, path, ans);
        return ans;
    }
    private void combineDFS(int n, int k, int begin, Deque<Integer> path, List<List<Integer>> ans) {
        if (k == 0) {
            ans.add(new ArrayList<>(path));
            return;
        }
        for (int i = begin; i <= n; i++) {
            path.addLast(i);
            k = k - 1;
            combineDFS(n, k, i + 1, path, ans);
            path.removeLast();
            k = k + 1;
        }
    }


    /**
     * 75. 颜色分类， 0、 1 和 2 分别表示红色、白色和蓝色并按照红色、白色、蓝色顺序排列
     * 输入：nums = [2,0,2,1,1,0] ==> 输出：[0,0,1,1,2,2]
     *
     * @param nums
     */
    public void sortColors_20(int[] nums) {

    }

    /**
     * 64. 最小路径和：给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小
     * 例如：grid = [[1,3,1],[1,5,1],[4,2,1]] ==> 7
     *
     * @param grid
     * @return
     */

    public int minPathSum_64_ww(int[][] grid) {
        // 动态规划：dp核心在于根据当前的dp[][]值，结合各种情况推演规划出下一步的结果，最终求得最后值
        // 这里就很符合这个条件，当前这一步的值可以根据上一步结果推演：由于从左上角到右下角只能向下或者向右，则dp[i][j] = Math.min(dp[i-1,j],dp[i][j-1]) + grid[i][j]
        return minPathSum_dp(grid);
    }

    private int minPathSum_dp(int[][] grid) {
        int columns = grid[0].length;
        int row = grid.length;
        int[][] dp = new int[row][columns];
        dp[0][0] = grid[0][0];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < columns; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                if (i == 0) {
                    // row == 0的必定是最顶上横着移动的情况
                    dp[i][j] = dp[i][j - 1] + grid[i][j];
                }
                if (j == 0) {
                    dp[i][j] = dp[i - 1][j] + grid[i][j];
                } else {
                    // 每次过来要么是从上要么是从左，其实和背包问题有异曲同工之妙
                    dp[i][j] = Math.min(grid[i - 1][j], grid[i][j - 1]) + grid[i][j];
                }
            }
        }
        return grid[grid.length - 1][grid[0].length - 1];
    }

    int[][] pairs = new int[][]{{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
    int max = 0;

    private int minPathSum_fail(int[][] grid) {
        boolean[][] used = new boolean[grid.length][grid.length];
        // int[][] 转 map
        Map<Integer, List<Integer>> gridMap = new HashMap<>(grid.length);
        return 0;
    }

    private void minPathSumDFS(int[][] grid, boolean[][] used, int r, int c, int path) {
        path = path + grid[r][c];
        used[r][c] = true;
        for (int i = 0; i < pairs.length; i++) {

        }
    }

    /**
     * 56. 合并区间
     * 例如：intervals = [[1,3],[2,6],[8,10],[15,18]] ==> [[1,6],[8,10],[15,18]]
     *
     * @param intervals
     * @return
     */
    public int[][] merge_56(int[][] intervals) {
        // *** 死办法：左右区间大小比较合并 ***
        if (intervals.length == 0) {
            return new int[0][2];
        }
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        List<int[]> merged = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            int l = intervals[i][0], r = intervals[i][1];
            if (merged.size() == 0 || merged.get(merged.size() - 1)[1] < l) {
                merged.add(new int[]{l, r});
            } else {
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], r);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }

    /**
     * 53. 最大子数组和
     * 例如：nums = [-2,1,-3,4,-1,2,1,-5,4] ==》[4,-1,2,1] = 6
     *
     * @param nums
     * @return 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解
     */
    public int maxSubArray_17(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        // 贪心：：局部优解
//        maxSubArray4Greedy(nums);

        // 动态规划：dp[i]求解i位置的最大值 O(n)
        return maxSubArray4dp(nums);
    }
    private int maxSubArray4Greedy(int[] nums) {
        // 即当前元素之前的和小于0 则丢弃之前的元素。保证自己加上后不会结果比自己一个人更差(但不一定是最好)
        int preSum = 0;
        int max = nums[0];
        for (int i = 0; i < nums.length; i++) {
            if (preSum > 0) {
                preSum = preSum + nums[i];
            } else {
                preSum = nums[i];
            }
            max = Math.max(max, preSum);
        }
        return max;
    }
    private int maxSubArray4dp(int[] nums) {
        // 即dp[i]即代表在i位置的最大值
        int[] dp = new int[nums.length];
        int max = nums[0];
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && dp[i - 1] > 0) {
                dp[i] = dp[i - 1] + nums[i];
            } else {
                dp[i] = nums[i];
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    /**
     * 46. 全排列（经典的深度递归回溯算法）
     * 例如：nums = [1,2,3] ==> [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permute_46(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length == 0) {
            return res;
        }
        Deque<Integer> path = new ArrayDeque<>();
        boolean[] used = new boolean[nums.length];
        permuteDFS(nums, 0, used, path, res);
        return res;
    }
    private void permuteDFS(int[] nums, int depth, boolean[] used, Deque<Integer> path, List<List<Integer>> res) {
        if (depth == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        // 每个节点遍历一遍
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            // 防止出现[1,2,2,1] 两次121的情况
            used[i] = true;
            path.addLast(nums[i]);
            permuteDFS(nums, depth + 1, used, path, res);
            used[i] = false;
            path.removeLast();
        }
    }

    /**
     * 43. 字符串相乘
     * 输入: num1 = "123", num2 = "456" ==> 输出: "56088"  (注意：不能使用任何内置的 BigInteger 库或直接将输入转换为整数)
     *
     * @param num1
     * @param num2
     * @return
     */
    public String multiply_15(String num1, String num2) {

        return "";
    }

    /**
     * 39. 组合总和
     * candidates = [2,3,6,7], target = 7 ==> [[2,2,3],[7]]
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum_39(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (candidates.length == 0) {
            return res;
        }
        // *** 树型结构的深度递归回溯+减枝 ***
        List<List<Integer>> ans = new ArrayList<>();
        // 第1步：排序。减少减枝的复杂度
        Arrays.sort(candidates);
        Deque<Integer> path = new ArrayDeque<>();
        combinationDFS(candidates, 0, target, ans, path);
        return ans;
    }
    private void combinationDFS(int[] candidates, int begin, int target, List<List<Integer>> ans, Deque<Integer> path) {
        if (target == 0) {
            ans.add(new ArrayList<>(path));
            return;
        }
        // 第2步：从begin开始，遍历树枝的时候可以把前面遍历过的剪了
        for (int index = begin; index < candidates.length; index++) {
            if (target < 0) {
                break;
            }
            path.addLast(candidates[index]);
            combinationDFS(candidates, index, target - candidates[index], ans, path);
            // 第3步：开始循环的下一个节点前把前面的移除了
            path.removeLast();
        }
    }


    /**
     * 36. 有效的数独
     *
     * @param board
     * @return
     */
    public boolean isValidSudoku_13(char[][] board) {
        // 暴力遍历，时间复杂度O(1)；空间复杂度：O(n)
        return false;
    }

    /**
     * 在排序数组中查找元素的第一个和最后一个位置
     *
     * @param nums
     * @param target
     * @return 必须设计并实现时间复杂度为 O(log n) 的算法解决此问题
     */
    private int[] searchRange_12(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (target == nums[mid]) {
                int start = mid;
                for (int i = mid - 1; i >= 0; i--) {
                    if (nums[i] == target) {
                        start--;
                    }
                }
                int end = mid;
                for (int j = mid + 1; j < nums.length; j++) {
                    if (nums[j] == target) {
                        end++;
                    }
                }
                return new int[]{start, end};
            }
            if (nums[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return new int[]{-1, -1};
    }

    /**
     * 33.搜索旋转排序数组
     * 如 [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，
     * 则返回它的下标，否则返回 -1
     *
     * @param nums
     * @param target
     * @return 必须设计一个时间复杂度为 O(log n) 的算法解决此问题
     */
    public int search_33(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) {
            return -1;
        }
        if (n == 1) {
            return nums[0] == target ? 0 : -1;
        }
        // ** O(log n)想到二分法 **
        int l = 0, r = n - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            // 第1步：一切为二后任意一个节点是一定会有一侧是顺序的，也就是看nums[0]到nums[mid]是否顺序即可
            if (nums[0] <= nums[mid]) {
                if (nums[0] <= target && target < nums[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[n - 1]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return -1;
    }

    /**
     * 31. 下一个排列：指其整数的下一个字典序更大的排列
     * 例如，arr = [1,2,3] 的下一个排列是 [1,3,2] 。 类似地，arr = [2,3,1] 的下一个排列是 [3,1,2]
     *
     * @param nums
     */
    public void nextPermutation_31_gg(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        if (i >= 0) {
            int j = nums.length - 1;
            while (j >= 0 && nums[i] >= nums[j]) {
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i + 1);
    }
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    private void reverse(int[] nums, int start) {
        int left = start, right = nums.length - 1;
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }


    /**
     * 24.两两交换链表中的节点
     * 给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）
     * 输入：head = [1,2,3,4] ==> 输出：[2,1,4,3]
     *
     * @param head
     * @return
     */
    public ListNode swapPairs_24(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = head.next;
        // 第1步：构造虚拟结点。涉及节点交换移除的都可以考虑
        ListNode dummy = new ListNode(0, head);
        // 第2步：dummy.next和next.next交换
        ListNode first;
        ListNode second;
        while ((first = dummy.next) != null && (second = dummy.next.next) != null) {
            dummy.next = second;
            ListNode temp = second.next;
            second.next = first;
            first.next = temp;
            dummy = first;
        }
        return newHead;
    }


    /**
     * 22.括号生成：数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合
     * 输入：n = 3 ==> 输出：["((()))","(()())","(())()","()(())","()()()"]
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesis_22_wait(int n) {
        List<String> combinations = new ArrayList<>();
        // 暴力求解法
        generateAll(new char[2 * n], 0, combinations);

        // 回溯法
        backtrack(combinations, new StringBuilder(), 0, 0, n);

        // 动态规划
        return combinations;
    }
    private void generateAll(char[] current, int pos, List<String> result) {
        if (pos == current.length) {
            if (valid(current)) {
                result.add(new String(current));
            }
        } else {
            current[pos] = '(';
            generateAll(current, pos + 1, result);
            current[pos] = ')';
            generateAll(current, pos + 1, result);
        }
    }
    private boolean valid(char[] current) {
        int balance = 0;
        for (char c : current) {
            if (c == '(') {
                ++balance;
            } else {
                --balance;
            }
            if (balance < 0) {
                return false;
            }
        }
        return balance == 0;
    }
    private void backtrack(List<String> ans, StringBuilder cur, int open, int close, int max) {
        if (cur.length() == max * 2) {
            ans.add(cur.toString());
            return;
        }
        if (open < max) {
            cur.append('(');
            backtrack(ans, cur, open + 1, close, max);
            cur.deleteCharAt(cur.length() - 1);
        }
        if (close < open) {
            cur.append(')');
            backtrack(ans, cur, open, close + 1, max);
            cur.deleteCharAt(cur.length() - 1);
        }
    }


    /**
     * 19：删除链表的倒数第 N 个结点 你能尝试使用一趟扫描实现吗？
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd_19(ListNode head, int n) {
        // 第1步：虚拟个头节点。这是为了在落在N结点前一个
        ListNode dummy = new ListNode(0, head);
        // 第2步：两个快慢指针
        ListNode fast = head;
        for (int i = 0; i < n; ++i) {
            fast = fast.next;
        }
        // 第3步：找到倒数第N+1个结点，并移除
        ListNode slow = dummy;
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }

    /**
     * 18.四数之和：
     *
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> fourSum_18(int[] nums, int target) {
        // 在三数之和的基础上扩展...
        return null;
    }


    /**
     * 17.电话号码d的字母组合？？？
     *  TODO description
     * @param digits
     * @return
     */
    public List<String> letterCombinations_17(String digits) {
        List<String> combinations = new ArrayList<>();
        if (digits.length() == 0) {
            return combinations;
        }
        Map<Character, String> phoneMap = new HashMap<Character, String>() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};
        backtrack(combinations, phoneMap, digits, 0, new StringBuffer());
        return combinations;
    }

    private void backtrack(List<String> combinations, Map<Character, String> phoneMap, String digits, int index, StringBuffer combination) {
        if (index == digits.length()) {
            combinations.add(combination.toString());
        } else {
            char digit = digits.charAt(index);
            String letters = phoneMap.get(digit);
            int lettersCount = letters.length();
            for (int i = 0; i < lettersCount; i++) {
                combination.append(letters.charAt(i));
                // 不断的递归回溯
                backtrack(combinations, phoneMap, digits, index + 1, combination);
                combination.deleteCharAt(index);
            }
        }
    }


    /**
     * 给你一个长度为 n 的整数数组 nums 和 一个目标值 target。请你从 nums 中选出三个整数，使它们的和与 target 最接近，返回这三个数的和
     *
     * @param nums
     * @param target
     * @return
     */
    public int threeSumClosest_05(int[] nums, int target) {
        int len = nums.length;
        if (len <= 3) {
            return nums[0] + nums[1] + nums[2];
        }
        // 第一步，先排序
        Arrays.sort(nums);
        int best = 1000000;
        for (int i = 0; i < len; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            // 第二步，找[起,止]位置
            int j = i + 1, k = len - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == target) {
                    return target;
                }
                // 第三步，根据总值与target的大小决定指针移动方向
                if (sum < target) {
                    j++;
                } else {
                    k--;
                }
                best = Math.min(best, Math.abs(sum - target));
            }
        }
        return best;
    }


    /**
     * 给你一个整数数组nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，同时还满足 nums[i] + nums[j] + nums[k] == 0
     *
     * @param nums
     * @return 返回所有和为 0 且不重复的三元组。
     */
    public List<List<Integer>> threeSum_04(int[] nums) {
        // *** 难点在于如何去除重复解：可以排序后，保证每一层的循环节点nums[n]!=nums[n-1]。正常的三层循环时间复杂度是：O(n^3)，但这里我们可以通过「双指针」解决复杂度 ***
        Arrays.sort(nums);
        int len = nums.length;
        List<List<Integer>> ans = new ArrayList<>();
        // 第1步：循环第一个节点
        for (int first = 0; first < len; ++first) {
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            int third = len - 1;
            int target = -nums[first];
            // 第2步：左右指针循环，减少复杂度
            for (int second = first + 1; second < len; ++second) {
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }
                while (second < third && nums[second] + nums[third] > target) {
                    --third;
                }
                // 第3步：第二个指针和第三个指针相同的时候，说明相碰了则没有满足的条件
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
     * 11. 盛最多水的容器
     * TODO description
     * @param height
     * @return
     */
    public int maxArea_11(int[] height) {
        // 一眼看知道是左右指针处理，但怎么才能确定最多？
        // 想在O(n)的事件范围内找到最大的，那么l、r的范围在缩小的时候就要小的前进一位保证结果最大化
        int l = 0, r = height.length - 1, ans = 0;
        while (l <= r) {
            int result = (r - l) * Math.min(height[l], height[r]);
            ans = Math.max(ans, result);
//            if (height[l] > height[r]) {
//                r--;
//            } else {
//                l++;
//            }
        }
        return ans;
    }



    /**
     * 一个字符串s，找到s中最长的回文子串（如果字符串的反序与原始字符串相同，则该字符串称为回文字符串）
     * qabcabcdd --> abcabc
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        // 1、暴力求解：每个字符循环一遍比较 时间复杂度：O(n^2)
        return longestPalindrome_dp(s);
    }
    private String longestPalindrome_dp(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }
        // *** 动态规划：P(i,j) = P(i+1,j−1) && Si == Sj ; dp[i][j]代表着从i到j区间是否满足回文要求 ***
        // *** 简单来说是先L(0<L<len)大小的dp[i][j]是否满足回文，然后再根据s[i-1]==s[j+1]来依次判断dp[i-1][j+1]是否满足回文 ***
        // 第1步：所有单个dp[i][i]=true
        boolean[][] dp = new boolean[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }
        int begin = 0;
        int maxLen = 1;
        char[] charArray = s.toCharArray();
        // 第2步：start+L计算最大长度（时间复杂度有点高？？好像也没更好的办法）
        for (int L = 2; L <= len; L++) {
            for (int i = 0; i < len; i++) {
                int j = i + L - 1;
                if (j >= len) {
                    break;
                }
                if (charArray[i] != charArray[j]) {
                    dp[i][j] = false;
                } else {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }
                // 第3步：只要 dp[i][L] == true 成立，就表示子串 s[i..L] 是回文，此时记录回文长度和起始位置
                if (dp[i][j] && j - i + 1 > maxLen) {
                    begin = i;
                    maxLen = j - i + 1;
                }

            }
        }
        return s.substring(begin, begin + maxLen);
    }

    /**
     * 2.两数相加：两个非空的链表表示两个非负的整数。它们每位数字都是按照逆序的方式存储的，并且每个节点只能存储一位数字
     * eg. 输入：l1 = [2,4,3], l2 = [5,6,4] ==》 输出：[7,0,8] ； 342 + 465 = 807.然后反转
     *
     * @param l1
     * @param l2
     * @return 将两个数相加，并以相同形式返回一个表示和的链表
     */
    public ListNode addTwoNumbers_02(ListNode l1, ListNode l2) {
        // 1、暴力求解：遍历两个链表求和后再反转结果，时间复杂度：O(n) 空间复杂度：O(1)
        if (l1 == null || l2 == null) {
            return null;
        }
        Stack<Integer> resultStack = new Stack<>();
        int last = 0;
        while (l1 != null || l2 != null) {
            int v1 = l1 != null ? l1.val : 0;
            int v2 = l2 != null ? l2.val : 0;
            int value = v1 + v2 + last;
            last = value / 10;
            resultStack.add(value % 10);
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
        }
        if (last > 0) {
            resultStack.add(last);
        }
        // FIXME 加了堆栈之后这里的空间复杂度就增加不少了..
        ListNode next = new ListNode(resultStack.pop());
        while (!resultStack.isEmpty()) {
            next = new ListNode(resultStack.pop(), next);

        }
        return next;

        // 2、还没想到什么更好的解法..
    }

}
