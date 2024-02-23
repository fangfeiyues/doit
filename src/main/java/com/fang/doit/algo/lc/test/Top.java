package com.fang.doit.algo.lc.test;

import com.fang.doit.algo.dst.linked.ListNode;
import com.fang.doit.algo.tree.TreeNode;

import java.util.*;

/**
 * @author : fangfeiyue
 * @version V1.0
 * @Project: doit
 * @Package com.fang.doit.algo.lc.test
 * @Description: 2023.05月刷题记录
 * @date Date : 2023-05-07 4:21 下午
 */
public class Top {

    /**
     * 1、2024-02-25 : top200-middle over
     */


    /**
     * 164.给定一个无序的数组 nums，返回 数组在排序之后，相邻元素之间最大的差值 。如果数组元素个数小于 2，则返回 0 。
     * <p>
     * [3,6,9,1] -> 3
     *
     * @param nums
     * @return
     */
    public int maximumGap(int[] nums) {
        // 必须编写一个在「线性时间」内运行并使用「线性额外空间」的算法
        // 桶排序。。
        return 0;
    }

    /**
     * 162.峰值元素是指其值严格大于左右相邻值的元素。
     * 给你一个整数数组 nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。
     * 你可以假设 nums[-1] = nums[n] = -∞ 。
     * [1,2,3,1] --> 2
     * [1,2,1,3,5,6,4] -> 1 或 5
     *
     * @param nums
     * @return
     */
    public int findPeakElement(int[] nums) {
        // 必须实现时间复杂度为 O(log n) 的算法来解决此问题。
        // 二分法：往上走的一定有波峰
        int left = 0;
        int right = nums.length - 1;
        //二分法
        while (left < right) {
            int mid = (left + right) / 2;
            // 右边是往下，不一定有坡峰
            if (nums[mid] > nums[mid + 1]) {
                right = mid;
            }
            // 右边是往上，一定能找到波峰
            else {
                left = mid + 1;
            }
        }
        //其中一个波峰
        return right;
    }

    /**
     * 159.给你一个字符串 s ，请你找出至多包含两个不同字符的最长子串，并返回该子串的长度
     * "eceba" --> 3 "ece"
     * ccaabbb --> 5 "aabbb"
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        // 暴力循环：每个节点不断回退前两个不同点，时间复杂度O(n^2)
        // 左右指针：保证left-right指针区间内的数据类型==2,通过左右指针差求最大值
        // 滑动窗口:利用数组集合代替map,保持窗口内类型在2否则一直移动

        int[] window = new int[128];
        int length = s.length();
        int left = 0, right = 0;
        // count代表当前窗口的字符类型多少
        for (int count = 0; right < length; ) {
            // 左侧字符进窗口：如果不在窗口区则窗口数量和字符数都加1，否则数量加1
            if (window[s.charAt(right++)]++ == 0) {
                count++;
            }
            // 如果字符数量大于2：则窗口不断移动直到数量清零
            if (count > 2) {
                if (--window[s.charAt(left++)] == 0) {
                    count--;
                }
            }
        }
        return right - left;
    }

    /**
     * 156.上下翻转二叉树；左节点->根节点，根节点->右节点，右节点->左节点
     *
     * @param root
     * @return
     */
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        return null;
    }


    /**
     * 数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次的结果为数组 [a[n-2],a[n-1], a[0], a[1], a[2], ..., a[n-3]]
     * <p> [ 5,4,3,2,1] [ 5,4,3,2,1,6]
     * 给你一个元素值互不相同的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的最小元素
     *
     * @param nums 2024-02-22
     * @return
     */
    public static int findMin(int[] nums) {
        // 你必须设计一个时间复杂度为 O(log n) 的算法解决此问题
        // 二分法：找到节点的左右侧都大于它的或者它最小的
//        int low = 0;
//        int high = nums.length - 1;
//        int middle = (low + high) / 2;
//        while (low < high) {
//            // 先保证middle不是最小值
//            if (nums[middle + 1] > nums[middle] && nums[middle - 1] > nums[middle]) {
//                return nums[middle];
//            }
//            // 右边大，往左找
//            if (nums[middle + 1] > nums[middle]) {
//                high = middle - 1;
//            }
//            // 左边大，往右找
//            if (nums[middle - 1] > nums[middle]) {
//                low = middle + 1;
//            }
//            // ⚠️ 二分法查找一定要避免出现middle一直不变的死循环
//            middle = (low + high) / 2;
//        }
//        return nums[middle];

        // 这么简单吗。。。是我想复杂了？？？
        int low = 0;
        int high = nums.length - 1;
        while (low < high) {
            // 不用和下一个比，和最后一个比或者第一个比。保证在这个区间是否升降级
            int pivot = low + (high - low) / 2;
            if (nums[pivot] < nums[high]) {
                high = pivot;
            } else {
                low = pivot + 1;
            }
        }
        return nums[low];
    }

