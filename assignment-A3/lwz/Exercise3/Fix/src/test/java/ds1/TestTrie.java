package ds1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestTrie {

    @Test
    public void testInsertAndFindMinWord() {
        Trie trie = new Trie();
        trie.insert("save");
        trie.insert("same");
        trie.insert("state");
        trie.insert("status");
        trie.insert("time");
        trie.insert("try");

        Trie.TrieNode root = getRoot(trie);
        String minWord = trie.findMinWord(root);
        assertEquals("same", minWord);
    }

    @Test
    public void testNextWordInTrie() {
        Trie trie = new Trie();
        trie.insert("save");
        trie.insert("same");
        trie.insert("state");
        trie.insert("status");
        trie.insert("time");
        trie.insert("try");

        assertEquals("save", trie.nextWordInTrie("same")); //in lexicographic order
        assertEquals("state", trie.nextWordInTrie("save"));
        assertEquals("status", trie.nextWordInTrie("state"));
        assertEquals("time", trie.nextWordInTrie("status"));
        assertEquals("try", trie.nextWordInTrie("time"));
        assertNull(trie.nextWordInTrie("try"));
    }

    @Test
    public void testFindMinWordFromMidNode() {
        Trie trie = new Trie();
        trie.insert("save");
        trie.insert("same");
        trie.insert("state");
        trie.insert("status");
        trie.insert("time");
        trie.insert("try");

        Trie.TrieNode root = getRoot(trie);
        Trie.TrieNode sNode = root.children['s' - 'a'];
        assertEquals("ame", trie.findMinWord(sNode));
    }

    @Test
    public void testFindNextWordFromNode() {
        Trie trie = new Trie();
        trie.insert("save");
        trie.insert("same");
        trie.insert("state");
        trie.insert("status");
        trie.insert("time");
        trie.insert("try");

        Trie.TrieNode root = getRoot(trie);

        Trie.TrieNode sNode = root.children['s' - 'a'];
        assertEquals("ame", trie.findNextWordFromNode(sNode, "s"));

        Trie.TrieNode tNode = root.children['t' - 'a'];
        assertEquals("ime", trie.findNextWordFromNode(tNode, "t"));
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