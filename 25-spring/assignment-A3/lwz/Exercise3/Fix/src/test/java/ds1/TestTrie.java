package ds1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestTrie {

    @Test
    public void testInsertAndFindMinWord() {
        Trie trie = new Trie();
        trie.insert("app");
        trie.insert("apple");
        trie.insert("able");
        trie.insert("zone");
        trie.insert("time");
        trie.insert("try");

        Trie.TrieNode root = getRoot(trie);
        String minWord = trie.findMinWord(root);
        assertEquals("able", minWord);
    }

    @Test
    public void testNextWordInTrie() {
        Trie trie = new Trie();
        trie.insert("app");
        trie.insert("apple");
        trie.insert("able");
        trie.insert("zone");
        trie.insert("time");
        trie.insert("try");

        //without backtracking
        assertEquals("apple", trie.nextWordInTrie("app"));
        assertEquals("apple", trie.nextWordInTrie("appl")); 
        assertEquals("apple", trie.nextWordInTrie("appb"));

        //with backtracking
        assertEquals("app", trie.nextWordInTrie("able")); //
        assertEquals("time", trie.nextWordInTrie("apple"));
        assertEquals("try", trie.nextWordInTrie("time"));
        assertEquals("zone", trie.nextWordInTrie("try"));
        assertNull(trie.nextWordInTrie("zone"));
    }

    @Test
    public void testFindMinWordFromMidNode() {
        Trie trie = new Trie();
        trie.insert("app");
        trie.insert("apple");
        trie.insert("able");
        trie.insert("zone");
        trie.insert("time");
        trie.insert("try");

        Trie.TrieNode root = getRoot(trie);
        Trie.TrieNode sNode = root.children['t' - 'a'];
        assertEquals("ime", trie.findMinWord(sNode));
    }

    @Test
    public void testFindNextWordFromNode() {
        Trie trie = new Trie();
        trie.insert("app");
        trie.insert("apple");
        trie.insert("able");
        trie.insert("zone");
        trie.insert("time");
        trie.insert("try");

        Trie.TrieNode root = getRoot(trie);
        Trie.TrieNode tNode = root.children['t' - 'a'];
        assertEquals("time", trie.findNextWordFromNode(tNode, "t"));
    }

    // Helper method to get the root node (reflection workaround)
    private Trie.TrieNode getRoot(Trie trie) {
        try {
            java.lang.reflect.Field rootField = Trie.class.getDeclaredField("root");
            rootField.setAccessible(true);
            return (Trie.TrieNode) rootField.get(trie);
        } catch (Exception e) {
            throw new RuntimeException("Failed to access root node", e);
        }
    }
}