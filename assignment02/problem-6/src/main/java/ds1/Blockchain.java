package ds1;

import ds1.util.Sequence;

// Blockchain.java
public interface Blockchain {
    /** 
     * Request to a add a transaction to the blockchain.
     * The transaction is added to a transaction pool.  
     * @throws IllegalArgumentException if amount is not positive
     */
    void requestTransaction(String fromAddress, String toAddress, int amount, int fee);

    /** 
     * Extracts transactions from the transaction pool respecting the priorities.
     * If the current block becomes full after adding the transaction, 
     * the blockchain adds the block (see addBlock) and exits.
     * If the transaction pool becomes empty, before the block is full 
     * just leave the transaction in the block but do not proccess it
     * @return true if the block was mined (i.e., became full), false otherwise
     * O(transactionsPerBlock * (log T + log A) ) where A is the number of addresses 
     * and T is the number of transactions in the pool
     *
     */
    boolean mineBlock();
    
    /** 
     * Requires the block to be full of transactions.  
     * This method should execute the transactions update the balances of the addresses involved. 
     * If some transaction fail to complete the transaction should be marked as reverted and the balance 
     * of the involved addresses should be not affected.
     * Once all transactions are processed a new empty block is created and set as the current block. 
     * @throws IllegalStateException if block is not full
     * O(transactionsPerBlock log A) where A is the number of addresses
     *
     */ 
    void processCurrentBlockAndStartNewBlock();

    /**
     * Retrieves a block by index
     * @param index position in chain (0-based)
     * @return the Block at specified index
     * @throws IndexOutOfBoundsException if index is invalid
     * O(log B) where B is the number of blocks
     */
    Block getBlock(int index);
    
    /**
     * Returns number of blocks in blockchain
     * @return block count
     * O(1)
     */
    int size();
    
    /**
     * Returns the most recently added block
     * @return last block, or null if empty
     * O(1)
     */
    Block getLastBlock();
    
    /**
     * Returns all blocks in storage order
     * @return list of blocks
     * O(B) where B is the number of blocks
     */
    Sequence<Block> getBlocks();
    
    /**
     * Gets current balance of an address
     * @param address the address to check
     * @return balance of the address
     * O(log A) where A is the number of addresses
     */
    int getBalance(String address);

    /**
     * Returns the total number of transactions in the transaction pool
     * @return transaction pool size
     * O(1)
     */
    int getTransactionPoolSize();

    /**
     * Get block by its number in O(log B)
     * @param number the block number
     * @return the Block with the given number
     */
    Block getBlockByNumber(int number);

    /**
     * Get the number of successful transactions processed so far
     * @return count of successful transactions
     */
    int getSuccessfulTransactionsCount();

    /**
     * Get the number of reverted transactions processed so far
     * @return count of reverted transactions
     */
    int getRevertedTransactionsCount();

    /**
     * Get the total fees returned from reverted transactions so far
     * @return total returned fees
     */
    int getReturnedFees();
    
}