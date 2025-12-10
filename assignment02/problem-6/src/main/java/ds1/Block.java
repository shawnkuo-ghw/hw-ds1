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
    
    private PriorityQueue transactionsPQ;
    private final int transactionsPerBlock;
    private final int previousHash;
    private final int blockNumber;
    private final int blockHash;

    public Block(int previousHash, int transactionsPerBlock, int blockNumber) {
        this.blockHash = previousHash + 1;
        this.previousHash = previousHash;
        this.blockNumber = blockNumber;
        this.transactionsPerBlock = transactionsPerBlock;
        transactionsPQ = new PriorityQueue(transactionsPerBlock);
    }

    /* =========================== Modifiers ================================ */

    /** 
     * Adds a transaction to the block's priority queue.
     * @param t The transaction to be added.
     * @throws IllegalStateException if the priority queue is full
     * Time Complexity: O(log T) where T is the number of transactions in the block.
     *   - this method is implemented by calling `transactionsPQ.enquue()`
     *   - the time complexity of enqueue is O(log T)
     *   - therefore the time complexity is O(log T)
     * @see ds1.util.GenericMaxPQ#enqueue(Comparable)
     */
    public void addTransaction(TransactionWithFee t) {
        if ( transactionsPQ.isFull() )
            throw new IllegalStateException("Block.addTransaction(): block is full.");
        else 
            transactionsPQ.enqueue(t); // time complexity: O()
    }

    /* ============================== Getters =============================== */
    
    /** 
     * Retrieves the transaction with the highest priority (highest fee) without removing it.
     * @return The transaction with the highest priority.
     * @throws NoSuchElementException if the priority queue is empty
     * Time complexity: O(1)
     *   - this method is implemented by calling `transactionsPQ.next()`
     *   - the time complexity of `transactionsPQ.next()` is O(1)
     * @see ds1.util.GenericMaxPQ#next()
     */
    public TransactionWithFee getFirstTransaction() {
        if ( transactionsPQ.isEmpty() )
            throw new NoSuchElementException("Block.getFirstTransaction(): block is empty.");
        else
            return transactionsPQ.next();
    }
    
    /**
     * Returns an array of transactions in the block sorted by priority (highest fee first).
     * @return An array of transactions.
     * O(T log T) where T is the number of transactions in the block.
     * Time complexity: O(T log T)
     *   - this method is implemented by calling `transactionsPQ.toArray()`
     *   - the time complexity of `traWithFee.toArray()` is O(N log N), where N is the number of elements
     * @see ds1.util.GenericMaxPQ#toArray()
     */
    public TransactionWithFee[] getTransactions() { return transactionsPQ.toArray(); }
    
    public boolean isFull() { return transactionsPQ.isFull(); }
    public int getBlockHash() { return this.blockHash; }
    public int getPreviousHash() { return this.previousHash; }
    public int getBlockNumber() { return this.blockNumber; }
    public int getTransactionCount() { return transactionsPQ.size(); }
    
    // toString for debugging
    @Override
    public String toString() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // Compaprable interface
    @Override
    public int compareTo(Block o) {
        if ( !(o instanceof Block) ) throw new IllegalArgumentException("Block.compareTo(): o is not of type Block");
        Block other = (Block) o;
        return Integer.compare(this.blockNumber, o.blockNumber);
    }
}