package com.fang.doit.algo.lc;

import java.util.ArrayList;
import java.util.List;

/**
 * 回溯的处理思想有点类似枚举搜索
 *
 * @author fangfeiyue
 * @Date 2020/12/21 8:33 下午
 */
public class Recall12 {

//      我们枚举所有的解找到满足期望的解。
//      为了有规律地枚举所有可能的解避免遗漏和重复我们把问题求解的过程分为多个阶段。
//      每个阶段我们都会面对一个岔路口我们先随意选一条路走当发现这条路走不通的时候（不符合期望的解）就回退到上一个岔路口另选一种走法继续走。


    // 深度优先树：for+递归回溯

    /**
     * 22 括号生成：数字 n 代表生成括号的对数请你设计一个函数用于能够生成所有可能的并且有效的括号组合
     * 思考：罗列场景
     *
     * @param n
     * @return
     */
    // 做减法
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        // 特判
        if (n == 0) {
            return res;
        }

        // 执行深度优先遍历，搜索可能的结果
        dfs("", n, n, res);
        return res;
    }

    /**
     * @param curStr 当前递归得到的结果
     * @param left   左括号还有几个可以使用
     * @param right  右括号还有几个可以使用
     * @param res    结果集
     */
    private void dfs(String curStr, int left, int right, List<String> res) {
        // 因为每一次尝试，都使用新的字符串变量，所以无需回溯
        if (left == 0 && right == 0) {
            res.add(curStr);
            return;
        }

        // 剪枝（如图，左括号可以使用的个数严格大于右括号可以使用的个数，才剪枝，注意这个细节）
        if (left > right) {
            return;
        }

        if (left > 0) {
            dfs(curStr + "(", left - 1, right, res);
        }

        if (right > 0) {
            dfs(curStr + ")", left, right - 1, res);
        }
    }

    public List<String> generateParenthesisV2(int n) {
        List<String> ans = new ArrayList<String>();
        backtrack(ans, new StringBuilder(), 0, 0, n);
        return ans;
    }

    public void backtrack(List<String> ans, StringBuilder cur, int open, int close, int max) {
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

    /**
     * 八皇后棋子问题
     *
     * @param row
     */
    int[] result = new int[8];

    public void cal8queens(int row) {
        // 调用方式：cal8queens(0);
        if (row == 8) {
            // 8个棋子都放置好了，打印结果
            printQueens(result);
            return;
        }
        // 每一行都有8中放法
        for (int column = 0; column < 8; ++column) {
            // 有些放法不满足要求
            if (isOk(row, column)) {
                // 第row行的棋子放到了column列
                result[row] = column;
                // 考察下一行
                cal8queens(row + 1);
            }
        }
    }

    /**
     * 判断row行column列放置是否合适
     *
     * @param row
     * @param column
     * @return
     */
    private boolean isOk(int row, int column) {
        int leftup = column - 1, rightup = column + 1;
        // 逐行往上考察每一行
        for (int i = row - 1; i >= 0; --i) {
            // 第i行的column列有棋子吗？
            if (result[i] == column) {
                return false;
            }
            // 考察左上对角线：第i行leftup列有棋子吗？
            if (leftup >= 0) {
                if (result[i] == leftup) {
                    return false;
                }
            }
            // 考察右上对角线：第i行rightup列有棋子吗？
            if (rightup < 8) {
                if (result[i] == rightup) {
                    return false;
                }
            }
            --leftup;
            ++rightup;
        }
        return true;
    }

    /**
     * 打印出一个二维矩阵
     *
     * @param result
     */
    private void printQueens(int[] result) {
        for (int row = 0; row < 8; ++row) {
            for (int column = 0; column < 8; ++column) {
                if (result[row] == column) System.out.print("Q ");
                else System.out.print("* ");
            }
            System.out.println();
        }
        System.out.println();
    }

}
