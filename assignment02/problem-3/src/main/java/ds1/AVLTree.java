package ds1;

public class AVLTree {

    AVLNode root;
    int count;

    public AVLTree() {
        root = null;
        count = 0;
    }

    //O(1)
    public boolean isEmpty() {
        return root == null;
    }

    //O(1)
    public int size() {
        return count;
    }

    //O(1)
    public AVLNode getRoot() {
        return root;
    }

    //O(1)
    public int height() {
        return height(root);
    }

    //O(log n) because it walks down the tree height
    public boolean search(int value) {
        AVLNode curr = root;
        while (curr != null) {
            if (value == curr.value) {
                return true;
            } else if (value < curr.value) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }
        return false;
    }

    //O(log n) because it follows one path and rebalances
    public void insert(int value) {
        ArrayOverStack<AVLNode> path = new ArrayOverStack<AVLNode>(Math.max(4, count + 1));
        if (root == null) {
            root = new AVLNode(value);
            count = 1;
            return;
        }

        AVLNode curr = root;
        AVLNode parent = null;
        // go down from root to leaf path, thus O(log n)
        while (curr != null) {
            parent = curr;
            path.push(parent);
            if (value < curr.value) {
                curr = curr.left;
            } else if (value > curr.value) {
                curr = curr.right;
            } else {
                throw new IllegalArgumentException("duplicate value");
            }
        }

        AVLNode newNode = new AVLNode(value);
        if (value < parent.value) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
        count++;

        // go back up same path to rebalance, thus O(log n)
        while (!path.isEmpty()) {
            AVLNode node = path.pop();
            boolean wasLeftChild = !path.isEmpty() && path.readTop().left == node;
            node = rebalance(node);
            if (path.isEmpty()) {
                root = node;
            } else {
                AVLNode ancestor = path.readTop();
                if (wasLeftChild) {
                    ancestor.left = node;
                } else {
                    ancestor.right = node;
                }
            }
        }
    }

    //O(log n) since it search path then rebalance
    public void delete(int value) {
        ArrayOverStack<AVLNode> path = new ArrayOverStack<AVLNode>(Math.max(4, count));
        AVLNode curr = root;

        // search one path, thus O(log n) on balanced height
        while (curr != null && curr.value != value) {
            path.push(curr);
            if (value < curr.value) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }

        if (curr == null) {
            throw new IllegalArgumentException("value not found");
        }

        if (curr.left != null && curr.right != null) {
            // find successor along right subtree path, thus O(log n)
            path.push(curr);
            AVLNode succ = curr.right;
            while (succ.left != null) {
                path.push(succ);
                succ = succ.left;
            }
            curr.value = succ.value;
            curr = succ;
        }

        AVLNode child = (curr.left != null) ? curr.left : curr.right;
        if (path.isEmpty()) {
            root = child;
        } else {
            AVLNode parent = path.readTop();
            if (parent.left == curr) {
                parent.left = child;
            } else {
                parent.right = child;
            }
        }
        if (count > 0) {
            count--;
        }

        // rebalance while pop the path, thus O(log n)
        while (!path.isEmpty()) {
            AVLNode node = path.pop();
            boolean wasLeftChild = !path.isEmpty() && path.readTop().left == node;
            node = rebalance(node);
            if (path.isEmpty()) {
                root = node;
            } else {
                AVLNode ancestor = path.readTop();
                if (wasLeftChild) {
                    ancestor.left = node;
                } else {
                    ancestor.right = node;
                }
            }
        }
    }

    //O(n) because it visits every node once
    public int[] toArray() {
        int[] arr = new int[count];
        if (root == null) {
            return arr;
        }
        ArrayOverStack<AVLNode> stack = new ArrayOverStack<AVLNode>(Math.max(4, count));
        AVLNode curr = root;
        int index = 0;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            arr[index] = curr.value;
            index++;
            curr = curr.right;
        }
        return arr;
    }

    //O(n) because verity is O(n)
    public boolean repOK() {
        // root must be null iff count == 0
        if ((root == null && count != 0) || (root != null && count == 0)) {
            return false;
        }
        CheckResult res = verify(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
        if (!res.valid) {
            return false;
        }
        return res.count == count;
    }

    //O(n) because it recurses into every node to validate
    private CheckResult verify(AVLNode node, int min, int max) {
        if (node == null) {
            return new CheckResult(true, 0, 0);
        }
        if (node.value <= min || node.value >= max) {
            return new CheckResult(false, 0, 0);
        }

        CheckResult leftRes = verify(node.left, min, node.value);
        if (!leftRes.valid) {
            return leftRes;
        }
        CheckResult rightRes = verify(node.right, node.value, max);
        if (!rightRes.valid) {
            return rightRes;
        }

        int expectedHeight = 1 + Math.max(leftRes.height, rightRes.height);
        int balance = rightRes.height - leftRes.height;
        if (node.height != expectedHeight) {
            return new CheckResult(false, 0, 0);
        }
        if (balance < -1 || balance > 1) {
            return new CheckResult(false, 0, 0);
        }
        int total = 1 + leftRes.count + rightRes.count;
        return new CheckResult(true, expectedHeight, total);
    }

    //O(1)
    private int height(AVLNode node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    //O(1)
    private int balanceFactor(AVLNode node) {
        return height(node.right) - height(node.left);
    }

    //O(1)
    private void updateHeight(AVLNode node) {
        node.height = 1 + Math.max(height(node.left), height(node.right));
    }

    //O(1) for a single node (rotations and height updates)
    private AVLNode rebalance(AVLNode node) {
        updateHeight(node);
        int bf = balanceFactor(node);
        if (bf < -1) {
            if (balanceFactor(node.left) > 0) {
                node.left = leftRotate(node.left);
            }
            node = rightRotate(node);
        } else if (bf > 1) {
            if (balanceFactor(node.right) < 0) {
                node.right = rightRotate(node.right);
            }
            node = leftRotate(node);
        }
        updateHeight(node);
        return node;
    }

    //O(1)
    private AVLNode leftRotate(AVLNode p) {
        AVLNode q = p.right;
        p.right = q.left;
        q.left = p;
        updateHeight(p);
        updateHeight(q);
        return q;
    }

    //O(1)
    private AVLNode rightRotate(AVLNode p) {
        AVLNode q = p.left;
        p.left = q.right;
        q.right = p;
        updateHeight(p);
        updateHeight(q);
        return q;
    }

    private static class CheckResult {
        boolean valid;
        int height;
        int count;

        CheckResult(boolean valid, int height, int count) {
            this.valid = valid;
            this.height = height;
            this.count = count;
        }
    }
}
