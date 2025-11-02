package mygroup;

public class Node<T extends Comparable<T> > {
    T value;
    Node<T> left;
    Node<T> right;
    Node<T> parent;

    public Node(T value){
        this.value = value;
        this.left = null;
        this.right = null;
        this.parent = null;
    }
}
