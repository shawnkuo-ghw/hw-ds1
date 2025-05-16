package ex04.collections.implementations;

/**
 * The class of a singly linked node with an associated value
 */
public class Node<T> {

    private T value;
    private Node<T> next;
    
    /**
     * Constructs a new node with a {@code null} value 
     * and no next ({@code null} as next). 
     */
    public Node()
    {
        value = null;
        next = null;
    }
    
    /**
     * Constructs a new node with a specific value 
     * and no next ({@code null} as next). 
     * @param value the value for this node
     */
    public Node(T value)
    {
        this.value = value;
        next = null;
    }
    
    /**
     * Constructs a new node with a specific value 
     * and a specific next node. 
     * @param value the value for this node
     * @param next the next node
     */
    public Node(T value, Node<T> next)
    {
        this.value = value;
        this.next = next;
    }
    
    /**
     * @return the value associated to this node.
     */
    public T getValue()
    {
        return value;
    }
    
    /**
     * Changes the value associated to this node.
     * @param value the new value for this node
     */
    public void setValue(T value)
    {
        this.value = value;
    }
    
    /**
     * @return the next node.
     */
    public Node<T> getNext()
    {
        return next;
    }
    
    /**
     * @return {@code true} iff {@code getNext() != null}.
     */
    public boolean hasNext()
    {
        return next != null;
    }
    
    /**
     * Changes the next node.
     * @param next the new next node
     */
    public void setNext(Node<T> next)
    {
        this.next = next;
    }    
}
