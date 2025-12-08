package ds1.util;

import java.util.NoSuchElementException;

/**
 * The interface of generic priority queue
 */
public interface GenericPQ<T extends Comparable<? super T>>
{
    /**
     * Enqueue an element {@code elem} into priority queue.
     * <p>Time Complexity: O(logN)</p>
     * @param elem the element to insert of type {@code T}
     * @throws IllegalStateException if the priority queue is full
     */
    void enqueue(T elem);

    /**
     * Dequeue the element with the greatest priority in priority queue.
     * <p>Time Complexity: O(logN)</p>
     * @return the element with the greatest priority of type {@code T}
     * @throws NoSuchElementException if priority queue is empty
     */
    T dequeue();

    /**
     * Return the next-in-line element in priority queue.
     * <p>Time Complexity: O(1)</p>
     * @return next-in-line element of type {@code T}
     * @throws NoSuchElementException if priority queue is empty
     */
    T next();

    /**
     * Return the number of elements in priority queue.
     * <p>Time Complexity: O(1)</p>
     * @return the number of elements in priority queue
     */
    int size();

    /**
     * Return whether the priority queue is empty.
     * <p>Time Complexity: O(1)</p>
     * @return {@code true} if priority queue is empty, {@code false} otherwise
     */
    boolean isEmpty();

    /**
     * Return whether the priority queue is full.
     * <p>Time Complexity: O(1)</p>
     * @return {@code true} if priority queue is full, {@code false} otherwise
     */
    boolean isFull();
    
    /**
     * Return the array of elements of priority queue in descending order.
     * <p>Time Complexity: O(N)</p>
     * @return the arrary of elements of type {@code T}
     */
    T[] toArray();
}