package ex04.collections.implementations;
import ex04.collections.interfaces.Graph;
import ex04.collections.interfaces.List;
import ex04.collections.interfaces.ListIterator;
import ex04.collections.interfaces.Vertex;

/**
 * The directed implementations over {@code AdjacencyList} of interface {@code Graph}
 * @param V the type of nodes in the graph
 * @see ex04.collections.interfaces.Graph
 * @see ex04.collections.implementations.AdjacencyList
 */
public class DirectedGraphAL<V extends Vertex> implements Graph<V>
{
    private int vertexNumber;
    private List<V> vertexList;
    private AdjacencyList<V>[] adjList;

    public DirectedGraphAL(int vertexNumber, List<V> vertextList)
    {
        this.vertexList = vertextList;
        this.vertexNumber = vertexNumber;
        if ( vertexNumber != vertextList.size() ) {
            throw new IllegalStateException("DirectedGraphAL(): vertex number and vertex list does not coinside.");
        }
        adjList = (AdjacencyList<V>[]) new AdjacencyList[vertexNumber];
        for ( int i = 0; i < vertexNumber; i ++ ) {
            adjList[i] = new AdjacencyList<V>();
        }
    }

    @Override
    public void addEdge(V from, V to)
    {
        int fromNO = from.vertexNO();
        int toNO = to.vertexNO();
        if ( !(0 <= fromNO && fromNO < vertexNumber) || !(0 <= toNO && toNO < vertexNumber) ) {
            throw new IllegalArgumentException("DirectedGraphAL.addEdge(): invalid vertex number.");
        }
        adjList[fromNO].add(to);
    }

    @Override
    public List<V> neighbours(V vertex)
    {
        int vertexNO = vertex.vertexNO();
        if ( !( 0 <= vertexNO && vertexNO < vertexNumber) ) {
            throw new IllegalArgumentException("DirectedGraphAL.neighbours(): invalid vertex number.");
        }
        return adjList[vertexNO].getList();
    }

    @Override
    public int size()
    { return this.vertexNumber; }

    @Override
    public void showGraphInfo() {
        System.out.println("Vertex Number: " + vertexNumber);
        System.out.println("Vertex Names: ");
        String vertexNames = "";
        for ( int i = 0; i < vertexNumber; i ++ ) {
            vertexNames += vertexList.get(i).toString() + ( i < vertexNumber - 1 ? " " : "");
        }
        System.out.println(vertexNames);
        System.out.println("Graph Edges:");
        for ( int i = 0; i < vertexNumber; i ++ ) {
            int currNO = i;
            List<V> currNeighbours = adjList[i].getList();
            ListIterator<V> itr = currNeighbours.getIterator();
            while ( itr.hasNext() ) {
                int neighbourNO = itr.getNext().vertexNO();
                System.out.println(vertexList.get(currNO).toString() + " -> " + vertexList.get(neighbourNO).toString());
            }
        }
    }
}