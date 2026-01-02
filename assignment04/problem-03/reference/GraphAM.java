package ds1;

public class GraphAM {
    // implememts a directed graph using an adjacency matrix
    // each vertex has a list of adjacent vertices

    private int numVertices;
    private int[][] adjMatrix;
    private Boolean[] visited;
    private Boolean undirected = false;

    public GraphAM(int numVertices) {
        this.numVertices = numVertices;
        adjMatrix = new int[numVertices][numVertices];
        visited = new Boolean[numVertices];
        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }
    }

    public void setUndirected() {
        undirected = true;
    }

    public void addEdge(int src, int dest) {
        adjMatrix[src][dest] = 1;
        if(undirected)
            adjMatrix[dest][src] = 1;
    }

    public Sequence<Integer> neighbors(int vertex) {
        Sequence<Integer> neighbors = new ListoverLinkedList<Integer>();
        for (int i = 0; i < numVertices; i++) {
            if (adjMatrix[vertex][i] == 1) {
                neighbors.insertFront(i);
            }
        }
        return neighbors;
    }

    public void print() {
        for (int i = 0; i < numVertices; i++) {
            System.out.print(i + ": ");
            for (int j = 0; j < numVertices; j++) {
                if (adjMatrix[i][j] == 1) {
                    System.out.print(j + " ");
                }
            }
            System.out.println();
        }
    }

    // Non-recursive BFS starting for a given node
    // use a queue to keep track of the nodes to visit (can be implemented using a linked list)
    // use neighbors() to get the list of neighbors of a node
    public Sequence<Integer> BFS(int vertex) {
        // breadth first search
        // returns a list of vertices in BFS order
        // starting from vertex
        // mark all vertices as not visited
        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }
        Sequence<Integer> elements = new ListoverLinkedList<Integer>();
        LinkedListQueue<Integer> queue = new LinkedListQueue<Integer>();
        visited[vertex] = true;
        queue.enqueue(vertex);
        while (!queue.isEmpty()) {
            int u = queue.dequeue();
            elements.insertRear(u);
            SequenceIterator<Integer> iter = neighbors(u).getIterator();
            while (iter.hasNext()) {
                int v = iter.next();
                if (!visited[v]) {
                    visited[v] = true;
                    queue.enqueue(v);
                }
            }
        }
        return elements;
    }

    // Non-recursive DFS starting for a given node
    // returns the list of visited nodes in the order they were visited
    // use a stack to keep track of the nodes to visit 
    // use neighbors() to get the list of neighbors of a node
    public Sequence<Integer> DFS(int vertex) {
        // depth first search
        // returns a list of vertices in DFS order
        // starting from start
        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }
        Sequence<Integer> elements = new ListoverLinkedList<Integer>();
        LinkedListStack<Integer> stack = new LinkedListStack<Integer>();
        stack.push(vertex);
        while (!stack.isEmpty()) {
            int u = stack.pop();
            visited[u] = true;
            elements.insertRear(u);
            SequenceIterator<Integer> iter = neighbors(u).getIterator();
            while (iter.hasNext()) {
                int v = iter.next();
                if (!visited[v]) {
                    stack.push(v);
                }
            }
        }
        return elements;
    }

    // DFS starting for a given node recursively
    public Sequence<Integer> DFSRec(int vertex) {
        // mark all vertices as not visited
        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }
        Sequence<Integer> elements = new ListoverLinkedList<Integer>();
        DFSRec(vertex, elements);
        return elements;
    }

    // implement recursive DFS
    // use neighbors() to get the list of neighbors of a node
    public void DFSRec(int vertex, Sequence<Integer> elements) {
        if (visited[vertex]) {
            return;
        }
        visited[vertex] = true;
        elements.insertRear(vertex);
        SequenceIterator<Integer> iter = neighbors(vertex).getIterator();
        while (iter.hasNext()) {
            int v = iter.next();
            if (!visited[v]) {
                DFSRec(v, elements);
            }
        }
    }



}
