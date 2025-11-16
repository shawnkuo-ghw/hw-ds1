package mygroup;

public class minHeapRep {

    public static <T extends Comparable<T>>
    boolean minHeapRepOK(Node<T> root){
        if(root == null)
            return true;
        return isHeap(root) && isComplete(root, 1, countNumNode(root));
    }

    //Check if a binary tree satisfies the min-heap property.
    public static <T extends Comparable<T>> boolean isHeap(Node<T> root){
        if(root == null)
            return true;
        boolean root_left = (root.left == null) || (root.value.compareTo(root.left.value) <= 0);
        boolean root_right =(root.right == null) || (root.value.compareTo(root.right.value) <= 0);
        return isHeap(root.left) && isHeap(root.right) && root_left && root_right;
    }

    //Check if a binary tree is complete.
    public static <T extends Comparable<T>> boolean isComplete(Node<T> root, int index, int nodeNum){
        if(root == null){
            return true;
        }
        if(index > nodeNum)
            return false;
        int leftIndex = 2 * index;
        int rightIndex = 2 * index + 1;
        return isComplete(root.left, leftIndex, nodeNum) && isComplete(root.right, rightIndex, nodeNum);
    }

    //Count the number of the node in a heap.
    public static <T extends Comparable<T>> int countNumNode(Node<T> root){
        if(root == null)
            return 0;
        return 1 + countNumNode(root.left) + countNumNode(root.right);
    }
}
