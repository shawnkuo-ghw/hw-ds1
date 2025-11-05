package ds1;
// SBlockChain.java

import ds1.utils.Sequence;

/** 
 * Implementation of a simple blockchain using sequences and balances.
 * Each block contains a set of transactions, and the blockchain maintains
 * a balance for each address.
 * The blockchain supports processing transactions, adding new blocks,
 * and verifying its integrity through the repOK method.
 * Do not forget to implement all methods from the Blockchain interface.
 * Do not forget the genesis block creation.
 * Respect the runtime complexities indicated in the assignment. 
*/

public class SBlockchain implements Blockchain {
    
    public SBlockchain(int transactionsPerBlock, int initialBalance) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
    @Override
    public void processTransaction(String fromAddress, String toAddress, int amount) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    @Override
    public void addBlock() {
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    // Other interface methods implementation...
    // Runtime complexity not relevant for repOK
    public boolean repOK() {
        // 1) Check: balance equals sum of all non-reverted transactions
        // 2) Check: correct hash linking (previousHash == currentHash - 1)
        // 3) Check: all blocks have same transaction capacity
        // 4) Check: block numbers are strictly increasing
        throw new UnsupportedOperationException("Not implemented yet");
    }


    @Override
    public Block getBlock(int index) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public Block getLastBlock() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public Sequence<Block> getBlocks() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public int getBalance(String address) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}