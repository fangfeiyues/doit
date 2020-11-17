package com.fang.doit.algo.dynamic;

/**
 * @author by Feiyue on 2020/5/29 12:46 PM
 */
public class DynamicSolution {

    /**
     * 【背包问题】
     * <p>
     * weight:物品重量，n:物品个数，w:背包可承载重量
     *
     * @param weight
     * @param n
     * @param w
     * @return
     */
    public int knapsack(int[] weight, int n, int w) {

        // 默认值false [n]物品个数；[w+1]背包可承受的重量
        boolean[][] states = new boolean[n][w + 1];

        // 第一行的数据要特殊处理，可以利用哨兵优化
        states[0][0] = true;

        if (weight[0] <= w) {
            states[0][weight[0]] = true;
        }

        for (int i = 1; i < n; ++i) {

            // 不把第i个物品放入背包
            // 这里是把上一个放入背包的步骤结果都直接保持不变
            for (int j = 0; j <= w; ++j) {
                if (states[i - 1][j] == true) {
                    states[i][j] = states[i - 1][j];
                }

            }

            // 把第i个物品放入背包
            // 在不超过总量 w-weight[i] 的范围内如果之前的物品在背包内则此次重量+背包 weight[i]+j
            // 这样就表示之前只要在背包有过可能达到达的重量都会加上这次放入放入重量
            for (int j = 0; j <= w - weight[i]; ++j) {
                if (states[i - 1][j] == true) {
                    states[i][j + weight[i]] = true;
                }
            }

        }

        // 最后一个物品 n-1 放下的时候为准
        for (int i = w; i >= 0; --i) {
            if (states[n - 1][i] == true) {
                return i;
            }
        }
        return 0;
    }


    /**
     * 一纬数组方式减少空间复杂度
     *
     * @param items
     * @param n
     * @param w
     * @return
     */
    public static int knapsack2(int[] items, int n, int w) {
        boolean[] states = new boolean[w + 1];
        // 默认值false
        // 第一行的数据要特殊处理，可以利用哨兵优化
        states[0] = true;
        if (items[0] <= w) {
            states[items[0]] = true;
        }
        for (int i = 1; i < n; ++i) {
            // 动态规划
            for (int j = w - items[i]; j >= 0; --j) {
                //把第i个物品放入背包
                if (states[j] == true) states[j + items[i]] = true;
            }
        }
        for (int i = w; i >= 0; --i) {
            // 输出结果
            if (states[i] == true) return i;
        }
        return 0;
    }


    /**
     * 升级版：加入价值
     *
     * @param weight
     * @param value
     * @param n
     * @param w
     * @return
     */
    public static int knapsack3(int[] weight, int[] value, int n, int w) {
        int[][] states = new int[n][w + 1];
        for (int i = 0; i < n; ++i) {
            // 初始化states
            for (int j = 0; j < w + 1; ++j) {
                states[i][j] = -1;
            }
        }
        states[0][0] = 0;
        if (weight[0] <= w) {
            states[0][weight[0]] = value[0];
        }
        for (int i = 1; i < n; ++i) {
            //动态规划，状态转移
            for (int j = 0; j <= w; ++j) {
                // 不选择第i个物品
                if (states[i - 1][j] >= 0) {
                    states[i][j] = states[i - 1][j];
                }
            }

            // 在不超过总的重量范围内 选择第i个物品
            for (int j = 0; j <= w - weight[i]; ++j) {

                if (states[i - 1][j] >= 0) {
                    int v = states[i - 1][j] + value[i];

                    // 在达到相同的重量下 取价值更大的！！！
                    if (v > states[i][j + weight[i]]) {
                        states[i][j + weight[i]] = v;
                    }
                }
            }
        }

        // 找出最大值
        int maxvalue = -1;
        for (int j = 0; j <= w; ++j) {

            if (states[n - 1][j] > maxvalue) {
                maxvalue = states[n - 1][j];
            }

        }
        return maxvalue;
    }

    /**
     * 双十一购物车n个商品，选择出商品总价最大程度接近满减200减50的商品集合
     * <p>
     * https://time.geekbang.org/column/article/74788
     * <p>
     * items商品价格，n商品个数, w表示满减条件，比如200
     *
     * @param items
     * @param n
     * @param w
     */
    public static void double11advance(int[] items, int n, int w) {
        // 假设在 w-3w 内的价格取最小的
        int maxW = 3 * w;

        boolean[][] values = new boolean[n][maxW];
        if (items[0] < maxW) {
            values[0][items[0]] = true;
        }

        for (int i = 1; i < n; i++) {

            // 当前商品不加入购物车
            for (int j = 0; j < maxW; j++) {
                if (values[i - 1][j] == true) {
                    values[i][j] = values[i - 1][j];
                }
            }

            // 当前物品加入购物车
            for (int j = maxW - items[i]; j > 0; j--) {
                if (values[i - 1][j] == true) {
                    values[i][items[i] + j] = true;
                }
            }

        }

        // j价值的最接近w
        int j;
        for (j = w; j < maxW + 1; j--) {
            if (values[n - 1][j] == true) {
                break;
            }
        }

        // 说明在 w-3w+1没有可行的解
        if (j == maxW + 1) {
            return;
        }


        for (int i = n - 1; i > 0; --i) {

            // 表示是从当前item[i]的商品到达的
            if (j - items[i] > 0 && values[i - 1][j - items[i]] == true) {

                System.out.println(items[i]);
                j = j - items[i];
            }

            // 否则不是从这个商品到达的 不做处理继续向下
        }


        if (j != 0) {
            System.out.print(items[0]);
        }
    }

}

