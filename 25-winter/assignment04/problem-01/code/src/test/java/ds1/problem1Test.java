package ds1;

import org.junit.jupiter.api.Test;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

public class problem1Test {

    @Test
    public void part1() {
        Set<Edge> result = problem1.part1();
        //MST with 8 vertices must have 7 edges
        assertEquals(7, result.size());
        //the cost should be 35 as we calculated in problem-01.md(dry part)
        assertEquals(35, problem1.cost(result));
        assertTrue(problem1.containsEdge(result, 'G', 'H', 2));
        assertTrue(problem1.containsEdge(result, 'C', 'E', 3));
        assertTrue(problem1.containsEdge(result, 'B', 'D', 4));
        assertTrue(problem1.containsEdge(result, 'A', 'C', 5));
        assertTrue(problem1.containsEdge(result, 'D', 'F', 6));
        assertTrue(problem1.containsEdge(result, 'E', 'G', 7));
        //since we know this is not unique, thus both edges are valid
        assertTrue(problem1.containsEdge(result, 'C', 'D', 8) 
            ||problem1.containsEdge(result, 'E', 'F', 8));
    }
}


