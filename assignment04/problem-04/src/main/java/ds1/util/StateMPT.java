package ds1.util;
/** 
 * MPTNode represents a node in the simplified Merkle Patricia Trie
 **/
class MPTNode {
    // 0-9 children for numeric addresses
    MPTNode[] children = new MPTNode[10];

    public Integer balance = null; // Value is null unless it's a leaf/terminating node
    public String hash = "";

    public MPTNode() {
        updateHash();
    }

    /** 
     * Compute the hash of this node based on its balance and children's hashes
     **/
    public String computeHash() {
        StringBuilder sb = new StringBuilder();
        // null balance is treated as "null" string
        if (balance != null) sb.append(balance);
        else sb.append("null");

        // null children are treated as "0" hash
        for (MPTNode child : children) {
            if (child != null) sb.append(child.hash);
            else sb.append("0");
        }
        return HashUtils.hash(sb.toString());
    }

    /** 
     * Update the hash of this node based on its balance and children's hashes
     **/
    public void updateHash() {
        this.hash = computeHash();
    }
}

/** 
 * StateMPT represents a simplified Merkle Patricia Trie
 * for managing account balances in a blockchain
 **/
public class StateMPT {
    private MPTNode root;

    public StateMPT() {
        this.root = new MPTNode();
    }

    /**
     * Put or update the balance for a given address
     *  O(L) - Update balance and propagate hash changes
     **/
    public void put(String address, int balance) {
        // Calls the recursive helper to do the work
        putRecursive(root, address, 0, balance);
    }

    /** Helper method for put operation 
     **/
    private void putRecursive(MPTNode current, String address, int index, int balance) {
        if (index == address.length()) {
            // End of address
            current.balance = balance;
            current.updateHash(); // Leaf change
            return;
        }

        // Recur down the trie
        char c = address.charAt(index);
        int digit = c - '0';
        if (digit < 0 || digit > 9) throw new IllegalArgumentException("Address must be 0-9");

        if (current.children[digit] == null) {
            current.children[digit] = new MPTNode();
        }

        putRecursive(current.children[digit], address, index + 1, balance);
        
        // After returning, update current node's hash
        // to reflect changes in subtree
        current.updateHash();
    }

    /** 
     * Get the balance for a given address
     * Returns 0 if address not found
     * O(L)
     **/ 
    public int get(String address) {
        // Calls the recursive helper to do the work
        MPTNode node = getRecursive(root, address, 0);
        return (node != null && node.balance != null) ? node.balance : 0;
    }

    /** 
     * Helper method for get operation 
     **/
    private MPTNode getRecursive(MPTNode current, String address, int index) {
        if (current == null) return null;
        if (index == address.length()) return current;

        int digit = address.charAt(index) - '0';
        return getRecursive(current.children[digit], address, index + 1);
    }

    /** 
     * Get the root hash of the MPT
     * O(1)
     **/
    public String getRootHash() {
        return root.hash;
    }

    /** 
     * Get all addresses stored in the MPT
     * O(N) where N is number of addresses
     **/
    public ListoverLinkedList<String> getAllAddresses() {
        ListoverLinkedList<String> addresses = new ListoverLinkedList<>();
        collectAddresses(root, "", addresses);
        return addresses;
    }

    /** 
     * Helper method to collect addresses
     **/
    private void collectAddresses(MPTNode current, String prefix, ListoverLinkedList<String> addresses) {
        if (current == null) return;
        if (current.balance != null) {
            addresses.insertFront(prefix);
        }
        for (int i = 0; i < 10; i++) {
            if (current.children[i] != null) {
                collectAddresses(current.children[i], prefix + (char)('0' + i), addresses);
            }
        }
    }

    public boolean repOK() {
        // Check: root is not null
        if (root == null) return false;
        // Check hash consistency of the hash of each node
        return repOKRecursive(root);
    }
    private boolean repOKRecursive(MPTNode current) {
        if (current == null) return true;
        // Recompute hash
        String oldHash = current.hash;
        String computedHash = current.computeHash();
        if (!oldHash.equals(computedHash)) return false;
        // Recur for children
        for (MPTNode child : current.children) {
            if (!repOKRecursive(child)) return false;
        }
        return true;
    }



}