// BalanceImp.java

package ds1;
import ds1.utils.Sequence;

import java.util.NoSuchElementException;

import ds1.utils.ListoverLinkedList;

/**
 * Implement using appropriate data structure to support O(|A|) getBalance
 * where |A| is the number of addresses                                      
 * Recommendation: use the AddressBalancePair to store address-balance pairs  
 */
public class BalanceImp implements Balance
{
    /**********
     * Fields *
     **********/
    private final Sequence<AddressBalancePair> addBalPairs; // the list of all address-balance pairs

    /***************
     * Constructor *
     ***************/
    public BalanceImp() {
        addBalPairs = new ListoverLinkedList<AddressBalancePair>(); // Instantiate the addBalPairs
    }

    /**************
     * Operations *
     **************/
    @Override
    public void updateBalance(String address, int newBalance)
    {
        AddressBalancePair newPair = new AddressBalancePair(address, newBalance);
        int index = addBalPairs.indexOf(newPair); // Time Complexity: O(|A|)
        if (index == -1) {
            addBalPairs.insertRear(newPair); // add new pair, O(1)
        } else {
            addBalPairs.updateAt(index, newPair); // update address-balance pair, O(|A|)
        }
    }

    @Override
    public int getBalance(String address)
    {
        int balanceOfAddress;
        AddressBalancePair target = new AddressBalancePair(address, 0);
        int index = addBalPairs.indexOf(target); // Time Complexity: O(|A|)
        if (index != -1) {
            balanceOfAddress = addBalPairs.at(index).getBalance(); // Time Complexity: O(|A|)
        } else {
            balanceOfAddress = 0; // address not found
        }
        return balanceOfAddress;
    }

    @Override
    public String[] getAllAddresses()
    {
        int length = addBalPairs.length();
        String[] addresses = new String[length];
        for (int i = 0; i < length; i++) {
            addresses[i] = addBalPairs.at(i).getAddress();
        }
        return addresses;
    }

    // toString for debugging
    @Override
    public String toString()
    {
        String strRep = "Balance(s) in Block Chain:\n";
        int length = addBalPairs.length();
        if (length == 0) {
            strRep += "Empty\n";
        } else {
            for (int i = 0; i < length; i++) {
                strRep += addBalPairs.at(i).toString() + "\n";
            }
        }
        return strRep + "\n";
    }
}