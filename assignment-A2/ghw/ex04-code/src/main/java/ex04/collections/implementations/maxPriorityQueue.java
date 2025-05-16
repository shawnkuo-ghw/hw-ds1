package ex04.collections.implementations;

import java.util.NoSuchElementException;
import ex04.collections.interfaces.Queue;

/**
 * The max-priority queue implementation of interface {@code Queue}
 * @see ex04.collections.interfaces.Queue
 */
public class maxPriorityQueue<T extends Comparable<T> > implements Queue<T> {

    private maxHeap<T> heap;

    public maxPriorityQueue() { heap = new maxHeap<T>(); }

    @Override
    public void enqueue(T newElem) { heap.push(newElem); }

    @Override
    public T dequeue()
    {
        if ( heap.empty() ) {
            throw new NoSuchElementException("maxPriorityQueue.dequeue(): queue is empty.");
        }
        return heap.pop();
    }

    @Override
    public T top() { 
        if ( heap.empty() ) {
            throw new NoSuchElementException("maxPriorityQueue.top(): queue is empty.");
        }
        return heap.top(); 
    }

    @Override
    public void setTop(T newElem)
    {
        if ( heap.empty() ) {
            throw new NoSuchElementException("maxPriorityQueue.setTop(): queue is empty.");
        }
        heap.setTop(newElem);
    }

    @Override
    public int size() { return heap.size(); }

    @Override
    public boolean empty() { return heap.empty(); }
}