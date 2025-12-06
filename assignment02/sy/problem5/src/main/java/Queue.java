public interface Queue {
    void enqueue(IndexNode elem);
    IndexNode dequeue();
    IndexNode front();
    IndexNode rear();
    boolean isEmpty();
    boolean isFull();
}

