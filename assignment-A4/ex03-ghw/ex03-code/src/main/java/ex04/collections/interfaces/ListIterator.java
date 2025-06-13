package ex04.collections.interfaces;

/**
 * The interface of the iterator of list
 * @param T the type of elements in the list
 */
public interface ListIterator<T>
{
    /**
     * Check whether iterator can point to the next element in the list
     * @return {@code true} if current element is not null; {@code false} otherwise
     */
    boolean hasNext();

    /**
     * Return current element and iterator points to the next element in the list (if any)
     * @return current element pointed by iterator
     */
    T getNext();
}