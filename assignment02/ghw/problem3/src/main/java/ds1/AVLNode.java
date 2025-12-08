package ds1;

public class AVLNode {
    int value;
    AVLNode left;
    AVLNode right;
    int height;

    // create a new AVL node
    //O(1)
    AVLNode(int value) {
        this.value = value;
        this.height = 1;
    }

    // get the value of this node
    //O(1)
    int getValue() {
        return value;
    }

    // get the left child
    //O(1)
    AVLNode getLeft() {
        return left;
    }

    // get the right child
    //O(1)
    AVLNode getRight() {
        return right;
    }

    // get the height of this node
    //O(1)
    int getHeight() {
        return height;
    }
}
