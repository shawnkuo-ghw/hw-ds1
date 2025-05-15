package ex04.collections.implementations;
import ex04.collections.interfaces.Heap;

/**
 * The implementation of interface {@code Heap} with max-heap property
 */
public class minHeap<T extends Comparable<T> > implements Heap<T> {

    private T[] heap;
    private int size;

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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'top'");
    }

    @Override
    public boolean empty() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'empty'");
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'size'");
    }
    
}
