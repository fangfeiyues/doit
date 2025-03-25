package com.fang.doit.design.tree;

/**
 * @author : fangfeiyue
 * @version V1.0
 * @Project: doit
 * @Package com.fang.doit.design.tree
 * @Description:
 * @date Date : 2024-06-30 14:45
 */
class TrieNode {
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

