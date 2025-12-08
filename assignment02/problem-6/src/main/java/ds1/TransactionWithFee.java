package ds1;
/**
 * TransactionWithFee.java
 * This class represents a transaction that includes a fee.
 * It extends the basic Transaction class and adds a fee attribute.
 * The fee is used to prioritize transactions in the blockchain.
 */

public class TransactionWithFee extends Transaction
{
    private final int fee;
    public TransactionWithFee(String from, String to, int amount, int fee) {
        super(from, to, amount);
        this.fee = fee;
    }

    @Override
    public int compareTo(Transaction o) {
        if (!(o instanceof TransactionWithFee)) { return super.compareTo(o); }
        TransactionWithFee other = (TransactionWithFee) o;
        return Integer.compare(this.fee, other.fee);
    }
}