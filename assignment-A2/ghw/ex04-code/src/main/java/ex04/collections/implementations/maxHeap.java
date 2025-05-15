package ex04.collections.implementations;
import java.util.NoSuchElementException;

import ex04.collections.interfaces.Heap;
import ex04.collections.interfaces.List;

/**
 * The implementation of interface {@code Heap} with max-heap property
 */
public class maxHeap<T extends Comparable<T> > implements Heap<T> {

    private List<T> heap;
    private int size;

    public maxHeap() {
        heap = new LinkedList<T>();
        size = 0;
    }

    private static int parent(int i)   { return i / 2; }
    private static int leftIdx(int i)  { return 2 * i; }
    private static int rightIdx(int i) { return 2 * i + 1; }

    private boolean maxHeapPropertyCheck() {
        boolean checkResult = true;
        if ( ! heap.empty() & heap.size() > 1 ) {
            int i = 1;
            while ( checkResult & i < heap.size() ) {
                T currValue = heap.get(i);
                T pareValue = heap.get(parent(i));
                if ( pareValue.compareTo(currValue) < 0 ) {
                    checkResult = false;
                }
                i ++;
            }
        }
        return checkResult;
    }

    private void maxHeapify() {

    }

    @Override
    public void push(T newElem) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'push'");
    }

    @Override
    public T pop() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'pop'");
    }

    @Override
    public T top() {
        if ( heap.empty() ) {
            throw new NoSuchElementException("maxHeap.top(): the heap is empty.");
        }
        return heap.first();
    }

    @Override
    public boolean empty() { return heap.empty(); }

    @Override
    public int size() { return heap.size(); }   
}
