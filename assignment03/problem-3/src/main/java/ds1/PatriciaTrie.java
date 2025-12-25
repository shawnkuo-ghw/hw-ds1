package ds1;

public class PatriciaTrie {
    PatriciaTrieNode root;

    public PatriciaTrie() {
        root = new PatriciaTrieNode();
        root.index = 0;
    }

    PatriciaTrieNode findNode(String prefix) {
        if (prefix == null) {
            throw new IllegalArgumentException("Prefix cannot be null");
        }
        PatriciaTrieNode current = root;
        while(current != null && current.index < prefix.length()) {
            int i = current.index;
            char ch = prefix.charAt(i);
            PatriciaTrieNode child = current.getChild(ch);
            if (child == null) return null;
            current = child;
        }
        return current;
    }

    public boolean search(String word) {
        if (word == null) {
            throw new IllegalArgumentException("Word cannot be null");
        }
        PatriciaTrieNode node = findNode(word);
        return node != null && node.isEndOfWord && node.fullWord != null && node.fullWord.equals(word);
    }
}