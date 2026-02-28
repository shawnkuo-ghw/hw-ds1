package ds1;

class Node<T2> {
    T2 data;
    Node<T2> next;
}

public class ListoverLinkedList<T> implements Sequence<T>{
    // implement Sequence using LinkedList
    Node<T> head;

    public ListoverLinkedList() {
        head = null;
    }

    public LinkedListIterator<T> getIterator() {
        return new LinkedListIterator<T>(head);
    }

    public T at(int i) {
        int startPosition = 0;
        Node<T> curr = head;
        for (int j = startPosition; j < i; j++) {
            curr = curr.next;
        }
        return curr.data;
    }

    public int length() {
        int count = 0;
        Node<T> curr = head;
        while (curr != null) {
            count++;
            curr = curr.next;
        }
        return count;
    }

    public void insertAt(int i, T elem) {
        if (i == 0) {
            insertFront(elem);
        } else {
            Node<T> newNode = new Node<T>();
            newNode.data = elem;
            Node<T> prev = findPrev(i);
            newNode.next = prev.next;
            prev.next = newNode;
        }
    }

    private Node<T> findPrev(int i) {
        Node<T> curr = head;
        for (int j = 0; j < i-1; j++) {
            curr = curr.next;
        }
        return curr;
    }

    public void removeAt(int i) {
        if (i == 0) {
            removeFront();
        } else {
            Node<T> prev = findPrev(i);
            prev.next = prev.next.next;
        }
    }

    public void insertFront(T elem) {
        Node<T> newNode = new Node<T>();
        newNode.data = elem;
        newNode.next = head;
        head = newNode;
    }

    public void removeFront() {
        if (head != null) {
            head = head.next;
        }
    }

    public void insertRear(T elem) {
        Node<T> newNode = new Node<T>();
        newNode.data = elem;
        newNode.next = null;
        if (head == null) {
            head = newNode;
        } else {
            Node<T> curr = head;
            while (curr.next != null) {
                curr = curr.next;
            }
            curr.next = newNode;
        }
    }

    // append another list to the end of this list
    public void append(ListoverLinkedList<T> other) {
        if (head == null) {
            head = other.head;
        } else {
            Node<T> curr = head;
            while (curr.next != null) {
                curr = curr.next;
            }
            curr.next = other.head;
        }
    }

    // insert elem in sorted order
    public void insertSorted(T elem) {
        Node<T> insert = new Node<T>();
        insert.data = elem;
        if (head == null) {
            head = insert;
        } else {
            Node<T> curr = head;
            Node<T> prev = null;
            while (curr != null && (Integer) elem >= (Integer) curr.data){
                prev = curr;
                curr = curr.next;
            }
            if (prev == null) {
                insert.next = head;
                head = insert;
            }
            else {
                insert.next = prev.next;
                prev.next = insert;
            }
        }
    }

    public Boolean containsAll(Sequence<T> seq){
        Boolean seq1_in_seq2 = true;
        SequenceIterator<T> iter_seq1 = this.getIterator();
        while (iter_seq1.hasNext() && seq1_in_seq2) {
            T elem_seq1 = iter_seq1.next();
            SequenceIterator<T> iter_seq2 = seq.getIterator();
            Boolean found = false;
            while (iter_seq2.hasNext() && !found) {
                T elem_seq2 = iter_seq2.next();
                if (elem_seq1 == elem_seq2) {
                    found = true;
                }
            }
            seq1_in_seq2 = found;
        }
        return seq1_in_seq2;
    }

    public int indexOf(T elem){
        SequenceIterator<T> iter_seq = this.getIterator();
        int index = -1;
        T curr_elem = null;
        while (iter_seq.hasNext() && curr_elem != elem) {
            curr_elem = iter_seq.next();
            index++;
        }
        return index;
    }
}


