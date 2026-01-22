package ds1.analytics;
import ds1.*;
import ds1.util.*;


public class BlockchainAnalytics {

    private GraphAL graph;
    private IndexAddressMap map;

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
        // 1. Get the number of addresses in block chain
        Transaction[] transactions = bc.getSuccessfulTransactions(); // O(T)
        // 2. Map every address with a unique index
        map = new IndexAddressMap();
        int nextId = 0;
        for (Transaction t : transactions) { // O(T) average
            String from = t.getFromAddress();
            String to = t.getToAddress();
            if (map.getIndex(from) == null) map.insert(nextId++, from);
            if (map.getIndex(to) == null) map.insert(nextId++, to);
        }

        // 3. Add weighted edges to the graph
        graph = new GraphAL(nextId); // O(V)
        for (Transaction t : transactions) { // O(T) average
            graph.addEdge(
                map.getIndex(t.getFromAddress()),
                map.getIndex(t.getToAddress()),
                t.getAmount()
            );
        }
    }
    private Sequence<String> toAddressCycle(Sequence<Integer> cycle) {
        if (cycle == null) return null;
        ListoverLinkedList<String> result = new ListoverLinkedList<String>();
        SequenceIterator<Integer> iter = cycle.getIterator();
        while (iter.hasNext()) {
            result.insertRear(map.getAddress(iter.next()));
        }
        return result;
    }
    /**
     * Detects if there is a money laundering loop (e.g., A  B  C  A).
     * Return the list of addresses involved in the cycle (e.g., `["Alice", "Bob", "Charlie", "Alice"]`).
     * Expected complexity: O(V+E)
     */
    public Sequence<String> detectCycle() {
        // Hint. You can use DFS to detect cycles in a directed graph
        // You implemented that in the tutorial.
        if (graph == null) return null;
        Sequence<Integer> cycle = graph.detectCycle();
        return toAddressCycle(cycle);
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
        if (graph == null) return null;
        Sequence<Integer> cycle = graph.findLeastCostCycle();
        return toAddressCycle(cycle);
    }

    /** 
     * findMoneyMule()
     * A "Money Mule" is defined here as an account that receives funds 
     * from many different sources but sends funds to very few.
     * Expected complexity: O(V+E)
    */
    public String findMoneyMule() {
        if (graph == null) return null;
        return map.getAddress(graph.findMoneyMuleVertex());
    }

    @Override
    public String toString() {
        // You may want to print the graph to facilitate debugging
        // Depending your implementation of GraphAL you may need to extend this 
        // with support for addresses
        if (graph == null) return "BlockchainAnalytics[graph=null]";
        return graph.toString();
    }

}