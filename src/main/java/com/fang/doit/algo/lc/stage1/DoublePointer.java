package com.fang.doit.algo.lc.stage1;

import com.fang.doit.algo.dst.linked.ListNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author : fangfeiyue
 * @version V1.0
 * @Project: doit
 * @Package com.fang.doit.algo.lc.stage1
 * @Description: 双指针
 * @date Date : 2023-05-04 12:33 上午
 */
public class DoublePointer {


    /**
     * 15. 三数之和 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，同时还满足 nums[i] + nums[j] + nums[k] == 0
     *
     * @param nums
     * @return 返回所有和为 0 且不重复的三元组
     */
    public List<List<Integer>> threeSum_01(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        for (int first = 0; first < n; ++first) {
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            int third = n - 1;
            int target = -nums[first];
            // +++双向指针：左右比较缩小时间复杂度
            for (int second = first + 1; second < n; ++second) {
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
     * 扩展两数求和
     *
     * @param nums
     * @param target
     * @return
     */
    private int[] twoSum(int[] nums, int target) {
        // 两数求和：map维护
        return new int[]{};
    }


    /**
     * 19. 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode first = head;
        ListNode second = dummy;
        for (int i = 0; i < n; ++i) {
            first = first.next;
        }
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        ListNode ans = dummy.next;
        return ans;
    }

    public void nextPermutation(int[] nums) {

    }

}
