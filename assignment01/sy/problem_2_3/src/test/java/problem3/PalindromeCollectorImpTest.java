package problem3;

import static org.junit.Assert.*;
import org.junit.Test;

public class PalindromeCollectorImpTest {
    
    /*
     * empty collector is empty, has size 0 and is palindrome and repok
     * Also doesn't allow to remove element
     */
    @Test
    public void emptyCollector() {
        PalindromeCollectorImp collector = new PalindromeCollectorImp(3);
        assertTrue(collector.isEmpty());
        assertEquals(0, collector.size());
        assertTrue(collector.isPalindrome());
        assertTrue(collector.repOK());
        assertThrows(IllegalStateException.class, () -> { collector.removeFirst();});
        assertThrows(IllegalStateException.class, () -> { collector.removeLast();});
    }

    /*
     * single element collector is not empty, has size 1 and is palindrome and repok
     * Also can be empty by removal
     */
    @Test
    public void singleElementCollector() {
        PalindromeCollectorImp collector = new PalindromeCollectorImp(1);
        collector.addFirst('a');
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
        assertFalse(collector.isEmpty());
        assertEquals(3, collector.size());
        assertTrue(collector.isPalindrome());
        assertTrue(collector.repOK());

        collector.addFirst('c');
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
        assertFalse(collector.isEmpty());
        assertEquals(6, collector.size());
        assertFalse(collector.isPalindrome());
        assertTrue(collector.repOK());

        collector.removeFirst();
        collector.removeLast();
        assertFalse(collector.isEmpty());
        assertEquals(4, collector.size());
        assertTrue(collector.repOK());
        assertTrue(collector.isPalindrome());
    }

    @Test
    public void edgeCases() {
        
    }

}

