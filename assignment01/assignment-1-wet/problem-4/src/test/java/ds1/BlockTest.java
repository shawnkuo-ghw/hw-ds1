package ds1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class BlockTest { 
    // Create tests for Block class here. Respect the signatures of its methods:
    // Use information from Block.java and Transaction.java as needed as well as SBlockchain.java and Blockchain.java for context.
     
    @Test
    void testAddTransactionAndIsFull() {
        Block block = new Block(0, 2, 1); // previousHash=0, transactionsPerBlock=2, blockNumber=1
        Transaction t1 = new Transaction("A", "B", 50);
        Transaction t2 = new Transaction("C", "D", 30);     
        block.addTransaction(t1);
        block.addTransaction(t2);
        assertTrue(block.isFull(), "Block should be full after adding 2 transactions");
    }   
    
    @Test
    void testGetFirstTransaction() {
        Block block = new Block(0, 2, 1);
        Transaction t1 = new Transaction("A", "B", 50);
        block.addTransaction(t1);
        assertEquals(t1, block.getFirstTransaction(), "First transaction should be t1");
        Transaction t2 = new Transaction("C", "D", 30);
        block.addTransaction(t2);
        assertEquals(t1, block.getFirstTransaction(), "First transaction should still be t1");
        block.removeTransaction(0); // Remove t1
        assertEquals(t2, block.getFirstTransaction(), "First transaction should now be t2");
    }    
    
    @Test
    void testRemoveTransaction() {
        Block block = new Block(0, 2, 1);
        Transaction t1 = new Transaction("A", "B", 50);
        Transaction t2 = new Transaction("C", "D", 30);
        block.addTransaction(t1);
        block.addTransaction(t2);
        block.removeTransaction(0); // Remove t1
        assertEquals(t2, block.getFirstTransaction(), "First transaction should be t2 after removing t1");
    }
    
    // revert transaction test
    @Test
    void testRevertTransaction() {
        Block block = new Block(0, 2, 1);
        Transaction t1 = new Transaction("A", "B", 50);
        block.addTransaction(t1);
        t1.revert();
        Transaction revertedT1 = block.getFirstTransaction();
        assertTrue(revertedT1.isReverted(), "Transaction t1 should be marked as reverted");
    }
}