package com.fang.doit.algo.sort;

import java.util.*;

/**
 * @author : fangfeiyue
 * @version V1.0
 * @Project: doit
 * @Package com.fang.doit.algo.sort
 * @Description:
 * @date Date : 2022-02-24 11:13 PM
 */
public class StringSort {

    /**
     * 给定一个字符串数组 strs ，将 变位词 组合在一起。 可以按任意顺序返回结果列表。注意：若两个字符串中每个字符出现的次数都相同，则称它们互为变位词
     * https://leetcode-cn.com/problems/group-anagrams/
     *
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>(strs.length);
        for (String str : strs) {
            char[] array = str.toCharArray();
            // 字符排序！！！
            // O(nklogk) 其中 n 是字符串的数量，kk 是字符串的的最大长度
            Arrays.sort(array);
            String key = new String(array);
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList(map.values());
    }


}
