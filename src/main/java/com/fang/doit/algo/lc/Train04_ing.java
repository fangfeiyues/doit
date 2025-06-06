package com.fang.doit.algo.lc;
import com.fang.doit.algo.classes.linked.ListNode;

import java.util.*;

/**
 * @author : fangfeiyue
 * @version V1.0
 * @Project: doit
 * @Package com.fang.doit.algo.lc
 * @Description:
 * @date Date : 2025-05-16 10:01
 */
public class Train04_ing {


    //  ------------ ！！！2025.05月～06月，一场漫长的战役，摆正心态、积极面对 ！！！------------


    /**
     * 43.字符串相乘，给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
     * <p>
     * 注意：不能使用任何内置的 BigInteger 库或直接将输入转换为整数。
     * 输入: num1 = "2", num2 = "3"
     * 输出: "6"
     * <p>
     * 输入: num1 = "123", num2 = "456"
     * 输出: "56088"
     *
     * @param num1
     * @param num2
     * @return
     */
    public String multiply(String num1, String num2) {
        // 1. 先判断是否有0
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        // 2. 初始化结果数组，长度为两个字符串长度之和
        int[] result = new int[num1.length() + num2.length()];
        // 3. 从后往前遍历两个字符串
        for (int i = num1.length() - 1; i >= 0; i--) {
            for (int j = num2.length() - 1; j >= 0; j--) {
                // 4. 计算当前位的乘积
                int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                // 5. 累加到结果数组中
                int sum = mul + result[i + j + 1];
                result[i + j + 1] = sum % 10; // 当前位
                result[i + j] += sum / 10; // 进位
            }
        }
        // 6. 构建结果字符串
        StringBuilder sb = new StringBuilder();
        for (int num : result) {
            if (!(sb.length() == 0 && num == 0)) { // 跳过前导0
                sb.append(num);
            }
        }
        // 7. 如果结果字符串为空，说明结果为0
        if (sb.length() == 0) {
            return "0";
        }
        // 8. 返回结果字符串
        return sb.toString();
    }

    /**
     * 41. 缺失的第一个正数
     *
     * 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
     *
     * 请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
     *
     * 输入：nums = [1,2,0]
     * 输出：3
     * 解释：范围 [1,2] 中的数字都在数组中。
     * 示例 2：
     *
     * 输入：nums = [3,4,-1,1]
     * 输出：2
     * 解释：1 在数组中，但 2 没有。
     * 示例 3：
     *
     * 输入：nums = [7,8,9,11,12]
     * 输出：1
     * 解释：最小的正数 1 没有出现。
     * @param nums
     * @return
     */
    public int firstMissingPositive(int[] nums) {

        return 0;
    }


    /**
     * 37 解独数
     *
     * 编写一个程序，通过填充空格来解决数独问题。
     * 数独的解法需 遵循如下规则：
     * 数字 1-9 在每一行只能出现一次。
     * 数字 1-9 在每一列只能出现一次。
     * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图）
     * 数独部分空格内已填入了数字，空白格用 '.' 表示
     *
     *
     *
     * @param board
     */
    public void solveSudoku(char[][] board) {

    }

    /**
     * ：给定一个整数数组 nums，请你找到其中的最长递增子序列（LIS），并返回其长度。
     *
     * 递增子序列 的定义是：子序列中每个元素都比前一个元素大，且这些元素在原数组中是连续的或不连续的。
     * 示例 1：
     * 输入：nums = [10, 9, 2, 5, 3, 7, 101, 18]
     * 输出：4
     * 解释：最长递增子序列是 [2, 3, 7, 101]，其长度为 4。
     * 示例 2：
     * 输入：nums = [0, 1, 0, 3, 2, 3]
     * 输出：4
     * 解释：最长递增子序列是 [0, 1, 2, 3]，其长度为 4。
     * 示例 3：
     * 输入：nums = [7, 7, 7, 7, 7, 7, 7]
     * 输出：1
     * 解释：所有元素都相等，最长递增子序列的长度为 1。
     */
//    public static int findMaxInc(int[] nums) {
//
//        // 10, 9, 2, 5, 3, 7, 101, 180 --> 2,3,7,101
//        // 10,
//        // 9
//        // 2
//        // 2、5
//        // 2、3
//        // 2、3、7
//        // 2、3、7、101
//
//        int max = 1;
//        Deque<Integer> deque = new ArrayDeque<>();
//        for (int num : nums) {
//            if(deque.contains(num)){
//                continue;
//            }
//            // 10, 9, 2, 5, 3, 7, 101, 18,19 --> 2,3,7,101
//            while (!deque.isEmpty() && deque.peekLast() >= num) {
//                deque.pollLast();
//            }
//            deque.addLast(num);
//            System.out.println(JSON.toJSON(deque));
//            max = Math.max(max, deque.size());
//        }
//        return max;
//    }
    public static int lengthOfLIS(int[] nums) {
        // 动态规划 或者 深度遍历（超出限制..）
        if (nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int maxans = 1;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                // 从之前的数字里，找到max+1
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxans = Math.max(maxans, dp[i]);
        }
        return maxans;
    }


