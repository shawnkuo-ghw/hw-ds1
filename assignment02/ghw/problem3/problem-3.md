## Problem 3

>   3.   [20p] Given an AVL designed using following data structure:
>
>        `AVL: <root: AVLNode>`
>
>        `AVLNode: <int value, AVLNode left, AVLNode right, int height>`
>
>        **a.** [2p] Provide the representation invariant for this data structure to effectively represent an AVL.
>
>        **b.** [3p] Provide the pseudocode of the iterative (non-recursive) versions of algorithms to insert, delete and search elements in the AVL.
>
>        **c.** [6p] Prove that the previous algorithms preserve the representation invariant (it is true before and after the execution of each of the 3 methods).
>
>        **d.** [7p] Implement the algorithms in Java, including the method boolean repOK() that checks the representation invariant, and provide meaningful tests, including test that checks repOK().

### (a) Provide the representation invariant for this data structure to effectively represent an AVL.

#### **AVL Representation :**

-   `AVLNode: <int value, AVLNode left, AVLNode right, int height>`
-   `AVL: <root: AVLNode>`

#### AVL Representation Invariant :

$$\begin{aligned}
RI(T) =\ & T \text{ is Acyclic w.r.t left and right} \\
\land \ & \forall n \in \text{nodes}(T): (\forall l \in \text{LeftSubtree}(n), l.value < n.value) \land (\forall r \in \text{RightSubtree}(n), r.value > n.value) \\
\land \ & \forall n \in \text{nodes}(T): \text{balance}(n) \in \{-1, 0, 1\} \\
\land \ & \forall n \in \text{nodes}(T): n.height = \text{height}(n)
\end{aligned}$$

where $height(n)$ is:

- If $n$ is null: return $0$ 
- Otherwise: return $1 + \max(height(n.left), height(n.right))$

$balance(n)$ is $height(n.right) - height(n.left)$.

### (b) Provide the pseudocode of the iterative (non-recursive) versions of algorithms to insert, delete and search elements in the AVL.

#### 1. insert

```pseudocode
Function insertAVL(root: AVLNode, val: int) -> AVLNode
    // Insert with Stack help to track path
    Stack<AVLNode> path = new Stack()
    AVLNode current = root
    AVLNode parent = null
    
    if current == null:
        return new AVLNode(val)

    // Traverse down
    while current != null:
        parent = current
        path.push(parent)
        if val < current.value:
            current = current.left
        else if val > current.value:
            current = current.right
        else:
            return root

    // New node and link to parent
    AVLNode newNode = new AVLNode(val)
    if val < parent.value:
        parent.left = newNode
    else:
        parent.right = newNode

    // Pop the stack to update heights and rebalance
    while not path.isEmpty():
        AVLNode node = path.pop()
        updateHeight(node)
        AVLNode subTree = rebalance(node)
        
        // If rotation happened, link the new subtree root to its parent
        if not path.isEmpty():
            AVLNode ancestor = path.readTop()
            if ancestor.left == node:
                ancestor.left = subTree
            else:
                ancestor.right = subTree
        else:
            root = subTree
            
    return root
```

#### 2. search

```pseudocode
Function searchAVL(p: AVLNode, val: int) -> AVLNode
    while p != null and val != p.value:
        if val < p.value: 
            p = p.left
        else:
            p = p.right
    return p
```

#### 3. delete

```pseudocode
Function deleteAVL(root: AVLNode, val: int) -> AVLNode
    Stack<AVLNode> path = new Stack()
    AVLNode current = root
    
    // Find the node to delete and track path
    while current != null and current.value != val:
        path.push(current)
        if val < current.value:
            current = current.left
        else:
            current = current.right
            
    // Not found
    if current == null:
        return root

    // Deletion
    // Case: Node has two children
    if current.left != null and current.right != null:
        // Find successor
        AVLNode successor = current.right
        path.push(current) 
        
        // Go left until null to find successor
        while successor.left != null:
            path.push(successor)
            successor = successor.left
            
        // Swap
        current.value = successor.value
        
        // Delete
        current = successor 

    // Case: Node has 0 or 1 child
    AVLNode child = null
	if current.left != null:
    	child = current.left
	else:
    	child = current.right
    
    if path.isEmpty():
        // Deleting the root
        return child
    else:
        AVLNode parent = path.readTop()
        if parent.left == current:
            parent.left = child
        else:
            parent.right = child

    // pop the path to rebalance
    while not path.isEmpty():
        AVLNode node = path.pop()
        updateHeight(node)
        AVLNode subTree = rebalance(node)
        
        if not path.isEmpty():
            AVLNode ancestor = path.readTop()
            if ancestor.left == node:
                ancestor.left = subTree
            else:
                ancestor.right = subTree
        else:
            root = subTree
            
    return root
```

