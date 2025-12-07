package ds1.util;
import ds1.TransactionWithFee;

public class PriorityQueue
{
    /* ========================= Field and Constructor ====================== */
    private final GenericPQ<TransactionWithFee> pqt; // priority queue of transaction
    public PriorityQueue(int capacity) {
        pqt = new GenericMaxPQ<TransactionWithFee>(TransactionWithFee.class, capacity);
    }
    public PriorityQueue(PriorityQueue o) { 
        if (o != null) pqt = new GenericMaxPQ<TransactionWithFee>((GenericMaxPQ<TransactionWithFee>) o.pqt);
        else throw new IllegalArgumentException("PriorityQueue(other): other is null.");
    }

    /* ======================= Operations =================================== */
    public void enqueue(TransactionWithFee value) { pqt.enqueue(value); }
    public TransactionWithFee dequeue() { return pqt.dequeue(); }
    public TransactionWithFee next() { return pqt.next(); }
    public int size() { return pqt.size(); }
    public boolean isFull() { return pqt.isFull(); }
    public boolean isEmpty() { return pqt.isEmpty(); }

    // Converts the priority queue to an array sorted by priority (highest priority first)
    // Take into account that dequeue modifies the heap, so may need to copy it first
    public TransactionWithFee[] toArray() { return pqt.toArray(); }
}