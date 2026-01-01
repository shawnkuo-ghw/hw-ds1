package ds1.analytics;

import ds1.*;
import ds1.util.Sequence;

public class BlockchainAnalytics {

    /**
     * Constructs a BlockchainAnalytics object from the given UBlockchain instance.
     * It builds a directed graph where vertices represent unique addresses and edges represent transactions.
     * The weight of each edge corresponds to the amount transferred in the transaction.
     * @param bc The UBlockchain instance to analyze.
     * O(V + T) where V is the number of unique addresses and T is the number of transactions.
     */
    public BlockchainAnalytics(UBlockchain bc) {
        // You may need need to use (and extend) the GraphAL class 
        // The class already implements a directed weighted graph
        // You may need to map addresses to vertex IDs or modify GraphAL to handle string addresses directly
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    /**
     * Detects if there is a money laundering loop (e.g., A  B  C  A).
     * Return the list of addresses involved in the cycle (e.g., `["Alice", "Bob", "Charlie", "Alice"]`).
     * Expected complexity: O(V+E)
    */
    public Sequence<String> detectCycle() {
        // Hint. You can use DFS to detect cycles in a directed graph
        // You implemented that in the tutorial.
        throw new UnsupportedOperationException("Not implemented yet");
    }
    /**
     * Similar to the previous one but in case of existing more than one loop, 
     * select the one with less cost. 
     * The cost is calculated by the sum of amounts in the loop.
     * Justify the complexity of your best solution
     */
    public Sequence<String> detectShortestCycle() {
        // You need to find all cycles in the directed graph
        // You may need to play with the graph to find all cycles
        // But you may need to modify the graph to avoid finding the same cycle again
        // Many approaches are possible here, select the one you consider best 
        // and justify its complexity
        // Try to modularize your code in helper methods if needed
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /** 
     *   findMoneyMule()
     *   A "Money Mule" is defined here as an account that receives funds 
     * from many different sources but sends funds to very few.
     *   Expected complexity: O(V+E)
    */
    public String findMoneyMule() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public String toString() {
        // You may want to print the graph to facilitate debugging
        // Depending your implementation of GraphAL you may need to extend this 
        // with support for addresses
        return super.toString();
    }
}