    public static int lengthOfLIS_V2(int[] nums) {
        // 超出限制
        if (nums.length == 0) {
            return 0;
        }
        int max = 1;
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(max, lengthOfLISDFS(nums, i, 1));
        }
        return max;
    }

    public static int lengthOfLISDFS(int[] nums, int i, int count) {
        if (i == nums.length) {
            return count;
        }
        int max = count;
        for (int j = i + 1; j < nums.length; j++) {
            if (nums[j] > nums[i]) {
                max = Math.max(max, lengthOfLISDFS(nums, j, count + 1));
            }
        }
        return max;
    }

//    public static void main(String[] args) {
//        System.out.println(lengthOfLIS_V2(new int[]{10,9,2,5,3,7,101,18}));
//    }

    /**
     * 313. 超级丑数是一个正整数，并满足其所有质因数都出现在质数数组 primes 中，给你一个整数 n 和一个整数数组 primes ，返回第 n 个 超级丑数
     * <p>
     * n = 12, primes = [2,7,13,19] ==》32 ，12 个超级丑数序列为：[1,2,4,7,8,13,14,16,19,26,28,32]
     *
     * @param n
     * @param primes
     * @return
     */
    public static int nthSuperUglyNumber(int n, int[] primes) {
        // 重点在于，动态规划

        // 值：找到最小值作为一次输出，然后更新
        // 1 -> 2、7、13、19 -> 4、7、13、19 -> 8、7、13、19、-> 8、14、13、19 -> 16、14、13、19 -> 16,14,26,19 -> 16,28,26,19 -> 32,28,26,19

        // 作为每次取值后，更新的最新大小情况
        int minpPimes = Arrays.stream(primes).min().getAsInt();
        int[] nums = Arrays.copyOf(primes, primes.length);
        // 纯粹记录每一次的最小值
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            int min = Arrays.stream(nums).min().getAsInt();
            dp[i] = min;
            // 更新 nums
            for (int k = 0; k < nums.length; k++) {
                if (min == nums[k]) {
                    nums[k] = min * minpPimes;
                }
            }
        }

        // 值：找到最小值作为一次输出
//        int[] nums = Arrays.copyOf(primes, primes.length);
//        // 次数：prime乘积的次数
//        int[] p = new int[primes.length];
//        // 丑数
//        int[] dp = new int[n];
//        dp[0] = 1;
//        for (int i = 1; i < n; i++) {
//            int min = Arrays.stream(nums).min().getAsInt();
//            dp[i] = min;
//            for (int j = 0; j < primes.length; j++) {
//                if (min == nums[j]) {
//                    p[j]++;
//                    // 关键点：p点的下一位乘积，比如现在 p 乘了2次，这次又是最小值，那么下一次就是 3 * primes[j] 作为 nums[p] 的值
//                    // p[j]: 1 ，j位置只+1，
//                    // dp[p[j]]：2 ，dp[1] = 2
//                    // primes[j]:7 ，j=1
//                    nums[j] = dp[p[j]] * primes[j];
//                }
//            }
//        }
        return dp[n - 1];
    }

