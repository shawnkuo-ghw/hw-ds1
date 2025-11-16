package ds1;

import javax.management.RuntimeErrorException;

// implement a Heap data structure over an array
public class MaxHeapArray {
    // the array that stores the heap
    private int[] heap;
    // the number of elements in the heap
    private int size;
    
    // constructor
    public MaxHeapArray(int capacity) {
        heap = new int[capacity];
        size = 0;
    }

    public int[] getHeap() {
        return heap;
    }

    public MaxHeapArray(int[] a) {
        if(!testMaxHeapProperty(a)) {
            MaxHeapArray mh = buildMaxHeap(a);
            heap = mh.heap;
            size = mh.size;
        }
        else {
            heap = a;
            size = a.length;
        }         
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
    public int getMax() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }
        throw new RuntimeException("Not Implemented");
    }

    // insert a new element into the heap
    public void insert(int x) {
        if (size == heap.length) {
            throw new IllegalStateException("Heap is full");
        }
        heap[size] = x;
        size++;
        // This is similar to HEAP-INCREASE-KEY
        heapifyUp(size - 1);
    }

    // helper method to restore the heap property
    // after inserting a new element at the end
    // It is a simplified version of HEAP-INCREASE-KEY
    // we do not need to check for the key since we assume
    // it will correctly invoked on a new element

    private void heapifyUp(int i) {
        throw new RuntimeException("Not Implemented");
    }

    // helper method to swap two elements in the heap
    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    // remove and return the maximum element in the heap
    public int extractMax() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }
        throw new RuntimeException("Not Implemented");
    }

    // helper method to restore the heap property
    // after removing the root element
    private void heapifyDown(int i) {
        throw new RuntimeException("Not Implemented");
    }

    // build maxheap
    public static MaxHeapArray buildMaxHeap(int a[]) {
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
    // Restore the heap property if necessary
    public void changeValue(int i, int x) {
        if (i < 0 || i >= size) {
            throw new IllegalArgumentException();
        }
        heap[i] = x;
        throw new RuntimeException("Not Implemented");
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
    

}