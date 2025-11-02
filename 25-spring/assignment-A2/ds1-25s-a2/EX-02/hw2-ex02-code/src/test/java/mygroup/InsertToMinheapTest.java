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
        Node<Integer> root = null;
        root = InsertToMinheap.insertToMinheap(root, 8);
        root = InsertToMinheap.insertToMinheap(root, 6);
        root = InsertToMinheap.insertToMinheap(root, 9);
        root = InsertToMinheap.insertToMinheap(root, 7);
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
