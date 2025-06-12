package ds1;

public class DismountOrder<E> {
    
    public Sequence<E> dismounting(Graph<E> graph) {
        int n = graph.numVertices;
        boolean[] visited = new boolean[n];
        ListoverLinkedList<E> result = new ListoverLinkedList<>();

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(graph, i, visited, result);
            }
        }

        return result;  // the sequence is in dismounting order
    }

    private void dfs(Graph<E> graph, int i, boolean[] visited, ListoverLinkedList<E> result) {
        visited[i] = true;
        Sequence<E> neighbors = graph.adjLists[i].getList();
        neighbors.start();
        while (!neighbors.isEnd()) {
            E neighborLabel = neighbors.examine();
            if (neighborLabel != null) {
                int j = graph.indexOf(neighborLabel);
                if (j >= 0 && !visited[j]) {
                    dfs(graph, j, visited, result);
                }
            }
            neighbors.advance();
        }
        result.insertRear(graph.label(i));  // insert after visiting children
    }
}
