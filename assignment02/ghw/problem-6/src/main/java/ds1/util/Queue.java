package ds1.util;

import ds1.TransactionWithFee;

public interface Queue {
    // Inserts methods for queues
    void enqueue(TransactionWithFee elem);
    TransactionWithFee dequeue();
    TransactionWithFee front();
    TransactionWithFee rear();
    boolean isEmpty();
    boolean isFull();
    int size();
    TransactionWithFee[] toArray();
    void remove(int index);
}
