package com.fang.doit.algo.lc;

import com.fang.doit.algo.classes.linked.ListNode;
import com.fang.doit.algo.classes.tree.TreeNode;

import java.util.*;

/**
 * @author : fangfeiyue
 * @version V1.0
 * @Project: doit
 * @Package com.fang.doit.algo.lc
 * @Description:
 * @date Date : 2025-01-07 17:26
 */
public class Review0107 {


    /**
     * 15.给你一个整数数组nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，同时还满足 nums[i] + nums[j] + nums[k] == 0，返回所有和为 0 且 不重复的三元组
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] == nums[i + 1]) {
                continue;
            }
            int target = -nums[i];
            int left = i, right = n - 1;
            while (left < right) {
                while (nums[left] == nums[left + 1]) {
                    left++;
                }
                while (nums[right] == nums[right - 1]) {
                    right--;
                }
                int num = nums[left] + nums[right];
                if (num == target) {
                    ans.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    left++;
                    right--;
                    continue;
                }
                if (num > target) {
                    right--;
                } else {
                    left++;
                }
            }
        }

        return ans;
    }

    /**
     * 31.下一个排列：指其整数的下一个字典序更大的排列
     * <p>
     * 例如，arr = [1,2,3] 的下一个排列是 [1,3,2] 。 类似地，arr = [2,3,1] 的下一个排列是 [3,1,2]
     *
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        // 先把最大值前移，再将后面剩下排序，2 3 1 -> 3 1 2

        // 最大值：3 2 1
        // 有几个递减
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }

        // 存在递减的
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

//    public static void main(String[] args) {
//        Review0107 review0107 = new Review0107();
//        int[] nums = new int[]{1, 2, 3};
//        review0107.nextPermutation(nums);
//        for (int i = 0; i < nums.length; i++) {
//            System.out.println(nums[i]);
//        }
//    }


    /**
     * 103. 给你二叉树的根节点 root ，返回其节点值的锯齿形层序遍历。即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行
     * <p>
     * root = [3,9,20,null,null,15,7] ==》[ [3], [20,9], [15,7] ]
     *
     * @param root
     * @return
     */
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new LinkedList<>();
        // 下一层节点放到双端队列里，并通过标签控制左右
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean left = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            Deque<Integer> deque = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.peek();
                if (node == null) {
                    continue;
                }
                // 下一层的节点维护在队列里
                if (left) {
                    deque.offerFirst(node.val);
                } else {
                    deque.offerLast(node.val);
                }
                // 当层的输出顺序维护在双端里
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            ans.add(new LinkedList<>(deque));
            left = !left;
        }
        return ans;
    }


    /**
     * 139.给你一个字符串 s 和一个字符串列表 wordDict 作为字典。如果可以利用字典中出现的一个或多个单词拼接出 s 则返回 true
     * <p>
     * s = "leetcode", wordDict = ["leet", "code"] ==》 true
     *
     * @param s 2024-02-21
     * @param wordDict
     * @return
     */
    static boolean wordBreak = false;

    public static boolean wordBreak(String s, List<String> wordDict) {

        // 左进右出，比较字符数 -> 不行，它是任意拼接的

        // 试试深度递归
        wordBreakDFS(s, 0, wordDict);
        return wordBreak;
    }

    private static void wordBreakDFS(String s, int i, List<String> wordDict) {
        if (s.length() == i) {
            wordBreak = true;
            return;
        }
        for (int k = i; k < s.length(); k++) {
            String word = s.substring(i, k + 1);
            if (wordDict.contains(word)) {
                wordBreakDFS(s, k + 1, wordDict);
            }
        }
    }

