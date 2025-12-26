package ds1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class PatriciaTrieTest {

    @Test
    public void insertPositiveTest() {
        PatriciaTrie trie = new PatriciaTrie();
        trie.insert("essence");
        trie.insert("essential");
        trie.insert("estimate");
        trie.insert("estimation");
        trie.insert("sublease");
        trie.insert("sublimate");
        trie.insert("sublime");     
        trie.insert("subliminal");

        assertTrue(trie.search("essence"));
        assertTrue(trie.search("sublime"));
        assertTrue(trie.search("sublimate"));
        
        PatriciaTrieNode root = trie.root;
        //sNode is subl
        PatriciaTrieNode sublNode = root.getChild('s');
        assertNotNull(sublNode);
        assertEquals(4, sublNode.index);
        assertNull(sublNode.fullWord);
        assertFalse(sublNode.isEndOfWord);

        // sublilmNode is sublim
        PatriciaTrieNode sublimNode = sublNode.getChild('i'); 
        assertNotNull(sublimNode);
        assertEquals(sublNode, sublimNode.parent);
        assertEquals(6, sublimNode.index);
        assertNull(sublimNode.fullWord);
        assertFalse(sublimNode.isEndOfWord);
        
        PatriciaTrieNode sublimeNode = sublimNode.getChild('e');
        assertNotNull(sublimeNode);
        assertTrue(sublimeNode.isEndOfWord);
        assertEquals(0, sublimeNode.countChildren());
        assertEquals(sublimNode, sublimeNode.parent);
        assertEquals(7, sublimeNode.index);
        assertEquals("sublime", sublimeNode.fullWord);

        PatriciaTrieNode sublimateNode = sublimNode.getChild('a');
        assertNotNull(sublimateNode);
        assertTrue(sublimateNode.isEndOfWord);
        assertEquals(sublimNode, sublimateNode.parent);
        assertEquals(9, sublimateNode.index);
        assertEquals("sublimate", sublimateNode.fullWord);
    }

    @Test
    public void insertNegativeTest1() {
        // null case
        PatriciaTrie trie = new PatriciaTrie();
        assertThrows(IllegalArgumentException.class, () -> {
            trie.insert(null);});
    }

    @Test
    public void insertNegativeTest2() {
        // invalid case
        PatriciaTrie trie = new PatriciaTrie();
        assertThrows(IllegalArgumentException.class, () -> {
            trie.insert("Apple");});
        assertThrows(IllegalArgumentException.class, () -> {
            trie.insert("apple#");});
        assertThrows(IllegalArgumentException.class, () -> {
            trie.insert("apple1");});
    }

    @Test
    public void removePositiveTest() {
        PatriciaTrie trie = new PatriciaTrie();
        trie.insert("essence");
        trie.insert("essential");
        trie.insert("estimate");
        trie.insert("estimation");
        trie.insert("sublease");
        trie.insert("sublimate");
        trie.insert("sublime");

        assertTrue(trie.search("essential"));
        assertTrue(trie.search("essence"));

        // Capture the essence node before removal to verify structural integrity
        // "essential" extends "essence" via char 't'
        PatriciaTrieNode essenceNode = trie.findNode("essence");
        assertNotNull(essenceNode);

        trie.remove("essential");
        //then essential should disappear
        assertFalse(trie.search("essential"));
        assertNotNull(trie.findNode("essence"));
        assertNull(essenceNode.getChild('t'));
        assertEquals(0, essenceNode.countChildren()); // essence is a leaf
        //another words don't change
        assertTrue(trie.search("essence"));
        assertTrue(trie.search("estimate"));
        assertTrue(trie.search("sublime"));
    }

    @Test
    public void removeNegativeTest1() {
        // null case
        PatriciaTrie trie = new PatriciaTrie();
        assertThrows(IllegalArgumentException.class, () -> {
            trie.remove(null);});
    }

    @Test
    public void removeNegativeTest2() {
        // invalid input
        PatriciaTrie trie = new PatriciaTrie();
        assertThrows(IllegalArgumentException.class, () -> {
            trie.remove("Hello");});
        assertThrows(IllegalArgumentException.class, () -> {
            trie.remove("hello!");});
        assertThrows(IllegalArgumentException.class, () -> {
            trie.remove("hello1");});
    }

    @Test
    public void searchPositiveTest() {
        PatriciaTrie trie = new PatriciaTrie();
        trie.insert("essence");
        trie.insert("essential");
        trie.insert("estimate");
        trie.insert("estimation");
        trie.insert("sublease");
        trie.insert("sublimate");
        trie.insert("sublime");
        trie.insert("subliminal");

        assertTrue(trie.search("essence"));
        assertTrue(trie.search("subliminal"));
        assertTrue(trie.search("sublease"));
        
        assertFalse(trie.search("sub")); 
        assertFalse(trie.search("es"));
        assertFalse(trie.search("sublim"));
    }

    @Test
    public void searchNegativeTest1() {
        // null case
        PatriciaTrie trie = new PatriciaTrie();
        assertThrows(IllegalArgumentException.class, () -> {
            trie.search(null);});
    }

    @Test
    public void searchNegativeTest2() {
        // empty search
        PatriciaTrie trie = new PatriciaTrie();
        assertFalse(trie.search(""));
    }

    @Test
    public void searchNegativeTest3() {
        // invalid input case
        PatriciaTrie trie = new PatriciaTrie();
        assertThrows(IllegalArgumentException.class, () -> {
            trie.remove("Hello");});
        assertThrows(IllegalArgumentException.class, () -> {
            trie.remove("hello!");});
        assertThrows(IllegalArgumentException.class, () -> {
            trie.remove("hello1");});
    }

    @Test
    public void toPatriciaTriePositiveTest() {
        PatriciaTrie trie = new PatriciaTrie();
        trie.insert("essence");
        trie.insert("essential");
        trie.insert("estimate");
        trie.insert("estimation");
        trie.insert("sublease");
        trie.insert("sublimate");
        trie.insert("sublime");
        trie.insert("subliminal");

        trie.toPatriciaTrie();
        PatriciaTrieNode root = trie.root;

        // First we test the branch of s, subl should be compressed to s
        PatriciaTrieNode sublNode = root.getChild('s');
        assertNotNull(sublNode);
        assertEquals(4, sublNode.index);
        assertNull(sublNode.fullWord);
        
        // After subl, there should be 2 children: e and i 
        assertEquals(2, sublNode.countChildren());
        PatriciaTrieNode childE = sublNode.getChild('e');
        PatriciaTrieNode childI = sublNode.getChild('i');
        assertNotNull(childE);
        assertNotNull(childI);

        //e and i should be child of sublNode
        assertEquals(sublNode, childE.parent);
        assertEquals(sublNode, childI.parent);

        //check sublease, ease should be compressed to e and e should be endOfWord
        assertTrue(childE.isEndOfWord);
        assertEquals(0, childE.countChildren());
        assertEquals(8, childE.index);
        assertEquals("sublease", childE.fullWord);

        //check branch of i
        // childI should have e, a and i, then e is endOfWord
        assertEquals(6, childI.index);
        assertNull(childI.fullWord);
        PatriciaTrieNode sublimeEnd = childI.getChild('e');
        assertNotNull(sublimeEnd);
        assertTrue(sublimeEnd.isEndOfWord);
        assertEquals(7, sublimeEnd.index);
        assertEquals("sublime", sublimeEnd.fullWord);

        //test the branch of e, es should be compressed to e
        PatriciaTrieNode esNode = root.getChild('e');
        assertNotNull(esNode);
        assertEquals(2, esNode.index);
        assertNull(esNode.fullWord);
        // esNode should have s and t children
        assertEquals(2, esNode.countChildren());
        assertNotNull(esNode.getChild('s')); 
        assertNotNull(esNode.getChild('t'));
    }

    @Test
    public void toPatriciaTrieNegativeTest() {
        // empty case
        PatriciaTrie trie = new PatriciaTrie();
        trie.toPatriciaTrie();
        assertEquals(0, trie.root.countChildren());
    }
}