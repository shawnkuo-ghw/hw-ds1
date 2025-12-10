package ds1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


class BlockchainTest {
    @Test
    void testProcessTransactionAndAddBlock() {
        ABlockchain blockchain = new ABlockchain(2,1000); // 2 transactions per block
        // Load some balances this would generate the genesis block and first block (2 blocks)
        blockchain.requestTransaction("0", "A", 51,0);
        blockchain.requestTransaction("0", "C", 52,0);
        // This would create balances for A and C and add transactions to the first block
        // There will be a second block created already but empty (3 blocks total)
        // Now process transactions
        boolean mined = blockchain.mineBlock();
        blockchain.requestTransaction("A", "B", 50,1);

        mined = blockchain.mineBlock();
        assertFalse(mined, "Block should not be mined as current block is not full");
        assertEquals(1, blockchain.getLastBlock().getTransactionCount(), "Current block should have 1 transaction");

        blockchain.requestTransaction("C", "D", 30,2); // This should fill the block and add it
        // New block should be created (4 blocks total )
        mined = blockchain.mineBlock();
        assertTrue(mined, "Block should be mined after adding transaction");

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
        ABlockchain blockchain = new ABlockchain(2,1000); // 2 transactions per block
        // Load some balances
        blockchain.requestTransaction("0", "A", 50,0);
        blockchain.requestTransaction("0", "C", 50,0);
        // This would create balances for A and C and add transactions to the first block
        boolean mined = blockchain.mineBlock();
        // Now process transactions
        blockchain.requestTransaction("A", "B", 50,1);
        mined = blockchain.mineBlock();
        assertFalse(mined, "Block should not be mined as current block is not full");
        assertEquals(1, blockchain.getLastBlock().getTransactionCount(), "Current block should have 1 transaction");
        assertEquals(3, blockchain.size(), "Blockchain should have 3 blocks after adding full block");
        assertEquals(1, blockchain.getLastBlock().getTransactionCount(),
                "New current block should have 1 transactions");
        // Check balances
        assertEquals(50, blockchain.getBalance("A"), "Balance of A should be 0");
        assertEquals(0, blockchain.getBalance("B"), "Balance of B should be 50");
        assertEquals(50, blockchain.getBalance("C"), "Balance of C should be 50");
        assertEquals(0, blockchain.getBalance("D"), "Balance of D should be 0");
        assertTrue(blockchain.repOK(), "Blockchain should be in a valid state");
    }

   @Test
    void testGetBalanceNotReverted() {
        ABlockchain blockchain = new ABlockchain(2,1000);
         // Load some balances
        blockchain.requestTransaction("0", "A", 70,0);
        blockchain.requestTransaction("0", "C", 50,10);
        // This will add a block of non-reverted transactions
        blockchain.requestTransaction("A", "B", 50,100);
        blockchain.requestTransaction("C", "D", 30,1); 

        // transaction pool should have 4 transactions now
        assertEquals(4, blockchain.getTransactionPoolSize(),
                "Current block should have 4 transactions");

        boolean mined = blockchain.mineBlock();
        assertTrue(mined, "Block should be mined after adding full block");
        // Only process the first two transactions with more fee 0->C and A->B
        // The second two should be reverted due to insufficient funds
        assertEquals(50, blockchain.getBalance("C"), "Balance of C should be 50");
        assertEquals(0, blockchain.getBalance("A"), "Balance of A should be 0");
        mined = blockchain.mineBlock();
        assertTrue(mined, "Block should be mined after adding full block");

        assertEquals(70, blockchain.getBalance("A"), "Balance of A should be 70");
        assertEquals(0, blockchain.getBalance("B"), "Balance of B should be 0");
        assertEquals(19, blockchain.getBalance("C"), "Balance of C should be 50-20-1");
        assertEquals(30, blockchain.getBalance("D"), "Balance of D should be 30");
        
        
        assertTrue(blockchain.repOK(), "Blockchain should be in a valid state");

    }

