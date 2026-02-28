import static org.junit.Assert.*;
import org.junit.Test;

public class PalindromeCollectorImpTest {
    
    /*
     * empty collector is empty, has size 0 and is palindrome and repok
     */
    @Test
    public void emptyCollector() {
        PalindromeCollectorImp collector = new PalindromeCollectorImp(3);
        assertTrue(collector.isEmpty());
        assertEquals(0, collector.size());
        assertTrue(collector.isPalindrome());
        assertTrue(collector.repOK());
    }

    /*
     * single element collector is not empty, has size 1 and is palindrome and repok
     * Also can be empty by removal
     */
    @Test
    public void singleElementCollector() {
        PalindromeCollectorImp collector = new PalindromeCollectorImp(1);
        collector.addFirst('a');
        char[] expected = {'a'};
        assertArrayEquals(expected, collector.toArray());
        assertFalse(collector.isEmpty());
        assertEquals(1, collector.size());
        assertTrue(collector.isPalindrome());
        assertTrue(collector.repOK());

        collector.removeFirst();
        assertTrue(collector.isEmpty());
    }

    /*
     * even length collector is palindrome, not empty, repok and size 4
     */
    @Test
    public void evenLengthPalindrome() {
        PalindromeCollectorImp collector = new PalindromeCollectorImp(4);
        collector.addLast('a');
        collector.addLast('b');
        collector.addLast('b');
        collector.addLast('a');
        char[] expected = {'a','b','b','a'};
        assertArrayEquals(expected, collector.toArray());
        assertFalse(collector.isEmpty());
        assertEquals(4, collector.size());
        assertTrue(collector.isPalindrome());
        assertTrue(collector.repOK());
    }

    /*
     * odd length collector is palindrome, repok, not empty and size 5
     */
    @Test
    public void oddLengthPalindrome() {
        PalindromeCollectorImp collector = new PalindromeCollectorImp(5);
        collector.addLast('r');
        collector.addLast('a');
        collector.addLast('d');
        collector.addLast('a');
        collector.addLast('r');
        char[] expected = {'r','a','d','a','r'};
        assertArrayEquals(expected, collector.toArray());
        assertFalse(collector.isEmpty());
        assertEquals(5, collector.size());
        assertTrue(collector.isPalindrome());
        assertTrue(collector.repOK());
    }

    /*
     * nonPalindrome collector after insertions which is not empty, size 4 and repok
     */
    @Test
    public void nonPalindromeAfterInsertions() {
        PalindromeCollectorImp collector = new PalindromeCollectorImp(5);
        collector.addLast('a');
        collector.addLast('b');
        collector.addLast('a');
        collector.addLast('a');
        char[] expected = {'a','b','a','a'};
        assertArrayEquals(expected, collector.toArray());
        assertFalse(collector.isEmpty());
        assertEquals(4, collector.size());
        assertFalse(collector.isPalindrome());
        assertTrue(collector.repOK());
        
    }

    /*
     * palindrome destroyed after insertion
     */
    @Test
    public void palindromeDestroyedAfterInsertion() {
        PalindromeCollectorImp collector = new PalindromeCollectorImp(4);
        collector.addLast('a');
        collector.addLast('b');
        collector.addLast('a');
        char[] expected = {'a','b','a'};
        assertArrayEquals(expected, collector.toArray());
        assertFalse(collector.isEmpty());
        assertEquals(3, collector.size());
        assertTrue(collector.isPalindrome());
        assertTrue(collector.repOK());

        collector.addFirst('c');
        char []expected2 = {'c','a','b','a'};
        assertArrayEquals(expected2, collector.toArray());
        assertFalse(collector.isEmpty());
        assertEquals(4, collector.size());
        assertTrue(collector.repOK());
        assertFalse(collector.isPalindrome());
    }

    /*
     * palindrome restored after removal
     */
    @Test
    public void palindromeRestoredAfterRemoval() {
        PalindromeCollectorImp collector = new PalindromeCollectorImp(6);
        collector.addLast('a');
        collector.addLast('b');
        collector.addLast('t');
        collector.addFirst('a');
        collector.addFirst('b');
        collector.addFirst('e');
        char[] expected = {'e','b','a','a','b','t'};
        assertArrayEquals(expected, collector.toArray());
        assertFalse(collector.isEmpty());
        assertEquals(6, collector.size());
        assertFalse(collector.isPalindrome());
        assertTrue(collector.repOK());

        collector.removeFirst();
        collector.removeLast();
        char[] expected2 = {'b','a','a','b'};
        assertArrayEquals(expected2, collector.toArray());
        assertFalse(collector.isEmpty());
        assertEquals(4, collector.size());
        assertTrue(collector.repOK());
        assertTrue(collector.isPalindrome());
    }

    /* 0. we cannot construct a collector with negative or zero capacity
     * 1. we cannot remove element in empty collector 
     * 2. we cannot add characters that not in [a,z]
     * 3. we cannot add characters when the collector is full
     */
    @Test
    public void edgeCases() {
        assertThrows(IllegalArgumentException.class, () -> new PalindromeCollectorImp(-4));
        assertThrows(IllegalArgumentException.class, () -> new PalindromeCollectorImp(0));
        PalindromeCollectorImp collector = new PalindromeCollectorImp(1);
        assertThrows(IllegalStateException.class, () -> { collector.removeFirst();});
        assertThrows(IllegalStateException.class, () -> { collector.removeLast();});
        assertThrows(IllegalArgumentException.class, () -> { collector.addFirst('A');});
        assertThrows(IllegalArgumentException.class, () -> { collector.addLast('A');});
        collector.addFirst('a');
        assertThrows(IllegalStateException.class, () -> { collector.addLast('b');});
    }

}

