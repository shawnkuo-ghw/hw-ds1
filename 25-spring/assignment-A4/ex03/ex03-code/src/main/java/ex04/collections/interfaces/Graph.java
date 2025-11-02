package ex04.collections.interfaces;

/**
 * The interface of graph
 * @param V the type of vertices of the graph
 */
public interface Graph<V extends Vertex>
{
    /**
     * Add a new directed edge to the graph
     * @param from vertex the new edge starting from
     * @param to vertex the new edge going to
     * @throws IllegalArgumentException if {@code from} or {@code to} is not a valid vertex in the graph
     */
    void addEdge(V from, V to);

    /**
     * Retern the list of adjacent vertices of a specific vertex
     * @param vertex a specific vertex
     * @return the list of adjacent vertices
     * @throws IllegalArgumentException if {@code vertex} is not a valid vertex in the graph 
     */
    List<V> neighbours(V vertex);

    /**
     * Retern the number of vertices in the graph
     * @returnm the number of vertices in the graph
     */
    int size();

    /**
     * Print all information of the graph
     */
    void showGraphInfo();
}