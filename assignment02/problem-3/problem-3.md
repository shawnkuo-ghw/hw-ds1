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

#### **AVL Representation:**

-   `AVLNode: <int value, AVLNode left, AVLNode right, int height>`
-   `AVL: <root: AVLNode>`

#### **AVL Representation Invariant:**

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
AVLNode insert(root: AVLNode, val: int)
    //record the visited node using stack
    stack<AVLNode> stack = new stack()
    AVLNode curr = root
    AVLNode parent = null
    
    if curr == null
        return new AVLNode(val)

    //go down
    while curr != null
        parent = curr
        stack.push(parent)
        if val < curr.value
            curr = curr.left
        else if val > curr.value
            curr = curr.right
        else
            return root

    // Create new node and link to parent
    AVLNode newNode = new AVLNode(val)
    if val < parent.value
        parent.left = newNode
    else
        parent.right = newNode

    // Pop the node to update height and rebbalance
    while !stack.isEmpty()
        AVLNode node = stack.pop()
        updateHeight(node)
        AVLNode subTree = rebalance(node)
        
        // If rotation happened, link the new subtree root to its parent
        if !stack.isEmpty()
            AVLNode ancestor = stack.readTop()
            if ancestor.left == node
                ancestor.left = subTree
            else
                ancestor.right = subTree
        else
            root = subTree
            
    return root
```

#### 2. search

```pseudocode
AVLNode search(p: AVLNode, val: int)
    while p != null && val != p.value
        if val < p.value
            p = p.left
        else
            p = p.right
    return p
```

#### 3. delete

```pseudocode
AVLNode delete(root: AVLNode, val: int)
    stack<AVLNode> stack = new stack()
    AVLNode curr = root
    
    // Find the node to delete and stored the visited node
    while curr != null && curr.value != val
        stack.push(curr)
        if val < curr.value
            curr = curr.left
        else
            curr = curr.right
            
    // Not found
    if curr == null
        throw exception

    // delete
    // Case 1: node has two children
    if curr.left != null && curr.right != null
        //find successor
        AVLNode successor = curr.right
        stack.push(curr) 
        
        // Go left until null to find successor and let curr be successor
        while successor.left != null
            stack.push(successor)
            successor = successor.left
        curr.value = successor.value
        curr = successor 

    // Case 2: Node has 0 or 1 child
    AVLNode child = null
	if curr.left != null
    	child = curr.left
	else
    	child = curr.right
    
    if stack.isEmpty()
        return child
    else
        AVLNode parent = stack.readTop()
        if parent.left == curr
            parent.left = child
        else:
            parent.right = child

    // pop the node to rebalance
    while !stack.isEmpty()
        AVLNode node = stack.pop()
        updateHeight(node)
        AVLNode subTree = rebalance(node)
        
        if !stack.isEmpty()
            AVLNode ancestor = stack.readTop()
            if ancestor.left == node
                ancestor.left = subTree
            else
                ancestor.right = subTree
        else
            root = subTree
        
    return root
```

### (c) Prove that the previous algorithms preserve the representation invariant (it is true before and after the execution of each of the 3 methods).

#### Search

> ```
> while p != null && val != p.value
>     if val < p.value
>         p = p.left
>     else
>         p = p.right
> ```

It starts at the root and go down to find a value. It does not add nodes, remove nodes, or change any links between nodes.

Since the algorithm never modifies $T$, the state of the tree after execution is same with the state before execution.

---

#### Insert

First, we record the visited node using stack. This part doesn't modify any node and doesn't break the structure.

##### Part 1

> ```
> while curr != null
>     //...store the nodes
>     if val < curr.value {curr = curr.left}
>     else if val > curr.value {curr = curr.right}
> ...
> if val < parent.value {parent.left = newNode}
> else {parent.right = newNode}
> ```

In this part, we go down the tree to find an empty position. If the value we want to add is smaller than the curr node, go left. If bigger, go right. Then We link the newNode to this position.

Since "smaller go left, bigger go right," we put `newNode` in the exact position required by the BSt Invariant (i.e. $\forall l \in Left, l < n < r$).

We link a new leaf node to an existing tree. Since a leaf cannot link back to ancestors, the tree remains Acyclic.


##### Part 2

> ```
> while !stack.isEmpty():
>     AVLNode node = stack.pop()
>     updateHeight(node)
>     AVLNode subTree = rebalance(node)
>     // ...link subTree to parent ...
> ```

We go back up the tree from the new node to the root using stack. For every node we going through, we recalculate its height. Then we call rebalance, thus we check if the left and right loose balance, then we rotate the nodes to fix it if needed

The `updateHeight(node)` method recalculate the height based on children ($1 + \max(h_L, h_R)$). Since we do this from the bottom up, every node's height is updated

The `rebalance(node)` method checks the balance factor ($h_R - h_L$).
If the factor is $\in \{-1, 0, 1\}$, do notiing. If the factor is $\pm 2$, rotations are needed
These rotations only move whole subtrees, so the BST order is preserved while we restore the balance

Thus the RI perserved

---

#### Delete

First, we find the node to delete and stored the visited node. This part doesn't modify any node and doesn't break the structure.

##### Part 1

> ```
> // Case 1: Node has two children
> if curr.left != null && curr.right != null:
>     // ... find successor
>     curr.value = successor.value
>     curr = successor // let curr be successor
> 	// Case 2: Node has 0 or 1 child
> 	// ...link parent to child ...
> ```

- If it has two children, we find the successor, let curr be successor.
- If it has 0 or 1 child, we just remove it and connect its parent to its child.

Replacing a node by a successor preserves the order. Because the successor is larger than every nodes in the left subtree and smaller than every node in the right subtree.

Connect a parent to a child by skipping the deleted node which also preserves the order of the remaining nodes

And the rotations in `rebalance` rearrange whole subtrees, so they also keep this BST order

Since we only remove link or pop stack instead of creating links to ancestors, thus it is acyclic

##### Part 2

> ```
> while !stack.isEmpty():
>     AVLNode node = stack.pop()
>     updateHeight(node)
>     AVLNode subTree = rebalance(node)
>     // ...link subTree to parent
> ```

Similar to insert, removing a node might make one side be short. Thus we need to go back up the tree using stack and fix the heights and use rebalance to rotate the tree if node becomes unbalanced.

Deleting changes the height of the subtrees. But `stack.pop()` ensures we visit every ancestor of the deleted node. `updateHeight` ensures $n.height$ matches the new tree.

Deleting can make a node's balance factor to become $\pm 2$. But the `rebalance` function can fix this by rotations

The `while` loop ensures we check every ancestor's hegiht up to the root by rebalance

Thus after the loop finished, the tree is sorted and the hiight is balanced

Thus the invariant is preserved
