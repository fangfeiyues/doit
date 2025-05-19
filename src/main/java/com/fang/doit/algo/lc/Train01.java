package com.fang.doit.algo.lc;

import com.fang.doit.algo.classes.linked.ListNode;
import com.fang.doit.algo.classes.tree.TreeNode;
import com.fang.doit.design.lru.LFUCache;
import com.fang.doit.design.serialize.Codec;
import com.google.common.collect.Lists;

import java.util.*;

/**
 * @author : fangfeiyue
 * @version V1.0
 * @Project: doit
 * @Package com.fang.doit.algo.lc.test
 * @Description: 2023.05~2024.05刷题记录
 * @date Date : 2023-05-07 4:21 下午
 */
public class Train01 {


    /**
     * 473. 一个整数数组 matchsticks ，其中 matchsticks[i] 是第 i 个火柴棒的长度，你要用所有的火柴棍拼成一个正方形，不能折断任何一根火柴棒，但可以把它们连在一起，而且每根火柴棒必须使用一次
     * <p>
     * matchsticks = [1,1,2,2,2] ==> true
     * matchsticks = [3,3,3,3,4] ==> false
     *
     * @param matchsticks
     * @return
     */
    public boolean xxx_makesquare_473(int[] matchsticks) {
        int totalLen = Arrays.stream(matchsticks).sum();
        if (totalLen % 4 != 0) {
            return false;
        }
    
        Arrays.sort(matchsticks);
        // 排序后左右两端交换，从大到小？
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
        // 重点在于，理解递归的两个边界一个index总长一个每条边长len/4，如果不满足边界后回溯递归
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

    /**
     * 470.用 Rand7() 实现 Rand10()
     *
     * 给定方法 rand7 可生成 [1,7] 范围内的均匀随机整数，试写一个方法 rand10 生成 [1,10] 范围内的均匀随机整数。
     *
     * 你只能调用 rand7() 且不能调用其他方法。请不要使用系统的 Math.random() 方法。
     *
     * 每个测试用例将有一个内部参数 n，即你实现的函数 rand10() 在测试时将被调用的次数。请注意，这不是传递给 rand10() 的参数
     */





    /**
     * 462. 给你一个长度为 n 的整数数组 nums ，返回使所有数组元素相等需要的最小操作数，在一次操作中，你可以使数组中的一个元素加 1 或者减 1
     * <p>
     * nums = [1,2,3] ==》2次 [1,2,3]  =>  [2,2,3]  =>  [2,2,2]
     *
     * @param nums
     * @return
     */
    public int minMoves2(int[] nums) {
        // 所有的数都移动到中位数？？为什么不是平均数？？
        Arrays.sort(nums);
        int l = 0, h = nums.length - 1;
        int moves = 0;
        while (l < h) {
            // 假设中位数m，那么a,b移动到m的和是 (b-m)+(m-a) = b-a
            moves += nums[h--] - nums[l++];
        }
        return moves;
    }

    /**
     * 460 Least Frequently Used 最不经常使用，记录的是次数
     *
     * @see LFUCache
     */
    public void xxx_LFUCache() {
        // 1、LRU 最近使用：计时器，双向链表，快速移出某个节点到头部；Map，快速查找
        // 2、LFU 最少使用：计数器
    }

    /**
     * 456. 132 模式 给你一个整数数组 nums ，数组中共有 n 个整数。132 模式的子序列 由三个整数 nums[i]、nums[j] 和 nums[k] 组成，并同时满足：i < j < k 和 nums[i] < nums[k] < nums[j] 。
     *
     * 如果 nums 中存在 132 模式的子序列 ，返回 true ；否则，返回 false
     *
     * @param nums
     * @return
     */
    public boolean find132pattern(int[] nums) {
        // 暴力解法：三层循环???

        return false;
    }

    /**
     * 454. 四数相加，给你四个整数数组 nums1、nums2、nums3 和 nums4 ，数组长度都是 n ，请你计算有多少个元组 (i, j, k, l) 能满足 nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0
     * <p>
     * [1,2], nums2 = [-2,-1], nums3 = [-1,2], nums4 = [0,2] ==》2  1 + (-2) + (-1) + 2 = 0 、2 + (-1) + (-1) + 0 = 0
     *
     * @param nums1
     * @param nums2
     * @param nums3
     * @param nums4
     * @return
     */
    public static int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        // 暴力点：3指针，但不知道数组是不是有序
        int sum = 0;
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                int last = -nums1[i] - nums2[j];
                int left = 0, right = nums4.length - 1;
                while (left < nums3.length && right >= 0) {
                    if (nums3[left] + nums4[right] == last) {
                        sum++;
                        left++;
                    } else if (nums3[left] + nums4[right] < last) {
                        left++;
                    } else if (nums3[left] + nums4[right] > last) {
                        right--;
                    }
                }
            }
        }
        return sum;
    }

    /**
     * 453. 给你一个长度为 n 的整数数组，每次操作将会使 n - 1 个元素增加 1，返回让数组所有元素相等的最小操作次数
     * <p>
     * 输入：nums = [1,2,3] 输出：3 [1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]
     *
     * @param nums
     * @return
     */
    public int x_minMoves_453(int[] nums) {
        // 转换思维 ==》每次操作使 1 个元素减少 1，返回让数组所有元素相等的最小操作次数
        int n = nums.length;
        // minNum 为数组 nums 中的最小值，初始值设为 nums[0]
        int minNum = nums[0];
        // sum 为数组 nums 中所有元素之和
        int sum = 0;
        for (int num : nums) {
            minNum = Math.min(minNum, num);
            sum += num;
        }
        // 总数减去最小数*n，即是操作数量
        return sum - minNum * n;
    }

    /**
     * 451.根据字符出现次数排序，给定一个字符串 s ，根据字符出现的频率对其进行降序排序，一个字符出现的频率是它出现在字符串中的次数。返回已排序的字符串 。如果有多个答案，返回其中任何一个
     *
     * <p>
     * s = "tree" ==> "eert"
     * s = "Aabb" ==> "bbAa"
     *
     * @param s
     * @return
     */
    public String frequencySort_451(String s) {
        // 哈希表暴力解法..
        StringBuilder builder = new StringBuilder();
        Map<Character, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            hashMap.put(s.charAt(i), hashMap.getOrDefault(s.charAt(i), 0) + 1);
        }
        List<Map.Entry<Character, Integer>> list = new ArrayList<>(hashMap.entrySet());
        list.sort((entry1, entry2) -> entry2.getValue() - entry1.getValue());
        for (Map.Entry<Character, Integer> map : list) {
            for (int i = 0; i < map.getValue(); i++) {
                builder.append(map.getKey());
            }
        }
        return builder.toString();
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
    public TreeNode xxx_deleteNode_450(TreeNode root, int key) {
        // tips：找到删除节点后，把节点的右子树的左子树最小节点作为新的根
        return deleteNode(root, key);
    }

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
     * 449.设计一个算法来序列化和反序列化二叉搜索树，编码的字符串应尽可能紧凑
     *
     * @param root
     * @return
     */
    public String serializeCodec_449(TreeNode root) {
        // 序列化是将数据结构或对象转换为一系列位的过程，以便它可以存储在文件或内存缓冲区中，或通过网络连接链路传输，以便稍后在同一个或另一个计算机环境中重建
        Codec codec = new Codec();
        return codec.serialize(root);
    }

    /**
     * 445. 两个非空链表来代表两个非负整数，数字最高位位于链表开始位置，它们的每个节点只存储一位数字，将这两数相加会返回一个新的链表
     * <p>
     * l1 = [7,2,4,3], l2 = [5,6,4] ==》[7,8,0,7]
     * l1 = [2,4,3], l2 = [5,6,4] ==> [8,0,7]
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers_445(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        }
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        // -> 链表倒转
        ListNode l1_reverse = addTwoNumbersReverse(l1);
        ListNode l2_reverse = addTwoNumbersReverse(l2);

        // -> 相加
        int carry = 0;
        ListNode head = new ListNode(-1);
        ListNode node = new ListNode(-1);
        while (l1_reverse != null && l2_reverse != null) {
            int val = l1_reverse.val + l2_reverse.val + carry;
            carry = val / 10;
            val = val % 10;
            ListNode curr = new ListNode(val);
            head.setNext(curr);
            node = curr;
            l1_reverse = l1_reverse.next;
            l2_reverse = l2_reverse.next;
        }
        while (l1_reverse != null) {
            node.setNext(l1_reverse);
            l1_reverse = l1_reverse.next;
        }

        while (l2_reverse != null) {
            node.setNext(l2_reverse);
            l2_reverse = l2_reverse.next;
        }
        return head.next;
    }

//    public static void main(String[] args) {
//        ListNode l1 = new ListNode(7,new ListNode(2,new ListNode(4,new ListNode(3))));
//        ListNode l2 = new ListNode(5,new ListNode(6,new ListNode(4)));
//
//        DoitTop doitTop = new DoitTop();
//        doitTop.addTwoNumbers(l1,l2);
//    }


    private ListNode addTwoNumbersReverse(ListNode node) {
        ListNode pre = null;
        while (node != null) {
            ListNode next = node.next;
            node.next = pre;
            pre = node;
            node = next;
        }
        return pre;
    }

    /**
     * 442. 给你一个长度为 n 的整数数组 nums ，其中 nums 的所有整数都在范围 [1, n] 内，且每个整数出现 一次 或 两次 ，请你找出所有出现 两次 的整数，时间复杂度为 O(n) 且仅使用常量额外空间
     * <p>
     * nums = [4,3,2,7,8,2,3,1]  ==> [2,3]
     *
     * @param nums
     * @return
     */
    public static List<Integer> findDuplicates_442(int[] nums) {
        ArrayList<Integer> resultList = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int num = Math.abs(nums[i]);
            if (nums[num - 1] > 0) {
                // num下标的数据nums[i]正反交替
                nums[num - 1] = -nums[num - 1];
            } else {
                resultList.add(num);
            }
        }
        return resultList;
    }

//    public static void main(String[] args) {
//        int[] nums = {4, 3, 2, 7, 8, 2, 3, 1};
//        System.out.println(findDuplicates_442(nums));
//    }

    /**
     * 435. 给定一个区间的集合 intervals ，其中 intervals[i] = [starti, endi] 。返回需要移除区间的最小数量，使剩余区间互不重叠
     * <p>
     * [[1,2],[2,3],[3,4],[1,3]] ==> 1 移除 [1,3]
     * [ [1,2], [1,2], [1,2] ] ==> 2 移除 [1,2]
     * [ [1,2], [2,3] ] ==> 0
     *
     * @param intervals
     * @return
     */
    public int eraseOverlapIntervals(int[][] intervals) {

        return 0;
    }

    /**
     * 424. 给你一个字符串 s 和一个整数 k 。你可以选择字符串中的任一字符，并将其更改为任何其他大写英文字符，该操作最多可执行 k 次，在执行上述操作后，返回包含相同字母的最长子字符串的长度
     * <p>
     *
     * s = "ABAB", k = 2  ==》 4  用两个'A'替换为两个'B',反之亦然
     * s = "AABABBA", k = 1  ==》4  "AABBBBA"
     * s = "ABCCDFGCFG"，K = 2  ==》
     *
     * @param s
     * @param k
     * @return
     */
    public static int xxx_characterReplacement_424(String s, int k) {
        // 重点在于，记录下窗口内的最大重复数，然后 windowsLen - maxSame 跟 k 比较是否超出
        int len = s.length();
        int[] map = new int[26];
        int left = 0;
        int right = 0;
        int maxSame = 0;
        while (right < len) {
            int index = s.charAt(right) - 'A';
            map[index]++;
            maxSame = Math.max(maxSame, map[index]);
            int windowsLen = right - left + 1;
            if (windowsLen - maxSame > k) {
                map[s.charAt(left) - 'A']--;
                left++;
            }
            right++;
        }
        return right - left;
    }


    /**
     * 421. 给你一个整数数组 nums ，返回 nums[i] XOR nums[j] 的最大运算结果，其中 0 ≤ i ≤ j < n
     * <p>
     * nums = [3,10,5,25,2,8] ==》 28 -- 5 XOR 25 = 28.
     *
     * @param nums
     * @return
     */
