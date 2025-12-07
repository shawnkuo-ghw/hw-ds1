package ds1.util;
import ds1.Transaction;
import java.util.NoSuchElementException;

public class GenericMaxPQ<T extends Comparable<T>> implements GenericPQ<T>
{
    /* ======================================================================= *
     *                                Fields                                   *
     * ======================================================================= */

    private final T[] heap; // 0-based index array
    private final int capacity;
    private final Class<T> type;
    private int size;

    /* ======================================================================= *
     *                             Constructor                                 *
     * ======================================================================= */

    @SuppressWarnings("unchecked")
    public GenericMaxPQ(Class<T> type, int capacity) {
        this.type = type;
        this.capacity = capacity;
        this.heap = (T[]) java.lang.reflect.Array.newInstance(type, capacity);
        this.size = 0;
    }
    
    // Copy constructor
    @SuppressWarnings("unchecked")
    public GenericMaxPQ(GenericMaxPQ<T> other)
    {
        if ( other == null ) {
            throw new IllegalArgumentException(
                "GenericMaxPQ(other): argument other is null."
            );
        }
        this.type = other.type;
        this.capacity = other.capacity;
        this.size = other.size;
        // construct a new array of type T and of size capacity
        this.heap = (T[]) java.lang.reflect.Array.newInstance(type, capacity);
        System.arraycopy(other.heap, 0, this.heap, 0, this.size);
    }


    /* ======================================================================= *
     *                               Setters                                   *
     * ======================================================================= */

    @Override
    public void enqueue(T elem)
    {
        if ( this.isFull() ) {
            throw new IllegalStateException(
                "GenericMaxPQ.enqueue(): pq is full."
            );
        } else {
            heap[size] = elem;
            size++;
            swim(size - 1);
            if ( !repOK() ) {
                throw new IllegalStateException(
                    "GenericMaxPQ.enqueue(): RI is not satisfied."
                );
            }
        }
    }

    @Override
    public T dequeue()
    {
        if ( this.isEmpty() ) {
            throw new NoSuchElementException(
                "GenericMaxPQ.dequeue(): pq is empty."
            );
        } else {
            T maxElem = heap[0];
            if ( size > 1 ) swap(0, size-1);
            heap[size-1] = null;
            size--;
            sink(0);
            if ( !repOK() ) {
                throw new IllegalStateException(
                    "GenericMaxPQ.dequeue(): RI is not satisfied."
                );
            }
            return maxElem;
        }
    }

    /* ======================================================================= *
     *                               Getters                                   *
     * ======================================================================= */

    @Override
    public T next()
    {
        if ( this.isEmpty() ) { 
            throw new NoSuchElementException(
                "GenericMaxPQ.next(): pq is empty."
            );
        } else {
            return heap[0];
        }
    }

    @Override
    public int size() { return this.size; }

    @Override
    public boolean isEmpty() { return this.size() == 0; }

    @Override
    public boolean isFull() { return this.size() == this.capacity; }

    // Converts the priority queue to an array sorted by priority (highest priority first)
    // Take into account that dequeue modifies the heap, so may need to copy it first
    @Override
    @SuppressWarnings("unchecked")
    public T[] toArray()
    {
        GenericMaxPQ<T> auxPQ = new GenericMaxPQ<T>(this.type, this.capacity);
        auxPQ.size = this.size;
        for (int i = 0; i < this.size; i++) auxPQ.heap[i] = this.heap[i];
        // construct a new array of type T and of size capacity
        T[] resArray = (T[]) java.lang.reflect.Array.newInstance(type, this.size);
        // pop all elements from auxPQ into resArray
        int i = 0;
        while (!auxPQ.isEmpty()) resArray[i++] = auxPQ.dequeue();
        return resArray;
    }

    /* ======================================================================= *
     *                          Private Methods                                *
     * ======================================================================= */
    
    // Representation Invariant Checker of max priority queue
    private boolean repOK()
    {
        boolean is_RI_satisfified = true;
        int i = this.size - 1;
        while ( i > 0 ) {
            int parent = PARENT(i);
            if ( !less(i, parent) ) {
                is_RI_satisfified = false;
            } else {
                i--;
            }
        }
        return is_RI_satisfified;
    }

    // index of parent node of i
    private static int PARENT(int i) { return (i - 1) / 2; }
    // index of left child of i
    private static int LEFT(int i) { return 2 * i + 1; }
    // index of right child of i
    private static int RIGHT(int i) { return 2 * i + 2; }

    // check whether element of index i is less than element of index j
    private boolean less(int i, int j)
    {
        if ( i == j ) {
            throw new IllegalArgumentException(
                "GenericMaxPQ.less(): i should not be equal to j."
            );
        } else {
            return heap[i].compareTo(heap[j]) < 0;
        }
    }

    private void swim(int k)
    {
        int parent = PARENT(k);
        while ( k > 0 && less(parent, k) )
        {
            swap(parent, k);
            k = parent;
            parent = PARENT(k);
        }
    }

    private void sink(int k)
    {
        int left_child = LEFT(k);
        int right_child = RIGHT(k);
        int child = left_child;
        boolean if_continue = true;
        while ( if_continue && left_child < this.size )
        {
            // find the bigger child among left and right children
            if ( right_child < this.size && less(left_child, right_child) )
            {
                child = right_child;
            }
            // swap the node and its child, if needed
            if ( less(k, child) )
            {
                swap(k, child);
                k = child;
                left_child = LEFT(k);
                right_child = RIGHT(k);
                child = left_child;
            } else {
                if_continue = false;
            }
        }
    }

    // swap elements of index i and j
    private void swap(int i, int j)
    {
        if ( !(0 <= i && i < j && j < this.size) ) {
            throw new IllegalStateException(
                "GenericMaxPQ.swap(): it should be the case that 0 <= i < j < size."
            );
        }
        T temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
}