    @Test
    void testGetBalanceReverted() {
        ABlockchain blockchain = new ABlockchain(2,1000);
         // Load some balances
        blockchain.requestTransaction("A", "B", 50,1);
        blockchain.requestTransaction("C", "D", 30,2);
        boolean mined = blockchain.mineBlock();
        assertTrue(mined, "Block should be mined after adding full block");

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
        ABlockchain blockchain = new ABlockchain(2, 1000);
        blockchain.requestTransaction("A", "B", 50,1);
        blockchain.requestTransaction("C", "D", 30,2);
        assertTrue(blockchain.repOK(), "Blockchain should be in a valid state");
    }

    @Test
    void testInvalidTransaction() {
        ABlockchain blockchain = new ABlockchain(2, 1000);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            blockchain.requestTransaction("A", "B", -10, 1);
        });
        String expectedMessage = "Amount must be positive";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage), "Exception message should indicate invalid amount");
        blockchain.requestTransaction("A", "B", 10, 1);
        assertTrue(blockchain.repOK(), "Blockchain should be in a valid state");
    }
    // Create new test for the ABlockchain class covering new methods and representation invariant.
    @Test
    // Explain this test:
    // This test verifies the functionality of the ABlockchain class, specifically
    // the processTransactionWithFee and mineBlock methods. It checks that transactions
    // are processed correctly, that the balances are updated appropriately, and that
    // the counts of successful and reverted transactions, as well as returned fees,
    // are tracked accurately. Finally, it uses the repOK method to ensure the internal
    // consistency of the blockchain after these operations.
    void testABlockchainMethods() {
        ABlockchain blockchain = new ABlockchain(2,1000); // 2 transactions per block
        // Load some balances this would generate the genesis block and first block (2 blocks)
        // Transactions has same fees but different orders 
        blockchain.requestTransaction("0", "A", 50,1);
        blockchain.requestTransaction("0", "C", 50,1);
        // This would create balances for A and C and add transactions to the first block
        // There will be a second block created already but empty (3 blocks total)
        // Now process transactions
        blockchain.requestTransaction("A", "B", 40,1);
        // mine only one block, but the next block is not full yet
        boolean mined = blockchain.mineBlock();   
        assertEquals(0, blockchain.getLastBlock().getTransactionCount(), "Current block should have 0 transaction");
        // only one block mined as current block is not full
        mined = blockchain.mineBlock();
        assertFalse(mined, "Block should not be mined as current block is not full");
        assertEquals(1, blockchain.getLastBlock().getTransactionCount(), "Current block should have 1 transaction");
        // This transaction should fill the block, and will be processed before A->B due to higher fee
        blockchain.requestTransaction("C", "D", 30,2); // This should fill the block and add it
        mined = blockchain.mineBlock();
        assertTrue(mined, "Block should be mined after adding transaction");
        // New block should be created (4 blocks total )
        // Verify
        assertEquals(4, blockchain.size(), "Blockchain should have 4 blocks after adding full block");
        assertEquals(0, blockchain.getLastBlock().getTransactionCount(),
                "New current block should have 0 transactions");
        // Check balances
        assertEquals(9, blockchain.getBalance("A"), "Balance of A should be 10-1");
        assertEquals(40, blockchain.getBalance("B"), "Balance of B should be 51-1");
        assertEquals(18, blockchain.getBalance("C"), "Balance of C should be 20-2");
        assertEquals(30, blockchain.getBalance("D"), "Balance of D should be 30");
        assertTrue(blockchain.repOK(), "Blockchain should be in a valid state");

    }

    // add test for reverted transactions count and returned fees
    @Test
    void testRevertedTransactionsAndReturnedFees() {
        ABlockchain blockchain = new ABlockchain(2,1000); // 2 transactions per block
        // Load some balances this would generate the genesis block and first block (2 blocks)
        blockchain.requestTransaction("0", "A", 50,0);
        blockchain.requestTransaction("0", "C", 50,0);
        // This would create balances for A and C and add transactions to the first block
        boolean mined = blockchain.mineBlock();
        // Now process transactions
        blockchain.requestTransaction("A", "B", 60,4); // This will be reverted
        blockchain.requestTransaction("C", "D", 30,2); // This will be processed
        mined = blockchain.mineBlock();
        assertTrue(mined, "Block should be mined after adding full block");
        assertEquals(1, blockchain.getRevertedTransactionsCount(), "There should be 1 reverted transaction");
        assertEquals(3, blockchain.getSuccessfulTransactionsCount(), "There should be 3 successful transactions");
        assertEquals(2, blockchain.getReturnedFees(), "Returned fees should be 2");
        assertTrue(blockchain.repOK(), "Blockchain should be in a valid state");    
    }

    // test for getBlockByNumber
    @Test
    void testGetBlockByNumber() {
        ABlockchain blockchain = new ABlockchain(2,1000); // 2 transactions per block
        // Load some balances this would generate the genesis block and first block (2 blocks)
        blockchain.requestTransaction("0", "A", 50,0);
        blockchain.requestTransaction("0", "C", 50,0);
        // This would create balances for A and C and add transactions to the first block
        boolean mined = blockchain.mineBlock();
        // Now process transactions
        blockchain.requestTransaction("A", "B", 40,1);
        mined = blockchain.mineBlock();
        assertFalse(mined, "Block should not be mined as current block is not full");

        // Get block by number
        Block block1 = blockchain.getBlockByNumber(1);
        assertEquals(1, block1.getBlockNumber(), "Block number should be 1");
        // check transactions in block1
        TransactionWithFee[] transactionsBlock1 = block1.getTransactions();
        assertEquals(2, transactionsBlock1.length, "Block 1 should have 2 transactions");

        Block block2 = blockchain.getBlockByNumber(2);
        assertEquals(2, block2.getBlockNumber(), "Block number should be 2");
        TransactionWithFee[] transactionsBlock2 = block2.getTransactions();
        assertEquals(1, transactionsBlock2.length, "Block 2 should have 1 transaction");
        assertTrue(blockchain.repOK(), "Blockchain should be in a valid state");
    }

    // test missing methods in ABlockchain
    @Test
    void testGetTransactionPoolSize() {
        ABlockchain blockchain = new ABlockchain(2,1000); // 2 transactions per block
        blockchain.requestTransaction("0", "A", 50,1);
        blockchain.requestTransaction("0", "C", 50,2);
        assertEquals(2, blockchain.getTransactionPoolSize(), "Transaction pool size should be 2");
        blockchain.requestTransaction("A", "B", 40,3);
        assertEquals(3, blockchain.getTransactionPoolSize(), "Transaction pool size should be 3");
        assertTrue(blockchain.repOK(), "Blockchain should be in a valid state");    
    }

   //edge cases

    @Test
    void testRequestTransactionInvalidAddressesNegativeTest() {
        ABlockchain blockchain = new ABlockchain(2, 1000);
        // fromAddress is null
        assertThrows(IllegalArgumentException.class, () ->
            blockchain.requestTransaction(null, "B", 10, 1));
        // toAddress is empty
        assertThrows(IllegalArgumentException.class, () ->
            blockchain.requestTransaction("A", "", 10, 1));
    }

    @Test
    void testRequestTransactionNegativeFeeNegativeTest() {
        ABlockchain blockchain = new ABlockchain(2, 1000);
        assertThrows(IllegalArgumentException.class, () ->
            blockchain.requestTransaction("A", "B", 10, -1));
    }

    @Test
    void testMineBlockEmptyTransactionPoolNegativeTest() {
        ABlockchain blockchain = new ABlockchain(2, 1000);
        int sizeBefore = blockchain.size();
        boolean mined = blockchain.mineBlock();
        assertFalse(mined);
        assertEquals(sizeBefore, blockchain.size());
        assertTrue(blockchain.repOK());
    }

    @Test
    void testGetBlockByNumberInvalidIndicesNegativeTest() {
        ABlockchain blockchain = new ABlockchain(2, 1000);
        assertThrows(IndexOutOfBoundsException.class, () -> blockchain.getBlockByNumber(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> blockchain.getBlockByNumber(9999));
    }
}