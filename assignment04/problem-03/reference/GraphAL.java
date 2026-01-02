package ds1;

import java.util.HashMap;
import java.util.Map;

class AdjacencyList {
    private Sequence<Integer> elems;
    AdjacencyList() {
        elems = new ListoverLinkedList<Integer>();
    }
    void add(int dest) {
        elems.insertFront(dest);
    }

    Sequence<Integer> getList() {
        return elems;
    }

    int get(int index) {
        return elems.at(index);
    }

    int size() {
        return elems.length();
    }
}

class BFSResult {
    Map<Integer,Integer> edgeTo;
    Map<Integer,Integer> distTo;
    BFSResult(Map<Integer,Integer> edgeTo, Map<Integer,Integer> distTo) {
        this.edgeTo = edgeTo;
        this.distTo = distTo;
    }
}

public class GraphAL {
    // implememts a directed graph using an adjacency list
    // each vertex has a list of adjacent vertices
    // the list is implemented using a linked list
    // the graph is implemented using an array of linked lists
  
    private int numVertices;
    private AdjacencyList[] adjLists;
    private Boolean[] visited;
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

    // BFS starting for a given node
    public BFSResult BFS(int vertex) {
        Map<Integer,Integer> edgeTo = new HashMap<Integer,Integer>();
        Map<Integer,Integer> distTo = new HashMap<Integer,Integer>();
        // mark all vertices as not visited
        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
            distTo.put(i, 0);
        }
        // use a queue to store the vertices to be processed
        LinkedListQueue<Integer> queue = new LinkedListQueue<Integer>();
        visited[vertex] = true;
        queue.enqueue(vertex);
        while (!queue.isEmpty()) {
            int u = queue.dequeue();
            SequenceIterator<Integer> iter = neighbors(u).getIterator();
            while (iter.hasNext()) {
                Integer v = iter.next();
                if (!visited[v]) {
                    visited[v] = true;
                    edgeTo.put(v, u);
                    distTo.put(v, distTo.get(u) + 1);
                    queue.enqueue(v);
                }
            }
        }
        return new BFSResult(edgeTo, distTo);
    }
    // compute the vertex that is far from the given vertex
    // use the graph and BFS method

    // DFS starting for a given node
    public Sequence<Integer> DFS(int vertex) {
        // mark all vertices as not visited
        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }
        Sequence<Integer> elements = new ListoverLinkedList<Integer>();
        Stack<Integer> stack = new LinkedListStack<Integer>();
        stack.push(vertex);
        while (!stack.isEmpty()) {
            int v = stack.pop();
            if (!visited[v]) {
                visited[v] = true;
                elements.insertFront(v);
                SequenceIterator<Integer> iter = neighbors(v).getIterator();
                while (iter.hasNext()) {
                    Integer u = iter.next();
                    stack.push(u);
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

    void DFSRec(int vertex, Sequence<Integer> elements) {
        visited[vertex] = true;
        elements.insertFront(vertex);
        SequenceIterator<Integer> iter = neighbors(vertex).getIterator();
        while (iter.hasNext()) {
            Integer u = iter.next();
            if (!visited[u]) {
                DFSRec(u, elements);
            }
        }
    }

    // topological sort of a DAG using DFS
    public Sequence<Integer> topologicalSortDFS() {
        // mark all vertices as not visited
        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }
        // use the DFS method to compute the topological sort
        // use recursion to store the elements in the correct order 
        Sequence<Integer> elements = new ListoverLinkedList<Integer>();
        // call the recursive method for each vertex
        // call topologicalSortRec with vertex an elements
        for(int i = 0; i < numVertices; i++) {
            if(!visited[i])
                topologicalSort(i, elements);
        }
        return elements;
    }

    void topologicalSort(int vertex, Sequence<Integer> elements) {
        visited[vertex] = true;
        SequenceIterator<Integer> iter = neighbors(vertex).getIterator();
            while (iter.hasNext()) {
                Integer v = iter.next();
                if(!visited[v])
                    topologicalSort(v, elements);
            }
            elements.insertFront(vertex);
    }

    // topological sort of a DAG using BFS
    // use a map to compute the in-degree of each vertex
    // use a queue to store the vertices with in-degree 0
    public Sequence<Integer> topologicalSortBFS() {
        Map<Integer,Integer> inDegree = new HashMap<Integer,Integer>();
        for (int i = 0; i < numVertices; i++) {
            inDegree.put(i, 0);
        }
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
        LinkedListQueue<Integer> queue = new LinkedListQueue<Integer>();
        queue.enqueue(0);
        // -1 as end of level marker
        queue.enqueue(-1);
        visited[0] = true;
        color[0] = isOdd;
        while(!queue.isEmpty()) {
            int u = queue.dequeue();
            if(u == -1) {
                if(!queue.isEmpty()) {
                    isOdd = !isOdd;
                    queue.enqueue(-1);
                }
            }
            else {
                color[u] = isOdd;
                SequenceIterator<Integer> iter = neighbors(u).getIterator();
                while (iter.hasNext()) {
                    Integer v = iter.next();
                    if(!visited[v]) {
                        visited[v] = true;
                        queue.enqueue(v);
                    }
                    else {
                        if(color[v] == color[u])
                            return false;
                    }
                }
            } 
        }
        return true; 
    }

    // compute the diameter of a tree (the longest path between two nodes)
    // the diameter is the sum of the two longest paths from the root
    // use the graph and shortestPath method from bfs
    public int diameter(int root) {
        BFSResult bfsResult = BFS(root);
        int max = 0;
        int maxVertex = 0;
        // find the vertex that is farthest from the root
        for (int i = 0; i < numVertices; i++) {
            if (bfsResult.distTo.get(i) > max) {
                max = bfsResult.distTo.get(i);
                maxVertex = i;
            }
        }
        // find the vertex that is farthest from the maxVertex
        // run a second BFS starting from the node we just found
        BFSResult bfsResult2 = BFS(maxVertex);
        int diameter = 0;
        for (int i = 0; i < numVertices; i++) {
            if (bfsResult2.distTo.get(i) > diameter) {
                diameter = bfsResult2.distTo.get(i);
            }
        }
        return diameter;
    }
}
