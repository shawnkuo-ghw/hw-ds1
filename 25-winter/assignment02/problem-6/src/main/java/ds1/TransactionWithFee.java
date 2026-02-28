package ds1;
/**
 * TransactionWithFee.java
 * This class represents a transaction that includes a fee.
 * It extends the basic Transaction class and adds a fee attribute.
 * The fee is used to prioritize transactions in the blockchain.
 */

public class TransactionWithFee extends Transaction {

    public TransactionWithFee(String from, String to, int amount, int fee) {
        // TODO Auto-generated constructor stub
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public int compareTo(Transaction o) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'compareTo'");
    }

}