### Problem 5
[15p] Heaps over binary tree and over arrays

For the next two items you can either represent the binary tree using the mathematical representation (i.e., BT = Nil | <root, BT, BT>) or the in memory representation (i.e., BT = <root: Node>, Node: <data, left: BT, right: BT>).

1. [5p] Write an algorithm that converts a binary tree into an array. What are the preconditions over the binary tree such that the resulting array has the max-heap property?
    ```java
    private int countNodes(Node curr) {
        if (curr == null) return 0;
        return 1 + countNodes(curr.left) + countNodes(curr.right);
    }

    int[] BtToArray(BinaryTree bt) {
        int[] array = new int[countNodes(bt.root)];
        Queue<Pair<Node, Integer>> queue = empty;
        if (bt.root != null) {
	        enqueue(queue, (bt.root, 0));
	        while queue is not empty {
		        (node, index) = dequeue(queue)
		        array[index] = node.data;
		        if node.left != null
			        enqueue(queue, (node.left, 2 * index + 1))
		        if node.right != null		
			        enqueue(queue, (node.right, 2 * index + 2))
            }
        }
        return array;
    }
    ```

    Precondition:
    1. Every node.data is greater or equal to each child.data
    2. Binary tree is complete tree(except the last level)
2. [5p] Write an algorithm that converts an array into a binary tree. What are the preconditions over the array such that resulting binary tree has the max-heap property?
    ```java
    BinaryTree arrayToBt(int[] array) {
        if(array == null || array.length == 0) return new BinaryTree(null);
        Node[] nodes = new Node[array.length];
        for (int i = 0; i < array.length; i++)
            nodes[i] = new Node(array[i]);
        for(int i = 0; i < array.length; i++) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            if (left < array.length) nodes[i].left = nodes[left];
            if (right < array.length) nodes[i].right = nodes[right];
        }
        BinaryTree bt = new BinaryTree();
        bt.root = nodes[0];
        return bt;
    }
    ```

    Precondition:
    1. For all i, array[i] >= array[2*i+1] and array[i] >= array[2*i+2] (when those children exist)
    2. Array represents a complete binary tree