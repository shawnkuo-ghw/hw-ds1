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
    
    private PriorityQueue transactionsPQ;   // priority queue of transactions in a block
    private final int transactionsPerBlock; // number of transaction in per block
    private final int blockHash;            // the hash of block
    private final int previousHash;         // the hash of previous block
    private final int blockNumber;          // the number of block

    public Block(int previousHash, int transactionsPerBlock, int blockNumber) {
        this.blockHash = previousHash + 1;
        this.previousHash = previousHash;
        this.blockNumber = blockNumber;
        this.transactionsPerBlock = transactionsPerBlock;
        transactionsPQ = new PriorityQueue();
    }

    /* =========================== Modifiers ================================ */

    /** 
     * Adds a transaction to the block's priority queue.
     * @param t The transaction to be added.
     * @throws IllegalStateException if the priority queue is full
     * <p> Time Complexity: O(log T) where T is the number of transactions in the block </p>
     * <li> - this method is implemented by calling {@code transactionsPQ.enquue} </li>
     * <li> - the time complexity of enqueue is O(log T) </li>
     * <li> - therefore the time complexity is O(log T) </li>
     * @see ds1.util.GenericMaxPQ#enqueue(Comparable)
     */
    public void addTransaction(TransactionWithFee t) {
        if ( isFull() )
            throw new IllegalStateException("Block.addTransaction(): block is full.");
        else 
            transactionsPQ.enqueue(t); // O(log T)
    }

    /* ============================== Getters =============================== */
    
    /** 
     * Retrieves the transaction with the highest priority (highest fee) without removing it.
     * @return The transaction with the highest priority.
     * @throws NoSuchElementException if the priority queue is empty
     * <p> Time complexity: O(1) </p>
     * <li> - this method is implemented by calling {@code transactionsPQ.next} </li>
     * <li> - the time complexity of {@code transactionsPQ.next} is O(1) </li>
     * @see ds1.util.GenericMaxPQ#next()
     */
    public TransactionWithFee getFirstTransaction() {
        if ( isEmpty() )
            throw new NoSuchElementException("Block.getFirstTransaction(): block is empty.");
        else
            return transactionsPQ.next();
    }
    
    /**
     * Returns an array of transactions in the block sorted by priority (highest fee first).
     * @return An array of transactions.
     * <p> Time complexity: O(T log T), where T is the number of transactions in the block. </p>
     * <li> - this method is implemented by calling {@code transactionsPQ.toArray} </li>
     * <li> - the time complexity of {@code traWithFee.toArray} is O(N log N), where N is the number of elements </li>
     * @see ds1.util.GenericMaxPQ#toArray()
     */
    public TransactionWithFee[] getTransactions() { return transactionsPQ.toArray(); }
    
    public int getBlockHash() { return this.blockHash; }
    public int getPreviousHash() { return this.previousHash; }
    public int getBlockNumber() { return this.blockNumber; }
    public int getTransactionCount() { return transactionsPQ.size(); }
    public boolean isEmpty() { return transactionsPQ.size() == 0; }
    public boolean isFull() { return transactionsPQ.size() == transactionsPerBlock; }
    
    // toString for debugging
    @Override
    public String toString() {
        return "";
    }

    // Compaprable interface
    @Override
    public int compareTo(Block o) {
        if ( !(o instanceof Block) )
            throw new IllegalArgumentException("Block.compareTo(): o is not of type Block");
        Block other = (Block) o;
        return Integer.compare(this.blockNumber, other.blockNumber);
    }
}