package ex04.collections.implementations;
import ex04.collections.interfaces.List;
import ex04.collections.interfaces.ListIterator;
import ex04.collections.implementations.LinkedList;

/**
 * The class that represents the adjacent list of a vertex
 */
public class AdjacencyList<V>
{    
    private List<V> neighbours;

    AdjacencyList()
    { neighbours = new LinkedList<V>(); }
    
    public void add(V dest)
    { neighbours.append(dest); }

    public List<V> getList()
    { return neighbours; }

    public V get(int index)
    { return neighbours.get(index); }

    public int size()
    { return neighbours.size(); }

    public ListIterator<V> getIterator()
    { return neighbours.getIterator(); }
}