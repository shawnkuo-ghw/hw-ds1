package ds1;
import ds1.util.*;

/** 
 * BalanceImp.java
 * This class implements the Balance tree to store address-balance pairs.
 * It provides methods to update balances, retrieve balances, get all addresses, and compute total supply.
 * The operations should be O(log|A|) where |A| is the number of addresses.
 */
public class BalanceImp implements Balance
{
    /* ====================== Fields and Constructor ======================== */

    private final AVLTree<String, AddressBalancePair> addBalPairs;
    private final Sequence<String> allAddress;
    private int totalSupply;

    public BalanceImp() {
        totalSupply = 0;
        allAddress = new ListoverLinkedList<String>(String.class);
        addBalPairs = new AVLTreeImp<String, AddressBalancePair>(
            AVLNode.class, 
            AddressBalancePair.class
        );
    }

    /* ========================== Modifier ================================== */

    /**
     * Time Complexity: O(log |A|)
     * Explaination:
     *   - time complexity of {@code searchTree} and {@code insertTree} is O(log |A|)
     *   - time complexity of {@code insertRear} is O(1)
     *   - the overall time complexity is O(log |A|)
     * @see ds1.util.AVLTree#searchTree(Comparable)
     * @see ds1.util.AVLTree#insertTree(Comparable, Object)
     * @see ds1.util.ListoverLinkedList#insertRear(Object)
     */
    @Override
    public void updateBalance(String address, int newBalance) {
        AddressBalancePair targetPair = addBalPairs.searchTree(address);                  // O(log |A|)
        if ( address.equals("0") && allAddress.length() == 0 ) {
            // initiat `totalSupply` with the amount of BB-coins of 
            // the address "0" in the genesis block
            addBalPairs.insertTree(address, targetPair);                                  // O(log |A|)
            totalSupply = newBalance;
        } else if ( targetPair == null ) {
            // address does not exist in Balance
            addBalPairs.insertTree(address, new AddressBalancePair(address, newBalance)); // O(log |A|)
            allAddress.insertRear(address);                                               // O(1)
        } else {
            // address already exists in Balance
            addBalPairs.updateTree(address, new AddressBalancePair(address, newBalance));
        }
        if ( !repOK() ) {
            throw new IllegalStateException(
                "BalanceImp.updateBalance(): totalSupply does not equal to the sum of all accounts."
            );
        }
    }

    /* ========================== Getters =================================== */

    /**
     * Time Complexity: O(log |A|)
     * <p>Explaination:</p>
     * <li> - the height of AVL tree is less than or equal to log|A|</li>
     * <li> - the number of times to compare the elements with the target is O(log |A|)</li>
     * <li> - so the time complexity of {@code searchTree} is O(log |A|)</li>
     * @see ds1.util.AVLTreeImple#searchTree(Comparable)
     */
    @Override
    public int getBalance(String address) {
        int balanceOfAddress;
        AddressBalancePair targetPair = addBalPairs.searchTree(address); // O(log |A|)
        if ( targetPair != null ) balanceOfAddress = targetPair.balance;
        else balanceOfAddress = 0;
        return balanceOfAddress;
    }

    @Override
    public int totalSupply() { return totalSupply; }

    @Override
    public String[] getAllAddresses() {
        AddressBalancePair[] pairsArray = addBalPairs.toArray();
        String[] allAddresses = new String[pairsArray.length];
        for ( int i = 0; i < pairsArray.length; i++ ) { allAddresses[i] = pairsArray[i].address; }
        return allAddresses;
    }

    // representation invariant checker for Balance
    private boolean repOK() {
        int totalBalance = 0;
        AddressBalancePair[] pairsArray = addBalPairs.toArray();
        for ( AddressBalancePair pair: pairsArray ) { totalBalance += pair.balance; }
        return totalBalance == totalSupply;
    }   
}