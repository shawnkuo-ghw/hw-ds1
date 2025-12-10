package ds1;

/**
 * TransactionWithFee.java
 * This class represents a transaction that includes a fee.
 * It extends the basic Transaction class and adds a fee attribute.
 * The fee is used to prioritize transactions in the blockchain.
 */
public class TransactionWithFee extends Transaction
{
    // Fields
    private final int fee;

    // Constructor
    public TransactionWithFee(String from, String to, int amount, int fee) {
        super(from, to, amount);
        this.fee = fee;
    }

    // Getter
    public int getFee() { return this.fee; }

    // Comparable interface
    @Override
    public int compareTo(Transaction o) {
        if ( !(o instanceof TransactionWithFee) ) {
            throw new IllegalArgumentException(
                "TransactionWithFee.compareTo(o): the type of o is not TransactionWithFee."
            );
        }
        TransactionWithFee other = (TransactionWithFee) o;
        return Integer.compare(this.fee, other.fee);
    }
}