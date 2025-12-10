package ds1.util;
import ds1.TransactionWithFee;

public class PriorityQueue
{
    /* ========================= Field and Constructor ====================== */

    private final GenericPQ<TransactionWithFee> pqt; // priority queue of transactions
    private final int capacity;                      // capacity of priority queue
    public PriorityQueue(int newCapacity) {
        pqt = new GenericMaxPQ<TransactionWithFee>(TransactionWithFee.class);
        capacity = newCapacity;
    }
    public PriorityQueue(PriorityQueue o) { 
        if ( o != null ) {
            capacity = o.capacity;
            pqt = new GenericMaxPQ<TransactionWithFee>((GenericMaxPQ<TransactionWithFee>) o.pqt);
        } else throw new IllegalArgumentException("PriorityQueue(other): other is null.");
    }

    /* ======================== Modifiers =================================== */

    public void enqueue(TransactionWithFee value) { pqt.enqueue(value); }
    public TransactionWithFee dequeue()           { return pqt.dequeue(); }
    public TransactionWithFee next()              { return pqt.next(); }
    public boolean isEmpty()                      { return pqt.isEmpty(); }
    public boolean isFull()                       { return pqt.size() == capacity; }
    public int size()                             { return pqt.size(); }

    // Converts the priority queue to an array sorted by priority (highest priority first)
    // Take into account that dequeue modifies the heap, so may need to copy it first
    public TransactionWithFee[] toArray() { return pqt.toArray(); }
}