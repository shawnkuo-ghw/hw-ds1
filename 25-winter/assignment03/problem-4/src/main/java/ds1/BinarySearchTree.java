package ds1;

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
			while(curr != null) {
				prev = curr;
				if(val < curr.val )
					curr = curr.left;
				else
					curr = curr.right;
			}
			newNode.parent = prev;
			if(prev == null)
				root = newNode;
            else if(val < prev.val)
                prev.left = newNode;
            else
                prev.right = newNode;
		}
    }
}


