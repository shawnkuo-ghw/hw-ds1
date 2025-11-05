package ds1;
/** 
 * Class representing a transaction in the blockchain.
 * Read the information about the methods carefully in the assignment.
 * Remember to mark transactions as reverted when they fail.
 */
public class Transaction {
    
    public Transaction(String fromAddress, String toAddress, int amount) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    // Getters
    public String getFromAddress() { 
        throw new UnsupportedOperationException("Not implemented yet");
    }
    public String getToAddress() { 
        throw new UnsupportedOperationException("Not implemented yet");
    }
    public boolean isReverted() { 
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public void revert() { 
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // toString for debugging
    @Override
    public String toString() {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}