//    Trie root = new Trie();
//
//    public int findMaximumXOR_421(int[] nums) {
//        int n = nums.length;
//        int x = 0;
//        for (int i = 1; i < n; ++i) {
//            append(nums[i - 1]);
//            x = Math.max(x, check(nums[i]));
//        }
//        return x;
//    }


    /**
     * 与（AND）运算：&
     * 如果两个相应的位都是1，则结果位是1；
     * 如果任一相应的位是0，则结果位是0
     * <p>
     * 或（OR）运算：|
     * 如果两个相应的位中至少有一个是1，则结果位是1；
     * 如果两个相应的位都是0，则结果位是0
     * <p>
     * 异或（XOR）运算：^
     * 如果两个相应的位不同，则结果位是1
     * 如果两个相应的位相同，则结果位是0
     * <p>
     * 非（NOT）运算：
     * 反转每一位，即0变成1，1变成0
     *
     * @param num
     */
//    private void append(int num) {
//        Trie cur = root;
//        // 数字按bit位拆分成0-1字典树
//        // 每个数字的二进制位，从高位到低位存储到前缀树中，也就是说前缀树中仅有0和1这两个数字
//        // 每个数字跟字字典树进行异或，其中最长的分支，就是最大值
//
//        // 1 << 30 左移30位 即最大的数；
//        // k >>= 1 右移1位 即 k = k / 2
//        for (int k = 1 << 30; k > 0; k >>= 1) {
//            //
//            int bit = num & k;
//            if (bit == 0) {
//                if (cur.left == null) {
//                    cur.left = new Trie();
//                }
//                cur = cur.left;
//            } else {
//                if (cur.right == null) {
//                    cur.right = new Trie();
//                }
//                cur = cur.right;
//            }
//        }
//    }
//
//    private int check(int num) {
//        Trie cur = root;
//        int x = 0;
//        for (int k = 1 << 30; k > 0; k >>= 1) {
//            int bit = num & k;
//            if (bit == 0) {
//                if (cur.right != null) {
//                    cur = cur.right;
//                    x += k;
//                } else {
//                    cur = cur.left;
//                }
//            } else {
//                if (cur.left != null) {
//                    cur = cur.left;
//                    x += k;
//                } else {
//                    cur = cur.right;
//                }
//            }
//        }
//        return x;
//    }

    /**
     * 417 给定一个 m x n 的整数矩阵 heights ， heights[r][c] 表示坐标 (r, c) 上单元格 高于海平面的高度 。
     * 岛上雨水较多，如果相邻单元格的高度小于或等于当前单元格的高度，雨水可以直接向北、南、东、西流向相邻单元格。水可以从海洋附近的任何单元格流入海洋。
     * 返回网格坐标 result 的 2D 列表 ，其中 result[i] = [ri, ci] 表示雨水从单元格 (ri, ci) 流动 既可流向太平洋也可流向大西洋
     * <p>
     * 输入: heights = [[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]
     * 输出: [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]
     * <p>
     * 输入: heights = [[2,1],[1,2]]
     * 输出: [[0,0],[0,1],[1,0],[1,1]]
     *
     * @param heights
     * @return
     */
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        // 从上左 或 下右 流向对方，都要走两遍？


        return null;
    }

    /**
     * 416 给你一个 只包含正整数 的 非空数组nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等
     * <p>
     * 输入：nums = [1,5,11,5] ==> true  [1, 5, 5] 和 [11]
     * 输入：nums = [1,2,3,5] ==> false
     * [2,2,1,1]
     *
     * @param nums
     * @return
     */
    public boolean canPartition_416(int[] nums) {
        // 排序后，两端比较
        if (nums.length < 2) {
            return false;
        }
        // 排序后，左右指针
        Arrays.sort(nums);
        int l = 0, l_s = nums[l], r = nums.length - 1, r_s = nums[r];
        int d = 0;
        while (l <= r) {
            d = r_s - l_s;
            if (d > 0) {
                l++;
                l_s = l_s + nums[l];
            } else {
                r--;
                r_s = r_s + nums[r];
            }
        }
        return d == 0;
    }

//    public static void main(String[] args) {
//        int[] nums = {2,2,1,1};
//        DoitTop doitTop = new DoitTop();
//        System.out.println(doitTop.canPartition(nums));
//    }

    /**
     * 413 给你一个整数数组 nums ，返回数组 nums 中所有等差数组的子数组个数
     * <p>
     * nums = [1,2,3,4] ==》3 子等差数组：[1, 2, 3]、[2, 3, 4] 、[1,2,3,4]
     *
     * @param nums
     * @return
     */
    public int xxx_numberOfArithmeticSlices_413(int[] nums) {
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
            // nums[i]加入后，增加了 temp+1 个连续等差数列，如 [1,2,3,4] temp = 2 因为4出现了2次那么5加入后，就会新增 2 + 1 个连续等差
            ans += temp;
        }
        return ans;
    }

    /**
     * 407. 给你一个 m x n 的矩阵，其中的值均为非负整数，代表二维高度图每个单元的高度，请计算图中形状最多能接多少体积的雨水
     * <p>
     * [[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]] ==> 输出: 4
     * [[3,3,3,3,3],[3,2,2,2,3],[3,2,1,2,3],[3,2,2,2,3],[3,3,3,3,3]] ==> 10
     *
     * @param heightMap
     * @return
     */
    public int trapRainWater_407(int[][] heightMap) {
        // 毫无思路
        return 0;
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
    public String xxx_removeKdigit_402(String num, int k) {
        // 重点在于，使用双端队列可以在Last控制进和出，从而使得队列保持递减。那么最终从First出来的就是最小
        int length = num.length();
        if (length == k) {
            return "0";
        }

        // -------------------------------------

        // 定义递减的双端队列，就要在Last位置不断移出大的数字
        Deque<Character> deque = new LinkedList<>();
        for (int i = 0; i < length; i++) {
            char c = num.charAt(i);
            while (!deque.isEmpty() && k > 0 && deque.peekLast() > c) {
                deque.pollLast();
                k--;
            }
            deque.offerLast(c);
        }
        // 如果此时 k > 0，则说明队列 deque 中的元素已经是单调递增了，只需将队尾元素移除 k 次
        for (int i = 0; i < k; i++) {
            deque.pollLast();
        }

        // -------------------------------------

        // 防止结果中出现前导 0，所以如果第一个元素为 0，则直接跳过
        boolean flag = true;
        StringBuilder res = new StringBuilder();
        while (!deque.isEmpty()) {
            Character c = deque.pollFirst();
            if (flag && c == '0') {
                continue;
            }
            flag = false;
            res.append(c);
        }
        return res.length() == 0 ? "0" : res.toString();
    }


//    public static void main(String[] args) {
//        DoitTop doitTop = new DoitTop();
//        System.out.println(doitTop.xxx_removeKdigits("1432259", 3));
//    }

    /**
     * 400 给你一个整数 n ，请你在无限的整数序列 [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...] 中找出并返回第n位上的数字
     * <p>
     * n = 11 ==> 0
     *
     * @param n
     * @return
     */
    public int findNthDigit(int n) {
        // 数学题：1 * 9 + 2 * 90 + 3 * 900 + 4 * 9000 + 5 * 90000..

        return 0;
    }


    /**
     * 396. 旋转函数 F(k) = 0 * arrk[0] + 1 * arrk[1] + ... + (n - 1) * arrk[n - 1]，返回 F(0), F(1), ..., F(n-1)中的最大值
     * <p>
     * 输入: nums = [4,3,2,6] 输出: 26
     * F(0) = (0 * 4) + (1 * 3) + (2 * 2) + (3 * 6) = 0 + 3 + 4 + 18 = 25
     * F(1) = (0 * 6) + (1 * 4) + (2 * 3) + (3 * 2) = 0 + 4 + 6 + 6 = 16
     * F(2) = (0 * 2) + (1 * 6) + (2 * 4) + (3 * 3) = 0 + 6 + 8 + 9 = 23
     * F(3) = (0 * 3) + (1 * 2) + (2 * 6) + (3 * 4) = 0 + 2 + 12 + 12 = 26
     *
     * @param nums
     * @return
     */
    public static int maxRotateFunction_396(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        // dp推导：多个 n-1的头，少length-1个 n-1的尾
        int init = 0;
        int all = 0;
        for (int i = 0; i < nums.length; i++) {
            init = init + i * nums[i];
            all = all + nums[i];
        }
        int[] dp = new int[nums.length];
        dp[0] = init;
        int max = dp[0];
        for (int i = 1; i < nums.length; i++) {
            // 减去i-1数组的最后一个
            int reduce = (nums.length - 1) * nums[nums.length - i];
            // 加上i-1数组的其他累加
            int add = all - nums[nums.length - i];
            dp[i] = dp[i - 1] - reduce + add;
            max = Math.max(max, dp[i]);
        }
        return max;
    }

//    public static void main(String[] args) {
//        int[] nums = {4,3,2,6};
//        System.out.println(maxRotateFunction(nums));
//    }

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
    public int xxx_longestSubstring_395(String s, int k) {
        // tips：找到出现 次数<k 的字符，不断切割其左右字符串，递归比较最终左右切割结果（好思路！！！）
        return longestSubstring(s, k);
    }

    private int longestSubstring(String s, int k){
        if (s.length() < k) {
            return 0;
        }
        HashMap<Character, Integer> counter = new HashMap();
        for (int i = 0; i < s.length(); i++) {
            counter.put(s.charAt(i), counter.getOrDefault(s.charAt(i), 0) + 1);
        }
        for (char c : counter.keySet()) {
            // count < k 则只能在两字符分割区间内找，不能在外找，因为外部肯定包含数量小于k的字符
            if (counter.get(c) < k) {
                int res = 0;
                for (String t : s.split(String.valueOf(c))) {
                    res = Math.max(res, longestSubstring(t, k));
                }
                return res;
            }
        }
        // 当s中所有字母出现的次数都大于k的时候，则最大子串就是s本身
        return s.length();
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
    public static List<Integer> topKFrequent_347(int[] nums, int k) {
        // 使用字典，统计每个元素出现的次数，元素为键，元素出现的次数为值
        HashMap<Integer, Integer> map = new HashMap();
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        // 小顶堆：顶部最小，每次跟顶部最小值比较，大于最小值则进入k大小内
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(map::get));
        for (Integer key : map.keySet()) {
            if (pq.size() < k) {
                pq.add(key);
            } else if (map.get(key) > map.get(pq.peek())) {
                pq.remove();
                pq.add(key);
            }
        }
        List<Integer> res = new ArrayList<>();
        while (!pq.isEmpty()) {
            res.add(pq.remove());
        }
        return res;
    }

    public List<Integer> xxx_topKFrequent_v2(int[] nums, int k) {
        // 使用字典，统计每个元素出现的次数，元素为键，元素出现的次数为值
        HashMap<Integer, Integer> map = new HashMap();
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        // 桶排序：将出现次数作为数组下标
        List<Integer>[] list = new List[nums.length + 1];
        for (int key : map.keySet()) {
            int i = map.get(key);
            if (list[i] == null) {
                list[i] = new ArrayList();
            }
            list[i].add(key);
        }
        // 遍历桶 <出现次数，数字>, 然后倒序即是前k个
        List<Integer> res = new ArrayList();
        for (int i = list.length - 1; i >= 0 && res.size() < k; i--) {
            if (list[i] == null) {
                continue;
            }
            res.addAll(list[i]);
        }
        return res;
    }

//    public static void main(String[] args) {
//        int[] nums = {1, 2, 2, 3, 3, 3, 4, 4, 4, 4, 5};
//        DoitTop doitTop = new DoitTop();
//        doitTop.xxx_topKFrequent_v2(nums, 2).forEach(System.out::println);
//    }

    /**
     * 267. 给定一个字符串 s ，返回其重新排列组合后可能构成的所有回文字符串，并去除重复的组合
     *
     * s = "aabb" ==> ["abba", "baab"]
     *
     * @param s
     * @return
     */
    public List<String> generatePalindromes(String s) {
        // 一个奇数倍，其他都是偶数倍
        int[] count = new int[128];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i)]++;
        }
        int odd = 0;
        for (int i = 0; i < s.length(); i++) {
            if (count[s.charAt(i)] % 2 == 1) {
                odd++;
            }
        }
        if (odd > 1) {
            return null;
        }

        return null;
    }

    /**
     * 260.给你一个整数数组 nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。找出只出现一次的那两个元素 可以按任意顺序返回答案
     * <p>
     * [1,2,1,3,2,5] => [3,5] / [5,3]
     *
     * @param nums
     * @return
     */
    public int[] singleNumber_260(int[] nums) {
        // 数组查找：滑动窗口、双指针、动态规划、二分法、
        // 性时间复杂度 + 常数空间复杂度

        Arrays.sort(nums);

        // 考的是思路...

        return null;
    }

    /**
     * 343. 给定一个正整数 n ，将其拆分为 k 个 正整数 的和（ k >= 2 ），并使这些整数的乘积最大化，返回你可以获得的最大乘积
     * <p>
     * n = 10 ==> 10 = 3 + 3 + 4, 3 × 3 × 4 = 36
     *
     * @param n
     * @return
     */
    public int xxx_integerBreak_343(int n) {
        // 暴力点就DFS
        // dp[i] 表示拆分 i 可获得的最大乘积
        int[] dp = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            int tmpMax = 0;
            /*
                先将 i 分解为 j 和 i - j
                如果 i - j 不再分解，那么 dp[i] = j * (i - j)
                如果 i - j 继续分解，那么 dp[i] = j * dp[i - j]
                其中 j 的取值范围是 [1, i - 1]，所以需要遍历 j 的所有可能情况
            */
            for (int j = 1; j < i; j++) {
                tmpMax = Math.max(tmpMax, Math.max(j * (i - j), j * dp[i - j]));
            }
            dp[i] = tmpMax;
        }
        return dp[n];
    }

    /**
     * 340.给你一个字符串 s 和一个整数 k ，请你找出 至多 包含 k 个 不同 字符的最长子串并返回该子串的长度
     *
     * s = "eceba", k = 2 ==> 3 "ece"
     * s = "aa", k = 1 ==> 2 "aa"
     *
     * @param s
     * @param k
     * @return
     */
    public int xxx_lengthOfLongestSubstringKDistinct_340(String s, int k) {
        // 重点在于，一道普通的窗口题怎么统计窗口的最大值 (r-l)
        if (s.length() == 0 || s.length() <= k) {
            return s.length();
        }
        int[] window = new int[128];
        int l = 0, r = 0;
        for (int count = 0; r < s.length(); ) {
            // 右入窗口
            if (window[s.charAt(r++)]++ == 0) {
                count++;
            }
            // 窗口值大于k, 左出窗口
            if (count > k && (--window[s.charAt(l++)]) == 0) {
                count--;
            }
        }
        return r - l;
    }

    /**
     * 314. 二叉树的垂直遍历，返回其结点垂直方向（从上到下，逐列）遍历的值如果两个结点在同一行和列，那么顺序则为从左到右
     * <p>
     * root = [3,9,20,null,null,15,7] ==》 [[9],[3,15],[20],[7]]
     *
     * @param root
     * @return
     */
    public List<List<Integer>> verticalOrder_314(TreeNode root) {
        // 以根节点为基点，左减右加
        HashMap<Integer, List<Integer>> hashMap = new HashMap<>();
        verticalOrderDFS(root, hashMap, 0);

        List<List<Integer>> res = Lists.newArrayList();
        res.addAll(hashMap.values());
        return res;
    }

    private void verticalOrderDFS(TreeNode root, HashMap<Integer, List<Integer>> hashMap, int index) {
        if (root == null) {
            return;
        }
        hashMap.computeIfAbsent(index, k -> new ArrayList<>()).add(root.val);
        // 前序遍历
        verticalOrderDFS(root.left, hashMap, index - 1);
        verticalOrderDFS(root.right, hashMap, index + 1);
    }

