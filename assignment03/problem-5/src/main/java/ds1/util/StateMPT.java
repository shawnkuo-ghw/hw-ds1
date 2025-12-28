package ds1.util;
import ds1.util.HashUtils;
import ds1.util.Sequence;
import javax.swing.text.Utilities;
import ds1.util.ListoverLinkedList;

/** 
 * MPTNode represents a node in the simplified Merkle Trie
 **/
class MPTNode
{
    /* =================== Fields and Constructor =========================== */

    Integer balance;    // the balance
    boolean isAddress;  // whether the node is the end of an address
    MPTNode parent;     // the parent of the node
    String nodeHash;    // the hash of the node
    MPTNode[] children; // the children of the node
    final int N = 10; // the number of children
        
    public MPTNode() {
        balance   = null;
        isAddress = false;
        parent    = null;
        nodeHash  = null;
        children  = new MPTNode[N];
        for (int i = 0; i < N; i++) children[i] = null;
    }

    /** 
     * Compute the hash of this node based on its balance and children's hashes
     * <p> Time complexity: O(1) </p>
     **/
    public String computeHash() {        
        String balanceOrNull = ( isAddress ? balance.toString() : "null" );
        String childrenHash = "";
        for (MPTNode child : children) {
            if ( child == null ) childrenHash += "0";
            else childrenHash += child.nodeHash;
        }
        // System.out.println("balanceOrNull: " + balanceOrNull);
        // System.out.println("childrenHash: " + childrenHash);
        return HashUtils.hash(balanceOrNull + childrenHash);
    }

    /** 
     * Update the hash of this node based on its balance and children's hashes
     * <p> Time complexity: O(1) </p>
     **/
    public void updateHash() { nodeHash = computeHash(); }
}

/** 
 * StateMPT represents a simplified Simple Merkle Trie
 * for managing account balances in a blockchain
 **/
public class StateMPT
{
    /* ======================= Field and Constructor ======================== */

    private int size;           // number of addresses
    private final int N = 10;   // number of children
    private final MPTNode root; // root of simplified simple merkle trie
    public StateMPT() { root = new MPTNode(); }

    // Implement required methods here
    // at least getRootHash, and operation to insert/update balances
    // Include a repOK method to check class invariants

    /* ============================= Getters ================================ */

    public int adddressesCount() { return size; }
    public String getRoothash() { return root.nodeHash; }
    public Sequence<String> getAllAdressesSequence() { return stringsWithPrefix(""); }

    /**
     * Search the balance associated with the given address (if any)
     * <p> Time Complexity: O(L) </p>
     * <li> - This method is implemented by calling {@code findPrefixNode}, whose time complexity is O(L) </li>
     * @param address the address to query
     * @return the associated balance of {@code address}; or {@code -1} if not found
     * @see #findPrefixNode(String)
     */
    public int search(String address) {
        MPTNode node = findPrefixNode(address);
        if ( node == null ) return -1;
        else {
            if ( node.isAddress ) return node.balance.intValue();
            else return -1;
        }
    }

    /* ============================== Modifiers ============================= */

    /**
     * Insert a new address-balance pair
     * <p> Time Complexity: O(L) </p>
     * <li> - The implementation consist of a L-loop and updating hashes, where L is the length of {@code newAddress} </li>
     * <li> - It takes constant time to run each loop, so the L-loop has time complexity O(L). </li>
     * <li> - The time complexity of {@code propagateHashUpdatingUpwards} is exactly O(L), since P is equal to L in this case </li>
     * @param newAddress new address
     * @param newBalance new balance
     * @throws IllegalArgumentException if {@code newBalance < 0} or {@newAddress} is null, empty or duplicate.
     * @see #propagateHashUpdatingUpwards(MPTNode)
     */
    public void insert(String newAddress, int newBalance) {
        if ( newAddress == null || newAddress.equals("" ) || newBalance < 0) 
            throw new IllegalArgumentException("StateMPT.insert(newAdd, newBal): methods arguments illegal.");
        if ( findPrefixNode(newAddress) != null && findPrefixNode(newAddress).isAddress )
            throw new IllegalArgumentException("StateMPT.insert(newAdd, newBal): duplicate key.");
        MPTNode currNode = root;
        for (int i = 0; i < newAddress.length(); i++) {
            int digit = charToNum(newAddress.charAt(i));
            MPTNode child = currNode.children[digit];
            if ( child == null ) {
                MPTNode newNode = new MPTNode();
                newNode.parent = currNode;
                currNode.children[digit] = newNode;
                currNode = newNode;
            } else currNode = child;
        }
        currNode.isAddress = true;
        currNode.balance = newBalance;
        propagateHashUpdatingUpwards(currNode);
        size++;
    }