//    public static void main(String[] args) {
//        int[] nums = {5,4,3,2,1,1,6};
//        System.out.println(findMin(nums));
//    }


    /**
     * 152.给你一个整数数组 nums ，请你找出数组中乘积最大的非空连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积
     * nums = [2,3,-2,4] ==> 6(2 * 3)
     * [-3,-1,-1]
     * [-2,3,-4]
     *
     * @param nums 2024-02-22
     * @return
     */
    public static int maxProduct_152_xx(int[] nums) {
//        int[] dp = new int[nums.length];
//        dp[0] = nums[0];
//        int max = dp[0];
//        for (int i = 1; i < nums.length; i++) {
//            // 要连续
//            if (dp[i - 1] * nums[i] > dp[i - 1]) {
//                dp[i] = dp[i - 1] * nums[i];
//            }else {
//                dp[i] = nums[i];
//            }
//            max = Math.max(max, dp[i]);
//        }
//        return max;
        int maxF = nums[0], minF = nums[0], ans = nums[0];
        int length = nums.length;
        for (int i = 1; i < length; ++i) {
            int mx = maxF, mn = minF;
            maxF = Math.max(mx * nums[i], Math.max(nums[i], mn * nums[i]));
            minF = Math.min(mn * nums[i], Math.min(nums[i], mx * nums[i]));
            ans = Math.max(maxF, ans);
        }
        return ans;
    }

    /**
     * 151.给你一个字符串 s ，请你反转字符串中 单词 的顺序（空格隔开）
     * s = "the sky is blue" ==》 "blue is sky the"
     *
     * @param s 2024-02-22
     * @return
     */
    public static String reverseWords_151(String s) {
        // 除去开头和末尾的空白字符
        s = s.trim();
        // 正则匹配连续的空白字符作为分隔符分割
        List<String> wordList = Arrays.asList(s.split("\\s+"));
        Collections.reverse(wordList);
        return String.join(" ", wordList);
    }

