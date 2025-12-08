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
    //O(n)
    private FibonacciNode buildFibonacciTree(int i) {
        //O(1)
        if (i == 0) {
            return null;
        }
        //O(1)
        else if (i == 1) {
            FibonacciNode rootNode = new FibonacciNode();
            rootNode.left = null;
            rootNode.right = null;
            return rootNode;
        }
        //O(n) since we recurse every node
        else {
            FibonacciNode rootNode = new FibonacciNode();
            rootNode.left = buildFibonacciTree(i - 1);
            rootNode.right = buildFibonacciTree(i - 2);
            return rootNode;
        }
    }

    // get the root of the Fibonacci tree
    //O(1)
    public FibonacciNode getRoot() {
        return root;
    }

    // compute the height of the Fibonacci tree
    //O(n)
    public int height() {
        return height(root);
    }

    // compute the height of the tree with root curr
    //O(n) in worst case since it recurse every node
    //and the work for each level call is just O(1)
    private int height(FibonacciNode curr) {
        if (curr == null)
            return 0;
        else
            return 1 + Math.max(height(curr.left), height(curr.right));
    }

    //compute balance factor of a node
    //O(n) in the worst case since height is O(n) and we need recurse every node
    //and the work for each level call is just O(1)
    public int balanceFactor(FibonacciNode curr) {
        if (curr == null) {
            return 0;
        }
        return height(curr.right) - height(curr.left);
    }

    // count the number of nodes
    //O(n)
    public int countNodes() {
        return countNodes(root);
    }

    //count the number of nodes in tree with root curr recursively
    //O(n) in the worst case
    private int countNodes(FibonacciNode curr) {
        //O(1)
        if (curr == null)
            return 0;
        //O(1) * n = O(n) in the worst case since we need recurse all node
        //and the work for each level call is just O(1)
        return 1 + countNodes(curr.left) + countNodes(curr.right);
    }

    // get balance factors as anarray
    // O(n^2)
    public int[] getBalanceFactorsArray() {
        //O(n)
        int count = countNodes();
        if (count == 0)
            return new int[0];
        int[] balanceFactors = new int[count];
        int index = 0;
        // O(n^2)
        collectBalanceFactors(root, balanceFactors, index);
        return balanceFactors;
    }

    // collect balance factors in preorder
    //O(n^2)
    private int collectBalanceFactors(FibonacciNode curr, int[] balanceFactors, int index) {
        if (curr != null) {
            //O(n)
            balanceFactors[index++] = balanceFactor(curr);
            //For each level call the work is O(n), and 
            // we need to call it for every nodes (n) times
            // That is O(n) * n = O(n^2)
            index = collectBalanceFactors(curr.left, balanceFactors, index);
            index = collectBalanceFactors(curr.right, balanceFactors, index);
        }
        return index;
    }

    //O(1)
    public boolean isEmpty() {
        return root == null;
    }

    // print the balance factor of each node of the Fibonacci tree
    //O(n^2)
    public void printBalanceFactors() {
        System.out.println("Balance factors of Fibonacci Tree is:");
        printBalanceFactors(root, "root");
    }

    // print the balance factor of each node in the tree with toot curr
    //O(n^2) in the worst case
    public void printBalanceFactors(FibonacciNode curr, String position) {
        if (curr != null) {
            //O(n)
            int balanceFactor = balanceFactor(curr);
            System.out.println("node at " + position + ": balance factor is " + balanceFactor);
            //For each level call the work is O(n), and 
            // we need to call it for every nodes (n) times
            // That is O(n) * n = O(n^2)
            printBalanceFactors(curr.left, position + " .left");
            printBalanceFactors(curr.right, position + " .right");
        }
    }
}

