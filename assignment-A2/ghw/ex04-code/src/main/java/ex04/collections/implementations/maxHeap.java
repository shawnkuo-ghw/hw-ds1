package ex04.collections.implementations;

import java.util.NoSuchElementException;
import ex04.collections.interfaces.Heap;
import ex04.collections.interfaces.List;

/**
 * The implementation of interface {@code Heap} with max-heap property
 * @see ex04.collections.interfaces.Heap
 */
public class maxHeap<T extends Comparable<T> > implements Heap<T> {

    private List<T> heap;
    
    private static int PARENT(int i) { return (i - 1) / 2; }
    private static int LEFT(int i)   { return 2 * i + 1; }
    private static int RIGHT(int i)  { return 2 * i + 2; }

    private boolean less(int i, int j)
    { return heap.get(i).compareTo(heap.get(j)) < 0; }

    private boolean maxHeapPropertyCheck()
    {
        boolean checkResult = true;
        if ( !heap.empty() && heap.size() > 1 ) {
            int i = 1;
            while ( checkResult & i < heap.size() ) {
                T currValue = heap.get(i);
                T pareValue = heap.get(PARENT(i));
                if ( pareValue.compareTo(currValue) < 0 ) {
                    checkResult = false;
                } else {
                    i ++;
                }
            }
        }
        return checkResult;
    }

    private void swim(int k)
    {   
        while ( k > 0 && this.less(PARENT(k), k) ) {
            heap.swap(PARENT(k), k);
            k = PARENT(k);
        }
    }

    private void sink(int k)
    {
        boolean hasChildren = true;
        boolean lessThanChildren = true;
        while ( hasChildren & lessThanChildren )
        {
            if ( LEFT(k) >= heap.size() ) {
                hasChildren = false;
            } else {
                int largest = LEFT(k);
                if ( RIGHT(k) < heap.size() && this.less(largest, RIGHT(k)) ) {
                    largest = RIGHT(k);
                }

                if ( less(k, largest) ) {
                    heap.swap(k, largest);
                    k = largest;
                } else {
                    lessThanChildren = false;
                }
            }
        }
    }

    public maxHeap() { heap = new LinkedList<T>(); }

    @Override
    public void push(T newElem)
    {
        heap.append(newElem);
        this.swim(heap.size() - 1);
        if ( !maxHeapPropertyCheck() ) {
            throw new IllegalStateException("maxHeap.push(): max-heap property is not satisfied.");
        }
        // printAsTree();
    }

    @Override
    public T pop()
    {
        if ( heap.empty() ) {
            throw new NoSuchElementException("maxHeap.pop(): heap is empty.");
        }
        T popedElem = heap.first();
        if ( heap.size() > 1 ) {
            heap.swap(0, heap.size() - 1);
            heap.removeLast();
            this.sink(0);
        } else {
            heap.removeFirst();
        }
        if ( !maxHeapPropertyCheck() ) {
            throw new IllegalStateException("maxHeap.pop(): max-heap property is not satisfied.");
        }
        printAsTree();
        return popedElem;
    }

    @Override
    public T top()
    {
        if ( heap.empty() ) {
            throw new NoSuchElementException("maxHeap.top(): heap is empty.");
        }
        return heap.first();
    }   

    @Override
    public void setTop(T newTop)
    {
        if ( heap.empty() ) {
            throw new NoSuchElementException("maxHeap.setTop(): heap is empty.");
        }
        if ( heap.first().compareTo(newTop) < 0 ) {
            heap.setFirst(newTop);
        } else {
            heap.setFirst(newTop);
            sink(0);
        }
        if ( !maxHeapPropertyCheck() ) {
            throw new IllegalStateException("maxHeap.setTop(): max-heap property is not satisfied.");
        }
    }

    @Override
    public boolean empty() { return heap.empty(); }

    @Override
    public int size() { return heap.size(); }
    
    public void printAsTree() {
        System.out.println("Heap:");
        printHeapAsTree(0, "");
        System.out.println("\n");
    }
    private void printHeapAsTree(int i, String prefix) {
        if (i < heap.size()) {
            printHeapAsTree(RIGHT(i), prefix + "    ");
            System.out.println(prefix + heap.get(i));
            printHeapAsTree(LEFT(i), prefix + "    ");
        }
    }
}