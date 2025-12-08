package ds1.util;

/**
 * Class of generic node for AVL tree.
 */
public class AVLNode<K extends Comparable<K>, V>
{
    K key;                     // the key of the node
    V val;                     // the data value stored in the node
    int height;                // the height of the node
    AVLNode<K, V> left, right; // left and right subtree
    AVLNode<K, V> parent;      // parent node
    AVLNode(K key, V value) {
        this.key    = key;
        this.val    = value;
        this.height = 0;
        this.left   = null;
        this.right  = null;
        this.parent = null;
    }
}