//    public static void main(String[] args) {
//        TreeNode root = new TreeNode(3,new TreeNode(9),new TreeNode(20,new TreeNode(15),new TreeNode(7)));
//        DoitTop doitTop = new DoitTop();
//        System.out.println(doitTop.verticalOrder(root));
//
//    }

    /**
     * 316. 给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证返回结果的字典序最小（要求不能打乱其他字符的相对位置）
     * <p>
     * s = "bcabc" ==> abc
     * s = "cbacdcbc" ==> acdb
     *
     * @param s
     * @return
     */
    public String xxx_removeDuplicateLetters_316(String s) {
        // 单调栈
        int len = s.length();
        char[] chararray = s.toCharArray();
        // 记录某一个字符是否在栈中出现
        boolean[] visited = new boolean[26];
        // 记录每个元素最后一次出现的位置
        int[] lastIndex = new int[26];
        for (int i = 0; i < len; i++) {
            lastIndex[chararray[i] - 'a'] = i;
        }
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < len; i++) {
            if (visited[chararray[i] - 'a']) {
                // 如果出现舍弃当前字符
                continue;
            }
            // 当前字符在栈顶元素之前 且 栈顶元素在后面还有
            while (!stack.isEmpty() && stack.peekLast() > chararray[i] && lastIndex[stack.peekLast() - 'a'] > i) {
                // 移除栈顶元素
                Character c = stack.removeLast();
                // 表示该字符没有在栈中出现
                visited[c - 'a'] = false;
            }
            stack.addLast(chararray[i]);
            visited[chararray[i] - 'a'] = true;
        }
        StringBuilder stringbuilder = new StringBuilder();
        for (char c : stack) {
            stringbuilder.append(c);
        }
        return stringbuilder.toString();
    }

    /**
     * 315.一个整数数组 nums ，按要求返回一个新数组 counts，数组 counts 有该性质 counts[i] 的值是 nums[i] 右侧小于 nums[i] 的元素的数量
     * <p>
     * nums = [5,2,6,1] ==> [2,1,1,0]
     *
     * @param nums
     * @return
     */
    public List<Integer> countSmaller_315(int[] nums) {
        // 暴力求解 时间复杂度 O(n^2)
        // 归并插入 从右往左比较 时间复杂度O(nlogn)
        int[] res = nums.clone();

        return null;
    }

    private void countSmallerMerge(int[] res, int[] nums, int start, int end) {
        // 归并递归不知道怎么写..
        if (start >= end) {
            return;
        }
        int mid = (start + end) / 2;

        countSmallerMerge(res, nums, start, mid);

        countSmallerMerge(res, nums, mid, end);
    }


    private void merge(int[] res, int[] nums, int start, int mid, int end) {
        // mid 右边的比较给左边的
        for (int i = start; i < mid; i++) {
            for (int j = mid; j < end; j++) {
                // 还是O(n^2)
            }
        }
    }

    /**
     * 334.整数数组 nums ，判断这个数组中是否存在长度为3的递增子序列（递增不限于连续）
     * <p>
     * nums = [1,2,3,4,5] ==> true
     * nums = [5,4,3,2,1] ==> false
     * nums = [2,1,5,0,4,6] ==> true
     *
     * @param nums
     * @return
     */
    public boolean xxx_increasingTriplet_334(int[] nums) {
        // tips：前缀和，正好当前点加上左右的一大一小，组成3个
        // 如果是长度为4怎么办？简单，继续判断左节点的左边或者右节点的右边是否还有更小更大值
        int n = nums.length;
        if (n < 3) {
            return false;
        }
        // leftMin：位置i左侧的最小值（前缀和）
        int[] leftMin = new int[n];
        leftMin[0] = nums[0];
        for (int i = 1; i < n; i++) {
            leftMin[i] = Math.min(leftMin[i - 1], nums[i]);
        }

        // rightMax:位置i右侧的最大值
        int[] rightMax = new int[n];
        rightMax[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], nums[i]);
        }

        // i 大于左边最小值 且 小于右边最大值 的时候说明可行
        for (int i = 1; i < n - 1; i++) {
            if (nums[i] > leftMin[i - 1] && nums[i] < rightMax[i + 1]) {
                return true;
            }
        }
        return false;
    }


    /**
     * 333. 给定一个二叉树，找到其中子树节点数最多的二叉搜索树（BST）子树并返回该子树的大小
     * <p>
     * 输入：root = [10,5,15,1,8,null,7] ==> 输出：3
     * 输入：root = [4,2,7,2,3,5,null,2,null,null,null,null,null,1] ==> 输出：2
     *
     * @param root
     * @return
     */
    int largestBSTSubtreeRes = 0;

    public int xxx_largestBSTSubtree_333(TreeNode root) {
        if (root == null) {
            return 0;
        }
        largestBSTSubtreeDFS(root);
        return largestBSTSubtreeRes;
    }

    private Node largestBSTSubtreeDFS(TreeNode root) {
        if (root.left == null && root.right == null) {
            largestBSTSubtreeRes = Math.max(largestBSTSubtreeRes, 1);
            return new Node(root.val, root.val, 1);
        }
        int min = root.val;
        int max = root.val;
        int size = 1;
        boolean valid = true;
        // 左子树：存在子树 且 根节点大于子树最大值 则重置节点size、L、R
        if (root.left != null) {
            Node L = largestBSTSubtreeDFS(root.left);
            if (L.size != -1 && root.val > L.max) {
                size += L.size;
                min = L.min;
            } else {
                valid = false;
            }
        }
        // 右子树：存在子树 且 根节点小于子树最小值 则重置节点size、L、R
        if (root.right != null) {
            Node R = largestBSTSubtreeDFS(root.right);
            if (R.size != -1 && root.val < R.min) {
                size += R.size;
                max = R.max;
            } else {
                valid = false;
            }
        }
        // 如果左右子树任意一个非BST，那么根节点都不是BST
        if (valid) {
            largestBSTSubtreeRes = Math.max(size, largestBSTSubtreeRes);
            return new Node(min, max, size);
        }
        return new Node(-1, -1, -1);
    }

    class Node {
        //最小值
        int min;
        //最大值
        int max;
        //节点个数，如果不是bst则为-1
        int size;

        Node(int min, int max, int size) {
            this.min = min;
            this.max = max;
            this.size = size;
        }
    }

//    public static void main(String[] args) {
//        TreeNode root = new TreeNode(10, new TreeNode(5,new TreeNode(1),new TreeNode(8)), new TreeNode(15,null,new TreeNode(7)));
//        Top top = new Top();
//        System.out.println(top.xxx_largestBSTSubtree_333(root));
//    }


    //    public int largestBSTSubtree_ee(TreeNode root) {
