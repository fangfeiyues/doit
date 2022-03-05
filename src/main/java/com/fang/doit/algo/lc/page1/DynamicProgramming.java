package com.fang.doit.algo.lc.page1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * ��̬�滮
 *
 * @author created by fang on 2020/7/9/009 0:25
 */
public class DynamicProgramming {

    /**
     * https://leetcode-cn.com/problems/re-space-lcci/
     * Ҫ��δʶ����ַ�����
     *
     * @param dictionary dictionary = ["looked","just","like","her","brother"]
     * @param sentence   sentence = "jesslookedjustliketimherbrother"
     * @return "jess looked just like tim her brother" 7���ַ�δʶ��
     */
    public int respace(String[] dictionary, String sentence) {
        Set<String> dic = new HashSet<>();
        for (String str : dictionary) {
            dic.add(str);
        }

        // 1.������⣨��̬�滮��
        int n = sentence.length();
        int[] dp = new int[n + 1];

        // dp[i]��ʾ�ַ���sentenceǰi��ƥ��Ľ��
        for (int i = 1; i <= n; i++) {
            // �ȼ����i�������ֵ���
            dp[i] = dp[i - 1] + 1;
            for (int j = 0; j < i; j++) {

                // ��j��i���ֵ��� ��ʱdp[i] = dp[j] ������Ƚ���С��
                if (dic.contains(sentence.substring(j, i))) {
                    dp[i] = Math.min(dp[i], dp[j]);
                }
            }
        }

//        return dp[n];

        // 2. trie �ֵ���
        // �����ֵ���
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

        // �����ʵ�������ֵ���
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

        // �ҵ� sentence ���� endPos Ϊ��β�ĵ��ʣ�������Щ���ʵĿ�ͷ�±ꡣ
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
