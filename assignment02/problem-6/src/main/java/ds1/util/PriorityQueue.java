package ds1.util;
import ds1.TransactionWithFee;

public class PriorityQueue
{
    /* ========================= Field and Constructor ====================== */

    private final GenericPQ<TransactionWithFee> pqt; // priority queue of transactions
    public PriorityQueue() { pqt = new GenericMaxPQ<TransactionWithFee>(TransactionWithFee.class); }
    public PriorityQueue(PriorityQueue o) { 
        if ( o == null )throw new IllegalArgumentException("PriorityQueue(other): param o is null.");
        else pqt = new GenericMaxPQ<TransactionWithFee>((GenericMaxPQ<TransactionWithFee>) o.pqt);
    }

    /* ======================== Modifiers =================================== */

    public void enqueue(TransactionWithFee value) { pqt.enqueue(value); }
    public TransactionWithFee dequeue()           { return pqt.dequeue(); }
    public TransactionWithFee next()              { return pqt.next(); }
    public boolean isEmpty()                      { return pqt.isEmpty(); }
    public int size()                             { return pqt.size(); }

    // Converts the priority queue to an array sorted by priority (highest priority first)
    // Take into account that dequeue modifies the heap, so may need to copy it first
    public TransactionWithFee[] toArray() { return pqt.toArray(); }
}