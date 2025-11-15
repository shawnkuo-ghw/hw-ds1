// Blockchain.java

package ds1;
import ds1.utils.Sequence;

/** 
 * Interface for a simple blockchain system.
 * Read the information about the methods carefully in the assignment.
 */
public interface Blockchain
{
    /** 
     * Request to a add a transaction to the current block in the blockchain.  
     * If the current block becomes full after adding the transaction, the blockchain must try 
     * to add the block (see addBlock).     
     * @throws IllegalArgumentException if amount is not positive
     */
    void processTransaction(String fromAddress, String toAddress, int amount);
        
    /** 
     * Requires the block to be full of transactions.
     * Adds the current block to the blockchain.  
     * This method should execute the transactions and update the balances of the addresses involved. 
     * If some transaction fail to complete the transaction should be marked as reverted and the balance 
     * of the involved addresses should be not affected. 
     * @throws IllegalStateException if block is not full
     */ 
    void addBlock();

    /**
     * Retrieves a block by index
     * @param index position in chain (0-based)
     * @return the Block at specified index
     * @throws IndexOutOfBoundsException if index is invalid
     */
    Block getBlock(int index);
    
    /**
     * Returns number of blocks in blockchain
     * @return block count
     */
    int size();
    
    /**
     * Returns the most recently added block
     * @return last block, or null if empty
     */
    Block getLastBlock();
    
    /**
     * Returns all blocks in storage order
     * @return list of blocks
     */
    Sequence<Block> getBlocks();
    
    /**
     * Gets current balance of an address
     * @param address the address to check
     * @return balance of the address
     */
    int getBalance(String address);
}