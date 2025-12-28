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
    private final StateMPT stateMPT ;
    private int totalSupply;
    
    public BalanceImp() {
        totalSupply = 0;
        stateMPT = new StateMPT();
    }

    /**
     * <p> Time complexity: O(L) </p>
     * <li> - The implementation consists of calling {@code stateMPT.search},
     *        {@code stateMPT.insert} and {@code stateMPT.update} in linear order. </li>
     * <li> - Time complexities of these three methods are all O(L). </li>
     * @see ds1.util.StateMPT#search(String)
     * @see ds1.util.StateMPT#insert(String, int)
     * @see ds1.util.StateMPT#update(String, int)
     */
    @Override
    public void updateBalance(String address, int newBalance) {
        if ( address == null ) throw new IllegalArgumentException(
            "BalanceImp.updateBalance(): param address is null."
        );
        int oldBalance = stateMPT.search(address); // O(L)
        if ( address.equals("0") && stateMPT.adddressesCount() == 0 ) {
            // initiate `totalSupply` with the amount of BB-coins of 
            // the address "0" in the genesis block`
            stateMPT.insert(address, newBalance); // O(1)
            totalSupply = newBalance;
        } else if ( oldBalance == -1 ) {
            // address does not exist in stateMPT
            stateMPT.insert(address, newBalance); // O(L)
        } else {
            // address exists in stateMPT
            stateMPT.update(address, newBalance); // O(L)
        }
        totalSupply = getSumOfBalances(); // update total supply
    }
    
    /* ============================= Getters ================================ */

    @Override
    public int totalSupply() { return totalSupply; }

    // Get root hash of the StateMPT
    @Override
    public String getStateHash() { return stateMPT.getRoothash(); }
    
    @Override
    public int getBalance(String address) {
        int balance = stateMPT.search(address);
        if ( balance == -1 ) return 0;
        else return balance;
    }

    @Override
    public String[] getAllAddresses() { return stateMPT.getAllAdressesSequence().toArray(); }

    /* ========================= Class Invariant ============================ */

    /**
     * repOK method to check class invariants 
     * remember to check also the StateMPT repOK
     */
    public boolean repOK() {
        return totalSupply >= 0 && totalSupply == getSumOfBalances() && stateMPT.repOK();
    }

    /* ========================== Private Utilities ========================= */

    private int getSumOfBalances() {
        int sumOfBalances = 0;
        String[] allAddress = getAllAddresses();
        for ( String address: allAddress) sumOfBalances += stateMPT.search(address);
        return sumOfBalances;
    }

    /* =========================== Debuggers ================================ */

    // toString for debugging
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("BalanceImp [totalSupply=").append(totalSupply).append(", addresses={");
        String[] addresses = getAllAddresses();
        for (int i = 0; i < addresses.length; i++) {
            String address = addresses[i];
            sb.append(address).append(":").append(getBalance(address));
            if (i < addresses.length - 1) sb.append(", ");
        }
        sb.append("}]");
        return sb.toString();
    }
}