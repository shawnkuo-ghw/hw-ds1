package ds1;

import java.util.HashMap;
import java.util.Map;

public class DisjointSetArray<K> implements DisjointSet<K> {
    private Map<K, Integer> componentToForest;
    private int[] forest;

    public DisjointSetArray(int elements) {
        componentToForest = new HashMap<>();
        forest = new int[elements];
    }

    public void makeSet(K elem) {
        if (!componentToForest.containsKey(elem)) {
            forest[componentToForest.size()] = -1;
            componentToForest.put(elem, componentToForest.size());
        }
    }

    private int size(int root) {
        return -forest[root];
    }

    public int find(K elem) {
        // get the index of the element in the forest
        int index = componentToForest.get(elem);
        Stack<Integer> stack = new LinkedListStack<>();
        // while the element is not the root of the tree
        // move up the tree
        while (forest[index] >= 0) {
            stack.push(index);
            index = forest[index];
        }
        // path compression: point all nodes along the path directly to the root
        while (!stack.isEmpty()) {
            int node = stack.pop();
            forest[node] = index;
        }
        return index;
    }

    public void union(K elem1, K elem2) {
        int root1 = find(elem1);
        int root2 = find(elem2);
        // if the elements are in different trees
        if (root1 == root2) {
            return;
        }
        // merge the trees
        // use the size of the trees to decide which tree to merge into the other
        int size1 = size(root1);
        int size2 = size(root2);
        // merge smaller tree into larger tree
        if (size1 >= size2) {
            forest[root2] = root1;
            forest[root1] = -(size1 + size2);
        } else {
            forest[root1] = root2;
            forest[root2] = -(size1 + size2);
        }
    }
}


