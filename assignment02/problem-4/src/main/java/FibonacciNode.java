public class FibonacciNode {
    FibonacciNode left;
    FibonacciNode right;

    // create a new Fibonacci Node
    FibonacciNode() {
        left = null;
        right = null;
    }

    // get the left child of this node
    //O(1)
    FibonacciNode getLeft() {
        return left;
    }

    //get the right child of this node
    //O(1)
    FibonacciNode getRight() {
        return right;
    }
}

