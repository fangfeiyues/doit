package com.fang.doit.algo.lc.stage2;

import com.sun.org.apache.regexp.internal.RE;

import java.util.*;

/**
 * @author : fangfeiyue
 * @version V1.0
 * @Project: doit
 * @Package com.fang.doit.algo.sort
 * @Description: TODO
 * @date Date : 2022-03-05 3:36 PM
 */
public class LeetCodeSort {

    /**
     * 值和下标之差都在给定范围内: 给你一个整数数组 nums 和两个整数 k 和 t 。请你判断是否存在两个不同下标 i 和 j，使得 abs(nums[i] - nums[j]) <=t ，
     * 同时又满足 abs(i - j) <= k
     * https://leetcode-cn.com/problems/contains-duplicate-iii/Related/
     *
     * @param nums
     * @param k
     * @param t
     * @return
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        // 滑动窗口 + 有序排序. 时间复杂度：
        containsNearbyAlmostDuplicateByTreeSet(nums, k, t);

        // 桶排序. 时间复杂度:O(n)
        containsNearbyAlmostDuplicateByBucket(nums, k, t);

        return false;
    }

    public boolean containsNearbyAlmostDuplicateByTreeSet(int[] nums, int k, int t) {
        int n = nums.length;
        TreeSet<Long> set = new TreeSet<Long>();
        for (int i = 0; i < n; i++) {
            Long ceiling = set.ceiling((long) nums[i] - (long) t);
            if (ceiling != null && ceiling <= (long) nums[i] + (long) t) {
                return true;
            }
            set.add((long) nums[i]);
            if (i >= k) {
                set.remove((long) nums[i - k]);
            }
        }
        return false;
    }

    public boolean containsNearbyAlmostDuplicateByBucket(int[] nums, int k, int t) {
        int n = nums.length;
        Map<Long, Long> map = new HashMap<>();
        long w = (long) t + 1;
        for (int i = 0; i < n; i++) {
            long id = getID(nums[i], w);
            if (map.containsKey(id)) {
                return true;
            }
            if (map.containsKey(id - 1) && Math.abs(nums[i] - map.get(id - 1)) < w) {
                return true;
            }
            if (map.containsKey(id + 1) && Math.abs(nums[i] - map.get(id + 1)) < w) {
                return true;
            }
            map.put(id, (long) nums[i]);
            if (i >= k) {
                map.remove(getID(nums[i - k], w));
            }
        }
        return false;
    }

    public long getID(long x, long w) {
        if (x >= 0) {
            return x / w;
        }
        return (x + 1) / w - 1;
    }


    /**
     * 出现频率最高的k个数字，进阶：所设计算法的时间复杂度 必须 优于 O(n log n) ，其中 n 是数组大小。
     * https://leetcode-cn.com/problems/top-k-frequent-elements/Related
     * <p>
     * 【大顶堆】
     *
     * @param nums
     * @param k
     * @return
     */
    public List<Integer> topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = getFrequentTimes(nums);
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> map.get(a) - map.get(b));
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

    /**
     * 【桶排序】将频率作为数组下标，对于出现频率不同的数字集合存入对应的数组下标
     *
     * @param nums
     * @param k
     * @return
     */
    public List<Integer> topKFrequentByBucket(int[] nums, int k) {
        HashMap<Integer, Integer> map = getFrequentTimes(nums);
        List<Integer>[] list = new ArrayList[nums.length + 1];
        for (int key : map.keySet()) {
            int i = map.get(key);
            if (list[i] == null) {
                list[i] = new ArrayList();
            }
            list[i].add(key);
        }

        List<Integer> res = new ArrayList();
        for (int i = list.length - 1; i >= 0 && res.size() < k; i--) {
            if (list[i] == null) {
                continue;
            }
            res.addAll(list[i]);
        }
        return res;
    }


    private HashMap<Integer, Integer> getFrequentTimes(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap(nums.length);
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        return map;
    }

    /**
     * 找到和最小的k个数对
     * https://leetcode-cn.com/problems/find-k-pairs-with-smallest-sums/
     *
     * @param nums1 给定两个以 升序排列 的整数数组 nums1 和 nums2
     * @param nums2
     * @param k     以及一个整数 k
     * @return 请找到和最小的 k 个数对值 (u,v)
     */
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        // 小顶堆o1-o2; 大顶堆o2-o1;
        PriorityQueue<int[]> pq = new PriorityQueue<>(k, (o1, o2) -> nums1[o1[0]] + nums2[o1[1]] - nums1[o2[0]] - nums2[o2[1]]);
        List<List<Integer>> ans = new ArrayList<>();
        int m = nums1.length;
        int n = nums2.length;
        for (int i = 0; i < Math.min(m, k); i++) {
            pq.offer(new int[]{i, 0});
        }
        while (k-- > 0 && !pq.isEmpty()) {
            int[] idxPair = pq.poll();
            List<Integer> list = new ArrayList<>();
            list.add(nums1[idxPair[0]]);
            list.add(nums2[idxPair[1]]);
            ans.add(list);
            if (idxPair[1] + 1 < n) {
                pq.offer(new int[]{idxPair[0], idxPair[1] + 1});
            }
        }

        return ans;
    }

    /**
     * 数组中的第k大的数字
     * https://leetcode-cn.com/problems/kth-largest-element-in-an-array/
     *
     * @param nums 无序数组
     * @param k
     * @return 数组排序后的第 k 个最大的元素
     */
    public int findKthLargest(int[] nums, int k) {
        // 1. K大的小顶推，堆顶即是
        findKthLargestByPri(nums, k);

        // 2. 快排的分治思想
        findKthLargestByQuick(nums, k);

        return -1;
    }

    public int findKthLargestByPri(int[] nums, int k) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(k, Comparator.comparingInt(a -> a));
        for (int num : nums) {
            Integer minNum = priorityQueue.peek();
            if (minNum == null || minNum < num) {
                priorityQueue.add(num);
            }
        }
        return priorityQueue.isEmpty() ? -1 : priorityQueue.poll();
    }

    public int findKthLargestByQuick(int[] nums, int k) {
        if (k < 1) {
            return -1;
        }
        // 快排主要是利用分治的思想。选折分区点然后左右排列，
        int provit = 0;
        while (provit < nums.length) {
            int point = quickSort(nums, provit);
            if (point == nums.length - k - 1) {
                return nums[point - 1];
            }
            // 比较point和k的大小
            provit = point - 1;
        }
        return -1;
    }

    public int quickSort(int[] nums, int provit) {
        // i,j两个游标。i标记i及以前的都是小于nums[k]的，j往前走
        // 首位标记为不能动
        if (provit > nums.length - 1) {
            return -1;
        }
        int i = provit;
        for (int j = provit; j < nums.length; j++) {
            if (nums[j] < nums[provit]) {
                // 交换 第i+1个(理论上大于)和第j个(小于)即可
                swap(nums, i + 1, j);
                i++;
            }
        }
        swap(nums, provit, i);
        return i + 1;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        double a = 1.1;
        long b = (long) (a * 100L);
        System.out.println(b);
    }

    /**
     * 56.合并区间：https://leetcode-cn.com/problems/merge-intervals/
     *
     * @param intervals 表示若干个区间集合
     * @return
     */
    public int[][] mergeRegion(int[][] intervals) {
        if (intervals.length == 0) {
            return new int[0][2];
        }
        // 按照数组区间的前一个排序后，循环遍历
        return mergeRegionByFirstSort(intervals);
    }


    public int[][] mergeRegionByFirstSort(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        List<int[]> merged = new ArrayList<int[]>();
        for (int i = 0; i < intervals.length; ++i) {
            int L = intervals[i][0], R = intervals[i][1];
            // 不断比较前一个区间的右侧数据和新区间的左侧数据大小，小则作为一个新的区间大则后者加入到前一个区间
            if (merged.size() == 0 || merged.get(merged.size() - 1)[1] < L) {
                merged.add(new int[]{L, R});
            } else {
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], R);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }


}
