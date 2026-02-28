package ds1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class BlockTest { 
     // Create tests for Block class here. Respect the signatures of its methods:
     // Use information from Block.java and Transaction.java as needed as well as SBlockchain.java and Blockchain.java for context.
     
    @Test
    void testAddTransactionAndIsFull() {
        Block block = new Block("0", 2, 1); // previousHash="0", transactionsPerBlock=2, blockNumber=1
        TransactionWithFee t1 = new TransactionWithFee("1", "2", 50,0);
        TransactionWithFee t2 = new TransactionWithFee("3", "4", 30,0);     
        block.addTransaction(t1);
        block.addTransaction(t2);
        assertTrue(block.isFull(), "Block should be full after adding 2 transactions");
    }   
    @Test
    void testGetFirstTransaction() {
        Block block = new Block("0", 2, 1); // previousHash="0", transactionsPerBlock=2, blockNumber=1
        TransactionWithFee t1 = new TransactionWithFee("1", "2", 50, 1);
        block.addTransaction(t1);
        assertEquals(t1, block.getFirstTransaction(), "First transaction should be t1");
        TransactionWithFee t2 = new TransactionWithFee("3", "4", 30, 2);
        block.addTransaction(t2);
        assertEquals(t2, block.getFirstTransaction(), "First transaction should still be t2");
        TransactionWithFee[] transactions = (TransactionWithFee[]) block.getTransactions();
        assertEquals(2, transactions.length, "There should be 2 transactions in the block");
        assertEquals(t1, transactions[1], "Second transaction should be t1");
    }    
    @Test
    void testRemoveTransaction() {
        Block block =   new Block("0", 2, 1);
        TransactionWithFee t1 = new TransactionWithFee("1", "2", 50,2);
        TransactionWithFee t2 = new TransactionWithFee("3", "4", 30,1);
        block.addTransaction(t1);
        block.addTransaction(t2);
        TransactionWithFee[] transactions = (TransactionWithFee[]) block.getTransactions();
        assertEquals(2, transactions.length, "There should be 2 transactions in the block");
        assertEquals(t1, transactions[0], "First transaction should be t1");
        assertEquals(t2, transactions[1], "Second transaction should be t2");

    }
    // revert transaction test
    @Test
    void testRevertTransaction() {
        Block block = new Block("0", 2, 1);
        TransactionWithFee t1 = new TransactionWithFee("1", "2", 50,0);
        block.addTransaction(t1);
        t1.revert();
        TransactionWithFee revertedT1 = (TransactionWithFee) block.getFirstTransaction();
        TransactionWithFee[] transactions = (TransactionWithFee[]) block.getTransactions();
        assertEquals(1, transactions.length, "There should be 1 transaction in the block");
        assertTrue(revertedT1.isReverted(), "Transaction t1 should be marked as reverted");
    }

    // Test computeTxHash
    @Test
    void testComputeTxHash() {
        Block block = new Block("0", 2, 1);
        TransactionWithFee t1 = new TransactionWithFee("1", "2", 50,0);
        TransactionWithFee t2 = new TransactionWithFee("3", "4", 30,0);
        block.addTransaction(t1);
        block.addTransaction(t2);
        block.setStateRootHash("stateRootHash");
        String computedHash = block.computeAndSetBlockHash();
        assertEquals("d31b8e0", computedHash);
    }
}
