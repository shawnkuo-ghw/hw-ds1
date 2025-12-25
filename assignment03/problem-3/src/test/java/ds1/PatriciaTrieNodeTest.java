package ds1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PatriciaTrieNodeTest {

    @Test
    public void countChildrenPositiveTest() {
        PatriciaTrieNode node = new PatriciaTrieNode();
        node.setChild('a', new PatriciaTrieNode());
        node.setChild('b', new PatriciaTrieNode());
        node.setChild('c', new PatriciaTrieNode());
        node.setChild('d', new PatriciaTrieNode());
        node.setChild('e', new PatriciaTrieNode());
        assertEquals(5, node.countChildren());
    }

    @Test
    public void countChildrenNegativeTest1() {
        // Empty case
        PatriciaTrieNode node = new PatriciaTrieNode();
        assertEquals(0, node.countChildren());
    }

    @Test
    public void countChildrenNegativeTest2() {
        // Null case
        PatriciaTrieNode node = new PatriciaTrieNode();
        node.setChild('a', new PatriciaTrieNode());
        node.setChild('a', null);
        assertEquals(0, node.countChildren());
    }
    
    @Test
    public void getChildPositiveTest() {
        PatriciaTrieNode root = new PatriciaTrieNode();
        PatriciaTrieNode node1 = new PatriciaTrieNode();
        PatriciaTrieNode node2 = new PatriciaTrieNode();
        PatriciaTrieNode node3 = new PatriciaTrieNode();
        PatriciaTrieNode node4 = new PatriciaTrieNode();
        root.setChild('a', node1);
        node1.setChild('b', node2);
        node2.setChild('c', node3);
        node3.setChild('d', node4);
        assertEquals(node1, root.getChild('a'));
        assertEquals(node2, node1.getChild('b'));
        assertEquals(node3, node2.getChild('c'));
        assertEquals(node4, node3.getChild('d'));
    }

    @Test
    public void getChildNegativeTest1() {
        // Null case
        PatriciaTrieNode node = new PatriciaTrieNode();
        assertNull(node.getChild('a'));
    }

    @Test
    public void getChildNegativeTest2() {
        PatriciaTrieNode node = new PatriciaTrieNode();
        assertThrows(IllegalArgumentException.class, () -> {
            node.getChild('A');});
        assertThrows(IllegalArgumentException.class, () -> {
            node.getChild('1');});
    }

    @Test
    public void getOnlyChildPositiveTest() {
        PatriciaTrieNode root = new PatriciaTrieNode();
        PatriciaTrieNode childA = new PatriciaTrieNode();
        PatriciaTrieNode childB = new PatriciaTrieNode();
        PatriciaTrieNode childC = new PatriciaTrieNode();
        root.setChild('a', childA);
        root.setChild('b', childB);
        root.setChild('c', childC);

        childA.setChild('x', new PatriciaTrieNode());
        childC.setChild('y', new PatriciaTrieNode()); 
        childC.setChild('z', new PatriciaTrieNode());
        assertEquals(childA, root.getOnlyChild());
    }

    @Test
    public void getOnlyChildNegativeTest1() {
        // Empty case
        PatriciaTrieNode node = new PatriciaTrieNode();
        assertNull(node.getOnlyChild());
    }

    @Test
    public void getOnlyChildNegativeTest2() {
        //no only child
        PatriciaTrieNode root = new PatriciaTrieNode();
        PatriciaTrieNode childA = new PatriciaTrieNode();
        PatriciaTrieNode childB = new PatriciaTrieNode();
        root.setChild('a', childA);
        root.setChild('b', childB);
        childB.setChild('x', new PatriciaTrieNode());
        childB.setChild('y', new PatriciaTrieNode());
        assertNull(root.getOnlyChild());
    }

    @Test
    public void setChildPositiveTest() {
        PatriciaTrieNode root = new PatriciaTrieNode();
        PatriciaTrieNode node1 = new PatriciaTrieNode();
        PatriciaTrieNode node2 = new PatriciaTrieNode();
        PatriciaTrieNode node3 = new PatriciaTrieNode();
        PatriciaTrieNode node4 = new PatriciaTrieNode();
        root.setChild('a', node1);
        node1.setChild('b', node2);
        node2.setChild('c', node3);
        node3.setChild('d', node4);
        assertEquals(root, node1.parent);
        assertEquals(node1, node2.parent);
        assertEquals(node2, node3.parent);
        assertEquals(node3, node4.parent);
    }

    @Test
    public void setChildNegativeTest1() {
        //invalid case
        PatriciaTrieNode node = new PatriciaTrieNode();
        assertThrows(IllegalArgumentException.class, () -> {
            node.setChild('A', new PatriciaTrieNode());});
        assertThrows(IllegalArgumentException.class, () -> {
            node.setChild('#', new PatriciaTrieNode());});
    }

    @Test
    public void setChildNegativeTest3() {
        // Null case
        PatriciaTrieNode node = new PatriciaTrieNode();
        node.setChild('a', new PatriciaTrieNode());
        node.setChild('a', null);
        assertNull(node.children[0]);
    }

    @Test
    public void charToIndexPositiveTest() {
        assertEquals(0, PatriciaTrieNode.charToIndex('a'));
        assertEquals(1, PatriciaTrieNode.charToIndex('b'));
        assertEquals(25, PatriciaTrieNode.charToIndex('z'));
    }

    @Test
    public void charToIndexNegativeTest() {
        // invalid case
        assertThrows(IllegalArgumentException.class, () -> {
            PatriciaTrieNode.charToIndex('A');});
        assertThrows(IllegalArgumentException.class, () -> {
            PatriciaTrieNode.charToIndex('#');});
        assertThrows(IllegalArgumentException.class, () -> {
            PatriciaTrieNode.charToIndex('1');});
    }
}