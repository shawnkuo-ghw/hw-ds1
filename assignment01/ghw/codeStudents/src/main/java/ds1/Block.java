package ds1;
// Block.java

public class Block {
    
    public Block(int previousHash, int transactionsPerBlock, int blockNumber) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    public void addTransaction(Transaction t) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    public Transaction getFirstTransaction() {
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    public void  removeTransaction(int index) {
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
    
    public boolean repOK() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public Transaction[] getTransactions() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // toString for debugging
    @Override
    public String toString() {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}