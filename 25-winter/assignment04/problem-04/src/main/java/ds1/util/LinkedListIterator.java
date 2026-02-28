package ds1.util;


// Implements the Iterator interface for a linked list
// Example usage:
// ListOverLinkedList<Integer> list = new ListOverLinkedList<>();
// LinkedListIterator<Integer> iter = list.getIterator();
// while (iter.hasNext()) {
//     System.out.println(iter.next());
// }
public class LinkedListIterator<T> implements SequenceIterator<T> {
    ListoverLinkedList<T>.Node<T> curr;

    public LinkedListIterator(ListoverLinkedList<T> list) {
        curr = list.head;
    }

    public boolean hasNext() {
        return curr != null;
    }

    public T next() {
        T data = curr.data;
        curr = curr.next;
        return data;
    }
}