### (c) Prove that the previous algorithms preserve the representation invariant (it is true before and after the execution of each of the 3 methods).

Search

> ```
> while p != null and val != p.value:
>     if val < p.value:
>         p = p.left
>     else:
>         p = p.right
> ```

It starts at the root and go down to find a value. It does not add nodes, remove nodes, or change any links between nodes.

Since the algorithm never modifies $T$, the state of the tree after execution is same with the state before execution.

---

Insert

Part 1

> ```
> while current != null:
>     // ... (path tracking)
>     if val < current.value: current = current.left
>     else if val > current.value: current = current.right
> // ...
> if val < parent.value: parent.left = newNode
> else: parent.right = newNode
> ```

Go down the tree to find an empty position. If the value we want to add is smaller than the current node, we go left; if bigger, we go right. We link the newNode to this position.

Since "smaller goes left, bigger go right," we place `newNode` in the exact position required by the BST Invariant (i.e. $\forall l \in Left, l < n < r$).

We link a new leaf node to an existing tree. Since a leaf cannot link back to ancestors, the tree remains Acyclic.


Part 2

> ```
> while not path.isEmpty():
>     AVLNode node = path.pop()
>     updateHeight(node)
>     AVLNode subTree = rebalance(node)
>     // ... link subTree to parent ...
> ```

We go back up the tree from the new node to the root (using stack). For every node we pass, we recalculate its height. Then we call rebalance, thus we check if the "left" and "right" sides loose balance, then we rotates the nodes to fix it if needed

The `updateHeight(node)` function recalculates the height based on children ($1 + \max(h_L, h_R)$). Since we do this from the bottom up, every node's height is corrected.

The `rebalance(node)` function checks the balance factor ($h_R - h_L$).
If the factor is $\in \{-1, 0, 1\}$, no changes. If the factor is $\pm 2$, rotations are performed.
Rotations can reduce the height difference to preserve the BST Order

Thus the RI perserved

---

Delete

Part 1

> ```
> // Case: Node has two children
> if current.left != null and current.right != null:
>     // ... find successor ...
>     current.value = successor.value
>     current = successor // delete successor instead
> 	// Case: Node has 0 or 1 child
> 	// ... link parent to child ...
> ```

First, we find the node to delete.

- If it has two children, we find the successor, copy its value here, and then delete the successor instead.
- If it has 0 or 1 child, we just cut it and connect its parent to its only child.

Replacing a node with its successor maintains the sorted order. Because the successor is larger than everything in the left subtree and smaller than everything else in the right subtree.

Connecting a parent to a child (skipping the deleted node) preserves the relative order of the remaining nodes.

We are only removing links or shortening paths, never creating links to ancestors, thus it is acyclic

Part 2

> ```
> while not path.isEmpty():
>     AVLNode node = path.pop()
>     updateHeight(node)
>     AVLNode subTree = rebalance(node)
>     // ... link subTree to parent ...
> ```

Similar to insert, removing a node might let one side of the tree short. We go back the tree using the stack and fix the heights and use rebalance to rotate the tree if node become unbalanced.

Deletion changes the height of the subtrees. But `path.pop()` ensures we visit every ancestor of the deleted node. `updateHeight` ensures $n.height$ matches the new tree.

Deletion can make a node's balance factor to become $\pm 2$. But the `rebalance` function can fix this by rotations.

Deletion might require rotations at many levels. The `while` loop ensures we check every ancestor up to the root.

Thus after the loop, the tree satisfies both the sorting property and the height balance property.

Thus the invariant is preserved.

### (d) Implement the algorithms in Java, including the method boolean `repOK()` that checks the representation invariant, and provide meaningful tests, including test that checks repOK()
