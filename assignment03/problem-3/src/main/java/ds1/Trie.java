package ds1;

class TrieNode {
    TrieNode[] children;
    TrieNode parent;
    boolean isEndOfWord;
    int index;

    public TrieNode() {
        children = new TrieNode[26];
        isEndOfWord = false;
        index = 0;
    }

    // count the number of children of a node
    public int countChildren() {
        int count = 0;
        for (TrieNode child : children) {
            if (child != null) {
                count++;
            }
        }
        return count;
    }

    // return the only child when countChildren() == 1
    public TrieNode getOnlyChild() {
        if (countChildren() != 1)
            return null;
        for (TrieNode child : children) {
            if (child != null)
                return child;
        }
        return null;
    }

    // get the child corresponding to a character
    public TrieNode getChild(char ch) {
        int index = charToIndex(ch);
        return children[index];
    }

    // set the child corresponding to a character
    public void setChild(char ch, TrieNode childNode) {
        int index = charToIndex(ch);
        if (childNode != null) {
            childNode.parent = this;
        }
        children[index] = childNode;
    }

    // convert a character to an index
    static int charToIndex(char ch) {
        if (ch < 'a' || ch > 'z') {
            throw new IllegalArgumentException("The character should only be lowercase a-z");
        }
        return ch - 'a';
    }
}

public class Trie {
    // root of the trie
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }
    
    TrieNode getRoot() {
        return root;
    }

    // insert a word into the trie
    // use the following algorithm:
    // start from the root
    // for each character in the word
    //   if there is no child corresponding to the character
    //     create a new node and add it as a child
    //   move to the child corresponding to the character
    // mark the last node as the end of a word
    // you can use the following methods:
    //   TrieNode.getChild
    //   TrieNode.setChild
    //   word.charAt(i) to get the character at index i
    public void insert(String word) {
        if (word == null) {
            throw new IllegalArgumentException("Word cannot be null");
        }
        TrieNode node = root;
        for(int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if(node.getChild(ch) == null) {
                node.setChild(ch, new TrieNode());
            }
            node = node.getChild(ch);
        }
        node.isEndOfWord = true;
    }

    public void toPatriciaTrie() {
        toPatriciaTrie(root);
    }

    public static void toPatriciaTrie(TrieNode node) {
        //Base case: if node is null, return
        if (node == null) return;
        //recurse all child
        for(TrieNode child : node.children)
            toPatriciaTrie(child);
        for(int i = 0; i < node.children.length; i++) {
            TrieNode child = node.children[i];
            if (child != null) {
                if (child.countChildren() == 1 && !child.isEndOfWord) {
                    TrieNode grandChild = child.getOnlyChild();
                    child.index += grandChild.index;
                    child.children = grandChild.children;
                    child.isEndOfWord = grandChild.isEndOfWord;
                }
            }
        }
    }

    
}


