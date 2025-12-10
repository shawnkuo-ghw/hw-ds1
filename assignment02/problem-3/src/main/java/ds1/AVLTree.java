package ds1;

public class AVLTree {

    AVLNode root;
    private static final int MIN_STACK_CAPACITY = 5;

    public AVLTree() {
        root = null;
    }

    //O(1)
    public boolean isEmpty() {
        return root == null;
    }

    //O(1)
    public int size() {
        return size(root);
    }

    //O(n)
    private int size(AVLNode node) {
        if(node == null)
            return 0;
        return 1 + size(node.left) + size(node.right);
    }

    //O(1)
    public AVLNode getRoot() {
        return root;
    }

    //O(1)
    public int height() {
        return height(root);
    }

    //O(1)
    private int height(AVLNode node) {
        if(node == null)
            return 0;
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

    //O(1) since updateHeight and rotate are all O(1)
    private AVLNode rebalance(AVLNode curr) {
        if (balanceFactor(curr) < -1) {
            if(height(curr.left) >= height(curr.right)) {
                curr = rightRotate(curr);
            }
            else {
                curr = leftRightRotate(curr);
            }
        }
        else if (balanceFactor(curr) > 1) {
            if (height(curr.right) >= height(curr.left)) {
                curr = leftRotate(curr);
            }
            else {
                curr = rightLeftRotate(curr);
            }
        }
        updateHeight(curr);
        return curr;
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
    //O(1)
    private AVLNode leftRightRotate(AVLNode node) {
        node.left = leftRotate(node.left);
        return rightRotate(node);
    }
    //O(1)
    private AVLNode rightLeftRotate(AVLNode node) {
        node.right = rightRotate(node.right);
        return leftRotate(node);
    }

    //O(log n) since we need to go left again and agin(the height of tree)
    private AVLNode findSuccessor(AVLNode curr) {
        AVLNode succ = curr.right;
        // find the inorder successor
        while (succ.left != null) {
            succ = succ.left;
        }
        return succ;
    }

    //O(log n) because it go down the tree height since each time it go left of right
    public AVLNode search(int value) {
        AVLNode curr = root;
        while(curr != null) {
            if(value == curr.value)
                return curr;
            else if(value < curr.value)
                curr = curr.left;
            else
                curr = curr.right;
        }
        return null;
    }

    //O(log n)
    public AVLNode insert(int value){
        //O(1)
        ArrayOverStack<AVLNode> stack = new ArrayOverStack<AVLNode>(Math.max(MIN_STACK_CAPACITY, height()));
        if(root == null){
            root = new AVLNode(value);
            return root;
        }
        //O(1)
        AVLNode curr = root;
        AVLNode parent = null;
        // go down from root to leaf, O(log n)
        while(curr != null) {
            parent = curr;
            stack.push(parent);
            if(value < curr.value) {
                curr = curr.left;
            }
            else if(value > curr.value) {
                curr = curr.right;
            }
            else
                throw new IllegalArgumentException("the value exists!");
        }
        //O(1)
        AVLNode newNode = new AVLNode(value);
        if(value < parent.value)
            parent.left = newNode;
        else
            parent.right = newNode;

        // go back up using stack, since in the stack is the nodes we visited and we go through the tree 
        // of log n level, thus O(log n)
        while (!stack.isEmpty()) {
            AVLNode node = stack.pop();
            node = rebalance(node);
            if (stack.isEmpty()){
                root = node;
            }
            else{
                AVLNode ancestor = stack.readTop();
                if(!stack.isEmpty() && stack.readTop().left == node)
                    ancestor.left = node;
                else
                    ancestor.right = node;
            }
        }
        return root;
    }

    //O(log n)
    public AVLNode delete(int val) {
        ArrayOverStack<AVLNode> stack = new ArrayOverStack<>(Math.max(MIN_STACK_CAPACITY, height()));
        AVLNode curr = root;

        // Find the node to delete and stored the visited node O(log n)
        while (curr != null && curr.value != val) {
            stack.push(curr);
            if (val < curr.value) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }

        // Not found O(1)
        if (curr == null) {
            throw new IllegalArgumentException("value not found");
        }

        // delete
        // Case 1: node has two children O(log n)
        if (curr.left != null && curr.right != null) {
            stack.push(curr);
            //find successor O(log n)
            AVLNode successor = findSuccessor(curr);
            // Go left and store the node O(log n)
            AVLNode prev = curr.right;
            while (prev != successor) {
                stack.push(prev);
                prev = prev.left;
            }
            curr.value = successor.value;
            curr = successor;
        }

        // Case 2: Node has 0 or 1 child
        //O(1)
        AVLNode child = null;
        if (curr.left != null) {
            child = curr.left;
        }
        else {
            child = curr.right;
        }

        if (stack.isEmpty()) {
            root = child;
        }
        else {
            AVLNode parent = stack.readTop();
            if (parent.left == curr) {
                parent.left = child;
            }
            else {
                parent.right = child;
            }
        }
        // pop the node to rebalance O(log n)
        while(!stack.isEmpty()) {
            AVLNode node = stack.pop();
            updateHeight(node);
            AVLNode subTree = rebalance(node);

            if (!stack.isEmpty()) {
                AVLNode ancestor = stack.readTop();
                if (ancestor.left == node) {
                    ancestor.left = subTree;
                }
                else {
                    ancestor.right = subTree;
                }
            }
            else {
                root = subTree;
            }
        }
        return root;
    }

    //O(n) because it visits every node once
    public int[] toArray() {
        int n = size();
        int[] arr = new int[n];
        if (root == null) {
            return arr;
        }
        ArrayOverStack<AVLNode> stack = new ArrayOverStack<AVLNode>(Math.max(MIN_STACK_CAPACITY, height()));
        AVLNode curr = root;
        int index = 0;
        while(curr != null || !stack.isEmpty()) {
            while(curr != null) {
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

    //O(n^2)
    public boolean repOK() {
        if (root == null)
            return true;
        //O(n^2)
        if (!checkAcyclicInvariant())
            return false;
        //O(n)
        if (!checkBSTInvariant())
            return false;
        //O(n)
        if (!checkBalanceInvariant())
            return false;
        //O(n)
        if (!checkHeightInvariant())
            return false;
        return true;
    }

    // invariant 1: tree is acyclic w.r.t left/right, O(n^2)
    private boolean checkAcyclicInvariant() {
        ArrayOverStack<AVLNode> stack = new ArrayOverStack<>(MIN_STACK_CAPACITY);
        return !hasCycle(root, stack);
    }

    //O(n^2)
    private boolean hasCycle(AVLNode node, ArrayOverStack<AVLNode> stack) {
        if (node == null) {
            return false;
        }
        //O(n)
        if (stack.contains(node)) {
            return true;
        }
        stack.push(node);
        //Each level, we do O(n) work `contains`
        //And we need to call hasCycle n times(every node)
        //Thus O(n^2)
        boolean leftCycle = hasCycle(node.left, stack);
        if(leftCycle) {
            stack.pop();
            return true;
        }
        boolean rightCycle = hasCycle(node.right, stack);
        stack.pop();
        return rightCycle;
    }

    // invariant 2: BST, O(n)
    private boolean checkBSTInvariant() {
        return validBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    //O(n) because it visit each node at most once and each level work is O(1)
    private boolean validBST(AVLNode node, long min, long max) {
        if (node == null) {
            return true;
        }
        if(node.value <= min || node.value >= max) {
            return false;
        }
        return validBST(node.left, min, node.value) && validBST(node.right, node.value, max);
    }

    // invariant 3: balance factor is -1, 0, or 1, O(n)
    private boolean checkBalanceInvariant() {
        return validBalance(root) != -1;
    }

    //O(n) since we recurse every node to comute height
    //And each level work is O(1)
    private int validBalance(AVLNode node) {
        if (node == null)
            return 0;
        int leftHeight = validBalance(node.left);
        if (leftHeight == -1)
            return -1;
        int rightHeight = validBalance(node.right);
        if (rightHeight == -1)
            return -1;
        int balance = rightHeight - leftHeight;
        if (balance < -1 || balance > 1)
            return -1;
        return 1 + Math.max(leftHeight, rightHeight);
    }

    // invariant 4: height invariant, O(n)
    private boolean checkHeightInvariant() {
        return validHeights(root) != -1;
    }

    //O(n) since we verify each node's heght
    private int validHeights(AVLNode node) {
        if (node == null) 
            return 0;
        int leftHeight = validHeights(node.left);
        if (leftHeight == -1)
            return -1;
        int rightHeight = validHeights(node.right);
        if (rightHeight == -1)
            return -1;
        int actual = 1 + Math.max(leftHeight, rightHeight);
        if (node.height != actual)
            return -1;
        return actual;
    }
}
