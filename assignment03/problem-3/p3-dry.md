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
        for each child in node.children do
            if child != null do
            if |child.children| = 1 and !child.EndOfWord then
                grandChild = child.child
                child.index = grandChild.index
                child.children = grandChild.children
                grandChild is a word iff child is a word  
                for each node in child.children do
                    if (nod != null) then nod.parent = child end if   
                end for   
            end if
        end for
    ```
2. Write the pseudocode of search, insert and remove in a Patricia Trie.
    Here is the structure of Patricia Trie Node
    - search
        ```java
        //find node accroding to the prefix
        findNode(prefix)
            if isValidWord(prefix) is false then throw exception end if
            current = root
            while current != null and current.index < |prefix| do
                i = current.index
                ch = prefix.CharAt(i)
                child = current.getChild(ch)
                if child == null return null end if
                current = child
            end while
            return current
                
        search(word)
            if isValidWord(prefix) is false then throw exception end if
            node = findNode(word)
            //this node is a word iff it's not null, it is a word and equals word
            return node != null and node.isEndOfWord and node.fullWord.equals(word)
        ```
    - insert
        ```java
        insert(word)
            if isValidWord(word) is false then throw exception end if
            if search(word) is true then return end if
            current = root
            while current.index < word.length do
                ch = word.charAt(current.index)
                child = current.getChild(ch)
                if child is null then
                    insertNode(current, ch, word)
                    return
                else
                    if prepareSpliting(current, child, word) is true then
                        return
                    end if
                    current = child
                end if
            end while
        ```
        
        These functions are used to insert

        ```java
        //return if we need to process the child depends on whether we need to split the path
        prepareSpliting(parent, child, word)
            existingWord = getOneWord(child)
            splitNodeIndex = findSplitedNodeIndex(parent.index, child.index, word, existingWord)
            // if we find the splited index, the splitNodeIndex is not -1
            // But if splitNodeIndex == -1, it is also possible that the word is some prefix of existingWord
            if splitNodeIndex != -1 or word.length < child.index then
                if splitNodeIndex != -1 then 
                    // find splitIndex, also considering the case that the word is some prefix of existingWord
                    splitIndex = splitNodeIndex 
                else 
                    splitIndex = word.length 
                end if
                split(parent, child, word, existingWord, splitIndex)
                return true
            end if
            return false
        
        findSplitedNodeIndex(parentIndex, childIndex, word, existingWord)
            length = min(word.length, childIndex)
            i = parentIndex + 1
            while i < length do
                if existingWord.charAt(i) != word.charAt(i) then
                    return i
                end if
                i++
            end while
            //no need to split
            return -1

        //split in splitIndex, word is at splitIndex. And move the child to splitIndexNode
        split(parent, child, word, existingWord, splitIndex)
            ch = existingWord.charAt(parent.index)
            //create the splitNode
            splitNode = new PatriciaTrieNode()
            splitNode.index = splitIndex
            parent.setChild(ch, splitNode)
        
            // move the child to the splitNode
            oldChildChar = existingWord.charAt(splitIndex)
            splitNode.setChild(oldChildChar, child)
        
            //add the word in splitNode
            if splitIndex == word.length then
                //do not need to create branch
                splitNode.isEndOfWord = true
                splitNode.fullWord = word
            else
                //need to branch
                newNode = new PatriciaTrieNode()
                newNode.index = word.length
                newNode.isEndOfWord = true
                newNode.fullWord = word
                newChildChar = word.charAt(splitIndex)
                splitNode.setChild(newChildChar, newNode)
            end if
        ```
    - remove
        ```java
        remove(word)
        if isValidWord(word) is false then throw exception end if
        node = findNode(word)
        if node != null and node.isEndOfWord and word equals node.fullWord then
            node.isEndOfWord = false
            node.fullWord = null
            toPatriciaTrie(root)
        end if
        ```