//    public static void main(String[] args) {
//        System.out.println(wordBreak("catsandog", Lists.newArrayList("cat","dog","san","and","cat")));
//    }


    /**
     * 148.链表的头结点 head ，请将其按升序排列并返回排序后的链表
     * <p>
     * [4,2,7,5,9] -> [2,4,5,7,9]
     *
     * @param head 2024-02-22 again
     * @return
     */
    public static ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        // 列表的归并：前后指针找到中间点，合并左右区间
        ListNode middle = findMiddle(head);
        ListNode next = middle.next;
        middle.next = null;

        // 找到中间节点后，一个从头开始一个从mid.next开始往后合并
        ListNode left = sortList(head);
        ListNode right = sortList(next);
        return mergeListNode(left, right);
    }


    private static ListNode findMiddle(ListNode head) {
        ListNode node = new ListNode(0, head);
        ListNode fast = node;
        ListNode slow = node;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    private static ListNode mergeListNode(ListNode a, ListNode b) {
        ListNode sentry = new ListNode(-1);
        ListNode curr = sentry;
        while (a != null && b != null) {
            if (a.val < b.val) {
                curr.next = a;
                a = a.next;
            } else {
                curr.next = b;
                b = b.next;
            }
            curr = curr.next;
        }
        curr.next = a == null ? b : a;
        return sentry.next;
    }


//    public static void main(String[] args) {
//        sortList(new ListNode(3,
//                new ListNode(5,
//                        new ListNode(2,
//                                new ListNode(1,
//                                        new ListNode(6,
//                                                new ListNode(10,
//                                                        new ListNode(9,
//                                                                new ListNode(11,
//                                                                        new ListNode(4,
//                                                                                new ListNode(7)))))))))))
//                .print();
//    }

    /**
     * 154.数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次的结果为数组 [a[n-2], a[n-1], a[0], a[1], a[2], ..., a[n-3]]，给你一个元素值互不相同的数组 nums ，
     * 它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的最小元素
     *
     * @param nums
     * @return
     */
    public static int findMin(int[] nums) {
        // 你必须设计一个时间复杂度为 O(log n) 的算法解决此问题

        // 二分找到节点的左右都大，找到大的那一边不断二分

        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int middle = low + (high - low) / 2;
            // 增长不一定能找到最小值，递减一定能找到最小值
            if (nums[middle] < nums[high]) {
                high = middle;
            } else if (nums[middle] > nums[high]) {
                low = middle + 1;
            } else {
                high = high - 1;
            }
        }
        return nums[low];
    }


    /**
     * 159.给你一个字符串 s ，请你找出至多包含两个不同字符的最长子串，并返回该子串的长度
     * <p>
     * "eceba" --> 3 "ece"
     * ccaabbb --> 5 "aabbb"
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int[] nums = new int[128];
        int count = 0, left = 0, right = 0;
        while (right < s.length()) {
            if (nums[s.charAt(right)]++ == 0) {
                count++;
            }
            if (count > 2) {
                if (--nums[s.charAt(left++)] == 0) {
                    count--;
                }
            }
            right++;
        }
        return right - left;
    }


    /**
     * 207. 你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 ， prerequisites[i] = [ai, bi] ，表示如果要学习课程ai则必须先学习课程bi，是否能完成学习
     * <p>
     * numCourses = 2, prerequisites = [[1,0]] ==> true
     * numCourses = 2, prerequisites = [[1,0],[0,1]] ==> false
     *
     * @param numCourses
     * @param prerequisites
     * @return 是否可能完成所有课程的学习
     */
    static boolean canFinish = false;

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, int[]> preMap = new HashMap<>();
        for (int i = 0; i < prerequisites.length; i++) {
            int[] next = prerequisites[i];
            preMap.put(i, next);
        }

        int[] visited = new int[prerequisites.length];
        for (int i = 0; i < numCourses; i++) {
            if (visited[numCourses] == 0) {
                //
            }
        }

        return canFinish;
    }


    private static boolean canFinishDFS(int[] visited, int course, int[][] prerequisites) {
        // 未执行、执行中、执行结束
        visited[course] = 1;

        return false;
    }

    /**
     * 213. 第一个房屋和最后一个房屋是紧挨着的，同时相邻的房屋装有相互连通的防盗系统。如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警，在不触动警报装置的情况下今晚能够偷窃到的最高金额
     * <p>
     * [1,3,1] ==> 3
     *
     * @param nums
     * @return
     */
    public static int rob(int[] nums) {
        // 每晚可偷的最大金额
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        int max = dp[1];
        for (int i = 2; i < nums.length; i++) {
            // 当天可偷最大金额为：当天偷（i-2的最大值+今天） 或 当天不偷（最大值还是i-1）
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
            max = Math.max(dp[i], max);
        }
        return max;
    }


    /**
     * 337.打家劫舍III 除了 root 之外，每栋房子有且只有一个父房子与之相连。一番侦察之后，聪明的小偷意识到这个地方的所有房屋的排列类似于一棵二叉树
     * 如果两个直接相连的房子在同一天晚上被打劫 ，房屋将自动报警，在不触动警报的情况下小偷能够盗取的最高金额
     *
     * <p>
     * root = [3,2,3,null,3,null,1] ==> 3 + 3 + 1 = 7
     * root = [3,4,5,1,3,null,1] ==> 4 + 5 = 9
     *
     * @param root
     * @return
     */
    public static int x_rob(TreeNode root) {
        // 深度遍历树，最终会得到两个选择：父节点选或不选
        int[] rob = robDFS(root);
        return Math.max(rob[0], rob[1]);
    }

    private static int[] robDFS(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0};
        }
        // 自下往上遍历，能不能上到下？
        int[] l = robDFS(node.left);
        int[] r = robDFS(node.right);

        // 当天可偷最大金额为：父节点被偷（子节点不能偷） 或 父节点没偷（可偷两子节点中任一）
        int steal = node.val + l[1] + r[1];
        // 层层遍历，好题
        int no_steal = Math.max(Math.max(l[0], l[1]), Math.max(r[0], r[1]));
        return new int[]{steal, no_steal};
    }


    /**
     * 214. 给定一个字符串 s，你可以通过在字符串前面添加字符将其转换为回文串，找到并返回可以用这种方式转换的最短回文串
     * <p>
     * "aacecaaa" ==》"aaacecaaa"
     * "abcd" ==》"dcbabcd"
     *
     * @param s
     * @return
     */
    public static String x_shortestPalindrome(String s) {
        // 核心：怎么找到[0,i]区间回文，可以从尾部开始的

        return null;
    }


