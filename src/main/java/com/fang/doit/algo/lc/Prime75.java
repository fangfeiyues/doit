package com.fang.doit.algo.lc;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author : fangfeiyue
 * @version V1.0
 * @Project: doit
 * @Package com.fang.doit.algo.lc
 * @Description:
 * @date Date : 2024-07-19 00:27
 */
public class Prime75 {

    /**
     * 2390. 从字符串中移除星号， 给你一个包含若干星号*的字符串s，在一步操作中，你可以选中s中的一个星号、移除星号左侧最近的那个非星号字符，并移除该星号自身，返回移除所有星号之后的字符串
     * 注意： 生成的输入保证总是可以执行题面中描述的操作；可以证明结果字符串是唯一的
     *
     * <p>
     * 输入：s = "leet**cod*e"
     * 输出："lecoe"
     *
     * 输入：s = "erase*****"
     * 输出：""
     * @param s
     * @return
     */
    public static String removeStars(String s) {
        // 栈存放，遇到*则移除一个字符
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '*' && !stack.isEmpty()){
                stack.pop();
                continue;
            }
            stack.push(s.charAt(i));
        }

        // 反转stack
        StringBuilder res = new StringBuilder();
        while (!stack.isEmpty()){
            res.append(stack.pop().toString());
        }
        return res.reverse().toString();
    }


    /**
     * 735 小行星碰撞
     * 给定一个整数数组 asteroids，表示在同一行的小行星。对于数组中的每一个元素，其绝对值表示小行星的大小，正负表示小行星的移动方向（正表示向右移动，负表示向左移动）。每一颗小行星以相同的速度移动，找出碰撞后剩下的所有小行星
     * 碰撞规则：两个小行星相互碰撞，较小的小行星会爆炸。如果两颗小行星大小相同，则两颗小行星都会爆炸。两颗移动方向相同的小行星，永远不会发生碰撞
     * <p>
     * 输入：asteroids = [5,10,-5] ==》输出：[5,10]  解释：10 和 -5 碰撞后只剩下 10 。 5 和 10 永远不会发生碰撞
     * 输入：asteroids = [8,-8] ==》输出：[]  解释：8 和 -8 碰撞后，两者都发生爆炸。
     * 输入：asteroids = [10,2,-5] ==》输出：[10] 解释：2 和 -5 发生碰撞后剩下 -5 。10 和 -5 发生碰撞后剩下 10
     *
     * @param asteroids
     * @return
     */
    public int[] asteroidCollision(int[] asteroids) {

        //

        return new int[]{};
    }


    /**
     * 1657. 确定两个字符串是否接近
     * <p>
     * 如果可以使用以下操作从一个字符串得到另一个字符串，则认为两个字符串 接近 ：
     * 操作 1：交换任意两个 现有 字符。例如，abcde -> aecdb
     * 操作 2：将一个 现有 字符的每次出现转换为另一个 现有 字符，并对另一个字符执行相同的操作。例如，aacabb -> bbcbaa（所有 a 转化为 b ，而所有的 b 转换为 a ）
     * <p>
     * 输入：word1 = "abc", word2 = "bca"
     * 输出：true
     *
     * @param word1
     * @param word2
     * @return
     */
    public boolean closeStrings(String word1, String word2) {
        int[] count1 = new int[26], count2 = new int[26];
        for (char c : word1.toCharArray()) {
            count1[c - 'a']++;
        }
        for (char c : word2.toCharArray()) {
            count2[c - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (count1[i] > 0 && count2[i] == 0 || count1[i] == 0 && count2[i] > 0) {
                return false;
            }
        }
        Arrays.sort(count1);
        Arrays.sort(count2);
        return Arrays.equals(count1, count2);
    }


    /**
     * 2352. 相等行列对
     * <p>
     * 给你一个下标从 0 开始、大小为 n x n 的整数矩阵 grid ，返回满足 Ri 行和 Cj 列相等的行列对 (Ri, Cj) 的数目。
     * 如果行和列以相同的顺序包含相同的元素（即相等的数组），则认为二者是相等的
     * <p>
     * 输入：grid = [[3,2,1],[1,7,6],[2,7,7]]
     * 输出：1
     * 解释：存在一对相等行列对：
     * - (第 2 行，第 1 列)：[2,7,7]
     * <p>
     * <p>
     * 输入：grid = [[3,1,2,2],[1,4,4,5],[2,4,2,2],[2,4,2,2]]
     * 输出：3
     * 解释：存在三对相等行列对：
     * - (第 0 行，第 0 列)：[3,1,2,2]
     * - (第 2 行, 第 2 列)：[2,4,2,2]
     * - (第 3 行, 第 2 列)：[2,4,2,2]
     *
     * @param grid
     * @return
     */
    public int equalPairs(int[][] grid) {

        return 0;
    }

    /**
     * 1004. 最大连续1的个数，给定一个二进制数组 nums 和一个整数 k，如果可以翻转最多 k 个 0 ，则返回 数组中连续 1 的最大个数
     * <p>
     * 输入：nums = [1,1,1,0,0,0,1,1,1,1,0], K = 2 ==》 6  [1,1,1,0,0,1,1,1,1,1,1]
     * 输入：nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3 ==》 10  [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
     *
     * @param nums
     * @param k
     * @return
     */
    public static int longestOnes(int[] nums, int k) {
        int left = 0, right = 0, count = 0;
        while (right < nums.length) {
            // 右区间：一直推进，但可能 k 不够
            if (nums[right] == 0 && k >= 0) {
                k--;
            }
            // 左区间：k<0说明要还回去一个
            while (k < 0) {
                if (nums[left++] == 0) {
                    k++;
                }
            }
            // 保证左右两边都是1，再计算区间
            count = Math.max(count, right - left + 1);
            right++;
        }
        return count;
    }


    /**
     * 1493.删除一个元素后全为1的最长子数组，给你一个二进制数组 nums ，你需要从中删掉一个元素，请你在删掉元素的结果数组中，返回最长的且只包含 1 的非空子数组的长度。如果不存在这样的子数组，请返回 0
     *
     * 提示 1：
     *
     * 输入：nums = [1,1,0,1]
     * 输出：3
     * 解释：删掉位置 2 的数后，[1,1,1] 包含 3 个 1 。
     * 示例 2：
     *
     * 输入：nums = [0,1,1,1,0,1,1,0,1]
     * 输出：5
     * 解释：删掉位置 4 的数字后，[0,1,1,1,1,1,0,1] 的最长全 1 子数组为 [1,1,1,1,1] 。
     * 示例 3：
     *
     * 输入：nums = [1,1,1]
     * 输出：2
     * 解释：你必须要删除一个元素。
     *
     * @param nums
     * @return
     */
//    public static int longestSubarray(int[] nums) {
//        int left = 0, right = 0, count = 0, len = 0;
//        while (right < nums.length) {
//
//            if (nums[right] != 1) {
//                count++;
//            }
//
//            while (count > 1) {
//                if (nums[left++] != 1) {
//                    count--;
//                }
//            }
//
//            len = Math.max(len, right - left - count);
//
//            right++;
//        }
//
//        if(count ==0){
//            len --;
//        }
//
//        return len;
//    }


    public int longestSubarray(int[] nums) {
        int ans = 0;
        int p0 = 0, p1 = 0;
        for (int num : nums) {
            if (num == 0) {
                p1 = p0;
                p0 = 0;
            } else {
                ++p0;
                ++p1;
            }
            ans = Math.max(ans, p1);
        }
        if (ans == nums.length) {
            --ans;
        }
        return ans;
    }

    /**
     * 1456. 定长子串中元音的最大数目
     * 给你字符串 s 和整数 k ，请返回字符串 s 中长度为 k 的单个子字符串中可能包含的最大元音字母数，英文中的 元音字母 为（a, e, i, o, u）
     *
     * 示例 1：
     *
     * 输入：s = "abciiidef", k = 3
     * 输出：3
     * 解释：子字符串 "iii" 包含 3 个元音字母。
     * 示例 2：
     *
     * 输入：s = "aeiou", k = 2
     * 输出：2
     * 解释：任意长度为 2 的子字符串都包含 2 个元音字母。
     * 示例 3：
     *
     * 输入：s = "leetcode", k = 3
     * 输出：2
     * 解释："lee"、"eet" 和 "ode" 都包含 2 个元音字母。
     * 示例 4：
     *
     * 输入：s = "rhythms", k = 4
     * 输出：0
     * 解释：字符串 s 中不含任何元音字母。
     * 示例 5：
     *
     * 输入：s = "tryhard", k = 4
     * 输出：1
     *
     * @param s
     * @param k
     * @return
     */
    public int maxVowels(String s, int k) {

        return 0;
    }

    /**
     * 1679. K和数对的最大数目，给你一个整数数组 nums 和一个整数 k，每一步操作中，你需要从数组中选出和为 k 的两个整数，并将它们移出数组，返回你可以对数组执行的最大操作数
     * <p>
     * 输入：nums = [1,2,3,4], k = 5 ==》2次  [1,4]、[2,3]
     * 输入：nums = [3,1,3,4,3], k = 6
     *
     * @param nums
     * @param k
     * @return
     */
    public int maxOperations(int[] nums, int k) {
        // 排序后双指针
        Arrays.sort(nums);
        int left = 0, right = nums.length - 1, count = 0;
        while (left < right) {
            int sum = nums[left] + nums[right];
            // 两者相同，则直接移除
            if (sum == k) {
                count++;
                left++;
                right--;
            } else if (sum < k) {
                left++;
            } else {
                right--;
            }
        }
        return count;
    }

    /**
     * 443.压缩字符串
     * 给你一个字符数组 chars ，请使用下述算法压缩：从一个空字符串 s 开始。对于 chars 中的每组连续重复字符：
     * 如果这一组长度为 1 ，则将字符追加到 s 中。否则，需要向 s 追加字符，后跟这一组的长度
     * 压缩后得到的字符串 s 不应该直接返回 ，需要转储到字符数组 chars 中
     * 需要注意的是，如果组长度为 10 或 10 以上，则在 chars 数组中会被拆分为多个字符
     * 请在 修改完输入数组后 ，返回该数组的新长度。
     * 你必须设计并实现一个只使用常量额外空间的算法来解决此问题
     *
     * 示例：
     *
     * 输入：chars = ["a","a","b","b","c","c","c"] ==》["a","2","b","2","c","3"]
     * 输入：chars = ["a","b","b","b","b","b","b","b","b","b","b","b","b"] ==》["a","b","1","2"]
     *
     *
     * @param chars
     * @return
     */
    public int compress(char[] chars) {



        return 0;
    }

    /**
     * 334. 递增的三元子序列
     * 给你一个整数数组 nums ，判断这个数组中是否存在长度为 3 的递增子序列。
     * 如果存在这样的三元组下标 (i, j, k) 且满足 i < j < k ，使得 nums[i] < nums[j] < nums[k] ，返回 true ；否则，返回 false
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [1,2,3,4,5]
     * 输出：true
     * 解释：任何 i < j < k 的三元组都满足题意
     * 示例 2：
     * <p>
     * 输入：nums = [5,4,3,2,1]
     * 输出：false
     * 解释：不存在满足题意的三元组
     * 示例 3：
     * <p>
     * 输入：nums = [2,1,5,0,4,6]
     * 输出：true
     * 解释：三元组 (3, 4, 5) 满足题意，因为 nums[3] == 0 < nums[4] == 4 < nums[5] == 6
     *
     * @param nums
     * @return
     */
    public boolean increasingTriplet(int[] nums) {
        if (nums.length < 3) {
            return false;
        }
        int len = nums.length;

        // 左小前缀 和 右大前缀 之间

        int[] left = new int[len];
        left[0] = nums[0];
        for (int i = 1; i < len; i++) {
            left[i] = left[i - 1];
            if (nums[i - 1] < left[i - 1]) {
                left[i] = nums[i - 1];
            }
        }

        int[] right = new int[len];
        right[len - 1] = nums[len - 1];
        for (int i = len - 2; i >= 0; i--) {
            right[i] = right[i + 1];
            if (nums[i + 1] > right[i + 1]) {
                right[i] = nums[i + 1];
            }
        }

        for (int i = 0; i < len; i++) {
            if (nums[i] > left[i] && nums[i] < right[i]) {
                return true;
            }
        }
        return false;
    }

    /**
     * 215.数组中的第K个最大元素，给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素，请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素
     * <p>
     * 你必须设计并实现时间复杂度为 O(n) 的算法解决此问题
     * <p>
     * [3,2,1,5,6,4], k = 2 ==》5
     * [3,2,3,1,2,4,5,5,6], k = 4 ==》 4
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {

        // 快排序
        findKthLargestByQuick(nums, k);

        // 堆排序
        findKthLargestByHeap(nums, k);

        return 0;
    }


    /**
     * 1、分解： 将数组 a[l⋯r] 「划分」成两个子数组 a[l⋯q−1]、a[q+1⋯r]，使得 a[l⋯q−1] 中的每个元素小于等于 a[q]，且 a[q] 小于等于 a[q+1⋯r] 中的每个元素
     *          其中，计算下标 q 也是「划分」过程的一部分
     *
     * 2、解决： 通过递归调用快速排序，对子数组 a[l⋯q−1] 和 a[q+1⋯r] 进行排序
     *
     * 3、合并： 因为子数组都是原址排序的，所以不需要进行合并操作，a[l⋯r] 已经有序
     *
     * 上文中提到的「划分」过程是：从子数组 a[l⋯r] 中选择任意一个元素 x 作为主元，调整子数组的元素使得左边的元素都小于等于它，右边的元素都大于等于它， x 的最终位置就是 q
     *
     * @param _nums
     * @param k
     * @return
     */
    public int findKthLargestByQuick(int[] _nums, int k) {
        int n = _nums.length;
        return quickSelect(_nums, 0, n - 1, n - k);
    }

    int quickSelect(int[] nums, int l, int r, int k) {
        if (l == r) {
            return nums[k];
        }
        // i 和 j 交叉后，即 x 在自己的位置上
        // [3,2,3,1,2,4,5,5,6], k = 4 ==》4
//        int x = nums[l], i = l - 1, j = r + 1;
        int x = nums[l], i = l, j = r;
        while (i < j) {
//            do i++; while (nums[i] < x);
//            do j--; while (nums[j] > x);
            while (nums[i] < x) {
                i++;
            }
            while (nums[j] > x) {
                j--;
            }
            if (i < j) {
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j] = tmp;
            }
        }
        if (k <= j) {
            return quickSelect(nums, l, j, k);
        } else {
            return quickSelect(nums, j + 1, r, k);
        }
    }


//    public static void main(String[] args) {
//        Prime75 prime75 = new Prime75();
//        System.out.println(prime75.findKthLargestByQuick(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 5));
//    }


    public int findKthLargestByHeap(int[] nums, int k) {
        int heapSize = nums.length;
        buildMaxHeap(nums, heapSize);
        for (int i = nums.length - 1; i >= nums.length - k + 1; --i) {
            swap(nums, 0, i);
            --heapSize;
            maxHeapify(nums, 0, heapSize);
        }
        return nums[0];
    }

    public void buildMaxHeap(int[] a, int heapSize) {
        // heapSize / 2
        for (int i = heapSize / 2; i >= 0; --i) {
            maxHeapify(a, i, heapSize);
        }
    }

    public void maxHeapify(int[] a, int i, int heapSize) {
        int l = i * 2 + 1, r = i * 2 + 2, largest = i;
        if (l < heapSize && a[l] > a[largest]) {
            largest = l;
        }
        if (r < heapSize && a[r] > a[largest]) {
            largest = r;
        }
        if (largest != i) {
            swap(a, i, largest);
            maxHeapify(a, largest, heapSize);
        }
    }

    public void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
