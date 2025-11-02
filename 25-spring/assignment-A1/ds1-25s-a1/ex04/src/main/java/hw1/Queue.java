package hw1;

public interface Queue {
    /**
     * Insert a new element into a queue
     * Time Complexity: O(1)
     */
    public void enqueue(int elem);

    /**
     * Pop the front element of a queue
     * Time Complexity: O(1)
     */
    public int dequeue();

    /**
     * Return the data of the front element of a queue
     * Time Complexity: O(1)
     * @return the data of the front element
     */
    public int  front();

    /**
     * Set the data of the front element
     * Time Complexity: O(1)
     */
    public void setFront(int newData);

    /**
     * Check whether a queue is empty
     * Time Complexity: O(1)
     * @return true if queue is empty, false otherwise
     */
    public boolean isEmpty();

    /**
     * Check whether a queue is empty
     * Time Complexity: O(1)
     * @return true if queue is full, false otherwise
     */
    public boolean isFull();

    /**
     * Retern the number of elements in queue
     * Time Complexity: O(1)
     * @return the number of elements
     */
    public int elemNum();

    /**
     * Retern the sum of all elements in queue
     * Time Complexity: O(1)
     * @return the sum of all elements
     */
    public int elemSum();

    /**
     * Return the String representation of queue
     * Time Complexity: O(E), where E is the number of elements
     * @return string rep of queue
     */
    public String toString();
}