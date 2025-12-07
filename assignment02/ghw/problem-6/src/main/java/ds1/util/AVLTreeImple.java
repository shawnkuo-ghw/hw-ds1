package ds1.util;
import java.util.NoSuchElementException;

public class AVLTreeImple<K extends Comparable <K>, V> implements AVLTree<K, V>
{
    /* ====================== Fields and Constructor ======================== */
    
    private final Class<AVLNode<K, V>> NType; // type of node
    private final Class<V> VType;       // type of value
    private AVLNode<K, V> root;         // the root of AVL tree
    private int nodesNum;               // number of nodes
    public AVLTreeImple(Class<? extends AVLNode> newNType, Class<V> newVType) {
        this.NType = (Class<AVLNode<K, V>>) newNType;
        this.VType = newVType;
        this.root = null;
        nodesNum = 0;
    }

    /* ============================ Modifiers =============================== */

    @Override
    public void insertTree(K key, V value) {
        AVLNode<K, V> node = searchNode(root, key);
        if ( node != null ) {
            throw new IllegalArgumentException(
                "AVLTree.insertTree(): key already exists in AVL tree."
            );
        }
        this.root = insertNode(this.root, null, key, value);
        nodesNum++;
        print();
        if ( !repOK() ) {
            throw new IllegalStateException(
                "AVLTree.insertTree(): representation invariant of AVL tree is not satisfied."
            );
        }
    }

    @Override
    public V searchTree(K key) {
        AVLNode<K, V> keyNode = searchNode(this.root, key);
        if (keyNode != null) return keyNode.val;
        else                 return null;
    }

    @Override
    public void updateTree(K key, V newValue) {
        AVLNode<K, V> keyNode = searchNode(this.root, key);
        if ( keyNode == null ) throw new NoSuchElementException(
            "AVLTree.updateTree(): key does not exist in AVL tree."
        );
        else keyNode.val = newValue;
    }

    @Override
    public V[] toArray() {
        // construct a new array of type V and of size nodesNum
        Sequence<AVLNode<K, V>> nodes = nodesSequence();
        Sequence<V> valsArray = new ListoverLinkedList<V>(VType);
        for ( int i = 0; i < nodesNum; i++ ) valsArray.insertRear(nodes.at(i).val);
        return valsArray.toArray();
    }

    /* ============================ Utilities =============================== */

    private Sequence<AVLNode<K, V>> nodesSequence() {
        Sequence<AVLNode<K, V>> seq = new ListoverLinkedList<AVLNode<K, V>>(NType);
        inorder(this.root, seq);
        System.out.println("here");
        return seq;
    }

    private void inorder(AVLNode<K, V> curr, Sequence<AVLNode<K, V>> seq) {
        if ( curr == null ) return;
        inorder(curr.left, seq);
        seq.insertRear(curr);
        inorder(curr.right, seq);
    }

    private int height(AVLNode<K, V> node) {
        return node == null ? -1 : node.height;
    }

    private int balanceFactor(AVLNode<K, V> node) {
        return height(node.right) - height(node.left);
    }

    private void updateHeight(AVLNode<K, V> node) {
        node.height = 1 + Math.max(height(node.left), height(node.right));
    }

    // Iteratively search for the node corresponding 
    // to the given key in the AVL tree rooted at `node`
    private AVLNode<K, V> searchNode(AVLNode<K, V> node, K key) {
        AVLNode<K, V> curr = node;
        while ( curr != null && !key.equals(curr.key) ) {
            if ( key.compareTo(curr.key) < 0 ) curr = curr.left;  // key < curr.key
            else                               curr = curr.right; // key > curr.key
        }
        return curr;
    }

    // insert the key-value pair into an AVL tree rooted at `node`
    private AVLNode<K, V> insertNode(AVLNode<K, V> node, AVLNode<K, V> parent, K newKey, V newValue) {
        // Step 1. do BST insert
        if ( node == null ) { node = new AVLNode<K, V>(newKey, newValue); node.parent = parent; }
        else if ( newKey.compareTo(node.key) < 0 ) { node.left  = insertNode(node.left, node, newKey, newValue); }
        else                                       { node.right = insertNode(node.right, node, newKey, newValue); }
        // Step 2. rebalance the AVL tree rooted at `node`
        return rebalance(node);
    }

    private AVLNode<K, V> rebalance(AVLNode<K, V> node) {
        if ( node == null )                                           return node;                     // nothing to do
        if ( balanceFactor(node) > 1 ) {                                                               // right-heavy
            if ( height(node.right.left) > height(node.right.right) ) node = rotateRightLeft(node);    // right-left-heavy
            else                                                      node = rotateLeft(node);         // right-right-heavy
        } else if ( balanceFactor(node) < -1 ) {                                                       // left-heavy
            if ( height(node.left.right) > height(node.left.left) )   node = rotateLeftRight(node);    // left-right-heavy
            else                                                      node = rotateRight(node);        // left-left-heavy
        }
        updateHeight(node);
        return node;
    }