//    public static void main(String[] args) {
//
//        System.out.println(x_shortestPalindrome("abcecadba"));
//    }

    /**
     * 236.给定一个二叉树, 找到该树中两个指定节点的最近公共祖先
     *
     * @param cur
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode cur, TreeNode p, TreeNode q) {
        // 找到在左右子树上两个节点的最小父节点，然后比较
        return lowestCommonAncestorDFS(cur, p, q);
    }


    private TreeNode lowestCommonAncestorDFS(TreeNode cur, TreeNode p, TreeNode q) {
        if (cur == null) {
            return null;
        }
        if (cur == p || cur == q) {
            return cur;
        }
        TreeNode left = lowestCommonAncestorDFS(cur.left, p, q);
        TreeNode right = lowestCommonAncestorDFS(cur.right, p, q);
        // 左没有，则是该节点可能右有
        if (left == null) {
            return right;
        }
        // 右没有，则是该节点可能左有
        if (right == null) {
            return left;
        }
        // 左右节点都有，返回当前当前节点
        return cur;
    }


    /**
     * 239.给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧，你只可以看到在滑动窗口内的 k 个数字，求滑动窗口中的最大值
     * <p>
     * eg.[1,3,-1,-3,5,3,6,7] k = 3 ==> [3,3,5,5,6,7]
     *
     * @param nums
     * @param k
     * @return
     */
    public static int[] maxSlidingWindow(int[] nums, int k) {
        // 1、新加入后保持头部最大，保持队列递增
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            while (!queue.isEmpty() && queue.peek() < nums[i]) {
                queue.poll();
            }
            queue.add(nums[i]);
        }

        int[] res = new int[nums.length - k + 1];
        res[0] = queue.peek();

        for (int i = k; i < nums.length; i++) {
            while (!queue.isEmpty() && queue.peek() < nums[i]) {
                queue.poll();
            }
            if (queue.isEmpty() || queue.size() < k) {
                queue.add(nums[i]);
            }
            res[i - k + 1] = queue.peek();
        }
        return res;
    }


//
//    public static void main(String[] args) {
////        int[] nums = {1, 3, 2, 5, 4, 3, 7, 5, 6, 8};
//        int[] nums = {1, 2, 3, 4, 5, 6, 7, 5, 6, 8};
//        int[] res = maxSlidingWindow(nums, 3);
//        for (int re : res) {
//            System.out.println(re);
//        }
//    }

    /**
     * 253.给你一个会议时间安排的数组intervals ，每个会议时间都会包括开始和结束的时间 intervals[i] = [starti, endi]，返回所需会议室的最小数量
     * <p>
     * intervals = [[0,30],[5,10],[15,20]] ==> 2
     *
     * @param intervals
     * @return
     */
    public static int minMeetingRooms(int[][] intervals) {
        int meetingCount = 0;
        // 新会议来之后结束所有小于开始时间的老会议 -> queue来维护递减序列  ==》不行，queue只能递增
//        Queue<Integer> meetings = new LinkedList<>();
//        for (int i = 0; i < intervals.length; i++) {
//            int start = intervals[i][0];
//            int end = intervals[i][1];
//
//            while (!meetings.isEmpty() && meetings.peek() < start) {
//                meetings.poll();
//            }
//            meetings.add(end);
//            meetingCount = Math.max(meetingCount, meetings.size());
//        }

        // 小顶堆：维护即将到期的会议（默认小顶堆）
        PriorityQueue<Integer> smallQueue = new PriorityQueue<>((Comparator.comparingInt(o -> o)));
        for (int i = 0; i < intervals.length; i++) {
            int start = intervals[i][0];
            int end = intervals[i][1];
            while (!smallQueue.isEmpty() && smallQueue.peek() < start) {
                smallQueue.poll();
            }
            smallQueue.offer(end);
            meetingCount = Math.max(meetingCount, smallQueue.size());
        }

        return meetingCount;
    }


