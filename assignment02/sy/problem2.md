### Problem 2
[10p] Show that the recursive version of DELETE algorithm for AVL trees runs in O($\log$ n).

Hint: you need to show the complexity of all functions that are invoked by DELETE.

We know the complexity = (complexity per level) $\times$ (height of tree)

Let's first consider the worst case and assume the total number of nodes is $n$

Then the height of the AVL tree is $\log n$

```java
    //O(log n)
    private AVLNode delete(AVLNode curr, int val) {
        //Case1: This case is O(1)
        if (curr == null) {
            return null; //O(1)
        } 
        /* Case 2: at this level we do O(1) work,
         * then we go down one level by calling delete on a child.
         * In the worst case, we can go down (the height of the tree) levels,
         * so the total work along this path is O(1) * log (n) = O(log n).
         */
        else if (val < curr.val) {
            curr.left = delete(curr.left, val); 
        } 
        /* Case 3: at this level we also do O(1) work,
         * then we go down one level by calling delete on a child.
         * In the worst case, we again go down (the height of the tree) levels,
         * so the total work along this path is O(1) * log (n) = O(log n).
         */
        else if (val > curr.val) {
            curr.right = delete(curr.right, val); 
        } 
        //Case 4:
        else {
            //Subcase 1: O(1)
            if (curr.left == null) {
                return curr.right; //O(1)
            } 
            //Subcase 2: O(1)
            else if (curr.right == null) {
                return curr.left; //O(1)
            } 
            //Subcase 3: 
            else {
                //O(log n) since findSuccessor is O(log n)
                AVLNode succ = findSuccessor(curr);
                //O(1)
                curr.val = succ.val;
                /* For this recursive call we do O(1) work per level,
                 * then go down, and the height of the tree starting
                 * at curr.right is less or equal to log (n).
                 * Thus in the worst case this call to delete visits (the height of the tree) levels,
                 * so its complexity is O(1) * log (n) = O(log n).
                 */
                curr.right = delete(curr.right, succ.val);
            }
        }
        // O(1) since rebalance is O(1)
        return rebalance(curr);
    }

    //Worst case: O(log n)
    private AVLNode findSuccessor(AVLNode curr) {
        //O(1)
        AVLNode succ = curr.right;
        // O(1) * log n = O(log n) when curr is root
        while (succ.left != null) {
            succ = succ.left;
        }
        return succ;
    }

    // O(1)
    private AVLNode rebalance(AVLNode curr) {
        // O(1) since balanceFactor, height, rightRotate and leftRightRotate are all O(1)
        if (balanceFactor(curr) < -1) {
            if(height(curr.left) >= height(curr.right))
                curr = rightRotate(curr);
            else
                curr = leftRightRotate(curr);
        }
        // O(1) since balanceFactor, height, leftRotate and rightLeftRotate are all O(1)
        else if (balanceFactor(curr) > 1) {
            if(height(curr.right) >= height(curr.left))
                curr = leftRotate(curr);
            else
                curr = rightLeftRotate(curr);
        }
        //O(1) since updateHeight is O(1)
        updateHeight(curr);
        return curr;
    }

    // O(1)
    private AVLNode leftRotate(AVLNode p) {
        //O(1)
        AVLNode q = p.right;
        p.right = q.left;
        if(p == root)
            root = q;

        // O(1) since updateParentsLeftRotate is O(1)
        updateParentsLeftRotate(p, q);
        q.left = p;
        // O(1) since updateHeight is O(1) 
        updateHeight(p);
        updateHeight(q);
        return q;
    }

    //O(1)
    private void updateParentsLeftRotate(AVLNode p, AVLNode q) {
        //O(1)
        if (q.left != null) {
            q.left.parent = p;
        }
        //O(1)
        q.parent = p.parent;
        //O(1)
        if (p.parent != null) {
           if (p == p.parent.left) {
             p.parent.left = q;
            } else {
               p.parent.right = q;
            };
        }
        //O(1)
        p.parent = q;

    }
    
    // O(1)
    private AVLNode rightRotate(AVLNode p) {
        //O(1)
        AVLNode q = p.left;
        p.left = q.right;
        if(p == root)
            root = q;

        // O(1)
        updateParentsRightRotate(p, q);
        //O(1)
        q.right = p;
      
        //O(1) since updateHeight is O(1)
        updateHeight(p);
        updateHeight(q);
        return q;
    }

    //O(1)
    private void updateParentsRightRotate(AVLNode p, AVLNode q) {
        //O(1)
        if (q.right != null) {
            q.right.parent = p;
        }
        //O(1)
        q.parent = p.parent;
        //O(1)
        if (p.parent != null) {
            if (p == p.parent.right) {
                p.parent.right = q;
            } else {
                p.parent.left = q;
            }
        }
        //O(1)
        p.parent = q;

    }

    // O(1) since leftRotate and rightRotate are both O(1)
    private AVLNode leftRightRotate(AVLNode curr) {
        curr.left = leftRotate(curr.left);
        return rightRotate(curr);
    }

    // O(1) since leftRotate and rightRotate are both O(1)
    private AVLNode rightLeftRotate(AVLNode curr) {
        curr.right = rightRotate(curr.right);
        return leftRotate(curr);
    }

    //O(1) since hight is O(1)
    public int balanceFactor(AVLNode curr) {
        return height(curr.right) - height(curr.left);
    }

    //O(1)
    public int height() {
        return height(root);
    }

    //O(1)
    private int height(AVLNode curr) {
        if (curr == null) {
            return 0;
        } else {
            return curr.height;
        }
    }

    // O(1)
    private void updateHeight(AVLNode curr) {
        curr.height = 1 + Math.max(height(curr.left), height(curr.right));
    }
```