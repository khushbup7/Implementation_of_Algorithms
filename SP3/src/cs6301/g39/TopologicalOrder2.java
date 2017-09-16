package cs6301.g39;

import java.util.Iterator;
import java.util.List;

public class TopologicalOrder2 {
	
	public static List<Graph.Vertex> topologicalOrder2(Graph g) {
		
		Iterator<Graph.Vertex> itOrder = g.iterator();
		DFS dfsTrav = new DFS(g);
		dfsTrav.dfs(itOrder);
		
		if(!dfsTrav.isDAG)
			return null;
		
		return dfsTrav.decFinList;
	}
}