//    public static void main(String[] args) {
//        System.out.println(minMeetingRooms(new int[][]{{0, 30}, {5, 10}, {15, 20}}));
//    }

    /**
     * 255.给定一个无重复元素的整数数组 preorder，如果它是以二叉搜索树的先序遍历排列返回 true
     * <p>
     * preorder = [5,2,1,3,6] ==> true
     * preorder = [5,2,6,1,3] ==> false
     *
     * @param preorder
     * @return
     */
    public static boolean x_verifyPreorder(int[] preorder) {
        // 先序排序规则：先根节点，再左节点，最后右节点；是先递减在递增回溯的过程

        Stack<Integer> stack = new Stack<>();
        int left_max = Integer.MIN_VALUE;
        for (int cur : preorder) {
            // 先序拐点在根节点：后续右子树节点都要大于此节点
            while (!stack.isEmpty() && cur > stack.peek()) {
                left_max = stack.pop();
            }
            // max：左子树的最大值，cur：右子树的最小值，cur < max 说明不是二叉搜索树
            if (cur < left_max) {
                return false;
            }
            stack.push(cur);
        }
        return true;
    }

//    public static void main(String[] args) {
//        System.out.println(x_verifyPreorder(new int[]{5, 2, 6, 1, 3}));
//    }

    /**
     * 279.给你一个整数 n ，返回和为 n 的完全平方数的最少数量（ 完全平方数：是其值等于一个整数自乘的积，例如 1、4、9 和 16 都是完全平方数，而 3 和 11 不是 ）
     * <p>
     * n = 12 ==> 3，4+4+4
     *
     * @param n
     * @return
     */
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[n];
    }


    /**
     * 287. 给定一个包含n + 1个整数的数组 nums ，其数字都在[1, n]范围内，可知至少存在一个重复的整数返回这个重复的数（假设nums只有一个重复的整数）
     * <p>
     * [3,2,5,4,6,1,4] ==> 4
     *
     * @param nums
     * @return
     */
    private int findDuplicate_4_mid(int[] nums) {
        // 时间复杂度:O(logn)，空间复杂度：O(1)
        // 巧妙利用二分法
        int left = 1;
        int right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            int cnt = 0;
            // 找到所有小于mid的数
            for (int num : nums) {
                if (num <= mid) {
                    cnt++;
                }
            }
            // cnt > mid 说明重复数在 mid 的左边，则值在 [1,mid]
            if (cnt > mid) {
                right = mid;
            } else {
                // cnt = mid 说明在左侧或自己，left可以返回
                left = mid + 1;
            }
        }
        return left;
    }

    private int findDuplicate_4_slow(int[] nums) {
        // 通过nums[n] -> nums[nums[n]]的指向构建成一个环（不在意环的位置是否是链表最后只要保证环存在即可）
        int slow = nums[0];
        int fast = nums[nums[0]];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        fast = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }


    /**
     * 309. 给定一个整数数组prices，其中第  prices[i] 表示第 i 天的股票价格。设计一个算法计算出最大利润在满足以下约束条件下，你可以尽可能地完成更多的交易，卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）
     * <p>
     * eg. prices = [1,2,3,0,2] ==> 3 [买入, 卖出, 冷冻期, 买入, 卖出]
     *
     * @param prices
     * @return
     */
    public static int x_maxProfit(int[] prices) {
        // 某一天最大利润，前一天买入/卖出/冷冻期 ==> 选择当天视角或者前一天视角理论上都行，这里是当前视角
        int[][] dp = new int[prices.length][3];
        int n = prices.length;
        dp[0][0] = 0;
        for (int i = 1; i < prices.length; i++) {
            // 手上持有股票: 一直持有 或 前一天非冷冻期买入
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][2] - prices[0]);
            // 手上不持有股票且处于冷冻期: 前一天卖出
            dp[i][1] = dp[i - 1][0] + prices[i - 1];
            // 手上不持有股票且不处于冷冻期：第i-1天没操作，可能在冷冻也可能不在
            dp[i][2] = Math.max(dp[i - 1][1], dp[i - 1][2]);
        }
        return Math.max(dp[n - 1][1], dp[n - 1][2]);
    }


