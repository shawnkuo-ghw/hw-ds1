package ex04.collections.implementations;

import java.util.NoSuchElementException;
import ex04.collections.interfaces.List;
import ex04.collections.interfaces.Queue;

/**
 * The linked list implementation of interface {@code queue}
 * @see ex04.collections.interfaces.Queue
 */
public class LinkedListQueue<T> implements Queue<T> {
    
    private List<T> ll;

    public LinkedListQueue()
    {
        ll = new LinkedList<T>();
    }

    @Override
    public void enqueue(T newElem)
    { ll.append(newElem); }

    @Override
    public T dequeue()
    {
        if ( this.empty() ) {
            throw new NoSuchElementException("LinkedListQueue.dequeue(): the queue is empty.");
        }
        T popedElem = ll.first();
        ll.removeFirst();
        return popedElem;
    }
    
    @Override
    public T top()
    {
        if ( this.empty() ) {
            throw new NoSuchElementException("LinkedListQueue.top(): the queue is empty.");
        }
        return ll.first();
    }

    @Override
    public void setTop(T newElem)
    {
        if ( this.empty() ) {
            throw new NoSuchElementException("LinkedListQueue.setTop(): the queue is empty.");
        }
        ll.setFirst(newElem);
    }

    @Override
    public int size()
    { return ll.size(); }

    @Override
    public boolean empty()
    { return ll.empty(); }

    @Override
    public String toString()
    { return ll.toString(); }
}