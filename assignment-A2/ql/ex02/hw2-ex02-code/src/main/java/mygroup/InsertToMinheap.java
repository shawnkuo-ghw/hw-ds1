package mygroup;

import java.util.LinkedList;
import java.util.Queue;

public class InsertToMinheap {
    public static <T extends Comparable<T>> Node<T> insertToMinheap(Node<T> root, T value){
        Node<T> newNode = new Node<>(value);
        Node<T> parent = parentOfNew(root);
        newNode.parent = parent;

        if(parent == null){
            root = newNode;
        } else if (parent.left == null) {
            parent.left = newNode;
        } else if (parent.right == null) {
            parent.right = newNode;
        }

        MinHeapify(newNode);
        return root;
    }

    public static <T extends Comparable<T>> Node<T> parentOfNew(Node<T> root){
        if(root == null){
            return null;
        }
        Queue<Node<T>> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            Node<T> curr = q.remove();
            if(curr.left == null || curr.right == null)
                return curr;
            q.add(curr.left);
            q.add((curr.right));
        }
        return null;
    }

    public static <T extends Comparable<T>> void MinHeapify(Node<T> node){
        while(node.parent != null && node.value.compareTo(node.parent.value) <= 0){
            T val = node.value;
            node.value = node.parent.value;
            node.parent.value = val;
            node = node.parent;
        }
    }
}
