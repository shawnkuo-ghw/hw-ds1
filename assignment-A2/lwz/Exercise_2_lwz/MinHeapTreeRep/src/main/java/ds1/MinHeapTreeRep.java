package ds1;

public class MinHeapTreeRep {
    
    public static class Node<T extends Comparable<T>> {
        T value;
        Node<T> parent;
        Node<T> left;
        Node<T> right;

        public Node(T value) {
            this.value = value;
        }
    }

    public static <T extends Comparable<T>> boolean minHeapRepOK(Node<T> root) {
        if (root == null) {
            return true;
        }

        if (root.left != null) {
            if (root.value.compareTo(root.left.value) > 0) {
                return false;
            } else if (root.left.parent != root) {
                return false;
            } else if (!minHeapRepOK(root.left)) {
                return false;
            }
        }

        if (root.right != null) {
            if (root.value.compareTo(root.right.value) > 0) {
                return false;
            } else if (root.right.parent != root) {
                return false;
            } else if (!minHeapRepOK(root.right)) {
                return false;
            }
        }

        return true;
    }
}