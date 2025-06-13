package ex04.collections.interfaces;
import java.util.NoSuchElementException;

/**
 * The interface of priority queue
 * @param Key the type of elements in priority queue
 */
public interface PriorityQueue<Key extends Comparable<Key> > {

    /**
     * Enqueue a new key to the priority queue.
     * @param newKey the new key to enqueue
     */
    void enqueue(Key newKey);

    /**
     * Dequeue the "next in line" key in the priority queue.
     * @return "next in line" key
     * @throws NoSuchElementException if the priority queue is empty
     */
    Key dequeue();

    /**
     * Return the "next in line" key without removing it.
     * @return the "next in line" key
     * @throws NoSuchElementException if the priority queue is empty
     */
    Key top();

    /**
     * Check whether the priority queue is empty.
     * @return true if the priority queue is empty; false otherwise
     */
    boolean isEmpty();

    /**
     * Return the number of keys in the priority queue
     * @return the number of keys in priority queue
     */
    int size();
}