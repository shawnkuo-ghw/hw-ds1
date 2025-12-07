package ds1;
import ds1.util.PriorityQueue;
import java.util.NoSuchElementException;

/** 
 * Block.java
 * This class represents a block in the blockchain.
 * It contains a priority queue of transactions, a block hash, a previous block hash, and a block number.
 * Transactions are stored in a priority queue based on their fees.
 */

public class Block implements Comparable<Block>
{
    /* ==================== Fields and Constructor ========================== */
    
    private PriorityQueue pq;
    private final int blockHash;
    private final int previousHash;
    private final int blockNumber;
    private final int transactionsPerBlock;

    public Block(int previousHash, int transactionsPerBlock, int blockNumber) {
        this.blockHash = previousHash + 1;
        this.previousHash = previousHash;
        this.blockNumber = blockNumber;
        this.transactionsPerBlock = transactionsPerBlock;
        pq = new PriorityQueue(transactionsPerBlock);
    }

    /* =========================== Modifier ================================= */

    /** 
     * Adds a transaction to the block's priority queue.
     * @param t The transaction to be added.
     * @throws IllegalStateException if the priority queue is full
     * Time Complexity: O(log T) where T is the number of transactions in the block.
     *   - this method is implemented by calling `pq.enquue()`
     *   - the time complexity of enqueue is O(log T)
     *   - therefore the time complexity is O(log T)
     */
    public void addTransaction(TransactionWithFee t) {
        if ( pq.isFull() ) {
            throw new IllegalStateException("Block.addTransaction(): block is full.");
        }
        pq.enqueue(t); // time complexity: O()
    }

    /* ============================== Getter ================================ */
    
    /** 
     * Retrieves the transaction with the highest priority (highest fee) without removing it.
     * @return The transaction with the highest priority.
     * @throws NoSuchElementException if the priority queue is empty
     * Time complexity: O(1)
     *   - the time complexity of pq.next() is O(1)
     */
    public TransactionWithFee getFirstTransaction() {
        if ( pq.isEmpty() ) {
            throw new NoSuchElementException("Block.getFirstTransaction(): block is empty.");
        }
        return pq.next();
    }
    
    /**
     * Returns an array of transactions in the block sorted by priority (highest fee first).
     * @return An array of transactions.
     * O(T log T) where T is the number of transactions in the block.
     */
    public TransactionWithFee[] getTransactions() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public boolean isFull() { return pq.isFull(); }
    
    // Getters
    public int getBlockHash() { return this.blockHash; }
    public int getPreviousHash() { return this.previousHash; }
    public int getBlockNumber() { return this.blockNumber; }
    public int getTransactionCount() { return pq.size(); }
    
    // toString for debugging
    @Override
    public String toString() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public int compareTo(Block o) {
        if ( !(o instanceof Block) ) {
            throw new IllegalArgumentException("Block.compareTo(): o is not of type Block");
        }
        Block other = (Block) o;
        return Integer.compare(blockNumber, o.blockNumber);
    }
}