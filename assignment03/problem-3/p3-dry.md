### 3. Working with tries (Dry and Wet)
Given a Trie that is designed using TrieNode, nodes that contains an array of 26 child nodes (see tutorial 8).
1. Write the pseudocode of a function that takes a Trie and converts it into a Patricia Trie.
    ```java
    toPatriciaTrie(node)
        //Base case: if node is null, return
        if node is null then return
        //recurse all child
        for each child in node.children do
            toPatriciaTrie(child)
        end for
        for each child node.children do
            if child != null do
            if |child.children| = 1 and !child.EndOfWord then
                grandChild = child.child
                child.index += grandChild.index
                child.children = grandChild.children
                grandChild is a word iff child is a word          
    ```
2. Write the pseudocode of search, insert and remove in a Patricia Trie.
    Here is the structure of Patricia Trie Node
    - search
        ```java
        findNode(prefix)
            if prefix == null throw exception end if
            current = root
            while current != null && current.index < |prefix| do
                i = current.index
                ch = prefix.CharAt(i)
                child = current.getChild(ch)
                if child == null return null end if
                current = child
            end while
            return current
                
        search(word)
            if (word == null) throw exception end if
            node = findNode(word)
            return node != null and node.isEndOfWord and node.fullWord.equals(word)
        ```
    - insert
        ```java
        void insert(String word)
            if (word == null) exception
            if(search(word) == true)
                return
            PatriciaTrieNode current = root
            while(current != null && current.index < word.length())
                int i = current.index
                char ch = word.charAt(i)
                PatriciaTrieNode child = current.getChild(ch)
                if (child == null) 
                    PatriciaTrieNode newNode = new PatriciaTrieNode()
                    newNode.index = current.index
                    newNode.index++
                    current.setChild(ch, newNode)
                    child = newNode
                current = child
            if (current != null) 
                current.isEndOfWord = true;
                current.fullWord = word;
        ```
    - remove
        ```java
         void remove(String word)
            if (word == null) exception
            if(search(word) == false)
                return
            PatriciaTrieNode node = findNode(word)
            if (node == null) return;
            if(node.countChildren() != 0) 
                node.isEndOfWord = false
                node.fullWord = null
            else
                PatriciaTrieNode parent = node.parent
                if(parent != null)
                    if (parent.index >= 0 && parent.index < word.length())
                        parent.setChild(word.charAt(parent.index), null)
                else
                    node.isEndOfWord = false
                    node.fullWord = null
            toPatriciaTrie(root)
            ```
3. Implement the pseudocodes of the 2 previuos items in Java
4. Provide meaningful test.