// Balance.java

package ds1;
public interface Balance
{
    /**
     * Returns the balance of the given address
     * @param address the address to check
     * @return balance of the address, 0 if address doesn't exist
     */
    int getBalance(String address);

    /**
     * Updates the balance of the given address
     * @param address the address to update
     * @param newBalance the new balance to set
     */
    void updateBalance(String address, int newBalance);

    /**
     * Get all addresses int the block chain
     * @return string list of all addresses in the block chain
     */
    String[] getAllAddresses();
}