package ds1;

class Node<E> {
    E data;
    Node<E> next;
}

public class ListoverLinkedList<E> implements Sequence<E>{
    // implement Sequence using LinkedList
    private Node<E> head;
    private Node<E> current;

    public ListoverLinkedList() {
        head = null;
    }

    public LinkedListIterator<E> getIterator() {
        return new LinkedListIterator<E>(head);
    }

    public E at(int i) {
        int startPosition = 0;
        Node<E> curr = head;
        for (int j = startPosition; j < i; j++) {
            curr = curr.next;
        }
        return curr.data;
    }

    public int length() {
        int count = 0;
        Node<E> curr = head;
        while (curr != null) {
            count++;
            curr = curr.next;
        }
        return count;
    }

    public void insertAt(int i, E elem) {
        if (i == 0) {
            insertFront(elem);
        } else {
            Node<E> newNode = new Node<E>();
            newNode.data = elem;
            Node<E> prev = findPrev(i);
            newNode.next = prev.next;
            prev.next = newNode;
        }
    }

    private Node<E> findPrev(int i) {
        Node<E> curr = head;
        for (int j = 0; j < i-1; j++) {
            curr = curr.next;
        }
        return curr;
    }

    public void removeAt(int i) {
        if (i == 0) {
            removeFront();
        } else {
            Node<E> prev = findPrev(i);
            prev.next = prev.next.next;
        }
    }

    public void insertFront(E elem) {
        Node<E> newNode = new Node<E>();
        newNode.data = elem;
        newNode.next = head;
        head = newNode;
    }

    public void removeFront() {
        if (head != null) {
            head = head.next;
        }
    }

    public void insertRear(E elem) {
        Node<E> newNode = new Node<E>();
        newNode.data = elem;
        newNode.next = null;
        if (head == null) {
            head = newNode;
        } else {
            Node<E> curr = head;
            while (curr.next != null) {
                curr = curr.next;
            }
            curr.next = newNode;
        }
    }

    // append another list to the end of this list
    public void append(ListoverLinkedList<E> other) {
        if (head == null) {
            head = other.head;
        } else {
            Node<E> curr = head;
            while (curr.next != null) {
                curr = curr.next;
            }
            curr.next = other.head;
        }
    }

    // insert elem in sorted order
    public void insertSorted(E elem) {        
        Node<E> insert = new Node<E>();
        insert.data = elem;
        if (head == null) {
            head = insert;
        } else {
            // You dont need to create a node again 
            // you have newNode, I renamed it to insert
            // Node<T> insert = new Node<T>();
            // insert.data = elem;
            Node<E> curr = head;
            // prev is the previos one, at the beguining it is null 
            // not curr
            Node<E> prev = null;
            while (curr != null && (Integer) elem >= (Integer) curr.data){
                prev = curr;
                curr = curr.next;
            }
            // now I know that elem < curr.data
            // or curr == null 
            // and prev is the previous one
            // Case 1: prev is null => insert at the front
            if (prev == null) {
                insert.next = head;
                head = insert;                
            }
            // Case 2 : prev is not null => insert after prev
            else {
                insert.next = prev.next;
                prev.next = insert;
            }
        }
    }

    public void start() {
        current = head;  // or the first node
    }

    public void advance() {
        current = current.next;
    }

    public boolean isEnd() {
        return current == null;
    }

    public Boolean containsAll(Sequence<E> seq){
        Boolean seq1_in_seq2 = true;
        SequenceIterator<E> iter_seq1 = this.getIterator();
        while (iter_seq1.hasNext() && seq1_in_seq2) {
            E elem_seq1 = iter_seq1.next();
            SequenceIterator<E> iter_seq2 = seq.getIterator();
            Boolean found = false;
            while (iter_seq2.hasNext() && !found) {
                E elem_seq2 = iter_seq2.next();
                if (elem_seq1 == elem_seq2) {
                    found = true;
                }
            }
            seq1_in_seq2 = found;
        }
        return seq1_in_seq2;
    }

    public int indexOf(E elem){
        SequenceIterator<E> iter_seq = this.getIterator();
        int index = -1;
        E curr_elem = null;
        while (iter_seq.hasNext() && curr_elem != elem) {
            curr_elem = iter_seq.next();
            index++;
        }
        return index;
    }

    public E examine() {
        return current.data;
    }
}
