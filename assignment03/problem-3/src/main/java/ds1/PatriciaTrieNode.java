package ds1;

class PatriciaTrieNode {
    PatriciaTrieNode[] children;
    PatriciaTrieNode parent;
    boolean isEndOfWord;
    int index;
    String fullWord; //when isEndOfWord is false, it is null. Otherwise it will be the word

    public PatriciaTrieNode() {
        children = new PatriciaTrieNode[26];
        parent = null;
        isEndOfWord = false;
        index = 0;
        fullWord = null;
    }

    // count the number of children of a node
    public int countChildren() {
        int count = 0;
        for (PatriciaTrieNode child : children) {
            if (child != null) {
                count++;
            }
        }
        return count;
    }

    // get the child corresponding to a character
    public PatriciaTrieNode getChild(char ch) {
        int position = charToIndex(ch);
        return children[position];
    }

   // return the only child when countChildren() == 1
    public PatriciaTrieNode getOnlyChild() {
        for (PatriciaTrieNode child : children) {
            if (child != null && child.countChildren() == 1) {
                return child;
            }
        }
        return null;
    }

    // set the child corresponding to a character
    public void setChild(char ch, PatriciaTrieNode childNode) {
        int position = charToIndex(ch);
        if (childNode != null)
            childNode.parent = this;
        this.children[position] = childNode;
    }

    // convert a character to an index
    static int charToIndex(char ch) {
        if (ch < 'a' || ch > 'z') {
            throw new IllegalArgumentException("Character should only be lowercase a-z");
        }
        return ch - 'a';
    }

}


