package com.xuyao.test.algorithm;

public class Trie {
    Trie[] next;
    int count;
    public Trie() {
        this.next = new Trie[26];
        count = 0;
    }

    public void insert(String str, Trie root){
        if(str.length() == 0){
            return;
        }
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if(root.next[chars[i] - 'a'] == null){
                root.next[chars[i] - 'a'] = new Trie();
            }
            root = root.next[chars[i] - 'a'];
        }
        root.count++;
    }

    public int search(String str, Trie root){
        if (str.length() == 0) {
            return -1;
        }
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if(root.next[chars[i] - 'a'] == null){
                return -1;
            }
            root = root.next[chars[i] - 'a'];
        }
        if(root.count == 0){
            return -1;
        }
        return root.count;
    }

}
