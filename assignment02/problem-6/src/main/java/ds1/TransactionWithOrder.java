package ds1;
/** 
 * TransactionWithOrder.java
 * This class represents a transaction that includes an order attribute.
 * It extends the TransactionWithFee class and adds an order attribute.
 * The order is used as a secondary priority criterion in the blockchain.
 */
public class TransactionWithOrder extends TransactionWithFee {

    public TransactionWithOrder(String from, String to, int amount, int fee, int order) {
        super(from, to, amount, fee);
        // TODO Auto-generated constructor stub
        throw new UnsupportedOperationException("Not implemented yet");
    }
    @Override
    public int compareTo(Transaction o) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'compareTo'");
    }

}
