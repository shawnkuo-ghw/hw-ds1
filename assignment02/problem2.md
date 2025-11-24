### Problem 2
```java
    // remove an element from the AVL tree rooted at curr
    private AVLNode delete(AVLNode curr, int val) {
        if (curr == null) {
            return null;
        } else if (val < curr.val) {
            curr.left = delete(curr.left, val);
        } else if (val > curr.val) {
            curr.right = delete(curr.right, val);
        } else {
            // found the node to be removed
            if (curr.left == null) {
                return curr.right;
            } else if (curr.right == null) {
                return curr.left;
            } else {
                // the node to be removed has two children
                // find the successor of the current node
                AVLNode succ = findSuccessor(curr);
                // copy the value
                curr.val = succ.val;
                // remove the successor
                curr.right = delete(curr.right, succ.val);
            }
        }
        // rebalance the tree
        return rebalance(curr);
    }

    private AVLNode findSuccessor(AVLNode curr) {
        AVLNode succ = curr.right;
        // find the inorder successor
        while (succ.left != null) {
            succ = succ.left;
        }
        return succ;
    }

    // rebalance the AVL tree rooted at curr
    private AVLNode rebalance(AVLNode curr) {
        System.out.println("before rebalance of node: [" + curr.val+"]");
        print();
        // check if the tree is left heavy
        if (balanceFactor(curr) < -1) {
            if(height(curr.left) >= height(curr.right))
                curr = rightRotate(curr);
            else
                curr = leftRightRotate(curr);
        }
        // check if the tree is right heavy
        else if (balanceFactor(curr) > 1) {
            if(height(curr.right) >= height(curr.left))
                curr = leftRotate(curr);
            else
                curr = rightLeftRotate(curr);
        }
        updateHeight(curr);
        System.out.println("after rebalance");
        print();

        return curr;
    }

    // left rotate the subtree rooted at curr
    private AVLNode leftRotate(AVLNode p) {
        AVLNode q = p.right;
        p.right = q.left;
        if(p == root)
            root = q;

        // optional update parents
        updateParentsLeftRotate(p, q);
        q.left = p;
        // update the height of the current node
        updateHeight(p);
        updateHeight(q);
        return q;
    }

    private void updateParentsLeftRotate(AVLNode p, AVLNode q) {
        if (q.left != null) {
            q.left.parent = p;
        }
        q.parent = p.parent;
        if (p.parent != null) {
           if (p == p.parent.left) {
             p.parent.left = q;
            } else {
               p.parent.right = q;
            };
        }
        p.parent = q;

    }
    
    // right rotate the subtree rooted at curr
    private AVLNode rightRotate(AVLNode p) {
        AVLNode q = p.left;
        p.left = q.right;
        if(p == root)
            root = q;

        // optional update parents
        updateParentsRightRotate(p, q);
        // 
        q.right = p;
      
        // update the height of the current node
        updateHeight(p);
        updateHeight(q);
        return q;
    }

    private void updateParentsRightRotate(AVLNode p, AVLNode q) {
        if (q.right != null) {
            q.right.parent = p;
        }
        q.parent = p.parent;
        if (p.parent != null) {
            if (p == p.parent.right) {
                p.parent.right = q;
            } else {
                p.parent.left = q;
            }
        }
        p.parent = q;

    }

    // left-right rotate the subtree rooted at curr
    private AVLNode leftRightRotate(AVLNode curr) {
        curr.left = leftRotate(curr.left);
        return rightRotate(curr);
    }

    // right-left rotate the subtree rooted at curr
    private AVLNode rightLeftRotate(AVLNode curr) {
        curr.right = rightRotate(curr.right);
        return leftRotate(curr);
    }

    // compute balance factor of the AVL tree
    public int balanceFactor(AVLNode curr) {
        return height(curr.right) - height(curr.left);
    }

    public int height() {
        return height(root);
    }

    private int height(AVLNode curr) {
        if (curr == null) {
            return 0;
        } else {
            return curr.height;
        }
    }

    // update the height of the AVL tree rooted at curr
    private void updateHeight(AVLNode curr) {
        curr.height = 1 + Math.max(height(curr.left), height(curr.right));
    }
```