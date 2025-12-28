package ds1.util;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode parent;

    TreeNode(int x) {
        val = x;
    }
}

public class BinarySearchTree {
    TreeNode root;

    // constructor
    public BinarySearchTree() {
        root = null;
    }

    // computes the height of the binary search tree
    public int height() {
        return height(root);
    }

    // computes the height of the binary search tree rooted at curr
    private int height(TreeNode curr) {
        if (curr == null) {
            return 0;
        } else {
            return 1 + Math.max(height(curr.left), height(curr.right));
        }
    }

    // search for an element in the binary search tree
    public TreeNode search(int val) {
        TreeNode curr = root;
        // search for the node
        while (curr != null && curr.val != val) {
            if (val < curr.val) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }
        return curr;
    }

    // insert an element in the binary search tree
    public void insert(int val) {
        TreeNode newNode = new TreeNode(val);
        // if the tree is empty, set the new node as root
        if (root == null) {
            root = newNode;
        } 
        else {
            TreeNode curr = root;
            TreeNode prev = null;
            // search for the position to insert the new node
            while (curr != null) {
                prev = curr;
                if (val < curr.val) {
                    curr = curr.left;
                } else {
                    curr = curr.right;
                }
            }
            if (val < prev.val) {
                prev.left = newNode;
            } else {
                prev.right = newNode;
            }
            newNode.parent = prev;
        }
    }

    // remove an element from the binary search tree
    public void remove(int val) {
        // we call z the node to be deleted
        TreeNode z = root;
        // search for the node to be deleted
        while (z != null && z.val != val) {
            if (val < z.val) {
                z = z.left;
            } else {
                z = z.right;
            }
        }
        // if the node is not found, return
        if (z == null) {
            return;
        }
        // The cases we see in class
        if(z.left==null)
            transplant(z, z.right);
        else if(z.right==null)
            transplant(z, z.left);
        else {
            TreeNode succ = findSuccessor(z);
            if(succ.parent!=z) {
                transplant(succ, succ.right);
                succ.right = z.right;
                succ.right.parent = succ;
            }
            transplant(z, succ);
            succ.left = z.left;
            succ.left.parent = succ;
        }

    }

    // implement transplant method from Cormen's book
    private void transplant(TreeNode u, TreeNode v) {
        if (u.parent == null) {
            this.root = v;
        } else if (u == u.parent.left) {
            u.parent.left = v;
        } else {
            u.parent.right = v;
        }
    }

    private TreeNode findSuccessor(TreeNode curr) {
        TreeNode succ = curr.right;
        // find the inorder successor
        while (succ.left != null) {
            succ = succ.left;
        }
        return succ;
    }
    public ListoverLinkedList<Integer> inorder() {
        return inorder(root);
    }

    // get a list of the nodes of the binary search tree in inorder
    private ListoverLinkedList<Integer> inorder(TreeNode curr) {
        ListoverLinkedList<Integer> list = new ListoverLinkedList<Integer>();
        if (curr != null) {
            list.append(inorder(curr.left));
            list.insertRear(curr.val);
            list.append(inorder(curr.right));
        }
        return list;
    }

    public void print() {
        System.out.println("Binary Tree");
        print(root, "");
    }

    // pretty print the Binary tree rooted at curr
    private void print(TreeNode curr, String prefix) {
        if (curr != null) {
            print(curr.right, prefix + "    ");
            System.out.println(prefix + curr.val);
            print(curr.left, prefix + "    ");
        }
    }
}
