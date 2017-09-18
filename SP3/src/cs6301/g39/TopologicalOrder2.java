/** Class  for Topological Order Algorithm 2. Run DFS on g and add nodes to the front of the output list,
    *  in the order in which they finish.  Try to write code without using global variables.
 *  @author Khushbu Patil, Vatsal Patel, Shruti Shetye
 *  Ver 1.0: 2017/09/16
 *  Usage: TopologicalOrder2.topologicalOrder2Graph g)
*/
package cs6301.g39;

import java.util.Iterator;
import java.util.List;

public class TopologicalOrder2 {
	
	/**
	 * @param g - Graph to be processes
	 * @return - List of vertices in the topological order
	 */
	public static List<Graph.Vertex> topologicalOrder2(Graph g) {
		
		Iterator<Graph.Vertex> itOrder = g.iterator();
		DFS dfsTrav = new DFS(g);
		dfsTrav.dfs(itOrder);
		
		if(!dfsTrav.isDAG)
			return null;
		
		return dfsTrav.decFinList;
	}
}
