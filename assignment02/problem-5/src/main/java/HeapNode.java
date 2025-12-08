public class HeapNode {
    int data;
    HeapNode left;
    HeapNode right;

    // create a new heap node
    HeapNode(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    // get the value of this node
    //O(1)
    int getData() {
        return data;
    }

    // get the left child
    //O(1)
    HeapNode getLeft() {
        return left;
    }

    // get the right child
    //O(1)
    HeapNode getRight() {
        return right;
    }
}