//    public static void main(String[] args) {
//        System.out.println(x_maxProfit(new int[]{1, 2, 3, 0, 2}));
//    }


    /**
     * 264.一个整数n，返回第n个丑数，丑数就是质因子只包含 2、3 和 5 的正整数
     * <p>
     * n = 10 ==> 12 [1, 2, 3, 4, 5, 6, 8, 9, 10, 12] 丑数组成的前10列表
     *
     * @param n
     * @return
     */
    public static int nthUglyNumber(int n) {
        // 在i的最小丑数值
        int[] dp = new int[n];
        dp[0] = 1;

        // 2、3、5丑数每次在之前的基础上再乘以自身数字，找到最小的那个

        // p 表示当前数字乘了几次
        int p2 = 0, p3 = 0, p5 = 0;
        for (int i = 1; i < n; i++) {
            // dp[p2] 代表上一次2的累计值，那么下一次关于2的乘就是 dp[p2] * 2，如果是最小那么 P2+1
            int nums2 = dp[p2] * 2, nums3 = dp[p3] * 3, nums5 = dp[p5] * 5;
            dp[i] = Math.min(nums2, Math.min(nums3, nums5));
            if (dp[i] == nums2) {
                p2++;
            }
            if (dp[i] == nums3) {
                p3++;
            }
            if (dp[i] == nums5) {
                p5++;
            }
        }
        return dp[n - 1];
    }


//    public static void main(String[] args) {
//
//        System.out.println(nthUglyNumber(10));
//
//    }

    /**
     * 313.超级丑数是一个正整数，并满足其所有质因数都出现在质数数组 primes 中，给你一个整数 n 和一个整数数组 primes ，返回第 n 个 超级丑数
     * <p>
     * n = 12, primes = [2,7,13,19] ==》32 ，12 个超级丑数序列为：[1,2,4,7,8,13,14,16,19,26,28,32]
     *
     * @param n
     * @param primes
     * @return
     * @see
     */
    public static int x_nthSuperUglyNumber(int n, int[] primes) {
        // 值：记录更新后的值
        int[] nums = Arrays.copyOf(primes, primes.length);
        // 次数：
        int[] p = new int[primes.length];
        // 丑数
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            int min = Arrays.stream(nums).min().getAsInt();
            dp[i] = min;
            for (int j = 0; j < primes.length; j++) {
                if (min == nums[j]) {
                    p[j]++;
                    // 关键点：p点的下一位乘积，比如现在 p 乘了2次，这次又是最小值，那么下一次就是 3 * primes[j] 作为 nums[p] 的值
                    nums[j] = dp[p[j]] * primes[j];
                }
            }
        }
        return dp[n - 1];
    }


