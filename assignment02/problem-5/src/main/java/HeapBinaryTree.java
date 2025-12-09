public class HeapBinaryTree {

    HeapNode root;

    // create an empty heap binary tree
    public HeapBinaryTree() {
        root = null;
    }

    // create a heap binary tree with a given root
    public HeapBinaryTree(HeapNode root) {
        this.root = root;
    }

    // create a heap binary tree from an array
    // O(n) to build all node and link children
    public static HeapBinaryTree fromArray(int[] array) {
        if (array == null || array.length == 0)
            return new HeapBinaryTree(null);
        HeapNode[] nodes = new HeapNode[array.length];
        //O(n)
        for(int i = 0; i < array.length; i++) {
            nodes[i] = new HeapNode(array[i]);
        }
        //O(n)
        for(int i = 0; i < array.length; i++){
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            if (left < array.length)
                nodes[i].left = nodes[left];
            if (right < array.length)
                nodes[i].right = nodes[right];
        }
        return new HeapBinaryTree(nodes[0]);
    }

    // convert the heap binary tree into an array by heap index
    // O(n) since each node is visited
    public int[] toArray() {
        int size = countNodes();
        if (size == 0)
            return new int[0];
        int[] array = new int[size];
        Queue queue = new LinkedListQueue();
        //O(1)
        queue.enqueue(new IndexNode(root, 0));
        //O(n)
        while (!queue.isEmpty()) {
            IndexNode current = queue.dequeue();
            if (current.index >= size) {
                throw new IllegalStateException("Tree is not complete");
            }
            array[current.index] = current.node.data;
            int leftIndex = 2 * current.index + 1;
            int rightIndex = 2 * current.index + 2;
            if (current.node.left != null) {
                queue.enqueue(new IndexNode(current.node.left, leftIndex));
            }
            if (current.node.right != null) {
                queue.enqueue(new IndexNode(current.node.right, rightIndex));
            }
        }
        return array;
    }

    // get the root of the heap binary tree
    //O(1)
    public HeapNode getRoot() {
        return root;
    }

    // couny the number of nodes
    //O(n)
    public int countNodes() {
        return countNodes(root);
    }

    // count nodes recursively
    //O(n) in the worst case
    private int countNodes(HeapNode curr) {
        if (curr == null)
            return 0;
        return 1 + countNodes(curr.left) + countNodes(curr.right);
    }

    // check if the tree is empty
    //O(1)
    public boolean isEmpty() {
        return root == null;
    }

    // check if the tree satisfies the max-heap property
    //O(n)
    public boolean isMaxHeap() {
        return isMaxHeap(root);
    }

    // help to check heap property and completeness
    //O(n) since we need to visit all nodes
    private boolean isMaxHeap(HeapNode curr) {
        if (curr == null)
            return true;
        int size = countNodes(curr);
        Queue queue = new LinkedListQueue();
        queue.enqueue(new IndexNode(curr, 0));
        while(!queue.isEmpty()) {
            IndexNode current = queue.dequeue();
            // if we reach an index outside [0, size - 1], the tree is not complete
            if (current.index >= size)
                return false;
            HeapNode node = current.node;
            int leftIndex = 2 * current.index + 1;
            int rightIndex = 2 * current.index + 2;
            if(node.left != null) {
                if(node.left.data > node.data)
                    return false;
                queue.enqueue(new IndexNode(node.left, leftIndex));
            }
            if(node.right != null) {
                if (node.right.data > node.data)
                    return false;
                queue.enqueue(new IndexNode(node.right, rightIndex));
            }
        }
        return true;
    }
}

