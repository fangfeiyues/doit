package com.fang.doit.algo.lc.stage1;

import java.util.Arrays;

/**
 * 动态规划（0-1背包，二维数组最小路径，硬币求和等）
 *
 * @author fangfeiyue
 * @Date 2020/12/24 11:10 下午
 */
public class Dp13 {

// 【归纳总结】
//    1.画好递归树。
//    2.自底向上分析。dp[n+1] = min(dp[n+1],...+) + ...
//    3.找到规律很关键！！！如
//      3.1 0-1背包：枚举所有的背包存放可能情况，每次都有放入和不放入两种及2^N种可能
//      3.2 最小路径：枚举以上从上或从左两种情况，取其中较小的一种即可
//      3.3 硬币求和：枚举结果的上一次所有来源coins，从结果往前推求的最小值（和3.2处理方式很像）
//    可以看出这里求出【最小来源体】是核心


    /**
     * 【背包问题】
     * 核心：遍历出所有(即每个节点都有放入和不放入背包两种可能)可能产生的结果，然后从结果中找到最合适的那个
     *
     * @param weight 物品重量
     * @param n      物品个数
     * @param w      背包可承载重量
     * @return 背包最大可承受重量
     */
    public int knapsack(int[] weight, int n, int w) {
        // 二维数组。n:物品数量；w+1：n个物品时背包承受的重量
        boolean[][] states = new boolean[n][w + 1];

        // 第一行的数据要特殊处理，可以利用哨兵优化
        states[0][0] = true;

        if (weight[0] <= w) {
            states[0][weight[0]] = true;
        }

        // 遍历所有节点的两种情况产生的可能：放入和不放入
        for (int i = 1; i < n; ++i) {
            // 情况1：第i个物品不放入到背包。此时背包二维数组i的状态和i-1时候的状态一样，没有变化
            for (int j = 0; j <= w; ++j) {
                if (states[i - 1][j]) {
                    states[i][j] = states[i - 1][j];
                }
            }
            // 情况2：第i个物品放入到背包。那如果i-1个物品的重量小于w-weight[i]则表示可以放入
            for (int j = 0; j <= w - weight[i]; ++j) {
                if (states[i - 1][j]) {
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
     * 64 最小路径和：给定一个包含非负整数的m x n网格grid找出一条从左上角到右下角的路径 使得路径上的数字总和为最小 (每次只能向下或者向右移动一步)
     *
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
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
                    // row == 0的必定是最顶上横着移动的情况
                    dp[i][j] = dp[i][j - 1] + grid[i][j];
                }
                if (j == 0) {
                    dp[i][j] = dp[i - 1][j] + grid[i][j];
                } else {
                    // 每次过来要么是从上要么是从左，其实和背包问题有异曲同工之妙
                    dp[i][j] = Math.min(grid[i - 1][j], grid[i][j - 1]) + grid[i][j];
                }
            }
        }
        return grid[grid.length - 1][grid[0].length - 1];
    }


    public int minPathSum2(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int rows = grid.length, columns = grid[0].length;
        int[][] dp = new int[rows][columns];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < rows; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int j = 1; j < columns; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < columns; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[rows - 1][columns - 1];
    }

    /**
     * 322 零钱兑换：给定不同面额的硬币coins和一个总金额amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数（可以认为每种硬币的数量是无限的）
     * TODO 写不来...
     *
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        // dp[i]默认值是max
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    // min(dp[i]): 遍历回查coins的列表，从中找到dp[x]最小的。从而加1即可到达dp[i]。
                    // 思考实在是精妙！！！
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }


    // ======================================

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
