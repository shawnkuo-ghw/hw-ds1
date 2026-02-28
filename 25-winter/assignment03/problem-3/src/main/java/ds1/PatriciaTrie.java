package ds1;

public class PatriciaTrie {
    PatriciaTrieNode root;

    public PatriciaTrie() {
        root = new PatriciaTrieNode();
        root.index = 0;
    }

    private boolean isValidWord(String word) {
        if (word == null) return false;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) < 'a' || word.charAt(i) > 'z')
                return false;
        }
        return true;
    }

    //find node accroding to the prefix
    PatriciaTrieNode findNode(String prefix) {
        if (isValidWord(prefix) == false) {
            throw new IllegalArgumentException("Prefix is not valid");
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
        if (isValidWord(word) == false) {
            throw new IllegalArgumentException("word is not valid");
        }
        PatriciaTrieNode node = findNode(word);
        //this node is a word iff it's not null, it is a word and equals word
        return node != null && node.isEndOfWord && node.fullWord.equals(word);
    }

    //find a word starting from the node
    private String getOneWord(PatriciaTrieNode node) {
        //base case
        if (node == null) return null;
        if (node.isEndOfWord) return node.fullWord;
        //recursive case
        for (PatriciaTrieNode child : node.children) {
            if (child != null) {
                String word = getOneWord(child);
                // if find this word, return it
                if (word != null) return word;
            }
        }
        return null;
    }

    //used in case 1, directly insert
    private void insertNode(PatriciaTrieNode parent, char ch, String word) {
        PatriciaTrieNode node = new PatriciaTrieNode();
        node.index = word.length();
        node.isEndOfWord = true;
        node.fullWord = word;
        parent.setChild(ch, node);
    }

    //used in case 2, return if we need to process the child depends on whether we need to split the path
    private boolean prepareSpliting(PatriciaTrieNode parent, PatriciaTrieNode child, String word) {
        String existingWord = getOneWord(child);
        int splitNodeIndex = findSplitedNodeIndex(parent.index, child.index, word, existingWord);

        // if we find the splited index, the splitNodeIndex is not -1
        // But if splitNodeIndex == -1, it is also possible that the word is some prefix of existingWord
        if (splitNodeIndex != -1 || word.length() < child.index) {
            // find splitIndex, also considering the case that the word is some prefix of existingWord
            int splitIndex = (splitNodeIndex != -1) ? splitNodeIndex : word.length();
            split(parent, child, word, existingWord, splitIndex);
            return true;
        }
        return false;
    }

    //used in case 2, find the the index of splitted node
    private int findSplitedNodeIndex(int parentIndex, int childIndex, String word, String existingWord) {
        int length = Math.min(word.length(), childIndex);
        int i = parentIndex + 1;
        while (i < length) {
            if (existingWord.charAt(i) != word.charAt(i))
                return i;
            i++;
        }
        //no need to split
        return -1;
    }

    //used in case 2, split in splitIndex, word is at splitIndex. And move the child to splitIndexNode
    private void split(PatriciaTrieNode parent, PatriciaTrieNode child, String word, String existingWord, int splitIndex) {
        char ch = existingWord.charAt(parent.index);
        //create the solitNode
        PatriciaTrieNode splitNode = new PatriciaTrieNode();
        splitNode.index = splitIndex;
        parent.setChild(ch, splitNode);
        // move the child to the splitNode
        char oldChildChar = existingWord.charAt(splitIndex);
        splitNode.setChild(oldChildChar, child);
        //add the word in splitNode
        if (splitIndex == word.length()) {
            //do not need to create branch
            splitNode.isEndOfWord = true;
            splitNode.fullWord = word;
        } else {
            //need to branch
            PatriciaTrieNode newNode = new PatriciaTrieNode();
            newNode.index = word.length();
            newNode.isEndOfWord = true;
            newNode.fullWord = word;
            char newChildChar = word.charAt(splitIndex);
            splitNode.setChild(newChildChar, newNode);
        }
    }

    public void insert(String word) {
        if (isValidWord(word) == false)
            throw new IllegalArgumentException("word is not valid");
        if (search(word)) return;
        
        PatriciaTrieNode current = root;
        while (current.index < word.length()) {
            char ch = word.charAt(current.index);
            PatriciaTrieNode child = current.getChild(ch);
            if (child == null) {
                // Case 1: This means we are in the end of trie, then directly insert
                insertNode(current, ch, word);
                return; 
            }
            else {
                //case 2: check if we need to split and insert
                // if we have already splited and inserted, return
                if (prepareSpliting(current, child, word)) {
                    return;
                }
                //if not, we need to continue chekcing
                current = child;
            }
        }

    }

    public void remove(String word) {
        if (isValidWord(word) == false)
            throw new IllegalArgumentException("word is not valid");
        PatriciaTrieNode node = findNode(word);
        if (node != null && node.isEndOfWord && word.equals(node.fullWord)) {
            node.isEndOfWord = false;
            node.fullWord = null;
            //toPatriciaTrie will delete the node
            toPatriciaTrie(root);
        }
    }

    public void toPatriciaTrie() {
        toPatriciaTrie(root);
    }

    private static void toPatriciaTrie(PatriciaTrieNode node) {
        // Base case: if node is null, return
        if (node == null) return;
        //Recusive case
        // and we need to delete the nodes that has been removed in method remove
        //since in remove method, we didn't set it as null. The removed node is still
        // on the trie
        for (int i = 0; i < node.children.length; i++) {
            if (node.children[i] != null) {
                toPatriciaTrie(node.children[i]);
                if (!node.children[i].isEndOfWord && node.children[i].countChildren() == 0)
                    node.children[i] = null;
            }
        }
        // compress logic(similar to the pseudo code in dry)
        for (PatriciaTrieNode child : node.children) {
            if (child != null) {
                if (child.countChildren() == 1 && !child.isEndOfWord) {
                    PatriciaTrieNode grandChild = child.getOnlyChild();
                    if (grandChild != null) {
                        child.index = grandChild.index;
                        child.children = grandChild.children;
                        child.isEndOfWord = grandChild.isEndOfWord;
                        child.fullWord = grandChild.fullWord;
                        for (PatriciaTrieNode nod : child.children) {
                            if (nod != null) nod.parent = child;
                        }
                        toPatriciaTrie(child);
                    }
                }
            }
        }
    }
}