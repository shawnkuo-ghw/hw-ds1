package ds1;

/**
 * @param <T> element type
 */
public interface Stack<T> {
    void push(T elem);
    T pop();
    T readTop();
    boolean isEmpty();
    boolean isFull();
    int size();
}