//    public static void main(String[] args) {
//
//        System.out.println(nthSuperUglyNumber(12, new int[]{2, 7, 13, 19}));
//
//    }

    /**
     * 40.给定一个候选人编号的集合 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
     * candidates 中的每个数字在每个组合中只能使用 一次 。
     *
     * 注意：解集不能包含重复的组合。
     *
     * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
     * 输出:
     * [
     * [1,1,6],
     * [1,2,5],
     * [1,7],
     * [2,6]
     * ]
     * 示例 2:
     *
     * 输入: candidates = [2,5,2,1,2], target = 5,
     * 输出:
     * [
     * [1,2,2],
     * [5]
     * ]
     *
     * @param candidates
     * @param target
     * @return
     */
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        // 深度遍历，主要是判断重复的情况
        Arrays.sort(candidates); // 排序，方便后面判断重复
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < candidates.length; i++) {
            // 排除重复的情况
            if (i > 0 && candidates[i] == candidates[i - 1]) {
                continue;
            }
            List<Integer> list = new ArrayList<>();
            list.add(candidates[i]);
            combinationSum2DFS(candidates, target, i, candidates[i], list, res);
        }
        return res;
    }

    private static void combinationSum2DFS(int[] candidates, int target, int i, int candidate, List<Integer> list, List<List<Integer>> res) {
        if (candidate == target) {
            res.add(new ArrayList<>(list));
            return;
        }
        if (candidate > target) {
            return; // 超过目标值，直接返回
        }
        // 深度遍历，主要是判断重复的情况
        for (int j = i + 1; j < candidates.length; j++) {
            // 排除重复的情况（少了 j > i + 1） 1 2 2 2 5 不能连着来？
            if (j > i + 1 && candidates[j] == candidates[j - 1]) {
                continue;
            }
            list.add(candidates[j]);
            combinationSum2DFS(candidates, target, j, candidate + candidates[j], list, res);
            list.remove(list.size() - 1);
        }
    }


