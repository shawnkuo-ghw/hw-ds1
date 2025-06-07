### Exercise 3 : Tries

#### Task 1: Verifying Correctness

​	[code in the wet part : `Figure`]

Result : the test come up with errors for 

`nextWordInTrie(String word)` and `findNextWordFromNode(TrieNode, prefix)`

#### Task 2 : Bug Identification

In `nextWordInTrie(String word)`

It tried to access the root if `j == -1`, but the loop starts at `path.size() - 2`, so `j` is never `-1`. As a result, it may skip valid alternatives.

Here's the test case reveals its failure:

```java
assertEquals("save", trie.nextWordInTrie("same"));
```

The root can not be accessed, then the backtracking algorithm goes wrong. So it will never explore "save" from the parent node 's' after reaching "same".

#### Task 3: Bug Fixing

​	[code in the wet part : `Fix`]

