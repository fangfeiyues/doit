package com.fang.doit.algo.lc;

/**
 * 二分查找法
 *
 * @author fangfeiyue
 * @Date 2020/12/28 11:36 上午
 */
public class BinarySearch15 {


    /**
     * 34 给定一个按照升序排列的整数数组nums和一个目标值target。找出给定目标值在数组中的开始位置和结束位置.
     * O(log n) 解决
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        // 遍历的话O(n)能解决

        // 二分法O(log n)可以 相当于找到二分节点前最开始的和二分节点后最末的变种算法
        int[] result = new int[nums.length];
        int low = 0;
        int high = nums.length - 1;
        int k = 0;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (target == nums[mid]) {
                result[k] = mid;
                // 向前
                for (int i = mid - 1; i >= 0; i--) {
                    if (nums[i] == target) {
                        result[++k] = i;
                    } else {
                        break;
                    }
                }
                // 向后
                for (int j = mid + 1; j < nums.length; j++) {
                    if (nums[j] == target) {
                        result[++k] = j;
                    } else {
                        break;
                    }
                }
                break;
            }
            if (mid > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return result;
    }

    /**
     * 162 寻找峰值：峰值元素是指其值大于左右相邻值的元素。可以O(logN)解决吗
     *
     * @param nums
     * @return
     */
    public int findPeakElement(int[] nums) {
        // 1.滑动窗口判断中间值但时间复杂度在O(N)

        // 2.二分查看趋势 比较之后如下一个元素比mid值大则继续执行
        findPeakElementByBinary(nums);

        return 0;
    }

    public int findPeakElementByBinary(int[] nums) {
        return search(nums, 0, nums.length - 1);
    }

    public int search(int[] nums, int l, int r) {
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

    public static void main(String[] args) {
        BinarySearch15 binarySearch15 = new BinarySearch15();
        int[] nums = new int[]{5, 7, 7, 8, 8, 10};
        int[] result = binarySearch15.searchRange(nums, 8);
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }

    }

    /**
     * 230 查找二叉搜索树第k个最小的元素
     *
     * @param root
     * @param k
     * @return
     */
    public int kthSmallest(TreeNode root, int k) {
        // 1.中序遍历后得到有序数组 然后一次找到第K个数即可。时间复杂度O(n)

        // 2. 不断减枝方式避免重复计算
        kthSmallestByK(root, k);

        return 0;
    }


    int k = 0;
    int res = 0;

    public int kthSmallestByK(TreeNode root, int k) {
        this.k = k;
        helper(root);
        return res;
    }

    public void helper(TreeNode root) {
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

}
