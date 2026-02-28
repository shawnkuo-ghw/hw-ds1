package ds1;
/** 
 * TransactionWithOrder.java
 * This class represents a transaction that includes an order attribute.
 * It extends the TransactionWithFee class and adds an order attribute.
 * The order is used as a secondary priority criterion in the blockchain.
 */
public class TransactionWithOrder extends TransactionWithFee
{
    // Field
    private final int order;

    // Constructor
    public TransactionWithOrder(String from, String to, int amount, int fee, int order) {
        super(from, to, amount, fee);
        this.order = order;
    }

    // Getter
    public int getOrder() { return this.order; }

    // Priority calculator
    private static double PRIORITY(TransactionWithOrder t) { return t.getFee() + 1 / (double) t.getOrder(); }

    // Comparable interface
    @Override
    public int compareTo(Transaction o) {
        if ( !(o instanceof TransactionWithOrder) ) throw new IllegalArgumentException(
            "TransactionWithOrder.compareTo: the type of o is not TransactionWithOrder"
        );
        TransactionWithOrder other = (TransactionWithOrder) o;
        double thisPriority = PRIORITY(this);
        double otherPriority = PRIORITY(other);
        return Double.compare(thisPriority, otherPriority);
    }
}