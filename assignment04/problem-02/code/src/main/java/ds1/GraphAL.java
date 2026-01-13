package ds1;
import java.util.HashMap;
import java.util.Map;

class AdjacencyList {
    private Sequence<Integer> elems;
    AdjacencyList() { elems = new ListoverLinkedList<Integer>(); }
    void add(int dest) { elems.insertFront(dest); }
    Sequence<Integer> getList() { return elems; }
    int get(int index) { return elems.at(index); }
    int size() { return elems.length(); }
}

class ParallelResult {
    private Sequence<Sequence<Integer>> pElems;
    ParallelResult(Sequence<Sequence<Integer>> pElems) { this.pElems = pElems; }
    public int getStepNumber() { return pElems.length(); }
    public String toString() {
        String str = "[ ";
        SequenceIterator<Sequence<Integer>> verticesIter = pElems.getIterator();
        while (verticesIter.hasNext()) {
            Sequence<Integer> parallelVertices = verticesIter.next();
            SequenceIterator<Integer> vertexIter = parallelVertices.getIterator();
            str += "(";
            while (vertexIter.hasNext()) {
                str += vertexIter.next();
                if ( vertexIter.hasNext() ) str += ", ";
                else str += ")";
            }
            if (verticesIter.hasNext()) str += ", ";
            else str += " ]";
        }
        return str;
    }
}

public class GraphAL {
    // implememts a directed graph using an adjacency list
    // each vertex has a list of adjacent vertices
    // the list is implemented using a linked list
    // the graph is implemented using an array of linked lists
  
    private int numVertices;
    private Boolean[] visited;
    private AdjacencyList[] adjLists;
    private Boolean undirected = false;
    
    public GraphAL(int numVertices) {
        this.numVertices = numVertices;
        adjLists = new AdjacencyList[numVertices];
        visited = new Boolean[numVertices];
        for (int i = 0; i < numVertices; i++) {
            adjLists[i] = new AdjacencyList();
            visited[i] = false;
        }
    }

    public void setUndirected() {
        undirected = true;
    }

    public void addEdge(int src, int dest) {
        adjLists[src].add(dest);
        if(undirected)
            adjLists[dest].add(src);
    }

    public Sequence<Integer> neighbors(int vertex) {
        return adjLists[vertex].getList();
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

    /* ====================== topologicalSortDFS ============================ */

    // topological sort of a DAG using DFS recursively
    public Sequence<Integer> topologicalSortDFS() {
        // mark all vertices as not visited
        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }
        // use the DFS method to compute the topological sort
        // use recursion to store the elements in the correct order 
        Sequence<Integer> elements = new ListoverLinkedList<Integer>();
        // call the recursive method for each vertex
        // call DFSRec with unvisited vertex and elements
        for (int i = 0; i < numVertices; i++) {
            if (!visited[i]) DFSRec(i, elements);
        }
        return elements;
    }

    // DFS starting for a given node recursively
    void DFSRec(int vertex, Sequence<Integer> elements) {
        visited[vertex] = true;
        SequenceIterator<Integer> vertex_neighbors = neighbors(vertex).getIterator();
        while (vertex_neighbors.hasNext()) {
            Integer u = vertex_neighbors.next();
            if (!visited[u]) {
                DFSRec(u, elements);
            }
        }
        elements.insertFront(vertex);
    }

    /* ====================== topologicalSortBFS ============================ */
    
    // topological sort of a DAG using BFS
    // use a map to compute the in-degree of each vertex
    // use a queue to store the vertices with in-degree 0
    public Sequence<Integer> topologicalSortBFS() {
        // init the map storing the in-degree of each vertex
        Map<Integer,Integer> inDegree = new HashMap<Integer,Integer>();
        for (int i = 0; i < numVertices; i++) { inDegree.put(i, 0); }
        for (int i = 0; i < numVertices; i++) {
            SequenceIterator<Integer> iter = neighbors(i).getIterator();
            while (iter.hasNext()) {
                Integer u = iter.next();
                inDegree.put(u, inDegree.get(u) + 1);
            }
        }
        Sequence<Integer> elements = new ListoverLinkedList<Integer>();
        Sequence<Integer> queue = new ListoverLinkedList<Integer>();
        for (int i = 0; i < numVertices; i++) {
            if (inDegree.get(i) == 0) {
                queue.insertRear(i);
                elements.insertFront(i);
            }
        }
        while (queue.length() != 0) {
            int v = queue.at(0);
            queue.removeAt(0);
            SequenceIterator<Integer> iter = neighbors(v).getIterator();
            while (iter.hasNext()) {
                Integer u = iter.next();
                inDegree.put(u, inDegree.get(u) - 1);
                if (inDegree.get(u) == 0) {
                    queue.insertRear(u);
                    elements.insertRear(u);
                }
            }
        }
        return elements;
    }

    /* ==================== topologicalSortParallel ========================= */

    public ParallelResult topologicalSortParallel() {
        Map<Integer,Integer> inDegree = new HashMap<Integer,Integer>();
        for (int i = 0; i < numVertices; i++) { inDegree.put(i, 0); }
        for (int i = 0; i < numVertices; i++) {
            SequenceIterator<Integer> iter = neighbors(i).getIterator();
            while (iter.hasNext()) {
                Integer u = iter.next();
                inDegree.put(u, inDegree.get(u) + 1);
            }
        }
        Sequence<Sequence<Integer>> pElements = new ListoverLinkedList<Sequence<Integer>>();
        Sequence<Integer> elements = new ListoverLinkedList<Integer>();
        Sequence<Integer> queue = new ListoverLinkedList<Integer>();
        for (int i = 0; i < numVertices; i++) {
            if (inDegree.get(i) == 0) {
                queue.insertRear(i);
            }
        }
        while (queue.length() != 0) {
            Sequence<Integer> parallelVerticies = new ListoverLinkedList<Integer>();            
            while (queue.length() != 0) {
                int v = queue.at(0);
                queue.removeAt(0); // dequeue
                parallelVerticies.insertRear(v);
                elements.insertRear(v);
            }
            pElements.insertRear(parallelVerticies);
            // update the in-degree of each neighbor of parallel vertices
            SequenceIterator<Integer> pvIter = parallelVerticies.getIterator();
            while (pvIter.hasNext()) {
                Integer v = pvIter.next();
                SequenceIterator<Integer> iter = neighbors(v).getIterator();
                while (iter.hasNext()) {
                    Integer u = iter.next();
                    inDegree.put(u, inDegree.get(u) - 1);
                    if (inDegree.get(u) == 0) {
                        queue.insertRear(u);
                    }
                }
            }
        }
        return new ParallelResult(pElements);
    }
}