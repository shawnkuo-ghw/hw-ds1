package ds1;

import java.util.*;

public class Trie {
    private static final int ALPHABET_SIZE = 26;

    static class TrieNode {
        TrieNode[] children = new TrieNode[ALPHABET_SIZE];
        boolean isWordEnd = false;
    }

    private final TrieNode root = new TrieNode();

    // Insert a word into the trie
    public void insert(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (node.children[idx] == null) {
                node.children[idx] = new TrieNode();
            }
            node = node.children[idx];
        }
        node.isWordEnd = true;
    }

    // Find lex smallest word starting from node (including node if isWordEnd)
    public String findMinWord(TrieNode node) {
        return findMinWordHelper(node, new StringBuilder());
    }

    private String findMinWordHelper(TrieNode node, StringBuilder sb) {
        if (node.isWordEnd) return sb.toString();

        for (char c = 'a'; c <= 'z'; c++) {
            int idx = c - 'a';
            if (node.children[idx] != null) {
                sb.append(c);
                String result = findMinWordHelper(node.children[idx], sb);
                if (result != null) return result;
                sb.deleteCharAt(sb.length() - 1); // backtrack
            }
        }

        return null;
    }

    // Find next word lex greater than the exact word, from the given node
    public String findNextWordFromNode(TrieNode node, String prefix) {
        if (node.isWordEnd) {
            return prefix;
        }

        String min = findMinWordHelper(node, new StringBuilder());
        return min != null ? prefix + min : null;
    }

    // Public method: find lex smallest word in trie strictly greater than given word
    public String nextWordInTrie(String word) {
        List<TrieNode> path = new ArrayList<>();
        List<Integer> indexes = new ArrayList<>();

        TrieNode node = root;
        int i = 0;

        // Walk down trie matching prefix of word
        while (i < word.length()) {
            char c = word.charAt(i);
            int idx = c - 'a';
            if (node.children[idx] != null) {
                node = node.children[idx];
                path.add(node);
                indexes.add(i);
                i++;
            } else {
                break; // mismatch here
            }
        }

        // Case 1: fully matched word and is word end
        if (i == word.length()) {
            // Try to find next word lex after this exact word
            String next = findNextWordFromNode(node, "");
            if (next != null) {
                String fullNext = word + next;
                if (fullNext.compareTo(word) > 0) {
                    return fullNext;
                }
            }
        }

        // Case 2: partial match or word not ending here
        // prefix matched up to i
        String prefix = word.substring(0, i);
        char c = (i < word.length()) ? word.charAt(i) : 0;

        TrieNode current = (i == 0) ? root : node;

        // Try children with character > c
        for (char nextChar = (i < word.length() ? (char)(c + 1) : 'a'); nextChar <= 'z'; nextChar++) {
            int idx = nextChar - 'a';
            if (current.children[idx] != null) {
                String suffix = findMinWord(current.children[idx]);
                if (suffix != null) {
                    return prefix + nextChar + suffix;
                }
            }
        }

        // Case 3: Backtrack up path to find next larger sibling
        for (int j = path.size() - 2; j >= 0; j--) {
            TrieNode parent = path.get(j);
            int idxInWord = indexes.get(j);
            char curChar = word.charAt(idxInWord);
            for (char nextChar = (char)(curChar + 1); nextChar <= 'z'; nextChar++) {
                int idx = nextChar - 'a';
                if (parent.children[idx] != null) {
                    String suffix = findMinWord(parent.children[idx]);
                    if (suffix != null) {
                        return word.substring(0, idxInWord) + nextChar + suffix;
                    }
                }
            }
        }

        // No next word found
        return null;
    }

}