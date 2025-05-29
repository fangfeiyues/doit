package com.fang.doit.design.scene;

/**
 * @author : fangfeiyue
 * @version V1.0
 * @Project: doit
 * @Package com.fang.doit.design.tree
 * @Description:
 * @date Date : 2024-06-30 15:01
 */
public class TreeTrie {

    private final TrieNode root;

    public TreeTrie() {
        root = new TrieNode();
    }

    /**
     * 插入字符串到前缀树
     *
     * @param word
     */
    public void insert(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char currentChar = word.charAt(i);
            if (!node.containsKey(currentChar)) {
                node.put(currentChar, new TrieNode());
            }
            node = node.get(currentChar);
        }
        node.setEndOfWord();
    }

    /**
     * 查找字符串是否在前缀树中
     *
     * @param word
     * @return
     */
    public boolean search(String word) {
        TrieNode node = searchPrefix(word);
        return node != null && node.isEndOfWord();
    }

    /**
     * 查找字符串的前缀是否存在于前缀树中
     *
     * @param word
     * @return
     */
    public TrieNode searchPrefix(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char curLetter = word.charAt(i);
            if (node.containsKey(curLetter)) {
                node = node.get(curLetter);
            } else {
                return null;
            }
        }
        return node;
    }

    /**
     * 从给定的前缀开始，查找所有可能的完成的单词
     *
     * @param prefix
     */
    public void startsWith(String prefix) {
        TrieNode node = searchPrefix(prefix);
        if (node == null) {
            return;
        }
        findAllWordsFromNode(node, prefix);
    }

    private void findAllWordsFromNode(TrieNode node, String prefix) {
        if (node.isEndOfWord()) {
            System.out.println(prefix);
        }
        for (char c = 'a'; c <= 'z'; c++) {
            if (node.containsKey(c)) {
                findAllWordsFromNode(node.get(c), prefix + c);
            }
        }
    }



    static class TrieNode {
        // 1. 数组 TrieNode[] children
        // 2. List<TrieNode>

        private TrieNode[] children;
        private boolean isEndOfWord;

        public TrieNode() {
            // 用TrieNode数组的方式把各个节点字符串连起来
            this.children = new TrieNode[26];
            this.isEndOfWord = false;
        }

        public boolean containsKey(char ch) {
            return children[ch - 'a'] != null;
        }

        public TrieNode get(char ch) {
            return children[ch - 'a'];
        }

        public void put(char ch, TrieNode node) {
            children[ch - 'a'] = node;
        }

        public void setEndOfWord() {
            isEndOfWord = true;
        }

        public boolean isEndOfWord() {
            return isEndOfWord;
        }
    }

    public static void main(String[] args) {
        TreeTrie trieTree = new TreeTrie();
        trieTree.insert("apple");
        trieTree.insert("app");
        trieTree.insert("april");
        // true
        System.out.println(trieTree.search("app"));
        // true
        System.out.println(trieTree.search("al"));
        // false
        System.out.println(trieTree.search("appl"));
        // 输出 "app" 和 "apple"
        trieTree.startsWith("app");
    }
}
