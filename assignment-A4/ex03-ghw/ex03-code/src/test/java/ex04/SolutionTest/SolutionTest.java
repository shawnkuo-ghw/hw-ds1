package ex04.SolutionTest;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import ex04.Solution;
import ex04.collections.interfaces.*;
import ex04.collections.implementations.*;

public class SolutionTest {

    private static final int N = 6;
    Graph<Station> graph;

    @Test
    public void test01()
    {
        System.out.println("Test case 01:");
        // initialize vertices
        Station stationA = new Station(0, "A");
        Station stationB = new Station(1, "B");
        Station stationC = new Station(2, "C");
        Station stationD = new Station(3, "D");
        Station stationX = new Station(4, "X");
        Station stationY = new Station(5, "Y");
        List<Station> stationsList = new LinkedList<Station>();
        stationsList.append(stationA);
        stationsList.append(stationB);
        stationsList.append(stationC);
        stationsList.append(stationD);
        stationsList.append(stationX);
        stationsList.append(stationY);
        // initialize graph and edges
        graph = new DirectedGraphAL<Station>(N, stationsList);
        graph.addEdge(stationA, stationC);
        graph.addEdge(stationA, stationX);
        graph.addEdge(stationB, stationY);
        graph.addEdge(stationC, stationY);
        graph.addEdge(stationD, stationB);
        graph.addEdge(stationX, stationC);
        graph.addEdge(stationX, stationD);
        graph.showGraphInfo();
        // get the connvinient path
        List<Station> sol = Solution.convenientPath(graph, stationA, stationX, stationY);
        assertEquals("[0:A, 4:X, 2:C, 5:Y]", sol.toString());
    }

    @Test
    public void test02()
    {
        System.out.println("\nTest case 02:");
        // initialize vertices
        Station stationA = new Station(0, "A");
        Station stationB = new Station(1, "B");
        Station stationC = new Station(2, "C");
        Station stationD = new Station(3, "D");
        Station stationX = new Station(4, "X");
        Station stationY = new Station(5, "Y");
        List<Station> stationsList = new LinkedList<Station>();
        stationsList.append(stationA);
        stationsList.append(stationB);
        stationsList.append(stationC);
        stationsList.append(stationD);
        stationsList.append(stationX);
        stationsList.append(stationY);
        // initialize graph and edges
        graph = new DirectedGraphAL<Station>(N, stationsList);
        graph.addEdge(stationA, stationB);
        graph.addEdge(stationA, stationD);
        graph.addEdge(stationA, stationX);
        graph.addEdge(stationB, stationD);
        graph.addEdge(stationB, stationX);
        graph.addEdge(stationC, stationB);
        graph.addEdge(stationC, stationY);
        graph.addEdge(stationD, stationY);
        graph.addEdge(stationX, stationC);
        graph.showGraphInfo();

        // get the connvinient path
        List<Station> sol = Solution.convenientPath(graph, stationA, stationX, stationY);
        assertEquals("[0:A, 4:X, 2:C, 5:Y]", sol.toString());
    }
}