    private AVLNode<K, V> rotateRight(AVLNode<K, V> node) {
        AVLNode<K, V> nodeParent = node.parent;
        AVLNode<K, V> nodeLeft = node.left;
        // modify nodeParent
        if ( nodeParent != null ) {
            if ( nodeParent.right == node ) nodeParent.right = nodeLeft;
            else nodeParent.left  = nodeLeft;
        }
        // modify node
        node.parent = nodeLeft;
        node.left = nodeLeft.right;
        // modify nodeLeft.right
        if ( nodeLeft.right != null ) nodeLeft.right.parent = node;
        // modify nodeLeft
        nodeLeft.right = node;
        nodeLeft.parent = nodeParent;
        // update height
        updateHeight(node);
        updateHeight(nodeLeft);
        return nodeLeft;
    }
    
    private AVLNode<K, V> rotateLeft(AVLNode<K, V> node) {
        AVLNode<K, V> nodeParent = node.parent;
        AVLNode<K, V> nodeRight = node.right;
        // modify nodeParent
        if ( nodeParent != null ) {
            if ( nodeParent.right == node ) nodeParent.right = nodeRight;
            else nodeParent.left  = nodeRight;
        }
        // modify node
        node.parent = nodeRight;
        node.right = nodeRight.left;
        // modify nodeRight.left
        if ( nodeRight.left != null ) nodeRight.left.parent = node;
        // modify nodeRight
        nodeRight.left = node;
        nodeRight.parent = nodeParent;
        // update height
        updateHeight(node);
        updateHeight(nodeRight);
        return nodeRight;
    }

    private AVLNode<K, V> rotateLeftRight(AVLNode<K, V> node) {
        node.left = rotateLeft(node.left);
        return rotateRight(node);
    }

    private AVLNode<K, V> rotateRightLeft(AVLNode<K, V> node) {
        node.right = rotateRight(node.right);
        return rotateLeft(node);
    }

    /* ========================= Print Methods ============================== *
     *                                                                        *
     *     The following two methods is implemented in tutorial of week 05.   *
     *                                                                        *
     * ====================================================================== */

    // pretty print the AVL tree
    public void print() {
        System.out.println("AVL Tree:");
        print(this.root, "");
        System.out.println("\n");
    }

    // pretty print the AVL tree rooted at curr
    public void print(AVLNode<K, V> curr, String prefix) {
        if (curr != null) {
            print(curr.right, prefix + "    ");
            System.out.println(prefix + curr.val + "(" + curr.height + "," + balanceFactor(curr) + ")");
            // System.out.println(prefix + "(" + curr.key + "," + curr.val + ")");
            print(curr.left, prefix + "    ");
        }
    }

    /* ====================== Representation Invariant ====================== */
    
    // representation invariant checker for AVL tree
    private boolean repOK() {
        return AVLBalanceCondition() && RIofBST() && BSTreeProperty();
    }
    
    // AVL tree balance condition
    private boolean AVLBalanceCondition() {
        boolean checkFlag = true;
        Sequence<AVLNode<K ,V>> nodes = nodesSequence();
        int i = 0;
        while ( checkFlag && i < nodesNum ) {
            int balFactor = balanceFactor(nodes.at(i));
            if ( balFactor < -1 || balFactor > 1 ) checkFlag = false;
            else i++; 
        }
        return checkFlag;
    }
        
    // binary search tree property
    private boolean BSTreeProperty() {
        boolean checkeFlag = true;
        Sequence<AVLNode<K, V>> nodes = nodesSequence();
        int i = 0;
        while ( checkeFlag && i < nodesNum ) {
            AVLNode<K, V> curr = nodes.at(i);
            AVLNode<K, V> left = curr.left;
            AVLNode<K, V> right = curr.right;
            if ( right != null && left != null )
                // left.key < curr.key < right.key
                checkeFlag = ( left.key.compareTo(curr.key) < 0 ) && ( curr.key.compareTo(right.key) < 0 );
            else if ( right != null )
                // curr.key < right
               checkeFlag = ( curr.key.compareTo(right.key) < 0 ); 
            else if ( left != null )
                // left.key < curr.key
                checkeFlag = ( left.key.compareTo(curr.key) < 0 );
            i++;
        }
        return checkeFlag;
    }

    // representation invariant of BST
    private boolean RIofBST() { return uniqueRoot() && parentChildRelationship(); }

    private boolean uniqueRoot() {
        boolean checkFlag = true;
        Sequence<AVLNode<K, V>> nodes = nodesSequence();
        int i = 0;
        while ( checkFlag && i < nodesNum ) {
            AVLNode<K, V> curr = nodes.at(i);
            if ( curr != this.root && curr.parent == null ) checkFlag = false;
            else i++;
        }
        return checkFlag;
    }

    private boolean parentChildRelationship() {
        boolean checkeFlag = true;
        Sequence<AVLNode<K, V>> nodes = nodesSequence();
        int i = 0;
        int j = 0;
        while ( checkeFlag && i < nodesNum ) {
            while ( checkeFlag && j < nodesNum ) {
                AVLNode<K, V> node1 = nodes.at(i);
                AVLNode<K, V> node2 = nodes.at(j);
                if (
                    node1.left  == node2 && node2.parent != node1 ||
                    node1.right == node2 && node2.parent != node1
                ) checkeFlag = false;
                else j++;
            }
            i++;
        }
        return checkeFlag;
    }
}