//    public static void main(String[] args) {
//        System.out.println(reverseWords("  hello world  ").length());
//    }

    /**
     * 148.链表的头结点 head ，请将其按升序排列并返回排序后的链表
     * [4,2,7,5,9] -> [2,4,5,7,9]
     *
     * @param head 2024-02-22 again..
     * @return
     */
    public ListNode sortList_148_xx(ListNode head) {
        // 归并排序：数组不断按中间节点分割成最小但愿如此，然后递归合并排序。简称先归一再合并
        if (head == null || head.next == null) {
            return head;
        }
        ListNode middle = findMiddleNode(head);
        ListNode rightHead = middle.next;
        // 中间侧断开保证下一次遍历只有前半部分（由于后续不会再用到这个链表所以不用担心链表接不上的问题）
        middle.next = null;
        ListNode left = sortList_148_xx(head);
        ListNode right = sortList_148_xx(rightHead);
        return mergeListNode(left, right);
    }

    private ListNode findMiddleNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow = head;
        // 因为要从中间分成两截，跟正常的快慢不一样，fast提前走保证slow在中间点左侧
        ListNode fast = head.next.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private ListNode mergeListNode(ListNode a, ListNode b) {
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
//        Top top = new Top();
//        top.sortList_148(new ListNode(3,new ListNode(5,new ListNode(2,new ListNode(1))))).print();
//    }

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

    public static boolean wordBreak_139(String s, List<String> wordDict) {
        // 不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用
        if (s == null || "".equals(s)) {
            return true;
        }
        if (wordDict == null || wordDict.size() == 0) {
            return false;
        }
        // 树型递归:只有要一条链路都包含在字典内即可
        dfs(s, 0, wordDict);
        return wordBreak;
    }

    private static void dfs(String s, Integer start, List<String> wordDict) {
        if (start == s.length()) {
            wordBreak = true;
            return;
        }
        for (int i = start; i < s.length(); i++) {
            String word = s.substring(start, i + 1);
            if (!wordDict.contains(word)) {
                continue;
            }
            // 不能直接return返回，否则会导致for后面的遍历跳过
            dfs(s, i + 1, wordDict);
        }
    }


//    public static void main(String[] args) {
//        System.out.println(wordBreak("catsandog", Lists.newArrayList("cats","dog","sand","and","cat")));
//    }


    /**
     * 143.重排链表 L0 → L1 → … → Ln - 1 → Ln  ===》 L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 →
     *
     * @param head 2024-02-21
     */
    public static void reorderList_143(ListNode head) {
        // 涉及到链表3个知识：先通过快慢指针找到中间点、再反转后半部分链表、最后做前后插入
        // 1、找到中间节点
        ListNode middle = findMiddle(new ListNode(0, head));
        // 2、反转slow后到到尾部链表
        ListNode last = reversal(middle);
        // 3、插入
        insertLast(head, last);
    }
    private static ListNode findMiddle(ListNode dummy){
        ListNode fast = dummy;
        ListNode slow = dummy;
        while (fast.next != null) {
            if (fast.next.next != null) {
                fast = fast.next.next;
            } else {
                fast = fast.next;
            }
            slow = slow.next;
        }
        return slow;
    }
    private static ListNode reversal(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode cur = head;
        // 反转pre节点设置为null，否则会进入节点反向依赖
        ListNode pre = null;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
    private static void insertLast(ListNode head, ListNode last) {
        ListNode node = head;
        while (node != null) {
            ListNode cur = node.next;
            node.next = last;
            ListNode temp = last;
            last = last.next;
            temp.next = cur;
            node = cur;
        }
    }

//    public static void main(String[] args) {
//        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
//        reorderList(head);
//        while (head != null) {
//            System.out.println(head.val);
//            head = head.next;
//        }
//    }

    /**
     * 142. 给定一个链表的头节点 head ，返回链表开始入环的第一个节点。 如果链表无环，则返回 null
     *
     * @param head 2024-02-21
     * @return
     */
    public ListNode detectCycle_142(ListNode head) {
        // 1、哈希表
        ListNode pos = head;
        Set<ListNode> visited = new HashSet<>();
        while (pos != null) {
            if (visited.contains(pos)) {
                return pos;
            } else {
                visited.add(pos);
            }
            pos = pos.next;
        }

        // 2、快慢指针
        return null;
    }

    /**
     * 137.给你一个整数数组 nums ，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次
     *
     * @param nums 2024-02-22
     * @return 返回那个只出现了一次的元素
     */
    public int singleNumber_137(int[] nums) {
        // 实现线性时间复杂度的算法且使用常数级空间来解决此问题
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        int ans = 0;
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            int num = entry.getKey(), occ = entry.getValue();
            if (occ == 1) {
                ans = num;
                break;
            }
        }
        return ans;
    }

    /**
     * 131.给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是回文串
     * <p>
     * s = "aab" ==》[["a","a","b"],["aa","b"]]
     *
     * @param s 2024-02-20
     * @return s 所有可能的分割方案
     */

    boolean[][] f;
    List<List<String>> ret = new ArrayList<>();
    List<String> ans = new ArrayList<>();
    int n;

    public List<List<String>> partition_131(String s) {
        n = s.length();
        f = new boolean[n][n];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(f[i], true);
        }
        // 两层循环：遍历所有[i,j]节点，同时满足i+1和j-1依次从大到小和从小到大
        for (int i = n - 1; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                f[i][j] = (s.charAt(i) == s.charAt(j)) && f[i + 1][j - 1];
            }
        }
        // 深度递归：字符串解析成一层一层的树结构，然后从上到下递归遍历。注意要移除前一棵树枝的节点
        dfs(s, 0);
        return ret;
    }

    private void dfs(String s, int i) {
        if (i == n) {
            ret.add(new ArrayList<>(ans));
            return;
        }
        // for循环下一个树节点是从上一个的尾开始
        for (int j = i; j < n; ++j) {
            if (f[i][j]) {
                ans.add(s.substring(i, j + 1));
                dfs(s, j + 1);
                // ans作为唯一一个list存储，下一层节点结束的时候要移除再返回上一层
                ans.remove(ans.size() - 1);
            }
        }
    }