//        return largestBSTSubtreeDFS(root,0);
//    }
//
//    public int largestBSTSubtreeDFS(TreeNode root, int path) {
//        if (root == null) {
//            return path;
//        }
//        // 后序遍历
//        int left = largestBSTSubtreeDFS(root.left, path);
//        int right = largestBSTSubtreeDFS(root.right, path);
//        // 叶子节点：只有一个
//        if (root.right == null && root.left == null) {
//            return 1;
//        }
//        // 左右节点都行：
//        if ((root.left != null && root.val > root.left.val)
//                && (root.right != null && root.val < root.right.val)) {
//            return left + right + 1;
//        }
//        return 0;
//    }


    /**
     * 329.给定一个 m x n 整数矩阵 matrix，找出其中最长递增路径的长度（对于每个单元格，你可以往上下左右四个方向移动）
     *
     * matrix = [[9,9,4],[6,6,8],[2,1,1]]  输出：4 解释：最长递增路径为 [1, 2, 6, 9]
     * matrix = [[3,4,5],[3,2,6],[2,2,1]] 输出：4 解释：最长递增路径是 [3, 4, 5, 6]
     *
     * @param matrix
     * @return
     */
    int[][] moves = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    int longestIncreasingPath = 0;

    public int x_longestIncreasingPath_329(int[][] matrix) {
        if (matrix.length == 0) {
            return 0;
        }
        // ⚠️ 每个节点前后左右递归判断 -- 超出时间限制!!!
        int[][] used = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                int path = longestIncreasingPathDFS(i, j, matrix, used, 1);
                used[i][j] = path;
                longestIncreasingPath = Math.max(longestIncreasingPath, path);
            }
        }
        return longestIncreasingPath;
    }

    private int longestIncreasingPathDFS(int row, int col, int[][] matrix, int[][] used, int path) {
        if (used[row][col] > 0) {
            return path + used[row][col] - 1;
        }
        int next = path;
        for (int i = 0; i < moves.length; i++) {
            int[] move = moves[i];
            // 最上面不能在往上走
            if (row == 0 && move[0] < 0) {
                continue;
            }
            // 最下面不能在往下走
            if (row == matrix.length - 1 && move[0] > 0) {
                continue;
            }
            // 最左面不能在往左走
            if (col == 0 && move[1] < 0) {
                continue;
            }
            // 最右面不能在往右走
            if (col == matrix[0].length - 1 && move[1] > 0) {
                continue;
            }
            int newRow = row + move[0];
            int newCol = col + move[1];
            // 啥意思？
            if (matrix[newRow][newCol] <= matrix[row][col]) {
                continue;
            }
            // 在4个方向的路径中找出最大的那个
            path = Math.max(path, longestIncreasingPathDFS(newRow, newCol, matrix, used, next + 1));
        }
        return path;
    }


//    public static void main(String[] args) {
//        Top top = new Top();
//        // [[3,4,5],[3,2,6],[2,2,1]]
//        // [[9,9,4],[6,6,8],[2,1,1]]
//        // [[7,7,5],[2,4,6],[8,2,0]]
//        // int[][] matrix = {{3, 4, 5}, {3, 2, 6}, {2, 2, 1}};
//        // int[][] matrix = {{9, 9, 4}, {6, 6, 8}, {2, 1, 1}};
//        int[][] matrix = {{7, 7, 5}, {2, 4, 6}, {8, 2, 0}};
//        System.out.println(top.longestIncreasingPath_329(matrix));
//    }

    /**
     * 328.给定单链表的头节点 head ，将所有索引为奇数的节点和索引为偶数的节点分别组合在一起，然后返回重新排序的列表。
     * <p>
     * 第一个节点的索引被认为是奇数 ， 第二个节点的索引为偶数 ，以此类推。
     * <p>
     * 请注意，偶数组和奇数组内部的相对顺序应该与输入时保持一致。
     * <p>
     * 你必须在 O(1) 的额外空间复杂度和 O(n) 的时间复杂度下解决这个问题
     * <p>
     * head = [1,2,3,4,5] ==> [1,3,5,2,4]
     * <p>
     * head = [2,1,3,5,6,4,7] ==> [2,3,6,7,1,5,4]
     *
     * @param head
     * @return
     */
    public ListNode oddEvenList(ListNode head) {

        return null;
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
    private static int maxSubArrayLen_ee(int[] nums, int k) {
        // 滑动窗口啊
        int windowSum = 0;
        int max = 0;
//        Queue<Integer> queue = new LinkedList<>();
//        for (int j : nums) {
//            queue.add(j);
//            windowSum = windowSum + j;
//            // TODO windowSum存在负数的话，就不满足滑动窗口，不能保证窗口内的一定是最大值
//            while (queue.size() > 0 && windowSum > k) {
//                int num = queue.poll();
//                windowSum -= num;
//            }
//            if (windowSum == k) {
//                max = Math.max(max, queue.size());
//            }
//        }
        return max;
    }

//    public static void main(String[] args) {
//        int[] nums = {3, 1, -1, 5, -2, 3};
//        System.out.println(maxSubArrayLen_ee(nums, 3));
//    }

    public static int xxx_maxSubArrayLen_325(int[] nums, int k) {
        int prefixSum = 0;
        int longestSubarray = 0;
        // <前缀和，位置i>
        HashMap<Integer, Integer> indices = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];
            // 最长的连续子数组为k的可能性有二
            // 1、从头开始连续大小为k
            if (prefixSum == k) {
                longestSubarray = i + 1;
            }
            // 2、某一段区间大小为k，即 [prefixSum - k, prefixSum]
            if (indices.containsKey(prefixSum - k)) {
                longestSubarray = Math.max(longestSubarray, i - indices.get(prefixSum - k));
            }
            // 不更新prefixSum，保证区间值最大
            if (!indices.containsKey(prefixSum)) {
                indices.put(prefixSum, i);
            }
        }
        return longestSubarray;
    }


    /**
     * 324.摆动排序，给你一个整数数组 nums，将它重新排列成 nums[0] < nums[1] > nums[2] < nums[3]... 的顺序
     * <p>
     * nums = [1,5,1,1,6,4] ==> [1,6,1,5,1,4]，[1,4,1,5,1,6] 同样是符合题目要求的结果，可以被判题程序接受
     *
     * @param nums
     */
    public void wiggleSort_324(int[] nums) {
        // 暴力点：排序后分成两组，然后合并插入即可O(n^2)
        Arrays.sort(nums);

        // 归并排序：merge的时候直接按照小大来？-- 归并的优势在哪
        // 小1大1小1大1 小2大2小2大2

        // 三指针重新排序
        int[] arr = nums.clone();
        Arrays.sort(arr);
        int n = nums.length;
        int x = (n + 1) / 2;
        // 两个点：一个从中间开始，一个从最后开始，依次往前填塞
        for (int i = 0, j = x - 1, k = n - 1; i < n; i += 2, j--, k--) {
            nums[i] = arr[j];
            if (i + 1 < n) {
                nums[i + 1] = arr[k];
            }
        }
    }

    /**
     * 323：无向图中连通分量的数目，你有一个包含 n 个节点的图。给定一个整数 n 和一个数组 edges ，其中 edges[i] = [ai, bi] 表示图中 ai 和 bi 之间有一条边，返回图中已连接分量的数目
     * <p>
     * n = 5, [[0, 1], [1, 2], [3, 4]] ==> 2
     * n = 5, [[0,1], [1,2], [2,3], [3,4]] ==> 1
     * n=5 [[0,1],[0,4],[1,4],[2,3]]
     *
     * @param n
     * @param edges
     * @return
     */
    private static int countComponents_323(int n, int[][] edges) {
        // 排序后，然后遍历
        Arrays.sort(edges, Comparator.comparingInt(v -> v[0]));
        int pre = edges[0][1];
        int count = 1;
        for (int i = 1; i < edges.length; i++) {
            if (edges[i][0] > pre) {
                count++;
            }
            pre = edges[i][1];
        }
        return count;
    }

    public int xxx_countComponents_323(int n, int[][] edges) {
        int components = 0;
        int[] visited = new int[n];
        List<Integer>[] adjList = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<>();
        }
        for (int i = 0; i < edges.length; i++) {
            // 双向联通的，可以a->b，也可以 b->a
            adjList[edges[i][0]].add(edges[i][1]);
            adjList[edges[i][1]].add(edges[i][0]);
        }
        // 重点在于，遍历节点后，打上一个已读标签。通过标签来识别存在的路径数量
        for (int i = 0; i < n; i++) {
            if (visited[i] == 0) {
                // 这么简单的DFS.. 怎么想不到呢
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


//    public static void main(String[] args) {
//        // [[0,1],[0,4],[1,4],[2,3]]
//        int[][] edges = {{0, 1}, {1, 2}, {3, 4}};
//        System.out.println(ee_countComponents_323(5, edges));
//    }

    /**
     * 322. 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额，计算并返回可以凑成总金额所需的最少的硬币个数，如果没有任何一种硬币组合能组成总金额，返回 -1
     * <p>
     * coins = [1, 2, 5], amount = 11 ==》3 11 = 5 + 5 + 1
     *
     * @param coins
     * @param amount
     * @return
     */
    public static int coinChange_322(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        Arrays.sort(coins);
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin > i) {
                    break;
                }
                // 找到从 dp[i-coin] -> dp[i] 的最短路径
                dp[i] = Math.min(dp[i - coin] + 1, dp[i]);
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

//    public static void main(String[] args) {
//        int[] coins = {1,2,5};
//        System.out.println(coinChange_322(coins, 11));
//    }

    /**
     * 319. 灯泡开关
     * <p>
     * 初始时有 n 个灯泡处于关闭状态。第一轮，你将会打开所有灯泡。接下来的第二轮，你将会每两个灯泡关闭第二个。
     * <p>
     * 第三轮，你每三个灯泡就切换第三个灯泡的开关（即，打开变关闭，关闭变打开）。第 i 轮，你每 i 个灯泡就切换第 i 个灯泡的开关。直到第 n 轮，你只需要切换最后一个灯泡的开关。
     * <p>
     * 找出并返回 n 轮后有多少个亮着的灯泡
     * <p>
     * 输入：n = 3
     * 输出：1
     * 解释：
     * 初始时, 灯泡状态 [关闭, 关闭, 关闭].
     * 第一轮后, 灯泡状态 [开启, 开启, 开启].
     * 第二轮后, 灯泡状态 [开启, 关闭, 开启].
     * 第三轮后, 灯泡状态 [开启, 关闭, 关闭].
     *
     * @param n
     * @return
     */
    public int bulbSwitch_319(int n) {
        // 暴力循环 1:开；0：关 -- 直接超出时间限制
        int[] nums = new int[n];
        Arrays.fill(nums, 1);
        for (int i = 1; i < n; i++) {
            // 时间复杂度:O(n^2)
            for (int j = 0; j < n; j++) {
                // 整除 /；取余 %
                if ((j + 1) % (i + 1) == 0) {
                    nums[j]++;
                }
            }
        }
        int num = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] % 2 == 1) {
                num++;
            }
        }

        // 看一个数被[1,n]能整除多少次即可 --
        // 只有当 k 是「完全平方数」时，它才会有奇数个约数，否则一定有偶数个约数 ？？
        return (int) Math.sqrt(n + 0.5);
    }


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
    public int xxx_nthSuperUglyNumber(int n, int[] primes) {
        // 记录第n次的最小数
        int[] dp = new int[n + 1];
        int m = primes.length;
        // 记录丑数列表的每个数字累加了多少次
        int[] pointers = new int[m];
        // 记录point次乘积后的最小值
        int[] nums = new int[m];
        Arrays.fill(nums, 1);
        for (int i = 1; i <= n; i++) {
            int minNum = Arrays.stream(nums).min().getAsInt();
            dp[i] = minNum;
            // 循环找到本次的最小值是谁，然后把最小值继续推进：
            for (int j = 0; j < m; j++) {
                if (nums[j] == minNum) {
                    // pointers[j]: j指针执行次数；
                    pointers[j]++;
                    // dp[pointers[j]] j指针执行几次后，dp产生的最小值？？？（死记吧）
                    // nums[j]已经是最小值，要继续推进，下一个值就是？？？
                    nums[j] = dp[pointers[j]] * primes[j];
                    // - nums[j] = pointers[j] * primes[j]; // 错误：不是乘积的关系，是幂的关系
                }
            }
        }
        return dp[n];
    }

