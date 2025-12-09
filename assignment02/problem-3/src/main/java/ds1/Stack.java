package ds1;

public interface Stack<T> {
    void push(T element);
    T pop();
    T readTop();
    boolean isEmpty();
    boolean isFull();
    int size();
    boolean contains(T element);
}
