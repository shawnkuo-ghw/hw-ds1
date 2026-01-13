package ds1.util;

class AdjacencyList {
    private Sequence<NodeWeight> elems;
    AdjacencyList() {
        elems = new ListoverLinkedList<NodeWeight>();
    }
    void add(int dest, float weight) {
        elems.insertFront(new NodeWeight(dest, weight));
    }
    Sequence<NodeWeight> getList() {
        return elems;
    }
}

public class GraphAL {
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

    public void setUndirected() { undirected = true; }

    public int getNumVertices() { return numVertices; }

    public void addEdge(int src, int dest, float weight) {
        adjLists[src].add(dest, weight);
        if (undirected) adjLists[dest].add(src, weight);
    }

    public void addEdge(int src, int dest) {
        addEdge(src, dest, 0);
    }

    public Sequence<NodeWeight> neighbours(int vertex) {
        return adjLists[vertex].getList();
    }

    public Float getWeight(int src, int dest) {
        SequenceIterator<NodeWeight> iterator = neighbours(src).getIterator();
        while (iterator.hasNext()) {
            NodeWeight neighbour = iterator.next();
            if (neighbour.getDest() == dest) return neighbour.getWeight();
        }
        return null;
    }

    public Sequence<Integer> detectCycle() {
        int[] state = new int[numVertices]; // where 0 is unvisited, 1 is visiting,2 is visied
        int[] parent = new int[numVertices];
        for (int vertex = 0; vertex < numVertices; vertex++) parent[vertex] = -1;
        for (int startVertex = 0; startVertex < numVertices; startVertex++) {
            if (state[startVertex] == 0) {
                Sequence<Integer> cycle = dfsFindCycle(startVertex, state, parent);
                if (cycle != null) return cycle;
            }
        }
        return null;
    }


    private Sequence<Integer> dfsFindCycle(int vertex, int[] state, int[] parent) {
        state[vertex] = 1;
        SequenceIterator<NodeWeight> iterator = neighbours(vertex).getIterator();
        while (iterator.hasNext()) {
            NodeWeight neighbour = iterator.next();
            int nextVertex = neighbour.getDest();
            if (nextVertex == vertex) {
                ListoverLinkedList<Integer> loop = new ListoverLinkedList<Integer>();
                loop.insertRear(vertex);
                loop.insertRear(vertex);
                return loop;
            }
            if (state[nextVertex] == 0) {
                parent[nextVertex] = vertex;
                Sequence<Integer> found = dfsFindCycle(nextVertex, state, parent);
                if (found != null) return found;
            } else if (state[nextVertex] == 1) {
                ListoverLinkedList<Integer> cycle = new ListoverLinkedList<Integer>();
                cycle.insertRear(nextVertex);
                int currentVertex = vertex;
                while (currentVertex != -1 && currentVertex != nextVertex) {
                    cycle.insertRear(currentVertex);
                    currentVertex = parent[currentVertex];
                }
                cycle.insertRear(nextVertex);
                return cycle;
            }
        }
        state[vertex] = 2;
        return null;
    }

    private static class DijkstraResult {
        double[] dist;
        int[] parent;
        DijkstraResult(double[] dist, int[] parent) {
            this.dist = dist;
            this.parent = parent;
        }
    }

    private DijkstraResult dijkstraWithoutPQ(int sourceVertex) {
        double[] dist = new double[numVertices];
        int[] parent = new int[numVertices];
        boolean[] processed = new boolean[numVertices];

        for (int vertex = 0; vertex < numVertices; vertex++) {
            dist[vertex] = Double.POSITIVE_INFINITY;
            parent[vertex] = -1;
            processed[vertex] = false;
        }
        dist[sourceVertex] = 0.0;

        int iteration = 0;
        boolean shouldContinue = true;
        while (iteration < numVertices && shouldContinue) {
            int currentVertex = -1;
            double bestDistance = Double.POSITIVE_INFINITY;
            for (int vertex = 0; vertex < numVertices; vertex++) {
                if (!processed[vertex] && dist[vertex] < bestDistance) {
                    bestDistance = dist[vertex];
                    currentVertex = vertex;
                }
            }

            if (currentVertex == -1) {
                shouldContinue = false;
            } else {
                processed[currentVertex] = true;

                SequenceIterator<NodeWeight> neighbourIterator = neighbours(currentVertex).getIterator();
                while (neighbourIterator.hasNext()) {
                    NodeWeight neighbour = neighbourIterator.next();
                    int nextVertex = neighbour.getDest();
                    double edgeWeight = neighbour.getWeight();
                    if (dist[currentVertex] + edgeWeight < dist[nextVertex]) {
                        dist[nextVertex] = dist[currentVertex] + edgeWeight;
                        parent[nextVertex] = currentVertex;
                    }
                }
                iteration++;
            }
        }
        return new DijkstraResult(dist, parent);
    }

    private Sequence<Integer> buildCycleFromParent(int start, int end, int[] parent) {
        ListoverLinkedList<Integer> inversePath = new ListoverLinkedList<Integer>();
        int currentVertex = end;
        while (currentVertex != -1 && currentVertex != start) {
            inversePath.insertRear(currentVertex);
            currentVertex = parent[currentVertex];
        }
        // end is not reachable, return null
        if (currentVertex != start) return null;

        ListoverLinkedList<Integer> cycle = new ListoverLinkedList<Integer>();
        cycle.insertRear(start);
        for (int i = inversePath.length() - 1; 0 <= i; i--) {
            cycle.insertRear(inversePath.at(i));
        }
        cycle.insertRear(start);
        return cycle;
    }

    //find the cheapest cost directed cycle
    public Sequence<Integer> findLeastCostCycle() {
        double bestCost = Double.POSITIVE_INFINITY;
        Sequence<Integer> best = null;

        for (int startVertex = 0; startVertex < numVertices; startVertex++) {
            DijkstraResult res = dijkstraWithoutPQ(startVertex);
            double[] dist = res.dist;
            int[] parent = res.parent;

            // find a cycle
            for (int endVertex = 0; endVertex < numVertices; endVertex++) {
                if (endVertex != startVertex) {
                    if (dist[endVertex] != Double.POSITIVE_INFINITY) {
                        Float edgeWeight = getWeight(endVertex, startVertex);
                        if (edgeWeight != null) {
                            double cost = dist[endVertex] + edgeWeight;
                            if (cost < bestCost) {
                                bestCost = cost;
                                best = buildCycleFromParent(startVertex, endVertex, parent);
                            }
                        }
                    }
                }
            }
        }
        return best;
    }

    public int findMoneyMuleVertex() {
        double[] received = new double[numVertices];
        double[] sent = new double[numVertices];

        for (int srcVertex = 0; srcVertex < numVertices; srcVertex++) {
            SequenceIterator<NodeWeight> iterator = neighbours(srcVertex).getIterator();
            while (iterator.hasNext()) {
                NodeWeight neighbour = iterator.next();
                int destVertex = neighbour.getDest();
                double edgeWeight = neighbour.getWeight();
                sent[srcVertex] += edgeWeight;
                received[destVertex] += edgeWeight;
            }
        }

        int bestVertex = 0;
        double bestRatio = -1.0;
        for (int vertex = 0; vertex < numVertices; vertex++) {
            double ratio;
            if (sent[vertex] == 0.0) ratio = received[vertex];
            else ratio = received[vertex] / sent[vertex];
            if (ratio > bestRatio) {
                bestRatio = ratio;
                bestVertex = vertex;
            }
        }
        return bestVertex;
    }
}