//    public static void main(String[] args) {
//        DoitTop top = new DoitTop();
//        int[] nums = {2,7,13,19};
//        System.out.println(top.xxx_nthSuperUglyNumber(12, nums));
//    }


    /**
     * 264.一个整数n，返回第n个丑数（丑数就是质因子只包含 2、3 和 5 的正整数）
     *
     * n = 10 ==> 12 [1, 2, 3, 4, 5, 6, 8, 9, 10, 12] 丑数组成的前10列表
     *
     * @param n
     * @return
     */
    public int xxx_nthUglyNumber_264(int n) {
        int[] dp = new int[n];
        dp[0] = 1;
        int p2 = 0, p3 = 0, p5 = 0;
        for (int i = 1; i < n; i++) {
            // 2*2*2.. ｜ 3*3*3..  | 5*5*5.. 之间的比较
            int num2 = dp[p2] * 2, num3 = dp[p3] * 3, num5 = dp[p5] * 5;
            dp[i] = Math.min(num2, Math.min(num3, num5));
            System.out.println(dp[i] + " ");
            if (dp[i] == num2) {
                p2++;
            }
            if (dp[i] == num3) {
                p3++;
            }
            if (dp[i] == num5) {
                p5++;
            }
        }
        return dp[n - 1];
    }

//    public static void main(String[] args) {
//        Top top = new Top();
//        top.xxx_nthUglyNumber_264(20);
//    }

    /**
     * 309. 给定一个整数数组prices，其中第 prices[i] 表示第 i 天的股票价格。设计一个算法计算出最大利润在满足以下约束条件下，
     *      你可以尽可能地完成更多的交易（多次买卖一支股票） 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)
     *      注意：你不能同时参与多笔交易，你必须在再次购买前出售掉之前的股票
     * <p>
     * eg.prices = [1,2,3,0,2] ==> 3 [买入, 卖出, 冷冻期, 买入, 卖出]
     *
     * @param prices
     * @return
     */
    public int xxx_maxProfit_309(int[] prices) {
        // 重点在于，分析出今天利润的各种可能：有股票（只能有一只所以不会在冷冻期）、无股票且在冷冻期、无股票不在冷冻期
        if (prices.length == 0) {
            return 0;
        }
        // f[i][0]: 手上持有股票的最大收益
        // f[i][1]: 手上不持有股票，并且处于冷冻期中的累计最大收益
        // f[i][2]: 手上不持有股票，并且不在冷冻期中的累计最大收益
        int n = prices.length;
        int[][] f = new int[n][3];
        f[0][0] = -prices[0];
        for (int i = 1; i < n; ++i) {
            // 持有股票：一直持有的 或者 前一天天买入的
            f[i][0] = Math.max(f[i - 1][0], f[i - 1][2] - prices[i]);
            // 不持有&在冷冻期：第i-1天持有股票，后卖出（注意，这里的值都是一天之初的情况）
            f[i][1] = f[i - 1][0] + prices[i - 1];
            // 不持有&不在冷冻：第i-1天没操作，可能在冷冻也可能不在
            f[i][2] = Math.max(f[i - 1][1], f[i - 1][2]);
        }
        // 最后一天肯定不会持有股票
        return Math.max(f[n - 1][1], f[n - 1][2]);
    }


    /**
     * 306.累加数 是一个字符串，组成它的数字可以形成累加序列。
     * <p>
     * 一个有效的 累加序列 必须 至少 包含 3 个数。除了最开始的两个数以外，序列中的每个后续数字必须是它之前两个数字之和。
     * <p>
     * 给你一个只包含数字 '0'-'9' 的字符串，编写一个算法来判断给定输入是否是 累加数 。如果是，返回 true ；否则，返回 false
     * <p>
     * <p>
     * 112358 -->true
     * 199100199 --> true
     *
     * @param num
     * @return
     */
    public boolean isAdditiveNumber_306(String num) {

        return false;
    }


    /**
     * 304.给定一个二维矩阵 matrix，以下类型的多个请求
     *
     * 计算其子矩形范围内元素的总和，该子矩阵的 左上角 为 (row1, col1) ，右下角 为 (row2, col2)
     *
     * [[[[3,0,1,4,2],[5,6,3,2,1],[1,2,0,1,5],[4,1,0,1,7],[1,0,3,0,5]]],[2,1,4,3],[1,1,2,2],[1,2,2,4]]
     *  ==>
     * numMatrix.sumRegion(2, 1, 4, 3); // return 8 (红色矩形框的元素总和)
     * numMatrix.sumRegion(1, 1, 2, 2); // return 11 (绿色矩形框的元素总和)
     * numMatrix.sumRegion(1, 2, 2, 4); // return 12 (蓝色矩形框的元素总和)
     */


    /**
     * 301.给你一个由若干括号和字母组成的字符串 s ，删除最小数量的无效括号，使得输入的字符串有效
     * <p>
     * s = "()())()" ==》["(())()","()()()"]
     *
     * @param s
     * @return
     */
    public List<String> removeInvalidParentheses_304(String s) {
        return null;
    }


    /**
     * 300. 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度
     * <p>
     * nums = [10,9,2,3,7,101,18] ==》4 [2,3,7,101]
     *
     * @param nums
     * @return
     */
    public static int lengthOfLIS_300(int[] nums) {
        // 左右指针，左指针在原地,右指针通过比较不断前移
        int left = 0, right = 0;
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > nums[right]) {
                right++;
            } else {
                max = Math.max(max, right - left);
                right = i;
                left = i;
            }
        }
        return max > 0 ? max + 1 : max;
    }

//    public static void main(String[] args) {
//        int[] nums = {10, 9, 2, 3, 7, 101, 18};
//        System.out.println(lengthOfLIS_300(nums));
//    }

    /**
     * 287. 给定一个包含n + 1个整数的数组 nums ，其数字都在[1, n]范围内，可知至少存在一个重复的整数返回这个重复的数（假设nums只有一个重复的整数）
     * 解决方案必须不修改数组nums 且 只用常量级O(1)的额外空间
     * <p>
     * [3,2,5,4,6,1,4] ==> 4
     *
     * @param nums
     * @return
     */
    public int xxx_findDuplicate_287(int[] nums) {
        // 二分法：findDuplicate_4_mid -- 常量级的时间复杂度和空间复杂
        // 快慢指针：findDuplicate_4_slow -- 保证了在快慢指针能在重复节点相遇
        return 0;
    }

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
        // 通过 nums[n] -> nums[nums[n]] 的指向构建成一个环（不在意环的位置是否是链表最后只要保证环存在即可）
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
     * 285.给定一棵二叉搜索树和其中的一个节点 p ，找到该节点在树中的中序后继。如果节点没有中序后继，请返回 null
     *
     * root = [2,1,3], p = 1 ==》2
     * root = [5,3,6,2,4,null,null,1], p = 6  ==> null
     *
     * @param root
     * @param p
     * @return
     */
    public TreeNode inorderSuccessor_285(TreeNode root, TreeNode p) {
        // 中序遍历放到stack，然后找到该节点
        Stack<TreeNode> stack = new Stack<>();
        inorderSuccessor(root, stack);
        TreeNode pre = null;
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node == p) {
                return pre;
            }
            pre = node;
        }
        return pre;
    }

    private void inorderSuccessor(TreeNode root, Stack<TreeNode> stack) {
        if (root == null) {
            return;
        }
        inorderSuccessor(root.left, stack);
        stack.push(root);
        inorderSuccessor(root.right, stack);
    }

//    public static void main(String[] args) {
//        TreeNode find = new TreeNode(1);
//        TreeNode root = new TreeNode(2, find, new TreeNode(3));
//        Top top = new Top();
//        TreeNode result = top.inorderSuccessor(root, find);
//        System.out.println(result == null ? null : result.val);
//    }


    /**
     * 279.给你一个整数 n ，返回和为 n 的完全平方数的最少数量
     *
     * 完全平方数：是其值等于一个整数自乘的积，例如 1、4、9 和 16 都是完全平方数，而 3 和 11 不是
     *
     * n = 12 ==> 3 4+4+4
     *
     * @param n
     * @return
     */
    public int xx_numSquares_279(int n) {
        // ** 核心：之前的动态规划都是正向推进如dp[i] = dp[i-1]+nums[i]，这个是倒着推前一个最小的从而找到心在最大的 **
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            // 状态转移方程：找到最小的 n-i*i -> n
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[n];
    }

//    public static void main(String[] args) {
//        Top service = new Top();
//        System.out.println(service.xxx_numSquares_279(12));
//    }

    /**
     * 255.给定一个无重复元素的整数数组preorder，如果它是以二叉搜索树的先序遍历排列返回true
     * <p>
     * preorder = [5,2,1,3,6] ==> true
     * preorder = [5,2,6,1,3] ==> false
     *
     * @param preorder
     * @return
     */
    public boolean xxx_verifyPreorder_255(int[] preorder) {
        // 重点在于，找到左右子树的分界点，然后判断右子树是否都大于左子树的根节点
        // 另外二叉树的核心一般都是用栈来实现
        Stack<Integer> stack = new Stack<>();
        int max = Integer.MIN_VALUE;
        for (int cur : preorder) {
            // 先序拐点在根节点，后续右子树节点都要大于此节点
            while (!stack.isEmpty() && cur > stack.peek()) {
                max = stack.pop();
            }
            // 右子树 > 根 > 左子树
            if (cur < max) {
                return false;
            }
            stack.push(cur);
        }
        return true;
    }