//    public static void main(String[] args) {
//        System.out.println(x_nthSuperUglyNumber(12, new int[]{2, 7, 13, 19}));
//    }


    /**
     * 323：无向图中连通分量的数目，你有一个包含 n 个节点的图。给定一个整数 n 和一个数组 edges ，其中 edges[i] = [ai, bi] 表示图中 ai 和 bi 之间有一条边，返回图中已连接分量的数目
     * <p>
     * n = 5, [[0, 1], [1, 2], [3, 4]] ==> 2
     * n = 5, [[0,1], [1,2], [2,3], [3,4]] ==> 1
     * n = 5 [[0,1],[0,4],[1,4],[2,3]]
     *
     * @param n
     * @param edges
     * @return
     */
    public int x_countComponents_323(int n, int[][] edges) {
        int components = 0;
        int[] visited = new int[n];
        // 数组列表 或者 哈希列表都可
        List<Integer>[] adjList = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<>();
        }
        for (int i = 0; i < edges.length; i++) {
            // 注意是双向联通的：a->b b->a
            adjList[edges[i][0]].add(edges[i][1]);
            adjList[edges[i][1]].add(edges[i][0]);
        }

        for (int i = 0; i < n; i++) {
            if (visited[i] == 0) {
                components++;
                countComponentsDFS(adjList, visited, i);
            }
        }
        return components;
    }

    private void countComponentsDFS(List<Integer>[] adjList, int[] visited, int startNode) {
        visited[startNode] = 1;
        for (int i = 0; i < adjList[startNode].size(); i++) {
            if (visited[adjList[startNode].get(i)] == 0) {
                countComponentsDFS(adjList, visited, adjList[startNode].get(i));
            }
        }
    }

    /**
     * 325.给定一个数组 nums 和一个目标值 k，找到和等于 k 的最长连续子数组长度。如果不存在任意一个符合要求的子数组，则返回 0
     * <p>
     * nums = [1,-1,5,-2,3], k = 3 ==》4 [1,-1,5,-2]
     *
     * @param nums
     * @param k
     * @return
     */
    private static int x_maxSubArrayLen(int[] nums, int k) {

        // 滑动窗口，可能有负数 双端才行

        // 前缀和，pre[j] - pre[i] = k 的情况下说明 j-i的长度满足要求
        int preSum = 0, max = 0;
        Map<Integer, Integer> point = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (point.containsKey(preSum - k)) {
                max = Math.max(max, i - point.get(preSum - k));
            }
            preSum = preSum + nums[i];

            // 要更新，保证最长
            point.put(preSum, i);
        }
        return max;
    }

//    public static void main(String[] args) {
//
//        System.out.println(maxSubArrayLen(new int[]{1, -1, 5, -2, 3}, 3));
//
//    }


    /**
     * 333. 给定一个二叉树，找到其中子树节点数最多的二叉搜索树（BST）子树并返回该子树的大小
     * <p>
     * 输入：root = [10,5,15,1,8,null,7] ==> 输出：3
     * 输入：root = [4,2,7,2,3,5,null,2,null,null,null,null,null,1] ==> 输出：2
     *
     * @param root
     * @return
     */

    public int x_largestBSTSubtree(TreeNode root) {

        // 二叉搜索树，左小右大，深度递归后逐步找到满足条件的节点树，通过节点标识是否是BST、大小SIZE、及最大最小值

        return 0;
    }


    /**
     * 316. 给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证返回结果的字典序最小（要求不能打乱其他字符的相对位置）
     * <p>
     * s = "bcabc" ==> abc
     * s = "cbacdcbc" ==> acdb
     *
     * @param s
     * @return
     */
    public String removeDuplicateLetters(String s) {
        // 放弃xxx
        return "";
    }


    /**
     * 340.给你一个字符串 s 和一个整数 k ，请你找出至多包含 k 个不同字符的最长子串并返回该子串的长度
     * <p>
     * s = "eceba", k = 2 ==> 3 "ece"
     * s = "aa", k = 1 ==> 2 "aa"
     *
     * @param s
     * @param k
     * @return
     */
    public static int lengthOfLongestSubstringKDistinct(String s, int k) {
        // 滑动窗口，统计窗口内不同字符的数量
        int[] nums = new int[128];
        int left = 0, right = 0, count = 0, max = 1;
        while (right < s.length()) {
            // 有新入的
            if (nums[s.charAt(right++)]++ == 0) {
                count++;
            }
            // 超过k的
            while (count > k) {
                if (--nums[s.charAt(left++)] == 0) {
                    count--;
                }
            }

            // 这里的 right 是下一次循环要进入窗口的，所以这里 right - left 后不用再 +1
            max = Math.max(max, right - left);
        }
        return max;
    }

