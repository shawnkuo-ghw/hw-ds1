package ds1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ds1.analytics.BlockchainAnalytics;
import ds1.util.Sequence;


public class BlockchainAnalyticsTests {
    @Test
    /**
     * Test detectCycle on a blockchain with no cycles
     */
    void testDetectCycleNoCycle() {
        UBlockchain ubc = new UBlockchain(2, 1000);
        // Load some balances this would generate the genesis block and first block (2 blocks)
        ubc.requestTransaction("0", "123", 500,0);
        ubc.requestTransaction("0", "456", 500,0);
        // This would create balances for A and C and add transactions to the first block
        ubc.mineBlock();
        ubc.requestTransaction("123", "456", 100, 0);
        ubc.requestTransaction("456", "789", 200, 0);
        ubc.mineBlock();
        BlockchainAnalytics bg = new BlockchainAnalytics(ubc);
        assertNull(bg.detectCycle());
    }

    @Test
    /** 
     * Test detectCycle on a blockchain with a cycle
     */
    void testDetectCycleWithCycle() {
        UBlockchain ubc = new UBlockchain(2, 1000);
        // Load some balances this would generate the genesis block and first block (2 blocks)
        ubc.requestTransaction("0", "123", 500,0);
        ubc.requestTransaction("0", "456", 500,0);
        // This would create balances for A and C and add transactions to the first block
        ubc.mineBlock();
        ubc.requestTransaction("123", "456", 100, 0);
        ubc.requestTransaction("456", "789", 100, 0);
        ubc.mineBlock();
        ubc.requestTransaction("789", "123", 100, 0); // creates a cycle 123 -> 456 -> 789 -> 123
        ubc.requestTransaction("789", "111", 100, 0); 
        ubc.mineBlock();
        BlockchainAnalytics bg = new BlockchainAnalytics(ubc);
        Sequence<String> cycle = bg.detectCycle();
        assertNotNull(cycle);
        assertEquals(4, cycle.length());
        // Check the cycle content regardless of starting point
        String[] expectedCycleElements = {"123", "456", "789"};
        for (int i = 0; i < expectedCycleElements.length; i++) {
            // check element in cycle
            assertTrue(cycle.in(expectedCycleElements[i]));
        }
        assertFalse(cycle.in("0"));
        assertFalse(cycle.in("111"));
        ubc.requestTransaction("111", "0", 100, 0); 
        ubc.requestTransaction("123", "456", 100, 0);
        ubc.mineBlock();        
    }

    @Test
    /** 
     * Test detectCycle on a blockchain with a self-loop
     */
    void testDetectCycleSelfLoop() {
        UBlockchain ubc = new UBlockchain(2, 1000);
        // Load some balances this would generate the genesis block and first block (2 blocks)
        ubc.requestTransaction("0", "123", 500,0);
        ubc.requestTransaction("0", "456", 500,0);      
        // This would create balances for A and C and add transactions to the first block
        ubc.mineBlock();
        ubc.requestTransaction("123", "123", 100, 0); // self-loop
        ubc.requestTransaction("456", "789", 100, 0);
        ubc.mineBlock();
        BlockchainAnalytics bg = new BlockchainAnalytics(ubc);
        Sequence<String> cycle = bg.detectCycle();
        assertNotNull(cycle);
        assertEquals(2, cycle.length());
        assertEquals("123", cycle.at(0));
        assertEquals("123", cycle.at(1));   
    }

    @Test
    /** 
     * Test detectCycle on a blockchain with reverted transactions
     * the reverted transactions should not be considered in cycle detection
     */
    void testDetectCycleWithRevertedTransactions() {
        UBlockchain ubc = new UBlockchain(2, 1000);
        // Load some balances this would generate the genesis block and first block (2 blocks)
        ubc.requestTransaction("0", "123", 500,0);
        ubc.requestTransaction("0", "456", 500,0);
        // This would create balances for A and C and add transactions to the first block
        ubc.mineBlock();
        ubc.requestTransaction("123", "456", 100, 0);
        ubc.requestTransaction("456", "789", 100, 0);
        ubc.mineBlock();
        ubc.requestTransaction("789", "111", 100, 0); 
        // creates a cycle 123 -> 456 -> 789 -> 123 but is reverted
        ubc.requestTransaction("789", "123", 1000, 0); 
        // revert the transaction that would create the cycle
        ubc.mineBlock();
        BlockchainAnalytics bg = new BlockchainAnalytics(ubc);
        Sequence<String> cycle = bg.detectCycle();
        assertNull(cycle); // no cycle should be detected
    }

    @Test
    /** 
     * Test findMoneyMule on a blockchain with a money mule
     * A money mules is the that receives from multiple sources and sends to a few (or zero) destinations
     */
    void testFindMoneyMule() {
        UBlockchain ubc = new UBlockchain(2, 1000);
        // Load some balances this would generate the genesis block and first block (2 blocks)
        ubc.requestTransaction("0", "123", 500,0);
        ubc.requestTransaction("0", "456", 500,0);
        // This would create balances for A and C and add transactions to the first block
        ubc.mineBlock();
        ubc.requestTransaction("123", "789", 100, 0);
        ubc.requestTransaction("789", "456", 100, 0);
        ubc.mineBlock();
        ubc.requestTransaction("123", "456", 100, 0); // creates a cycle 123 -> 456 -> 789 -> 123
        ubc.requestTransaction("789", "111", 100, 0); 
        ubc.mineBlock();
        BlockchainAnalytics bg = new BlockchainAnalytics(ubc);
        String moneyMule = bg.findMoneyMule();
        assertEquals("456", moneyMule);
    }

    @Test
    /** 
     * Test detectShortestCycle on a blockchain with multiple cycles
     */
    void testDetectShortestCycle() {
        UBlockchain ubc = new UBlockchain(1, 10000);
        // Load some balances this would generate the genesis block and first block (2 blocks)
        ubc.requestTransaction("0", "123", 5000,0);
        ubc.mineBlock();
        ubc.requestTransaction("0", "456", 5000,0);
        ubc.mineBlock();
        ubc.requestTransaction("123", "789", 1000, 0);
        ubc.mineBlock();
        ubc.requestTransaction("789", "456", 200, 0);
        ubc.mineBlock();
        ubc.requestTransaction("456", "123", 100, 0); // creates a cycle 123 -> 789 -> 456 -> 123
        ubc.mineBlock();
        ubc.requestTransaction("123", "111", 50, 0); 
        ubc.mineBlock();
        ubc.requestTransaction("111", "0", 20, 0); 
        ubc.mineBlock();
        // ubc.requestTransaction("789", "0", 20, 0); 
        // ubc.mineBlock();
        BlockchainAnalytics bg2 = new BlockchainAnalytics(ubc);
        Sequence<String> shortestCycle2 = bg2.detectShortestCycle();
        assertNotNull(shortestCycle2);
        assertEquals(4, shortestCycle2.length());
        String[] expectedCycleElements = {"123", "111", "0","123"};
        for (int i = 0; i < expectedCycleElements.length; i++) {
            // check element in cycle
            assertTrue(shortestCycle2.in(expectedCycleElements[i]));
        }
        assertFalse(shortestCycle2.in("789"));
        assertFalse(shortestCycle2.in("456"));
    }


}
