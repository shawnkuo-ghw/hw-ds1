package ds1.util;

import ds1.TransactionWithFee;

/** 
 * Max-Heap implementation using an array
 * for managing TransactionWithFee objects
 **/
public class MaxHeapArray {
    // the array that stores the heap
    private TransactionWithFee[] heap;
    // the number of elements in the heap
    private int size;
    
    // constructor
    public MaxHeapArray(int capacity) {
        heap = new TransactionWithFee[capacity];
        size = 0;
    }

    public TransactionWithFee[] getHeap() {
        return heap;
    }

    // return the number of elements in the heap
    public int size() {
        return size;
    }

    // return true if the heap is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // return the index of the parent of the element at index i
    private int parent(int i) {
        return (i - 1) / 2;
    }

    // return the index of the left child of the element at index i
    private static int leftChild(int i) {
        return 2 * i + 1;
    }

    // return the index of the right child of the element at index i
    private static int rightChild(int i) {
        return 2 * i + 2;
    }

    // return the maximum element in the heap
    public TransactionWithFee getMax() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }
        return heap[0];
    }

    // insert a new element into the heap
    public void insert(TransactionWithFee x) {
        if (size == heap.length) {
            throw new IllegalStateException("Heap is full");
        }
        heap[size] = x;
        size++;
        heapifyUp(size - 1);
    }

    // helper method to restore the heap property
    // after inserting a new element at the end
    private void heapifyUp(int i) {
        if (i != 0 && heap[parent(i)].compareTo(heap[i]) < 0) {
            swap(i, parent(i));
            heapifyUp(parent(i));
        }
    }

    // helper method to swap two elements in the heap
    private void swap(int i, int j) {
        TransactionWithFee temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    // remove and return the maximum element in the heap
    public TransactionWithFee extractMax() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }
        TransactionWithFee max = heap[0];
        heap[0] = heap[size - 1];
        size--;
        heapifyDown(0);
        return max;
    }

    // helper method to restore the heap property
    // after removing the root element
    private void heapifyDown(int i) {
        int left = leftChild(i);
        int right = rightChild(i);
        int largest = i;
        if (left < size && heap[left].compareTo(heap[largest]) > 0) {
            largest = left;
        }
        if (right < size && heap[right].compareTo(heap[largest]) > 0) {
            largest = right;
        }
        if (largest != i) {
            swap(i, largest);
            heapifyDown(largest);
        }
    }

    // build maxheap
    public static MaxHeapArray buildMaxHeap(TransactionWithFee a[]) {
        MaxHeapArray maxHeap = new MaxHeapArray(a.length);
        maxHeap.size = a.length;
        maxHeap.heap = a;
        for (int i = maxHeap.size / 2 - 1; i >= 0; i--) {
            maxHeap.heapifyDown(i);
        }
        return maxHeap;
    }

    // test maxheap property in an array
    public static boolean testMaxHeapProperty(int a[]) {
        return testMaxHeapProperty(a, 0);
    }

    // test maxheap property for the subtree rooted at i
    private static boolean testMaxHeapProperty(int a[], int i) {
        if (i >= a.length) {
            return true;
        }
        int left = leftChild(i);
        int right = rightChild(i);
        if (left < a.length && a[i] < a[left]) {
            return false;
        }
        if (right < a.length && a[i] < a[right]) {
            return false;
        }
        return testMaxHeapProperty(a, left) && testMaxHeapProperty(a, right);
    }

    // change the value of the element at index i
    // to a new value x
    public void changeValue(int i, TransactionWithFee x) {
        if (i < 0 || i >= size) {
            throw new IllegalArgumentException();
        }
        heap[i] = x;
        if (heap[i].getFee() > heap[parent(i)].getFee()) {
            heapifyUp(i);
        } else {
            heapifyDown(i);
        }
    }
    public void printHeapAsTree() {
        System.out.println("Heap");
        printHeapAsTree(0, "");
    }

    // pretty print the Binary tree rooted at curr
    private void printHeapAsTree(int i, String prefix) {
        if (i < size) {
            printHeapAsTree(leftChild(i), prefix + "    ");
            System.out.println(prefix + heap[i]);
            printHeapAsTree(rightChild(i), prefix + "    ");
        }
    }
    public void printAsArray() {
        System.out.println("Heap");
        for (int i = 0; i < heap.length; i++) {
            System.out.print(heap[i]+" ");
        }
    }

    public boolean repOK() {
        // Check that size is non-negative and does not exceed array length
        if (size < 0 || size > heap.length) {
            return false;
        }

        // Check max-heap property
        for (int i = 0; i < size; i++) {
            int left = leftChild(i);
            int right = rightChild(i);
            if (left < size && heap[i].getFee() < heap[left].getFee()) {
                return false;
            }
            if (right < size && heap[i].getFee() < heap[right].getFee()) {
                return false;
            }
        }

        return true;
    }
    // to string
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(heap[i]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public TransactionWithFee[] toArray() {
        TransactionWithFee[] arr = new TransactionWithFee[size];
        for (int i = 0; i < size; i++) {
            arr[i] = heap[i];
        }
        return arr;
    }

}