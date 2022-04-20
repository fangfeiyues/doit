package com.fang.doit.algo.lc.stage1;

import com.alibaba.fastjson.JSON;

import java.util.*;

/**
 * 可以看的出来 堆的核心能力就是维护一个大小顶推的问题。滑动窗口问题还是有些巧妙的
 *
 * @author fangfeiyue
 * @Date 2020/11/29 3:33 下午
 */
public class Heap02 {


    // 堆

    /**
     * 215：在未排序的数组中找到第 k 个最大的元素
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

        // 大顶推 PriorityQueue<Integer> queue = new PriorityQueue<>(26, Collections.reverseOrder());
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
                    // 这里注意是要和++i替换下，因为i还是小于pivot的
                    swap(nums, j, ++i);
                }
            }
            // 交换第一个数和比较的第i个数
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
//        Heap02 heap0202 = new Heap02();
//        int[] nums = {1, 1, 1, 1, 2, 2, 3, 4, 4, 5, 4, 4, 1};
//        System.out.println(heap0202.topKFrequentByBucket(nums, 1));
//        int[] array = {3, 2, 4, 5, 1, 7, 10};

        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        System.out.println(JSON.toJSONString(maxSlidingWindowByPriority(nums, 3)));
    }


    // ------------------------------------

    /**
     * 动态的题目
     * 239：给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字滑动窗口每次只向右移动一位
     * 返回滑动窗口中的最大值。
     * 进阶：你能在线性时间复杂度内解决此题吗？
     * <p>
     *
     * @param nums
     * @param k
     * @return
     */
    public static int[] maxSlidingWindowByPriority(int[] nums, int k) {
        // 利用
        int n = nums.length;
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
            @Override
            public int compare(int[] pair1, int[] pair2) {
                return pair1[0] != pair2[0] ? pair2[0] - pair1[0] : pair2[1] - pair1[1];
            }
        });
        for (int i = 0; i < k; ++i) {
            pq.offer(new int[]{nums[i], i});
        }
        int[] ans = new int[n - k + 1];
        ans[0] = pq.peek()[0];
        for (int i = k; i < n; ++i) {
            pq.offer(new int[]{nums[i], i});
            // 这里并不在乎其他元素只在乎堆顶元素是否在移除的元素之内，好思路！！！
            while (pq.peek()[1] <= i - k) {
                pq.poll();
            }
            ans[i - k + 1] = pq.peek()[0];
        }
        return ans;
    }


    // ------------------

    /**
     * 743：给定一个列表times，示信号经过有向边的传递时间。
     * times[i] = (u, v, w)其中 u 是源节点，v 是目标节点 w 是一个信号从源节点传递到目标节点的时间
     * 现在从某个节点 K 发出一个信号，需要多久才能使所有节点都收到信号
     *
     * @param times
     * @param N
     * @param K
     * @return
     * @see WidthTree#networkDelayTime(int[][], int, int) 见此
     */
    public int networkDelayTime(int[][] times, int N, int K) {

        // 在每个下个节点的列表维护一个大顶堆 从其中找到最大的即是最长路径

        return 0;
    }

    /**
     * 787: K站中转战最便宜的航班
     *
     * @param n
     * @param flights
     * @param src
     * @param dst
     * @param K
     * @return
     * @see WidthTree#findCheapestPrice(int, int[][], int, int, int)
     */
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {

        //
        return 0;
    }


}



