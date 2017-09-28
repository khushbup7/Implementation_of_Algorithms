/** Class to find Strongly connected components of a directed graph.
 *  @author Khushbu Patil, Vatsal Patel, Shruti Shetye
 *  Ver 1.0: 2017/09/16
 *  Usage: StronglyConnectedComponents.stronglyConnectedComponents(Graph g)
*/
package cs6301.g39;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class StronglyConnectedComponents {

	/**
	 * @param g
	 *            - Graph to be processed
	 * @return - Number of strongly connected components in the given graph
	 */
	public static int stronglyConnectedComponents(Graph g) {

		Iterator<Graph.Vertex> itOrder = g.iterator();
		DFS dfsTrav = new DFS(g);
		dfsTrav.dfs(itOrder);

		LinkedList<Graph.Vertex> decFinOrd = new LinkedList<Graph.Vertex>();

		for (Graph.Vertex v : dfsTrav.decFinList)
			decFinOrd.add(v);

		for (Graph.Vertex t : g) {
			List<Graph.Edge> temp = t.adj;
			t.adj = t.revAdj;
			t.revAdj = temp;
		}

		dfsTrav.reinitialize();
		dfsTrav.dfs(decFinOrd.iterator());
		return dfsTrav.cNo;
	}

}
