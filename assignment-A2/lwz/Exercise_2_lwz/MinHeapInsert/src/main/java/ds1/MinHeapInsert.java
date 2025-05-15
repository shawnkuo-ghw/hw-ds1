package ds1;

public class MinHeapInsert<T extends Comparable<T>> {
    
    public static class Node<T extends Comparable<T>> {
        T value;
        Node<T> parent;
        Node<T> left;
        Node<T> right;

        public Node(T value) {
            this.value = value;
        }
    }

    public Node<T> insertion(Node<T> node, T value) {
        Node<T> newNode = new Node<T>(value);
        if (node == null) {
            return newNode;
        }

        Node<T> parent = parentOfNew(node);
        if (parent.left == null) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
        newNode.parent = parent;
        MinHeapify(newNode);
        return node;
    }

    private Node<T> parentOfNew(Node<T> node) {
        if (node == null) {
            return null;
        }
        if (node.left == null || node.right == null) {
            return node;
        }
        Node<T> leftSub = parentOfNew(node.left);
        if (leftSub != null) {
            return leftSub;
        }
        return parentOfNew(node.right);
    }

    private void MinHeapify(Node<T> node) {
        while (node.parent != null) {
            if (node.value.compareTo(node.parent.value) >= 0) {
                break;
            } else {
                T temp = node.value;
                node.value = node.parent.value;
                node.parent.value = temp;
                node = node.parent;
            }
        }
    }

    public boolean minHeapRepOK(Node<T> root) {
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