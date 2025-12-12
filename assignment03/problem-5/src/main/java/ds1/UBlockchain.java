package ds1;

/** 
 * UBlockchain.java
 * This class represents an unoptimized blockchain implementation.
 * It extends the abstract ABlockchain class and provides concrete implementations
 * for creating the genesis block, processing blocks, and creating new blocks.
 * Students are expected to implement the missing methods.
 * Can use the inherited methods from ABlockchain for common functionality.
 * Or override them if needed.
 */

public class UBlockchain extends ABlockchain {
    // Add required fields here

    public UBlockchain(int transactionsPerBlock, int initialBalance) {
        super(transactionsPerBlock, initialBalance); 
    }

    @Override
    protected Block createGenesisBlock(int initialBalance) {
        // Should be implemented by students
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void processCurrentBlockAndStartNewBlock() {
        // Should be implemented by students
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    protected void processBlockTransactions() {
        // Should be implemented by students
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    protected void createNewBlock() {
        // Should be implemented by students
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // Mine a new block from the transaction pool
    // It is similar to addBlock but selects transactions from the pool
    @Override
    public boolean mineBlock() {
        return super.mineBlock();
    }

    @Override
    /** You can use part of old repOK and adapt it to the new structure
     *      
    **/
	public boolean repOK() {
        // Should be implemented by students
        throw new UnsupportedOperationException("Not implemented yet");
    }
    public String getStateMPTHash() {
        // Should be implemented by students
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
