### Exercise 3 : Tries

#### Task 1: Verifying Correctness

​	[code in the wet part : `Figure`]

Result : the test come up with errors for 

`nextWordInTrie(String word)` and `findNextWordFromNode(TrieNode, prefix)`

#### Task 2 : Bug Identification

1. In `findNextWordFromNode(TrieNode node, String prefix)`

   It fails to **check** if the current node is the **end** of a valid word. This bug leads to it incorrectly skipping over valid words and jumps straight into the children.

   Here's the test case reveals its failure:

   ```java
   Trie.TrieNode sNode = root.children['s' - 'a'];
       assertEquals("ame", trie.findNextWordFromNode(sNode, "s"));
   ```

   `"same"` exists in the trie and should be returned. However,  `node.isWordEnd` isn't checked, the method skips `"same"` and dives into deeper children.

2. In `nextWordInTrie(String word)`

   It tried to access the root if `j == -1`, but the loop starts at `path.size() - 2`, so `j` is never `-1`. As a result, it may skip valid alternatives.

   Here's the test case reveals its failure:

   ```java
   assertEquals("save", trie.nextWordInTrie("same"));
   ```

   The root can not be accessed, then the backtracking algorithm goes wrong. So it will never explore "save" from the parent node 's' after reaching "same".

#### Task 3: Bug Fixing

​	[code in the wet part : `Fix`]

