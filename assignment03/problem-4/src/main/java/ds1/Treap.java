package ds1;

class TreapNode {
    int key, priority;
    TreapNode left, right;
    TreapNode parent;

    TreapNode(int key, int priority) {
        this.key = key;
        this.priority = priority;
    }
}

public class Treap {
    private static final int MAX_PRIORITY = Integer.MAX_VALUE;
    // root of the Treap
    TreapNode root;

    // create a new Treap
    public Treap() {
        root = null;
    }

    // compute height (empty tree has height 0, one node has height 1)
    public int height() {
        return height(root);
    }

    private int height(TreapNode curr) {
        if (curr == null) {
            return 0;
        }
        return 1 + Math.max(height(curr.left), height(curr.right));
    }

    // insert a new key into the Treap using the given priority
    public void insertWithPriority(int key, int priority) {
        root = insert(root, key, priority);
        if (root != null) {
            root.parent = null;
        }
    }

    // insert a new key into the Treap
    // first insert the node as in BST
    // then rotate the node up to maintain the heap property
    // use restructure() to rotate the node up
    // and getPriority() to get the priority of a node
    private TreapNode insert(TreapNode p, int key, int priority) {
        if (p == null) {
            return new TreapNode(key, priority);
        }
        if (p.key == key) {
            throw new IllegalArgumentException("The key has already existed!");
        } else if (p.key < key) {
            TreapNode rightChild = insert(p.right, key, priority);
            p.right = rightChild;
            if (rightChild != null) {
                rightChild.parent = p;
            }
        } else {
            TreapNode leftChild = insert(p.left, key, priority);
            p.left = leftChild;
            if (leftChild != null) {
                leftChild.parent = p;
            }
        }
        return restructure(p);
    }

    // get the priority of a node
    // if the node is null, return MAX_PRIORITY
    int getPriority(TreapNode p) {
        if (p == null)
            return MAX_PRIORITY;
        else
            return p.priority;
    }

    // Get the node of lowest priority between the node and the two children
    TreapNode lowestPriority(TreapNode p) {
        if (p == null)
            return null;
        TreapNode q = p;
        if (getPriority(q) > getPriority(p.left))
            q = p.left;
        if (getPriority(q) > getPriority(p.right))
            q = p.right;
        return q;
    }

    // rotate the node up to maintain the heap property
    // also maintain the BST property
    // if the node is null, return null
    // if the node is already the node of lowest priority, return the node
    // otherwise, rotate the node up and return the new node
    private TreapNode restructure(TreapNode p) {
        if (p == null)
            return p;
        // get child of lowest priority
        TreapNode q = lowestPriority(p);
        if (q == p.left)
            p = rotateRight(p); // rotate as needed
        else if (q == p.right)
            p = rotateLeft(p);
        return p;
    }

    // left rotate the subtree rooted at p (same idea as AVL rotation)
    private TreapNode rotateLeft(TreapNode p) {
        TreapNode q = p.right;
        p.right = q.left;
        if (p == root)
            root = q;

        // update parents
        updateParentsLeftRotate(p, q);

        q.left = p;
        return q;
    }

    private void updateParentsLeftRotate(TreapNode p, TreapNode q) {
        if (q.left != null) {
            q.left.parent = p;
        }
        q.parent = p.parent;
        if (p.parent != null) {
            if (p == p.parent.left) {
                p.parent.left = q;
            } else {
                p.parent.right = q;
            }
        }
        p.parent = q;
    }

    // right rotate the subtree rooted at p (same idea as AVL rotation)
    private TreapNode rotateRight(TreapNode p) {
        TreapNode q = p.left;
        p.left = q.right;
        if (p == root)
            root = q;

        // update parents
        updateParentsRightRotate(p, q);

        q.right = p;
        return q;
    }

    private void updateParentsRightRotate(TreapNode p, TreapNode q) {
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
}


