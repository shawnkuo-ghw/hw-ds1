package ds1.util;
/** 
 * MPTNode represents a node in the simplified Merkle Patricia Trie
 **/
class MPTNode {
    // Add required fields here
    // Recall nodes has 10 children for numeric addresses
    // and may store a balance if it's a leaf
    // and a hash for the node

    /** 
     * Compute the hash of this node based on its balance and children's hashes
     **/
    public String computeHash() {
        // Should be implemented by students
        throw new UnsupportedOperationException("Not implemented yet");

    }

    /** 
     * Update the hash of this node based on its balance and children's hashes
     **/
    public void updateHash() {
        // Should be implemented by students
        throw new UnsupportedOperationException("Not implemented yet");
    }
}

/** 
 * StateMPT represents a simplified Simple Merkle Trie
 * for managing account balances in a blockchain
 **/
public class StateMPT {
    private MPTNode root;

    public StateMPT() {
        this.root = new MPTNode();
    }

    // Implement required methods here
    // at least getRootHash, and operation to insert/update balances
    // Include a repOK method to check class invariants

}