package ds1;
import ds1.util.ListoverLinkedList;
import ds1.util.StateMPT;
/** 
 * BalanceImp.java
 * This class implements the Balance tree to store address-balance pairs.
 * It provides methods to update balances, retrieve balances, get all addresses, and compute total supply.
 */
public class BalanceImp implements Balance {
    // implement use StateMPT
    private StateMPT stateMPT ;
    int totalSupply;
    
        public BalanceImp() {
        // Should be implemented by students
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void updateBalance(String address, int newBalance) {
        // Should be implemented by students
        throw new UnsupportedOperationException("Not implemented yet");
    }
    @Override
    public int getBalance(String address) {
        // Should be implemented by students
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public String[] getAllAddresses() {
        // Should be implemented by students
        throw new UnsupportedOperationException("Not implemented yet");
     }

    @Override
    public int totalSupply() {
        // Implementation
        return totalSupply;
    }

    /**
     * repOK method to check class invariants 
     * remember to check also the StateMPT repOK
    */
    public boolean repOK() {
        // Should be implemented by students
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // toString for debugging
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("BalanceImp[totalSupply=").append(totalSupply).append(", addresses={");
        String[] addresses = getAllAddresses();
        for (int i = 0; i < addresses.length; i++) {
            String address = addresses[i];
            sb.append(address).append(":").append(getBalance(address));
            if (i < addresses.length - 1) sb.append(", ");
        }
        sb.append("}]");
        return sb.toString();
    }
    // Get root hash of the StateMPT
    @Override
    public String getStateHash() {
        // Should be implemented by students
        throw new UnsupportedOperationException("Not implemented yet");
    }

}