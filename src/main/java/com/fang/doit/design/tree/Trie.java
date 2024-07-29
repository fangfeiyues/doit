package com.fang.doit.design.tree;

/**
 * @author : fangfeiyue
 * @version V1.0
 * @Project: doit
 * @Package com.fang.doit.design.tree
 * @Description:
 * @date Date : 2024-06-30 15:01
 */
public class Trie {

    private final TrieNode root;

    public Trie() {
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

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        trie.insert("app");
        trie.insert("april");
        // true
        System.out.println(trie.search("app"));
        // true
        System.out.println(trie.search("al"));
        // false
        System.out.println(trie.search("appl"));
        // 输出 "app" 和 "apple"
        trie.startsWith("app");
    }
}
