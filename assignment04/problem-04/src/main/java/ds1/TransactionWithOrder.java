package ds1;
/** 
 * TransactionWithOrder.java
 * This class represents a transaction that includes an order attribute.
 * It extends the TransactionWithFee class and adds an order attribute.
 * The order is used as a secondary priority criterion in the blockchain.
 */
public class TransactionWithOrder extends TransactionWithFee {
    private final int order;

    // constructor from Transaction parameters plus order
    public TransactionWithOrder(String fromAddress, String toAddress, int amount, int fee, int order) {
        super(fromAddress, toAddress, amount, fee);
        this.order = order;
    }

    public int getOrder() {
        return order;
    }

    @Override
    public int compareTo(Transaction o) {
        if (o instanceof TransactionWithOrder) {
            TransactionWithOrder two = (TransactionWithOrder) o;
            // First compare by fee (higher fee = higher priority)
            // float priority2 = 1/(float)two.fee + 1/two.order;
            // float priority1 = 1/(float)this.fee + 1/this.order;
            double priority1 = this.fee + 1.0/this.order;
            double priority2 = two.fee + 1.0/two.order;
            // // If fees are equal, compare by order (lower order = higher priority)
            return Double.compare(priority1, priority2);
        } else {
            System.out.println("Fallback to base class comparison for Transaction");
            // Fallback to base class comparison if not TransactionWithOrder
            return super.compareTo(o);
        }
    }

}