//    public static void main(String[] args) {
//
//        System.out.println(lengthOfLongestSubstringKDistinct("eceba", 2));
//
//    }


    /**
     * 343. 给定一个正整数 n ，将其拆分为 k 个 正整数 的和（ k >= 2 ），并使这些整数的乘积最大化，返回你可以获得的最大乘积
     * <p>
     * n = 10 ==> 10 = 3 + 3 + 4, 3 × 3 × 4 = 36
     *
     * @param n
     * @return
     */
    public int x_integerBreak(int n) {
        // 动态规划来看，找到某一个值的最大乘机即是过去某一个的最大乘机再乘以（i-j）

        int[] dp = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            int tmpMax = 0;
            /*
                先将 i 分解为 j 和 i - j
                如果 i - j 不再分解，那么 dp[i] = j * (i - j)
                如果 i - j 继续分解，那么 dp[i] = j * dp[i - j]
                为什么要单独捞出 j * (i - j) 这种情况？
            */
            for (int j = 1; j < i; j++) {
                tmpMax = Math.max(tmpMax, Math.max(j * (i - j), j * dp[i - j]));
            }
            dp[i] = tmpMax;
        }
        return dp[n];
    }


    /**
     * 347.给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案
     * <p>
     * 输入: nums = [1,1,1,2,2,3], k = 2  ==>  [1,2]
     * 输入: nums = [1], k = 1 ==>  [1]
     *
     * @param nums
     * @param k
     * @return
     */
    public static List<Integer> topKFrequent_queue(int[] nums, int k) {

        // 统计出现次数，然后丢到大顶堆


        return null;
    }

    public static List<Integer> topKFrequent_bat(int[] nums, int k) {

        return null;
    }


    /**
     * 395.给你一个字符串 s 和一个整数 k ，请你找出 s 中的最长子串，要求该子串中的每一字符出现次数都不少于 k
     * <p>
     * s = "aaabb", k = 3 ==》3  aaa
     * s = "ababbc", k = 2 ==》5  ababb
     *
     * @param s
     * @param k
     * @return
     */
    public int x_longestSubstring(String s, int k) {
        // 出现次数不少于k:
        // 1、滑动窗口？不行，不能判断最后一个字符出现在哪
        // 2、分割，把不满足条件的字符全部分割出去后留下的最大的字符即是结果

        return longestSubstring(s, k);
    }


    private int longestSubstring(String s, int k) {
        // 统计字符内出现的次数
        Map<Character, Integer> counter = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            counter.put(s.charAt(i), counter.getOrDefault(s.charAt(i), 0) + 1);
        }

        int res = 0;
        for (char c : counter.keySet()) {
            // 字符出现次数不满足条件，应该要被分割出去
            if (counter.get(s.charAt(c)) < k) {
                for (String t : s.split(String.valueOf(c))) {
                    // 字符左右分割后，再看看不含有字符的两个字符串哪个满足条件
                    res = Math.max(res, longestSubstring(t, k));
                }
                return res;
            }
        }

        return s.length();
    }


    /**
     * 402. 给你一个以字符串表示的非负整数 num 和 一个整数 k ，移除这个数中的 k 位数字，使得剩下的数字最小
     * <p>
     * num = "1432259", k = 3 ==> 1225  移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219
     * num = "10200", k = 1 ==> "200" 移掉首位的 1 剩下的数字为 200. 注意输出不能有任何前导零
     *
     * @param num
     * @param k
     * @return
     */
    public String removeKdigit(String num, int k) {

        // 雪耻之题！！！

        // 队列先进先出，下一个来的时候不断找前一个小的
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < num.length(); i++) {
            while (k > 0 && !stack.isEmpty() && stack.peek() > num.charAt(i)) {
                stack.pop();
                k--;
            }
            stack.push(num.charAt(i));
        }
        // 如果是一直递减，那么k的值不变，则需要从头拿掉k位
        for (int i = 0; i < k; i++) {
            stack.pop();
        }

        // 最后再把stack的数字倒着输出即可

        return null;
    }


    /**
     * 413 给你一个整数数组 nums ，返回数组 nums 中所有等差数组的子数组个数
     * <p>
     * nums = [1,2,3,4] ==》3 子等差数组：[1, 2, 3]、[2, 3, 4] 、[1,2,3,4]
     * [1, 2, 3]、[2, 3, 4] 、[1,2,3,4]、[1,2,3,4,5]
     *
     * @param nums
     * @return
     */
    public static int numberOfArithmeticSlices(int[] nums) {
        // 1、深度遍历 复杂度太高且实现有点难
        // 2、动态规划..（傻逼了居然没想到）

        int n = nums.length;
        int ans = 0;
        int temp = 0;
        int d = nums[1] - nums[0];
        for (int i = 2; i < n; i++) {
            if (nums[i] - nums[i - 1] == d) {
                temp++;
            } else {
                // 如果不符合等差，清零并更新差
                temp = 0;
                d = nums[i] - nums[i - 1];
            }
            // 每新增一位则加 temp 个子数组，如 [1,2,3,4] 时 temp = 2，那么 [1,2,3,4,5] 后会新增 temp = 3 个连续等差
            ans += temp;
        }
        return ans;
    }

