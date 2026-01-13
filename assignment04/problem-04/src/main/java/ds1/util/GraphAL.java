package ds1.util;
/** 
 * This adjacency list class to be used in the GraphAL class
 * It uses the NodeWeight class to store the destination vertex and the weight of the edge
 */
class AdjacencyList {
    private Sequence<NodeWeight> elems;
    AdjacencyList()                  { elems = new ListoverLinkedList<NodeWeight>(); }
    void add(int dest, float weight) { elems.insertFront(new NodeWeight(dest, weight)); }
    void add(int dest)               { elems.insertFront(new NodeWeight(dest, 0)); }
    Sequence<NodeWeight> getList()   { return elems; }
    NodeWeight get(int index)        { return elems.at(index); }
    int size()                       { return elems.length(); }

    /** 
     * Creates and returns a deep copy of this AdjacencyList.
     * @return A deep copy of this AdjacencyList.
     */
    @Override
    public AdjacencyList clone() {
        AdjacencyList copy = new AdjacencyList();
        SequenceIterator<NodeWeight> iter = this.elems.getIterator();
        while (iter.hasNext()) {
            NodeWeight nw = iter.next();
            copy.add(nw.getDest(), nw.getWeight());
        }
        return copy;
    }
}

/** 
 * GraphAL class implements a directed graph using an adjacency list
 * Each vertex has a list of adjacent vertices with weights
 */
public class GraphAL { 
    private int numVertices;
    private AdjacencyList[] adjLists;
    private Boolean[] visited;
    private Boolean undirected = false;
    
    public GraphAL(int numVertices) {
        this.numVertices = numVertices;
        adjLists = new AdjacencyList[numVertices];
        visited = new Boolean[numVertices];
        for (int i = 0; i < numVertices; i++) {
            adjLists[i] = new AdjacencyList();
            visited[i] = false;
        }
    }

    public void setUndirected() {
        undirected = true;
    }
    
    /**
     *  get number of vertices
     */
    public int getNumVertices() {
        return numVertices;
    }

    /**
     * Adds a directed edge from src to dest with the given weight.
     * If the graph is undirected, also adds the reverse edge.
     * @param src source vertex
     * @param dest destination vertex
     * @param weight weight of the edge
     * O(1)
     */
    public void addEdge(int src, int dest, float weight) {
        adjLists[src].add(dest,weight);
        if(undirected) adjLists[dest].add(src,weight);
    }

    /**
     * Adds a directed edge from src to dest with weight 0.
     * If the graph is undirected, also adds the reverse edge.
     * @param src source vertex
     * @param dest destination vertex
     */
    public void addEdge(int src, int dest) {
        adjLists[src].add(dest);
        if(undirected) adjLists[dest].add(src);
    }

    /** 
     * Removes the directed edge from src to dest.
     * @param src source vertex
     * @param dest destination vertex
     * It may be inefficient, as it requires searching the adjacency list
     * Could be improved by using a hashtable or other data structures
     */
    public void removeEdge(int src, int dest) {
        Sequence<NodeWeight> neighbors = adjLists[src].getList();
        ds1.util.SequenceIterator<NodeWeight> iter = neighbors.getIterator();
        int index = 0;
        boolean found = false;
        // Find the index of the edge to be removed
        while (iter.hasNext() && !found) {
            NodeWeight neighbor = iter.next();
            if (neighbor.getDest() == dest) { found = true; }
            else index++;
        }
        // remove the edge
        neighbors.removeAt(index);
    }

    /** 
     * Get the weight of the edge from src to dest
     * @param src source vertex
     * @param dest destination vertex
     * @return weight of the edge, or null if the edge does not exist
     * This is innefficient, as it requires searching the adjacency list
     * Could be improved by using a hashtable or other data structures
     */
    public Float getWeightForEdge(int src, int dest) {
        Sequence<NodeWeight> neighbors = adjLists[src].getList();
        ds1.util.SequenceIterator<NodeWeight> iter = neighbors.getIterator();
        while (iter.hasNext()) {
            NodeWeight neighbor = iter.next();
            if (neighbor.getDest() == dest) {
                return neighbor.getWeight();
            }
        }
        return null; // edge not found
    }   

    public Sequence<NodeWeight> neighbors(int vertex) {
        return adjLists[vertex].getList();
    }

    public void print() {
        for (int i = 0; i < numVertices; i++) {
            System.out.print(i + ": ");
            for (int j = 0; j < adjLists[i].size(); j++) {
                System.out.print(adjLists[i].get(j) + " ");
            }
            System.out.println();
        }
    }

    // BFS starting for a given node
    public Sequence<Integer> BFS(int vertex) {
        // mark all vertices as not visited
        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }
        ListoverLinkedList<Integer> elements = new ListoverLinkedList<Integer>();
        ListoverLinkedList<Integer> queue = new ListoverLinkedList<Integer>();
        queue.insertRear(vertex);
        while (queue.length() != 0) {
            int v = queue.at(0);
            queue.removeAt(0);
            if (!visited[v]) {
                visited[v] = true;
                elements.insertFront(v);
                SequenceIterator<NodeWeight> iter = neighbors(v).getIterator();
                while (iter.hasNext()) {
                    NodeWeight u = iter.next();
                    queue.insertRear(u.getDest());
                }
            }
        }
        return elements;
    }        

    // DFS starting for a given node recursively
    public Sequence<Integer> DFSRec(int vertex) {
        // mark all vertices as not visited
        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }
        Sequence<Integer> elements = new ListoverLinkedList<Integer>();
        DFSRec(vertex, elements);
        return elements;
    }

    void DFSRec(int vertex, Sequence<Integer> elements) {
        visited[vertex] = true;
        elements.insertFront(vertex);
        SequenceIterator<NodeWeight> iter = neighbors(vertex).getIterator();
        while (iter.hasNext()) {
            NodeWeight u = iter.next();
            if (!visited[u.getDest()]) {
                DFSRec(u.getDest(), elements);
            }
        }
    }
    /** 
     * Creates and returns a deep copy of the adjacency list array.
     * @return A deep copy of the adjacency list array.
     * This can be useful to manipulate the graph without affecting the original one.
     */
    private AdjacencyList[] getAdjacencyCopy() {
        AdjacencyList[] adjacencyCopy = new AdjacencyList[this.adjLists.length];
        for (int i = 0; i < this.adjLists.length; i++) {
            adjacencyCopy[i] = this.adjLists[i].clone();
        }
        return adjacencyCopy;
    }

    @Override
    /** 
     * Returns a string representation of the graph.
     * @return A string representation of the graph.
     * It will be useful for debugging purposes.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numVertices; i++) {
            var neighbors = neighbors(i);
            var iter = neighbors.getIterator();
            while (iter.hasNext()) {
                var neighbor = iter.next();
                sb.append(i).append(" -> ").append(neighbor.getDest()).append(" (").append(neighbor.getWeight()).append(")\n");
            }
        }
        sb.append("]");
        return sb.toString();
    }
 }
