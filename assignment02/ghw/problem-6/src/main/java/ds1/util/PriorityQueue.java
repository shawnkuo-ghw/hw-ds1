package ds1.util;
import ds1.Transaction;

public class PriorityQueue
{
    private final GenericPQ<Transaction> pqt; // priority queue of transaction
    public PriorityQueue(int capacity) { pqt = new GenericMaxPQ<Transaction>(Transaction.class, capacity); }
    public void enqueue(Transaction value) { pqt.enqueue(value); }
    public Transaction dequeue() { return pqt.dequeue(); }
    public Transaction next() { return pqt.next(); }
    public int size() { return pqt.size(); }

    // Converts the priority queue to an array sorted by priority (highest priority first)
    // Take into account that dequeue modifies the heap, so may need to copy it first
    public Transaction[] toArray() { return pqt.toArray(); }
}