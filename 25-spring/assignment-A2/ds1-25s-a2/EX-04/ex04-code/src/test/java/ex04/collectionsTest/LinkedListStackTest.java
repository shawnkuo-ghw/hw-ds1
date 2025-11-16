package ex04.collectionsTest;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.EmptyStackException;
import ex04.collections.interfaces.Stack;
import ex04.collections.implementations.LinkedListStack;

public class LinkedListStackTest {

    private Stack<Integer> stack;

    @BeforeEach
    public void init() {
        stack = new LinkedListStack<Integer>();
    }

    @Test
    public void Test() {
        assertTrue(stack.empty());
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        // stack = [5, 4, 3, 2, 1]
        assertFalse(stack.empty());
        assertEquals(5, stack.size());
        assertEquals(5, stack.top());
        assertEquals(5, stack.pop());
        assertEquals(4, stack.pop());
        // stack = [3, 2, 1]
        stack.setTop(6);
        // stack = [6, 2, 1]
        assertEquals(6, stack.pop());
        assertEquals(2, stack.pop());
        assertEquals(1, stack.pop());
        assertThrows(EmptyStackException.class, () -> stack.pop());   
    }
}
