package ds1;

public interface Queue<T> {
    void enqueue(T elem);
    T dequeue();
    T front();
    T rear();
    boolean isEmpty();
    boolean isFull();
}


