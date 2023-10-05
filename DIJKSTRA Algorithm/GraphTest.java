
// --== CS400 File Header Information ==--
// Name: Matej Popovski
// Email: popovski@wisc.edu
// Team: CI
// TA: ABHINAV AGARWAL
// Lecturer: Garry Dahl
// Notes to Grader: <optional extra notes>
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import java.util.ArrayList;

/**
 * Tests the implementation of CS400Graph for the individual component of
 * Project Three: the implementation of Dijsktra's Shortest Path algorithm.
 */
public class GraphTest {

	private CS400Graph<String, Integer> graph;

	/**
	 * Instantiate graph from last week's shortest path activity.
	 */
	@BeforeEach
	public void createGraph() {
		graph = new CS400Graph<>();
		// insert vertices A-F
		graph.insertVertex("A");
		graph.insertVertex("B");
		graph.insertVertex("C");
		graph.insertVertex("D");
		graph.insertVertex("E");
		graph.insertVertex("F");
		graph.insertEdge("A", "B", 6);
		graph.insertEdge("A", "C", 2);
		graph.insertEdge("A", "D", 5);
		graph.insertEdge("B", "E", 1);
		graph.insertEdge("B", "C", 2);
		graph.insertEdge("C", "B", 3);
		graph.insertEdge("C", "F", 1);
		graph.insertEdge("D", "E", 3);
		graph.insertEdge("E", "A", 4);
		graph.insertEdge("F", "A", 1);
		graph.insertEdge("F", "D", 1);
	}

	/**
	 * This method checks the distance/total weight cost from the vertex A to F.
	 */
	@Test
	public void testPathCostAtoF() {
		assertTrue(graph.getPathCost("A", "F") == 3);
	}

	/**
	 * This method checks the distance/total weight cost from the vertex A to D.
	 */
	@Test
	public void testPathCostAtoD() {
		assertTrue(graph.getPathCost("A", "D") == 4);
	}

	/**
	 * This method checks the ordered sequence of data within vertices from the
	 * vertex A to D.
	 */
	@Test
	public void testPathAtoD() {
		assertTrue(graph.shortestPath("A", "D").toString().equals("[A, C, F, D]"));
	}

	/**
	 * This method checks the ordered sequence of data within vertices from the
	 * vertex A to F.
	 */
	@Test
	public void testPathAtoF() {
		assertTrue(graph.shortestPath("A", "F").toString().equals("[A, C, F]"));
	}

	/**
	 * This method checks the ordered sequence of data within vertices from the
	 * vertex A to E.
	 */
	@Test 
	public void testPathAtoE() {
		assertTrue(graph.shortestPath("A", "E").toString().equals("[A, C, B, E]"));
	}

	/**
	 * This method checks the predecessor of B along the path from F to B.
	 */
	@Test 
	public void testPredecessorFtoB() {
		ArrayList<String> path = new ArrayList<String>();
		path.addAll(graph.dijkstrasShortestPath("F", "B").dataSequence);
		assertEquals("C", path.get(path.size() - 2));
	}

	/**
	 * This method checks the predecessor of B from the vertex F to B.
	 */
	@Test 
	public void testNodeBPredecessorFtoB() {
		assertTrue(graph.shortestPath("F", "B").get(graph.shortestPath("F", "B").size() - 2).equals("C"));
	}

	/**
	 * This method checks the distance and total weight cost from the vertex D to B.
	 */
	@Test 
	public void testPathCostDtoB() {
		assertEquals(12, graph.getPathCost("D", "B"));
	}

	/**
	 * This method checks the distance/total weight cost from the vertex A to E.
	 */
	@Test 
	public void testPathCostAtoE() {
		assertTrue(graph.getPathCost("A", "E") == 6);
	}

	/**
	 * This method checks the distance/total weight cost from the vertex E to F.
	 */
	@Test 
	public void testPathCostEtoF() {
		assertEquals(7, graph.getPathCost("E", "F"));
	}

	/**
	 * This method checks the ordered sequence of data within vertices from the
	 * vertex E to B.
	 */
	@Test 
	public void testPathEtoB() {
		assertTrue(graph.shortestPath("E", "B").toString().equals("[E, A, C, B]"));
	}

	/**
	 * This method checks that no paths ever take the edge from A to B since it's
	 * more efficient to go A-C-B instead.
	 */
	@Test 
	public void testNoPathsTakeEdgeAtoB() {
		String[] vertices = { "A", "B", "C", "D", "E", "F" };

		for (String start : vertices) {
			for (String end : vertices) {
				assertFalse(graph.shortestPath(start, end).toString().contains("A, B"));
			}
		}
	}

	/**
	 * This method checks the distance/total weight cost from the vertex B to F.
	 */
	@Test 
	public void testPathCostBtoF() {
		assertTrue(graph.getPathCost("B", "F") == 3);
	}

	/**
	 * This method checks the ordered sequence of data within vertices from the
	 * vertex D to B.
	 */
	@Test 
	public void testPathDtoB() {
		assertEquals("[D, E, A, C, B]", graph.shortestPath("D", "B").toString());
	}

}