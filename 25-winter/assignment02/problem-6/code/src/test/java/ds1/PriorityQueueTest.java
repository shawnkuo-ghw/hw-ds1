package ds1;

import ds1.util.PriorityQueue;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class PriorityQueueTest
{
    PriorityQueue pq;

    @BeforeEach
    void init() { pq = new PriorityQueue(); }

    @Test
    void transactionWithFeeTest()
    {
        TransactionWithFee t1 = new TransactionWithFee("0", "A", 1, 3); // priority = 3
        TransactionWithFee t2 = new TransactionWithFee("0", "A", 1, 4); // priority = 4
        TransactionWithFee t3 = new TransactionWithFee("0", "A", 1, 1); // priority = 1
        TransactionWithFee t4 = new TransactionWithFee("0", "A", 1, 2); // priority = 2
        pq.enqueue(t1);
        pq.enqueue(t2);
        pq.enqueue(t3);
        pq.enqueue(t4);
        assertEquals(t2, pq.dequeue());
        assertEquals(t1, pq.dequeue());
        assertEquals(t4, pq.dequeue());
        assertEquals(t3, pq.dequeue());
    }

    @Test
    void transactionWithOrderTest()
    {
        TransactionWithOrder t1 = new TransactionWithOrder("0", "A", 1, 0, 1); // priority = 0 + 1/1 =   1 = 1.00
        TransactionWithOrder t2 = new TransactionWithOrder("0", "A", 1, 1, 2); // priority = 1 + 1/2 = 3/2 = 1.50
        TransactionWithOrder t3 = new TransactionWithOrder("0", "A", 1, 2, 3); // priority = 2 + 1/3 = 7/3 = 2.33
        TransactionWithOrder t4 = new TransactionWithOrder("0", "A", 1, 1, 4); // priority = 1 + 1/4 = 5/4 = 1.25
        pq.enqueue(t1);
        pq.enqueue(t2);
        pq.enqueue(t3);
        pq.enqueue(t4);
        assertEquals(t3, pq.dequeue());
        assertEquals(t2, pq.dequeue());
        assertEquals(t4, pq.dequeue());
        assertEquals(t1, pq.dequeue());
    }
}