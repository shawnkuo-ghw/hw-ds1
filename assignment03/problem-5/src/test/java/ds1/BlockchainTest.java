package ds1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


class BlockchainTest {
    @Test
    void testProcessTransactionAndAddBlock() {
        UBlockchain blockchain = new UBlockchain(2,1000); // 2 transactions per block
        // Load some balances this would generate the genesis block and first block (2 blocks)
        blockchain.requestTransaction("0", "1", 51,0);
        blockchain.requestTransaction("0", "3", 52,0);
        // This would create balances for A and C and add transactions to the first block
        // There will be a second block created already but empty (3 blocks total)
        // Now process transactions
        boolean mined = blockchain.mineBlock();
        blockchain.requestTransaction("1", "2", 50,1);

        mined = blockchain.mineBlock();
        assertFalse(mined, "Block should not be mined as current block is not full");
        assertEquals(1, blockchain.getLastBlock().getTransactionCount(), "Current block should have 1 transaction");

        blockchain.requestTransaction("3", "4", 30,2); // This should fill the block and add it
        // New block should be created (4 blocks total )
        mined = blockchain.mineBlock();
        assertTrue(mined, "Block should be mined after adding transaction");

        // Verify
        assertEquals(4, blockchain.size(), "Blockchain should have 4 blocks after adding full block");
        assertEquals(0, blockchain.getLastBlock().getTransactionCount(),
        "New current block should have 0 transactions");
        // Check balances
        assertEquals(0, blockchain.getBalance("1"), "Balance of 1 should be 0");
        assertEquals(50, blockchain.getBalance("2"), "Balance of 2 should be 50");
        assertEquals(20, blockchain.getBalance("3"), "Balance of 3 should be 20");
        assertEquals(30, blockchain.getBalance("4"), "Balance of 4 should be 30");
        assertTrue(blockchain.repOK(), "Blockchain should be in a valid state");
    }

        @Test
    void testUnfinishedBlockDoesnotAffectBalance() {
        UBlockchain blockchain = new UBlockchain(2,1000); // 2 transactions per block
        // Load some balances
        blockchain.requestTransaction("0", "1", 50,0);
        blockchain.requestTransaction("0", "3", 50,0);
        // This would create balances for A and C and add transactions to the first block
        boolean mined = blockchain.mineBlock();
        // Now process transactions
        blockchain.requestTransaction("1", "2", 50,1);
        mined = blockchain.mineBlock();
        assertFalse(mined, "Block should not be mined as current block is not full");
        assertEquals(1, blockchain.getLastBlock().getTransactionCount(), "Current block should have 1 transaction");
        assertEquals(3, blockchain.size(), "Blockchain should have 3 blocks after adding full block");
        assertEquals(1, blockchain.getLastBlock().getTransactionCount(),
                "New current block should have 1 transactions");
        // Check balances
        assertEquals(50, blockchain.getBalance("1"), "Balance of 1 should be 0");
        assertEquals(0, blockchain.getBalance("2"), "Balance of 2 should be 50");
        assertEquals(50, blockchain.getBalance("3"), "Balance of 3 should be 50");
        assertEquals(0, blockchain.getBalance("4"), "Balance of 4 should be 0");
        assertTrue(blockchain.repOK(), "Blockchain should be in a valid state");
    }

   @Test
    void testGetBalanceNotReverted() {
        UBlockchain blockchain = new UBlockchain(2,1000);
         // Load some balances
        blockchain.requestTransaction("0", "1", 70,0);
        blockchain.requestTransaction("0", "3", 50,10);
        // This will add a block of non-reverted transactions
        blockchain.requestTransaction("1", "2", 50,100);
        blockchain.requestTransaction("3", "4", 30,1); 
        // transaction pool should have 4 transactions now
        assertEquals(4, blockchain.getTransactionPoolSize(),
                "Current block should have 4 transactions");

        boolean mined = blockchain.mineBlock();
        assertTrue(mined, "Block should be mined after adding full block");
        // Only process the first two transactions with more fee 0->C and A->B
        // The second two should be reverted due to insufficient funds
        assertEquals(50, blockchain.getBalance("3"), "Balance of 3 should be 50");
        assertEquals(0, blockchain.getBalance("1"), "Balance of 1 should be 0");
        mined = blockchain.mineBlock();
        assertTrue(mined, "Block should be mined after adding full block");

        assertEquals(70, blockchain.getBalance("1"), "Balance of 1 should be 70");
        assertEquals(0, blockchain.getBalance("2"), "Balance of 2 should be 0");
        assertEquals(19, blockchain.getBalance("3"), "Balance of 3 should be 50-20-1");
        assertEquals(30, blockchain.getBalance("4"), "Balance of 4 should be 30");
        
        
        assertTrue(blockchain.repOK(), "Blockchain should be in a valid state");

    }

