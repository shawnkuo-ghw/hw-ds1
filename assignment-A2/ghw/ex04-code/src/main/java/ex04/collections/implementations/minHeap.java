package ex04.collections.implementations;

import java.util.NoSuchElementException;
import ex04.collections.interfaces.Heap;
import ex04.collections.interfaces.List;

/**
 * The implementation of interface {@code Heap} with max-heap property
 * @see ex04.collections.interfaces.Heap
 */
public class minHeap<T extends Comparable<T> > implements Heap<T> {

    private List<T> heap;
    
    private static int PARENT(int i) { return (i - 1) / 2; }
    private static int LEFT(int i)   { return 2 * i + 1; }
    private static int RIGHT(int i)  { return 2 * i + 2; }

    private boolean greater(int i, int j)
    { return heap.get(i).compareTo(heap.get(j)) > 0; }

    private boolean minHeapPropertyCheck() {
        boolean checkResult = true;
        if ( !heap.empty() && heap.size() > 1 ) {
            int i = 1;
            while ( checkResult & i < heap.size() ) {
                T currValue = heap.get(i);
                T pareValue = heap.get(PARENT(i));
                if ( pareValue.compareTo(currValue) > 0 ) {
                    checkResult = false;
                } else {
                    i ++;
                }
            }
        }
        return checkResult;
    }

    private void swim(int k) {   
        while ( k > 0 && this.greater(PARENT(k), k) ) {
            heap.swap(PARENT(k), k);
            k = PARENT(k);
        }
    }

    private void sink(int k) {
        boolean hasChildren = true;
        boolean lessThanChildren = true;
        while ( hasChildren & lessThanChildren )
        {
            if ( LEFT(k) >= heap.size() ) {
                hasChildren = false;
            } else {
                int largest = LEFT(k);
                if ( RIGHT(k) < heap.size() && this.greater(largest, RIGHT(k)) ) {
                    largest = RIGHT(k);
                }

                if ( greater(k, largest) ) {
                    heap.swap(k, largest);
                    k = largest;
                } else {
                    lessThanChildren = false;
                }
            }
        }
    }

    public minHeap() {
        heap = new LinkedList<T>();
    }

    public minHeap(minHeap<T> otherHeap) {
        heap = new LinkedList<T>((LinkedList<T>) otherHeap.heap);
    }

    @Override
    public void push(T newElem) {
        heap.append(newElem);
        this.swim(heap.size() - 1);
        if ( !minHeapPropertyCheck() ) {
            throw new IllegalStateException("minHeap.push(): min-heap property is not satisfied.");
        }
        // printAsTree();
    }

    @Override
    public T pop()
    {
        if ( heap.empty() ) {
            throw new NoSuchElementException("minHeap.pop(): heap is empty.");
        }
        T popedElem = heap.first();
        if ( heap.size() > 1 ) {
            heap.swap(0, heap.size() - 1);
            heap.removeLast();
            this.sink(0);
        } else {
            heap.removeFirst();
        }
        if ( !minHeapPropertyCheck() ) {
            throw new IllegalStateException("minHeap.pop(): min-heap property is not satisfied.");
        }
        // printAsTree();
        return popedElem;
    }

    @Override
    public T top() {
        if ( heap.empty() ) {
            throw new NoSuchElementException("minHeap.top(): heap is empty.");
        }
        return heap.first();
    }
    
    @Override
    public void setTop(T newTop) {
        if ( heap.empty() ) {
            throw new NoSuchElementException("minHeap.setTop(): heap is empty.");
        }
        if ( heap.first().compareTo(newTop) > 0 ) {
            heap.setFirst(newTop);
        } else {
            heap.setFirst(newTop);
            sink(0);
        }
        if ( !minHeapPropertyCheck() ) {
            throw new IllegalStateException("minHeap.setTop(): min-heap property is not satisfied.");
        }
    }
    
    @Override
    public boolean empty() { return heap.empty(); }

    @Override
    public int size() { return heap.size(); }

    @Override
    public String toString() { return heap.toString(); }
    
    private void printHeapAsTree(int i, String prefix) {
        if (i < heap.size()) {
            printHeapAsTree(RIGHT(i), prefix + "    ");
            System.out.println(prefix + heap.get(i));
            printHeapAsTree(LEFT(i), prefix + "    ");
        }
    }

    public void printAsTree() {
        System.out.println("Heap:");
        printHeapAsTree(0, "");
        System.out.println("\n");
    }
    
    public void printAsArray() {
        System.out.println(this.heap.toString());
    }
}