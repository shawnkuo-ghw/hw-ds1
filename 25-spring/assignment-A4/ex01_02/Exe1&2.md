## Exercise 1: Minimum Spanning Tree

##### 1: Different spanning trees

None of them is correct. The first one misses one case(A $-$ B, A $-$ C, C $-$ E, C $-$ D).

The second one includes two wrong cases(A–C, A–E, C–E, C–D; A–C, A–E, C–E, D–E).

We will explicitly construct all distinct spanning trees by Kruskal's algorithm.

There are 5 vertices, then MSTs must have 4 edges in total.

**Sorting all edges:**

- Weight 1: A $-$ B, A $-$ E, A $-$ C, C $-$ E (there are 4 edges with the same weight, then we can list them in different orders)
- Weight 2: B $-$ E, C $-$ D, D $-$ E (similar to the above)
- Weight 3: B $-$ D

We insert the edges with weight 1 to T firstly, then with weight 2, and with weight 3 finally. 

**All Minimum Spanning Trees** (Weight = 5) : (They follow different lists of edges)

1. **A $-$ B, A $-$ E, A $-$ C, C $-$ D :** Add A $-$ B, A $-$ E, A $-$ C. We can not add C $-$ E since it will form a cycle. Follow the list of edges, the next lightest edge that will not form a cycle is C $-$ D. Add C $-$ D into T. Weight(T) = 1 + 1 + 1 + 2 = 5.
2. **A $-$ B, A $-$ E, A $-$ C, D $-$ E :** Add A $-$ B, A $-$ E, A $-$ C. We can not add C $-$ E since it will form a cycle. Follow the list of edges, the next lightest edge that will not form a cycle is D $-$ E. Add D $-$ E into T. Weight(T) = 1 + 1 + 1 + 2 = 5.

3. **A $-$ B, A $-$ E, C $-$ E, C $-$ D :** Add A $-$ B, A $-$ E, C $-$ E. We can not add A $-$ C since it will form a cycle. The next lightest edge that will not form a cycle is C $-$ D. Add C $-$ D into T. Weight(T) = 1 + 1 + 1 + 2 = 5.

4. **A $-$ B, A $-$ E, C $-$ E, D $-$ E :** Add A $-$ B, A $-$ E, C $-$ E. We can not add A $-$ C since it will form a cycle. The next lightest edge that will not form a cycle is D $-$ E. Add D $-$ E into T. Weight(T) = 1 + 1 + 1 + 2 = 5.

5. **A $-$ B, A $-$ C, C $-$ E, D $-$ E :** Add A $-$ B, A $-$ C, C $-$ E. We can not add A $-$ E since it will form a cycle. The next lightest edge that will not form a cycle is D $-$ E. Add D $-$ E into T. Weight(T) = 1 + 1 + 1 + 2 = 5.

6. **A $-$ B, A $-$ C, C $-$ E, C $-$ D :** Add A $-$ B, A $-$ C, C $-$ E. We can not add A $-$ E since it will form a cycle. The next lightest edge that will not form a cycle is C $-$ D. Add C $-$ D into T. Weight(T) = 1 + 1 + 1 + 2 = 5.

There are totally 6 conditions.

##### 2. Kruskal

We see that the first answer is correct. According to the above process we built the MSTs, we can know that all six of the MST can be obtained by Kruskal's algorithm. When we sort all edges from lightest to heaviest,  we can list edges of the same weight in different order. Hence, we can get different lists of edges and then built distinctive minimum spanning trees.



### Exercise 2 : Dismounting sequence

#### Task 1: Algorithm

```python
List<E> dismounting(Graph<E> graph):
    visited ← set
    dismountOrder ← list
        
    for node in gragh:
		if node not in visited:
			DFS_recur(node, graph, visited, dismountOrder)
    
    return removeOrder
```

In the algorithm we design for `dismounting`, we:

1. Create an empty set `visited` to store the nodes already been visited.

   Create an empty list `dismountOrder` to store the result list.

2. Use **DFS** to traverse the graph

   ```python
   void DFS_recur(node, graph, visited, order):
       add node to visited
       for nextNode in neighbors(node):
       	if nextNode not in visited:
               DFS_recur(nextNode, graph, visited, order)
       add node to order
   ```

   - Mark current node as visited if it is not.
   - For each node depends on this, if it is not visited, traverse by DFS
   - After all children are explored, add node to the list `dismountOrder`

3. Return the list `dismountOrder`. Now the length of the list should equal to the size of the set `visited`.

#### Task 2: Complexity Analysis

​	The dismounting algorithm visits each vertex and each edge exactly once using DFS. And the other operations, like conditional check, all run in O(1). Therefore, the worst-case time complexity is the same as DFS: 
$$
\mathbf{O}(V+E)
$$
, where $V$ is the number of vertices (components),

​	      $E$ is the number of edges (dependency relations).

#### Task 3: Implementation

​	[code in the wet part : `DismountOrder`]