    /**
     * Updates balance and recalculates hashes up to root in the SMT
     * <p> Time Complexity: O(L), where L is address length </p>
     * <li> - This method is implemented by calling {@code findPrefixNode}
     *        and {@code propagateHashUpdatingUpwards} </li>
     * <li> - both of these two methods run with time complexity O(L) </li>
     * @param address the address
     * @param newBalance new balance to update
     * @see #findPrefixNode(String)
     * @see #propagateHashUpdatingUpwards(MPTNode)
     */
    public void update(String address, int newBalance) {
        MPTNode node = findPrefixNode(address); // O(L)
        if ( node != null ) node.balance = newBalance;
        propagateHashUpdatingUpwards(node); // O(L)
    }

    /* ========================= Private Utilities ========================== */

    private static int charToNum(char ch) { return ch - '0'; }
    private static char numToChar(int num) { return (char) ('0' + num);}
    
    /**
     * Propagate the hash updating upwards to the root from the node {@code node}
     * <p> Time Complexity: O(P), where P is the length of prefix associated with {@code node} </p>
     * @param node the node to start propagating the hash updating to the root
     */
    private void propagateHashUpdatingUpwards(MPTNode node) {
        MPTNode currNode = node;
        while ( currNode != null ) {
            currNode.updateHash();
            currNode = currNode.parent;
        }
    }

    /**
     * Find the node corresponding to {@code prefix}
     * <p> Time Complexity: O(L), where L is the prefix length </p>
     * <li> - There are at most L many loops to be executed </li>
     * <li> - In each loop, the time complexity is constant time </li>
     * <li> - Therefore, the time complexity is L * O(1) = O(L) </li>
     * @param prefix the prefix to query
     * @return the node associted with {@code prefix} if it exists; {@code null} otherwise
     */
    private MPTNode findPrefixNode(String prefix) {
        MPTNode currNode = root;
        // System.out.println("findPrefixNode: prefix: " + prefix);
        for (int i = 0; i < prefix.length(); i++) { // L * O(1) = O(L)
            int digit = charToNum(prefix.charAt(i));
            // System.out.println("digit: " + digit);
            MPTNode child = currNode.children[digit];
            if ( child == null ) return null;
            else currNode = child;
        }
        return currNode;
    }

    /**
     * Get all strings with common prefix {@code prefix}
     * @param prefix the common prefix of strings
     * @return a list of strings with prefix {@code prefix}
     */
    private Sequence<String> stringsWithPrefix(String prefix) {
        // System.out.println("stringsWithPrefix:");
        Sequence<String> queue = new ListoverLinkedList<String>();
        MPTNode prefixNode = findPrefixNode(prefix);
        collect(prefixNode, prefix, queue);
        return queue;
    }

    private void collect(MPTNode currNode, String currPrefix, Sequence<String> queue) {
        // System.out.println("curr prefix: " + currPrefix);
        if ( currNode.isAddress ) queue.insertRear(currPrefix);
        for (int i = 0; i < N; i++) {
            MPTNode child = currNode.children[i];
            if ( child != null ) collect(child, currPrefix + numToChar(i), queue);
        }
    }

    /* ================== Representation Invariant ========================== */

    // SMT is a trie where hashes of parent nodes
    // is the sum of hashes of their childreen.
    public boolean repOK() { return checkMPTNodesInvariantRecursivly(root); }

    private boolean checkMPTNodesInvariantRecursivly(MPTNode node) {
        boolean nodesInvariant = true;
        nodesInvariant = checkMPTNodeInvariant(node) && nodesInvariant;
        for (MPTNode child : node.children)
            if ( child != null ) nodesInvariant = checkMPTNodeInvariant(child) && nodesInvariant;
        return nodesInvariant;
    }

    private boolean checkMPTNodeInvariant(MPTNode node) {
        return node.nodeHash.equals( HashUtils.hash(getBalanceHash(node) + getChildrenHash(node)) );
    }

    private String getChildrenHash(MPTNode node) {
        String childrenHash = "";
        for ( MPTNode child : node.children ) {
            if ( child == null ) childrenHash += "0";
            else childrenHash += child.nodeHash;
        }
        return childrenHash;
    }

    private String getBalanceHash(MPTNode node) {
        return node.isAddress ? node.balance.toString() : "null";
    }
}