    @Test
    void testGetBalanceReverted() {
        UBlockchain blockchain = new UBlockchain(2,1000);
         // Load some balances
        blockchain.requestTransaction("1", "2", 50,1);
        blockchain.requestTransaction("3", "4", 30,2);
        boolean mined = blockchain.mineBlock();
        assertTrue(mined, "Block should be mined after adding full block");

        // All transactions should be reverted due to insufficient funds
        assertEquals(0, blockchain.getBalance("1"), "Balance of 1 should be 0");
        assertEquals(0, blockchain.getBalance("2"), "Balance of 2 should be 0");
        assertEquals(0, blockchain.getBalance("3"), "Balance of 3 should be 0");
        assertEquals(0, blockchain.getBalance("4"), "Balance of 4 should be 0");

        assertTrue(blockchain.repOK(), "Blockchain should be in a valid state");
    }

    // Additional tests can be added here to cover more scenarios, use repOK, and
    // edge cases.
    @Test
    void testTransactionProcessing() {
        UBlockchain blockchain = new UBlockchain(2, 1000);
        blockchain.requestTransaction("1", "2", 50,1);
        blockchain.requestTransaction("3", "4", 30,2);
        assertTrue(blockchain.repOK(), "Blockchain should be in a valid state");
    }

    @Test
    void testInvalidTransaction() {
        UBlockchain blockchain = new UBlockchain(2, 1000);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            blockchain.requestTransaction("1", "2", -10, 1);
        });
        String expectedMessage = "Amount must be positive";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage), "Exception message should indicate invalid amount");
        blockchain.requestTransaction("1", "2", 10, 1);
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
        UBlockchain blockchain = new UBlockchain(2,1000); // 2 transactions per block
        // Load some balances this would generate the genesis block and first block (2 blocks)
        // Transactions has same fees but different orders 
        blockchain.requestTransaction("0", "1", 50,1);
        blockchain.requestTransaction("0", "3", 50,1);
        // This would create balances for A and C and add transactions to the first block
        // There will be a second block created already but empty (3 blocks total)
        // Now process transactions
        blockchain.requestTransaction("1", "2", 40,1);
        // mine only one block, but the next block is not full yet
        boolean mined = blockchain.mineBlock();   
        assertEquals(0, blockchain.getLastBlock().getTransactionCount(), "Current block should have 0 transaction");
        // only one block mined as current block is not full
        mined = blockchain.mineBlock();
        assertFalse(mined, "Block should not be mined as current block is not full");
        assertEquals(1, blockchain.getLastBlock().getTransactionCount(), "Current block should have 1 transaction");
        // This transaction should fill the block, and will be processed before A->B due to higher fee
        blockchain.requestTransaction("3", "4", 30,2); // This should fill the block and add it
        mined = blockchain.mineBlock();
        assertTrue(mined, "Block should be mined after adding transaction");
        // New block should be created (4 blocks total )
        // Verify
        assertEquals(4, blockchain.size(), "Blockchain should have 4 blocks after adding full block");
        assertEquals(0, blockchain.getLastBlock().getTransactionCount(),
                "New current block should have 0 transactions");
        // Check balances
        assertEquals(9, blockchain.getBalance("1"), "Balance of 1 should be 10-1");
        assertEquals(40, blockchain.getBalance("2"), "Balance of 2 should be 51-1");
        assertEquals(18, blockchain.getBalance("3"), "Balance of 3 should be 20-2");
        assertEquals(30, blockchain.getBalance("4"), "Balance of 4 should be 30");
        assertTrue(blockchain.repOK(), "Blockchain should be in a valid state");

    }

    // add test for reverted transactions count and returned fees
    @Test
    void testRevertedTransactionsAndReturnedFees() {
        UBlockchain blockchain = new UBlockchain(2,1000); // 2 transactions per block
        // Load some balances this would generate the genesis block and first block (2 blocks)
        blockchain.requestTransaction("0", "1", 50,0);
        blockchain.requestTransaction("0", "3", 50,0);
        // This would create balances for A and C and add transactions to the first block
        boolean mined = blockchain.mineBlock();
        // Now process transactions
        blockchain.requestTransaction("1", "2", 60,4); // This will be reverted
        blockchain.requestTransaction("3", "4", 30,2); // This will be processed
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
        UBlockchain blockchain = new UBlockchain(2,1000); // 2 transactions per block
        // Load some balances this would generate the genesis block and first block (2 blocks)
        blockchain.requestTransaction("0", "1", 50,0);
        blockchain.requestTransaction("0", "3", 50,0);
        // This would create balances for A and C and add transactions to the first block
        boolean mined = blockchain.mineBlock();
        // Now process transactions
        blockchain.requestTransaction("1", "2", 40,1);
        mined = blockchain.mineBlock();
        assertFalse(mined, "Block should not be mined as current block is not full");

        // Get block by number
        Block block1 = blockchain.getBlockByNumber(1);
        assertEquals(1, block1.getBlockNumber(), "Block number should be 1");
        // check transactions in block1
        TransactionWithFee[] transactionsBlock1 = (TransactionWithFee[]) block1.getTransactions();
        assertEquals(2, transactionsBlock1.length, "Block 1 should have 2 transactions");

        Block block2 = blockchain.getBlockByNumber(2);
        assertEquals(2, block2.getBlockNumber(), "Block number should be 2");
        TransactionWithFee[] transactionsBlock2 = (TransactionWithFee[]) block2.getTransactions();
        assertEquals(1, transactionsBlock2.length, "Block 2 should have 1 transaction");
        assertTrue(blockchain.repOK(), "Blockchain should be in a valid state");
    }

    // test missing methods in ABlockchain
    @Test
    void testGetTransactionPoolSize() {
        UBlockchain blockchain = new UBlockchain(2,1000); // 2 transactions per block
        blockchain.requestTransaction("0", "1", 50,1);
        blockchain.requestTransaction("0", "3", 50,2);
        assertEquals(2, blockchain.getTransactionPoolSize(), "Transaction pool size should be 2");
        blockchain.requestTransaction("1", "2", 40,3);
        assertEquals(3, blockchain.getTransactionPoolSize(), "Transaction pool size should be 3");
        assertTrue(blockchain.repOK(), "Blockchain should be in a valid state");    
    }

    // test hash of stateMPT after some balance updates
    @Test
    void testStateMPTHashAfterBalanceUpdates() {
        UBlockchain blockchain = new UBlockchain(2,1000); // 2 transactions per block
        // Load some balances this would generate the genesis block and first block (2 blocks)
        blockchain.requestTransaction("0", "1", 50,0);
        blockchain.requestTransaction("0", "11", 50,0);
        // This would create balances for A and C and add transactions to the first block
        boolean mined = blockchain.mineBlock();
        String stateMPTHash1 = blockchain.getStateMPTHash();
        assertEquals("4496977b", blockchain.getLastBlock().getPreviousHash());
        assertEquals("63236c10", stateMPTHash1);
        // Now process transactions
        blockchain.requestTransaction("1", "2", 40,1);
        blockchain.requestTransaction("11", "22", 30,2); // This should fill the block and add it
        mined = blockchain.mineBlock();
        String stateMPTHash2 = blockchain.getStateMPTHash();
        assertEquals("54e891d0", blockchain.getLastBlock().getPreviousHash());
        assertEquals("4e2e64bd", stateMPTHash2);
        assertNotEquals(stateMPTHash1, stateMPTHash2, "State MPT hash should change after balance updates");
        assertTrue(blockchain.repOK(), "Blockchain should be in a valid state");
    }

    /* ============================ edge cases ============================== */

    @Test
    void testRequestTransactionInvalidAddressesNegativeTest()
    {
        ABlockchain blockchain = new ABlockchain(2, 1000);
        // fromAddress is null
        assertThrows(IllegalArgumentException.class, () ->
            blockchain.requestTransaction(null, "B", 10, 1));
        // toAddress is empty
        assertThrows(IllegalArgumentException.class, () ->
            blockchain.requestTransaction("A", "", 10, 1));
    }

    @Test
    void testRequestTransactionNegativeFeeNegativeTest()
    {
        ABlockchain blockchain = new ABlockchain(2, 1000);
        assertThrows(IllegalArgumentException.class, () ->
            blockchain.requestTransaction("A", "B", 10, -1));
    }

    @Test
    void testMineBlockEmptyTransactionPoolNegativeTest()
    {
        ABlockchain blockchain = new ABlockchain(2, 1000);
        int sizeBefore = blockchain.size();
        boolean mined = blockchain.mineBlock();
        assertFalse(mined);
        assertEquals(sizeBefore, blockchain.size());
        assertTrue(blockchain.repOK());
    }
}