//    public static void main(String[] args) {
//        System.out.println(numberOfArithmeticSlices(new int[]{1,2,3,4,5}));
//    }

    /**
     * 424. 给你一个字符串 s 和一个整数 k 。你可以选择字符串中的任一字符，并将其更改为任何其他大写英文字符，该操作最多可执行 k 次，在执行上述操作后，返回包含相同字母的最长子字符串的长度
     * <p>
     * <p>
     * s = "ABAB", k = 2  ==》 4  用两个'A'替换为两个'B',反之亦然
     * s = "AABABBA", k = 1  ==》4  "AABBBBA"
     * s = "ABCCDFGCFG"，K = 2  ==》
     *
     * @param s
     * @param k
     * @return
     */
    public int characterReplacement(String s, int k) {
        int left = 0, right = 0, max = 0;
        int[] window = new int[26];
        while (right < s.length()) {
            int num = s.charAt(left) - 'A';
            window[num]++;
            max = Math.max(max, window[num]);
            int wl = right - left + 1;
            // 最大替换次数 + 可替换的次数k > 窗口就说明窗口内还有空间则可以继续右进,否则就要出
            if (max + k < wl) {
                window[num]--;
                left++;
            }
            right++;
        }
        return right - left;
    }


    /**
     * 450.给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变
     * <p>
     * root = [5,3,6,2,4,null,7], key = 3  ==》[5,4,6,2,null,null,7] 或 [5,2,6,null,4,null,7]
     * root = [5,3,6,2,4,null,7], key = 0  ==》[5,3,6,2,4,null,7]
     *
     * @param root
     * @param key
     * @return 根节点
     */
    private TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (root.val > key) {
            // 如果 root.left 正好是删除节点，那么 root.left = successor
            root.left = deleteNode(root.left, key);
            return root;
        }
        if (root.val < key) {
            // 如果 root.right 正好是删除节点，那么 root.right = successor
            root.right = deleteNode(root.right, key);
            return root;
        }

        // ------- root.val == key --------
        // 如果节点的左右子树都为null，则直接返回null，作为删除状态
        if (root.left == null && root.right == null) {
            return null;
        }
        // 如果右节点为空，则左子树直接衔接上
        if (root.right == null) {
            return root.left;
        }
        // 如果左节点为空，则右子树直接衔接上
        if (root.left == null) {
            return root.right;
        }

        // 1、找到root右子树的最小节点，即右子树的左子树叶子节点
        TreeNode successor = root.right;
        while (successor.left != null) {
            successor = successor.left;
        }
        // 2、删除右子树的该节点
        root.right = deleteNode(root.right, successor.val);
        // 3、把该节点作为新的根节点（该节点满足大于root的所有左子树，且小于root所有右子树）
        successor.right = root.right;
        successor.left = root.left;
        return successor;
    }


    /**
     * 473. 一个整数数组 matchsticks ，其中 matchsticks[i] 是第 i 个火柴棒的长度，你要用所有的火柴棍拼成一个正方形，不能折断任何一根火柴棒，但可以把它们连在一起，而且每根火柴棒必须使用一次
     * <p>
     * matchsticks = [1,1,2,2,2] ==> true
     * matchsticks = [3,3,3,3,4] ==> false
     *
     * @param matchsticks
     * @return
     */
    public boolean makesquare(int[] matchsticks) {
        int totalLen = Arrays.stream(matchsticks).sum();
        if (totalLen % 4 != 0) {
            return false;
        }
        Arrays.sort(matchsticks);
        for (int i = 0, j = matchsticks.length - 1; i < j; i++, j--) {
            int temp = matchsticks[i];
            matchsticks[i] = matchsticks[j];
            matchsticks[j] = temp;
        }

        int[] edges = new int[4];
        // 对于数字index来说，每次都有4个选择，每次深度递归尝试后，可以则返回TRUE，否则返回FALSE，
        return makesquaredfs(0, matchsticks, edges, totalLen / 4);
    }

    private boolean makesquaredfs(int index, int[] matchsticks, int[] edges, int len) {
        if (index == matchsticks.length) {
            return true;
        }
        for (int i = 0; i < edges.length; i++) {
            edges[i] += matchsticks[index];
            if (edges[i] <= len && makesquaredfs(index + 1, matchsticks, edges, len)) {
                return true;
            }
            edges[i] -= matchsticks[index];
        }
        return false;
    }


}