//    public static void main(String[] args) {
//        String s = "aab";
//        partition_131(s);
//    }

    /**
     * 128.给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度
     * [100,4,200,1,3,2] ==》 4 [1, 2, 3, 4]
     *
     * @param nums 2024-02-20
     * @return O(n) 的算法解决此问题
     */
    public int longestConsecutive_128(int[] nums) {
        // 连续数字..（遇到一下子没思路的题的时候可以尝试从暴力解法入手）
        Map<Integer, Boolean> map = new HashMap<>();
        for (int j : nums) {
            map.put(j, true);
        }
        // 都看后面数字是否存在
        int max = 0;

        for(Map.Entry<Integer,Boolean> entry:map.entrySet()){
            Integer num = entry.getKey();
            int length = 1;
            int stack = num;
            if (!map.containsKey(num - 1)) {
                while (map.containsKey(stack + 1)) {
                    length = length + 1;
                    stack = stack + 1;
                }
            }
            max = Math.max(max, length);
        }
        return max;
    }

//    public static void main(String[] args) {
//        int[] nums = {100,4,200,1,3,3,2};
//        LcTop202305 lcTop202305 = new LcTop202305();
//        System.out.println(lcTop202305.longestConsecutive(nums));
//    }


    /**
     * 122.整数数组 prices ，其中 prices[i] 表示某支股票第 i 天的价格
     * <p>
     * [7,1,5,3,6,4] ==> 7（1-5，3-6）
     * [1,2,3,4,5] ==> 4（1买5卖）
     * [7,6,4,3,1] ==> 0
     *
     * @param prices 2024-02-20
     * @return 最大利润
     */
    public int maxProfit_122(int[] prices) {
        // 在每一天，你可以决定是否购买和/或出售股票。你在任何时候最多只能持有一股股票
        // 动态规划：dp[i] = dp[i-1] + prices[i]
        int[] dp = new int[prices.length];
        dp[0] = 0;
        for (int i = 1; i < prices.length; i++) {
            dp[i] = dp[i - 1] + Math.max(0, prices[i] - prices[i - 1]);
        }
        return dp[prices.length - 1];
    }

//    public static void main(String[] args) {
//        LcTop202305 lcTop202305 = new LcTop202305();
//        int[] prices = {7,6,4,3,1};
//        System.out.println(lcTop202305.maxProfit(prices));
//    }


    /**
     * 120.三角形 triangle ，找出自顶向下的最小路径和，每一步只能移动到下一行中相邻的结点上
     * 如：triangle = [[2],[3,4],[6,5,7],[4,1,8,3]] ==> 11（2-3-5-1）
     *
     * @param triangle 2024-02-19
     * @return
     */
    public int minimumTotal_120(List<List<Integer>> triangle) {
        // 相邻结点即如果正位于当前行的下标 i 那么下一步可以移动到下一行的下标 i 或 i + 1
        if (triangle == null || triangle.size() == 0) {
            return 0;
        }
        // 最优解法：1、动态规划时间复杂度高；2、
        int columns = triangle.size();
        int row = triangle.get(columns - 1).size();
        int[][] dp = new int[row][columns];
        for (int i = 0; i < columns; i++) {
            List<Integer> c_list = triangle.get(i);
            for (int j = 0; j < triangle.get(i).size(); j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = c_list.get(j);
                } else if (j == 0) {
                    // 即最左边数字来源只能是正上
                    dp[i][j] = dp[i - 1][j] + c_list.get(j);
                } else if (j + 1 > triangle.get(i - 1).size()) {
                    // 即正上没值，只能走斜上
                    dp[i][j] = dp[i - 1][j - 1] + c_list.get(j);
                } else {
                    // 即(i,j)点只能是 正上(i-1,j) 或 斜上(i-1,j-1) 来的
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - 1]) + c_list.get(j);
                }
            }
        }
        int result = dp[columns - 1][0];
        for (int k = 1; k < row; k++) {
            result = Math.min(dp[columns - 1][k], result);
        }
        return result;
    }

