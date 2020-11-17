package com.fang.doit.algo.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 动态规划
 *
 * @author created by fang on 2020/7/9/009 0:25
 */
public class DynamicProgramming {

    /**
     * https://leetcode-cn.com/problems/re-space-lcci/
     * 要求未识别的字符最少
     *
     * @param dictionary dictionary = ["looked","just","like","her","brother"]
     * @param sentence   sentence = "jesslookedjustliketimherbrother"
     * @return "jess looked just like tim her brother" 7个字符未识别
     */
    public int respace(String[] dictionary, String sentence) {
        Set<String> dic = new HashSet<>();
        for (String str : dictionary) {
            dic.add(str);
        }

        // 1.暴力拆解（动态规划）
        int n = sentence.length();
        int[] dp = new int[n + 1];

        // dp[i]表示字符串sentence前i不匹配的结果
        for (int i = 1; i <= n; i++) {
            // 先假设第i个不在字典中
            dp[i] = dp[i - 1] + 1;
            for (int j = 0; j < i; j++) {

                // 第j到i再字典里 此时dp[i] = dp[j] 但还需比较最小的
                if (dic.contains(sentence.substring(j, i))) {
                    dp[i] = Math.min(dp[i], dp[j]);
                }
            }
        }

//        return dp[n];

        // 2. trie 字典树
        // 构建字典树
        Trie trie = new Trie();
        for (String word : dictionary) {
            trie.insert(word);
        }
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1] + 1;
            for (int idx : trie.search(sentence, i - 1)) {
                dp[i] = Math.min(dp[i], dp[idx]);
            }
        }

        return 0;
    }

    class Trie {
        TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        // 将单词倒序插入字典树
        public void insert(String word) {
            TrieNode cur = root;
            for (int i = word.length() - 1; i >= 0; i--) {
                int c = word.charAt(i) - 'a';
                if (cur.children[c] == null) {
                    cur.children[c] = new TrieNode();
                }
                cur = cur.children[c];
            }
            cur.isWord = true;
        }

        // 找到 sentence 中以 endPos 为结尾的单词，返回这些单词的开头下标。
        public List<Integer> search(String sentence, int endPos) {
            List<Integer> indices = new ArrayList<>();
            TrieNode cur = root;
            for (int i = endPos; i >= 0; i--) {
                int c = sentence.charAt(i) - 'a';
                if (cur.children[c] == null) {
                    break;
                }
                cur = cur.children[c];
                if (cur.isWord) {
                    indices.add(i);
                }
            }
            return indices;
        }
    }

    class TrieNode {
        boolean isWord;
        TrieNode[] children = new TrieNode[26];

        public TrieNode() {
        }
    }

    public static void main(String[] args) {
        DynamicProgramming dynamicProgramming = new DynamicProgramming();
        String[] dic = new String[]{"looked", "just", "like", "her", "brother"};
        String sentence = "jesslookedjustliketimherbrother";
        System.out.println(dynamicProgramming.respace(dic, sentence));

    }

}
