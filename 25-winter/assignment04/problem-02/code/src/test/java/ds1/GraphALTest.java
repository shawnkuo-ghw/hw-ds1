package ds1;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GraphALTest
{
    // Solving dependencies model
    @Test
    public void testDependenciesDAG(){
        GraphAL g = new GraphAL(8);
        g.addEdge(0, 1); 
        g.addEdge(0, 3); 
        g.addEdge(1, 2); 
        g.addEdge(1, 4); 
        g.addEdge(2, 5); 
        g.addEdge(3, 4); 
        g.addEdge(4, 5);  
        g.addEdge(5, 6);
        g.addEdge(6, 7);
        g.print();
        // checking the order of topological sort using DFS
        var topologicalDFS = g.topologicalSortDFS();
        System.out.println("topological DFS: " + topologicalDFS.toString());
        assertTrue(topologicalDFS.indexOf(0) < topologicalDFS.indexOf(1));
        assertTrue(topologicalDFS.indexOf(0) < topologicalDFS.indexOf(3));
        assertTrue(topologicalDFS.indexOf(1) < topologicalDFS.indexOf(2));
        assertTrue(topologicalDFS.indexOf(1) < topologicalDFS.indexOf(4));
        assertTrue(topologicalDFS.indexOf(2) < topologicalDFS.indexOf(5));
        assertTrue(topologicalDFS.indexOf(3) < topologicalDFS.indexOf(4));
        assertTrue(topologicalDFS.indexOf(4) < topologicalDFS.indexOf(5));
        assertTrue(topologicalDFS.indexOf(5) < topologicalDFS.indexOf(6));
        assertTrue(topologicalDFS.indexOf(6) < topologicalDFS.indexOf(7));
        // checking the order of topological sort using BFS
        var topologicalBFS = g.topologicalSortBFS();
        System.out.println("topological BFS: " + topologicalBFS.toString());
        assertTrue(topologicalBFS.indexOf(0) < topologicalBFS.indexOf(1));
        assertTrue(topologicalBFS.indexOf(0) < topologicalBFS.indexOf(3));
        assertTrue(topologicalBFS.indexOf(1) < topologicalBFS.indexOf(2));
        assertTrue(topologicalBFS.indexOf(1) < topologicalBFS.indexOf(4));
        assertTrue(topologicalBFS.indexOf(2) < topologicalBFS.indexOf(5));
        assertTrue(topologicalBFS.indexOf(3) < topologicalBFS.indexOf(4));
        assertTrue(topologicalBFS.indexOf(4) < topologicalBFS.indexOf(5));
        assertTrue(topologicalBFS.indexOf(5) < topologicalBFS.indexOf(6));
        assertTrue(topologicalBFS.indexOf(6) < topologicalBFS.indexOf(7));
        // checking the order of topological sort that allows parallel
        var topologicalPar = g.topologicalSortParallel();
        System.out.println("topological Parallel: " + topologicalPar.toString());
        assertEquals(6, topologicalPar.getStepNumber());
    }

    @Test
    public void test01() {
        GraphAL g = new GraphAL(9);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 3);
        g.addEdge(1, 4);
        g.addEdge(2, 5);
        g.addEdge(2, 6);
        g.addEdge(3, 7);
        g.addEdge(3, 8);
        g.print();
        // checking the order of topological sort using DFS
        var topologicalDFS = g.topologicalSortDFS();
        System.out.println("topological DFS: " + topologicalDFS.toString());
        assertTrue(topologicalDFS.indexOf(0) < topologicalDFS.indexOf(1));
        assertTrue(topologicalDFS.indexOf(0) < topologicalDFS.indexOf(2));
        assertTrue(topologicalDFS.indexOf(1) < topologicalDFS.indexOf(3));
        assertTrue(topologicalDFS.indexOf(1) < topologicalDFS.indexOf(4));
        assertTrue(topologicalDFS.indexOf(2) < topologicalDFS.indexOf(5));
        assertTrue(topologicalDFS.indexOf(2) < topologicalDFS.indexOf(6));
        assertTrue(topologicalDFS.indexOf(3) < topologicalDFS.indexOf(7));
        assertTrue(topologicalDFS.indexOf(3) < topologicalDFS.indexOf(8));
        // checking the order of topological sort using BFS
        var topologicalBFS = g.topologicalSortBFS();
        System.out.println("topological BFS: " + topologicalBFS.toString());
        assertTrue(topologicalBFS.indexOf(0) < topologicalBFS.indexOf(1));
        assertTrue(topologicalBFS.indexOf(0) < topologicalBFS.indexOf(2));
        assertTrue(topologicalBFS.indexOf(1) < topologicalBFS.indexOf(3));
        assertTrue(topologicalBFS.indexOf(1) < topologicalBFS.indexOf(4));
        assertTrue(topologicalBFS.indexOf(2) < topologicalBFS.indexOf(5));
        assertTrue(topologicalBFS.indexOf(2) < topologicalBFS.indexOf(6));
        assertTrue(topologicalBFS.indexOf(3) < topologicalBFS.indexOf(7));
        assertTrue(topologicalBFS.indexOf(3) < topologicalBFS.indexOf(8));
        // checking the order of topological sort that allows parallel
        var topologicalPar = g.topologicalSortParallel();
        System.out.println("topological Parallel: " + topologicalPar.toString());
        assertEquals(4, topologicalPar.getStepNumber());
    }
}