package ds1.util;

import java.util.NoSuchElementException;

/**
 * The interface of generic priority queue
 */
public interface GenericPQ<T extends Comparable<? super T> >
{
    /**
     * Enqueue an element {@code elem} into priority queue.
     * <p>Time Complexity: O(log N)</p>
     * @param elem the element to insert of type {@code T}
     */
    void enqueue(T elem);

    /**
     * Dequeue the element with the greatest priority in priority queue.
     * <p>Time Complexity: O(log N)</p>
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
     * @return {@code true} if priority queue is empty; {@code false} otherwise
     */
    boolean isEmpty();

    /**
     * Return the array of elements of priority queue in descending order.
     * <p>Time Complexity: O(N log N)</p>
     * Time complexity Explained:
     * <li> - toArray is implemented by calling copy constructor first to create
     *        another priority queue, which is the same as the original one. </li>
     * <li> - Then pop every element in the new priority, which of courese is in
     *        descending order, since every time an element is poped, it is the
     *        element with the most priority in the priority queue. </li>
     * <li> - There are N elements in the new priority queue, so dequeue will be
     *        called N times. Each time dequeue is called, the time complexity is
     *        O(log N).
     * <li> - Therefore, the overall complexity is N O(log N) = O(N log N).
     * @return the arrary of elements of type {@code T}
     */
    T[] toArray();
}