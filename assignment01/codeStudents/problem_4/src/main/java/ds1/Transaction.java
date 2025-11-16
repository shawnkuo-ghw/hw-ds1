// Transaction.java

package ds1;

/** 
 * Class representing a transaction in the blockchain.
 * Read the information about the methods carefully in the assignment.
 * Remember to mark transactions as reverted when they fail.
 */
public class Transaction
{
    /*
     * Fields
     */
    private final String fromAddress;
    private final String toAddress;
    private final int amount;
    private boolean reverted;

    /***************
     * Constructor *
     ***************/
    public Transaction(String fromAddress, String toAddress, int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Transaction(): amount should be greater than zero.");
        }
        else if (fromAddress == null || toAddress == null) {
            throw new IllegalArgumentException("Address should not be null");
        }
        else if (fromAddress == "" || toAddress == "") {
            throw new IllegalArgumentException("Address should not be empty");
        }

        this.fromAddress = fromAddress;
        this.toAddress = toAddress;
        this.amount = amount;
        this.reverted = false;
    }
    
    /**************
     * Operations *
     **************/
    public void revert() { 
        reverted = true;
    }

    /***********
     * Getters *
     ***********/
    public String getFromAddress() { 
        return fromAddress;
    }
    public String getToAddress() { 
        return toAddress;
    }
    public int getAmount() {
        return amount;
    }
    public boolean isReverted() { 
        return reverted;
    }

    // toString for debugging
    @Override
    public String toString() {
        return "[transaction: from " + fromAddress + " to " + toAddress + " by " + amount + "; reverted: " + reverted + "]\n";
    }
}