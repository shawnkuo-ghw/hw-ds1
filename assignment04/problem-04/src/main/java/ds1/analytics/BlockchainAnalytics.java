package ds1.analytics;
import java.util.Stack;

import ds1.*;
import ds1.util.*;


public class BlockchainAnalytics {

    private final GraphAL graph;
    private final IndexAddressMap map;
    private final int numVertices; // V
    private final int numEdges;    // T
    private boolean[] visited;
    
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
        
        // 1. Get addresses and transations in block chain
        String[] allAddresses = bc.getAllAdresses(); // O(V)
        Transaction[] transactions = bc.getSuccessfulTransactions(); // O(T)
        numVertices = allAddresses.length;
        numEdges = transactions.length;
        // 2. Map every address with a unique index
        map = new IndexAddressMap();
        for(int i = 0; i < allAddresses.length; i++) { // V * O(log V) = O(V * log V) ???
            String address = allAddresses[i];
            map.insert(i, address); // O(log V)
        }
        // 3. Add weighted edges to the graph
        graph = new GraphAL(allAddresses.length);
        for (Transaction t : transactions) { // T * O(log V) = O(T * log V) ???
            graph.addEdge(
                map.getIndex(t.getFromAddress()), // O(log V)
                map.getIndex(t.getToAddress()), // O(log V)
                t.getAmount()
            ); // O(log V)
        }
    }
    
    /**
     * Detects if there is a money laundering loop (e.g., A  B  C  A).
     * Return the list of addresses involved in the cycle (e.g., `["Alice", "Bob", "Charlie", "Alice"]`).
     * Expected complexity: O(V+E)
     */
    public Sequence<String> detectCycle() {
        // Hint. You can use DFS to detect cycles in a directed graph

        // for (int i = 0; i < numVertices; i++) visited[i] = false;        
        // Sequence<String> cycle = new ListoverLinkedList<String>();
        // for (int i = 0; i < numVertices; i++) {
        //     Sequence<Integer> elements = new ListoverLinkedList<Integer>();
        //     if ( detectCycleDFS(i, elements) ) {
        //         SequenceIterator<Integer> itr = elements.getIterator();
        //         while ( itr.hasNext() ) {
        //             Integer currIndex = itr.next();
        //             cycle.insertRear(map.getAddress(currIndex));
        //         }
        //     }
        // }
        // return cycle;

        // mark all vertices as not visited
        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }
        Sequence<Integer> elements = new ListoverLinkedList<Integer>();
        Sequence<Integer> stack = new ListoverLinkedList<Integer>();
        stack.push(vertex);
        while (!stack.isEmpty()) {
            int v = stack.pop();
            if (!visited[v]) {
                visited[v] = true;
                elements.insertFront(v);
                SequenceIterator<Integer> iter = neighbors(v).getIterator();
                while (iter.hasNext()) {
                    Integer u = iter.next();
                    stack.push(u);
                }
            }
        }
        return elements;        
    }

    private boolean detectCycleDFS(Integer vertex, Sequence<Integer> elements) {
        
        // a cycle has been detected
        if ( visited[vertex] ) {
            elements.insertFront(vertex);
            return true;
        } else {

        }
        return false;
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
     * findMoneyMule()
     * A "Money Mule" is defined here as an account that receives funds 
     * from many different sources but sends funds to very few.
     * Expected complexity: O(V+E)
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