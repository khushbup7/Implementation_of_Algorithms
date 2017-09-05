/** 
 *  Class for calculating diameter of a given graph. 
 *  This class use BFS() method from BreadFirstSearch class.
 *  
 *  @author Khushbu Patil, Vatsal Patel, Shruti Shetye
 *  Ver 1.2: 2017/09/02
 *  
 *  Usage: Diameter.diameter(g)
 *  Here, g is a Graph.
 */

package cs6301.g39;

import cs6301.g00.Graph;
import cs6301.g39.BreadthFirstSearch;
import java.util.LinkedList;

public class Diameter {

	/**
	 * @param g
	 *            - Graph
	 * @return - Longest path between any two vertices in a graph, length which is a
	 *         diameter of graph
	 */
	public static LinkedList<Graph.Vertex> diameter(Graph g) {

		// Choosing an arbitrary vertex
		Graph.Vertex v1 = g.getVertex(1);

		// Running BFS on a graph starting at v1 as a root
		LinkedList<Graph.Vertex> bfs1 = BreadthFirstSearch.BFS(g, v1);

		// Running BFS on a graph starting at a node at maximum distance from v1 as a
		// root
		LinkedList<Graph.Vertex> bfs2 = BreadthFirstSearch.BFS(g, bfs1.getLast());

		return bfs2;
	}

}
