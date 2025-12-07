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

Let `T` be an AVL tree, than `AVL-RI(T) = BST-RI(T) && AVL-BAL(T)`, where

-   `BST-RI(T)` is the representation invariant of binary search tree
-   `AVL-BAL(T)` is the AVL balance condition

To be more specificly,

`BST-RI(T) = ∀x: node of T, x.left.value < x.value && x.value < x.right.value`

`AVL-BAL(T) = ∀x: node of T, balance(x) ∈ {-1, 0, 1}`

-   `balance(x)` is the balance factor of node `x`

### (b) Provide the pseudocode of the iterative (non-recursive) versions of algorithms to insert, delete and search elements in the AVL.

#### 1. insert

```pseudocode
Function insertAVL(p: AVLNode, val: int)
	
```

```pseudocode
Function rebalance(p: AVLNode)
```

#### 2. search

```pseudocode
Function searchAVL(p: AVLNode, val: int)
	while p != null and val != p.val:
		if val < p.val: p = p.left
		else p = 
```

#### 3. delete

```pseudocode
```

### (c) Prove that the previous algorithms preserve the representation invariant (it is true before and after the execution of each of the 3 methods).

### (d) Implement the algorithms in Java, including the method boolean `repOK()` that checks the representation invariant, and provide meaningful tests, including test that checks repOK()
