package problem3;

import static org.junit.Assert.*;
import org.junit.Test;

public class PalindromeCollectorImpTest {
    
    /*
     * empty collector is empty, has size 0 and is palindrome
     */
    @Test
    public void emptyCollector() {
        PalindromeCollectorImp collector = new PalindromeCollectorImp(3);
        assertTrue(collector.isEmpty());
        assertEquals(0, collector.size());
        assertTrue(collector.isPalindrome());
    }

    /*
     * single element collector is not empty, has size 1 and is palindrome
     */
    @Test
    public void singleElementCollector() {
        PalindromeCollectorImp collector = new PalindromeCollectorImp(4);
        collector.addFirst('a');
        assertFalse(collector.isEmpty());
        assertEquals(1, collector.size());
        assertTrue(collector.isPalindrome());
    }

    /*
     * even length collector is palindrome
     */
    @Test
    public void evenLengthPalindrome() {
        PalindromeCollectorImp collector = new PalindromeCollectorImp(4);
        collector.addLast('a');
        collector.addLast('b');
        collector.addLast('b');
        collector.addLast('a');
        assertTrue(collector.isPalindrome());
    }

    /*
     * odd length collector is palindrome
     */
    @Test
    public void oddLengthPalindrome() {
        PalindromeCollectorImp collector = new PalindromeCollectorImp(5);
        collector.addLast('r');
        collector.addLast('a');
        collector.addLast('d');
        collector.addLast('a');
        collector.addLast('r');
        assertTrue(collector.isPalindrome());
    }

    /*
     * nonPalindrome collector after insertions
     */
    @Test
    public void nonPalindromeAfterInsertions() {
        PalindromeCollectorImp collector = new PalindromeCollectorImp(5);
        collector.addLast('a');
        collector.addLast('b');
        collector.addLast('c');
        collector.addLast('a');
        assertFalse(collector.isPalindrome());
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
        assertTrue(collector.isPalindrome());

        collector.addFirst('c');
        assertEquals(4, collector.size());
        assertTrue(collector.repOK());
        assertFalse(collector.isPalindrome());
    }


}

