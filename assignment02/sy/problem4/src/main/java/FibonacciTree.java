public class FibonacciTree {

    FibonacciNode root;

    //create an empty Fibonacci tree
    public FibonacciTree() {
        root = null;
    }

    // create the ith Fibonacci tree
    public FibonacciTree(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Index should be non negative");
        }
        root = buildFibonacciTree(i);
    }

    // build the ith Fibonacci tree recursively
    public FibonacciNode buildFibonacciTree(int i) {
        if (i == 0) {
            return null;
        }
        else if (i == 1) {
            FibonacciNode rootNode = new FibonacciNode();
            rootNode.left = null;
            rootNode.right = null;
            return rootNode;
        }
        else {
            FibonacciNode rootNode = new FibonacciNode();
            rootNode.left = buildFibonacciTree(i - 1);
            rootNode.right = buildFibonacciTree(i - 2);
            return rootNode;
        }
    }

    // get the root of the Fibonacci tree
    public FibonacciNode getRoot() {
        return root;
    }

    // compute the height of the Fibonacci tree
    public int height() {
        return height(root);
    }

    // compute the height of the tree with root curr
    public int height(FibonacciNode curr) {
        if (curr == null)
            return 0;
        else
            return 1 + Math.max(height(curr.left), height(curr.right));
    }

    //compute balance factor of a node
    public int balanceFactor(FibonacciNode curr) {
        if (curr == null) {
            return 0;
        }
        return height(curr.right) - height(curr.left);
    }

    // count the number of nodes
    public int countNodes() {
        return countNodes(root);
    }

    //count the number of nodes in tree with root curr
    public int countNodes(FibonacciNode curr) {
        if (curr == null)
            return 0;
        return 1 + countNodes(curr.left) + countNodes(curr.right);
    }

    // print the balance factor of each node of the Fibonacci tree
    public void printBalanceFactors() {
        System.out.println("Balance factors of Fibonacci Tree is:");
        printBalanceFactors(root, "root");
    }

    // get balance factors as anarray
    public int[] getBalanceFactorsArray() {
        int count = countNodes();
        if (count == 0)
            return new int[0];
        int[] balanceFactors = new int[count];
        int[] index = {0};
        collectBalanceFactors(root, balanceFactors, index);
        return balanceFactors;
    }

    // collect balance factors in preorder
    public void collectBalanceFactors(FibonacciNode curr, int[] balanceFactors, int[] index) {
        if (curr != null) {
            balanceFactors[index[0]++] = balanceFactor(curr);
            collectBalanceFactors(curr.left, balanceFactors, index);
            collectBalanceFactors(curr.right, balanceFactors, index);
        }
    }


    // print the balance factor of each node in the tree with toot curr
    public void printBalanceFactors(FibonacciNode curr, String position) {
        if (curr != null) {
            int balanceFactor = balanceFactor(curr);
            System.out.println("node at " + position + ": balance factor is " + balanceFactor);
            printBalanceFactors(curr.left, position + " .left");
            printBalanceFactors(curr.right, position + " .right");
        }
    }
    
    public boolean isEmpty() {
        return root == null;
    }
}

