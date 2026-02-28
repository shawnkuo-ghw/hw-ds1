package ds1.util;

class AVLNode<T extends Comparable<T>> {
    T val;
    AVLNode<T> left;
    AVLNode<T> right;
    AVLNode<T> parent;
    int height;

    AVLNode(T x) {
        val = x;
        height = 1;
    }
    T getValue() {
        return val;
    }

}

public class AVLTree<T extends Comparable<T>> {
    private static final boolean debugging = false;
    AVLNode<T> root;
    // create an empty AVL tree
    public AVLTree() {
        root = null;
    }

    // get the root of the AVL tree
    public T getRootValue() {
        return root.val;
    }

    // Helpers for testing
    public Object getRoot() {
        return root;
    }

    // get the left child of the AVL tree
    public AVLNode<T> getLeft() {
        return root.left;
    }

    public AVLNode<T> getRight() {
        return root.right;
    }
    
    // compute balance factor of the AVL tree
    public int balanceFactor(AVLNode<T> curr) {
        return height(curr.right) - height(curr.left);
    }

    // computes the height of the AVL tree
    public int height() {
        return height(root);
    }

    // computes the height of the AVL tree rooted at curr
    private int height(AVLNode<T> curr) {
        if (curr == null) {
            return 0;
        } else {
            return curr.height;
        }
    }

    // update the height of the AVL tree rooted at curr
    private void updateHeight(AVLNode<T> curr) {
        curr.height = 1 + Math.max(height(curr.left), height(curr.right));
    }

    // search get an element in the AVL tree
    public T searchGet(T val) {
        return searchGet(root, val);
    }

    private T searchGet(AVLNode<T> curr, T val) {
        if (curr == null) {
            return null;
        } else if (val.compareTo(curr.val) == 0) {
            return curr.val;
        } else if (val.compareTo(curr.val) < 0) {
            return searchGet(curr.left, val);
        } else {
            return searchGet(curr.right, val);
        }
    }

    // search for an element in the AVL tree
    public boolean search(T val) {
        return search(root, val);
    }

    // search for an element in the AVL tree rooted at curr
    private boolean search(AVLNode<T> curr, T val) {
        if (curr == null) {
            return false;
        } else if (val.compareTo(curr.val) == 0) {
            return true;
        } else if (val.compareTo(curr.val) < 0) {
            return search(curr.left, val);
        } else {
            return search(curr.right, val);
        }
    }

    // update an element in the AVL tree
    public void update(T oldVal, T newVal) {
        delete(oldVal);
        insert(newVal);
    }

    //insert into an AVL tree
    public void insert(T val) {
        root = insert(root, val);
    }

    // insert into an AVL tree rooted at curr
    private AVLNode<T> insert(AVLNode<T> curr, T val) {
        if (curr == null) {
            return new AVLNode<T>(val);
        } else if (val.compareTo(curr.val) < 0) {
            curr.left = insert(curr.left, val);
            curr.left.parent = curr;
        } else {
            curr.right = insert(curr.right, val);
            curr.right.parent = curr;
        }

        // rebalance the tree
        return rebalance(curr);
    }

    // remove an element from the AVL tree
    public void delete(T val) {
        root = delete(root, val);
    }

    // remove an element from the AVL tree rooted at curr
    private AVLNode<T> delete(AVLNode<T> curr, T val) {
        if (curr == null) {
            return null;
        } else if (val.compareTo(curr.val) < 0) {
            curr.left = delete(curr.left, val);
        } else if (val.compareTo(curr.val) > 0) {
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
                AVLNode<T> succ = findSuccessor(curr);
                // copy the value
                curr.val = succ.val;
                // remove the successor
                curr.right = delete(curr.right, succ.val);
            }
        }

        // rebalance the tree
        return rebalance(curr);
    }

    private AVLNode<T> findSuccessor(AVLNode<T> curr) {
        AVLNode<T> succ = curr.right;
        // find the inorder successor
        while (succ.left != null) {
            succ = succ.left;
        }
        return succ;
    }

    // rebalance the AVL tree rooted1 at curr
    private AVLNode<T> rebalance(AVLNode<T> curr) {
        if(debugging) {
           System.out.println("before rebalance of node: [" + curr.val+"]");
            print();
        }
        // check if the tree is left heavy
        if (balanceFactor(curr) < -1) {
            // check if left-left case
            if (height(curr.left.left) >= height(curr.left.right)) {
                System.out.println("right rotate");
                curr = rightRotate(curr);
            } else {
                System.out.println("left-right rotate");
                curr = leftRightRotate(curr);
            }
        }
        // check if the tree is right heavy
        else if (balanceFactor(curr) > 1) {
            // check if right-right case
            if (height(curr.right.right) >= height(curr.right.left)) {
                System.out.println("left rotate");
                curr = leftRotate(curr);
            } else {
                System.out.println("right-left rotate");
                curr = rightLeftRotate(curr);
            }
        }
        updateHeight(curr);
        if(debugging) { 
            System.out.println("after rebalance");
            print();
        }

        return curr;
    }

    // left rotate the subtree rooted at curr
    private AVLNode<T> leftRotate(AVLNode<T> p) {
        AVLNode<T> q = p.right;
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

    private void updateParentsLeftRotate(AVLNode<T> p, AVLNode<T> q) {
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
    private AVLNode<T> rightRotate(AVLNode<T> p) {
        AVLNode<T> q = p.left;
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

    private void updateParentsRightRotate(AVLNode<T> p, AVLNode<T> q) {
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
    private AVLNode<T> leftRightRotate(AVLNode<T> curr) {
        curr.left = leftRotate(curr.left);
        return rightRotate(curr);
    }

    // right-left rotate the subtree rooted at curr
    private AVLNode<T> rightLeftRotate(AVLNode<T> curr) {
        curr.right = rightRotate(curr.right);
        return leftRotate(curr);
    }

    public ListoverLinkedList<T> inorder() {
        return inorder(root);
    }

    // get a list of the nodes of the AVL tree in inorder
    private ListoverLinkedList<T> inorder(AVLNode<T> curr) {
        ListoverLinkedList<T> list = new ListoverLinkedList<T>();
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
    private void print(AVLNode<T> curr, String prefix) {
        if (curr != null) {
            print(curr.right, prefix + "    ");
            System.out.println(prefix + curr.val + "(" + curr.height + "," + balanceFactor(curr) + ")");
            print(curr.left, prefix + "    ");
        }
    }

    public boolean isEmpty() {
        return root == null;
    }

    // to string
    @Override
    public String toString() {
        ListoverLinkedList<T> inOrderList = inorder();
        return inOrderList.toString();
    }
}