//    public static void main(String[] args) {
//        int[] preorder = {4, 2, 1, 3, 5, 6};
//        Train01 doitTop = new Train01();
//        System.out.println(doitTop.xxx_verifyPreorder_255(preorder));
//    }

    /**
     * 如果它是以二叉搜索树的后序遍历排列返回true
     *
     * @param preorder
     * @return
     */
    private boolean verifyPreorderV2(int[] preorder) {
        if (preorder == null || preorder.length == 0) {
            return false;
        }
        Stack<Integer> stack = new Stack<>();
        int max = Integer.MIN_VALUE;
        for (int cur : preorder) {
            if (cur < max) {
                return false;
            }
            // 后续拐点在根节点：后续右子树节点都要大于此节点
            while (!stack.isEmpty() && stack.peek() < cur) {
                max = stack.pop();
            }
            stack.push(cur);
        }
        return true;
    }

    /**
     * 254.整数可以被看作是其因子的乘积
     * <p>
     * 12 ==> [
     * [2, 6],
     * [2, 2, 3],
     * [3, 4]
     * ]
     *
     * @param n
     * @return
     */
    List<List<Integer>> factors_res = new ArrayList<>();

    public List<List<Integer>> getFactors_254(int n) {
        // 注意： 你可以假定 n 为永远为正数 因子必须大于 1 并且小于 n
        // 递归的定义：dfs(n, start, path) 表示 n 的因子列表，因子的起始位置为 start，已经选择的因子列表为 path
        dfs(n, 2, new ArrayList<>());
        return factors_res;
    }

    private void dfs(int n, int start, List<Integer> path) {
        if (n == 1) {
            if (path.size() > 1) {
                factors_res.add(new ArrayList<>(path));
            }
            return;
        }
        for (int i = start; i <= n; i++) {
            if (n % i == 0) {
                path.add(i);
                dfs(n / i, i, path);
                path.remove(path.size() - 1);
            }
        }
    }

    /**
     * 253.给你一个会议时间安排的数组intervals ，每个会议时间都会包括开始和结束的时间intervals[i] = [starti, endi]，返回所需会议室的最小数量
     * <p>
     * intervals = [[0,30],[5,10],[15,20]] ==> 2
     *
     * @param intervals
     * @return
     */
    public int xx_minMeetingRooms_253(int[][] intervals) {
        // ** 看的是思路：小顶堆维护的是当前进行的会议室 **
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        // 从定义排序按照进入时间排序
        Arrays.sort(intervals, Comparator.comparingInt(v -> v[0]));
        // 定义一个小顶堆的优先队列，存放当前进行的会议室
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int meetingCount = 0;
        for (int[] meeting : intervals) {
            // [1,5],[2,4],[7,10] 那么 [6,8] 进来后 [1,5],[2,4] 可全部结束
            while (!heap.isEmpty() && meeting[0] >= heap.peek()) {
                heap.poll();
            }
            heap.add(meeting[1]);
            meetingCount = Math.max(meetingCount, heap.size());
        }
        return meetingCount;
    }

    /**
     * 247。给定一个整数 n ，返回所有长度为 n 的 中心对称数 。你可以以 任何顺序 返回答案
     * n = 2 ==》 ["11","69","88","96"]
     *
     * @param n
     * @return
     */
    public List<String> findStrobogrammatic(int n) {
        // 中心对称数是一个数字在旋转了 180 度之后看起来依旧相同的数字（或者上下颠倒地看）
        return null;
    }

    /**
     * 240.编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target，该矩阵具有以下特性：1、每行的元素从左到右升序排列；2、每列的元素从上到下升序排列
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix_240(int[][] matrix, int target) {
        // 一个比较巧妙的解法：通过大小的增减
        int row = matrix.length - 1;
        int col = 0;
        while (row >= 0 && col < matrix[0].length) {
            if (matrix[row][col] > target) {
                row--;
            } else if (matrix[row][col] < target) {
                col++;
            } else {
                return true;
            }
        }
        return false;
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
    public int[] x_maxSlidingWindow_239(int[] nums, int k) {
        // 维护大顶堆的时间复杂度为 O(logn)，总时间复杂度为 O(nlogn)
        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> o1[0] != o2[0] ? o2[0] - o1[0] : o2[1] - o1[1]);
        for (int i = 0; i < k; i++) {
            queue.offer(new int[]{nums[i], i});
        }
        int[] target = new int[nums.length - k + 1];
        target[0] = queue.peek()[0];

        for (int i = k; i < nums.length; i++) {
            queue.offer(new int[]{nums[i], i});
            // 移出不满足窗口内条件的元素
            while (queue.peek()[1] <= i - k) {
                queue.poll();
            }
            target[i - k + 1] = queue.peek()[0];
        }
        return target;
    }

    public int[] xxx_maxSlidingWindow_v2(int[] nums, int k) {
        // 重点在于，跟上门的 “移出k个数字后剩下的最小” 思路一样，
        // 维护一个双端队列来保持递减（即把小于当前值的全部移除，后续肯定也不会有机会出头了），然后置于队尾
        int n = nums.length;
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < k; ++i) {
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
        }
        int[] ans = new int[n - k + 1];
        ans[0] = nums[deque.peekFirst()];
        for (int i = k; i < n; ++i) {
            // 1、新加入的值如果大于队头，则代表在后面的窗口内队头数据再也不会是最大值
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
            // 2、只需要看最大值在不在区间内即可
            while (deque.peekFirst() <= i - k) {
                deque.pollFirst();
            }
            ans[i - k + 1] = nums[deque.peekFirst()];
        }
        return ans;
    }

//    public static void main(String[] args) {
//        Top top = new Top();
//        int[] nums = {1, 3, 2, 5, 4, 3, 7, 5, 6, 8};
//        int[] res = top.xxx_maxSlidingWindow_v2(nums, 3);
//        for (int i = 0; i < res.length; i++) {
//            System.out.println(res[i]);
//        }
//    }

    /**
     * 236.给定一个二叉树, 找到该树中两个指定节点的最近公共祖先
     *
     * @param cur
     * @param p
     * @param q
     * @return
     */
    public TreeNode xxx_lowestCommonAncestor_263(TreeNode cur, TreeNode p, TreeNode q) {
        return lowestCommonAncestor(cur, p, q);
    }

    private TreeNode lowestCommonAncestor(TreeNode cur, TreeNode p, TreeNode q) {
        if (cur == null || cur == p || cur == q) {
            return cur;
        }
        // 1、先左侧节点一撸到底；2、再逐步回塑右侧节点；3、在当前节点视角判断子树下是否存在指定节点，存在两则返回cur存在一则返回left/right都不存在则null
        TreeNode left = lowestCommonAncestor(cur.left, p, q);
        TreeNode right = lowestCommonAncestor(cur.right, p, q);
        // ！！！后序遍历：cur节点的左右子树是否存在指定节点，不存在即返回另一个节点都存在则返回当前节点。然后一直把节点往上回溯比较！！！
        if (left == null) {
            // left为空：说明当前节点的左子树一撸到底没有遇到指定节点，那就在右节点则直接返回右节点值
            return right;
        }
        if (right == null) {
            // right为空：说明当前节点的右子树一撸到底没有遇到指定节点，那就在左节点则直接返回左节点值
            return left;
        }
        // 如果left和right都不为空：说明这两个节点一个在cur的左子树上一个在cur的右子树上
        // return的数值代表啥？？？ - 代表当前节点可能所存在的树
        return cur;
    }

//    public static void main(String[] args) {
//        TreeNode treeNode7 = new TreeNode(7);
//        TreeNode treeNode8 = new TreeNode(8);
//        TreeNode treeNode6 = new TreeNode(6);
//        TreeNode treeNode5 = new TreeNode(5, treeNode7, treeNode8);
//        TreeNode treeNode4 = new TreeNode(4,null, treeNode6);
//        TreeNode treeNode3 = new TreeNode(3);
//        TreeNode treeNode2 = new TreeNode(2, treeNode4, treeNode5);
//        TreeNode treeNode1 = new TreeNode(1, treeNode2, treeNode3);
//        Top top = new Top();
//        System.out.println(top.xxx_lowestCommonAncestor(treeNode1, treeNode3, treeNode8).val);
//    }

    /**
     * 230.给定一个二叉搜索树的根节点 root 和 一个整数 k ，请你设计一个算法查找其中第 k 个最小元素（从 1 开始计数）
     *
     * @param root
     * @param k
     * @return
     */
    private int kth_smallest_res = 0;
    int rank = 0;

    private int x_kthSmallest_230(TreeNode root, int k) {
        if (root == null) {
            return 0;
        }
        traverse(root, k);
        return kth_smallest_res;
    }

    private void traverse(TreeNode root, int k) {
        if (root == null) {
            return;
        }
        traverse(root.left, k);
        // 中序遍历先右-根-左，从小到大，依次排列
        rank++;
        if (k == rank) {
            kth_smallest_res = root.val;
            return;
        }
        traverse(root.right, k);
    }

    /**
     * 221.在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积
     *
     * @param matrix
     * @return
     */
    public int x_maximalSquare_221(char[][] matrix) {
        int m = matrix.length, n = matrix[0].length, a = 0;
        // 二维数组dp，dp(i,j)表示以matrix(i,j)为右下角的只包含“1“的正方形的最大边长
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = matrix[i][j] - '0';
                } else if (matrix[i][j] == '1') {
                    // 正方形的右下角要取决于其上方、左侧、左上方三个单元格的情况，是三者所构成正方形最大边长的最小值+1
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j])) + 1;
                }
                a = Math.max(a, dp[i][j]);
            }
        }
        return a * a;
    }

    /**
     * 216.找出所有相加之和为 n 的 k 个数的组合，且满足下列条件：
     * 1、只使用数字1到9;
     * 2、每个数字最多使用一次
     * 所有可能的有效组合的列表，该列表不能包含相同的组合两次，组合可以以任何顺序返回
     *
     * @param k
     * @param n
     * @return
     */
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> list = new ArrayList<>();

    public List<List<Integer>> combinationSum3_216(int k, int n) {
        for (int j = 1; j < 9; j++) {
            list.add(j);
            combinationSum3DFS(n - j, k - 1, j, list);
            list.remove(list.size() - 1);
        }
        return res;
    }

    private void combinationSum3DFS(int n, int k, int i, List<Integer> list) {
        if (n < 0) {
            return;
        }
        if (n == 0 && k == 0) {
            res.add(new ArrayList<>(list));
            return;
        }
        if (k == 0) {
            return;
        }
        for (int j = i + 1; j < 9; j++) {
            if (n < j) {
                continue;
            }
            list.add(j);
            combinationSum3DFS(n - j, k - 1, j, list);
            list.remove(list.size() - 1);
        }
    }

//    public static void main(String[] args) {
//        System.out.println(JSON.toJSONString(combinationSum3(3, 9)));
//    }

    /**
     * 214. 给定一个字符串 s，你可以通过在字符串前面添加字符将其转换为回文串，找到并返回可以用这种方式转换的最短回文串
     * <p>
     * "aacecaaa" ==》"aaacecaaa"
     * "abcd" ==》"dcbabcd"
     *
     * @param s
     * @return
     */
    public String xxx_shortestPalindrome(String s) {
//        return shortestPalindrome_v1(s);
        return null;
    }


    private String shortestPalindrome_v1(String s) {
        // 核心：怎么找到[0,i]区间回文，可以从尾部开始的
        int i = 0, j = s.length() - 1;
        char[] c = s.toCharArray();
        // 1、找到 [0,i] 区间回文，只能死记硬背？
        while (j >= 0) {
            if (i == j) {
                continue;
            }
            if (c[i] == c[j]) {
                i++;
            }
            j--;
        }
        if (i == s.length()) {
            return s;
        }
        // 2、后缀倒置[i,length]
        String suffix = s.substring(i);
        String reverse = new StringBuilder(suffix).reverse().toString();
        // 3、拼接整个
        return reverse + s;
    }

//    public static void main(String[] args) {
//        DoitTop doitTop = new DoitTop();
//        System.out.println(doitTop.shortestPalindrome_v1("aacecada"));
//    }


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
    public int xxx_rob_337(TreeNode root) {
        int[] rootStatus = rob_dfs(root);
        return Math.max(rootStatus[0], rootStatus[1]);
    }

    private int[] rob_dfs(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0};
        }
        // 当前节点下有两条路可走：选它则左右都不选情况下最大值、不选它则左右都可选可不选，所以要先左右再根的后序遍历方式
        int[] l = rob_dfs(node.left);
        int[] r = rob_dfs(node.right);
        // 1、节点选中：当前节点值 + 左未 + 右未
        int selected = node.val + l[1] + r[1];
        // 2、节点未选中：左右都可选可不选
        int notSelected = Math.max(l[0], l[1]) + Math.max(r[0], r[1]);
        return new int[]{selected, notSelected};
    }

    /**
     * 213. 第一个房屋和最后一个房屋是紧挨着的，同时相邻的房屋装有相互连通的防盗系统。如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警，在不触动警报装置的情况下今晚能够偷窃到的最高金额
     *
     * [1,3,1] ==> 3
     *
     * @param nums
     * @return
     */
    public static int x_rob_213(int[] nums) {
        if (nums.length < 2) {
            return nums[0];
        }
        // 动态规划看n-2的大小
        int[] dp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            dp[i] = nums[i];
        }
        int max = Math.max(dp[0], dp[1]);
        dp[1] = max;
        for (int i = 2; i < nums.length; i++) {
            // 对于第i晚有两个选择：不偷（同前晚金额），或者 偷了（前前晚+今偷的）
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
            max = Math.max(max, dp[i]);
        }
        return max;
    }

