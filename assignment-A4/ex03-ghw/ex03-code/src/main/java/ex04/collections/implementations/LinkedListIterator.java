package ex04.collections.implementations;
import ex04.collections.interfaces.*;;

public class LinkedListIterator<T> implements ListIterator<T>
{
    Node<T> curr;

    public LinkedListIterator(Node<T> head)
    { curr = head; }

    @Override
    public boolean hasNext()
    { return curr != null; }

    @Override
    public T getNext()
    {
        T currVal = curr.getValue();
        curr = curr.getNext();
        return currVal;
    }   
}