// Block.java

package ds1;
import ds1.utils.Queue;
import ds1.utils.LinkedListQueue;
import java.util.NoSuchElementException;

public class Block
{   
    /*
     * Fields
     */
    private final int blockHash;
    private final int previousHash;
    private final int blockNumber;
    private final int transactionsPerBlock;
    private Queue transactions;
    
    /*
     * Constructor
     */
    public Block(int previousHash, int transactionsPerBlock, int blockNumber)
    {
        this.previousHash = previousHash;
        this.blockHash = previousHash + 1;
        this.blockNumber = blockNumber;
        this.transactionsPerBlock = transactionsPerBlock;
        transactions = new LinkedListQueue(); // instantiate transactions
        if ( !repOK() ) {
            throw new IllegalStateException("Block(): repOK() is false.");
        }
    }
    
    /*
     * Operations
     */ 
    public void addTransaction(Transaction t)
    {
        if ( isFull() ) {
            throw new IllegalStateException("Block.addTransaction(): block is full.");
        }
        transactions.enqueue(t);
    }
    
    public Transaction getFirstTransaction()
    {
        if ( transactions.isEmpty() ) {
            throw new NoSuchElementException("Block.getFirstTransaction(): transactions is empty.");
        }
        return transactions.front();
    }
    
    public void removeTransaction(int index)
    {
        if ( transactions.isEmpty() ) {
            throw new NoSuchElementException("Block.removeTransaction(): transactions is empty.");
        }
        transactions.remove(index);
    }
    
    public boolean isFull() {
        return transactions.size() == transactionsPerBlock;
    }
    
    /*
     * Getters
     */
    public int getBlockHash() {
        return blockHash;
    }
    public int getPreviousHash() {
        return previousHash;
    }
    public int getBlockNumber() { 
        return blockNumber;
    }
    public int getTransactionCount() { 
        return transactions.size();
    }
    public Transaction[] getTransactions() {
        return transactions.toArray();
    }
    
    /*
     * Checker
     */
    public boolean repOK() {
        return previousHash + 1 == blockHash;
    }
    
    /*
     * toString for debugging
     */
    @Override
    public String toString()
    {
        String strRep = "Block #" + blockNumber + ":\n";
        strRep += "Transactions Number: " + transactionsPerBlock + "\n";
        Transaction[] transactionsArray = transactions.toArray();
        for (int i = 0; i < transactionsArray.length; i++) {
            strRep += transactionsArray[i].toString();
        }
        return strRep + "\n";
    }
}