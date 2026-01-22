package ds1;
import ds1.util.ListoverLinkedList;
import ds1.util.StateMPT;
/** 
 * BalanceImp.java
 * This class implements the Balance tree to store address-balance pairs.
 * It provides methods to update balances, retrieve balances, get all addresses, and compute total supply.
 * The operations should be O(log |A|) where |A| is the number of addresses.
 */
public class BalanceImp implements Balance {
    // implement use StateMPT
    private StateMPT stateMPT ;
    int totalSupply;
    
    public BalanceImp() {
        stateMPT = new StateMPT();
        totalSupply = 0;    
    }

    @Override
    public void updateBalance(String address, int newBalance) {
        // Implementation
        int oldBalance = stateMPT.get(address);
        stateMPT.put(address, newBalance);
        totalSupply = totalSupply - oldBalance + newBalance;
    }
    @Override
    public int getBalance(String address) {
        return stateMPT.get(address);
    }

    @Override
    public String[] getAllAddresses() {
        // Implementation
        ListoverLinkedList<String> addresses = stateMPT.getAllAddresses();
        String[] result = new String[addresses.length()];
        for (int i = 0; i < addresses.length(); i++) {
            result[i] = addresses.at(i);
        }
        return result;
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
        // Check: totalSupply is non-negative
        if (totalSupply < 0) return false;
        // Check: sum of all balances equals totalSupply
        String[] addresses = getAllAddresses();
        int sum = 0;
        for (String address : addresses) {
            sum += getBalance(address);
        }
        if (sum != totalSupply) return false;
        // check no negative balances
        for (String address : addresses) {
            if (getBalance(address) < 0) return false;
        }
        // check stateMPT repOK
        if (!stateMPT.repOK()) return false;
        return true;

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
        return stateMPT.getRootHash();
    }

}