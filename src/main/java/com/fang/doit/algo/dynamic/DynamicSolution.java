package com.fang.doit.algo.dynamic;

/**
 * ��̬�滮
 *
 * @author by Feiyue on 2020/5/29 12:46 PM
 */
public class DynamicSolution {

    /**
     * ���������⡿
     * ���ģ�����������(��ÿ���ڵ㶼�з���Ͳ����뱳�����ֿ���)���ܲ����Ľ����Ȼ��ӽ�����ҵ�����ʵ��Ǹ�
     *
     * @param weight ��Ʒ����
     * @param n      ��Ʒ����
     * @param w      �����ɳ�������
     * @return �������ɳ�������
     */
    public int knapsack(int[] weight, int n, int w) {
        //��ά���顣n:��Ʒ������w+1��n����Ʒʱ�������ܵ�����
        boolean[][] states = new boolean[n][w + 1];

        // ��һ�е�����Ҫ���⴦�����������ڱ��Ż�
        states[0][0] = true;

        if (weight[0] <= w) {
            states[0][weight[0]] = true;
        }

        // �������нڵ��������������Ŀ��ܣ�����Ͳ�����
        for (int i = 1; i < n; ++i) {

            // ���1����i����Ʒ�����뵽��������ʱ������ά����i��״̬��i-1ʱ���״̬һ����û�б仯
            for (int j = 0; j <= w; ++j) {
                if (states[i - 1][j] == true) {
                    states[i][j] = states[i - 1][j];
                }
            }

            // ���2����i����Ʒ���뵽�����������i-1����Ʒ������С��w-weight[i]���ʾ���Է���
            for (int j = 0; j <= w - weight[i]; ++j) {
                if (states[i - 1][j] == true) {
                    states[i][j + weight[i]] = true;
                }
            }
        }

        // ���һ����Ʒ n-1 ���µ�ʱ��Ϊ׼
        for (int i = w; i >= 0; --i) {
            if (states[n - 1][i] == true) {
                return i;
            }
        }
        return 0;
    }


    /**
     * һγ���鷽ʽ���ٿռ临�Ӷ�
     *
     * @param items
     * @param n
     * @param w
     * @return
     */
    public static int knapsack2(int[] items, int n, int w) {
        boolean[] states = new boolean[w + 1];
        // Ĭ��ֵfalse
        // ��һ�е�����Ҫ���⴦�����������ڱ��Ż�
        states[0] = true;
        if (items[0] <= w) {
            states[items[0]] = true;
        }
        for (int i = 1; i < n; ++i) {
            // ��̬�滮
            for (int j = w - items[i]; j >= 0; --j) {
                //�ѵ�i����Ʒ���뱳��
                if (states[j] == true) states[j + items[i]] = true;
            }
        }
        for (int i = w; i >= 0; --i) {
            // ������
            if (states[i] == true) return i;
        }
        return 0;
    }


    /**
     * �����棺�����ֵ
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
            // ��ʼ��states
            for (int j = 0; j < w + 1; ++j) {
                states[i][j] = -1;
            }
        }
        states[0][0] = 0;
        if (weight[0] <= w) {
            states[0][weight[0]] = value[0];
        }
        for (int i = 1; i < n; ++i) {
            //��̬�滮��״̬ת��
            for (int j = 0; j <= w; ++j) {
                // ��ѡ���i����Ʒ
                if (states[i - 1][j] >= 0) {
                    states[i][j] = states[i - 1][j];
                }
            }

            // �ڲ������ܵ�������Χ�� ѡ���i����Ʒ
            for (int j = 0; j <= w - weight[i]; ++j) {

                if (states[i - 1][j] >= 0) {
                    int v = states[i - 1][j] + value[i];

                    // �ڴﵽ��ͬ�������� ȡ��ֵ����ģ�����
                    if (v > states[i][j + weight[i]]) {
                        states[i][j + weight[i]] = v;
                    }
                }
            }
        }

        // �ҳ����ֵ
        int maxvalue = -1;
        for (int j = 0; j <= w; ++j) {

            if (states[n - 1][j] > maxvalue) {
                maxvalue = states[n - 1][j];
            }

        }
        return maxvalue;
    }

    /**
     * ˫ʮһ���ﳵn����Ʒ��ѡ�����Ʒ�ܼ����̶Ƚӽ�����200��50����Ʒ����
     * <p>
     * https://time.geekbang.org/column/article/74788
     * <p>
     * items��Ʒ�۸�n��Ʒ����, w��ʾ��������������200
     *
     * @param items
     * @param n
     * @param w
     */
    public static void double11advance(int[] items, int n, int w) {
        // ������ w-3w �ڵļ۸�ȡ��С��
        int maxW = 3 * w;

        boolean[][] values = new boolean[n][maxW];
        if (items[0] < maxW) {
            values[0][items[0]] = true;
        }

        for (int i = 1; i < n; i++) {

            // ��ǰ��Ʒ�����빺�ﳵ
            for (int j = 0; j < maxW; j++) {
                if (values[i - 1][j] == true) {
                    values[i][j] = values[i - 1][j];
                }
            }

            // ��ǰ��Ʒ���빺�ﳵ
            for (int j = maxW - items[i]; j > 0; j--) {
                if (values[i - 1][j] == true) {
                    values[i][items[i] + j] = true;
                }
            }

        }

        // j��ֵ����ӽ�w
        int j;
        for (j = w; j < maxW + 1; j--) {
            if (values[n - 1][j] == true) {
                break;
            }
        }

        // ˵���� w-3w+1û�п��еĽ�
        if (j == maxW + 1) {
            return;
        }


        for (int i = n - 1; i > 0; --i) {

            // ��ʾ�Ǵӵ�ǰitem[i]����Ʒ�����
            if (j - items[i] > 0 && values[i - 1][j - items[i]] == true) {

                System.out.println(items[i]);
                j = j - items[i];
            }

            // �����Ǵ������Ʒ����� ���������������
        }


        if (j != 0) {
            System.out.print(items[0]);
        }
    }

}

