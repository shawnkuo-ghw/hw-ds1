package ds1;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

class TreapNode {
    TreapNode left, right;
    TreapNode parent;
    int priority;
    int key;

    TreapNode(int key, int priority) {
        this.key = key;
        this.priority = priority;
    }
}

public class MonteCarloForTreap{
    static Random r = new Random();  // random number generator
    private static final int MAX_PRIORITY = Integer.MAX_VALUE;  // maximum priority

    static TreapNode root;

    public MonteCarloForTreap() {
        root = null;
    }

    public static TreapNode insertTreap(TreapNode root, int key) {
        return insert(root, key, r.nextInt(100));
    }

    // insert a new key into the Treap
    // first insert the node as in BST
    // then rotate the node up to maintain the heap property
    // use resytructure() to rotate the node up
    // and getPriority() to get the priority of a node
    private static TreapNode insert(TreapNode p, int key, int priority) {
        if (p == null) {
            return new TreapNode(key, priority);
        }
        
        if (key < p.key) {
            p.left = insert(p.left, key, priority);
            p.left.parent = p;
        } else if (key > p.key) {
            p.right = insert(p.right, key, priority);
            p.right.parent = p;
        } else {
            throw new IllegalArgumentException("Duplicate key");
        }
        return restructure(p);
    }

    public static TreapNode builTreapNode(int n){
        TreapNode root = null;
        Set<Integer> usedKeys = new HashSet<>();  // Ensure uniqueness

        while (usedKeys.size() < n) {
            int key = r.nextInt(2 * n);
            if (usedKeys.add(key)) {
                root = insertTreap(root, key); 
            }
        }
        return root;
    }

    // get the priority of a node
    // if the node is null, return MAX_PRIORITY
    static int getPriority(TreapNode p) {
        if (p == null)
            return MAX_PRIORITY;
        else
            return p.priority;
    }

    // Get the node of lowest priority between the node and the two children
    static TreapNode lowestPriority(TreapNode p) {
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
    private static TreapNode restructure(TreapNode p) {
        if (p == null)
            return p;
        TreapNode q = lowestPriority(p); 
        if (q == p.left)
            p = rotateRight(p);
        else if (q == p.right)
            p = rotateLeft(p);
        return p;
    }

    // left rotate the subtree rooted at p
    private static TreapNode rotateLeft(TreapNode p) {
        TreapNode q = p.right;
        p.right = q.left;
        if (p == root) {
            root = q;
        }
        
        updateParentsLeftRotate(p, q);
        q.left = p;
        return q;
    }

    private static void updateParentsLeftRotate(TreapNode p, TreapNode q) {
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
            ;
        }
        p.parent = q;
    }

    // right rotate the subtree rooted at p
    private static TreapNode rotateRight(TreapNode p) {
        TreapNode q = p.left;
        p.left = q.right;
        if (p == root) {
            root = q;
        }
        
        updateParentsRightRotate(p, q);
        q.right = p;
        return q;
    }

    private static void updateParentsRightRotate(TreapNode p, TreapNode q) {
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

    public static int height(TreapNode root){
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(height(root.left), height(root.right));
    }

    // pretty print the Treap
    public static void print(TreapNode root) {
        System.out.println("Treap Tree");
        print(root, "");
        System.out.println("--");

    }

    // pretty print the AVL tree rooted at curr
    private static void print(TreapNode curr, String prefix) {
        if (curr != null) {
            print(curr.right, prefix + "    ");
            System.out.println(prefix + curr.key + "(" + curr.priority + ")");
            print(curr.left, prefix + "    ");
        }
    }

    public static void main(String[] args) {
        int[] sizes = {10, 15, 30, 100, 200, 500, 1000, 2000, 10000};
        int trial = 100;

        System.out.printf("%-10s %-20s %-20s%n", "size", "PracAvgHeight", "log_2(n)");

        for (int size : sizes) {
            int totalHeight = 0;
            for (int i = 0; i < trial; i++) {
                TreapNode Treap = builTreapNode(size);
                totalHeight += height(Treap);
            }

            double PracAvgHeight = (double)totalHeight / (double)trial;
            double ExpectHeight = Math.log(size) / Math.log(2);  // log_2(n) = ln(n)/ln(2)
            System.out.printf("%-10d %-20.4f %-20.4f%n", size, PracAvgHeight, ExpectHeight);
        }
    }
}