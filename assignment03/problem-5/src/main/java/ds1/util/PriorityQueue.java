package ds1.util;

import ds1.TransactionWithFee;

public class PriorityQueue {
    private MaxHeapArray maxHeap;

    public PriorityQueue(int capacity) {
        maxHeap = new MaxHeapArray(capacity);
    }

    public void enqueue(TransactionWithFee value) {
        maxHeap.insert(value);
    }

    public TransactionWithFee dequeue() {
        return maxHeap.extractMax();
    }

    public TransactionWithFee next() {
        return maxHeap.getMax();
    }

    public int size() {
        return maxHeap.size();
    }

    public TransactionWithFee[] toArray() {

        // First we need to copy the heap to avoid modifying it
        MaxHeapArray tempHeap = new MaxHeapArray(maxHeap.size());
        TransactionWithFee[] originalHeap = maxHeap.getHeap();
        for (int i = 0; i < maxHeap.size(); i++) {
            tempHeap.insert(originalHeap[i]);
        }
        // Now we can extract elements from the tempHeap to get them in order
        TransactionWithFee[] sortedTransactions = new TransactionWithFee[maxHeap.size()];
        for (int i = 0; i < sortedTransactions.length; i++) {
            sortedTransactions[i] = tempHeap.extractMax();
        }
        return sortedTransactions;
    }
}