//    public static void main(String[] args) {
//        System.out.println(x_rob_213(new int[]{1, 3, 4, 1}));
//    }


    /**
     * 207. 你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 ， prerequisites[i] = [ai, bi] ，表示如果要学习课程ai则必须先学习课程bi，是否能完成学习
     *
     * numCourses = 2, prerequisites = [[1,0]] ==> true
     * numCourses = 2, prerequisites = [[1,0],[0,1]] ==> false
     *
     * @param numCourses
     * @param prerequisites
     * @return 是否可能完成所有课程的学习
     */
    List<List<Integer>> edges;
    int[] visited;
    boolean valid = true;

    public boolean xxx_canFinish_207(int numCourses, int[][] prerequisites) {
        edges = new ArrayList<>();
        for (int i = 0; i < numCourses; ++i) {
            edges.add(new ArrayList<>());
        }
        visited = new int[numCourses];
        for (int[] info : prerequisites) {
            edges.get(info[1]).add(info[0]);
        }
        // 课程的序号记为 0 到 nums - 1，遍历所有课程
        for (int i = 0; i < numCourses && valid; ++i) {
            // visited[i]: 0代表还没开始，1是进行中如果重复判断则有环，2是完成也不需要再判断
            if (visited[i] == 0) {
                canFinishdfs(i);
            }
        }
        return valid;
    }

    private void canFinishdfs(int u) {
        visited[u] = 1;
        for (int v : edges.get(u)) {
            if (visited[v] == 0) {
                canFinishdfs(v);
                if (!valid) {
                    return;
                }
            } else if (visited[v] == 1) {
                // 课程完成中却再次访问节点，则说明有环存在。不能完成
                valid = false;
                return;
            }
        }
        visited[u] = 2;
    }

    // 广度遍历算法
    int[] indeg;

    private boolean canFinish(int numCourses, int[][] prerequisites) {
        edges = new ArrayList<>();
        for (int i = 0; i < numCourses; ++i) {
            edges.add(new ArrayList<>());
        }
        indeg = new int[numCourses];
        for (int[] info : prerequisites) {
            edges.get(info[1]).add(info[0]);
            ++indeg[info[0]];
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; ++i) {
            if (indeg[i] == 0) {
                queue.offer(i);
            }
        }
        int visited = 0;
        while (!queue.isEmpty()) {
            ++visited;
            int u = queue.poll();
            for (int v : edges.get(u)) {
                --indeg[v];
                if (indeg[v] == 0) {
                    queue.offer(v);
                }
            }
        }
        return visited == numCourses;
    }


    /**
     * 199.给定一个二叉树的根节点root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值
     *
     * [1,2,3,null,5,null,4] ==> [1,3,4]
     *
     * @param root 2024-02-27
     * @return
     */
    public List<Integer> x_rightSideView_199(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        // 队列先进先出，维护每层的叶子数据
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            // 当前层的叶子数，当叶子先进先出后仅剩下一个节点的时候，就是右侧节点
            int size = queue.size();
            while (size > 0) {
                // 下层的左右叶子数，继续加入到队列
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                if (size == 1) {
                    res.add(node.val);
                }
                size--;
            }
        }
        return res;
    }


    /**
     * 172.给定一个整数 n ，返回 n! 结果中尾随零的数量
     *
     * @param n 2024-02-26
     * @return
     */
    public int trailingZeroes_172(int n) {
        // 找到数字中因子5的数量就是后面0的次数，因为2*5=10而2的肯定会比5多，所以看5的就行
        int ans = 0;
        for (int i = 5; i <= n; i += 5) {
            int temp = i;
            while (temp % 5 == 0) {
                ans++;
                temp /= 5;
            }
        }
        return ans;
    }

    /**
     * 167. 给你一个下标从 1 开始的整数数组 numbers ，该数组已按非递减顺序排列，请你从数组中找出满足相加之和等于目标数target的两个数
     *
     * numbers = [2,7,11,15], target = 9 ==> [1,2]
     * numbers = [2,3,4], target = 6 ==> [1,3]
     *
     * @param numbers 2024-02-26
     * @param target
     * @return
     */
    public int[] twoSum_167(int[] numbers, int target) {
        for (int i = 0; i < numbers.length - 1; i++) {
            int left = i + 1, right = numbers.length - 1, mid;
            while (left <= right) {
                mid = (right - left) / 2 + left;
                if (numbers[mid] > target - numbers[i]) {
                    right = mid - 1;
                } else if (numbers[mid] < target - numbers[i]) {
                    left = mid + 1;
                } else {
                    return new int[]{i + 1, mid + 1};
                }
            }
        }
        return new int[0];
    }

    public int[] twoSum_v2(int[] numbers, int target) {
        int left = 0, right = numbers.length - 1;
        while (numbers[left] != target - numbers[right]) {
            if (numbers[left] > target - numbers[right]) {
                right--;
            } else {
                left++;
            }
        }
        return new int[]{left + 1, right + 1};
    }

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
     * 162.峰值元素是指其值严格大于左右相邻值的元素。给你一个整数数组 nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可
     * <p>
     * [1,2,3,1] -> 2
     * [1,2,1,3,5,6,4] -> 1 或 5
     *
     * @param nums
     * @return
     */
    public int x_findPeakElement_162(int[] nums) {
        // 必须实现时间复杂度为 O(log n) 的算法来解决此问题。
        // 二分法：往上走的一定有波峰
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            // 往大一侧走，一定能找到波峰
            if (nums[mid] > nums[mid + 1]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return right;
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
    public int xxx_lengthOfLongestSubstringTwoDistinct_159(String s) {
        // 暴力循环：每个节点不断回退前两个不同点，时间复杂度O(n^2)
        // 左右指针：保证left-right指针区间内的数据类型==2,通过左右指针差求最大值
        // 滑动窗口:利用数组集合代替map,保持窗口内类型在2否则一直移动 -- 题目有点精妙

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
        // 要在right-left间保持最大值，也就是说窗口只会大不会小。如果右侧进来相同的右移动，否则左右都移动
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
     * 154.数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次的结果为数组 [a[n-2], a[n-1], a[0], a[1], a[2], ..., a[n-3]]
     *
     * 给你一个元素值互不相同的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的最小元素
     *
     * @param nums 2024-02-22
     * @return
     */
    public static int xx_findMin_154(int[] nums) {
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

        // 这么简单。。。是我想复杂了？？？
        int low = 0;
        int high = nums.length - 1;
        while (low < high) {
            // 不用和下一个比，和最后一个比或者第一个比
            int pivot = low + (high - low) / 2;
            if (nums[pivot] < nums[high]) {
                high = pivot;
            } else if (nums[pivot] > nums[high]) {
                low = pivot + 1;
            } else {
                // pivot = high，high往向前走一步
                high -= 1;
            }
        }
        return nums[low];
    }

//    public static void main(String[] args) {
//        int[] nums = {1, 3, 3};
//        System.out.println(findMin_154(nums));
//    }


    /**
     * 152.给你一个整数数组 nums ，请你找出数组中乘积最大的非空连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积
     *
     * nums = [2,3,-2,4] ==> 6(2 * 3)
     * [-3,-1,-1]
     * [-2,3,-4]
     *
     * @param nums 2024-02-22
     * @return
     */
    public static int x_maxProduct_152(int[] nums) {
        int maxF = nums[0], minF = nums[0], ans = nums[0];
        int length = nums.length;
        for (int i = 1; i < length; ++i) {
            // 解决负负得正问题，最大和最小值都乘上nums[i]然后比较大小
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
     *
     * [4,2,7,5,9] -> [2,4,5,7,9]
     *
     * @param head 2024-02-22 again
     * @return
     */
    public ListNode xxx_sortList_148(ListNode head) {
        return sortList(head);
    }

    private ListNode sortList(ListNode head){
        // 重点在于，归并排序：数组不断按中间节点分割成最小，然后递归合并排序。先归一再合并
        if (head == null || head.next == null) {
            return head;
        }
        ListNode middle = findMiddleNode(head);
        ListNode rightHead = middle.next;
        middle.next = null;
        ListNode left = sortList(head);
        ListNode right = sortList(rightHead);
        return mergeListNode(left, right);
    }

    private ListNode findMiddleNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow = head;
        // 因为要从中间分成两截，跟正常的快慢不一样，fast提前走保证middle在中间点左侧
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
//        DoitTop top = new DoitTop();
//        top.xxxx_sortList_148(new ListNode(3, new ListNode(5, new ListNode(2, new ListNode(1, new ListNode(6, new ListNode(10, new ListNode(9, new ListNode(11, new ListNode(4, new ListNode(7)))))))))))
//                .print();
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

    public static boolean x_wordBreak_139(String s, List<String> wordDict) {
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
    public void reorderList_143(ListNode head) {
        // 涉及到链表3个知识：先通过快慢指针找到中间点、再反转后半部分链表、最后做前后插入
        // 1、找到中间节点
        ListNode middle = findMiddle(new ListNode(0, head));
        // 2、反转slow后到到尾部的节点
        ListNode last = reversal(middle);
        // 3、插入
        insertLast(head, last);
    }

    private ListNode findMiddle(ListNode dummy) {
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

    private ListNode reversal(ListNode head) {
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

    private void insertLast(ListNode head, ListNode last) {
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
     * 137.给你一个整数数组 nums ，除某个元素仅出现一次外，其余每个元素都恰出现三次，返回那个只出现了一次的元素
     *
     * @param nums 2024-02-22
     * @return
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
     * 131.给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是回文串，所有可能的分割方案
     * <p>
     * s = "aab" ==》[["a","a","b"],["aa","b"]]
     *
     * @param s 2024-02-20
     * @return s
     */

    boolean[][] f;
    List<List<String>> ret = new ArrayList<>();
    List<String> ans = new ArrayList<>();
    int n;

    public List<List<String>> xxx_partition_131(String s) {
        // 重点在于，找到字符串内所有的回文后，再深度递归
        n = s.length();
        f = new boolean[n][n];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(f[i], true);
        }
        // 1、找到字符的回文串（对比下面方式，推荐这种方式 简答明了）
        for (int i = n - 1; i >= 0; --i) {
            // 倒着来，那么 i + 1 和 j - 1 都能在这前遍历过
            for (int j = i + 1; j < n; ++j) {
                f[i][j] = (s.charAt(i) == s.charAt(j)) && f[i + 1][j - 1];
            }
        }
        // 2、深度递归树找到[i,j]
        partition_dfs(s, 0);
        return ret;
    }

    private void partition_dfs(String s, int i) {
        if (i == n) {
            ret.add(new ArrayList<>(ans));
            return;
        }
        // for循环下一个树节点是从上一个的尾开始
        for (int j = i; j < n; ++j) {
            if (f[i][j]) {
                ans.add(s.substring(i, j + 1));
                partition_dfs(s, j + 1);
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
     * 128.给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度，O(n) 的算法解决此问题
     *
     * [100,4,200,1,3,2] ==》 4 [1, 2, 3, 4]
     *
     * @param nums 2024-02-20
     * @return
     */
    public int x_longestConsecutive_128(int[] nums) {
        // 连续数字..（遇到一下子没思路的题的时候可以尝试从暴力解法入手）
        Map<Integer, Boolean> map = new HashMap<>();
        for (int j : nums) {
            map.put(j, true);
        }
        // 遍历到每个数字的时候都看看后面一位的数字是否存在，保证时间复杂度在O(n)
        int max = 0;
        for (Map.Entry<Integer, Boolean> entry : map.entrySet()) {
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
     * 122.整数数组 prices ，其中 prices[i] 表示某支股票第 i 天的价格，在每一天，你可以决定是否购买/出售股票。你在任何时候最多只能持有一股股票，求最大利润
     * <p>
     * [7,1,5,3,6,4] ==> 7（1-5，3-6）
     * [1,2,3,4,5] ==> 4（1买5卖）
     * [7,6,4,3,1] ==> 0
     *
     * @param prices 2024-02-20
     * @return
     */
    public int maxProfit_122(int[] prices) {
        int[] dp = new int[prices.length];
        dp[0] = 0;
        for (int i = 1; i < prices.length; i++) {
            // dp[i] = dp[i-1] + 第i天赚的最大值（要么不买，要么卖出赚钱，这时候 i-1 天的成本已经算在了 i 天所以i的就是纯利）
            dp[i] = dp[i - 1] + Math.max(0, prices[i] - prices[i - 1]);
        }
        return dp[prices.length - 1];
    }

//    public static void main(String[] args) {
//        DoitTop lcTop202305 = new DoitTop();
//        int[] prices = {7,1,5,3,6,4};
//        System.out.println(lcTop202305.maxProfit_122(prices));
//    }


    /**
     * 120.三角形 triangle ，找出自顶向下的最小路径和，每一步只能移动到下一行中相邻的结点上
     *
     * triangle = [[2],[3,4],[6,5,7],[4,1,8,3]] ==> 11（2-3-5-1）
     *
     * @param triangle
     * @return
     */
    public int minimumTotal_120(List<List<Integer>> triangle) {
        // 相邻结点即如果正位于当前行的下标 i 那么下一步可以移动到下一行的下标 i 或 i + 1
        if (triangle == null || triangle.size() == 0) {
            return 0;
        }
        // 最优解法：1、动态规划时间复杂度高；
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
     * 103. 给你二叉树的根节点 root ，返回其节点值的锯齿形层序遍历（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）
     * <p>
     * root = [3,9,20,null,null,15,7] ==》[ [3], [20,9], [15,7] ]
     *
     * @param root
     * @return
     */
    public static List<List<Integer>> xx_zigzagLevelOrder_103(TreeNode root) {
        // note：不用自己处理锯齿，借用双端队列的offerLast、offerFirst来处理左右端节点
        List<List<Integer>> ans = new LinkedList<>();
        if (root == null) {
            return ans;
        }
        boolean isOrderLeft = true;
        // tips: Queue队列一般用LinkedList实现，有可快速插入删除支持大数据等好处
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.offer(root);
        while (!nodeQueue.isEmpty()) {
            // step1：用了一个双向队列Deque存储结果，都是从左到右遍历 然后 利用双端队列选择插左还是插右
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
     * 92. 单链表的 头指针 head 和 两个整数left 和 right ，其中 left <= right ，反转从位置left到位置right的链表节点
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
     * 77.  给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的k个数的组合
     * <p>
     * n = 5, k = 2  --> [[1, 2], [1, 3], [1, 4], [1, 5], [2, 3], [2, 4], [2, 5], [3, 4], [3, 5], [4, 5]]
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
//        DoitTop top = new DoitTop();
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
     * <p>
     * grid = [[1,3,1],[1,5,1],[4,2,1]] ==> 7
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
     *
     * intervals = [[1,3],[2,6],[8,10],[15,18]] ==> [[1,6],[8,10],[15,18]]
     *
     * @param intervals
     * @return
     */
    public int[][] merge_56(int[][] intervals) {
        // *** 死办法：先排序，再左右区间比较大小合并 ***
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
     *
     * nums = [-2,1,-3,4,-1,2,1,-5,4] ==》[4,-1,2,1] = 6
     *
     * @param nums
     * @return 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解
     */
    public int maxSubArray_53(int[] nums) {
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
     * <p>
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
            // 在这一层的树上 防止出现 [1,2,2,1] 两次121的情况
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
     * <p>
     * candidates = [2,3,6,7], target = 7 ==> [ [2,2,3], [7] ]
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
        // 第一步：排序。减少减枝的复杂度
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
        // 第二步：从begin开始，遍历树枝的时候可以把前面遍历过的剪了
        for (int index = begin; index < candidates.length; index++) {
            if (target < 0) {
                break;
            }
            path.addLast(candidates[index]);
            combinationDFS(candidates, index, target - candidates[index], ans, path);
            // 第三步：开始循环的下一个节点前把前面的移除了，因为这个节点已经添加到结果列表了
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
     * 34.给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target，请你找出给定目标值在数组中的开始位置和结束位置，必须设计并实现时间复杂度为 O(log n) 的算法解决此问题
     * <p>
     * nums = [5,7,7,8,8,10], target = 8 ==》[3,4]
     * nums = [5,7,7,8,8,10], target = 6 ==》[-1,-1]
     *
     * @param nums
     * @param target
     * @return
     */
    private int[] x_searchRange_34(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            // 找到 mid 左右都是的
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
            // 继续 mid 找
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
     *
     * 如 [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 给你旋转后的数组 nums 和一个整数 target ，
     * 如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1，必须设计一个时间复杂度为 O(log n) 的算法解决此问题
     *
     * @param nums
     * @param target
     * @return
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
            // 第一步：一切为二后任意一个节点是一定会有一侧是顺序的，看 nums[0] 到 nums[mid] 是否顺序
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
     * 31.下一个排列：指其整数的下一个字典序更大的排列
     * <p>
     * 例如，arr = [1,2,3] 的下一个排列是 [1,3,2] 。 类似地，arr = [2,3,1] 的下一个排列是 [3,1,2]
     *
     * @param nums
     */
    public void xxx_nextPermutation(int[] nums) {
        if (nums == null || nums.length <= 0) {
            return;
        }

        int i = nums.length - 2;

        // 从后往前寻找第一个满足 a[i] < a[i + 1] 的顺序对
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            --i;
        }

        if (i >= 0) {
            int j = nums.length - 1;
            // 在 [i + 1, n) 区间，从后往前找第一个元素 j, 满足 a[i] < a[j]
            // 能走到这，说明一定存在一个满足条件的 j,所以不用担心 j 下标越界
            while (nums[i] >= nums[j]) {
                --j;
            }
            // 交换 i 和 j
            swap(nums, i, j);
        }
        // 反转 [i + 1, n) 区间的序列
        reverse(i + 1, nums);
    }

    private void reverse(int left, int[] nums) {
        int right = nums.length - 1;
        while (left < right) {
            swap(nums, left, right);
            ++left;
            --right;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }



    /**
     * 24.给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点
     *
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
        // 第1步：构造虚拟结点（涉及节点交换、移除的都可以考虑，这里主要第一个就要操作）
        ListNode dummy = new ListNode(0, head);
        // 第2步：dummy.next 和 next.next 交换
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
     *
     * 输入：n = 3 ==> 输出：["((()))","(()())","(())()","()(())","()()()"]
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesis_22_xxx(int n) {
        List<String> combinations = new ArrayList<>();
        // 暴力求解法
//        generateAll(new char[2 * n], 0, combinations);
        // 回溯法
        backtrack(combinations, new StringBuilder(), 0, 0, n);
        // 动态规划
        return combinations;
    }

//    private void generateAll(char[] current, int pos, List<String> result) {
//        if (pos == current.length) {
//            if (valid(current)) {
//                result.add(new String(current));
//            }
//        } else {
//            current[pos] = '(';
//            generateAll(current, pos + 1, result);
//            current[pos] = ')';
//            generateAll(current, pos + 1, result);
//        }
//    }
//
//    private boolean valid(char[] current) {
//        int balance = 0;
//        for (char c : current) {
//            if (c == '(') {
//                ++balance;
//            } else {
//                --balance;
//            }
//            if (balance < 0) {
//                return false;
//            }
//        }
//        return balance == 0;
//    }

    private void backtrack(List<String> ans, StringBuilder cur, int open, int close, int max) {
        // TODO 难理解。。每次都要先递归左分支再递归右分支
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


    public static void main(String[] args) {
        Train01 doitTop = new Train01();
        System.out.println(doitTop.generateParenthesis_22_xxx(3));
    }


    /**
     * 19：删除链表的倒数第 N 个结点，你能尝试使用一趟扫描实现吗
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd_19(ListNode head, int n) {
        // 第1步：虚拟个头节点。这是为了在落在N结点前一个（头指针为了数据）
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
     * 17.电话号码d的字母组合
     *
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
     * 16.给你一个长度为 n 的整数数组 nums 和 一个目标值 target。请你从 nums 中选出三个整数，使它们的和与 target 最接近，返回这三个数的和
     *
     * @param nums
     * @param target
     * @return
     */
    public int x_threeSumClosest_16(int[] nums, int target) {
        int len = nums.length;
        if (len <= 3) {
            return nums[0] + nums[1] + nums[2];
        }
        // 第一步，先排序
        Arrays.sort(nums);
        int best = Integer.MAX_VALUE;
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
     * 15.给你一个整数数组nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，同时还满足 nums[i] + nums[j] + nums[k] == 0，返回所有和为 0 且 不重复的三元组
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> x_threeSum_15(int[] nums) {
        // *** 难点在于如何去除重复解：可以排序后，保证每一层的循环节点nums[n]!=nums[n-1]。正常的三层循环时间复杂度是：O(n^3)，但这里我们可以通过「三指针」解决复杂度 ***
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
     * 11. 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水
     *
     *  [1,8,6,2,5,4,8,3,7] ==》49
     *
     * @param height
     * @return
     */
    public int maxArea_11(int[] height) {
        // 想在O(n)的事件范围内找到最大的，那么l、r的范围在缩小的时候就要小的前进一位保证结果最大化
        int l = 0, r = height.length - 1, ans = 0;
        while (l <= r) {
            int result = (r - l) * Math.min(height[l], height[r]);
            ans = Math.max(ans, result);
            // 谁小谁动
            if (height[l] > height[r]) {
                r--;
            } else {
                l++;
            }
        }
        return ans;
    }


    /**
     * 一个字符串s，找到s中最长的回文子串「如果字符串的反序与原始字符串相同，则该字符串称为回文字符串」
     *
     * eg. qabccbadd -> abccba
     *
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
        // *** 动态规划 dp[i][j] ==> s[i-1][j+1] ***
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
                        // L = 5 看 L = 3 的 是否满足
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
//        DoitTop top = new DoitTop();
//        System.out.println(top.longestPalindrome_dp("qabccbadd"));
//    }

    /**
     * 2.两数相加：两个非空的链表表示两个非负的整数。它们每位数字都是按照逆序的方式存储的，并且每个节点只能存储一位数字，将两个数相加并以相同形式返回一个表示和的链表
     *
     * [2,4,3] + [5,6,4] ==> [7,0,8] ； 342 + 465 = 807
     *
     * @param l1
     * @param l2
     * @return
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
