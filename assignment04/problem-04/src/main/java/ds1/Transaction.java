package ds1;

/** 
 * Transaction.java
 * This abstract class represents a transaction between two addresses with a specified amount.
 * It serves as a base class for more specialized transaction types.
 * It includes common attributes and methods for transactions.
 * It implements Comparable to allow for transaction prioritization.
 */
public abstract class Transaction  implements Comparable<Transaction> {
    protected String fromAddress;
    protected String toAddress;
    protected int amount;
    protected boolean reverted;

    // Getters
    public String getFromAddress() { return fromAddress; }
    public String getToAddress() { return toAddress; }
    public int getAmount() { return amount; }
    public boolean isReverted() { return reverted; }
    
    public void revert() { this.reverted = true; }

    public String hash() {
        return ds1.util.HashUtils.hash(this.toString());
    }   
    // toString for debugging
    @Override
    public String toString() {
        return "Transaction[from=" + fromAddress + ", to=" + toAddress + ", amount=" + amount + ", reverted=" + reverted + "]";
    }

}