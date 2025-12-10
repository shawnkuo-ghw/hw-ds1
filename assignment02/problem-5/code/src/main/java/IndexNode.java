public class IndexNode {
    HeapNode node;
    int index;
    //O(1)
    IndexNode(HeapNode node, int index) {
        this.node = node;
        this.index = index;
    }
}

