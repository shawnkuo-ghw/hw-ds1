package ds1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TrieTest {

    // TrieNode Tests
    @Test
    public void countChildrenPositiveTest() {
        TrieNode node = new TrieNode();
        node.setChild('a', new TrieNode());
        node.setChild('b', new TrieNode());
        node.setChild('c', new TrieNode());
        node.setChild('d', new TrieNode());
        node.setChild('e', new TrieNode());
        assertEquals(5, node.countChildren());
    }

    @Test
    public void countChildrenNegativeTest1() {
        // Empty case
        TrieNode node = new TrieNode();
        assertEquals(0, node.countChildren());
    }

    @Test
    public void countChildrenNegativeTest2() {
        // Null case
        TrieNode node = new TrieNode();
        node.setChild('a', new TrieNode());
        node.setChild('a', null);
        assertEquals(0, node.countChildren());
    }

    @Test
    public void getOnlyChildPositiveTest() {
        TrieNode root = new TrieNode();
        TrieNode node1 = new TrieNode();
        TrieNode node2 = new TrieNode();
        TrieNode node3 = new TrieNode();
        TrieNode node4 = new TrieNode();
        root.setChild('a', node1);
        node1.setChild('b', node2);
        node2.setChild('c', node3);
        node3.setChild('d', node4);
        assertEquals(node1, root.getOnlyChild());
    }

    @Test
    public void getOnlyChildNegativeTest1() {
        // Empty case
        TrieNode node = new TrieNode();
        assertNull(node.getOnlyChild());
    }

    @Test
    public void getOnlyChildNegativeTest2() {
        // Doesn't have only child case
        TrieNode node = new TrieNode();
        node.setChild('a', new TrieNode());
        node.setChild('b', new TrieNode());
        assertNull(node.getOnlyChild());
    }

    @Test
    public void getOnlyChildNegativeTest3() {
        // Null case
        TrieNode node = new TrieNode();
        node.setChild('z', new TrieNode());
        node.setChild('z', null);
        assertNull(node.getOnlyChild());
    }

    @Test
    public void getChildPositiveTest() {
        TrieNode node = new TrieNode();
        TrieNode child1 = new TrieNode();
        TrieNode child2 = new TrieNode();
        TrieNode child3 = new TrieNode();
        TrieNode child4 = new TrieNode();
        TrieNode child5 = new TrieNode();
        node.setChild('a', child1);
        node.setChild('b', child2);
        node.setChild('c', child3);
        node.setChild('d', child4);
        node.setChild('e', child5);
        assertEquals(child3, node.getChild('c'));
    }

    @Test
    public void getChildNegativeTest1() {
        // child doesn't exist case
        TrieNode node = new TrieNode();
        assertNull(node.getChild('a'));
    }

    @Test
    public void getChildNegativeTest2() {
        // Invalid input
        TrieNode node = new TrieNode();
        assertThrows(IllegalArgumentException.class, 
            () -> { node.getChild('A');});
    }

    @Test
    public void getChildNegativeTest3() {
        // Invalid input
        TrieNode node = new TrieNode();
        assertThrows(IllegalArgumentException.class,
            () -> { node.getChild('#');});
    }

    @Test
    public void setChildPositiveTest() {
        TrieNode node1 = new TrieNode();
        TrieNode node2 = new TrieNode();
        TrieNode node3 = new TrieNode();
        TrieNode node4 = new TrieNode();
        TrieNode node5 = new TrieNode();
        node1.setChild('a', node2);
        node2.setChild('b', node3);
        node3.setChild('c', node4);
        node4.setChild('d', node5);
        assertEquals(node5, node4.getChild('d'));
        assertEquals(node4, node5.parent);
    }

    @Test
    public void setChildNegativeTest1() {
        // Invalid input
        TrieNode node = new TrieNode();
        assertThrows(IllegalArgumentException.class,
            () -> {node.setChild('A', new TrieNode());});
    }

    @Test
    public void setChildNegativeTest2() {
        // Null case
        TrieNode node = new TrieNode();
        node.setChild('a', new TrieNode());
        node.setChild('a', null);
        assertNull(node.getChild('a'));
    }

    @Test
    public void setChildNegativeTest3() {
        // Invalid input
        TrieNode node = new TrieNode();
        assertThrows(IllegalArgumentException.class,
            () -> {node.setChild('#', new TrieNode());});
    }


    @Test
    public void charToIndexPositiveTest() {
        assertEquals(0, TrieNode.charToIndex('a'));
        assertEquals(12, TrieNode.charToIndex('m'));
        assertEquals(13, TrieNode.charToIndex('n'));
        assertEquals(24, TrieNode.charToIndex('y'));
        assertEquals(25, TrieNode.charToIndex('z'));
    }

    @Test
    public void charToIndexNegativeTest1() {
        // Invalid input
        assertThrows(IllegalArgumentException.class,
            () -> {TrieNode.charToIndex('A');});
    }

    @Test
    public void charToIndexNegativeTest2() {
        // Invalid input
        assertThrows(IllegalArgumentException.class,
            () -> {TrieNode.charToIndex('1');});
    }

    @Test
    public void charToIndexNegativeTest3() {
        // Invalid input
        assertThrows(IllegalArgumentException.class,
            () -> {TrieNode.charToIndex('#');});
    }

    // Trie Tests
    @Test
    public void insertPositiveTest() {
        Trie trie = new Trie();
        trie.insert("apple");
        trie.insert("apply");
        trie.insert("big");
        trie.insert("bit");
        TrieNode root = trie.getRoot();
        // check apple
        assertNotNull(root.getChild('a').getChild('p').getChild('p').getChild('l').getChild('e'));
        assertTrue(root.getChild('a').getChild('p').getChild('p').getChild('l').getChild('e').isEndOfWord);
        // check bat
        assertNotNull(root.getChild('b').getChild('i').getChild('t'));
        assertTrue(root.getChild('b').getChild('i').getChild('t').isEndOfWord);
    }

    @Test
    public void insertNegativeTest1() {
        // Null case
        Trie trie = new Trie();
        assertThrows(IllegalArgumentException.class,
            () -> {trie.insert(null);});
    }

    @Test
    public void insertNegativeTest2() {
        // Empty case
        Trie trie = new Trie();
        trie.insert("");
        assertTrue(trie.getRoot().isEndOfWord);
    }

    @Test
    public void insertNegativeTest3() {
        // Invalid input
        Trie trie = new Trie();
        assertThrows(IllegalArgumentException.class,
            () -> {trie.insert("#ee"); });
    }

    //toPatriciaTrie Test
    @Test
    public void toPatriciaTriePositiveTest() {
        Trie trie = new Trie();
        trie.insert("essence");
        trie.insert("essential");
        trie.insert("estimate");
        trie.insert("estimation");
        trie.insert("sublease");
        trie.insert("sublimate");
        trie.insert("sublime");
        trie.insert("subliminal");

        trie.toPatriciaTrie();
        TrieNode root = trie.getRoot();
        //First we test the branch of s, subl should becompressed to s
        TrieNode sublNode = root.getChild('s');
        assertNotNull(sublNode);
        
        // After subl, there should be 2 children: e and i
        assertEquals(2, sublNode.countChildren());
        assertNotNull(sublNode.getChild('e'));
        assertNotNull(sublNode.getChild('i'));

        //check sublease ease should be compressed to e
        TrieNode subleaseEnd = sublNode.getChild('e');
        assertTrue(subleaseEnd.isEndOfWord);
        assertEquals(0, subleaseEnd.countChildren());

        //we test the branch of e, es should becompressed to e
        TrieNode esNode = root.getChild('e');
        assertNotNull(esNode);
        // esNode should have s and t childs
        assertEquals(2, esNode.countChildren());
        assertNotNull(esNode.getChild('s')); 
        assertNotNull(esNode.getChild('t'));
    }

    @Test
    public void toPatriciaTrieNegativeTest1() {
        // Null case
        assertDoesNotThrow(() -> Trie.toPatriciaTrie(null));
    }

    @Test
    public void toPatriciaTrieNegativeTest2() {
        // no merge with 1 node
        Trie trie = new Trie();
        trie.toPatriciaTrie();
        assertEquals(0, trie.getRoot().countChildren());
    }
}