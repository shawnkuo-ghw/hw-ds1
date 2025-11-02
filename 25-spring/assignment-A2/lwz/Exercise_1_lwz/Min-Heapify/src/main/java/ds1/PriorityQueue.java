package ds1;

public class PriorityQueue {
    private MaxHeapArray maxHeap;

    public PriorityQueue(int capacity) {
        maxHeap = new MaxHeapArray(capacity);
    }

    public void enqueue(int value) {
        maxHeap.insert(value);
    }

    public int dequeue() {
        return maxHeap.extractMax();
    }

    public int next() {
        return maxHeap.getMax();
    }
}