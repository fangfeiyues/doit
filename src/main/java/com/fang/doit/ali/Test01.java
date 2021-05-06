package com.fang.doit.ali;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author fangfeiyue
 * @Date 2021/1/31 8:11 下午
 */
public class Test01 {

    private boolean as;

    public Boolean getAs() {
        return as;
    }

    public void setAs(Boolean as) {
        this.as = as;
    }

    /**
     * jvm内存受限为5M，有一个1G大小的一个文件，里面每一行是一个词，词的大小不超过16字节，内存限制大小是1M
     *
     * @return 返回频数最高的100个词
     */
    public List<String> findK100() {

        // 1. 分割与保存 1024M/5000 = 200k
        for (int i = 0; i < 5000; i++) {
            saveFile(spilt5000("spilt_5000"));
        }

        // 2. 两两文件比较与归并

        return merge();
    }


    private List<String> merge() {
        Map<String, Integer> map = readTop(",", "");
//        int[] array = map.values().toArray();
//        merge_sort_c(array, 0, array.length);

        return null;
    }

    private void merge_sort_c(int[] a, int p, int r) {
        if (p >= r) {
            return;
        }
        int q = (p + r) / 2;
        merge_sort_c(a, p, q);
        merge_sort_c(a, q + 1, r);

        // 从最低层的分割开始 合并的过程就是排序
        merge(a, p, q, r);

    }


    private void merge(int[] a, int p, int q, int r) {
        // 两个游标
        int i = p;
        int j = q + 1;
        //作为temp的临时存储下标
        int k = 0;
        int[] temp = new int[r - q + 1];

        // 两个数组比较并存放到temp
        while (i < q & j < r) {
            if (a[i] <= a[j]) {
                temp[k++] = a[i++];
            } else {
                temp[k++] = a[j++];
            }
        }

        // 判断哪个数组有剩余
        int start = i;
        int end = q;
        if (j < i) {
            start = j;
            end = r;
        }
        while (start <= end) {
            temp[k++] = temp[start++];
        }

        // 将temp数据考回到a[p...r]数组
        for (i = 0; i <= r - p; ++i) {
//            ArrayIndexOutOfBoundsException
            a[p + i] = temp[i];
        }
    }

    private void saveFile(PriorityQueue priorityQueue) {
        System.out.println("把每个文件的top100以Map的方式保存下来...");
    }

    private Map<String, Integer> readTop(String file1, String file2) {

        return null;
    }

    private PriorityQueue spilt5000(String fileName) {

        Map<String, Integer> map = readFileContent(fileName);
        // 维护这样的一个大小为100的大顶推
        return new PriorityQueue<>(100, (a, b) -> map.get(b) - map.get(a));
    }

    private static Map<String, Integer> readFileContent(String fileName) {
        Map<String, Integer> map = new HashMap<>();
        File file = new File(fileName);
        BufferedReader reader = null;
        StringBuffer sbf = new StringBuffer();
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempStr;
            while ((tempStr = reader.readLine()) != null) {
                Integer times = map.getOrDefault(tempStr, 0);
                map.putIfAbsent(tempStr, times++);
            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return map;
    }

    public static void main(String[] args) {
        System.out.println(0x2 << 2);
        System.out.println(1 << 2);
    }
}
