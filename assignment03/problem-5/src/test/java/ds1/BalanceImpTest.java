package ds1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class BalanceImpTest {
    // Create tests for BalanceImp class here. Respect the signatures of its methods:
    // Use information from BalanceImp.java and StateMPT.java as needed for context.
    @Test
    void testUpdateAndGetBalance() {
        BalanceImp balanceImp = new BalanceImp();
        balanceImp.updateBalance("123", 100);
        assertEquals(100, balanceImp.getBalance("123"), "Balance for address 123 should be 100");
        balanceImp.updateBalance("123", 200);
        assertEquals(200, balanceImp.getBalance("123"), "Balance for address 123 should be updated to 200");
    }   
    @Test
    void testGetAllAddresses() {
        BalanceImp balanceImp = new BalanceImp();
        balanceImp.updateBalance("123", 100);
        balanceImp.updateBalance("456", 200);
        String[] addresses = balanceImp.getAllAddresses();
        assertEquals(2, addresses.length, "There should be 2 addresses");
        assertTrue(java.util.Arrays.asList(addresses).contains("123"), "Addresses should contain 123");
        assertTrue(java.util.Arrays.asList(addresses).contains("456"), "Addresses should contain 456");
    }    
    @Test
    void testTotalSupply() {
        BalanceImp balanceImp = new BalanceImp();
        balanceImp.updateBalance("123", 100);
        balanceImp.updateBalance("456", 200);
        assertEquals(300, balanceImp.totalSupply(), "Total supply should be 300");
        balanceImp.updateBalance("123", 150);
        assertEquals(350, balanceImp.totalSupply(), "Total supply should be updated to 350");
    }

    @Test
    void testRepOK() {
        BalanceImp balanceImp = new BalanceImp();
        balanceImp.updateBalance("123", 100);
        balanceImp.updateBalance("456", 200);
        assertTrue(balanceImp.repOK(), "repOK should return true for valid state");
        balanceImp.updateBalance("123", -50); // Invalid balance
        assertFalse(balanceImp.repOK(), "repOK should return false for negative total supply"); 
    }

    // test getStateHash
    @Test
    void testGetStateHash() {
        BalanceImp balanceImp = new BalanceImp();
        balanceImp.updateBalance("123", 100);
        assertEquals("3116e4eb", balanceImp.getStateHash(), "State root hash should match computed hash");
        balanceImp.updateBalance("456", 200);
        assertEquals("c45d6201", balanceImp.getStateHash(), "State root hash should match computed hash");
        assertTrue(balanceImp.repOK(), "repOK should return true for valid state");
    }
    @Test
    void testGetStateHashCommonNodes() {
        BalanceImp balanceImp = new BalanceImp();
        balanceImp.updateBalance("123", 100);
        assertEquals("3116e4eb", balanceImp.getStateHash(), "State root hash should match computed hash");
        balanceImp.updateBalance("124", 200);
        assertEquals("2e5b02b8", balanceImp.getStateHash(), "State root hash should match computed hash");
        assertTrue(balanceImp.repOK(), "repOK should return true for valid state");
    }




}