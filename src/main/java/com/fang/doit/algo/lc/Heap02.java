package com.fang.doit.algo.lc;

import java.util.*;

/**
 * 堆 == 数组？？？
 * 无序数组
 * 1.第k个最大的元素
 * 2.出现频率前K
 * 3.大小为k的滑动窗口，路过的窗口内最大值集合
 * 4.字符出现的频率集合
 *
 * @author fangfeiyue
 * @Date 2020/11/29 3:33 下午
 */
public class Heap02 {


    // 堆

    /**
     * 215 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素
     * 输入: [3,2,1,5,6,4] 和 k = 2 ==》输出: 5
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargestByPriority(int[] nums, int k) {
        // 思路：1.数组排序再选择；2.快排思想用切割pivot方式(v)；3.二分切割+分治(X)；4.大顶推
        // 大小为k的小顶推
        PriorityQueue<Integer> queue = new PriorityQueue<>(nums.length);
        for (int num : nums) {
            queue.add(num);
            if (queue.size() > k) {
                queue.poll();
            }
        }
        return queue.peek();
    }

    /**
     * 快排思想即找到分区点pivot然后比较k值继续找
     * 解答失败: 测试用例:[3,2,1,5,6,4] 2 测试结果:6 期望结果:5 stdout:
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargestByPQuick(int[] nums, int k) {
        k = nums.length - k;
        int low = 0;
        int high = nums.length - 1;
        // 这里是'<=' 如果大小相等即low=high了说明到头了，否则会陷入死循环
        while (low <= high) {
            int i = low;
            for (int j = i + 1; j <= high; j++) {
                if (nums[j] < nums[low]) {
                    // 这里的i表示最接近pivot的值。如果小于的就换到i+1的位置上来（因为认为i+1位置是大于pivot的,i是最接近的那个）
                    swap(nums, j, ++i);
                }
            }
            swap(nums, low, i);
            if (i == k) {
                return nums[i];
            } else if (i > k) {
                high = i - 1;
            } else {
                low = i + 1;
            }
        }
        return -1;
    }

    private void swap(int[] nums, int a, int b) {
        // 在数组中的a&b交换位置
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }


    // --------------------------------


    /**
     * 347: 定一个非空的整数数组，返回其中出现频率前k高的元素。时间复杂度必须优于 O(n log n) , n 是数组的大小
     * 场景：某段时间的热度排行
     *
     * @param nums
     * @param k
     * @return
     */
    public List<Integer> topKFrequent(int[] nums, int k) {
        // 使用字典，统计每个元素出现的次数，元素为键，元素出现的次数为值
        HashMap<Integer, Integer> map = new HashMap();
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        // 遍历map，用最小堆保存频率最大的k个元素
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> map.get(a) - map.get(b));
        for (Integer key : map.keySet()) {
            if (pq.size() < k) {
                pq.add(key);
            } else if (map.get(key) > map.get(pq.peek())) {
                pq.remove();
                pq.add(key);
            }
        }
        // 取出最小堆中的元素
        List<Integer> res = new ArrayList<>();
        while (!pq.isEmpty()) {
            res.add(pq.remove());
        }
        return res;
    }


//    运行失败: Line 33: error: incompatible types: List  cannot be converted to int[] return res; ^ 测试用例:null stdout: null

    /**
     * Map + List<Integer>[]桶排序
     *
     * @param nums
     * @param k
     * @return
     */
    public List<Integer> topKFrequentByBucket(int[] nums, int k) {
        List<Integer> res = new ArrayList();
        // 使用字典，统计每个元素出现的次数，元素为键，元素出现的次数为值
        HashMap<Integer, Integer> map = new HashMap();
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        //桶排序
        //将频率作为数组下标，对于出现频率不同的数字集合，存入对应的数组下标
        List<Integer>[] list = new List[nums.length + 1];
        for (int key : map.keySet()) {
            // 获取出现的次数作为下标
            int i = map.get(key);
            if (list[i] == null) {
                list[i] = new ArrayList();
            }
            list[i].add(key);
        }

        // TODO 倒序遍历数组获取出现顺序从大到小的排列
        for (int i = list.length - 1; i >= 0 && res.size() < k; i--) {
            if (list[i] == null) {
                continue;
            }
            res.addAll(list[i]);
        }
        return res;
    }


    public static void main(String[] args) {
        Heap02 heap02 = new Heap02();
        int[] nums = {1, 1, 1, 1, 2, 2, 3, 4, 4, 5, 4, 4, 1};
        System.out.println(heap02.topKFrequentByBucket(nums, 1));
//        int[] array = {3, 2, 4, 5, 1, 7, 10};
    }


    // ------------------------------------

    /**
     * 动态的题目
     * 239：给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位
     * 返回滑动窗口中的最大值。进阶： 你能在线性时间复杂度内解决此题吗？
     * 场景：
     * <p>
     *
     * @param nums
     * @param k
     * @return
     */
    ArrayDeque<Integer> deq = new ArrayDeque<Integer>();
    int[] nums;

    public void clean_deque(int i, int k) {
        // remove indexes of elements not from sliding window
        if (!deq.isEmpty() && deq.getFirst() == i - k) {
            deq.removeFirst();
        }

        // remove from deq indexes of all elements
        // which are smaller than current element nums[i]
        while (!deq.isEmpty() && nums[i] > nums[deq.getLast()]) {
            deq.removeLast();
        }
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        if (n * k == 0) {
            return new int[0];
        }
        if (k == 1) {
            return nums;
        }
        // init deque and output
        this.nums = nums;
        int max_idx = 0;
        for (int i = 0; i < k; i++) {
            clean_deque(i, k);
            deq.addLast(i);
            // compute max in nums[:k]
            if (nums[i] > nums[max_idx]) {
                max_idx = i;
            }
        }
        int[] output = new int[n - k + 1];
        output[0] = nums[max_idx];

        // build output
        for (int i = k; i < n; i++) {
            clean_deque(i, k);
            deq.addLast(i);
            output[i - k + 1] = nums[deq.getFirst()];
        }
        return output;
    }


    // ------------------

    /**
     * 给定一个列表 times，表示信号经过有向边的传递时间。 times[i] = (u, v, w)，其中 u 是源节点，v 是目标节点， w 是一个信号从源节点传递到目标节点的时间
     *
     * @param times
     * @param N
     * @param K
     * @return
     */
    public int networkDelayTime(int[][] times, int N, int K) {


        return 0;
    }


}



