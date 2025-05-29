package com.fang.doit.design.scene;

import java.util.HashMap;
import java.util.Map;

public class SnakeDesign {
    /**
     * 贪吃蛇设计
     * 1、贪吃蛇是一个无限长的蛇，初始位置在(0,0)，向上开始吃东西
     * 2、每次吃完东西后，蛇会转向，转向规则是：向上到头即向左(+1,0),向左到头即向下(0,-1)，向下到头即向左(-1,0),向左到头即向上(0,+1)
     * 3、当蛇到达边界或已经吃过的地方时，停止移动
     *
     * @param m 蛇的高度
     * @param n 蛇的宽度
     * @return 最终位置坐标
     */
    public int[] eat(int m, int n) {

        // 贪吃蛇，无限长、左上右下左..(滴滴)

        // 定义4个方向，向上到头即向左(+1,0),向左到头即向下(0,-1)，向下到头即向左(-1,0),向左到头即向上(0,+1)
        Map<Integer,int[]> nextMap = new HashMap<>();
        nextMap.put(1, new int[]{0, +1});
        nextMap.put(2, new int[]{+1, 0});
        nextMap.put(3, new int[]{0, -1});
        nextMap.put(4, new int[]{-1, 0});

        // 初始向上
        return eatDFS(1, 0, 0, m, n, nextMap, new HashMap<>());
    }

    private String buildKey(int i,int j){
        return i + "+" + j;
    }
    private int[] eatDFS(int flag, int i, int j, int m, int n,
                        Map<Integer, int[]> nextMap,
                        Map<String, Boolean> hasEat) {
        System.out.println("flag:" + flag + " next:" + i + " + " + j);
        hasEat.put(buildKey(i,j),true);
        // 贪吃的深度遍历
        int next_i = i;
        int next_j = j;
        if (flag == 1) {
            // 向上开始
            next_j = j + 1;
        }
        if (flag == 2) {
            // 向左开始
            next_i = i + 1;
        }
        if (flag == 3 && j >= 0) {
            next_j = j - 1;
        }
        if (flag == 4 && i >= 0) {
            next_i = i - 1;
        }

        // 到头了，要转向
        Boolean has = hasEat.get(buildKey(next_i,next_j));
        if ((next_i > m || next_i < 0) || (next_j > n || next_j < 0) || (has != null && has)) {
            flag = flag % 4 + 1;
            int[] next = nextMap.get(flag);
            next_i = i + next[0];
            next_j = j + next[1];
            // 要终止：向flag方向到头后，转头也到头
             has = hasEat.get(buildKey(next_i,next_j));
            if ((next_i > m || next_i < 0) || (next_j > n || next_j < 0) || (has != null && has)) {
                // 结束
                return new int[]{i, j};
            }
        }
        // 没到头，继续跑
        return eatDFS(flag, next_i, next_j, m, n, nextMap, hasEat);
    }


    public static void main(String[] args) {
        SnakeDesign test = new SnakeDesign();
        int[] res = test.eat(3, 4);
        System.out.println(res[0] + ":" + res[1]);
    }

}
