/** Class to find Strongly connected components of a directed graph.
 *  @author Khushbu Patil, Vatsal Patel, Shruti Shetye
 *  Ver 1.0: 2017/09/16
 *  Usage: StronglyConnectedComponents.stronglyConnectedComponents(Graph g)
*/
package cs6301.g39;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import cs6301.g00.Graph;

public class StronglyConnectedComponents {

	DFS dfsTrav;
	int numComponents;
	Graph g;

	public StronglyConnectedComponents(Graph g) {
		this.g = g;
	}

	/**
	 * @param g
	 *            - Graph to be processed
	 * @return - Number of strongly connected components in the given graph
	 */
	public int stronglyConnectedComponents() {

		Iterator<Graph.Vertex> itOrder = g.iterator();
		dfsTrav = new DFS(g);
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

		for (Graph.Vertex t : g) {
			List<Graph.Edge> temp = t.adj;
			t.adj = t.revAdj;
			t.revAdj = temp;
		}

		numComponents = dfsTrav.cNo;
		return numComponents;
	}

}
