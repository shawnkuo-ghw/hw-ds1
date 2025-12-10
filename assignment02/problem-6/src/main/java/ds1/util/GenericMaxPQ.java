package ds1.util;
import ds1.Transaction;
import java.lang.reflect.*;
import java.util.NoSuchElementException;

public class GenericMaxPQ<T extends Comparable<? super T> > implements GenericPQ<T>
{
    /* ======================================================================= *
     *                                Fields                                   *
     * ======================================================================= */

    private final Sequence<T> heap;
    private final Class<T> type;
    private int size;

    /* ======================================================================= *
     *                             Constructor                                 *
     * ======================================================================= */

    @SuppressWarnings("unchecked")
    public GenericMaxPQ(Class<T> newType) {
        size = 0;
        type = newType;
        heap = new ListoverLinkedList<>(type);
    }
    
    // Copy constructor
    @SuppressWarnings("unchecked")
    public GenericMaxPQ(GenericMaxPQ<T> o) {
        if ( o == null ) {
            throw new IllegalArgumentException(
                "GenericMaxPQ(other): argument other is null."
            );
        }
        GenericMaxPQ<T> other = (GenericMaxPQ<T>) o;
        this.type = other.type;
        this.size = other.size;
        heap = new ListoverLinkedList<T>((ListoverLinkedList<T>) other.heap);
    }

    /* ======================================================================= *
     *                             Modifiers                                   *
     * ======================================================================= */

    // Time complexity: O(log N)
    @Override
    public void enqueue(T elem) {
        heap.insertRear(elem); // heap[size] = elem;
        size++;
        swim(size-1);
        if ( !repOK() ) {
            throw new IllegalStateException(
                "GenericMaxPQ.enqueue(): RI is not satisfied."
            );
        }
    }

    // Time complexity: O(log N)
    @Override
    public T dequeue() {
        if ( this.isEmpty() ) {
            throw new NoSuchElementException(
                "GenericMaxPQ.dequeue(): pq is empty."
            );
        } else {
            T maxElem = heap.at(0); // T maxElem = heap[0];
            if ( size > 1 ) swap(0, size-1);
            heap.removeRear();; // heap[size-1] = null;
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
    public T next() {
        if ( this.isEmpty() )
            throw new NoSuchElementException(
                "GenericMaxPQ.next(): pq is empty."
            );
        else
            return heap.at(0); // return heap[0];
    }

    @Override
    public int size() { return this.size; }

    @Override
    public boolean isEmpty() { return this.size() == 0; }

    // Converts the priority queue to an array sorted by priority (highest priority first)
    // Take into account that dequeue modifies the heap, so may need to copy it first
    @Override
    @SuppressWarnings("unchecked")
    public T[] toArray() {
        GenericMaxPQ<T> auxPQ = new GenericMaxPQ<T>(this);
        // construct a new array of type T and of size capacity
        T[] array = (T[]) Array.newInstance(type, this.size);
        // pop all elements from auxPQ into array
        int i = 0;
        while ( !auxPQ.isEmpty() ) array[i++] = auxPQ.dequeue(); // dequeue: O(log N)
        return array;
    }

    /* ======================================================================= *
     *                          Private Methods                                *
     * ======================================================================= */
    
    // Representation Invariant Checker of max priority queue
    private boolean repOK() {
        boolean is_RI_satisfified = true;
        int i = this.size - 1;
        while ( is_RI_satisfified && i > 0 ) {
            int parent = PARENT(i);
            if ( less(parent, i) ) is_RI_satisfified = false;
            else i--;
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
    private boolean less(int i, int j) {
        if ( i == j ) 
            throw new IllegalArgumentException(
                "GenericMaxPQ.less(): i should not be equal to j."
            );
        else
            // return heap[i].compareTo(heap[j]) < 0;
            return heap.at(i).compareTo(heap.at(j)) < 0;
    }

    private void swim(int k) {
        int parent = PARENT(k);
        while ( k > 0 && less(parent, k) )
        {
            swap(parent, k);
            k = parent;
            parent = PARENT(k);
        }
    }

    private void sink(int k) {
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
            if ( less(k, child) ) {
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
    private void swap(int i, int j) {
        if ( !(0 <= i && i < j && j < size) ) {
            throw new IllegalStateException(
                "GenericMaxPQ.swap(): it should be that 0 <= i < j < size."
            );
        }        
        T temp = heap.at(i);          // T temp = heap[i];
        heap.updateAt(i, heap.at(j)); // heap[i] = heap[j];
        heap.updateAt(j, temp);       // heap[j] = temp;   
    }
}