package ds1;
/** 
 * Block.java
 * This class represents a block in the blockchain.
 * It contains a priority queue of transactions, a block hash, a previous block hash, and a block number.
 * Transactions are stored in a priority queue based on their fees.
 */

public class Block implements Comparable<Block> {
    public Block(int previousHash, int transactionsPerBlock, int blockNumber) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /** 
     * Adds a transaction to the block's priority queue.
     * @param t The transaction to be added.
     * O(log T) where T is the number of transactions in the block.
     */
    public void addTransaction(TransactionWithFee t) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    /** 
     * Retrieves the transaction with the highest priority (highest fee) without removing it.
     * @return The transaction with the highest priority.
     * O(1)
     */
    public TransactionWithFee getFirstTransaction() {
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    /**
     * Returns an array of transactions in the block sorted by priority (highest fee first).
     * @return An array of transactions.
     * O(T log T) where T is the number of transactions in the block.
     */
    public TransactionWithFee[] getTransactions() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public boolean isFull() {
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    // Getters
    public int getBlockHash() {
        throw new UnsupportedOperationException("Not implemented yet");
    }
    public int getPreviousHash() {
        throw new UnsupportedOperationException("Not implemented yet");
     }
    public int getBlockNumber() { 
        throw new UnsupportedOperationException("Not implemented yet");
     }
    public int getTransactionCount() { 
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    // toString for debugging
    @Override
    public String toString() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public int compareTo(Block o) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'compareTo'");
    }
}