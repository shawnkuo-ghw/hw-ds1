package ds1;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BlockchainTest {
    // Create tests for Blockchain class here. Respect the signatures of its
    // methods:
    // Use information from Block.java and Transaction.java as needed as well as
    // SBlockchain.java and Blockchain.java for context.
    // you can use repOK to verify the internal consistency of the blockchain after
    // operations.

    @Test
    void testProcessTransactionAndAddBlock() {
        SBlockchain blockchain = new SBlockchain(2,1000); // 2 transactions per block
        // Load some balances this would generate the genesis block and first block (2 blocks)
        blockchain.processTransaction("0", "A", 50);
        blockchain.processTransaction("0", "C", 50);
        // This would create balances for A and C and add transactions to the first block
        // There will be a second block created already but empty (3 blocks total)
        // Now process transactions
        blockchain.processTransaction("A", "B", 50);
        assertEquals(1, blockchain.getLastBlock().getTransactionCount(), "Current block should have 1 transaction");
        blockchain.processTransaction("C", "D", 30); // This should fill the block and add it
        // New block should be created (4 blocks total )
        // Verify
        assertEquals(4, blockchain.size(), "Blockchain should have 4 blocks after adding full block");
        assertEquals(0, blockchain.getLastBlock().getTransactionCount(),
                "New current block should have 0 transactions");
        // Check balances
        assertEquals(0, blockchain.getBalance("A"), "Balance of A should be 0");
        assertEquals(50, blockchain.getBalance("B"), "Balance of B should be 50");
        assertEquals(20, blockchain.getBalance("C"), "Balance of C should be 20");
        assertEquals(30, blockchain.getBalance("D"), "Balance of D should be 30");
        assertTrue(blockchain.repOK(), "Blockchain should be in a valid state");
    }

    @Test
    void testUnfinishedBlockDoesnotAffectBalance() {
        SBlockchain blockchain = new SBlockchain(2,1000); // 2 transactions per block
        // Load some balances
        blockchain.processTransaction("0", "A", 50);
        blockchain.processTransaction("0", "C", 50);
        // Now process transactions
        blockchain.processTransaction("A", "B", 50);
        assertEquals(1, blockchain.getLastBlock().getTransactionCount(), "Current block should have 1 transaction");
        assertEquals(3, blockchain.size(), "Blockchain should have 3 blocks after adding full block");
        assertEquals(1, blockchain.getLastBlock().getTransactionCount(), "New current block should have 1 transactions");
        // Check balances
        assertEquals(50, blockchain.getBalance("A"), "Balance of A should be 0");
        assertEquals(0, blockchain.getBalance("B"), "Balance of B should be 50");
        assertEquals(50, blockchain.getBalance("C"), "Balance of C should be 50");
        assertEquals(0, blockchain.getBalance("D"), "Balance of D should be 0");
        assertTrue(blockchain.repOK(), "Blockchain should be in a valid state");
    }

   @Test
    void testGetBalanceNotReverted() {
        SBlockchain blockchain = new SBlockchain(2,1000);
         // Load some balances
        blockchain.processTransaction("0", "A", 70);
        blockchain.processTransaction("0", "C", 50);
        // This will add a block of non-reverted transactions
        blockchain.processTransaction("A", "B", 50);
        blockchain.processTransaction("C", "D", 30); // Block added
        assertEquals(20, blockchain.getBalance("A"), "Balance of A should be 20");
        assertEquals(50, blockchain.getBalance("B"), "Balance of B should be 50");
        assertEquals(20, blockchain.getBalance("C"), "Balance of C should be 20");
        assertEquals(30, blockchain.getBalance("D"), "Balance of D should be 30");
        
        assertTrue(blockchain.repOK(), "Blockchain should be in a valid state");

    }

    @Test
    void testGetBalanceReverted() {
        SBlockchain blockchain = new SBlockchain(2,1000);
         // Load some balances
        blockchain.processTransaction("A", "B", 50);
        blockchain.processTransaction("C", "D", 30); // Block added
        // All transactions should be reverted due to insufficient funds
        assertEquals(0, blockchain.getBalance("A"), "Balance of A should be 0");
        assertEquals(0, blockchain.getBalance("B"), "Balance of B should be 0");
        assertEquals(0, blockchain.getBalance("C"), "Balance of C should be 0");
        assertEquals(0, blockchain.getBalance("D"), "Balance of D should be 0");

        assertTrue(blockchain.repOK(), "Blockchain should be in a valid state");
    }

    // Additional tests can be added here to cover more scenarios, use repOK, and
    // edge cases.
    @Test
    void testTransactionProcessing() {
        SBlockchain blockchain = new SBlockchain(2, 1000);
        blockchain.processTransaction("A", "B", 50);
        blockchain.processTransaction("C", "D", 30);
        assertTrue(blockchain.repOK(), "Blockchain should be in a valid state");
    }

    @Test
    void testInvalidTransaction() {
        SBlockchain blockchain = new SBlockchain(2, 1000);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            blockchain.processTransaction("A", "B", -10);
        });
        String expectedMessage = "Amount must be positive";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage), "Exception message should indicate invalid amount");
        blockchain.processTransaction("A", "B", 10);
        assertTrue(blockchain.repOK(), "Blockchain should be in a valid state");
    }
}