//    public static void main(String[] args) {
//        List<Integer> l1 = Lists.newArrayList(2);
//        List<Integer> l2 = Lists.newArrayList(3,4);
//        List<Integer> l3 = Lists.newArrayList(6,5,7);
//        List<Integer> l4 = Lists.newArrayList(4,1,8,3);
//        List<List<Integer>> triangle = Lists.newArrayList(l1,l2,l3,l4);
//        LcTop202305 lcTop202305 = new LcTop202305();
//        System.out.println(lcTop202305.minimumTotal(triangle));
//    }

    /**
     * 103. root返回其节点值的锯齿形层序遍历
     * root = [3,9,20,null,null,15,7] ==》[[3],[20,9],[15,7]]
     *
     * @param root 2024-02-19
     * @return
     */
    public static List<List<Integer>> zigzagLevelOrder_103(TreeNode root) {
        List<List<Integer>> ans = new LinkedList<>();
        if (root == null) {
            return ans;
        }
        // 用了一个双向队列Deque存储结果：都是从左到右遍历然后利用双端队列选择插左还是插右
        Queue<TreeNode> nodeQueue = new ArrayDeque<>();
        nodeQueue.offer(root);
        boolean isOrderLeft = true;
        while (!nodeQueue.isEmpty()) {
            Deque<Integer> levelList = new LinkedList<>();
            int size = nodeQueue.size();
            for (int i = 0; i < size; ++i) {
                TreeNode curNode = nodeQueue.poll();
                if (isOrderLeft) {
                    levelList.offerLast(curNode.val);
                } else {
                    levelList.offerFirst(curNode.val);
                }
                if (curNode.left != null) {
                    nodeQueue.offer(curNode.left);
                }
                if (curNode.right != null) {
                    nodeQueue.offer(curNode.right);
                }
            }
            ans.add(new LinkedList<>(levelList));
            isOrderLeft = !isOrderLeft;
        }
        return ans;
//        boolean left = false;
//        List<List<Integer>> result = new ArrayList<>();
//        if (root == null) {
//            return result;
//        }
//        Queue<TreeNode> queue = new ArrayDeque<>();
//        queue.add(root);
//        int size = 1;
//        List<Integer> nodeList = new ArrayList<>();
//        while (!queue.isEmpty()) {
//            TreeNode node = queue.poll();
//            nodeList.add(node.val);
//            TreeNode rightNode = node.right;
//            TreeNode leftNode = node.left;
//            if (left) {
//                if (leftNode != null) {
//                    queue.add(leftNode);
//                }
//                if (rightNode != null) {
//                    queue.add(rightNode);
//                }
//            } else {
//                if (rightNode != null) {
//                    queue.add(rightNode);
//                }
//                if (leftNode != null) {
//                    queue.add(leftNode);
//                }
//            }
//            size--;
//            if (size == 0) {
//                // 上一层结束开始新的一层
//                size = queue.size();
//                left = !left;
//                result.add(nodeList);
//                nodeList = new ArrayList<>();
//            }
//        }
//        return result;
    }

//    public static void main(String[] args) {
//        TreeNode root3 = new TreeNode(4);
//        TreeNode root4 = new TreeNode(5);
//        TreeNode root1 = new TreeNode(2, root3, null);
//        TreeNode root2 = new TreeNode(3, null, root4);
//        TreeNode root = new TreeNode(1, root1, root2);
//        List<List<Integer>> result = zigzagLevelOrder_103(root);
//        System.out.println(JSON.toJSONString(result));
//    }

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
        // *** 核心在于用 树型结构 深度递归 ***
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

//    public static void main(String[] args) {
//        Top top = new Top();
//        System.out.println(top.combine_77(5, 2));
//    }

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
                    // row == 0的必定是最顶上横着移动
                    dp[i][j] = dp[i][j - 1] + grid[i][j];
                } else if (j == 0) {
                    // columns == 0的必定是最左上横着移动
                    dp[i][j] = dp[i - 1][j] + grid[i][j];
                } else {
                    // 每次过来要么是从上要么是从左，其实和背包问题有异曲同工之妙
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
                }
                System.out.println("i=" + i + " j=" + j + " dp=" + dp[i][j]);
            }
        }
        return dp[grid.length - 1][grid[0].length - 1];
    }

//    public static void main(String[] args) {
//        LcTop202305 lcTop202305 = new LcTop202305();
//        int[][] grid = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
//        System.out.println(lcTop202305.minPathSum_64_ww(grid));
//    }

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
            // 第3步：开始循环的下一个节点前把前面的移除了，因为这个节点已经添加到结果列表了
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
        // *** 简单来说是dp[i][j]是否满足回文，然后再根据s[i-1]==s[j+1]来依次判断dp[i-1][j+1]是否满足回文 ***
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
                        // 外面的看里面的是否回文。所以L循环得在外面保证L大的能兼容L小的
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

//    public static void main(String[] args) {
//        Top top = new Top();
//        System.out.println(top.longestPalindrome_dp("afabccba"));
//    }

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
