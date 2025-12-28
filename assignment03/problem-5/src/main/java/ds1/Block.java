package ds1;
import ds1.util.HashUtils;
import ds1.util.PriorityQueue;

/** 
 * Block.java
 * This class represents a block in the blockchain.
 * It contains a priority queue of transactions, a block hash, a previous block hash, and a block number.
 * Transactions are stored in a priority queue based on their fees.
*/
public class Block implements Comparable<Block>
{
    /* ============================= Fields ================================= */

    private final PriorityQueue transactions;
    private final int transactionsPerBlock;
    private final String previousHash;
    private final int blockNumber;
    private String stateRootHash;
    private String blockHash;
    private String txHash;

    public Block(String previousHash, int transactionsPerBlock, int blockNumber) {
        this.previousHash = previousHash;
        this.blockHash = "";
        this.transactionsPerBlock = transactionsPerBlock;
        this.transactions = new PriorityQueue(transactionsPerBlock);
        this.blockNumber = blockNumber;
    }

    /* =========================== Modifiers ================================ */

    public void addTransaction(TransactionWithFee t) {
        if (isFull()) throw new IllegalStateException("Block is full");
        transactions.enqueue(t);
    }
    
    /** 
     * Compute and set the block hash
     * Requires stateRootHash to be set
     * Block hash is computed as hash(previousHash + stateRootHash + TxHash)
     * where TxHash is the hash of all transactions in the block
     **/
    public String computeAndSetBlockHash() {
        txHash = computeTxHash(getTransactions());
        String newBlockHash = HashUtils.hash(previousHash + stateRootHash + txHash);
        blockHash = newBlockHash;
        return newBlockHash;
    }
    
    /** 
     * Set the state root hash for this block
     **/
    public void setStateRootHash(String stateRootHash) { this.stateRootHash = stateRootHash;}

    
    /* ============================== Getters =============================== */
    
    public boolean isFull() {
        // Check if the block is full
        // A block is considered full if it has reached its transaction limit
        // or is the genesis block
        return transactions.size() >= transactionsPerBlock;
    }
    
    public String getBlockHash() { return blockHash; }
    public String getPreviousHash() { return previousHash; }
    public Object getStateRootHash() { return stateRootHash; }
    public int getBlockNumber() { return blockNumber; }
    public int getTransactionCount() { return transactions.size(); }
    public TransactionWithFee getFirstTransaction() { return transactions.next(); }

    @Override
    public int compareTo(Block o) { return Integer.compare(this.blockNumber, o.blockNumber); }
    
    /** 
     * Get all transactions in the block as an array
     **/
    public TransactionWithFee[] getTransactions() { return transactions.toArray(); }

    /** 
     * Recommended helper method:
     * Compute the transaction hash for an array of transactions
     * TxHash = hash(Tx1.hash + hash(Tx2.hash + ...))
     * <p> Time Complexity: O(T) </p>
     * <li> - The method consists of iterating the list of transactions, whose length is T </li>
     * <li> - The time complexity of loop body is constant </li>
     * <li> - Thus the time complexity is T * O(1) = O(T) </li>
     **/
    private String computeTxHash(Transaction[] transactions) {
        // if ( !isFull() ) throw new IllegalStateException("Block is not full.");
        String newTxHash = "";
        for (int i = 0; i < transactions.length; i++) { // O(T)
            if (i == 0) newTxHash = HashUtils.hash(transactions[i].hash());
            else        newTxHash = HashUtils.hash(transactions[i].hash() + newTxHash);
        }
        return newTxHash;
    }
    
    /* ========================= Class Invariant ============================ */

    /** Legacy repOK method for checking class invariants
     * No longer used in the current implementation
     **/
    public boolean repOK() {
        // Check: previousHash == currentHash - 1
        // Check: block number is positive
        return blockHash == previousHash + 1 && blockNumber >= 0;
    }

    /* ============================== Debugger ============================== */

    // toString for debugging
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Block Number: ").append(blockNumber).append("\n");
        sb.append("Previous Hash: ").append(previousHash).append("\n");
        sb.append("Block Hash: ").append(blockHash).append("\n");
        sb.append("Transactions:\n");
        for (TransactionWithFee t : transactions.toArray()) {
            sb.append(t.toString()).append("\n");
        }
        return sb.toString();
    }
}