package ds1;
/**
 * TransactionWithFee.java
 * This class represents a transaction that includes a fee.
 * It extends the basic Transaction class and adds a fee attribute.
 * The fee is used to prioritize transactions in the blockchain.
 */

public class TransactionWithFee extends Transaction {
    protected final String fromAddress;
    protected final String toAddress;
    protected final int amount;
    protected boolean reverted;
    protected int fee;
    
    public TransactionWithFee(String fromAddress, String toAddress, int amount, int fee) {
        if (amount <= 0 || fee < 0 || fromAddress == null || toAddress == null) {
            throw new IllegalArgumentException("Invalid transaction parameters");
        }
        this.fromAddress = fromAddress;
        this.toAddress = toAddress;
        this.amount = amount;
        this.reverted = false;
        this.fee = fee;
    }
    
    // Getters
    public String getFromAddress() { return fromAddress; }
    public String getToAddress() { return toAddress; }
    public int getAmount() { return amount; }
    public boolean isReverted() { return reverted; }
    public int getFee() { return fee; }
    
    public void revert() { this.reverted = true; }

    // toString for debugging
    @Override
    public String toString() {
        return "Transaction[from=" + fromAddress + ", to=" + toAddress + ", amount=" + amount + ", reverted=" + reverted + "]";
    }

    @Override
    public int compareTo(Transaction o) {
        if(o instanceof TransactionWithFee == false) {
            return 1; // consider TransactionWithFee greater than base Transaction
        }
        TransactionWithFee other = (TransactionWithFee) o;
        // use fee for comparison (higher fee = higher priority)
        return Integer.compare(this.fee, other.fee);
    }
}