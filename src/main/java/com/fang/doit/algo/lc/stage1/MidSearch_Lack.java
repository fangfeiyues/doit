package com.fang.doit.algo.lc.stage1;

import com.fang.doit.algo.tree.TreeNode;

/**
 * 二分查找
 *
 * @author fangfeiyue
 * @Date 2020/12/28 11:36 上午
 */
public class MidSearch_Lack {


    /**
     *  ------------------- 二分的解题技巧！！！ -----------------
     *
     *  ------------------------------------------------------
     */


    /**
     * 34 给定一个按照升序排列的整数数组nums 和 一个目标值target，找出给定目标值在数组中的开始位置和结束位置
     * O(log n) 解决
     * eg. 输入：nums = [5,7,7,8,8,10], target = 8 ==》输出：[3,4]
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange_01(int[] nums, int target) {
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
     * 162 寻找峰值：峰值元素是指其值大于左右相邻值的元素。可以O(logN)解决吗
     *
     * @param nums
     * @return
     */
    public int findPeakElement_02(int[] nums) {
        // 1.滑动窗口判断中间值但时间复杂度在O(N)

        // 2.二分查看趋势 比较之后如下一个元素比mid值大则继续执行
        findPeakElementByBinary(nums);

        return 0;
    }

    private int findPeakElementByBinary(int[] nums) {
        return search(nums, 0, nums.length - 1);
    }

    private int search(int[] nums, int l, int r) {
        if (l == r) {
            return l;
        }
        int mid = (l + r) / 2;
        // 这里简单的地方在于 只需要返回一个满足峰值条件的即可
        if (nums[mid] > nums[mid + 1]) {
            return search(nums, l, mid);
        }
        return search(nums, mid + 1, r);
    }


    /**
     * 230 查找二叉搜索树第k个最小的元素
     *
     * @param root
     * @param k
     * @return
     */
    public int kthSmallest_03(TreeNode root, int k) {
        // 1.中序遍历后得到有序数组 然后一次找到第K个数即可。时间复杂度O(n)

        // 2. 不断减枝方式避免重复计算
        kthSmallestByK(root, k);

        return 0;
    }


    int k = 0;
    int res = 0;

    private int kthSmallestByK(TreeNode root, int k) {
        this.k = k;
        helper(root);
        return res;
    }

    private void helper(TreeNode root) {
        //越过叶子节点，返回
        if (root == null) {
            return;
        }
        helper(root.left);
        //已找到第k大，剪枝
        if (k == 0) {
            return;
        }
        k--; //每递归一个节点，k-1
        if (k == 0) {
            // 得0时即找到第k大
            res = root.val;
        }
        helper(root.right);
    }

    /**
     * 33. 搜索旋转排序数组：在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了旋转，给你旋转后的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1
     * eg. nums = [4,5,6,7,0,1,2], target = 3 ==》-1
     *
     * @param nums
     * @param target
     * @return 你必须设计一个时间复杂度为 O(log n) 的算法解决此问题
     */
    public int revolveSearch_04(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) {
            return -1;
        }
        if (n == 1) {
            return nums[0] == target ? 0 : -1;
        }
        int l = 0, r = n - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            // ++ 一切为二后任意一个节点是一定会有一侧是顺序的，也就是看nums[0]到nums[mid]是否顺序即可
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
     * 81. revolveSearch_04 变种数组可能会存在相同的数字
     * [0,1,2,4,4,4,5,6,6,7] 在下标 5 处经旋转后可能变为 [4,5,6,6,7,0,1,2,4,4]
     *
     * @param nums   存在一个按非降序排列的整数数组 nums ，数组中的值不必互不相同
     * @param target
     * @return
     */
    public boolean revolveSearch_05(int[] nums, int target) {
        int length = nums.length;
        if (length == 0) {
            return false;
        }
        if (length == 1) {
            return nums[0] == target;
        }

        int l = 0, r = length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == target) {
                return true;
            }
            if (nums[0] == nums[mid] && mid > 0) {
                //
            }
        }
        return false;
    }

    /**
     * 287.给定一个包含 n + 1 个整数的数组 nums ，其数字都在 [1, n] 范围内（包括 1 和 n），可知至少存在一个重复的整数。假设 nums 只有 一个重复的整数 ，返回 这个重复的数
     * eg. nums = [3,1,3,4,2] ==>3
     *
     * @param nums
     * @return 你设计的解决方案必须 不修改 数组 nums 且只用常量级 O(1) 的额外空间
     */
    public int findDuplicate_06(int[] nums) {
        int slow = 0, fast = 0;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);
        slow = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }


    /**
     * 300.最长递增子序列 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度. 子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序
     * eg. nums = [10,9,2,5,3,7,101,18]  ==》[2,3,7,101]
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS_07_dddd(int[] nums) {

        // 动态规划

        // 贪心+二分查找

        return 0;
    }

    /**
     * 寻找峰值：峰值元素是指其值严格大于左右相邻值的元素
     * eg. [1,2,1,3,5,6,4] ==》1 或 5
     *
     * @param nums
     * @return 你必须实现时间复杂度为 O(log n) 的算法来解决此问题
     */
    public int findPeakElement_08_dd(int[] nums) {
        // ++ 「人往高处走，水往低处流」. 如果我们从一个位置开始，不断地向高处走，那么最终一定可以到达一个峰值位置。
        int n = nums.length;
        int left = 0, right = n - 1, ans = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (compare(nums, mid - 1, mid) < 0 && compare(nums, mid, mid + 1) > 0) {
                ans = mid;
                break;
            }
            if (compare(nums, mid, mid + 1) < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return ans;
    }

    // 辅助函数，输入下标 i，返回一个二元组 (0/1, nums[i])
    // 方便处理 nums[-1] 以及 nums[n] 的边界情况
    public int[] get(int[] nums, int idx) {
        if (idx == -1 || idx == nums.length) {
            return new int[]{0, 0};
        }
        return new int[]{1, nums[idx]};
    }

    public int compare(int[] nums, int idx1, int idx2) {
        int[] num1 = get(nums, idx1);
        int[] num2 = get(nums, idx2);
        if (num1[0] != num2[0]) {
            return num1[0] > num2[0] ? 1 : -1;
        }
        if (num1[1] == num2[1]) {
            return 0;
        }
        return num1[1] > num2[1] ? 1 : -1;
    }


    public static void main(String[] args) {
        MidSearch_Lack midSearch15Lack = new MidSearch_Lack();
        int[] nums = new int[]{1, 2, 3};
        int[] result = midSearch15Lack.searchRange_01(nums, 1);
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
    }


}
