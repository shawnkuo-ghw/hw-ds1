package ds1;
/** 
 * Balance.java
 * This interface defines methods for managing and querying balances associated with addresses.
 */
public interface Balance {
    /**
     * Returns the balance of the given address
     * <p> Time complexity: O(log|A|), where |A| is the number of addresses </p>
     * @param address the address to check
     * @return balance of the address, 0 if address doesn't exist
     */
    int getBalance(String address);

    /**
     * Updates the balance of the given address
     * <p> Time complexity: O(log|A|), where |A| is the number of addresses </p>
     * @param address the address to update
     * @param newBalance the new balance to set
     * @throws IllegalStateException if totalSupply does not equal to the sum of all accounts
     */
    void updateBalance(String address, int newBalance);

    /**
     * returns the total supply of all balances
     * <p> Time complexity: O(1) </p>
     * @return total supply
     */
    int totalSupply();

    /**
     * Returns all addresses in the system
     * <p> Time complexity: O(|A|), where |A| is the number of addresses </p>
     * @return array of all addresses
     */
    String[] getAllAddresses();
}