//    public static void main(String[] args) {
//
//         System.out.println(JSON.toJSONString(combinationSum2(new int[]{10, 1, 2, 7, 6, 1, 5}, 8)));
//
//        // System.out.println(JSON.toJSONString(combinationSum2(new int[]{2, 5, 2, 1, 2}, 5)));
//    }


    public static List<List<Integer>> combinationSum2ByWindow(int[] candidates, int target){
        // 排序后滑动窗口
        Arrays.sort(candidates);

        // 1 2 2 2 5 -> 排序后的滑动窗口香啊！！！
        List<List<Integer>> res = new ArrayList<>();
        int left = 0, right = 0,sum = 0;
        while (right < candidates.length) {
            sum = sum + candidates[left];
            if (sum >= target) {
                if(sum == target){
                    List<Integer> list = new ArrayList<>();
                    for (int i = left; i <= right; i++) {
                        list.add(candidates[i]);
                    }
                    res.add(list);
                }
                left++;
            }
            right++;
        }
        return res;
    }

    /**
     * 36.有效的数独，请你判断一个 9 x 9 的数独是否有效。只需要根据以下规则，验证已经填入的数字是否有效即可
     * <p>
     * 数字 1-9 在每一行只能出现一次
     * 数字 1-9 在每一列只能出现一次
     * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次
     *
     * @param board
     * @return
     */
    public boolean isValidSudoku(char[][] board) {
        // 重点在于，见识到二维数组的威力，荜动态规划好使

        // 1. 使用三个数组分别记录行、列和宫内的数字出现情况
        boolean[][] rows = new boolean[9][9];
        boolean[][] cols = new boolean[9][9];
        boolean[][] boxes = new boolean[9][9];
        // 2. 遍历数独
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char num = board[i][j];
                if (num == '.') {
                    continue; // 跳过空格
                }
                int n = num - '1'; // 将字符转换为数字
                // 计算宫内索引，可都定位到 3*3宫格的某一个点如[3,0]，所以这里只要保证宫内所有的点可以汇聚一个即可
                int boxIndex = (i / 3) * 3 + (j / 3);
                // 3. 检查行、列和宫内是否已经存在该数字（强中自有强中手！！！）
                if (rows[i][n] || cols[j][n] || boxes[boxIndex][n]) {
                    return false; // 如果存在，返回 false
                }

                // 4. 标记该数字已出现
                rows[i][n] = true;
                cols[j][n] = true;
                boxes[boxIndex][n] = true;
            }
        }
        // 5. 如果没有冲突，返回 true
        return true;
    }


    /**
     * 30. 串联所有单词的子串
     * <p>
     * 给定一个字符串 s 和 一个字符串数组 words，words 中所有字符串长度相同，s 中的串联子串是指一个包含 words 中所有字符串以任意顺序排列连接起来的子串
     * <p>
     * 例如，如果 words = ["ab","cd","ef"]， 那么 "abcdef"， "abefcd"，"cdabef"， "cdefab"，"efabcd"， 和 "efcdab" 都是串联子串
     * "acdbef" 不是串联子串，因为他不是任何 words 排列的连接。
     * 返回所有串联子串在 s 中的开始索引。你可以以 任意顺序 返回答案
     *
     * <p>
     * 输入：s = "barfoothefoobarman", words = ["foo","bar"]
     * 输出：[0,9]
     * 解释：因为 words.length == 2 同时 words[i].length == 3，连接的子字符串的长度必须为 6
     * 子串 "barfoo" 开始位置是 0。它是 words 中以 ["bar","foo"] 顺序排列的连接
     * 子串 "foobar" 开始位置是 9。它是 words 中以 ["foo","bar"] 顺序排列的连接
     * 输出顺序无关紧要。返回 [9,0] 也是可以的
     *
     * @param s
     * @param words
     * @return
     */
    public List<Integer> x_findSubstring(String s, String[] words) {

        // 滑动窗口：遍历字符，找到长度等于 words 拼接起来的区间，然后通过两者字符的数量对比，确定是否可相同

        // 1. 先把所有的单词放入一个 map 中，key 为单词，value 为出现的次数
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        // 2. 计算单词的长度和总长度
        int wordLen = words[0].length();
        int totalLen = wordLen * words.length;
        List<Integer> res = new ArrayList<>();
        // 3. 遍历字符串，判断每个字符是否在 map 中
        for (int i = 0; i <= s.length() - totalLen; i++) {
            // 4. 如果在 map 中，判断是否是一个有效的子串
            if (isValidSubstring(s.substring(i, i + totalLen), map, wordLen)) {
                res.add(i);
            }
        }
        return res;
    }

    private boolean isValidSubstring(String substring, Map<String, Integer> map, int wordLen) {
        // 1. 创建一个新的 map，用于存储当前子串的单词
        Map<String, Integer> tempMap = new HashMap<>();
        for (int i = 0; i < substring.length(); i += wordLen) {
            String word = substring.substring(i, i + wordLen);
            // 2. 如果单词不在 map 中，返回 false
            if (!map.containsKey(word)) {
                return false;
            }
            // 3. 如果单词在 map 中，更新 tempMap 中的单词出现次数
            tempMap.put(word, tempMap.getOrDefault(word, 0) + 1);
        }
        // 4. 判断 tempMap 中的单词出现次数是否和 map 中的单词出现次数相同
        for (String key : map.keySet()) {
            if (!Objects.equals(map.get(key), tempMap.get(key))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 32.最长有效括号,给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
     * <p>
     * 输入：s = "(()"
     * 输出：2
     * 解释：最长有效括号子串是 "()"
     * <p>
     * 输入：s = ")()())"
     * 输出：4
     * 解释：最长有效括号子串是 "()()"
     *
     * @param s
     * @return
     */
    public static int x_longestValidParentheses(String s) {
        // 堆栈队列：从"("开始，栈道内的 "(" 得大于 ")"，但堆栈不好分段统计最长
        // 滑动窗口：窗口内满足条件的，也不知道下一个左出还是右进
        // 动态规划：dp[i] 代表以 i 结尾的最长有效括号长度

        // 1. 初始化 dp 数组
        int[] dp = new int[s.length()];
        int max = 0;
        // 2. 遍历字符串
        for (int i = 1; i < s.length(); i++) {
            // 3. 如果是右括号
            if (s.charAt(i) == ')') {
                // 4. 如果前一个是左括号，说明可以组成有效括号
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;

                } else if (s.charAt(i - 1) == ')') {
                    // 5. 如果前一个是右括号，说明需要判断前面，在有效长度之外的前一个，是否是是'('
                    // 比如 ( ()()()(()) ) 在最后一个是 ）的情况下，看第一个是否是(，如是则能形成有效的长度
                    if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                        // i >= dp[i - 1] + 2 说明前面还有有效括号
                        // dp[i - dp[i - 1] - 2] 代表在有效括号之前的有效括号长度
                        // dp[i - 1] + 2 代表当前的有效括号长度
                        // 总结来说，就是在新加两个长度后，还要判断再前面可不可以一起连起来，就是这个位置 dp[i - dp[i - 1] - 2]，妙啊！！！
                        dp[i] = dp[i - 1] + (i >= dp[i - 1] + 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                    }
                }
                max = Math.max(max, dp[i]);
            }
        }
        // 6. 返回最大值
        return max;
    }

//    public static void main(String[] args) {
//        System.out.println(longestValidParentheses(")()()()"));
//    }

    /**
     * 23.给你一个链表数组，每个链表都已经按升序排列，请你将所有链表合并到一个升序链表中，返回合并后的链表
     * <p>
     * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
     * 输出：[1,1,2,3,4,4,5,6]
     * 解释：链表数组如下：[1->4->5,1->3->4,2->6]  将它们合并到一个有序链表中得到 1->1->2->3->4->4->5->6
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        // 逐步合并两个链表即可、
        if (lists == null || lists.length == 0) {
            return null;
        }
        ListNode res = lists[0];
        for (int i = 1; i < lists.length; i++) {
            res = mergeNode(res, lists[i]);
        }
        return res;
    }

    private ListNode mergeNode(ListNode node1, ListNode node2) {
        // 重点在于，这里有3个标签，两个链表的以及合并后总链的
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (node1 != null && node2 != null) {
            if (node1.val < node2.val) {
                cur.next = node1;
                node1 = node1.next;
            } else {
                cur.next = node2;
                node2 = node2.next;
            }
            cur = cur.next;
        }
        if (node1 != null) {
            cur.next = node1;
        } else {
            cur.next = node2;
        }
        return dummy.next;
    }



    /**
     * 25.给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表
     * <p>
     * k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序
     * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换
     *
     * <p>
     * 输入：head = [1,2,3,4,5], k = 2  ==> 输出：[2,1,4,3,5]
     * 输入：head = [1,2,3,4,5], k = 3  ==> 输出：[3,2,1,4,5]
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        // 1. 先遍历链表，找到长度
        ListNode cur = head;
        int len = 0;
        while (cur != null) {
            len++;
            cur = cur.next;
        }

        // 2. 计算需要翻转的次数
        int count = len / k;

        // 3. 遍历翻转
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode end;
        ListNode start;
        for (int i = 0; i < count; i++) {
            // 4. 找到翻转的起始和结束节点
            start = pre.next;
            end = pre;
            for (int j = 0; j < k; j++) {
                end = end.next;
            }
            // 5. 翻转，end 的下一个 next 在翻转之后的 pre 就是 start 了
            ListNode next = end.next;
            end.next = null;
            pre.next = reverseNode(start);
            start.next = next;
            pre = start;
        }

        // 6. 返回头节点
        return dummy.next;
    }

    public ListNode reverseNode(ListNode start) {
        // 1 -> 2 -> 3 ==》 1 <- 2 <- 3 把箭头反着过来
        ListNode pre = null;
        ListNode cur = start;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

//    public static void main(String[] args) {
//
//        Train04 train = new Train04();
//        train.reverseNode(new ListNode(1,new ListNode(2,new ListNode(3,new ListNode(4,new ListNode(5)))))).print();
//
//    }


    /**
     * 325.给定一个数组 nums 和一个目标值 k，找到和等于 k 的最长连续子数组长度。如果不存在任意一个符合要求的子数组，则返回 0
     * <p>
     * nums = [1,-1,5,-2,3], k = 3 ==》4 [1,-1,5,-2]
     *
     * @param nums
     * @param k
     * @return
     */
    public int maxSubArrayLen(int[] nums, int k) {
        // 前缀和，如果两个点之和为k，那么之间数组即满足条件
        HashMap<Integer, Integer> sumMap = new HashMap<>();
        int sum = 0, max = 0;
        for (int i = 0; i < nums.length; i++) {
            sum = sum + nums[i];
            int num = sum - k;
            if (sumMap.containsKey(num)) {
                max = Math.max(max, Math.abs(i - sumMap.get(num))) + 1;
            }
            sumMap.put(sum, i);
        }
        return max;
    }

//    public static void main(String[] args) {
//        Train04 train04 = new Train04();
//        System.out.println(train04.maxSubArrayLen(new int[]{1, -1, 5, -2, 3}, 3));
//    }


    /**
     * 22. 括号生成，数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且有效的括号组合
     * <p>
     * 输入：n = 3
     * 输出：["((()))","(()())","(())()","()(())","()()()"]
     * <p>
     * 输入：n = 1
     * 输出：["()"]
     *
     * @param n
     * @return
     */
    public List<String> x_generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        generateParenthesisDFS(res, new StringBuilder(), 0, 0, n);
        return res;
    }

    private void generateParenthesisDFS(List<String> res, StringBuilder sb, int open, int close, int n) {
        if (sb.length() == n * 2) {
            res.add(sb.toString());
            return;
        }
        if (open < n) {
            sb.append("(");
            generateParenthesisDFS(res, sb, open + 1, close, n);
            sb.deleteCharAt(sb.length() - 1);
        }
        // 重点在于，这里 close 要小于 open，而不是 close 小于 n 否则会造成 close > open 的情况
        if (close < open) {
            sb.append(")");
            generateParenthesisDFS(res, sb, open, close + 1, n);
            sb.deleteCharAt(sb.length() - 1);
        }
    }


//    public static void main(String[] args) {
//        Train04 train04 = new Train04();
//        System.out.println(train04.generateParenthesis(3));
//    }

    /**
     * 395.给你一个字符串 s 和一个整数 k ，请你找出 s 中的最长子串，要求该子串中的每个字符出现次数都不少于 k
     * <p>
     * s = "aaabb", k = 3 ==》3  aaa
     * s = "ababbc", k = 2 ==》5  ababb
     *
     * @param s
     * @param k
     * @return
     */
    public static int longestSubstring(String s, int k) {

        // 滑动窗口不行，内进出数量不好控制

        // 切割术可行，找到不满足条件的字符左右切割

        if (s == null || s.length() == 0) {
            return 0;
        }
        Map<Character, Integer> counter = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            counter.put(s.charAt(i), counter.getOrDefault(s.charAt(i), 0) + 1);
        }

        int max = 0;
        for (Map.Entry<Character, Integer> entry : counter.entrySet()) {
            Integer count = entry.getValue();
            Character ch = entry.getKey();
            if (count < k) {
                for (String ss : s.split(ch.toString())) {
                    max = Math.max(longestSubstring(ss, k), max);
                }
                return max;
            }
        }
        return s.length();
    }

