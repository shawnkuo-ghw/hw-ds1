package mygroup;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class InsertToMinheapTest {

    @Test
    public void test1(){
        Node<Integer> root = new Node<>(8);
        assertSame(root, InsertToMinheap.parentOfNew(root));
        InsertToMinheap.insertToMinheap(root, 4);
        assertSame(4, root.value);
    }

    @Test
    public void test2(){
        Node<Integer> root = new Node<>(5);
        root.left = new Node<>(8);
        root.left.parent = root;
        root.right = new Node<>(6);
        root.right.parent = root;
        root.left.left = new Node<>(9);
        root.left.left.parent = root.left; // construct a min-heap.
        assertSame(root.left, InsertToMinheap.parentOfNew(root));
        Node<Integer> newNode = new Node<>(7);
        InsertToMinheap.insertToMinheap(root, 7);
        assertTrue(minHeapRep.minHeapRepOK(root));
    }

    @Test
    public void test3(){
        Node<Integer> root = null;
        assertSame(null, InsertToMinheap.parentOfNew(root));
        root = InsertToMinheap.insertToMinheap(root, 4);
        assertSame(4, root.value);
    }

}
