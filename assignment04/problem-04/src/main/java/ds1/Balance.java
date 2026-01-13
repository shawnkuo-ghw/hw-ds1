package ds1;
/** 
 * Balance.java
 * This interface defines methods for managing and querying balances associated with addresses.
 */
public interface Balance {
    /**
     * Returns the balance of the given address
     * @param address the address to check
     * @return balance of the address, 0 if address doesn't exist
     * O(log A) where A is the number of addresses
     */
    int getBalance(String address);

    /**
     * Updates the balance of the given address
     * @param address the address to update
     * @param newBalance the new balance to set
     * O(log A) where A is the number of addresses
     */
    void updateBalance(String address, int newBalance);

    /**
     * returns the total supply of all balances
     * @return total supply
     * O(1)
     */
    int totalSupply();

    /**
     * Returns all addresses in the system
     * @return array of all addresses
     * O(A) where A is the number of addresses
     */
    String[] getAllAddresses();
    String getStateHash();
}