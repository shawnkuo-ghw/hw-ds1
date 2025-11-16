package ds1.utils;

import ds1.Transaction;

public interface Queue {
    // Inserts methods for queues
    void enqueue(Transaction elem);
    Transaction dequeue();
    Transaction front();
    Transaction rear();
    boolean isEmpty();
    boolean isFull();
    int size();
    Transaction[] toArray();
    void remove(int index);
}