package ds1;
import ds1.util.HashUtils;
/** 
 * Block.java
 * This class represents a block in the blockchain.
 * It contains a priority queue of transactions, a block hash, a previous block hash, and a block number.
 * Transactions are stored in a priority queue based on their fees.
 */
import ds1.util.PriorityQueue;

public class Block implements Comparable<Block> {
    private String blockHash;
    private final String previousHash;
    private final int transactionsPerBlock;
    private final PriorityQueue transactions;
    private final int blockNumber;
    private String stateRootHash;
    
    public Block(String previousHash, int transactionsPerBlock, int blockNumber) {
        this.previousHash = previousHash;
        this.blockHash = "";
        this.transactionsPerBlock = transactionsPerBlock;
        this.transactions = new PriorityQueue(transactionsPerBlock);
        this.blockNumber = blockNumber;
    }
    
    public void addTransaction(TransactionWithFee t) {
        if (isFull()) {
            throw new IllegalStateException("Block is full");
        }
        transactions.enqueue(t);
    }
    
    public TransactionWithFee getFirstTransaction() {
        return transactions.next();
    }
        
    public boolean isFull() {
        // Check if the block is full
        // A block is considered full if it has reached its transaction limit
        // or is the genesis block
        return transactions.size() >= transactionsPerBlock;
    }
    
    // Getters
    public String getBlockHash() { return blockHash; }
    public String getPreviousHash() { return previousHash; }
    public int getBlockNumber() { return blockNumber; }
    public int getTransactionCount() { return transactions.size(); }

    /** Legacy repOK method for checking class invariants
     * No longer used in the current implementation
     **/
    public boolean repOK() {
        // Check: previousHash == currentHash - 1
        // Check: block number is positive
        return blockHash == previousHash + 1 && blockNumber >= 0;
    }

    /** 
     * Get all transactions in the block as an array
     **/
    public TransactionWithFee[] getTransactions() {
        return transactions.toArray();
    }

    /** 
     * Set the state root hash for this block
     **/
    public void setStateRootHash(String stateRootHash) {
        this.stateRootHash = stateRootHash;
    }

    /** 
     * Compute and set the block hash
     * Requires stateRootHash to be set
     * Block hash is computed as hash(previousHash + stateRootHash + TxHash)
     * where TxHash is the hash of all transactions in the block
     **/
    public String computeAndSetBlockHash() {
        String TxHash = computeTxHash(getTransactions());
        String blockHash = HashUtils.hash(previousHash + stateRootHash + TxHash);
        this.blockHash = blockHash;
        return blockHash;
    }

    /** 
     * Recommended helper method:
     * Compute the transaction hash for an array of transactions
     * TxHash = hash(Tx1.hash + hash(Tx2.hash + ...))
     **/
    private String computeTxHash(Transaction[] transactions) {
        String hash = HashUtils.hash(transactions[0].hash());
        for (int i = 1; i < transactions.length; i++) {
            hash = HashUtils.hash(transactions[i].hash() + hash);
        }
        this.blockHash = hash;
        return hash;
    }

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

    @Override
    public int compareTo(Block o) {
        return Integer.compare(this.blockNumber, o.blockNumber);
    }

    public Object getStateRootHash() {
        return stateRootHash;
    }
}