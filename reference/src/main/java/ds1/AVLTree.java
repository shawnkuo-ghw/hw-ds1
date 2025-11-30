package ds1;

class AVLNode {
    int val;
    AVLNode left;
    AVLNode right;
    AVLNode parent;
    int height;

    AVLNode(int x) {
        val = x;
        height = 1;
    }
    int getValue() {
        return val;
    }

}

public class AVLTree {
    AVLNode root;

    // create an empty AVL tree
    public AVLTree() {
        root = null;
    }

    // get the root of the AVL tree
    public int getRootValue() {
        return root.val;
    }

    // Helpers for testing
    public Object getRoot() {
        return root;
    }

    // get the left child of the AVL tree
    public AVLNode getLeft() {
        return root.left;
    }

    public AVLNode getRight() {
        return root.right;
    }
    
    // compute balance factor of the AVL tree
    public int balanceFactor(AVLNode curr) {
        return height(curr.right) - height(curr.left);
    }

    // computes the height of the AVL tree
    public int height() {
        return height(root);
    }

    // computes the height of the AVL tree rooted at curr
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

    // search for an element in the AVL tree
    public boolean search(int val) {
        return search(root, val);
    }

    // search for an element in the AVL tree rooted at curr
    private boolean search(AVLNode curr, int val) {
        if (curr == null) {
            return false;
        } else if (val == curr.val) {
            return true;
        } else if (val < curr.val) {
            return search(curr.left, val);
        } else {
            return search(curr.right, val);
        }
    }

    //insert into an AVL tree
    public void insert(int val) {
        root = insert(root, val);
    }

    // insert into an AVL tree rooted at curr
    private AVLNode insert(AVLNode curr, int val) {
        if(curr == null) {
            curr = new AVLNode(val);
        }
        else if(val < curr.val) {
            curr.left = insert(curr.left, val);
        }
        else if(val > curr.val){
            curr.right = insert(curr.right, val);
        }
        else {
            throw new IllegalArgumentException("duplicate val");
        }
        return rebalance(curr);
    }

    // remove an element from the AVL tree
    public void delete(int val) {
        root = delete(root, val);
    }

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

    public ListoverLinkedList<Integer> inorder() {
        return inorder(root);
    }

    // get a list of the nodes of the AVL tree in inorder
    private ListoverLinkedList<Integer> inorder(AVLNode curr) {
        ListoverLinkedList<Integer> list = new ListoverLinkedList<Integer>();
        if (curr != null) {
            list.append(inorder(curr.left));
            list.insertRear(curr.val);
            list.append(inorder(curr.right));
        }
        return list;
    }

    // pretty print the AVL tree
    public void print() {
        System.out.println("AVL Tree");
        print(root, "");

    }

    // pretty print the AVL tree rooted at curr
    private void print(AVLNode curr, String prefix) {
        if (curr != null) {
            print(curr.right, prefix + "    ");
            System.out.println(prefix + curr.val + "(" + curr.height + "," + balanceFactor(curr) + ")");
            print(curr.left, prefix + "    ");
        }
    }
}
