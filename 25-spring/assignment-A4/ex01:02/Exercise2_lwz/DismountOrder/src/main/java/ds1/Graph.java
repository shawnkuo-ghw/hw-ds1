package ds1;

class AdjacencyList<E> {
    private Sequence<E> elems;
    private E label;

    public AdjacencyList() {
        elems = new ListoverLinkedList<E>();
    }

    public AdjacencyList(E label) {
        this.label = label;
        elems = new ListoverLinkedList<E>();
    }

    public void add(E dest) {
        elems.insertFront(dest);

    }

    public Sequence<E> getList() {
        return elems;
    }

    public E getLabel() {
        return label;
    }

    E get(int index) {
        return elems.at(index);
    }

    int size() {
        return elems.length();
    }
}

public class Graph<E> {
    // implememts a directed graph using an adjacency list
    // each vertex has a list of adjacent vertices
    // the list is implemented using a linked list
    // the graph is implemented using an array of linked lists
  
    public int numVertices = 20;
    private int curSize;
    public AdjacencyList<E>[] adjLists;
    private Boolean[] visited;
    private Boolean undirected = false;
    
    public Graph() {
        curSize = 0;
        adjLists = new AdjacencyList[numVertices];
        visited = new Boolean[numVertices];
        for (int i = 0; i < numVertices; i++) {
            adjLists[i] = new AdjacencyList<E>();
            visited[i] = false;
        }
    }

    public void setUndirected() {
        undirected = true;
    }

    public void addVertex(E label) {
        adjLists[curSize] = new AdjacencyList<>(label);
        adjLists[curSize].add(label);
        curSize++;
    }

    public void addEdge(E src, E dest) {
        int i = indexOf(src);
        int j = indexOf(dest);
        if (i != -1 && j != -1) {
            adjLists[i].add(dest);
        }
        if(undirected) {
            if (i != -1 && j != -1) {
            adjLists[j].add(src);
        }
        }
        
    }

    public int indexOf(E label) {
        for (int i = 0; i < numVertices; i++) {
            if (adjLists[i].getLabel().equals(label)) return i;
        }
        return -1;
    }

    public Sequence<E> neighbors(int i) {
        return adjLists[i].getList();
    }


    public void print() {
        for (int i = 0; i < numVertices; i++) {
            System.out.print(i + ": ");
            for (int j = 0; j < adjLists[i].size(); j++) {
                System.out.print(adjLists[i].get(j) + " ");
            }
            System.out.println();
        }
    }

    public E label(int i) {
        return adjLists[i].getLabel();
    }

    // DFS starting for a given node recursively
    public Sequence<E> DFSRec(E vertex) {
        // mark all vertices as not visited
        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }
        Sequence<E> elements = new ListoverLinkedList<E>();
        DFSRec(vertex, elements);
        return elements;
    }

    void DFSRec(E vertex, Sequence<E> elements) {
        int cur = indexOf(vertex);
        visited[cur] = true;
        elements.insertFront(vertex);
        SequenceIterator<E> iter = neighbors(cur).getIterator();
        while (iter.hasNext()) {
            E u = iter.next();
            int idx = indexOf(u);
            if (!visited[idx]) {
                DFSRec(u, elements);
            }
        }
    }

    // check in a graph is bipartite using a variation of BFS
    // use a map to store the color of each vertex
    // use a queue to store the vertices to be processed
    public boolean isBipartite() {
        boolean isOdd = true;
        boolean[] color = new boolean[numVertices];
        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }
        // use a queue to store the vertices to be processed
        // add an end of level marker to the queue
        Sequence<Integer> queue = new ListoverLinkedList<Integer>();
        queue.insertRear(0);
        // -1 as end of level marker
        queue.insertRear(-1);
        visited[0] = true;
        color[0] = isOdd;
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
}
