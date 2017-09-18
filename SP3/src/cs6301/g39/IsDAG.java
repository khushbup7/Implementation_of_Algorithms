/** Class to implement Question 4. Checks if the given graph is a DAG using DFS
 * @author Khushbu Patil, Vatsal Patel, Shruti Shetye
 *  Ver 1.0: 2017/09/16
*/
package cs6301.g39;

import java.util.Iterator;

public class IsDAG {

	/**
	 * @param g
	 *            - Graph to be processes
	 * @return - boolean value if the given Graph is true or false
	 */
	public static boolean isDAG(Graph g) {

		Iterator<Graph.Vertex> itOrder = g.iterator();
		DFS dfsTrav = new DFS(g);
		dfsTrav.dfs(itOrder);

		if (!dfsTrav.isDAG)
			return false;

		return true;
	}

}