//    public static void main(String[] args) {
//        System.out.println(longestSubstring("ababbc",2));
//    }

    /**
     *  31. 整数数组的一个 排列  就是将其所有成员以序列或线性顺序排列。 指其整数的下一个字典序更大的排列
     *
     *  例如，arr = [1,2,3] 的下一个排列是 [1,3,2] 。
     *  类似地，arr = [2,3,1] 的下一个排列是 [3,1,2] 。
     *  而 arr = [3,2,1] 的下一个排列是 [1,2,3] ，因为 [3,2,1] 不存在一个字典序更大的排列。
     *  给你一个整数数组 nums ，找出 nums 的下一个排列。
     *
     *  必须 原地 修改，只允许使用额外常数空间。
     *
     *  输入：nums = [1,2,3]
     *  输出：[1,3,2]
     *  示例 2：
     *
     *  输入：nums = [3,2,1]
     *  输出：[1,2,3]
     *  示例 3：
     *
     *  输入：nums = [1,1,5]
     *  输出：[1,5,1]
     *
     *
     * @param nums
     */
    public void x_nextPermutation(int[] nums) {
        // 1. 从后往前遍历，找到第一个升序对
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        // 2. 如果没有升序对，说明已经是最大排列，直接反转
        if (i < 0) {
            reverseNums(nums, 0, nums.length - 1);
            return;
        }
        // 3. 找到第一个比nums[i]大的数，交换
        int j = nums.length - 1;
        while (j >= 0 && nums[j] <= nums[i]) {
            j--;
        }
        swap(nums, i, j);
        // 4. 反转i+1到最后的元素（说明i+1后的都是递减的那么只要直接反转就行）
        reverseNums(nums, i + 1, nums.length - 1);
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void reverseNums(int[] nums, int i, int i1) {
        while (i < i1) {
            swap(nums, i++, i1--);
        }
    }


    /**
     * 66. 给定一个整数数组 nums ，返回 所有可能的子集（幂集）。
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> subsets(int[] nums) {
        // 加入空集合
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        res.add(list);

        for(int i =0;i<nums.length;i++){
            // 往后深度遍历数字，如[1],[1,2],[1,2,3],[1,2,3,4] [1,3],[1,3,4],[1,4] ..
            list = new ArrayList<>();
            list.add(nums[i]);
            res.add(list);
            subsetsDFS(nums,i,res,list);
        }
        return res;
    }

    private static void subsetsDFS(int[] nums, int i, List<List<Integer>> res, List<Integer> list) {
        if (i == nums.length - 1) {
            return;
        }
        for (int j = i + 1; j < nums.length; j++) {
            // 把每一次的遍历结果加入到结果集
            List<Integer> list1 = new ArrayList<>(list);
            list1.add(nums[j]);
            res.add(list1);
            subsetsDFS(nums, j, res, list1);
            // 移出上一轮的
//			list.remove(list.size() - 1);
        }

    }

//    public static void main(String[] args) {
//        Train04 train04 = new Train04();
//        train04.subsets(new int[]{1,2,3}).stream().forEach(System.out::println);
//    }



}
